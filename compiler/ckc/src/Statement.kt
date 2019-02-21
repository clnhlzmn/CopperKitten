//Statements

data class BlockStatement(val statements: List<ASTNode>) : ASTNode {
    override fun <T> accept(visitor: ASTVisitor<T>): T {
        //TODO: order of visiting statments
        //TODO: return value?
        //TODO: do I have to visit statements here? I don't think so, let custom visitors deal with that
        for (node in statements) {
            node.accept(visitor)
        }
        return visitor.visit(this)
    }

    override fun toString(): String =
        "{${statements.toString("; ")}}"
}

data class LetStatement(val id: String, val value: Expr) : ASTNode {
    override fun <T> accept(visitor: ASTVisitor<T>): T {
        //TODO: value.accept(visitor)
        return visitor.visit(this)
    }

    override fun toString(): String =
        "let $id = $value"
}

data class ForStatement(
    val init: ASTNode?, val cond: Expr, val fin: Expr?, val statement: ASTNode
) : ASTNode {
    override fun <T> accept(visitor: ASTVisitor<T>): T {
        init?.accept(visitor)
        //TODO: cond.accept(visitor)
        //TODO: fin?.accept(visitor)
        statement.accept(visitor)
        //TODO: return value?
        return visitor.visit(this)
    }

    override fun toString(): String =
        "for (${init?.toString() ?: ""}; $cond; ${fin?.toString() ?: ""}) $statement"
}

data class IfStatement(val cond: Expr, val con: ASTNode, val alt: ASTNode?) : ASTNode {
    override fun <T> accept(visitor: ASTVisitor<T>): T {
        return visitor.visit(this)
    }

    override fun toString(): String =
        "if ($cond) $con${if (alt != null) " else $alt" else ""}"
}

data class ReturnStatement(val expr: Expr?) : ASTNode {
    override fun <T> accept(visitor: ASTVisitor<T>): T {
        return visitor.visit(this)
    }

    override fun toString(): String =
        "return $expr"
}

data class ExprStatement(val expr: Expr) : ASTNode {
    override fun <T> accept(visitor: ASTVisitor<T>): T {
        return visitor.visit(this)
    }

    override fun toString(): String =
        "$expr"
}