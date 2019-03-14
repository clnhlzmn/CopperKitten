class ExprVisitor : ckBaseVisitor<Expr>() {

    override fun visitNaturalExpr(ctx: ckParser.NaturalExprContext?): Expr =
        Expr.Natural(ctx!!.text.toLong())

    override fun visitSequenceExpr(ctx: ckParser.SequenceExprContext?): Expr =
        SequenceVisitor().visit(ctx!!.sequence())

    //if visiting a let expr by itself then it has no "body" just return value
    override fun visitLetExpr(ctx: ckParser.LetExprContext?): Expr =
        ctx!!.value.accept(ExprVisitor())

    override fun visitUnitExpr(ctx: ckParser.UnitExprContext?): Expr =
        Expr.Unit

    override fun visitRefExpr(ctx: ckParser.RefExprContext?): Expr =
        Expr.Ref(ctx!!.text)

    override fun visitApplyExpr(ctx: ckParser.ApplyExprContext?): Expr =
        Expr.Apply(
            fn = ExprVisitor().visit(ctx!!.expr()),
            args =
                if (ctx.args() != null) ArgsVisitor().visit(ctx.args())
                else ArrayList()
        )

    override fun visitUnaryExpr(ctx: ckParser.UnaryExprContext?): Expr =
        Expr.Unary(
            operator = ctx!!.op.text,
            operand = ExprVisitor().visit(ctx.expr())
        )

    override fun visitMultExpr(ctx: ckParser.MultExprContext?): Expr =
        Expr.Binary(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            operator = ctx.op.text,
            rhs = ExprVisitor().visit(ctx.rhs)
        )

    override fun visitAddExpr(ctx: ckParser.AddExprContext?): Expr =
        Expr.Binary(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            operator = ctx.op.text,
            rhs = ExprVisitor().visit(ctx.rhs)
        )

    override fun visitShiftExpr(ctx: ckParser.ShiftExprContext?): Expr =
        Expr.Binary(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            operator = ctx.op.text,
            rhs = ExprVisitor().visit(ctx.rhs)
        )

    override fun visitRelExpr(ctx: ckParser.RelExprContext?): Expr =
        Expr.Binary(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            operator = ctx.op.text,
            rhs = ExprVisitor().visit(ctx.rhs)
        )

    override fun visitEqualityExpr(ctx: ckParser.EqualityExprContext?): Expr =
        Expr.Binary(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            operator = ctx.op.text,
            rhs = ExprVisitor().visit(ctx.rhs)
        )

    override fun visitBitAndExpr(ctx: ckParser.BitAndExprContext?): Expr =
        Expr.Binary(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            operator = "&",
            rhs = ExprVisitor().visit(ctx.rhs)
        )

    override fun visitBitXorExpr(ctx: ckParser.BitXorExprContext?): Expr =
        Expr.Binary(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            operator = "^",
            rhs = ExprVisitor().visit(ctx.rhs)
        )

    override fun visitBitOrExpr(ctx: ckParser.BitOrExprContext?): Expr =
        Expr.Binary(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            operator = "|",
            rhs = ExprVisitor().visit(ctx.rhs)
        )

    override fun visitAndExpr(ctx: ckParser.AndExprContext?): Expr =
        Expr.Binary(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            operator = "&&",
            rhs = ExprVisitor().visit(ctx.rhs)
        )

    override fun visitOrExpr(ctx: ckParser.OrExprContext?): Expr =
        Expr.Binary(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            operator = "||",
            rhs = ExprVisitor().visit(ctx.rhs)
        )

    override fun visitCondExpr(ctx: ckParser.CondExprContext?): Expr =
        Expr.Cond(
            cond = ExprVisitor().visit(ctx!!.cond),
            csq = ExprVisitor().visit(ctx.con),
            alt = ExprVisitor().visit(ctx.alt)
        )

    override fun visitAssignExpr(ctx: ckParser.AssignExprContext?): Expr =
        Expr.Assign(
            target = ExprVisitor().visit(ctx!!.target),
            value = ExprVisitor().visit(ctx.value)
        )

    override fun visitCFunExpr(ctx: ckParser.CFunExprContext?): Expr =
        Expr.CFun(
            ctx!!.ID().text,
            ctx.funType().accept(TypeVisitor()) as Type.Fun
        )

    override fun visitFunExpr(ctx: ckParser.FunExprContext?): Expr {
        val params =
            if (ctx!!.params() != null) ParamsVisitor().visit(ctx.params())
            else ArrayList()
        return Expr.Fun(
            params = params,
            type = TypeVisitor().visit(ctx.type()),
            body = ExprVisitor().visit(ctx.expr())
        )
    }

    override fun visitWhileExpr(ctx: ckParser.WhileExprContext?): Expr =
        Expr.While(
            cond = ExprVisitor().visit(ctx!!.cond),
            body = ExprVisitor().visit(ctx.body)
        )

    override fun visitIfExpr(ctx: ckParser.IfExprContext?): Expr =
        Expr.If(
            cond = ExprVisitor().visit(ctx!!.cond),
            csq = ExprVisitor().visit(ctx.csq),
            alt =
                if (ctx.alt != null) ExprVisitor().visit(ctx.alt)
                else null
        )

    override fun visitBreakExpr(ctx: ckParser.BreakExprContext?): Expr =
        if (ctx!!.expr() != null)
            Expr.Break(ExprVisitor().visit(ctx.expr()))
        else
            Expr.Break(null)

}

class SequenceVisitor : ckBaseVisitor<Expr>() {
    override fun visitSequence(ctx: ckParser.SequenceContext?): Expr {
        val expr = ctx!!.expr()
        return if (ctx.sequence() == null)
            //if sequence is null then return expr
            expr.accept(ExprVisitor())
        else
            when (expr) {
                is ckParser.LetExprContext ->
                    //if expr is let then make sequence it's body
                    Expr.Let(
                        expr.ID().text,
                        ExprVisitor().visit(expr.value),
                        SequenceVisitor().visit(ctx.sequence())
                    )
                else ->
                    //otherwise it's a normal sequence
                    Expr.Sequence(
                        ExprVisitor().visit(ctx.expr()),
                        SequenceVisitor().visit(ctx.sequence())
                    )
            }
    }
}

class ArgsVisitor : ckBaseVisitor<List<Expr>>() {
    override fun visitArgs(ctx: ckParser.ArgsContext?): List<Expr> =
        ctx!!.expr().map { ectx -> ExprVisitor().visit(ectx) }
}

class ParamVisitor : ckBaseVisitor<Expr.Fun.Param>() {
    override fun visitParam(ctx: ckParser.ParamContext?): Expr.Fun.Param =
        Expr.Fun.Param(
            id = ctx!!.ID().text,
            declType = TypeVisitor().visit(ctx.type())
        )
}

class ParamsVisitor : ckBaseVisitor<List<Expr.Fun.Param>>() {
    override fun visitParams(ctx: ckParser.ParamsContext?): List<Expr.Fun.Param> =
        ctx!!.param().map { p -> ParamVisitor().visit(p) }
}

class TypeVisitor : ckBaseVisitor<Type>() {

    override fun visitSimpleType(ctx: ckParser.SimpleTypeContext?): Type =
        when (ctx!!.TYPEID().text) {
            "Int" -> Type.Int
            "Unit" -> Type.Unit
            else -> Type.Error("unknown type ${ctx.TYPEID().text}") //SimpleType(ctx.TYPEID().text)
        }

    override fun visitFunType(ctx: ckParser.FunTypeContext?): Type.Fun =
        Type.Fun(
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

