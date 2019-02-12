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

    //does it use offset or address
    boolean smallJump = false;
    //address or offset
    int address = 0;

    //create instruction with the given mnemonic and target label
    public JumpLabelInstruction(String mnemonic, String targetLabel) {
        this.mnemonic = mnemonic;
        this.targetLabel = targetLabel;
    }

    //returns the worst case size
    @Override
    public int size(int targetCellSize) {
        if (smallJump) {
            //best case jumpo(z) offset
            return 2;
        } else {
            //worst case: pushw address jump(z)
            return 2 + targetCellSize;
        }
    }

    @Override
    public void determineInstructions(ParseContext context, TargetContext targetContext, int thisIndex) {
        int begin = Math.min(thisIndex, context.labels.get(targetLabel));
        int end = Math.max(thisIndex, context.labels.get(targetLabel));
        address = 0;
        for (int i = begin; i < end; ++i) {
            address += context.instructions.get(i).size(targetContext.cellSize);
        }
        if (thisIndex < context.labels.get(targetLabel)) {
            //forward jump, have to subtract 1 + targetCellSize from dist
            address -= (1 + targetContext.cellSize);
        }
        smallJump = address <= 127;
    }

    @Override
    public void calculateJumps(ParseContext context, TargetContext targetContext, int thisIndex) {
        if (smallJump) {
            //have to calculate offset
            int begin = Math.min(thisIndex, context.labels.get(targetLabel));
            int end = Math.max(thisIndex, context.labels.get(targetLabel));
            address = 0;
            for (int i = begin; i < end; ++i) {
                address += context.instructions.get(i).size(targetContext.cellSize);
            }
            //add one because of when ip_ gets incremented
            address += 1;
            if (thisIndex < context.labels.get(targetLabel)) {
                //forward jump, have to subtract 2 from dist
                address -= (2);
            } else {
                address = -(address);
            }
        } else {
            //have to calculate address
            address = 0;
            for (int i = 0; i < context.labels.get(targetLabel); ++i) {
                address += context.instructions.get(i).size(targetContext.cellSize);
            }
        }
    }

    @Override
    public List<Instruction> getInstructions(int targetCellSize) {
        List<Instruction> ret = new ArrayList<>();
        if (smallJump) {
            if (mnemonic.equals("jump")) {
                ret.add(new SimpleInstruction("rjump"));
            } else if (mnemonic.equals("jumpz")) {
                ret.add(new SimpleInstruction("rjumpz"));
            }
            ret.add(new LiteralByteInstruction((byte)address));
        } else {
            ret.add(new SimpleInstruction("pushw"));
            ret.add(new LiteralIntInstruction(address));
            if (mnemonic.equals("jump")) {
                ret.add(new SimpleInstruction("ijump"));
            } else if (mnemonic.equals("jumpz")) {
                ret.add(new SimpleInstruction("ijumpz"));
            }
        }
        return ret;
    }

}
