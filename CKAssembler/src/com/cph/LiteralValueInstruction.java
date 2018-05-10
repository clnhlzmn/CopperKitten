package com.cph;

import java.util.Collections;
import java.util.List;

//a class that represents literal values (bytes) in the instruction stream
public class LiteralValueInstruction implements Instruction {

    byte value;

    public LiteralValueInstruction(byte value) {
        this.value = value;
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
        return Collections.singletonList(this.value);
    }
}
