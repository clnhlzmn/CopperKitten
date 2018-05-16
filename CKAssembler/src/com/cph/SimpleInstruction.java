package com.cph;

import java.util.Collections;
import java.util.List;

public class SimpleInstruction implements Instruction {

    String mnemonic;

    public SimpleInstruction(String mnemonic) {
        this.mnemonic = mnemonic;
    }

    @Override
    public int size(int targetCellSize) {
        return 1;
    }

    @Override
    public void determineInstructions(ParseContext context, TargetContext targetContext, int thisIndex) {

    }

    @Override
    public void calculateJumps(ParseContext context, TargetContext targetContext, int thisIndex) {

    }

    @Override
    public List<Instruction> getInstructions(int targetCellSize) {
        return Collections.singletonList(this);
    }

    @Override
    public String emit(TargetContext targetContext) {
        //convert simple jump mnemonics to ijump
        if (mnemonic.equals("jump")) {
            mnemonic = "ijump";
        } else if (mnemonic.equals("jumpz")) {
            mnemonic = "ijumpz";
        }
        return targetContext.mnemonicConverter.apply(mnemonic) + ", ";
    }
}
