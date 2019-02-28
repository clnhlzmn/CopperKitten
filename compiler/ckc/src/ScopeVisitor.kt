

class ScopeVisitor : ASTVisitor<Unit> {

    var scope = Scope()

    override fun visit(e: UnitExpr) {
        //nothing
    }

    override fun visit(e: SequenceExpr) {
        e.expr.accept(this)
        e.next?.accept(this)
    }

    override fun visit(e: NaturalExpr) {
        //nothing
    }

    override fun visit(e: RefExpr) {
        val found = scope.lookupLocal(e.id)
        if (found == -1) {
            scope.pushCapture(e.id, e)
        }
    }

    override fun visit(e: ApplyExpr) {
        e.target.accept(this)
        e.args.forEach{ a -> a.accept(this) }
    }

    override fun visit(e: UnaryExpr) {
        e.expr.accept(this)
    }

    override fun visit(e: BinaryExpr) {
        e.lhs.accept(this)
        e.rhs.accept(this)
    }

    override fun visit(e: CondExpr) {
        e.cond.accept(this)
        e.csq.accept(this)
        e.alt.accept(this)
    }

    override fun visit(e: AssignExpr) {
        e.target.accept(this)
        e.value.accept(this)
    }

    override fun visit(e: FunExpr) {
        //TODO: is this right?
        val savedScope = scope
        scope = Scope()
        e.body.accept(this)
        e.scope = scope
        //TODO: treat non locals from the function body as refExprs here
        scope = savedScope
    }

    override fun visit(e: LetExpr) {
        //TODO: is this right?
        scope.pushLocal(e.id, e)
        e.body?.accept(this)
    }

    override fun visit(e: IfExpr) {
        e.cond.accept(this)
        e.csq.accept(this)
        e.alt?.accept(this)
    }

    override fun visit(e: WhileExpr) {
        e.cond.accept(this)
        e.body.accept(this)
    }

    override fun visit(e: BreakExpr) {
        e.value?.accept(this)
    }

}