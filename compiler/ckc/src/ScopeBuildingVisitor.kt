

class ScopeBuildingVisitor : ASTVisitor<Unit> {

    //start with null scope (top level)
    var currentScope: ASTNode? = null

    //leaf node, do nothing
    override fun visit(e: UnitExpr) {
        //nothing
    }

    //not a ref or an enclosing scope, just have to visit children
    override fun visit(e: SequenceExpr) {
        e.expr.accept(this)
        e.next?.accept(this)
    }

    override fun visit(e: NaturalExpr) {
        //nothing
    }

    override fun visit(e: RefExpr) {
        //ref expr needs to have scope set
        e.enclosingScope = currentScope
    }

    //visit children
    override fun visit(e: ApplyExpr) {
        e.target.accept(this)
        e.args.forEach{ a -> a.accept(this) }
    }

    //visit children
    override fun visit(e: UnaryExpr) {
        e.expr.accept(this)
    }

    //visit children
    override fun visit(e: BinaryExpr) {
        e.lhs.accept(this)
        e.rhs.accept(this)
    }

    //visit children
    override fun visit(e: CondExpr) {
        e.cond.accept(this)
        e.csq.accept(this)
        e.alt.accept(this)
    }

    //visit children
    override fun visit(e: AssignExpr) {
        e.target.accept(this)
        e.value.accept(this)
    }

    //nothing
    override fun visit(p: Param) {
        //nothing
    }

    //FunExpr creates a new scope
    override fun visit(e: FunExpr) {
        //first save current scope as enclosing scope for e
        e.enclosingScope = currentScope
        //then set the current scope to e
        currentScope = e
        //then visit the body of e with the new current scope set
        e.body.accept(this)
    }

    //LetExpr creates a new scope
    override fun visit(e: LetExpr) {
        //first save current scope as enclosing scope for e
        e.enclosingScope = currentScope
        //then set the current scope to e
        currentScope = e
        //then visit the body of e with the new current scope set
        e.body?.accept(this)
    }

    //visit children
    override fun visit(e: IfExpr) {
        e.cond.accept(this)
        e.csq.accept(this)
        e.alt?.accept(this)
    }

    //visit children
    override fun visit(e: WhileExpr) {
        e.cond.accept(this)
        e.body.accept(this)
    }

    //visit children
    override fun visit(e: BreakExpr) {
        e.value?.accept(this)
    }

}