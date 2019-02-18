//ck File visitor

class FileVisitor : ckBaseVisitor<BlockStatement>() {
    override fun visitFile(ctx: ckParser.FileContext?): BlockStatement {
        if (ctx!!.statements() != null) {
            return BlockStatement(StatementsVisitor().visit(ctx.statements()))
        }
        return BlockStatement(ArrayList());
    }
}

