package ck.grammar.visitors

import ck.ast.node.CkFile
import ck.ast.node.Decl
import ck.ast.node.Expr
import ckBaseVisitor
import ckParser

//ck File visitor

class FileVisitor : ckBaseVisitor<CkFile>() {
    override fun visitFile(ctx: ckParser.FileContext?): CkFile {
        val expr =
            if (ctx!!.expr() != null)
                ExprVisitor().visit(ctx.expr())
            else
                Expr.Tuple()
        return CkFile(emptyList(), ctx.decl().foldRight(expr) { declContext, acc -> declContext.accept(DeclVisitor(acc)) })
    }
}
