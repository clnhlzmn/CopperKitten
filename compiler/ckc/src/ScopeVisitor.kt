

class ScopeVisitor : ASTVisitor<Unit> {

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
//        val found = scope.lookupLocal(e.id)
//        if (found == -1) {
//            scope.pushCapture(e.id, e)
//        }
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
//        val savedScope = scope
//        scope = Scope()
//        e.body.accept(this)
//        //TODO: treat non locals from the function body as refExprs here
//        e.scope = scope
//        scope = savedScope
    }

    override fun visit(e: LetExpr) {
//        //add the local to scope
//        scope.pushLocal(e.id, e)
//        //visit body
//        e.body?.accept(this)
//        //remove the local from scope
//        //TODO: save this state in e, instead of just throwing it away here
//        scope.popLocal()
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