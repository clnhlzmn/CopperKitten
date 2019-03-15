import java.lang.RuntimeException
import javax.lang.model.type.ErrorType

class Infer {

    data class Constraint(val t1: Type, val t2: Type) {
        override fun toString(): String =
            "($t1 = $t2)"
    }

    companion object {

        //collect constraints from a list of expr
        fun collect(la: List<Expr>): Sequence<Constraint> =
            la.map { a -> collect(a) }.fold(emptySequence<Constraint>()) { acc, seq -> acc + seq }

        //recursively generate a set of constraints from an expr
        fun collect(e: Expr): Sequence<Constraint> =
            when (e) {
                Expr.Unit -> sequenceOf(Constraint(e.aType, Type.Unit))
                is Expr.Sequence -> collect(e.first) + collect(e.second) + sequenceOf(Constraint(e.aType, e.second.aType))
                is Expr.Natural -> sequenceOf(Constraint(e.aType, Type.Int))
                is Expr.Ref -> {
                    val def = e.accept(GetDefinitionVisitor())
                    when (def) {
                        null -> sequenceOf(Constraint(e.aType, Type.Error("$e is undefined")))
                        is Definition.Let -> sequenceOf(Constraint(e.aType, def.node.value.aType))
                        is Definition.Param -> sequenceOf(Constraint(e.aType, def.node.aType))
                    }
                }
                is Expr.Apply ->
                    collect(e.fn) + collect(e.args) +
                        sequenceOf(Constraint(e.fn.aType, Type.Fun(e.args.map { a -> a.aType }, e.aType)))
                is Expr.Unary ->
                    collect(e.operand) +
                        sequenceOf(Constraint(e.aType, Type.Int), Constraint(e.operand.aType, Type.Int))
                is Expr.Binary ->
                    collect(e.lhs) + collect(e.rhs) +
                        sequenceOf(
                            Constraint(e.aType, Type.Int),
                            Constraint(e.lhs.aType, Type.Int), Constraint(e.rhs.aType, Type.Int)
                        )
                is Expr.Cond -> collect(e.cond) + collect(e.csq) + collect(e.alt) +
                    sequenceOf(Constraint(e.cond.aType, Type.Int), Constraint(e.csq.aType, e.alt.aType),
                        Constraint(e.aType, e.csq.aType), Constraint(e.aType, e.alt.aType)
                    )
                is Expr.Assign -> TODO()
                is Expr.Fun -> collect(e.body) +
                    sequenceOf(Constraint(e.aType, Type.Fun(e.params.map { p -> p.aType }, e.body.aType)))
                is Expr.CFun -> sequenceOf(Constraint(e.aType, e.sig))
                is Expr.Let -> collect(e.value) + collect(e.body) + sequenceOf(Constraint(e.aType, e.body.aType))
                is Expr.If ->
                    if (e.alt == null)
                        collect(e.cond) + collect(e.csq) +
                            sequenceOf(Constraint(e.aType, Type.Unit), Constraint(e.csq.aType, Type.Unit))
                    else
                        collect(e.cond) + collect(e.csq) + collect(e.alt) +
                            sequenceOf(Constraint(e.aType, e.csq.aType), Constraint(e.aType, e.alt.aType),
                                Constraint(e.csq.aType, e.alt.aType))
                is Expr.While ->
                    collect(e.cond) + collect(e.body) +
                        sequenceOf(Constraint(e.cond.aType, Type.Int), Constraint(e.body.aType, Type.Unit),
                            Constraint(e.aType, Type.Unit)
                        )
                is Expr.Break -> TODO()
            }

        fun substitute(u: Type, x: String, t: Type): Type =
            if (u is Type.Error)
                u
            else when (t) {
                is Type.Unit -> t
                is Type.Int -> t
                is Type.Unknown -> if (t.id == x) u else t
                is Type.Fun -> Type.Fun(t.paramTypes.map { p -> substitute(u, x, p) }, substitute(u, x, t.returnType))
                is Type.Error -> t
            }

        data class Substitution(val x: String, val u: Type)

        fun apply(subs: Sequence<Substitution>, t: Type): Type =
            subs.toList().foldRight(t){ sub, t -> substitute(sub.u, sub.x, t) }

        fun unify(constraints: Sequence<Constraint>): Sequence<Substitution> {
            val first = constraints.firstOrNull()
            return if (first == null) {
                emptySequence()
            } else {
                val t2 = unify(constraints.drop(1))
                val t1 = unifyOne(apply(t2, first.t1), apply(t2, first.t2))
                t1 + t2
            }
        }

        fun unifyOne(t1: Type, t2: Type): Sequence<Substitution> {
            return when {
                t1 is Type.Error -> emptySequence()
                t2 is Type.Error -> emptySequence()
                t1 is Type.Unit && t2 is Type.Unit -> emptySequence()
                t1 is Type.Int && t2 is Type.Int -> emptySequence()
                t1 is Type.Unknown -> sequenceOf(Substitution(t1.id, t2))
                t2 is Type.Unknown -> sequenceOf(Substitution(t2.id, t1))
                //both fun types then unify argument types and return type
                t1 is Type.Fun && t2 is Type.Fun -> {
                    if (t1.paramTypes.size != t2.paramTypes.size)
                        return sequenceOf(Substitution(Type.newUnknown().id, Type.Error("mismatched number of arguments between $t1 and $t2")))
                    unify(
                        t1.paramTypes.zip(t2.paramTypes).map { p -> Constraint(p.first, p.second) }.asSequence() +
                            sequenceOf(Constraint(t1.returnType, t2.returnType))
                    )
                }
                else -> sequenceOf(Substitution(Type.newUnknown().id, Type.Error("mismatched types between $t1 and $t2")))
            }
        }

        fun applyExpr(subs: Sequence<Substitution>, e: Expr) {
            when (e) {
                is Expr.Unit -> e.aType = apply(subs, e.aType)
                is Expr.Natural -> e.aType = apply(subs, e.aType)
                is Expr.Sequence -> {
                    applyExpr(subs, e.first)
                    applyExpr(subs, e.second)
                    e.aType = apply(subs, e.aType)
                }
                is Expr.Unary -> {
                    applyExpr(subs, e.operand)
                    e.aType = apply(subs, e.aType)
                }
                is Expr.Binary -> {
                    applyExpr(subs, e.lhs)
                    applyExpr(subs, e.rhs)
                    e.aType = apply(subs, e.aType)
                }
                is Expr.Ref -> e.aType = apply(subs, e.aType)
                is Expr.Let -> {
                    applyExpr(subs, e.value)
                    applyExpr(subs, e.body)
                    e.aType = apply(subs, e.aType)
                }
                is Expr.Fun -> {
                    applyExpr(subs, e.body)
                    e.params.forEach { p -> p.aType = apply(subs, p.aType) }
                    e.aType = apply(subs, e.aType)
                }
                is Expr.CFun -> e.aType = apply(subs, e.aType)
                is Expr.Apply -> {
                    applyExpr(subs, e.fn)
                    e.args.forEach { a ->
                        applyExpr(subs, a)
                    }
                    e.aType = apply(subs, e.aType)
                }
                is Expr.Cond -> {
                    applyExpr(subs, e.cond)
                    applyExpr(subs, e.csq)
                    applyExpr(subs, e.alt)
                    e.aType = apply(subs, e.aType)
                }
                is Expr.Assign -> TODO()
                is Expr.If -> {
                    applyExpr(subs, e.cond)
                    applyExpr(subs, e.csq)
                    if (e.alt != null) applyExpr(subs, e.alt)
                    e.aType = apply(subs, e.aType)
                }
                is Expr.While -> {
                    applyExpr(subs, e.body)
                    applyExpr(subs, e.cond)
                    e.aType = apply(subs, e.aType)
                }
                is Expr.Break -> TODO()
            }
        }

        //env included in e
        fun infer(e: Expr) {
            if (e.aType !is Type.Unknown) {
                e.aType = Type.Error("inference failed")
                return
            }
            val constraints = collect(e)
            val subs = unify(constraints)
            val errors = subs.filter { s -> s.u is Type.Error }.map { s -> s.u as Type.Error }
            if (errors.toList().isNotEmpty()) {
                e.aType = Type.Error(errors.map { e -> e.what }.toList().toString(", "))
                return
            }
            applyExpr(subs, e)
        }

    }

}

