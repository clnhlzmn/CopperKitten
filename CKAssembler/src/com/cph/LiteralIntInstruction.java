package com.cph;

import java.util.Collections;
import java.util.List;

public class LiteralIntInstruction implements Instruction {

    int value;

    public LiteralIntInstruction(int value) {
        this.value = value;
    }

    @Override
    public String emit(TargetContext targetContext) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < targetContext.cellSize; ++i) {
            sb.append("(int8_t)(" + String.valueOf(value) + " >> " + String.valueOf(i * 8) + "), ");
        }
        return sb.toString();
    }

    @Override
    public int size(int targetCellSize) {
        return targetCellSize;
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
}
