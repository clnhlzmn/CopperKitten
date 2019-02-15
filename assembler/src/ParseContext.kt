class ParseContext {
    val labels: MutableMap<String, Int> = HashMap()
    val instructions: MutableList<Instruction> = ArrayList()
    override fun toString(): String {
        return "(ParseContext ${labels.map { me -> "${me.key} : ${me.value}" }} " +
            "${instructions.map { i -> i.toString() }}" +
            ")"
    }
}

