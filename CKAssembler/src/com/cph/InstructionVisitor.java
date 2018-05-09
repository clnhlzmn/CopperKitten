package com.cph;

public class InstructionVisitor extends ckasmBaseVisitor<AbstractInstruction> {
    private Context context;
    public InstructionVisitor(Context context) {
        this.context = context;
    }
    @Override
    public AbstractInstruction visitLabel(ckasmParser.LabelContext ctx) {
        String name = ctx.LABEL().getText();
        if (context.labels.containsKey(name)) {
            throw new RuntimeException("duplicate label " + name);
        }
        AbstractInstruction inst = new Label();
        context.labels.put(name, inst);
        return inst;
    }
}
