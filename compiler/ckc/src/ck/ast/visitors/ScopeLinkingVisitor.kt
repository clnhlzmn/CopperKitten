package ck.ast.visitors

import ck.ast.node.Expr
import ck.ast.node.ASTNode
import ck.ast.node.CkFile

//TODO: add enclosingLoop field to break expr and link it up here
class ScopeLinkingVisitor : BaseASTVisitor<Unit>() {

    //start with null scope (top level)
    var currentScope: ASTNode? = null

    override fun visit(e: Expr.Fun.DataPredicate) {
        e.enclosingScope = currentScope
    }

    override fun visit(e: Expr.Fun.DataAccessor) {
        e.enclosingScope = currentScope
    }

    override fun visit(e: Expr.Fun.DataConstructor) {
        e.enclosingScope = currentScope
    }

    override fun visit(f: CkFile) {
        currentScope = f
        f.expr.accept(this)
    }

    //leaf node, do nothing
    override fun visit(e: Expr.Tuple) {
        e.enclosingScope = currentScope
    }

    //not a ref or an enclosing scope, just have to visit children
    override fun visit(e: Expr.Sequence) {
        e.enclosingScope = currentScope
        e.first.accept(this)
        e.second.accept(this)
    }

    override fun visit(e: Expr.Natural) {
        e.enclosingScope = currentScope
    }

    override fun visit(e: Expr.Ref) {
        e.enclosingScope = currentScope
    }

    override fun visit(e: Expr.Apply) {
        e.enclosingScope = currentScope
        e.fn.accept(this)
        e.args.forEach { a -> a.accept(this) }
    }

    override fun visit(e: Expr.Unary) {
        e.enclosingScope = currentScope
        e.operand.accept(this)
    }

    override fun visit(e: Expr.Binary) {
        e.enclosingScope = currentScope
        e.lhs.accept(this)
        e.rhs.accept(this)
    }

    override fun visit(e: Expr.Cond) {
        e.enclosingScope = currentScope
        e.cond.accept(this)
        e.csq.accept(this)
        e.alt.accept(this)
    }

    override fun visit(e: Expr.Assign) {
        e.enclosingScope = currentScope
        e.target.accept(this)
        e.value.accept(this)
    }

    //ck.ast.node.Expr.Fun creates a new scope
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
        e.enclosingScope = currentScope
    }

    //ck.ast.node.Expr.Let creates a new scope
    override fun visit(e: Expr.Let) {
        //visit value of let in currentScope
        e.binding.value.accept(this)
        //save current scope as enclosing scope for e
        e.enclosingScope = currentScope
        //then set the current scope to e
        currentScope = e
        //then visit the body of e with the new current scope set
        e.body.accept(this)
        //then reset enclosing scope
        currentScope = e.enclosingScope
    }

    override fun visit(e: Expr.Let.Rec) {
        //first save current scope as enclosing scope for e
        e.enclosingScope = currentScope
        //then set the current scope to e
        currentScope = e
        //then visit values of let operand
        e.bindings.forEach { b -> b.value.accept(this) }
        //then visit the body of e with the new current scope set
        e.body.accept(this)
        //then reset enclosing scope
        currentScope = e.enclosingScope
    }

    override fun visit(e: Expr.If) {
        e.enclosingScope = currentScope
        e.cond.accept(this)
        e.csq.accept(this)
        e.alt?.accept(this)
    }

}