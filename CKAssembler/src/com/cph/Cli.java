package com.cph;

import org.apache.commons.cli.*;

import java.io.File;
import java.util.List;

//borrowed and adapted from http://www.thinkplexx.com/blog/simple-apache-commons-cli-example-java-command-line-arguments-parsing
public class Cli {

//    private static final Logger log = Logger.getLogger(Cli.class.getName());

    private String[] args = null;

    private Options options = new Options();

    public Cli(String[] args) {
        this.args = args;
        options.addOption("h", "help", false, "show help");
//        options.addOption("c", "check", true, "check file");
//        options.addOption("i", "interpret", true, "interpret file");
    }

    public void parse() {

        CommandLineParser parser = new DefaultParser();

        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);

            if (cmd.hasOption("h")) {
                help();
            }

//            if (cmd.hasOption("c")) {
//                //System.out.println("option c=" + cmd.getOptionValue("c"));
//                Check.check(HContext.fromFileAugmentWithDefaults(cmd.getOptionValue("c")));
//            } else if (cmd.hasOption("i")) {
//                //System.out.println("option i=" + cmd.getOptionValue("i"));
//                HContext ctx = HContext.fromFileAugmentWithDefaults(cmd.getOptionValue("i"));
//                if (Check.check(ctx)) {
//                    Interpret.interpret(ctx);
//                }
//            } else {
//                help();
//            }

            if (cmd.getArgs().length == 1) {
                List<PseudoInstruction> file = Parse.file(new File(cmd.getArgs()[0]));
                for (PseudoInstruction pi : file) {
                    System.out.println(pi);
                }
            } else {
                help();
            }

        } catch (ParseException e) {
            help();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    private void help() {
        // This prints out some help
        HelpFormatter formater = new HelpFormatter();
        formater.printHelp("h", options);
        System.exit(0);
    }
}
