import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.apache.commons.cli.*;
import org.antlr.v4.runtime.BailErrorStrategy

class Cli(val args: Array<String>) {

    private val options = Options()

    fun Cli() {
        options.addOption("h", "help", false, "show help")
        //        options.addOption("c", "check", true, "check file");
        //        options.addOption("i", "interpret", true, "interpret file");
    }

    fun parse() {

        val parser = DefaultParser()

        var cmd: CommandLine?
        try {
            cmd = parser.parse(options, args)

            if (cmd!!.hasOption("h")) {
                help()
            }

            //create based on options passed
            val tc = TargetContext({ mnemonic -> "(enum vm_op_code)${mnemonic.toUpperCase()}" }, 4)

            //TODO: input and output file from options
            if (cmd.getArgs().size == 1) {
                val stream = CharStreams.fromFileName(cmd.args[0])
                val lexer = ckaLexer(stream)
                val tokens = CommonTokenStream(lexer)
                val ckaParser = ckaParser(tokens)
                ckaParser.errorHandler = BailErrorStrategy()
                val context = ckaParser.file()
                val pc = FileVisitor().visit(context)
                //TODO: memSize and stackSize from options
                val oc = OutputContext(1000, 100)
                for (inst in pc.instructions) {
                    inst.emit(pc, tc, oc)
                }
                println(oc.emit())
            } else {
                help()
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

