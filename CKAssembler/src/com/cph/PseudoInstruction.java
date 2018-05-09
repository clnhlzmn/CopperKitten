package com.cph;

import java.util.List;

//this interface represents an instruction (in ckasm)
//that represents zero or more actual instructions
public interface PseudoInstruction {
    //get the maximum size of this instruction
    //the maximum number of bytes in the
    //instruction stream that this (pseudo)instruction represents
    int size();
    //return the list of concrete instructions that make up this instruction
    List<Instruction> getInstructions();
}
