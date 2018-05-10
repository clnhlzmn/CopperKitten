package com.cph;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.*;
import java.util.List;

public final class Parse {

    private Parse() {}

    private static CharStream getInputStream(String inputString) {
        if (inputString == null) {throw new NullPointerException();}
        return CharStreams.fromString(inputString);
    }

    private static CharStream getInputStream(File inputFile) {
        if (inputFile == null) { throw new NullPointerException(); }
        try {
            return CharStreams.fromStream(new FileInputStream(inputFile));
        } catch (IOException e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }

    private static ckasmParser getParser(CharStream inputStream) {
        ckasmLexer lexer = new ckasmLexer(inputStream);
//        lexer.removeErrorListeners();
//        lexer.addErrorListener(DescriptiveErrorListener.INSTANCE);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ckasmParser parser = new ckasmParser(tokens);
//        parser.removeErrorListeners();
//        parser.addErrorListener(DescriptiveErrorListener.INSTANCE);
        parser.setErrorHandler(new BailErrorStrategy());
        return parser;
    }

    public static List<PseudoInstruction> file(File inputFile) {
        ParseTree tree = getParser(getInputStream(inputFile)).file();
        return new FileVisitor().visit(tree);
    }

    public static List<PseudoInstruction> file(String inputString) {
        ParseTree tree = getParser(getInputStream(inputString)).file();
        return new FileVisitor().visit(tree);
    }

}
