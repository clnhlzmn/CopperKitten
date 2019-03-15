import java.lang.RuntimeException

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
                        null -> throw RuntimeException("$e is undefined")
                        is Definition.Let -> sequenceOf(Constraint(e.aType, def.node.bType))
                        is Definition.Param -> sequenceOf(Constraint(e.aType, def.node.aType))
                    }
                }
                is Expr.Apply ->
                    collect(e.fn) + collect(e.args) +
                        sequenceOf(Constraint(e.fn.aType, Type.Fun(e.args.map { a -> a.aType }, e.aType)))
                is Expr.Unary ->
                    collect(e.operand) + sequenceOf(Constraint(e.aType, Type.Int), Constraint(e.operand.aType, Type.Int))
                is Expr.Binary ->
                    collect(e.lhs) + collect(e.rhs) +
                    sequenceOf(Constraint(e.aType, Type.Int), Constraint(e.lhs.aType, Type.Int), Constraint(e.rhs.aType, Type.Int))
                is Expr.Cond -> collect(e.cond) + collect(e.csq) + collect(e.alt) +
                    sequenceOf(Constraint(e.cond.aType, Type.Int), Constraint(e.csq.aType, e.alt.aType),
                        Constraint(e.aType, e.csq.aType), Constraint(e.aType, e.alt.aType)
                    )
                is Expr.Assign -> TODO()
                is Expr.Fun -> collect(e.body) +
                    sequenceOf(Constraint(e.aType, Type.Fun(e.params.map { p -> p.aType }, e.body.aType)))
                is Expr.CFun -> sequenceOf(Constraint(e.aType, e.sig))
                is Expr.Let -> collect(e.value) + collect(e.body) +
                    sequenceOf(Constraint(e.bType, e.value.aType), Constraint(e.aType, e.body.aType))
                is Expr.If ->
                    if (e.alt == null)
                        collect(e.cond) + collect(e.csq) +
                            sequenceOf(Constraint(e.aType, Type.Unit), Constraint(e.csq.aType, Type.Unit))
                    else
                        collect(e.cond) + collect(e.csq) + collect(e.alt) +
                            sequenceOf(Constraint(e.aType, e.csq.aType), Constraint(e.aType, e.alt.aType), Constraint(e.csq.aType, e.alt.aType))
                is Expr.While -> TODO()
                is Expr.Break -> TODO()
            }

        fun substitute(u: Type, x: String, t: Type): Type =
            when (t) {
                is Type.Unit -> t
                is Type.Int -> t
                is Type.Unknown -> if (t.id == x) u else t
                is Type.Fun -> Type.Fun(t.paramTypes.map { p -> substitute(u, x, p) }, substitute(u, x, t.returnType))
                is Type.Error -> throw RuntimeException(t.toString())
            }

        data class Substitution(val x: String, val u: Type)

        fun apply(subs: Sequence<Substitution>, t: Type): Type =
            subs.toList().foldRight(t){ sub, t -> substitute(sub.u, sub.x, t) }

//        let rec unify (constraints: (primitiveType * primitiveType) list) : substitutions =
//          match constraints with
//              | [] -> []
//              | (x, y) :: xs ->
//                  (* generate substitutions of the rest of the list *)
//                  let t2 = unify xs in
//                  (* resolve the LHS and RHS of the constraints from the previous substitutions *)
//                  let t1 = unify_one (apply t2 x) (apply t2 y) in
//                  t1 @ t2

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

//        and unify_one (t1: primitiveType) (t2: primitiveType) : substitutions =
//          match t1, t2 with
//          | TNum, TNum | TBool, TBool -> []
//          | T(x), z | z, T(x) -> [(x, z)]
//
//          (* This case is particularly useful when you are calling a function that returns a function *)
//          | TFun(a, b), TFun(x, y) -> unify [(a, x); (b, y)]
//          | _ -> raise (failwith "mismatched types")

        fun unifyOne(t1: Type, t2: Type): Sequence<Substitution> {
            return when {
                t1 is Type.Unit && t2 is Type.Unit -> emptySequence()
                t1 is Type.Int && t2 is Type.Int -> emptySequence()
                t1 is Type.Unknown -> sequenceOf(Substitution(t1.id, t2))
                t2 is Type.Unknown -> sequenceOf(Substitution(t2.id, t1))
                //both fun types then unify argument types and return type
                t1 is Type.Fun && t2 is Type.Fun ->
                    unify(
                        t1.paramTypes.zip(t2.paramTypes).map { p -> Constraint(p.first, p.second) }.asSequence() +
                            sequenceOf(Constraint(t1.returnType, t2.returnType))
                    )
                else -> throw RuntimeException("mismatched types $t1 and $t2")
            }
        }

//        let rec apply_expr (subs: substitutions) (ae: aexpr): aexpr =
//          match ae with
//              | ABoolLit(b, t) -> ABoolLit(b, apply subs t)
//              | ANumLit(n, t) -> ANumLit(n, apply subs t)
//              | AVal(s, t) -> AVal(s, apply subs t)
//              | ABinop(e1, op, e2, t) -> ABinop(apply_expr subs e1, op, apply_expr subs e2, apply subs t)
//              | AFun(id, e, t) -> AFun(id, apply_expr subs e, apply subs t)
//              | AApp(fn, arg, t) -> AApp(apply_expr subs fn, apply_expr subs arg, apply subs t)
//        ;;

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
                    e.bType = apply(subs, e.bType)
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
                is Expr.While -> TODO()
                is Expr.Break -> TODO()
            }
        }

//        let infer (env: environment) (e: expr) : aexpr =
//          let annotated_expr = annotate_expr e env in
//          let constraints = collect_expr annotated_expr in
//          let subs = unify constraints in
//          (* reset the type counter after completing inference *)
//          type_variable := (Char.code 'a');
//          apply_expr subs annotated_expr
//        ;;

        //env included in e
        fun infer(e: Expr) {
            val constraints = collect(e)
            println(constraints.toList().toString(" "))
            val subs = unify(constraints)
            applyExpr(subs, e)
            println(e)
        }

    }

}

