package ck.grammar.visitors

import ck.ast.node.Expr
import ck.ast.Type
import ckBaseVisitor
import ckParser
import java.lang.RuntimeException

class ExprVisitor : ckBaseVisitor<Expr>() {

    override fun visitNaturalExpr(ctx: ckParser.NaturalExprContext?): Expr =
        Expr.Natural(ctx!!.text.toLong())

    override fun visitSequenceExpr(ctx: ckParser.SequenceExprContext?): Expr =
        SequenceVisitor().visit(ctx!!.sequence())

    //if visiting a let expr by itself then it has no "body" just return value
    override fun visitLetExpr(ctx: ckParser.LetExprContext?): Expr =
        ctx!!.binding().expr().accept(ExprVisitor())

    override fun visitLetRecExpr(ctx: ckParser.LetRecExprContext?): Expr {
        throw RuntimeException("let rec without body")
    }

//    override fun visitTupleExpr(ctx: ckParser.TupleExprContext?): Expr {
//        return Expr.Tuple(if (ctx!!.exprs() == null) ArrayList() else ctx.exprs().accept(ExprsVisitor()))
//    }

    override fun visitRefExpr(ctx: ckParser.RefExprContext?): Expr =
        Expr.Ref(ctx!!.text, Type.newVar())

    override fun visitApplyExpr(ctx: ckParser.ApplyExprContext?): Expr =
        Expr.Apply(
            fn = ExprVisitor().visit(ctx!!.expr()),
            args =
            if (ctx.exprs() != null) ExprsVisitor().visit(ctx.exprs())
            else ArrayList(),
            t = Type.newVar()
        )

    override fun visitUnaryExpr(ctx: ckParser.UnaryExprContext?): Expr =
        Expr.Unary(
            operator = ctx!!.op.text,
            operand = ExprVisitor().visit(ctx.expr()),
            t = Type.newVar()
        )

    override fun visitMultExpr(ctx: ckParser.MultExprContext?): Expr =
        Expr.Binary(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            operator = ctx.op.text,
            rhs = ExprVisitor().visit(ctx.rhs),
            t = Type.newVar()
        )

    override fun visitAddExpr(ctx: ckParser.AddExprContext?): Expr =
        Expr.Binary(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            operator = ctx.op.text,
            rhs = ExprVisitor().visit(ctx.rhs),
            t = Type.newVar()
        )

    override fun visitShiftExpr(ctx: ckParser.ShiftExprContext?): Expr =
        Expr.Binary(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            operator = ctx.op.text,
            rhs = ExprVisitor().visit(ctx.rhs),
            t = Type.newVar()
        )

    override fun visitRelExpr(ctx: ckParser.RelExprContext?): Expr =
        Expr.Binary(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            operator = ctx.op.text,
            rhs = ExprVisitor().visit(ctx.rhs),
            t = Type.newVar()
        )

    override fun visitEqualityExpr(ctx: ckParser.EqualityExprContext?): Expr =
        Expr.Binary(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            operator = ctx.op.text,
            rhs = ExprVisitor().visit(ctx.rhs),
            t = Type.newVar()
        )

    override fun visitBitAndExpr(ctx: ckParser.BitAndExprContext?): Expr =
        Expr.Binary(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            operator = "&",
            rhs = ExprVisitor().visit(ctx.rhs),
            t = Type.newVar()
        )

    override fun visitBitXorExpr(ctx: ckParser.BitXorExprContext?): Expr =
        Expr.Binary(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            operator = "^",
            rhs = ExprVisitor().visit(ctx.rhs),
            t = Type.newVar()
        )

    override fun visitBitOrExpr(ctx: ckParser.BitOrExprContext?): Expr =
        Expr.Binary(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            operator = "|",
            rhs = ExprVisitor().visit(ctx.rhs),
            t = Type.newVar()
        )

    override fun visitAndExpr(ctx: ckParser.AndExprContext?): Expr =
        Expr.Binary(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            operator = "&&",
            rhs = ExprVisitor().visit(ctx.rhs),
            t = Type.newVar()
        )

    override fun visitOrExpr(ctx: ckParser.OrExprContext?): Expr =
        Expr.Binary(
            lhs = ExprVisitor().visit(ctx!!.lhs),
            operator = "||",
            rhs = ExprVisitor().visit(ctx.rhs),
            t = Type.newVar()
        )

    override fun visitCondExpr(ctx: ckParser.CondExprContext?): Expr =
        Expr.Cond(
            cond = ExprVisitor().visit(ctx!!.cond),
            csq = ExprVisitor().visit(ctx.con),
            alt = ExprVisitor().visit(ctx.alt),
            t = Type.newVar()
        )

    override fun visitAssignExpr(ctx: ckParser.AssignExprContext?): Expr =
        Expr.Assign(
            target = ExprVisitor().visit(ctx!!.target),
            value = ExprVisitor().visit(ctx.value),
            t = Type.newVar()
        )

    override fun visitCFunExpr(ctx: ckParser.CFunExprContext?): Expr =
        Expr.CFun(
            ctx!!.ID().text,
            ctx.type().accept(TypeVisitor()),
            t = Type.newVar()
        )

    override fun visitFunExpr(ctx: ckParser.FunExprContext?): Expr {
        return Expr.Fun(
            params =
            if (ctx!!.params() != null) ctx.params().accept(ParamsVisitor())
            else ArrayList(),
            body = ExprVisitor().visit(ctx.expr()),
            t = Type.newVar()
        )
    }

    override fun visitIfExpr(ctx: ckParser.IfExprContext?): Expr =
        Expr.If(
            cond = ExprVisitor().visit(ctx!!.cond),
            csq = ExprVisitor().visit(ctx.csq),
            alt =
            if (ctx.alt != null) ExprVisitor().visit(ctx.alt)
            else null,
            t = Type.newVar()
        )

}

//TODO: for some reason I can't put these classes in their own files...
class ParamsVisitor : ckBaseVisitor<List<Expr.Fun.Param>>() {
    override fun visitParams(ctx: ckParser.ParamsContext?): List<Expr.Fun.Param> =
        ctx!!.param().map { p -> ParamVisitor().visit(p) }
}

class ParamVisitor : ckBaseVisitor<Expr.Fun.Param>() {
    override fun visitParam(ctx: ckParser.ParamContext?): Expr.Fun.Param =
        Expr.Fun.Param(
            id = ctx!!.ID().text,
            t = Type.newVar()
        )
}

