

//Types

open class Type

data class SimpleType(val id:String) : Type() {
    override fun toString(): String = id
}

data class FunType(val argTypes:List<Type>, val returnType:Type) : Type() {
    override fun toString(): String =
            if (argTypes.isEmpty())
                "() -> $returnType"
            else
                "(${argTypes.map { a -> a.toString() }.reduce { acc, s -> "$acc, $s" }}) -> $returnType"
}