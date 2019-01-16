

//Expressions

open class Expr

data class NaturalExpr(val value:Int) : Expr() {
    override fun toString():String =
            value.toString()
}

data class RefExpr(val id:String) : Expr() {
    override fun toString(): String =
            id
}

data class ApplyExpr(val target:Expr, val args:List<Expr>) : Expr() {
    override fun toString(): String =
            "$target(${if (args.isNotEmpty())
                args.map { a -> a.toString() }.reduce { acc, s -> "$acc, $s" }
            else
                ""})"
}

data class UnaryExpr(val op:String, val expr:Expr) : Expr() {
    override fun toString(): String =
            "$op $expr"
}

data class BinaryExpr(val lhs:Expr, val op:String, val rhs:Expr) : Expr() {
    override fun toString(): String =
            "$lhs $op $rhs"
}

data class CondExpr(val cond:Expr, val con:Expr, val alt:Expr) : Expr() {
    override fun toString(): String =
            "$cond ? $con : $alt"
}

data class AssignExpr(val target:Expr, val value:Expr) : Expr() {
    override fun toString(): String =
            "$target = $value"
}

data class FormalParameter(val id:String, val type:Type) {
    override fun toString(): String =
        "$id: $type"
}

data class FunExpr(val parameters:List<FormalParameter>, val type:Type?, val body:Statement) : Expr() {
    override fun toString(): String {
        if (parameters.isEmpty())
            return "() ->${if (type != null) " $type" else ""} $body"
        else
            return "(${parameters.map { p -> p.toString() }.reduce { acc, s -> "$acc, $s" }}) ->${if (type != null) " $type" else ""} $body"
    }

}

data class LetExpr(val id:String, val value:Expr, val body:Expr) : Expr()