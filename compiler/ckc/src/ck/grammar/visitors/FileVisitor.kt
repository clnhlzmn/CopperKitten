package ck.grammar.visitors

import CkFile
import Expr
import ckBaseVisitor
import ckParser

//ck File visitor

class FileVisitor : ckBaseVisitor<CkFile>() {
    override fun visitFile(ctx: ckParser.FileContext?): CkFile =
        CkFile(
            ArrayList(),
            if (ctx!!.expr() != null)
                ExprVisitor().visit(ctx.expr())
            else
                Expr.Unit
        )
}
