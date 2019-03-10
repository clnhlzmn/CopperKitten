//ck File visitor

class FileVisitor : ckBaseVisitor<CkFile>() {
    override fun visitFile(ctx: ckParser.FileContext?): CkFile =
        CkFile(
            ctx!!.decl()!!.map { d -> d.accept(DeclsVisitor()) },
            if (ctx!!.expr() != null)
                ExprVisitor().visit(ctx.expr())
            else
                UnitExpr()
        )
}

class DeclsVisitor : ckBaseVisitor<CFunDecl>() {
    override fun visitCfunDecl(ctx: ckParser.CfunDeclContext?): CFunDecl {
        return CFunDecl()
    }
}
