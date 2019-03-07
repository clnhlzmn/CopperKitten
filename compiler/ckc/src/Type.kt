//Types

interface Type {
    fun isRefType(): Boolean
}

data class ErrorType(val what: String) : Type {

    override fun isRefType(): Boolean = false

    override fun toString(): String = "Error: $what"
}

object IntType : Type {
    override fun isRefType(): Boolean = false
    override fun toString(): String = "Int"
}

object UnitType : Type {
    override fun isRefType(): Boolean = false
    override fun toString(): String = "Unit"
}

data class FunType(val paramTypes: List<Type>, val returnType: Type) : Type {
    override fun isRefType(): Boolean = true
    override fun toString(): String =
        "(${paramTypes.toString(", ")}): $returnType"
}