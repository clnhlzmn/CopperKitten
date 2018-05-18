package com.cph;

import java.util.ArrayList;
import java.util.List;

//an instruction of the form
//jump(z) Label
public class NativeCallInstruction implements PseudoInstruction {

    //one of jump or jumpz
    String mnemonic;

    //label of jump target
    String name;

    //create instruction with the given mnemonic and target label
    public NativeCallInstruction(String mnemonic, String name) {
        this.mnemonic = mnemonic;
        this.name = name;
    }

    //returns the worst case size
    @Override
    public int size(int targetCellSize) {
        //pushw address ncall
        return 2 + targetCellSize;
    }

    @Override
    public void determineInstructions(ParseContext context, TargetContext targetContext, int thisIndex) {}

    @Override
    public void calculateJumps(ParseContext context, TargetContext targetContext, int thisIndex) {}

    @Override
    public List<Instruction> getInstructions(int targetCellSize) {
        List<Instruction> ret = new ArrayList<>();
        ret.add(new SimpleInstruction("pushw"));
        ret.add(new NativeAddressInstruction(name));
        ret.add(new SimpleInstruction("call"));
        return ret;
    }

}
