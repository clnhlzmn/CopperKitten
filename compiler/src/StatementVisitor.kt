//Statement Visitor

class StatementVisitor : ckBaseVisitor<Statement>() {
    override fun visitBlockStatement(ctx: ckParser.BlockStatementContext?): Statement =
        if (ctx!!.statements() != null)
            BlockStatement(StatementsVisitor().visit(ctx.statements()))
        else
            BlockStatement(ArrayList())

    override fun visitIfStatement(ctx: ckParser.IfStatementContext?): Statement =
        IfStatement(
            cond = ExprVisitor().visit(ctx!!.expr()),
            con = StatementVisitor().visit(ctx.con),
            alt = if (ctx.alt == null) null else StatementVisitor().visit(ctx.alt)
        )

    override fun visitExprStatement(ctx: ckParser.ExprStatementContext?): Statement =
        ExprStatement(ExprVisitor().visit(ctx!!.expr()))

    override fun visitLetStatement(ctx: ckParser.LetStatementContext?): Statement =
        LetStatement(id = ctx!!.ID().toString(), value = ExprVisitor().visit(ctx.expr()))

    override fun visitReturnStatement(ctx: ckParser.ReturnStatementContext?): Statement =
        ReturnStatement(ExprVisitor().visit(ctx!!.expr()))

    override fun visitForStatement(ctx: ckParser.ForStatementContext?): Statement =
        ForStatement(
            init = if (ctx!!.init == null) null else StatementVisitor().visit(ctx.init),
            cond = ExprVisitor().visit(ctx.cond),
            fin = if (ctx.fin == null) null else ExprVisitor().visit(ctx.fin),
            statement = StatementVisitor().visit(ctx.block)
        )
}

//statements visitor

class StatementsVisitor : ckBaseVisitor<List<Statement>>() {
    override fun visitStatements(ctx: ckParser.StatementsContext?): List<Statement> =
        ctx!!.statement().map { stmtCtx -> StatementVisitor().visit(stmtCtx) }
}