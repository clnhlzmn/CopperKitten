package ck.ast.node

import ck.ast.visitors.ASTVisitor

sealed class Decl: BaseASTNode() {

    data class Module(val id: String, val params: List<String>, val decls: List<Decl>): Decl() {
        override fun <T> accept(visitor: ASTVisitor<T>): T {
            return visitor.visit(this)
        }
    }

    data class Type(val id: String, val params: List<String>, val sum: Sum): Decl() {

        data class Sum(val products: List<Product>)

        data class Product(val id: String, val types: List<ck.ast.Type>)

        override fun <T> accept(visitor: ASTVisitor<T>): T {
            return visitor.visit(this)
        }
    }

}