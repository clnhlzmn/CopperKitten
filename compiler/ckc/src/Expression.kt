//Expressions

interface Expr : ASTNode

class UnitExpr : Expr {
    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String {
        return "()"
    }
}

data class SequenceExpr(val expr: Expr, val next: Expr?) : Expr {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        if (next == null)
            "$expr"
        else
            "{$expr; $next}"

}

data class NaturalExpr(val value: Int) : Expr {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        value.toString()
}

data class RefExpr(val id: String) : Expr {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        id
}

data class ApplyExpr(val target: Expr, val args: List<Expr>) : Expr {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        "$target(${args.toString(", ")})"
}

data class UnaryExpr(val op: String, val expr: Expr) : Expr {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        "$op $expr"
}

data class BinaryExpr(val lhs: Expr, val op: String, val rhs: Expr) : Expr {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        "$lhs $op $rhs"
}

data class CondExpr(val cond: Expr, val csq: Expr, val alt: Expr) : Expr {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        "$cond ? $csq : $alt"
}

data class AssignExpr(val target: Expr, val value: Expr) : Expr {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        "$target = $value"
}

data class Param(val id: String, val type: Type) {
    override fun toString(): String =
        "$id: $type"
}

data class FunExpr(val params: List<Param>, val type: Type, val body: Expr) : Expr {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        "(${params.toString(", ")}):$type $body"
}

data class LetExpr(val id: String, val value: Expr, val body: Expr?) : Expr {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        if (body == null)
            "let $id = $value"
        else
            "{let $id = $value; $body}"

}

data class IfExpr(val cond: Expr, val csq: Expr, val alt: Expr?) : Expr {
    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        if (alt == null)
            "if ($cond) $csq"
        else
            "if ($cond) $csq else $alt"

}

data class WhileExpr(val cond: Expr, val body: Expr) : Expr {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        "while ($cond) $body"

}

data class BreakExpr(val value: Expr?) : Expr {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        if (value != null)
            "break $value"
        else
            "break"

}

