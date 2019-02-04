import java.util.*

data class Error(val what: String)

class Checker: ckBaseVisitor<List<Error>>() {

    val env: Deque<MutableMap<String, Type>> = ArrayDeque()

    override fun visitFile(ctx: ckParser.FileContext?): List<Error> =
        if (ctx!!.statements().isEmpty)
            ArrayList()
        else
            visit(ctx.statements())

    override fun visitStatements(ctx: ckParser.StatementsContext?): List<Error> {
        val ret = ArrayList<Error>()
        for (statement in ctx!!.statement()) {
            ret.addAll(visit(statement))
            if (ret.isNotEmpty())
                return ret;
        }
        return ret
    }

    override fun visitExprStatement(ctx: ckParser.ExprStatementContext?): List<Error> {
        return super.visitExprStatement(ctx)
    }

    override fun visitBlockStatement(ctx: ckParser.BlockStatementContext?): List<Error> {
        return super.visitBlockStatement(ctx)
    }

    override fun visitForStatement(ctx: ckParser.ForStatementContext?): List<Error> {
        return super.visitForStatement(ctx)
    }

    override fun visitIfStatement(ctx: ckParser.IfStatementContext?): List<Error> {
        return super.visitIfStatement(ctx)
    }

    override fun visitLetStatement(ctx: ckParser.LetStatementContext?): List<Error> {
        val ret = ArrayList<Error>()
        val scope = env.last!!
        val old = scope.put(ctx!!.ID().toString(), TypeVisitor().visit(ctx.expr()))
        return ret
    }

    override fun visitReturnStatement(ctx: ckParser.ReturnStatementContext?): List<Error> {
        return super.visitReturnStatement(ctx)
    }

}

