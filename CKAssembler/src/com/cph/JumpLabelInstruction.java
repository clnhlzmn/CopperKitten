package com.cph;

import java.util.ArrayList;
import java.util.List;

//an instruction of the form
//jump(z) Label
public class JumpLabelInstruction implements PseudoInstruction {

    //one of jump or jumpz
    String mnemonic;

    //label of jump target
    String targetLabel;

    //create instruction with the given mnemonic and target label
    public JumpLabelInstruction(String mnemonic, String targetLabel) {
        this.mnemonic = mnemonic;
        this.targetLabel = targetLabel;
    }

    //returns the worst case size
    @Override
    public int size(int targetCellSize) {
        //worst case: pushw offset jump(z)
        return 2 + targetCellSize;
    }

    @Override
    public List<Instruction> getInstructions(int targetCellSize) {
        return null;
    }

}
