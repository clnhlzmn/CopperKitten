package ck.ast.visitors

import ck.ast.node.Expr
import java.security.spec.ECField

class GetEnclosingFunction : BaseASTVisitor<Expr.Fun?>() {

    override fun visit(e: Expr.Unit): Expr.Fun? {
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

    override fun visit(e: Expr.LetRec): Expr.Fun? {
        return e.enclosingScope?.accept(this)
    }

    override fun visit(e: Expr.If): Expr.Fun? {
        return e.enclosingScope?.accept(this)
    }

    override fun visit(e: Expr.While): Expr.Fun? {
        return e.enclosingScope?.accept(this)
    }

    override fun visit(e: Expr.Break): Expr.Fun? {
        return e.enclosingScope?.accept(this)
    }

}