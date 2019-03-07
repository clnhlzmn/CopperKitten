import org.antlr.v4.runtime.*

val stream = CharStreams.fromString(
    "42"
    //"{let foo = 42; ():Int {{():Int bar}()}}" //unbound reference bar
)

fun main() {
    val lexer = ckLexer(stream)
    val tokens = CommonTokenStream(lexer)
    val parser = ckParser(tokens)
    val parseError = arrayOf(false)
    parser.removeErrorListeners()
    parser.addErrorListener(DescriptiveErrorListener(parseError))
    val context = parser.file()
    if (!parseError[0]) {
        //get AST
        val res = context.accept(FileVisitor())
        //link enclosingScope fields
        res.accept(ScopeBuildingVisitor())
        //get program type
        val type = res.accept(GetTypeVisitor())
        //print type
        println(type)
        if (type !is ErrorType) {
            //compute function captures
            res.accept(ComputeCapturesVisitor())
            val code = compileFunctionBody(res)
            //print AST
            println(res)
            //print code
            println(code.toString("\n"))
        }
    }
}

