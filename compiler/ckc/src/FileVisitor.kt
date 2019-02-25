//ck File visitor

//TODO: make this return Environment
class FileVisitor : ckBaseVisitor<BlockStatement>() {
    override fun visitFile(ctx: ckParser.FileContext?): BlockStatement {
        if (ctx!!.statements() != null) {
            return BlockStatement(StatementsVisitor().visit(ctx.statements()))
        }
        return BlockStatement(ArrayList());
    }
}



