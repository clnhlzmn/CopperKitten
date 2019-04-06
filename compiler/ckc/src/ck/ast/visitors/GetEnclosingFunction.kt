package ck.ast.visitors

import ck.ast.node.CkFile
import ck.ast.node.Expr

class GetEnclosingFunction : BaseASTVisitor<Expr.Fun?>() {

    override fun visit(e: Expr.Fun.ProductCtor): Expr.Fun? {
        return null
    }

    override fun visit(e: Expr.CFun): Expr.Fun? {
        return null
    }

    override fun visit(e: Expr.Fun.ProductAccessor): Expr.Fun? {
        return null
    }

    override fun visit(f: CkFile): Expr.Fun? {
        return null
    }

    override fun visit(e: Expr.Tuple): Expr.Fun? {
        return e.enclosingScope?.accept(this)
    }

    override fun visit(e: Expr.Sequence): Expr.Fun? {
        return e.enclosingScope?.accept(this)
    }

    override fun visit(e: Expr.Natural): Expr.Fun? {
        return e.enclosingScope?.accept(this)
    }

    override fun visit(e: Expr.Ref): Expr.Fun? {
        return e.enclosingScope?.accept(this)
    }

    override fun visit(e: Expr.Apply): Expr.Fun? {
        return e.enclosingScope?.accept(this)
    }

    override fun visit(e: Expr.Unary): Expr.Fun? {
        return e.enclosingScope?.accept(this)
    }

    override fun visit(e: Expr.Binary): Expr.Fun? {
        return e.enclosingScope?.accept(this)
    }

    override fun visit(e: Expr.Cond): Expr.Fun? {
        return e.enclosingScope?.accept(this)
    }

    override fun visit(e: Expr.Assign): Expr.Fun? {
        return e.enclosingScope?.accept(this)
    }

    override fun visit(e: Expr.Fun): Expr.Fun? {
        return e
    }

    override fun visit(e: Expr.Let): Expr.Fun? {
        return e.enclosingScope?.accept(this)
    }

    override fun visit(e: Expr.Let.Rec): Expr.Fun? {
        return e.enclosingScope?.accept(this)
    }

    override fun visit(e: Expr.If): Expr.Fun? {
        return e.enclosingScope?.accept(this)
    }

}