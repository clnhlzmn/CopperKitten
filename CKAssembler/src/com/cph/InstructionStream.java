package com.cph;

import java.util.ArrayList;
import java.util.List;

public class InstructionStream {

    private List<AbstractInstruction> instructions = new ArrayList<AbstractInstruction>();

    void append(AbstractInstruction inst) {
        instructions.add(inst);
    }

}
