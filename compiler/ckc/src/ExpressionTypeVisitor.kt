import java.util.*

class ExpressionTypeVisitor(val frame: StackFrame, val error: MutableList<Error>) : ckBaseVisitor<Type>() {

    override fun visitNaturalExpr(ctx: ckParser.NaturalExprContext?): Type =
        SimpleType("Int")

    override fun visitSubExpr(ctx: ckParser.SubExprContext?): Type =
        visit(ctx!!.expr())

    override fun visitRefExpr(ctx: ckParser.RefExprContext?): Type {
        val type = frame.lookupType(ctx!!.ID().toString())
        if (type == null) {
            val what = "undefined symbol"
            error.add(Error(what))
            return ErrorType(what)
        }
        return type
    }

    override fun visitEqualityExpr(ctx: ckParser.EqualityExprContext?): Type {
        return super.visitEqualityExpr(ctx)
    }

}

