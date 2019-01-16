

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

data class ApplyExpr(val target:Expr, val args:Exprs?) : Expr() {
    override fun toString(): String =
        if (args == null)
            "$target()"
        else
            "$target($args)"
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

data class Param(val id:String, val type:Type) {
    override fun toString(): String =
        "$id: $type"
}

data class FunExpr(val params:Params?, val type:Type?, val body:Statement) : Expr() {
    override fun toString(): String {
        return if (params == null)
            "() ->${if (type != null) " $type" else ""} $body"
        else
            "($params) ->${if (type != null) " $type" else ""} $body"
    }

}

data class LetExpr(val id:String, val value:Expr, val body:Expr) : Expr()

//a list of expressions
data class Exprs(val expr:Expr, val exprs: Exprs?) {
    override fun toString(): String =
        if (exprs != null)
            "$expr, $exprs"
        else
            "$expr"
}

//a list of function params
data class Params(val param: Param, val params: Params?) {
    override fun toString(): String =
        if (params != null)
            "$param, $params"
        else
            "$param"
}

