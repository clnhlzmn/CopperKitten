//ck File visitor

class FileVisitor : ckBaseVisitor<Expr>() {
    override fun visitFile(ctx: ckParser.FileContext?): Expr =
        if (ctx!!.expr() != null) ExprVisitor().visit(ctx.expr())
        else EmptyExpr()
}



