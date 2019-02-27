class ExprVisitor : ckBaseVisitor<Expr>() {

    override fun visitNaturalExpr(ctx: ckParser.NaturalExprContext?): Expr =
        NaturalExpr(Integer.valueOf(ctx!!.text))

    override fun visitSequenceExpr(ctx: ckParser.SequenceExprContext?): Expr =
        SequenceVisitor().visit(ctx!!.sequence())

    override fun visitSubExpr(ctx: ckParser.SubExprContext?): Expr =
        ExprVisitor().visit(ctx!!.expr())

    override fun visitRefExpr(ctx: ckParser.RefExprContext?): Expr =
        RefExpr(ctx!!.text)

    override fun visitApplyExpr(ctx: ckParser.ApplyExprContext?): Expr =
        ApplyExpr(
            target = ExprVisitor().visit(ctx!!.expr()),
            args =
                if (ctx.args() != null) ArgsVisitor().visit(ctx.args())
                else ArrayList()
        )

    override fun visitUnaryExpr(ctx: ckParser.UnaryExprContext?): Expr =
        UnaryExpr(
            op = ctx!!.op.text,
            expr = ExprVisitor().visit(ctx.expr())
        )

    override fun visitMultExpr(ctx: ckParser.MultExprContext?): Expr =
        BinaryExpr(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            op = ctx.op.text,
            rhs = ExprVisitor().visit(ctx.rhs)
        )

    override fun visitAddExpr(ctx: ckParser.AddExprContext?): Expr =
        BinaryExpr(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            op = ctx.op.text,
            rhs = ExprVisitor().visit(ctx.rhs)
        )

    override fun visitShiftExpr(ctx: ckParser.ShiftExprContext?): Expr =
        BinaryExpr(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            op = ctx.op.text,
            rhs = ExprVisitor().visit(ctx.rhs)
        )

    override fun visitRelExpr(ctx: ckParser.RelExprContext?): Expr =
        BinaryExpr(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            op = ctx.op.text,
            rhs = ExprVisitor().visit(ctx.rhs)
        )

    override fun visitEqualityExpr(ctx: ckParser.EqualityExprContext?): Expr =
        BinaryExpr(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            op = ctx.op.text,
            rhs = ExprVisitor().visit(ctx.rhs)
        )

    override fun visitBitAndExpr(ctx: ckParser.BitAndExprContext?): Expr =
        BinaryExpr(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            op = "&",
            rhs = ExprVisitor().visit(ctx.rhs)
        )

    override fun visitBitXorExpr(ctx: ckParser.BitXorExprContext?): Expr =
        BinaryExpr(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            op = "^",
            rhs = ExprVisitor().visit(ctx.rhs)
        )

    override fun visitBitOrExpr(ctx: ckParser.BitOrExprContext?): Expr =
        BinaryExpr(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            op = "|",
            rhs = ExprVisitor().visit(ctx.rhs)
        )

    override fun visitAndExpr(ctx: ckParser.AndExprContext?): Expr =
        BinaryExpr(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            op = "&&",
            rhs = ExprVisitor().visit(ctx.rhs)
        )

    override fun visitOrExpr(ctx: ckParser.OrExprContext?): Expr =
        BinaryExpr(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            op = "||",
            rhs = ExprVisitor().visit(ctx.rhs)
        )

    override fun visitCondExpr(ctx: ckParser.CondExprContext?): Expr =
        CondExpr(
            cond = ExprVisitor().visit(ctx!!.cond),
            con = ExprVisitor().visit(ctx.con),
            alt = ExprVisitor().visit(ctx.alt)
        )

    override fun visitAssignExpr(ctx: ckParser.AssignExprContext?): Expr =
        AssignExpr(
            target = ExprVisitor().visit(ctx!!.target),
            value = ExprVisitor().visit(ctx.value)
        )

    override fun visitFunExpr(ctx: ckParser.FunExprContext?): Expr =
        FunExpr(
            params =
            if (ctx!!.params() != null) ParamsVisitor().visit(ctx.params())
            else ArrayList(),
            type = TypeVisitor().visit(ctx.type()),
            body = ExprVisitor().visit(ctx.expr())
        )

}

class SequenceVisitor : ckBaseVisitor<Expr>() {
    override fun visitSequence(ctx: ckParser.SequenceContext?): Expr {
        val expr = ctx!!.expr()
        return when (expr) {
            is ckParser.LetExprContext ->
                LetExpr(
                    expr.ID().text,
                    ExprVisitor().visit(expr.value),
                    if (ctx.sequence() == null) null
                    else SequenceVisitor().visit(ctx.sequence())
                )
            else ->
                SequenceExpr(
                    ExprVisitor().visit(ctx.expr()),
                    if (ctx.sequence() == null) null
                    else SequenceVisitor().visit(ctx.sequence())
                )
        }
    }
}

class ArgsVisitor : ckBaseVisitor<List<Expr>>() {
    override fun visitArgs(ctx: ckParser.ArgsContext?): List<Expr> =
        ctx!!.expr().map { ectx -> ExprVisitor().visit(ectx) }
}

class ParamVisitor : ckBaseVisitor<Param>() {
    override fun visitParam(ctx: ckParser.ParamContext?): Param =
        Param(
            id = ctx!!.ID().text,
            type = TypeVisitor().visit(ctx.type())
        )
}

class ParamsVisitor : ckBaseVisitor<List<Param>>() {
    override fun visitParams(ctx: ckParser.ParamsContext?): List<Param> =
        ctx!!.param().map { p -> ParamVisitor().visit(p) }
}

