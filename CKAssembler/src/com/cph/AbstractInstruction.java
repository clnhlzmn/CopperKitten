package com.cph;

import java.util.List;

public interface AbstractInstruction {
    //return the list of concrete instructions that make up this instruction
    List<ConcreteInstruction> getConcreteInstructions();
}
