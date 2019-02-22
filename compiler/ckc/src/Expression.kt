//Expressions

open class Expr

data class NaturalExpr(val value: Int) : Expr(), ASTNode {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        value.toString()
}

data class RefExpr(val id: String) : Expr() {
    override fun toString(): String =
        id
}

data class ApplyExpr(val target: Expr, val args: List<Expr>) : Expr(), ASTNode {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        "$target(${args.toString(", ")})"
}

data class UnaryExpr(val op: String, val expr: Expr) : Expr(), ASTNode {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        "($op $expr)"
}

data class BinaryExpr(val lhs: Expr, val op: String, val rhs: Expr) : Expr(), ASTNode {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        "($lhs $op $rhs)"
}

data class CondExpr(val cond: Expr, val con: Expr, val alt: Expr) : Expr(), ASTNode {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        "($cond ? $con : $alt)"
}

data class AssignExpr(val target: Expr, val value: Expr) : Expr(), ASTNode {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        "($target = $value)"
}

data class Param(val id: String, val type: Type) {
    override fun toString(): String =
        "$id: $type"
}

data class FunExpr(val params: List<Param>, val type: Type, val body: Statement) : Expr(), ASTNode {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        "(${params.toString(", ")}) :$type $body"
}

data class LetExpr(val id: String, val value: Expr, val body: Expr) : Expr(), ASTNode {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        "($id = $value in $body)"

}

