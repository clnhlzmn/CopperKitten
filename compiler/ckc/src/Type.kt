//Types

sealed class Type {
    abstract fun isRefType(): Boolean

    data class Error(val what: String): Type() {
        override fun isRefType(): Boolean = false
        override fun toString(): String = "Error: $what"
    }

    data class Var(val id: String): Type() {
        var instance: Type? = null
        override fun isRefType(): Boolean {
            TODO("not implemented")
        }
        override fun toString(): String =
            if (instance != null) "$instance"
            else id
    }

    companion object {

        var i = 0

        fun newId(): String =
            "${i++}"

        fun newVar(): Var =
            Var(newId())
    }

    data class Op(val operator: String, val params: List<Type>): Type() {

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