

//TODO: add enclosingLoop field to break expr and link it up here
class ScopeBuildingVisitor : BaseASTVisitor<Unit>() {

    //start with null scope (top level)
    var currentScope: ASTNode? = null

    //TODO: visit CkFile

    //leaf node, do nothing
    override fun visit(e: UnitExpr) {
        //nothing
    }

    //not a ref or an enclosing scope, just have to visit children
    override fun visit(e: SequenceExpr) {
        e.first.accept(this)
        e.second?.accept(this)
    }

    override fun visit(e: NaturalExpr) {
        //nothing
    }

    override fun visit(e: RefExpr) {
        //ref first needs to have scope set
        e.enclosingScope = currentScope
    }

    //visit children
    override fun visit(e: ApplyExpr) {
        e.target.accept(this)
        e.args.forEach{ a -> a.accept(this) }
    }

    //visit children
    override fun visit(e: UnaryExpr) {
        e.operand.accept(this)
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

    //FunExpr creates a new scope
    override fun visit(e: FunExpr) {
        //first save current scope as enclosing scope for e
        e.enclosingScope = currentScope
        //then set the current scope to e
        currentScope = e
        //then visit the body of e with the new current scope set
        e.body.accept(this)
    }

    override fun visit(e: CFunExpr) {
        //nothing
    }

    //LetExpr creates a new scope
    override fun visit(e: LetExpr) {
        //first save current scope as enclosing scope for e
        e.enclosingScope = currentScope
        //then set the current scope to e
        currentScope = e
        //then visit value of let operand
        e.value.accept(this)
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