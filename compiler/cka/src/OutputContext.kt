//represents information about the target code to be generated
data class OutputContext(
    //to convert mnemonics from cka grammar to opcodes to store in bytecode array
    val convert: (String) -> String,
    //sizeof(intptr_t) on target
    val wordSize: Int,
    //size of heap in units of intptr_t
    private val memSize: Int,
    //size of stack in units of intptr_t
    private val stackSize: Int,
    //name of gc impl
    private val gcImpl: String,
    //array of instructions
    val program:ArrayList<String>
) {

    //where layout functions are stored
    private val functions = ArrayList<Function>()

    private val strings = ArrayList<String>()

    //adds a function if it is not already present
    //returns index of function in function array
    fun addFunction(lf: Function): Long {
        val ret = functions.indexOf(lf)
        if (ret == -1) {
            functions.add(lf)
            return (functions.size - 1).toLong()
        }
        return ret.toLong()
    }

    fun addString(s: String): Long {
        val ret = strings.indexOf(s)
        if (ret == -1) {
            strings.add(s)
            return (strings.size - 1).toLong()
        }
        return ret.toLong()
    }

    //where the program array goes

    fun emit(): String {
        return "#include \"vm.h\"\n" +
            "#include \"$gcImpl\"\n" +
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
            "const char *strings[] = {\n" +
            strings.map { s -> "\t\"$s\",\n" }.fold("") { acc, s -> acc + s } +
            "\tNULL,\n" +
            "};\n" +
            "\n" +
            "int main(void) {\n" +
            "\tgc_init(&gc_instance, gc_mem, MEM_SIZE);\n" +
            "\tvm_init(&vm_instance, &gc_instance, stack_mem, functions, strings);\n" +
            "\tvm_execute(&vm_instance, program);\n" +
            "\treturn 0;\n" +
            "}"
    }
}
