
//represents areas of output c file in which to
//emit various parts of the compiled program
class OutputContext {
    //where layout functions are stored
    val layoutFunctions = HashSet<LayoutFunction>()
    //where the program array goes
    val program = ArrayList<String>()

    fun emit(): String {
        return "${layoutFunctions.map { lf -> lf.emit() }.fold("") { acc, s -> "$acc\n$s"}}\n" +
            //TODO: program array can't be initialized at compile time with arithmetic on addresses
            "uint8_t program[] = {\n" +
            program.map { op -> "\t$op,\n" }.fold("") { acc, s -> acc + s } +
            "};"
    }
}