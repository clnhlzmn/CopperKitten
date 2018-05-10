package com.cph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Label implements PseudoInstruction {

    @Override
    public int size(int targetCellSize) {
        return 0;
    }

    @Override
    public List<Instruction> getInstructions(int targetCellSize) {
        return new ArrayList<>();
    }
}
