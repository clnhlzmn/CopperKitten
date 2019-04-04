package ck.grammar.visitors

import ck.ast.node.Expr
import ckBaseVisitor
import ckParser

class DeclVisitor(val rest: Expr): ckBaseVisitor<Expr>() {
    override fun visitDecl(ctx: ckParser.DeclContext?): Expr {
        return super.visitDecl(ctx)
    }
}