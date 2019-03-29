package ck

import ck.analyze.Analyze
import ck.ast.visitors.CompilationVisitor
import ck.ast.visitors.ComputeCapturesVisitor
import ck.ast.visitors.FindTailCalls
import ck.ast.visitors.ScopeLinkingVisitor
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
        options.addOption("d", "debug", false, "generate debug strings")
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
                val debug = cmd.hasOption("d")

                val stream = CharStreams.fromFileName(inputFileName)

                Parse.file(stream).map(
                    { err ->
                        //parse error
                        println("$err\n")
                    },
                    { file ->
                        //check types
                        Analyze.analyze(file.expr, null, null)
                        //find tail calls
                        file.accept(FindTailCalls())
                        //link scopes
                        file.accept(ScopeLinkingVisitor())
                        //compute function captures
                        file.accept(ComputeCapturesVisitor())
                        //compile file
                        val code: List<String> = file.accept(CompilationVisitor(debug))
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

