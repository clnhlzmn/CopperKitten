package ck.grammar

import ck.ast.node.CkFile
import ck.ast.node.Expr
import ck.grammar.visitors.ExprVisitor
import ck.grammar.visitors.FileVisitor
import ckLexer
import ckParser
import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CodePointCharStream
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.misc.ParseCancellationException
import util.either.Either

sealed class Parse {

    data class Error(val what: String)

    companion object {

        fun getParser(stream: CharStream): ckParser {
            val lexer = ckLexer(stream)
            val tokens = CommonTokenStream(lexer)
            val ckParser = ckParser(tokens)
            ckParser.removeErrorListeners()
            ckParser.addErrorListener(ThrowingErrorListener.INSTANCE)
            return ckParser
        }

        fun expr(stream: CodePointCharStream): Either<Error, Expr> {
            val parser = getParser(stream)
            return try {
                val context = parser.expr()
                Either.right(context.accept(ExprVisitor()))
            } catch (e: ParseCancellationException) {
                Either.left(Error(e.localizedMessage))
            }
        }

        fun file(stream: CodePointCharStream): Either<Error, CkFile> {
            val parser = getParser(stream)
            return try {
                val context = parser.file()
                Either.right(context.accept(FileVisitor()))
            } catch (e: ParseCancellationException) {
                Either.left(Error(e.localizedMessage))
            }
        }

    }

}