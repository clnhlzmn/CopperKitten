//Expressions

sealed class Expr(var t: Type) : BaseASTNode() {

    class Error(val what: String): Expr(Type.Error(what)) {
        override fun toString(): String {
            return "Error: $what"
        }
    }

    object Unit : Expr(Type.Op("Unit", emptyList())) {
        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String {
            return "()"
        }
    }

    class Sequence(val first: Expr, val second: Expr, t: Type) : Expr(t) {
        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            "{$first; $second}"

    }

    class Natural(val value: Long) : Expr(Type.Op("Int", emptyList())) {

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            value.toString()
    }

    class Ref(val id: String, t: Type) : Expr(t) {

        var enclosingScope: ASTNode? = null

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            id
    }

    class Apply(val fn: Expr, val args: List<Expr>, t: Type) : Expr(t) {

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            "{$fn}(${args.toString(", ")})"
    }

    class Unary(val operator: String, val operand: Expr, t: Type) : Expr(t) {

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            "{$operator $operand}"
    }

    class Binary(val lhs: Expr, val operator: String, val rhs: Expr, t: Type) : Expr(t) {

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            "{$lhs $operator $rhs}"
    }

    class Cond(val cond: Expr, val csq: Expr, val alt: Expr, t: Type) : Expr(t) {

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            "{$cond ? $csq : $alt}"
    }

    class Assign(val target: Expr, val value: Expr, t: Type) : Expr(t) {

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            "{$target = $value}"
    }

    class Fun(val params: List<Param>, val declType: Type?, val body: Expr, t: Type) : Expr(t) {

        class Param(val id: String, val declType: Type?, val t: Type) {
            override fun toString(): String =
                id
        }

        //a list of Expr.Refs that are the variables that this Expr.Fun needs to capture
        val captures = ArrayList<Expr.Ref>()

        var enclosingScope: ASTNode? = null

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            "{(${params.toString(", ")}): $body}"
    }

    class CFun(val id: String, val sig: Type, t: Type) : Expr(t) {

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            "cfun $id $sig"
    }

    class Let(val id: String, val value: Expr, val body: Expr, t: Type) : Expr(t) {

        var enclosingScope: ASTNode? = null

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            "{let $id = $value; $body}"

    }

    class If(val cond: Expr, val csq: Expr, val alt: Expr?, t: Type) : Expr(t) {
        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            if (alt == null)
                "if ($cond) $csq"
            else
                "if ($cond) $csq else $alt"

    }

    class While(val cond: Expr, val body: Expr, t: Type) : Expr(t) {

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            "while ($cond) $body"

    }

    class Break(val value: Expr?, t: Type) : Expr(t) {

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            if (value != null)
                "break $value"
            else
                "break"

    }
}

