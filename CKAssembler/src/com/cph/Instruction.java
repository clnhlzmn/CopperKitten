package com.cph;

//represents a concrete instruction
public interface Instruction extends PseudoInstruction {

    //emit the instructions in a form determined in part by TargetContext
    String emit(TargetContext targetContext);

}
