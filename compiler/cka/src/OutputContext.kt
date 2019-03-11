//represents areas of output c file in which to
//define various parts of the compiled program
class OutputContext(
    private val memSize: Int,
    private val stackSize: Int) {

    //where layout functions are stored
    private val functions = ArrayList<Function>()

    fun addFunction(lf: Function): Long {
        val ret = functions.indexOf(lf)
        if (ret == -1) {
            functions.add(lf)
            return (functions.size - 1).toLong()
        }
        return ret.toLong()
    }

    //where the program array goes
    val program = ArrayList<String>()

    fun emit(): String {
        return "#include \"vm.h\"\n" +
            "#include \"copying_gc.h\"\n" +
            "\n" +
            "#define MEM_SIZE $memSize\n" +
            "intptr_t gc_mem[MEM_SIZE];\n" +
            "struct gc gc_instance;\n" +
            "\n" +
            "#define STACK_SIZE $stackSize\n" +
            "intptr_t stack_mem[STACK_SIZE];\n" +
            "struct vm vm_instance;\n" +
            "\n" +
            functions.map { f -> f.declare() }.fold("") { acc, s -> "$acc\n$s" } +
            "\n" +
            functions.map { lf -> lf.define() }.fold("") { acc, s -> "$acc\n$s" } +
            "\n" +
            "void *functions[] = {\n" +
            functions.map { lf -> "\t${lf.name()},\n" }.fold("") { acc, s -> acc + s } +
            "\tNULL,\n" +
            "};\n\n" +
            "uint8_t program[] = {\n" +
            program.map { op -> "\t$op,\n" }.fold("") { acc, s -> acc + s } +
            "};\n" +
            "\n" +
            "int main(void) {\n" +
            "\tgc_init(&gc_instance, gc_mem, MEM_SIZE);\n" +
            "\tvm_init(&vm_instance, &gc_instance, stack_mem, functions);\n" +
            "\tvm_execute(&vm_instance, program);\n" +
            "\treturn 0;\n" +
            "}"
    }
}