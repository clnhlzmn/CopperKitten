package com.cph;

import java.util.ArrayList;
import java.util.List;

public class Label implements PseudoInstruction {

    @Override
    public int size(int targetCellSize) {
        return 0;
    }

    @Override
    public void determineInstructions(ParseContext context, TargetContext targetContext, int thisIndex) {

    }

    @Override
    public void calculateJumps(ParseContext context, TargetContext targetContext, int thisIndex) {

    }

    @Override
    public List<Instruction> getInstructions(int targetCellSize) {
        return new ArrayList<>();
    }
}
