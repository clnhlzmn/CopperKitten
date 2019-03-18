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

    object Int: Type() {
        override fun isRefType(): Boolean = false
        override fun toString(): String = "Int"
    }

    object Unit: Type() {
        override fun isRefType(): Boolean = false
        override fun toString(): String = "Unit"
    }

    data class Fun(val paramTypes: List<Type>, val returnType: Type): Type() {
        override fun isRefType(): Boolean = true
        override fun toString(): String =
            "(${paramTypes.toString(", ")}): $returnType"
    }
}