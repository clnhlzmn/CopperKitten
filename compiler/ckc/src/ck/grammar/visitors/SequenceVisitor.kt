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
                    Expr.LetRec(
                        expr.ID().zip(expr.expr()).map { p ->
                            Pair(p.first.text, p.second.accept(ExprVisitor())) },
                        ctx.sequence().accept(SequenceVisitor()),
                        Type.newVar()
                    )
                is ckParser.LetExprContext ->
                    //if expr is let then make sequence it's body
                    Expr.Let(
                        id = expr.ID().text,
                        value = ExprVisitor().visit(expr.value),
                        body = SequenceVisitor().visit(ctx.sequence()),
                        t = Type.newVar()
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