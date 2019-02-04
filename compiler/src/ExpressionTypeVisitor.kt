import java.util.*

class ExpressionTypeVisitor(val env: Environment<Type>, val error: MutableList<Error>) : ckBaseVisitor<Type>() {

    override fun visitNaturalExpr(ctx: ckParser.NaturalExprContext?): Type =
        SimpleType("Int")

    override fun visitSubExpr(ctx: ckParser.SubExprContext?): Type =
        visit(ctx!!.expr())

    override fun visitRefExpr(ctx: ckParser.RefExprContext?): Type {
        val type = env.lookup(ctx!!.ID().toString())
        if (type == null) {
            val what = "undefined symbol"
            error.add(Error(what))
            return ErrorType(what)
        }
        return type
    }
}

