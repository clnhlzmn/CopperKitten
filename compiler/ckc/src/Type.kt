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

        fun distinctVars(t: Type): Set<Type> {
            return if (!t.isPolyType())
                emptySet()
            else {
                val distinct = HashSet<Type>()
                when (t) {
                    is Var -> distinct.add(t)
                    is Op -> t.params.forEach { p -> distinct.addAll(distinctVars(p)) }
                }
                distinct
            }
        }

        fun instances(t: Type): List<Type> {
            val distinct = distinctVars(t)
            //create 2^(distinct.size) instances

            return emptyList()
        }

    }

    data class Error(val what: String): Type() {
        override fun isPolyType(): Boolean = false
        override fun isRefType(): Boolean = false
        override fun toString(): String = "Error: $what"
    }

    data class Var(val id: String): Type() {
        var instance: Type? = null
        override fun isPolyType(): Boolean = instance == null
        override fun isRefType(): Boolean {
            TODO("not implemented")
        }
        override fun toString(): String =
            if (instance != null) "$instance"
            else id
    }

    data class Op(val operator: String, val params: List<Type>): Type() {

        override fun isPolyType(): Boolean = params.any { p -> p.isPolyType() }

        constructor(operator: String): this(operator, emptyList())

        override fun isRefType(): Boolean =
            when (operator) {
                "Int" -> false
                "Unit" -> false
                else -> true
            }

        override fun toString(): String =
            if (operator == "Fun")
                "(${params.take(params.size - 1).toString(", ")}): ${params.last()}"
            else
                "$operator ${params.toString(", ")}"
    }

}