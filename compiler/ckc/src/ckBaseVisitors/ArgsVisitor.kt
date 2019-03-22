package ckBaseVisitors

import Expr
import ckBaseVisitor
import ckParser

class ArgsVisitor : ckBaseVisitor<List<Expr>>() {
    override fun visitArgs(ctx: ckParser.ArgsContext?): List<Expr> =
        ctx!!.expr().map { ectx -> ExprVisitor().visit(ectx) }
}