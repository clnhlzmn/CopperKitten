
class Infer {

    data class Constraint(val t1: Type, val t2: Type) {
        override fun toString(): String =
            "($t1 = $t2)"
    }

    companion object {

        //collect constraints from a list of expr
        fun collect(env: Env<Type>?, la: List<Expr>): Sequence<Constraint> =
            la.map { a -> collect(env, a) }.fold(emptySequence()) { acc, seq -> acc + seq }

        //recursively generate a set of constraints from an expr
        fun collect(env: Env<Type>?, e: Expr): Sequence<Constraint> =
            when (e) {
                Expr.Unit -> sequenceOf(Constraint(e.t, Type.Unit))
                is Expr.Sequence -> collect(env, e.first) + collect(env, e.second) +
                    sequenceOf(Constraint(e.t, e.second.t))
                is Expr.Natural -> sequenceOf(Constraint(e.t, Type.Int))
                is Expr.Ref -> {
                    val refT = if (env == null) null else Env.lookup(e.id, env)
                    if (refT == null)
                        sequenceOf(Constraint(Type.newVar(), Type.Error("$e is undefined")))
                    else
                        sequenceOf(Constraint(e.t, refT))
                }
                is Expr.Apply ->
                    collect(env, e.fn) + collect(env, e.args) +
                        sequenceOf(Constraint(e.fn.t, Type.Fun(e.args.map { a -> a.t }, e.t)))
                is Expr.Unary ->
                    collect(env, e.operand) +
                        sequenceOf(Constraint(e.t, Type.Int), Constraint(e.operand.t, Type.Int))
                is Expr.Binary ->
                    collect(env, e.lhs) + collect(env, e.rhs) +
                        sequenceOf(
                            Constraint(e.t, Type.Int),
                            Constraint(e.lhs.t, Type.Int), Constraint(e.rhs.t, Type.Int)
                        )
                is Expr.Cond -> collect(env, e.cond) + collect(env, e.csq) + collect(env, e.alt) +
                    sequenceOf(Constraint(e.cond.t, Type.Int), Constraint(e.csq.t, e.alt.t),
                        Constraint(e.t, e.csq.t), Constraint(e.t, e.alt.t)
                    )
                is Expr.Assign -> collect(env, e.target) + collect(env, e.value) +
                    sequenceOf(Constraint(e.target.t, e.value.t), Constraint(e.t, e.value.t))
                is Expr.Fun ->
                    if (e.params.distinctBy { p -> p.id }.count() != e.params.size)
                        sequenceOf(Constraint(Type.newVar(), Type.Error("$e must have distinct parameter names")))
                    else
                        collect(Env.extend(e.params.map { p -> Pair(p.id, p.t) }, env), e.body) +
                            sequenceOf(Constraint(e.t, Type.Fun(e.params.map { p -> p.t }, e.body.t)))
                is Expr.CFun -> sequenceOf(Constraint(e.t, e.sig))
                is Expr.Let ->
                    collect(env, e.value) + collect(Env.extend(e.id, e.value.t, env), e.body) +
                        sequenceOf(Constraint(e.t, e.body.t))
                is Expr.If ->
                    if (e.alt == null)
                        collect(env, e.cond) + collect(env, e.csq) +
                            sequenceOf(Constraint(e.t, Type.Unit), Constraint(e.csq.t, Type.Unit))
                    else
                        collect(env, e.cond) + collect(env, e.csq) + collect(env, e.alt) +
                            sequenceOf(Constraint(e.t, e.csq.t), Constraint(e.t, e.alt.t),
                                Constraint(e.csq.t, e.alt.t))
                is Expr.While ->
                    collect(env, e.cond) + collect(env, e.body) +
                        sequenceOf(Constraint(e.cond.t, Type.Int), Constraint(e.body.t, Type.Unit),
                            Constraint(e.t, Type.Unit)
                        )
                is Expr.Break -> TODO()
            }

        fun substitute(u: Type, x: String, t: Type): Type =
            if (u is Type.Error)
                u
            else when (t) {
                is Type.Unit -> t
                is Type.Int -> t
                is Type.Var -> if (t.id == x) u else t
                is Type.Fun -> Type.Fun(t.paramTypes.map { p -> substitute(u, x, p) }, substitute(u, x, t.returnType))
                is Type.Error -> t
            }

        data class Substitution(val x: String, val u: Type)

        fun apply(subs: Sequence<Substitution>, t: Type): Type =
            subs.toList().foldRight(t){ sub, tAcc -> substitute(sub.u, sub.x, tAcc) }

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
                t1 is Type.Unit && t2 is Type.Unit -> emptySequence()
                t1 is Type.Int && t2 is Type.Int -> emptySequence()
                t1 is Type.Var ->
                    sequenceOf(Substitution(t1.id, t2))
                t2 is Type.Var ->
                    sequenceOf(Substitution(t2.id, t1))
                //both fun types then unify argument types and return type
                t1 is Type.Fun && t2 is Type.Fun -> {
                    if (t1.paramTypes.size != t2.paramTypes.size)
                        return sequenceOf(Substitution(Type.newVar().id, Type.Error("mismatched number of arguments between $t1 and $t2")))
                    unify(
                        t1.paramTypes.zip(t2.paramTypes).map { p -> Constraint(p.first, p.second) }.asSequence() +
                            sequenceOf(Constraint(t1.returnType, t2.returnType))
                    )
                }
                t2 is Type.Error -> emptySequence()
                t1 is Type.Error -> emptySequence()
                else -> sequenceOf(Substitution(Type.newVar().id, Type.Error("mismatched types between $t1 and $t2")))
            }
        }

        fun applyExpr(subs: Sequence<Substitution>, e: Expr): Expr {
            return when (e) {
                is Expr.Unit -> e
                is Expr.Natural -> e
                is Expr.Sequence ->
                    Expr.Sequence(applyExpr(subs, e.first), applyExpr(subs, e.second), apply(subs, e.t))
                is Expr.Unary ->
                    Expr.Unary(e.operator, applyExpr(subs, e.operand), apply(subs, e.t))
                is Expr.Binary ->
                    Expr.Binary(applyExpr(subs, e.lhs), e.operator, applyExpr(subs, e.rhs), apply(subs, e.t))
                is Expr.Ref -> Expr.Ref(e.id, apply(subs, e.t))
                is Expr.Let ->
                    Expr.Let(e.id, applyExpr(subs, e.value), applyExpr(subs, e.body), apply(subs, e.t))
                is Expr.Fun ->
                    Expr.Fun(e.params.map { p -> Expr.Fun.Param(p.id, p.declType, apply(subs, p.t)) },
                        e.declType, applyExpr(subs, e.body), apply(subs, e.t))
                is Expr.CFun -> Expr.CFun(e.id, e.sig, apply(subs, e.t))
                is Expr.Apply ->
                    Expr.Apply(applyExpr(subs, e.fn), e.args.map { a -> applyExpr(subs, a)}, apply(subs, e.t))
                is Expr.Cond ->
                    Expr.Cond(applyExpr(subs, e.cond), applyExpr(subs, e.csq), applyExpr(subs, e.alt), apply(subs, e.t))
                is Expr.Assign -> Expr.Assign(applyExpr(subs, e.target), applyExpr(subs, e.value), apply(subs, e.t))
                is Expr.If ->
                    Expr.If(applyExpr(subs, e.cond), applyExpr(subs, e.csq),
                        if (e.alt != null) applyExpr(subs, e.alt) else null, apply(subs, e.t))
                is Expr.While ->
                    Expr.While(applyExpr(subs, e.cond), applyExpr(subs, e.body), apply(subs, e.t))
                is Expr.Break -> TODO()
            }
        }

        //env included in e
        fun infer(e: Expr): Expr {
            if (e.t !is Type.Var) {
                return Expr.Ref("error", Type.Error("inference failed"))
            }
            val constraints = collect(null, e)
            val subs = unify(constraints)
            val errors = subs.filter { s -> s.u is Type.Error }.map { s -> s.u as Type.Error }
            if (errors.toList().isNotEmpty()) {
                return Expr.Ref("error", Type.Error(errors.map { err -> err.what }.toList().toString(", ")))
            }
            return applyExpr(subs, e)
        }

    }

}

