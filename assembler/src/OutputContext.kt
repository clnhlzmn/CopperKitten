
//represents areas of output c file in which to
//emit various parts of the compiled program
class OutputContext {
    //where layout functions are stored
    val layoutFunctions = HashSet<LayoutFunction>()
    //where the program array goes
    val program = StringBuilder()

    override fun toString(): String {
        return "${layoutFunctions.map { lf -> lf.toString() }.fold("") { acc, s -> "$acc\n$s"}}\n" +
                "uint8_t program[] = {$program};"
    }
}