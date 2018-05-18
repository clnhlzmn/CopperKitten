package com.cph;

import java.util.Collections;
import java.util.List;

public class ProgramAddressInstruction implements Instruction {

    int address;

    //create a literal address instruction where given address is index into program stream
    public ProgramAddressInstruction(int address) {
        this.address = address;
    }

    @Override
    public String emit(TargetContext targetContext) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < targetContext.cellSize; ++i) {
            sb.append("(int8_t)((intptr_t)(" + targetContext.baseProgramAddress + "+" + String.valueOf(address) + ") >> " + String.valueOf(i * 8) + "), ");
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
