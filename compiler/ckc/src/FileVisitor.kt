//ck File visitor

class FileVisitor : ckBaseVisitor<Expr>() {
    override fun visitFile(ctx: ckParser.FileContext?): Expr =
        if (ctx!!.expr() != null)
            FunExpr(
                ArrayList(),
                SimpleType("Unit"),
                ExprVisitor().visit(ctx.expr())
            )
        else
            FunExpr(
                ArrayList(),
                SimpleType("Unit"),
                UnitExpr()
            )
}



