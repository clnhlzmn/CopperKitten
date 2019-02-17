import org.antlr.v4.runtime.*

val stream = CharStreams.fromString(
    "Main:\n" +
        "push 65\n" +
        "out\n" +
        "jump Main"
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