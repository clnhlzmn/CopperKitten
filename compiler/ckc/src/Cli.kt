
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.apache.commons.cli.*;
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
                val parseError:MutableList<String> = ArrayList()
                ckParser.removeErrorListeners()
                ckParser.addErrorListener(DescriptiveErrorListener(parseError))
                val context: ckParser.FileContext = ckParser.file()
                if (parseError.isEmpty()) {
                    //get AST
                    val res: CkFile = context.accept(FileVisitor())
                    //link enclosingScope fields
                    res.accept(ScopeBuildingVisitor())
                    //get program type
                    val type: Type = res.expr.accept(GetTypeVisitor())
                    println(type)
                    println(res.expr)
                    val ae = Infer.infer(res.expr)
                    println(ae)
                    if (type !is Type.Error) {
                        //compute function captures
                        res.expr.accept(ComputeCapturesVisitor())
                        val code: List<String> = compileCkFile(res)
                        if (outputFileName != null) {
                            File(outputFileName).printWriter().use { out ->
                                out.print(code.toString("\n"))
                            }
                        } else {
                            println(code.toString("\n"))
                        }
                    } else {
                        //print error
                        println(type)
                    }
                } else {
                    //parse error
                    println(parseError.toString("\n"))
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

