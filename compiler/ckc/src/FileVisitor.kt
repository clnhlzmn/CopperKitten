//ck File visitor

class FileVisitor : ckBaseVisitor<CkFile>() {
    override fun visitFile(ctx: ckParser.FileContext?): CkFile =
        CkFile(
            ctx!!.decl()!!.map { d -> d.accept(DeclsVisitor()) },
            if (ctx.expr() != null)
                ExprVisitor().visit(ctx.expr())
            else
                Expr.Unit
        )
}

class DeclsVisitor : ckBaseVisitor<CFunDecl>() {

}
