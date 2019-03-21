//Expressions

sealed class Expr(var t: Type) : BaseASTNode() {

    class Error(val what: String): Expr(Type.Error(what)) {
        override fun toString(): String {
            return "Error: $what"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Error

            if (what != other.what) return false

            return true
        }

        override fun hashCode(): Int {
            return what.hashCode()
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

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Sequence

            if (first != other.first) return false
            if (second != other.second) return false

            return true
        }

        override fun hashCode(): Int {
            var result = first.hashCode()
            result = 31 * result + second.hashCode()
            return result
        }

    }

    class Natural(val value: Long) : Expr(Type.Op("Int", emptyList())) {

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            value.toString()

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Natural

            if (value != other.value) return false

            return true
        }

        override fun hashCode(): Int {
            return value.hashCode()
        }
    }

    class Ref(var id: String, t: Type) : Expr(t) {

        var enclosingScope: ASTNode? = null

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            id

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Ref

            if (id != other.id) return false

            return true
        }

        override fun hashCode(): Int {
            return id.hashCode()
        }
    }

    class Apply(val fn: Expr, val args: List<Expr>, t: Type) : Expr(t) {

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            "{$fn}(${args.toString(", ")})"

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Apply

            if (fn != other.fn) return false
            if (args != other.args) return false

            return true
        }

        override fun hashCode(): Int {
            var result = fn.hashCode()
            result = 31 * result + args.hashCode()
            return result
        }
    }

    class Unary(val operator: String, val operand: Expr, t: Type) : Expr(t) {

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            "{$operator $operand}"

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Unary

            if (operator != other.operator) return false
            if (operand != other.operand) return false

            return true
        }

        override fun hashCode(): Int {
            var result = operator.hashCode()
            result = 31 * result + operand.hashCode()
            return result
        }
    }

    class Binary(val lhs: Expr, val operator: String, val rhs: Expr, t: Type) : Expr(t) {

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            "{$lhs $operator $rhs}"

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Binary

            if (lhs != other.lhs) return false
            if (operator != other.operator) return false
            if (rhs != other.rhs) return false

            return true
        }

        override fun hashCode(): Int {
            var result = lhs.hashCode()
            result = 31 * result + operator.hashCode()
            result = 31 * result + rhs.hashCode()
            return result
        }
    }

    class Cond(val cond: Expr, val csq: Expr, val alt: Expr, t: Type) : Expr(t) {

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            "{$cond ? $csq : $alt}"

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Cond

            if (cond != other.cond) return false
            if (csq != other.csq) return false
            if (alt != other.alt) return false

            return true
        }

        override fun hashCode(): Int {
            var result = cond.hashCode()
            result = 31 * result + csq.hashCode()
            result = 31 * result + alt.hashCode()
            return result
        }
    }

    class Assign(val target: Expr, val value: Expr, t: Type) : Expr(t) {

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            "{$target = $value}"

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Assign

            if (target != other.target) return false
            if (value != other.value) return false

            return true
        }

        override fun hashCode(): Int {
            var result = target.hashCode()
            result = 31 * result + value.hashCode()
            return result
        }
    }

    class Fun(val params: List<Param>, val declType: Type?, val body: Expr, t: Type) : Expr(t) {

        class Param(val id: String, val declType: Type?, val t: Type) {
            override fun toString(): String =
                id

            override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (javaClass != other?.javaClass) return false

                other as Param

                if (id != other.id) return false
                if (declType != other.declType) return false

                return true
            }

            override fun hashCode(): Int {
                var result = id.hashCode()
                result = 31 * result + (declType?.hashCode() ?: 0)
                return result
            }
        }

        //a list of Expr.Refs that are the variables that this Expr.Fun needs to capture
        val captures = ArrayList<Expr.Ref>()

        var enclosingScope: ASTNode? = null

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            "{(${params.toString(", ")}): $body}"

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Fun

            if (params != other.params) return false
            if (declType != other.declType) return false
            if (body != other.body) return false

            return true
        }

        override fun hashCode(): Int {
            var result = params.hashCode()
            result = 31 * result + (declType?.hashCode() ?: 0)
            result = 31 * result + body.hashCode()
            return result
        }
    }

    class CFun(val id: String, val sig: Type, t: Type) : Expr(t) {

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            "cfun $id $sig"

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as CFun

            if (id != other.id) return false
            if (sig != other.sig) return false

            return true
        }

        override fun hashCode(): Int {
            var result = id.hashCode()
            result = 31 * result + sig.hashCode()
            return result
        }
    }

    class Let(val id: String, val value: Expr, val body: Expr, t: Type) : Expr(t) {

        var enclosingScope: ASTNode? = null

        //a list of instances that this id will be compiled to, in the case that value is a polymorphic function
        val instances = HashSet<Type>()

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            "{let $id = $value; $body}"

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Let

            if (id != other.id) return false
            if (value != other.value) return false
            if (body != other.body) return false

            return true
        }

        override fun hashCode(): Int {
            var result = id.hashCode()
            result = 31 * result + value.hashCode()
            result = 31 * result + body.hashCode()
            return result
        }

    }

    class If(val cond: Expr, val csq: Expr, val alt: Expr?, t: Type) : Expr(t) {
        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            if (alt == null)
                "if ($cond) $csq"
            else
                "if ($cond) $csq else $alt"

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as If

            if (cond != other.cond) return false
            if (csq != other.csq) return false
            if (alt != other.alt) return false

            return true
        }

        override fun hashCode(): Int {
            var result = cond.hashCode()
            result = 31 * result + csq.hashCode()
            result = 31 * result + (alt?.hashCode() ?: 0)
            return result
        }

    }

    class While(val cond: Expr, val body: Expr, t: Type) : Expr(t) {

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            "while ($cond) $body"

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as While

            if (cond != other.cond) return false
            if (body != other.body) return false

            return true
        }

        override fun hashCode(): Int {
            var result = cond.hashCode()
            result = 31 * result + body.hashCode()
            return result
        }

    }

    class Break(val value: Expr?, t: Type) : Expr(t) {

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            if (value != null)
                "break $value"
            else
                "break"

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Break

            if (value != other.value) return false

            return true
        }

        override fun hashCode(): Int {
            return value?.hashCode() ?: 0
        }

    }

    companion object {

        fun apply(subs: List<Pair<String, Type>>, e: Expr): Expr =
            when (e) {
                is Error -> e
                Unit -> Unit
                is Sequence -> Sequence(apply(subs, e.first), apply(subs, e.second), Type.apply(subs, Type.simplify(e.t)))
                is Natural -> Natural(e.value)
                is Ref -> Ref(e.id, Type.apply(subs, Type.simplify(e.t)))
                is Apply -> Apply(apply(subs, e.fn), e.args.map { a -> apply(subs, a) }, Type.apply(subs, Type.simplify(e.t)))
                is Unary -> TODO()
                is Binary -> TODO()
                is Cond -> TODO()
                is Assign -> TODO()
                is Fun ->
                    Fun(
                        e.params.map { p -> Fun.Param(p.id, p.declType, Type.apply(subs, Type.simplify(p.t))) },
                        e.declType,
                        apply(subs, e.body),
                        Type.apply(subs, Type.simplify(e.t))
                    )
                is CFun -> e
                is Let -> {
                    Let(e.id, apply(subs, e.value), apply(subs, e.body), Type.apply(subs, Type.simplify(e.t)))
                }
                is If -> TODO()
                is While -> TODO()
                is Break -> TODO()
            }

        fun expand(e: Expr): Expr {
            return when (e) {
                is Error -> e
                Unit -> e
                is Sequence -> Sequence(expand(e.first), expand(e.second), e.t)
                is Natural -> e
                is Ref -> e
                is Apply -> Apply(expand(e.fn), e.args.map { a -> expand(a) }, e.t)
                is Unary -> TODO()
                is Binary -> TODO()
                is Cond -> TODO()
                is Assign -> TODO()
                is Fun -> Fun(e.params, e.declType, expand(e.body), e.t)
                is CFun -> e
                is Let -> {
                    val instance = e.instances.firstOrNull()
                    if (instance == null) {
                        Let(e.id, expand(e.value), expand(e.body), e.t)
                    } else {
                        //get subs (to create value with concrete type)
                        val subs = Type.getSubstitutions(Type.simplify(e.value.t), instance)
                        //apply to value (so value has concrete type)
                        val value = Expr.apply(subs, e.value)
                        if (e.instances.drop(1).count() == 0) {
                            Let(e.id, expand(value), expand(e.body), e.t)
                        } else {
                            //create body (a new let expr with instances - first)
                            val body = Let(e.id, e.value, e.body, e.t)
                            body.instances.addAll(e.instances.drop(1))
                            //return Let with expanded value and expanded body
                            Let(e.id, expand(value), expand(body), e.t)
                        }
                    }
                }
                is If -> TODO()
                is While -> TODO()
                is Break -> TODO()
            }
        }

    }
}

