//Expressions

sealed class Expr : BaseASTNode() {

    //expression annotated type for inference algorithm
    var aType: Type = Type.newUnknown()

    object Unit : Expr() {
        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String {
            return "()"
        }
    }

    class Sequence(val first: Expr, val second: Expr) : Expr() {

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            "{$first; $second}::$aType"

    }

    class Natural(val value: Long) : Expr() {

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            value.toString()
    }

    class Ref(val id: String) : Expr() {

        var enclosingScope: ASTNode? = null

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            "$id::$aType"
    }

    class Apply(val fn: Expr, val args: List<Expr>) : Expr() {

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            "{$fn}(${args.toString(", ")})::$aType"
    }

    class Unary(val operator: String, val operand: Expr) : Expr() {

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            "{$operator $operand}::$aType"
    }

    class Binary(val lhs: Expr, val operator: String, val rhs: Expr) : Expr() {

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            "{$lhs $operator $rhs}::$aType"
    }

    class Cond(val cond: Expr, val csq: Expr, val alt: Expr) : Expr() {

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            "{$cond ? $csq : $alt}::$aType"
    }

    class Assign(val target: Expr, val value: Expr) : Expr() {

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            "{$target = $value}::$aType"
    }

    class Fun(val params: List<Param>, val type: Type?, val body: Expr) : Expr() {
//    class Fun(val params: List<Param>, val type: Type, val body: Expr) : Expr() {

        class Param(val id: String, val type: Type?) {
//        class Param(val id: String, val type: Type) {

            //expression annotated type for inference algorithm
            var aType: Type = Type.newUnknown()

            override fun toString(): String =
//                "$id${if (type == null) "" else ": $type"}"
                "$id::$aType"
        }

        //a list of Expr.Refs that are the variables that this Expr.Fun needs to capture
        val captures = ArrayList<Expr.Ref>()

        var enclosingScope: ASTNode? = null

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
//            "(${params.toString(", ")}): ${if (type == null) "" else "$type"} $body"
            "{(${params.toString(", ")}): $body}::$aType"
    }

    class CFun(val id: String, val sig: Type.Fun) : Expr() {

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            "cfun $id $sig"
    }

    class Let(val id: String, val value: Expr, val body: Expr) : Expr() {

        //binding type annotation for inference
        var bType: Type = Type.newUnknown()

        var enclosingScope: ASTNode? = null

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            "{let $id::$bType = $value; $body}::$aType"

    }

    class If(val cond: Expr, val csq: Expr, val alt: Expr?) : Expr() {
        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            if (alt == null)
                "if ($cond) $csq"
            else
                "if ($cond) $csq else $alt"

    }

    class While(val cond: Expr, val body: Expr) : Expr() {

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            "while ($cond) $body"

    }

    class Break(val value: Expr?) : Expr() {

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            if (value != null)
                "break $value"
            else
                "break"

    }
}

