import org.antlr.v4.runtime.*

val stream = CharStreams.fromString(
    "push 128"
)
val lexer = ckaLexer(stream)
val tokens = CommonTokenStream(lexer)
val parser = ckaParser(tokens)
val context = parser.file()

fun main() {
    //base "address" of the compiled program, which is really uint8_t program[] = {...};
    val programBaseAddress = "program"
    //to convert mnemonic to "opcode", which is really an enum
    val convert = { mnemonic:String -> "(vm_op_code)${mnemonic.toUpperCase()}" }
    //target word size in bytes
    val wordSize = 4
    val res = FileVisitor().visit(context)
    for (inst in res.instructions) {
        println(inst.emit(programBaseAddress, convert, wordSize))
    }
    println(res)
}