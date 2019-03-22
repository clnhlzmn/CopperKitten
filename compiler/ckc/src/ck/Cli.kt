package ck

import ck.grammar.DescriptiveErrorListener
import ck.ast.Type
import ck.analyze.Analyze
import ck.ast.node.CkFile
import ck.ast.node.Expr
import ck.ast.visitors.ComputeCapturesVisitor
import ck.ast.visitors.ComputeInstancesVisitor
import ck.ast.visitors.ScopeLinkingVisitor
import ck.ast.visitors.compileCkFile
import ck.grammar.visitors.FileVisitor
import ckLexer
import ckParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.apache.commons.cli.*
import util.extensions.toDelimitedString
import java.io.File

class Cli(val args: Array<String>) {

    private val options = Options()

    init {
        options.addOption("h", "help", false, "show help")
        options.addRequiredOption("i", "input", true, "input file")
        options.addOption("o", "output", true, "output file")
    }

    fun parse() {

        val cliParser = DefaultParser()

        val cmd: CommandLine?
        try {
            cmd = cliParser.parse(options, args)

            if (cmd!!.hasOption("h")) {
                help()
            } else {

                val inputFileName = cmd.getOptionValue("i")!!
                val outputFileName = cmd.getOptionValue("o")

                val stream = CharStreams.fromFileName(inputFileName)

                val lexer = ckLexer(stream)
                val tokens = CommonTokenStream(lexer)
                val ckParser = ckParser(tokens)
                val parseError: MutableList<String> = ArrayList()
                ckParser.removeErrorListeners()
                ckParser.addErrorListener(DescriptiveErrorListener(parseError))
                val context: ckParser.FileContext = ckParser.file()
                if (parseError.isEmpty()) {
                    //get AST
                    val res: CkFile = context.accept(FileVisitor())

                    val exprType = Analyze.analyze(res.expr, null, null)
//                    println(ck.analyze.Analyze.prune(exprType))
//                    println(res.expr)
                    res.expr.accept(ScopeLinkingVisitor())
                    res.expr.accept(ComputeInstancesVisitor())

//                    println(res.expr)

                    val expanded = Expr.expand(res.expr)

                    val file = CkFile(res.defs, expanded)

                    file.accept(ScopeLinkingVisitor())

//                  check if error type
                    if (file.expr.t !is Type.Error) {
                        //compute function captures
                        file.expr.accept(ComputeCapturesVisitor())
                        //compile file
                        val code: List<String> = compileCkFile(file)
                        //determine output location
                        if (outputFileName != null) {
                            File(outputFileName).printWriter().use { out ->
                                out.print(code.toDelimitedString("\n"))
                            }
                        } else {
                            println(code.toDelimitedString("\n"))
                        }
                    } else {
                        //print error
                        println(file.expr.t)
                    }
                } else {
                    //parse error
                    println(parseError.toDelimitedString("\n"))
                }
            }

        } catch (e: ParseException) {
            help()
        } catch (e: RuntimeException) {
            e.printStackTrace()
        }

    }

    private fun help() {
        // This prints out some help
        HelpFormatter().printHelp("h", options)
    }
}

