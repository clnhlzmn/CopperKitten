import org.antlr.v4.runtime.*

val stream = CharStreams.fromString(
    "Main:\n" +
        "enter\n" +
        "layout [ 0, 1, 10 ]\n" +
        "leave\n" +
        "jump Main"
)
val lexer = ckaLexer(stream)
val tokens = CommonTokenStream(lexer)
val parser = ckaParser(tokens)
val context = parser.file()

fun main() {
    val tc = TargetContext("program", { mnemonic -> "(vm_op_code)${mnemonic.toUpperCase()}" }, 2)
    val pc = FileVisitor().visit(context)
    val oc = OutputContext()
    for (inst in pc.instructions) {
        inst.emit(pc, tc, oc)
    }
    println(oc)
}