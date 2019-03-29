import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.apache.commons.cli.*;
import util.antlr.ThrowingErrorListener
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
        options.addOption("g", "garbage_collector", true, "set garbage collector implementation (copying, incremental, mark_compact)")
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
                var gcOpt = cmd.getOptionValue("g")

                val gcOpts = listOf("copying", "incremental", "mark_compact")

                if (gcOpt != null) {
                    if (!gcOpts.contains(gcOpt.toLowerCase())) {
                        println("unknown option $gcOpt")
                        help()
                        return
                    }
                } else {
                    gcOpt = "copying"
                }

                val gcImpls = gcOpts.map { o -> Pair(o, "${o}_gc.h") }.toMap()
                val gcImpl = gcImpls.getValue(gcOpt.toLowerCase())

                val oc = OutputContext(
                    { mnemonic -> mnemonic.toUpperCase() },
                    wordSize?.toInt() ?: 4,
                    heapSize?.toInt() ?: 1000,
                    stackSize?.toInt() ?: 100,
                    gcImpl,
                    ArrayList()
                )

                val stream = CharStreams.fromFileName(inputFileName)
                val lexer = ckaLexer(stream)
                val tokens = CommonTokenStream(lexer)
                val ckaParser = ckaParser(tokens)
                ckaParser.removeErrorListeners()
                ckaParser.addErrorListener(ThrowingErrorListener())
                val context = ckaParser.file()
                val pc = FileVisitor().visit(context)
                for (inst in pc.instructions) {
                    inst.emit(pc, oc)
                }
                if (outputFileName != null) {
                    File(outputFileName).printWriter().use { out ->
                        out.print(oc.emit())
                    }
                } else {
                    println(oc.emit())
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

