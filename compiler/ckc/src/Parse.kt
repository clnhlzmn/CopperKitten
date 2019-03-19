import org.antlr.v4.runtime.CodePointCharStream
import org.antlr.v4.runtime.CommonTokenStream

object Parse {

    data class Error(val what: String)

    fun getParser(stream: CodePointCharStream): ckParser {
        val lexer = ckLexer(stream)
        val tokens = CommonTokenStream(lexer)
        val ckParser = ckParser(tokens)
        return ckParser
    }

    fun addErrorListener(parser: ckParser, errors: MutableList<String>) {
        parser.removeErrorListeners()
        parser.addErrorListener(DescriptiveErrorListener(errors))
    }

    fun expr(stream: CodePointCharStream): Result<Error, Expr> {
        val parser = getParser(stream)
        val errors = ArrayList<String>()
        addErrorListener(parser, errors)
        val context = parser.expr()
        return if (errors.isNotEmpty())
            Result.Error(Error(errors.toString(", ")))
        else
            Result.Value(context.accept(ExprVisitor()))
    }

    fun file(stream: CodePointCharStream): Result<Error, Expr> {
        val lexer = ckLexer(stream)
        val tokens = CommonTokenStream(lexer)
        val ckParser = ckParser(tokens)
        val parseError:MutableList<String> = ArrayList()
        ckParser.removeErrorListeners()
        ckParser.addErrorListener(DescriptiveErrorListener(parseError))
        val context = ckParser.expr()
        return if (parseError.isNotEmpty())
            Result.Error(Error(parseError.toString(", ")))
        else
            Result.Value(context.accept(ExprVisitor()))
    }

}