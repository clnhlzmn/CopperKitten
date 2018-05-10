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
    public List<Byte> getBytes() {
        return Collections.singletonList(((byte) OpCode.getOpCode(mnemonic).VALUE));
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public List<Instruction> getInstructions() {
        return Collections.singletonList(this);
    }

}
