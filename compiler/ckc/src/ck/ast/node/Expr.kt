package ck.ast.node

import ck.ast.Type
import ck.ast.visitors.ASTVisitor
import util.extensions.toDelimitedString

//Expressions

sealed class Expr(var t: Type) : BaseASTNode() {

    var enclosingScope: ASTNode? = null

    class Tuple(val exprs: List<Expr> = ArrayList()): Expr(Type.Op("Tuple", exprs.map { e -> e.t })) {

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            "(${exprs.toDelimitedString(", ")})"

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Tuple

            if (exprs != other.exprs) return false

            return true
        }

        override fun hashCode(): Int {
            return exprs.hashCode()
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

        var isTailCall = false

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            "{$fn}(${args.toDelimitedString(", ")})"

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

    class Fun(val params: List<Param>, val body: Expr, t: Type) : Expr(t) {

        class Param(val id: String, val t: Type) {
            override fun toString(): String =
                id

            override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (javaClass != other?.javaClass) return false

                other as Param

                if (id != other.id) return false

                return true
            }

            override fun hashCode(): Int {
                return id.hashCode()
            }
        }

        //a list of ck.ast.node.Expr.Refs that are the variables that this ck.ast.node.Expr.Fun needs to capture
        val captures = ArrayList<Ref>()

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            "{(${params.toDelimitedString(", ")}): $body}"

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Fun

            if (params != other.params) return false
            if (body != other.body) return false

            return true
        }

        override fun hashCode(): Int {
            var result = params.hashCode()
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

    data class Binding(val id: String, val type: Type?, val value: Expr) {
        override fun toString(): String =
            "$id${if (type != null) ": $type" else ""} = $value"
    }

    class Let(val binding: Binding, val body: Expr, t: Type) : Expr(t) {

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String =
            "{let $binding; $body}"

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Let

            if (binding != other.binding) return false
            if (body != other.body) return false

            return true
        }

        override fun hashCode(): Int {
            var result = binding.hashCode()
            result = 31 * result + body.hashCode()
            return result
        }

    }

    class LetRec(val bindings: List<Binding>, val body: Expr, t: Type) : Expr(t) {

        override fun <T> accept(visitor: ASTVisitor<T>): T =
            visitor.visit(this)

        override fun toString(): String {
            val bindingsString = bindings.map { p -> "$p" }.toDelimitedString(" and ")
            return "{let rec $bindingsString; $body}"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as LetRec

            if (bindings != other.bindings) return false
            if (body != other.body) return false

            return true
        }

        override fun hashCode(): Int {
            var result = bindings.hashCode()
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

}

