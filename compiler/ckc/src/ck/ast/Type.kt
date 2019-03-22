package ck.ast

import util.extensions.toDelimitedString

//Types

sealed class Type {

    abstract fun isRefType(): Boolean
    abstract fun isPolyType(): Boolean

    companion object {

        var i = 0

        fun newId(): String =
            "${i++}"

        fun newVar(): Var =
            Var(newId())

        //take a type and return a simple version of it
        //where vars have been replaced by their instances
        fun simplify(t: Type): Type =
            when {
                t is Var && t.instance == null -> t
                t is Var -> simplify(t.instance!!)
                t is Op -> Op(
                    t.operator,
                    t.params.map { p -> simplify(p) })
                else -> t
            }

        //takes a simplified type for t1 and t2,
        //returns a list of substitutions formed by
        //associating Vars in t1 with matching types in t2
        fun getSubstitutions(t1: Type, t2: Type): Set<Pair<String, Type>> =
            when {
                t1 is Var -> setOf(Pair(t1.id, t2))
                t1 is Op && t2 is Op && t1.params.size == t2.params.size ->
                    t1.params.zip(t2.params).flatMap { p -> getSubstitutions(p.first, p.second) }.toSet()
                else -> emptySet()
            }

        //takes substitutions from getSubstitutions and applies them to a simplified type t
        fun apply(subs: Set<Pair<String, Type>>, t: Type): Type =
            when (t) {
                is Var -> {
                    val found = subs.find { p -> p.first == t.id }
                    found?.second ?: t
                }
                is Op -> Op(
                    t.operator,
                    t.params.map { p -> apply(subs, p) })
                else -> t
            }

    }

    data class Error(val what: String) : Type() {

        override fun isPolyType(): Boolean {
            TODO("not implemented")
        }

        override fun isRefType(): Boolean {
            TODO("not implemented")
        }

        override fun toString(): String = "util.extensions.Error: $what"

    }

    data class Var(val id: String) : Type() {
        //TODO: eliminate instance and store type var assignments in env

        var instance: Type? = null

        override fun isPolyType(): Boolean =
            if (instance == null)
                true
            else
                instance!!.isPolyType()

        override fun isRefType(): Boolean {
            TODO("not implemented")
        }

        override fun toString(): String =
            if (instance != null) "$instance"
            else id

    }

    data class Op(val operator: String, val params: List<Type>) : Type() {

        constructor(operator: String) : this(operator, emptyList())

        override fun isPolyType(): Boolean = params.any { p -> p.isPolyType() }

        override fun isRefType(): Boolean =
            when (operator) {
                "Int" -> false
                "Unit" -> false
                else -> true
            }

        override fun toString(): String =
            if (operator == "Fun")
                "(${params.take(params.size - 1).toDelimitedString(", ")}): ${params.last()}"
            else
                "$operator ${params.toDelimitedString(", ")}"
    }

}