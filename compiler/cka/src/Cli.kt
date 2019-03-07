import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.apache.commons.cli.*;
import org.antlr.v4.runtime.BailErrorStrategy
import java.io.File

class Cli(val args: Array<String>) {

    private val options = Options()

    init {
        options.addOption("h", "help", false, "show help")
        options.addRequiredOption("i", "input", true, "input file")
        options.addOption("o", "output", true, "output file")
        options.addOption("s", "stack_size", true, "set stack size (in words)")
        options.addOption("m", "memory_size", true, "set heap size (in words)")
        options.addOption("w", "word_size", true, "set heap size (in bytes)")
    }

    fun parse() {

        val parser = DefaultParser()

        val cmd: CommandLine?
        try {
            cmd = parser.parse(options, args)

            if (cmd!!.hasOption("h")) {
                help()
            } else {

                val inputFileName = cmd.getOptionValue("i")!!
                val outputFileName = cmd.getOptionValue("o")
                val stackSize = cmd.getOptionValue("s")
                val heapSize = cmd.getOptionValue("m")
                val wordSize = cmd.getOptionValue("w")

                val tc = TargetContext(
                    { mnemonic -> mnemonic.toUpperCase() },
                    wordSize?.toInt() ?: 4
                )

                val stream = CharStreams.fromFileName(inputFileName)
                val lexer = ckaLexer(stream)
                val tokens = CommonTokenStream(lexer)
                val ckaParser = ckaParser(tokens)
                ckaParser.errorHandler = BailErrorStrategy()
                val context = ckaParser.file()
                val pc = FileVisitor().visit(context)
                val oc = OutputContext(
                    heapSize?.toInt() ?: 1000,
                    stackSize?.toInt() ?: 100
                )
                for (inst in pc.instructions) {
                    inst.emit(pc, tc, oc)
                }
                if (outputFileName != null) {
                    File(outputFileName).printWriter().use { out ->
                        out.print(oc.emit())
                    }
                } else {
                    print(oc.emit())
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
        val formater = HelpFormatter()
        formater.printHelp("h", options)
        System.exit(0)
    }
}

