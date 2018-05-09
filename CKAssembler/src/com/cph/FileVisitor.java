package com.cph;

import java.util.ArrayList;
import java.util.List;

public class FileVisitor extends ckasmBaseVisitor<List<PseudoInstruction>> {

    private Context context = new Context();

    @Override
    public List<PseudoInstruction> visitFile(ckasmParser.FileContext ctx) {
        List<PseudoInstruction> stream = new ArrayList<>();
        for (int i = 0; i < ctx.instruction().size(); ++i) {
            stream.add(new InstructionVisitor(context).visit(ctx.instruction(i)));
        }
        return stream;
    }

}
