package ck.ast.node

import ck.ast.visitors.ASTVisitor

interface ASTNode {
    fun <T> accept(visitor: ASTVisitor<T>): T
}

open class BaseASTNode : ASTNode {
    override fun <T> accept(visitor: ASTVisitor<T>): T {
        TODO("not implemented")
    }
}

