package com.cph;

public class InstructionVisitor extends ckasmBaseVisitor<PseudoInstruction> {

    private Context context;

    public InstructionVisitor(Context context) {
        this.context = context;
    }

    @Override
    public PseudoInstruction visitLabel(ckasmParser.LabelContext ctx) {
        String name = ctx.LABEL().getText();
        if (context.labels.containsKey(name)) {
            throw new RuntimeException("duplicate label " + name);
        }
        Label ret = new Label();
        context.labels.put(name, context.currentIndex);
        return ret;
    }

    @Override
    public PseudoInstruction visitMnemonic(ckasmParser.MnemonicContext ctx) {
        return super.visitMnemonic(ctx);
    }

    @Override
    public PseudoInstruction visitMnemonicInteger(ckasmParser.MnemonicIntegerContext ctx) {
        return super.visitMnemonicInteger(ctx);
    }

    @Override
    public PseudoInstruction visitMnemonicLabel(ckasmParser.MnemonicLabelContext ctx) {
        return super.visitMnemonicLabel(ctx);
    }
}
