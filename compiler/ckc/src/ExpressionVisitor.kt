class ExprVisitor : ckBaseVisitor<Expr>() {

    override fun visitNaturalExpr(ctx: ckParser.NaturalExprContext?): Expr =
        NaturalExpr(ctx!!.text.toLong())

    override fun visitSequenceExpr(ctx: ckParser.SequenceExprContext?): Expr =
        SequenceVisitor().visit(ctx!!.sequence())

    override fun visitLetExpr(ctx: ckParser.LetExprContext?): Expr =
        LetExpr(ctx!!.ID().text, ctx.value.accept(ExprVisitor()), null)

    override fun visitUnitExpr(ctx: ckParser.UnitExprContext?): Expr =
        UnitExpr()

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
            operator = ctx!!.op.text,
            operand = ExprVisitor().visit(ctx.expr())
        )

    override fun visitMultExpr(ctx: ckParser.MultExprContext?): Expr =
        BinaryExpr(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            operator = ctx.op.text,
            rhs = ExprVisitor().visit(ctx.rhs)
        )

    override fun visitAddExpr(ctx: ckParser.AddExprContext?): Expr =
        BinaryExpr(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            operator = ctx.op.text,
            rhs = ExprVisitor().visit(ctx.rhs)
        )

    override fun visitShiftExpr(ctx: ckParser.ShiftExprContext?): Expr =
        BinaryExpr(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            operator = ctx.op.text,
            rhs = ExprVisitor().visit(ctx.rhs)
        )

    override fun visitRelExpr(ctx: ckParser.RelExprContext?): Expr =
        BinaryExpr(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            operator = ctx.op.text,
            rhs = ExprVisitor().visit(ctx.rhs)
        )

    override fun visitEqualityExpr(ctx: ckParser.EqualityExprContext?): Expr =
        BinaryExpr(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            operator = ctx.op.text,
            rhs = ExprVisitor().visit(ctx.rhs)
        )

    override fun visitBitAndExpr(ctx: ckParser.BitAndExprContext?): Expr =
        BinaryExpr(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            operator = "&",
            rhs = ExprVisitor().visit(ctx.rhs)
        )

    override fun visitBitXorExpr(ctx: ckParser.BitXorExprContext?): Expr =
        BinaryExpr(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            operator = "^",
            rhs = ExprVisitor().visit(ctx.rhs)
        )

    override fun visitBitOrExpr(ctx: ckParser.BitOrExprContext?): Expr =
        BinaryExpr(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            operator = "|",
            rhs = ExprVisitor().visit(ctx.rhs)
        )

    override fun visitAndExpr(ctx: ckParser.AndExprContext?): Expr =
        BinaryExpr(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            operator = "&&",
            rhs = ExprVisitor().visit(ctx.rhs)
        )

    override fun visitOrExpr(ctx: ckParser.OrExprContext?): Expr =
        BinaryExpr(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            operator = "||",
            rhs = ExprVisitor().visit(ctx.rhs)
        )

    override fun visitCondExpr(ctx: ckParser.CondExprContext?): Expr =
        CondExpr(
            cond = ExprVisitor().visit(ctx!!.cond),
            csq = ExprVisitor().visit(ctx.con),
            alt = ExprVisitor().visit(ctx.alt)
        )

    override fun visitAssignExpr(ctx: ckParser.AssignExprContext?): Expr =
        AssignExpr(
            target = ExprVisitor().visit(ctx!!.target),
            value = ExprVisitor().visit(ctx.value)
        )

    override fun visitCfunExpr(ctx: ckParser.CfunExprContext?): Expr =
        CFunExpr(
            ctx!!.ID().text,
            ctx.funType().accept(TypeVisitor()) as FunType
        )

    override fun visitFunExpr(ctx: ckParser.FunExprContext?): Expr {
        val params =
            if (ctx!!.params() != null) ParamsVisitor().visit(ctx.params())
            else ArrayList()
        return FunExpr(
            params = params,
            type = TypeVisitor().visit(ctx.type()),
            body = ExprVisitor().visit(ctx.expr())
        )
    }

    override fun visitWhileExpr(ctx: ckParser.WhileExprContext?): Expr =
        WhileExpr(
            cond = ExprVisitor().visit(ctx!!.cond),
            body = ExprVisitor().visit(ctx.body)
        )

    override fun visitIfExpr(ctx: ckParser.IfExprContext?): Expr =
        IfExpr(
            cond = ExprVisitor().visit(ctx!!.cond),
            csq = ExprVisitor().visit(ctx.csq),
            alt =
                if (ctx.alt != null) ExprVisitor().visit(ctx.alt)
                else null
        )

    override fun visitBreakExpr(ctx: ckParser.BreakExprContext?): Expr =
        if (ctx!!.expr() != null)
            BreakExpr(ExprVisitor().visit(ctx.expr()))
        else
            BreakExpr(null)

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

class ParamVisitor : ckBaseVisitor<FunExpr.Param>() {
    override fun visitParam(ctx: ckParser.ParamContext?): FunExpr.Param =
        FunExpr.Param(
            id = ctx!!.ID().text,
            type = TypeVisitor().visit(ctx.type())
        )
}

class ParamsVisitor : ckBaseVisitor<List<FunExpr.Param>>() {
    override fun visitParams(ctx: ckParser.ParamsContext?): List<FunExpr.Param> =
        ctx!!.param().map { p -> ParamVisitor().visit(p) }
}

class TypeVisitor : ckBaseVisitor<Type>() {

    override fun visitSimpleType(ctx: ckParser.SimpleTypeContext?): Type =
        when (ctx!!.TYPEID().text) {
            "Int" -> IntType
            "Unit" -> UnitType
            else -> ErrorType("unknown type ${ctx.TYPEID().text}") //SimpleType(ctx.TYPEID().text)
        }

    override fun visitFunType(ctx: ckParser.FunTypeContext?): FunType =
        FunType(
            paramTypes =
            if (ctx!!.types() != null)
                TypesVisitor().visit(ctx.types())
            else
                ArrayList(),
            returnType = TypeVisitor().visit(ctx.type())
        )
}

class TypesVisitor : ckBaseVisitor<List<Type>>() {
    override fun visitTypes(ctx: ckParser.TypesContext?): List<Type> =
        ctx!!.type().map { t -> TypeVisitor().visit(t) }
}

