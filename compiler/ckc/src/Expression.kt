//Expressions

open class Expr(val enclosingScope: ASTNode?) : BaseASTNode()

class UnitExpr(enclosingScope: ASTNode?) : Expr(enclosingScope) {
    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String {
        return "()"
    }
}

class SequenceExpr(enclosingScope: ASTNode?, val expr: Expr, val next: Expr?) : Expr(enclosingScope) {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        if (next == null)
            "$expr"
        else
            "{$expr; $next}"

}

class NaturalExpr(enclosingScope: ASTNode?, val value: Int) : Expr(enclosingScope) {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        value.toString()
}

class RefExpr(enclosingScope: ASTNode?, val id: String) : Expr(enclosingScope) {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        id
}

class ApplyExpr(enclosingScope: ASTNode?, val target: Expr, val args: List<Expr>) : Expr(enclosingScope) {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        "$target(${args.toString(", ")})"
}

class UnaryExpr(enclosingScope: ASTNode?, val op: String, val expr: Expr) : Expr(enclosingScope) {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        "$op $expr"
}

class BinaryExpr(enclosingScope: ASTNode?, val lhs: Expr, val op: String, val rhs: Expr) : Expr(enclosingScope) {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        "$lhs $op $rhs"
}

class CondExpr(enclosingScope: ASTNode?, val cond: Expr, val csq: Expr, val alt: Expr) : Expr(enclosingScope) {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        "$cond ? $csq : $alt"
}

class AssignExpr(enclosingScope: ASTNode?, val target: Expr, val value: Expr) : Expr(enclosingScope) {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        "$target = $value"
}

class Param(enclosingScope: ASTNode?, val id: String, val type: Type) {
    override fun toString(): String =
        "$id: $type"
}

class FunExpr(enclosingScope: ASTNode?, val params: List<Param>, val type: Type, val body: Expr) : Expr(enclosingScope) {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        "(${params.toString(", ")}):$type $body"
}

class LetExpr(enclosingScope: ASTNode?, val id: String, val value: Expr, val body: Expr?) : Expr(enclosingScope) {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        if (body == null)
            "let $id = $value"
        else
            "{let $id = $value; $body}"

}

class IfExpr(enclosingScope: ASTNode?, val cond: Expr, val csq: Expr, val alt: Expr?) : Expr(enclosingScope) {
    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        if (alt == null)
            "if ($cond) $csq"
        else
            "if ($cond) $csq else $alt"

}

class WhileExpr(enclosingScope: ASTNode?, val cond: Expr, val body: Expr) : Expr(enclosingScope) {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        "while ($cond) $body"

}

class BreakExpr(enclosingScope: ASTNode?, val value: Expr?) : Expr(enclosingScope) {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        if (value != null)
            "break $value"
        else
            "break"

}

