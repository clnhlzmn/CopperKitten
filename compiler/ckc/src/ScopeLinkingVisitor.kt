import ck.ast.ASTNode
import ck.ast.BaseASTVisitor

//TODO: add enclosingLoop field to break expr and link it up here
class ScopeLinkingVisitor : BaseASTVisitor<Unit>() {

    //start with null scope (top level)
    var currentScope: ASTNode? = null

    override fun visit(f: CkFile) {
        currentScope = f
        f.expr.accept(this)
    }

    //leaf node, do nothing
    override fun visit(e: Expr.Unit) {
        //nothing
    }

    //not a ref or an enclosing scope, just have to visit children
    override fun visit(e: Expr.Sequence) {
        e.first.accept(this)
        e.second.accept(this)
    }

    override fun visit(e: Expr.Natural) {
        //nothing
    }

    override fun visit(e: Expr.Ref) {
        //ref first needs to have scope set
        e.enclosingScope = currentScope
    }

    //visit children
    override fun visit(e: Expr.Apply) {
        e.fn.accept(this)
        e.args.forEach { a -> a.accept(this) }
    }

    //visit children
    override fun visit(e: Expr.Unary) {
        e.operand.accept(this)
    }

    //visit children
    override fun visit(e: Expr.Binary) {
        e.lhs.accept(this)
        e.rhs.accept(this)
    }

    //visit children
    override fun visit(e: Expr.Cond) {
        e.cond.accept(this)
        e.csq.accept(this)
        e.alt.accept(this)
    }

    //visit children
    override fun visit(e: Expr.Assign) {
        e.target.accept(this)
        e.value.accept(this)
    }

    //Expr.Fun creates a new scope
    override fun visit(e: Expr.Fun) {
        //first save current scope as enclosing scope for e
        e.enclosingScope = currentScope
        //then set the current scope to e
        currentScope = e
        //then visit the body of e with the new current scope set
        e.body.accept(this)
        //then reset enclosing scope
        currentScope = e.enclosingScope
    }

    override fun visit(e: Expr.CFun) {
        //nothing
    }

    //Expr.Let creates a new scope
    override fun visit(e: Expr.Let) {
        //first save current scope as enclosing scope for e
        e.enclosingScope = currentScope
        //then set the current scope to e
        currentScope = e
        //then visit value of let operand
        e.value.accept(this)
        //then visit the body of e with the new current scope set
        e.body.accept(this)
        //then reset enclosing scope
        currentScope = e.enclosingScope
    }

    //visit children
    override fun visit(e: Expr.If) {
        e.cond.accept(this)
        e.csq.accept(this)
        e.alt?.accept(this)
    }

    //visit children
    override fun visit(e: Expr.While) {
        e.cond.accept(this)
        e.body.accept(this)
    }

    //visit children
    override fun visit(e: Expr.Break) {
        e.value?.accept(this)
    }

}