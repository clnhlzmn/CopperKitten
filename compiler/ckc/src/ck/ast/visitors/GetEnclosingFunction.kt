package ck.ast.visitors

import ck.ast.node.Expr

class GetEnclosingFunction : BaseASTVisitor<Expr.Fun?>() {

    override fun visit(e: Expr.Unit): Expr.Fun? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: Expr.Sequence): Expr.Fun? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: Expr.Natural): Expr.Fun? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: Expr.Ref): Expr.Fun? {
        return e.enclosingScope?.accept(this)
    }

    override fun visit(e: Expr.Apply): Expr.Fun? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: Expr.Unary): Expr.Fun? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: Expr.Binary): Expr.Fun? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: Expr.Cond): Expr.Fun? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: Expr.Assign): Expr.Fun? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: Expr.Fun): Expr.Fun? {
        return e
    }

    override fun visit(e: Expr.Let): Expr.Fun? {
        return e.enclosingScope?.accept(this)
    }

    override fun visit(e: Expr.If): Expr.Fun? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: Expr.While): Expr.Fun? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(e: Expr.Break): Expr.Fun? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}