package com.cph;

import java.util.Collections;
import java.util.List;

public class NativeAddressInstruction implements Instruction {

    //name of the native function
    String name;

    //create a literal address instruction where given name is the name of the native function
    public NativeAddressInstruction(String name) {
        this.name = name;
    }

    @Override
    public String emit(TargetContext targetContext) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < targetContext.cellSize; ++i) {
            sb.append("(int8_t)((intptr_t)(" + name + ") >> " + String.valueOf(i * 8) + "), ");
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
