//Types

open class Type

data class ErrorType(val what: String) : Type() {
    override fun toString(): String = "Error: $what"
}

object IntType : Type() {
    override fun toString(): String = "Int"
}

object UnitType : Type() {
    override fun toString(): String = "Unit"
}

//data class SimpleType(val id: String) : Type() {
//    override fun toString(): String = id
//}

data class FunType(val paramTypes: List<Type>, val returnType: Type) : Type() {
    override fun toString(): String =
        "(${paramTypes.toString(", ")}): $returnType"
}