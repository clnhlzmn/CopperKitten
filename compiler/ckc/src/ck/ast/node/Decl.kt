package ck.ast.node

import ck.ast.visitors.ASTVisitor

sealed class Decl: BaseASTNode() {

    class Module: Decl() {
        override fun <T> accept(visitor: ASTVisitor<T>): T {
            return visitor.visit(this)
        }
    }

    class Type: Decl() {
        override fun <T> accept(visitor: ASTVisitor<T>): T {
            return visitor.visit(this)
        }
    }

}