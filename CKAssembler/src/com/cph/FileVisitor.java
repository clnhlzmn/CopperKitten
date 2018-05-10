package com.cph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileVisitor extends ckasmBaseVisitor<ParseContext> {

    @Override
    public ParseContext visitFile(ckasmParser.FileContext ctx) {
        List<PseudoInstruction> instructions = new ArrayList<>();
        Map<String, Integer> labels = new HashMap<>();
        for (int i = 0; i < ctx.instruction().size(); ++i) {
            instructions.add(new InstructionVisitor(i, labels).visit(ctx.instruction(i)));
        }
        return new ParseContext(labels, instructions);
    }

}
