//Expressions

open class Expr : BaseASTNode()

class UnitExpr() : Expr() {
    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String {
        return "()"
    }
}

class SequenceExpr(val first: Expr, val second: Expr?) : Expr() {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        when (second) {
            null -> "$first"
            else -> "{$first; $second}"
        }

}

class NaturalExpr(val value: Int) : Expr() {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        value.toString()
}

class RefExpr(val id: String) : Expr() {

    var enclosingScope: ASTNode? = null

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        id
}

class ApplyExpr(val target: Expr, val args: List<Expr>) : Expr() {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        "{$target}(${args.toString(", ")})"
}

class UnaryExpr(val operator: String, val operand: Expr) : Expr() {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        "$operator $operand"
}

class BinaryExpr(val lhs: Expr, val operator: String, val rhs: Expr) : Expr() {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        "$lhs $operator $rhs"
}

class CondExpr(val cond: Expr, val csq: Expr, val alt: Expr) : Expr() {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        "$cond ? $csq : $alt"
}

class AssignExpr(val target: Expr, val value: Expr) : Expr() {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        "$target = $value"
}

class FunExpr(val params: List<Param>, val type: Type, val body: Expr) : Expr() {

    class Param(val id: String, val type: Type) {
        override fun toString(): String =
            "$id: $type"
    }

    //a list of refExprs that are the variables that this funExpr needs to capture
    val captures = ArrayList<RefExpr>()

    var enclosingScope: ASTNode? = null

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        "(${params.toString(", ")}): $type $body"
}

class LetExpr(val id: String, val value: Expr, val body: Expr?) : Expr() {

    var enclosingScope: ASTNode? = null

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        if (body == null)
            "let $id = $value"
        else
            "{let $id = $value; $body}"

}

class IfExpr(val cond: Expr, val csq: Expr, val alt: Expr?) : Expr() {
    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        if (alt == null)
            "if ($cond) $csq"
        else
            "if ($cond) $csq else $alt"

}

class WhileExpr(val cond: Expr, val body: Expr) : Expr() {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        "while ($cond) $body"

}

class BreakExpr(val value: Expr?) : Expr() {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        if (value != null)
            "break $value"
        else
            "break"

}

