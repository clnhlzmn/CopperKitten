//Expressions

sealed class Expr : BaseASTNode() {

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
            "{$first; $second}"

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
            id
    }

    class Apply(val target: Expr, val args: List<Expr>) : Expr() {

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            "{$target}(${args.toString(", ")})"
    }

    class Unary(val operator: String, val operand: Expr) : Expr() {

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            "$operator $operand"
    }

    class Binary(val lhs: Expr, val operator: String, val rhs: Expr) : Expr() {

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            "$lhs $operator $rhs"
    }

    class Cond(val cond: Expr, val csq: Expr, val alt: Expr) : Expr() {

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            "$cond ? $csq : $alt"
    }

    class Assign(val target: Expr, val value: Expr) : Expr() {

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            "$target = $value"
    }

    class Fun(val params: List<Param>, val type: Type, val body: Expr) : Expr() {

        class Param(val id: String, val declType: Type) {
            //for inference
            var typeInfo: Type? = null
            override fun toString(): String =
                "$id: $declType"
        }

        //a list of Expr.Refs that are the variables that this Expr.Fun needs to capture
        val captures = ArrayList<Expr.Ref>()

        var enclosingScope: ASTNode? = null

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            "(${params.toString(", ")}): $type $body"
    }

    class CFun(val id: String, val sig: FunType) : Expr() {

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            "cfun $id $sig"
    }

    class Let(val id: String, val value: Expr, val body: Expr?) : Expr() {

        var enclosingScope: ASTNode? = null

        //for declType inference
        var typeInfo: Type? = null

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            if (body == null)
                "let $id = $value"
            else
                "{let $id = $value; $body}"

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

