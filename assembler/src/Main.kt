import org.antlr.v4.runtime.*

val stream = CharStreams.fromString(
    "push -1\n" +   //start at -1
        "Loop:\n" +     //begin loop here
        "push 1\n" +
        "add\n" +       //add 1
        "dup\n" +
        "push 26\n" +
        "cmp\n" +       //cmp to 27
        "jumpz End\n" + //if equal then end
        "dup\n" +
        "push 65\n" +
        "add\n" +       //else add 'A'
        "out\n" +       //print
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