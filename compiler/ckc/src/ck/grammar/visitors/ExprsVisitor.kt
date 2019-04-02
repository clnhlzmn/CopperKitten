package ck.grammar.visitors

import ck.ast.node.Expr
import ckBaseVisitor
import ckParser

class ExprsVisitor : ckBaseVisitor<List<Expr>>() {
    override fun visitExprs(ctx: ckParser.ExprsContext?): List<Expr> =
        ctx!!.expr().map { ectx -> ExprVisitor().visit(ectx) }
}