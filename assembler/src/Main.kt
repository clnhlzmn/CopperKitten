import org.antlr.v4.runtime.*

val stream = CharStreams.fromString(
    "push 12000\n" +
        "Main:\n" +
        "push Main\n" +
        "Next:\n" +
        "jump Next\n"
)
val lexer = ckaLexer(stream)
val tokens = CommonTokenStream(lexer)
val parser = ckaParser(tokens)
val context = parser.file()

fun main() {
    val tc = TargetContext("program", { mnemonic -> "(vm_op_code)${mnemonic.toUpperCase()}" }, 2)
    val res = FileVisitor().visit(context)
    val oc = OutputContext()
    for (inst in res.instructions) {
        inst.emit(res, tc, oc)
    }
    println(oc)
}