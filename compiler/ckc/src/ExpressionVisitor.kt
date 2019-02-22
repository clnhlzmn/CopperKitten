class ExprVisitor : ckBaseVisitor<Expr>() {

    override fun visitNaturalExpr(ctx: ckParser.NaturalExprContext?): Expr =
        NaturalExpr(Integer.valueOf(ctx!!.text))

    override fun visitSubExpr(ctx: ckParser.SubExprContext?): Expr =
        ExprVisitor().visit(ctx!!.expr())

    override fun visitRefExpr(ctx: ckParser.RefExprContext?): Expr =
        RefExpr(ctx!!.text)

    override fun visitApplyExpr(ctx: ckParser.ApplyExprContext?): Expr =
        ApplyExpr(
            target = ExprVisitor().visit(ctx!!.expr()),
            args =
                if (ctx.exprs() != null) ExprsVisitor().visit(ctx.exprs())
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
            body = StatementVisitor().visit(ctx.statement())
        )

    override fun visitLetExpr(ctx: ckParser.LetExprContext?): Expr =
        LetExpr(
            id = ctx!!.ID().text,
            value = ExprVisitor().visit(ctx.value),
            body = ExprVisitor().visit(ctx.body)
        )
}

class ExprsVisitor : ckBaseVisitor<List<Expr>>() {
    override fun visitExprs(ctx: ckParser.ExprsContext?): List<Expr> =
        ctx!!.expr().map { e -> ExprVisitor().visit(e) }
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

