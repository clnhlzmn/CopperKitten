

class ComputeInstancesVisitor: BaseASTVisitor<Unit>() {

    companion object {

        //add an actual type to a polymorphic definition
        fun addInstance(e: Expr, t: Type) {
            if (e is Expr.Ref) {
                val def = e.accept(GetDefinitionVisitor())
                if (def is Definition.Let) {
                    if (!t.isPolyType()) {
                        //if t is not a polytype then add as instance
                        def.node.instances.add(t)
                        //adjust e.id
                        e.id = "${e.id}<${def.node.instances.size - 1}>"
                        //recurse on def value
                        addInstance(def.node.value, t)
                    }
                }
            }
        }

    }

    override fun visit(f: CkFile) {
        f.expr.accept(this)
    }

    override fun visit(e: Expr.Unit) {
        //nothing
    }

    override fun visit(e: Expr.Sequence) {
        e.first.accept(this)
        e.second.accept(this)
    }

    override fun visit(e: Expr.Natural) {
        //nothing
    }

    override fun visit(e: Expr.Ref) {
        //nothing
    }

    override fun visit(e: Expr.Apply) {
        //if e.fn.t is ref then look at its def
        //if def.value.t is poly then add actual type of
        //e.fn.t to def.instances and adjust e.fn.id accordingly
        //then recurse on def.value (to look at its def if its ref and so on)
        addInstance(e.fn, Analyze.prune(e.fn.t))
        e.fn.accept(this)
        e.args.forEach { a -> a.accept(this) }
    }

    override fun visit(e: Expr.Unary) {
        e.operand.accept(this)
    }

    override fun visit(e: Expr.Binary) {
        e.lhs.accept(this)
        e.rhs.accept(this)
    }

    override fun visit(e: Expr.Cond) {
        e.cond.accept(this)
        e.csq.accept(this)
        e.alt.accept(this)
    }

    override fun visit(e: Expr.Assign) {
        e.target.accept(this)
        e.value.accept(this)
    }

    override fun visit(e: Expr.Fun) {
        e.body.accept(this)
    }

    override fun visit(e: Expr.CFun) {
        //nothing
    }

    override fun visit(e: Expr.Let) {
        e.value.accept(this)
        e.body.accept(this)
    }

    override fun visit(e: Expr.If) {
        e.cond.accept(this)
        e.csq.accept(this)
        e.alt?.accept(this)
    }

    override fun visit(e: Expr.While) {
        e.cond.accept(this)
        e.body.accept(this)
    }

    override fun visit(e: Expr.Break) {
        e.value?.accept(this)
    }

}

