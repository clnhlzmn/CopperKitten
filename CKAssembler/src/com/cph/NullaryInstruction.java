package com.cph;

import java.util.ArrayList;
import java.util.List;

public class NullaryInstruction implements Instruction {

    private String mnemonic;

    public NullaryInstruction(String mnemonic) {
        this.mnemonic = mnemonic;
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public List<Instruction> getInstructions() {
        List<Instruction> ret = new ArrayList<>();
        ret.add(this);
        return ret;
    }

    @Override
    public List<Byte> getBytes() {
        //TODO: convert to opcodes
        return null;
    }
}
