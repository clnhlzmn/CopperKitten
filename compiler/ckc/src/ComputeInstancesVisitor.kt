

class ComputeInstancesVisitor: BaseASTVisitor<Unit>() {

    companion object {



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
        //
        //e.fn must be Expr.Ref or Expr.Fun
        //if Expr.Fun then we don't care, it's not polymorphic
        //if Expr.Ref then
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

