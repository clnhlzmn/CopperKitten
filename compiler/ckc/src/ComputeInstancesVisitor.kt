

class ComputeInstancesVisitor: BaseASTVisitor<Unit>() {

    companion object {

        fun setInstance(fn: Expr, inst: Type): Int {
            val ret: Int
            if (fn is Expr.Ref) {
                val def = fn.accept(GetDefinitionVisitor())
                ret = if (def is Definition.Let) {
                    when (def.node.value) {
                        is Expr.Fun -> {
                            //TODO: don't add duplicate instances (based on Type.isRef)
                            def.node.value.instances.add(inst)
                            def.node.value.instances.size - 1
                        }
                        else -> setInstance(def.node.value, inst)
                    }
                } else {
                    -1
                }
                //adjust ref expr id based on instance
                fn.id = fn.id + ret
            } else {
                //fn is function expr and not polymorphic
                ret = -1
            }
            return ret
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
        val fn = e.fn
        setInstance(e.fn, e.fn.t)
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
        //TODO: this
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

