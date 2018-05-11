package com.cph;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class InstructionVisitor extends ckasmBaseVisitor<PseudoInstruction> {

    private int currentIndex;
    private Map<String, Integer> labels;

    public InstructionVisitor(int currentIndex, Map<String, Integer> labels) {
        this.currentIndex = currentIndex;
        this.labels = labels;
    }

    @Override
    public PseudoInstruction visitLabel(ckasmParser.LabelContext ctx) {
        String name = ctx.LABEL().getText();
        if (labels.containsKey(name)) {
            throw new RuntimeException("duplicate label " + name);
        }
        Label ret = new Label();
        labels.put(name, currentIndex);
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
        switch (mnemonic) {
            case "jump":
                return new JumpLabelInstruction(mnemonic, arg);
            case "call":
                return new CallLabelInstruction(mnemonic, arg);
            default:
                return null;
        }
    }
}
