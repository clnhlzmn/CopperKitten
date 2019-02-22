//Statements

open class Statement

data class BlockStatement(val statements: List<ASTNode>) : Statement(), ASTNode {
    override fun <T> accept(visitor: ASTVisitor<T>): T =
        //just visit this, implementations of ASTVisitor can visit children if they want
        visitor.visit(this)

    override fun toString(): String =
        "{${statements.toString("; ")}}"
}

data class LetStatement(val id: String, val value: Expr) : Statement(), ASTNode {
    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        "let $id = $value"
}

data class ForStatement(val init: ASTNode?, val cond: Expr, val fin: Expr?, val statement: ASTNode) : Statement(), ASTNode {
    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        "for (${init?.toString() ?: ""}; $cond; ${fin?.toString() ?: ""}) $statement"
}

data class IfStatement(val cond: Expr, val con: ASTNode, val alt: ASTNode?) : Statement(), ASTNode {
    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        "if ($cond) $con${if (alt != null) " else $alt" else ""}"
}

data class ReturnStatement(val expr: Expr?) : Statement(), ASTNode {
    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        "return $expr"
}

data class ExprStatement(val expr: Expr) : Statement(), ASTNode {
    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        "$expr"
}