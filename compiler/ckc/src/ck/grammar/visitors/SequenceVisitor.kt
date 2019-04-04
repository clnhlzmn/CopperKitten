package ck.grammar.visitors

import ck.ast.node.Expr
import ck.ast.Type
import ckBaseVisitor
import ckParser

class SequenceVisitor : ckBaseVisitor<Expr>() {
    override fun visitSequence(ctx: ckParser.SequenceContext?): Expr {
        val expr = ctx!!.expr()
        return if (ctx.sequence() == null)
        //if sequence is null then return expr
            expr.accept(ExprVisitor())
        else
            when (expr) {
                is ckParser.LetRecExprContext ->
                    Expr.Let.Rec(
                        expr.binding().map { b ->
                            Expr.Let.Binding(
                                b.ID().text,
                                if (b.type() == null) null else b.type().accept(TypeVisitor()),
                                b.expr().accept(ExprVisitor())
                            )
                        },
                        ctx.sequence().accept(SequenceVisitor()),
                        Type.newVar()
                    )
                is ckParser.LetExprContext ->
                    //if expr is let then make sequence it's body
                    Expr.Let(
                        Expr.Let.Binding(
                            expr.binding().ID().text,
                            if (expr.binding().type() == null) null else expr.binding().type().accept(TypeVisitor()),
                            ExprVisitor().visit(expr.binding().expr())
                        ),
                        SequenceVisitor().visit(ctx.sequence()),
                        Type.newVar()
                    )
                else ->
                    //otherwise it's a normal sequence
                    Expr.Sequence(
                        first = ExprVisitor().visit(ctx.expr()),
                        second = SequenceVisitor().visit(ctx.sequence()),
                        t = Type.newVar()
                    )
            }
    }
}