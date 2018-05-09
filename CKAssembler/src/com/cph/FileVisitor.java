package com.cph;

public class FileVisitor extends ckasmBaseVisitor<InstructionStream> {

    private Context context = new Context();

    @Override
    public InstructionStream visitFile(ckasmParser.FileContext ctx) {
        InstructionStream stream = new InstructionStream();
        for (int i = 0; i < ctx.instruction().size(); ++i) {
            stream.append(new InstructionVisitor(context).visit(ctx.instruction(i)));
        }
        return stream;
    }

}
