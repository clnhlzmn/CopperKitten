//Types

sealed class Type {
    abstract fun isRefType(): Boolean

    data class Error(val what: String): Type() {
        override fun isRefType(): Boolean = false
        override fun toString(): String = "Error: $what"
    }

    data class Unknown(val id: String): Type() {
        override fun isRefType(): Boolean {
            TODO("not implemented")
        }
        override fun toString(): String = id
    }

    companion object {

        var i = 0

        fun newUnknown(): Unknown =
            Unknown("T${i++}")
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