package com.cph;

import java.util.List;

//this interface represents an instruction (in ckasm)
//that expands to zero or more actual instructions
public interface PseudoInstruction {

    //get the maximum size of this instruction
    //the maximum number of bytes in the
    //instruction stream that this (pseudo)instruction represents
    int size(int targetCellSize);

    //call for the pseudo instruction to determine which instructions it will emit
    void determineInstructions(ParseContext context, TargetContext targetContext, int thisIndex);

    //call for the pseudo instruction to calculate jump offsets
    void calculateJumps(ParseContext context, TargetContext targetContext, int thisIndex);

    //get the list of actual instructions that this pseudo instruction represents
    List<Instruction> getInstructions(int targetCellSize);

}
