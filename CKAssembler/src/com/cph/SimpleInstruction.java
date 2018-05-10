package com.cph;

import java.util.Arrays;
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
    public List<Instruction> getInstructions(int targetCellSize) {
        return Collections.singletonList(this);
    }

    @Override
    public List<Byte> getBytes(int targetCellSize) {
        return Collections.singletonList((byte)OpCode.getOpCode(mnemonic).VALUE);
    }
}
