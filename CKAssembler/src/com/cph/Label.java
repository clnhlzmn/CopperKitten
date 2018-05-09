package com.cph;

import java.util.List;

public class Label implements PseudoInstruction {

    @Override
    public int size() {
        return 0;
    }

    @Override
    public List<Instruction> getInstructions() {
        return null;
    }
}
