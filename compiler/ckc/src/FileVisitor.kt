//ck File visitor

class FileVisitor : ckBaseVisitor<Expr>() {
    override fun visitFile(ctx: ckParser.FileContext?): Expr =
        if (ctx!!.expr() != null)
            FunExpr(
                null,
                ArrayList(),
                SimpleType("Unit"),
                ExprVisitor().visit(ctx.expr())
            )
        else
            FunExpr(
                null,
                ArrayList(),
                SimpleType("Unit"),
                UnitExpr()
            )
}



