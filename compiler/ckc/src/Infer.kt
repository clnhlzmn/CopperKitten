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
                Expr.Unit -> sequenceOf(Constraint(e.t, Type.Unit))
                is Expr.Sequence -> collect(e.first) + collect(e.second) + sequenceOf(Constraint(e.t, e.second.t))
                is Expr.Natural -> sequenceOf(Constraint(e.t, Type.Int))
                is Expr.Ref -> {
                    val def = e.accept(GetDefinitionVisitor())
                    when (def) {
                        null -> throw RuntimeException("$e is undefined")
                        is Definition.Let -> sequenceOf(Constraint(e.t, def.node.bindingT))
                        is Definition.Param -> sequenceOf(Constraint(e.t, def.node.t))
                    }
                }
                is Expr.Apply ->
                    collect(e.fn) + collect(e.args) +
                        sequenceOf(Constraint(e.fn.t, Type.Fun(e.args.map { a -> a.t }, e.t)))
                is Expr.Unary ->
                    collect(e.operand) + sequenceOf(Constraint(e.t, Type.Int), Constraint(e.operand.t, Type.Int))
                is Expr.Binary ->
                    collect(e.lhs) + collect(e.rhs) +
                    sequenceOf(Constraint(e.t, Type.Int), Constraint(e.lhs.t, Type.Int), Constraint(e.rhs.t, Type.Int))
                is Expr.Cond -> collect(e.cond) + collect(e.csq) + collect(e.alt) +
                    sequenceOf(Constraint(e.cond.t, Type.Int), Constraint(e.csq.t, e.alt.t),
                        Constraint(e.t, e.csq.t), Constraint(e.t, e.alt.t)
                    )
                is Expr.Assign -> TODO()
                is Expr.Fun -> collect(e.body) +
                    sequenceOf(Constraint(e.t, Type.Fun(e.params.map { p -> p.t }, e.body.t)))
                is Expr.CFun -> sequenceOf(Constraint(e.t, e.sig))
                is Expr.Let -> collect(e.value) + collect(e.body) +
                    sequenceOf(Constraint(e.bindingT, e.value.t), Constraint(e.t, e.body.t))
                is Expr.If ->
                    if (e.alt == null)
                        collect(e.cond) + collect(e.csq) +
                            sequenceOf(Constraint(e.t, Type.Unit), Constraint(e.csq.t, Type.Unit))
                    else
                        collect(e.cond) + collect(e.csq) + collect(e.alt) +
                            sequenceOf(Constraint(e.t, e.csq.t), Constraint(e.t, e.alt.t), Constraint(e.csq.t, e.alt.t))
                is Expr.While -> TODO()
                is Expr.Break -> TODO()
            }

    }

}

