import org.antlr.v4.runtime.*

val stream = CharStreams.fromString(
    "push 12000\n" +
    "Main:\n" +
    "push Main\n"
)
val lexer = ckaLexer(stream)
val tokens = CommonTokenStream(lexer)
val parser = ckaParser(tokens)
val context = parser.file()

fun main() {
    val tc = TargetContext("program", { mnemonic -> "(vm_op_code)${mnemonic.toUpperCase()}" }, 2)
    val res = FileVisitor().visit(context)
    for (inst in res.instructions) {
        println(inst.emit(res, tc))
    }
    println(res)
}