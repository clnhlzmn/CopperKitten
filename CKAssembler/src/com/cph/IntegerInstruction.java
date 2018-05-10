package com.cph;

import java.util.List;

public class IntegerInstruction implements Instruction {

    String mnemonic;

    int arg;

    public IntegerInstruction(String mnemonic, int arg) {
        this.mnemonic = mnemonic;
        this.arg = arg;
    }

    @Override
    public List<Byte> getBytes() {
        return null;
    }

    @Override
    public int size() {
        //1 + sizeof(pointer on target system)
        //maybe as low as 2 if arg is small enough
        return 1 + 4;
    }

    @Override
    public List<Instruction> getInstructions() {
        return null;
    }
}
