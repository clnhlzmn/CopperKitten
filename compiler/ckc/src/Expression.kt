//Expressions

interface Expr : ASTNode

class EmptyExpr : Expr {
    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)
}

data class SequenceExpr(val expr: Expr, val next: Expr?) : Expr {
    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String {
        return "{$expr${if (next != null) "; $next" else ""}}"
    }

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
        "($op $expr)"
}

data class BinaryExpr(val lhs: Expr, val op: String, val rhs: Expr) : Expr {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        "($lhs $op $rhs)"
}

data class CondExpr(val cond: Expr, val con: Expr, val alt: Expr) : Expr {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        "($cond ? $con : $alt)"
}

data class AssignExpr(val target: Expr, val value: Expr) : Expr {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        "($target = $value)"
}

data class Param(val id: String, val type: Type) {
    override fun toString(): String =
        "$id: $type"
}

data class FunExpr(val params: List<Param>, val type: Type, val body: Expr) : Expr {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        "(${params.toString(", ")}) :$type $body"
}

data class LetExpr(val id: String, val value: Expr, val body: Expr?) : Expr {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    override fun toString(): String =
        "($id = $value${if (body == null) "" else " in $body"})"

}

data class IfExpr(val cond: Expr, val csq: Expr, val alt: Expr?) : Expr {
    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    //TODO: toString

}

data class ForExpr(val init: Expr?, val cond: Expr, val fin: Expr?, val body: Expr) : Expr {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)

    //TODO: toString


}

data class ReturnExpr(val value: Expr?) : Expr {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)



}

data class BreakExpr(val value: Expr?) : Expr {

    override fun <T> accept(visitor: ASTVisitor<T>): T =
        visitor.visit(this)



}

