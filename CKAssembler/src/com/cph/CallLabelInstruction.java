package com.cph;

import java.util.ArrayList;
import java.util.List;

//an instruction of the form
//jump(z) Label
public class CallLabelInstruction implements PseudoInstruction {

    //one of jump or jumpz
    String mnemonic;

    //label of jump target
    String targetLabel;

    //address to call
    int address;

    //create instruction with the given mnemonic and target label
    public CallLabelInstruction(String mnemonic, String targetLabel) {
        this.mnemonic = mnemonic;
        this.targetLabel = targetLabel;
    }

    //returns the worst case size
    @Override
    public int size(int targetCellSize) {
        //worst case: pushw address call
        return 2 + targetCellSize;
    }

    @Override
    public void determineInstructions(ParseContext context, TargetContext targetContext, int thisIndex) {

    }

    @Override
    public void calculateJumps(ParseContext context, TargetContext targetContext, int thisIndex) {
        int dist = 0;
        for (int i = 0; i < context.labels.get(targetLabel); ++i) {
            dist += context.instructions.get(i).size(targetContext.cellSize);
        }
        //here dist is the distance from the beginning of the program to the target
        //it has to be added to targetContext.baseProgramAddress
        address = dist;
    }

    @Override
    public List<Instruction> getInstructions(int targetCellSize) {
        List<Instruction> ret = new ArrayList<>();
        ret.add(new SimpleInstruction("pushw"));
        ret.add(new ProgramAddressInstruction(address));
        ret.add(new SimpleInstruction("call"));
        return ret;
    }

}
