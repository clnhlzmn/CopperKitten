//Statements

open class Statement : ASTNode()

data class BlockStatement(val statements: List<Statement>) : Statement() {
    override fun toString(): String =
        "{${statements.toString("; ")}}"
}

data class LetStatement(val id: String, val value: Expr) : Statement() {
    override fun toString(): String =
        "let $id = $value"
}

data class ForStatement(
    val init: Statement?, val cond: Expr, val fin: Expr?, val statement: Statement) : Statement() {
    override fun toString(): String =
        "for (${init?.toString() ?: ""}; $cond; ${fin?.toString() ?: ""}) $statement"
}

data class IfStatement(val cond: Expr, val con: Statement, val alt: Statement?) : Statement() {
    override fun toString(): String =
        "if ($cond) $con${if (alt != null) " else $alt" else ""}"
}

data class ReturnStatement(val expr: Expr?) : Statement() {
    override fun toString(): String =
        "return $expr"
}

data class ExprStatement(val expr: Expr) : Statement() {
    override fun toString(): String =
        "$expr"
}