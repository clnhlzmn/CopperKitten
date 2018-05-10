package com.cph;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
    public PseudoInstruction visitMnemonicInstruction(ckasmParser.MnemonicInstructionContext ctx) {
        List<String> mnemonicInstructions = Arrays.asList("add", "sub", "mul", "div", "cmp", "call", "return",
                                                          "jump", "jumpz", "dup", "pop", "swap", "halt", "pushref",
                                                          "popref", "enter", "leave", "in", "out", "alloc", "refget",
                                                          "refset", "nop");
        String mnemonic = ctx.MNEMONIC().getText();
        if (!mnemonicInstructions.contains(mnemonic)) {
            throw new RuntimeException("unknown instruction " + mnemonic);
        }
        return new SimpleInstruction(mnemonic);
    }

    @Override
    public PseudoInstruction visitIntArgInstruction(ckasmParser.IntArgInstructionContext ctx) {
        List<String> pushInstructions = Collections.singletonList("push");
        String mnemonic = ctx.MNEMONIC().getText();
        String arg = ctx.INTEGER().getText();
        if (!pushInstructions.contains(mnemonic)) {
            throw new RuntimeException("unknown instruction " + mnemonic + " " + arg);
        }
        return new PushIntInstruction(Integer.valueOf(arg));
    }

    @Override
    public PseudoInstruction visitLabelArgInstruction(ckasmParser.LabelArgInstructionContext ctx) {
        List<String> pushInstructions = Arrays.asList("jump", "call");
        String mnemonic = ctx.MNEMONIC().getText();
        String arg = ctx.LABEL().getText();
        if (!pushInstructions.contains(mnemonic)) {
            throw new RuntimeException("unknown instruction " + mnemonic + " " + arg);
        }
        if (mnemonic.equals("jump")) {
            return new JumpLabelInstruction(mnemonic, arg);
        } else if (mnemonic.equals("call")) {
            return new CallLabelInstruction(mnemonic, arg);
        } else {
            return null;
        }
    }
}
