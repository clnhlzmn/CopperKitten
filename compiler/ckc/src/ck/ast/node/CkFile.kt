package ck.ast.node

import ck.ast.visitors.ASTVisitor

class CkFile(val expr: Expr) : BaseASTNode() {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

}

