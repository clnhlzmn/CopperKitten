package com.cph;

import java.util.Collections;
import java.util.List;

//a class that represents literal values (bytes) in the instruction stream
public class LiteralByteInstruction implements Instruction {

    byte value;

    public LiteralByteInstruction(byte value) {
        this.value = value;
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
        return String.valueOf(value) + ", ";
    }
}
