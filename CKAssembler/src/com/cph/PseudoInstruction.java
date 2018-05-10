package com.cph;

import java.util.List;

//this interface represents an instruction (in ckasm)
//that expands to zero or more actual instructions
public interface PseudoInstruction {

    //get the maximum size of this instruction
    //the maximum number of bytes in the
    //instruction stream that this (pseudo)instruction represents
    int size();

    //return the list of concrete instructions that make up this instruction
    //may need to pass more info (like Context)
    List<Instruction> getInstructions();

}
