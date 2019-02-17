
//represents areas of output c file in which to
//emit various parts of the compiled program
class OutputContext {

    //where layout functions are stored
    private val layoutFunctions = ArrayList<LayoutFunction>()

    fun addLayout(lf: LayoutFunction): Int {
        val ret = layoutFunctions.indexOf(lf)
        if (ret == -1) {
            layoutFunctions.add(lf)
            return layoutFunctions.size - 1
        }
        return ret
    }

    //where the program array goes
    val program = ArrayList<String>()

    fun emit(): String {
        return layoutFunctions.map { lf -> lf.emit() }.fold("") { acc, s -> "$acc\n$s"} +
            "foreach_t layouts[] = {\n" +
            layoutFunctions.map { lf -> "\t${lf.name()},\n" }.fold("") { acc, s -> acc + s } +
            "};\n\n" +
            "uint8_t program[] = {\n" +
            program.map { op -> "\t$op,\n" }.fold("") { acc, s -> acc + s } +
            "};"
    }
}