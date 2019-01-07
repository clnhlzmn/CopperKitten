

package cph.ck.compiler

import compiler.ckBaseListener
import compiler.ckLexer
import compiler.ckParser
import org.antlr.v4.runtime.*
import org.antlr.v4.runtime.tree.ParseTreeWalker

val stream = CharStreams.fromString("let foo = 42 ; let bar = \\():Int 42")
val lexer = ckLexer(stream)
val tokens = CommonTokenStream(lexer)
val parser = ckParser(tokens)
val context = parser.file()

fun main() {
    println("Hello, World!")
    ParseTreeWalker().apply {
        walk(object : ckBaseListener() {
            override fun enterStatement(ctx: ckParser.StatementContext) {
                println("a statement")
                println(ctx.text)
            }
        }, context)
    }
}

