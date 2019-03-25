package ck

import ck.analyze.Analyze
import ck.ast.visitors.ComputeCapturesVisitor
import ck.ast.visitors.ScopeLinkingVisitor
import ck.ast.visitors.compileCkFile
import ck.grammar.Parse
import org.antlr.v4.runtime.CharStreams
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

                Parse.file(stream).map(
                    { err ->
                        //parse error
                        println("$err\n")
                    },
                    { file ->
                        Analyze.analyze(file.expr, null, null)
                        file.accept(ScopeLinkingVisitor())
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
                    }
                )
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

