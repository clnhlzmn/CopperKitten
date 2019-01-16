

//Types

open class Type

data class SimpleType(val id:String) : Type() {
    override fun toString(): String = id
}

data class FunType(val argTypes:Types?, val returnType:Type) : Type() {
    override fun toString(): String =
            if (argTypes == null)
                "() -> $returnType"
            else
                "($argTypes) -> $returnType"
}

data class Types(val type: Type, val types: Types?) {
    override fun toString(): String =
        if (types != null)
            "$type, $types"
        else
            "$type"
}