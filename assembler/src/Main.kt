import org.antlr.v4.runtime.*

val stream = CharStreams.fromString(
    "push -1    //start at -1\n" +
        "Loop:      //begin loop here\n" +
        "push 1\n" +
        "add       //add 1\n" +
        "dup\n" +
        "push 26\n" +
        "cmp       //cmp to 27\n" +
        "jumpz End //if equal then end\n" +
        "dup\n" +
        "push 65\n" +
        "add       //else add 'A'\n" +
        "out       //print\n" +
        "jump Loop\n" +
        "End:\n" +
        "halt"
)
val lexer = ckaLexer(stream)
val tokens = CommonTokenStream(lexer)
val parser = ckaParser(tokens)
val context = parser.file()

fun main() {
    val tc = TargetContext({ mnemonic -> "(enum vm_op_code)${mnemonic.toUpperCase()}" }, 4)
    val pc = FileVisitor().visit(context)
    val oc = OutputContext(1000, 100)
    for (inst in pc.instructions) {
        inst.emit(pc, tc, oc)
    }
    println(oc.emit())
}