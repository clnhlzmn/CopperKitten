package com.cph;

import java.util.ArrayList;
import java.util.List;

//
public class PushInstruction implements PseudoInstruction {

    int arg;

    public PushInstruction(int arg) {
        this.arg = arg;
    }

    @Override
    public int size(int targetCellSize) {
        if (arg < 128 && arg >= -128) {
            return 2;
        } else {
            return 1 + targetCellSize;
        }
    }

    @Override
    public List<Instruction> getInstructions(int targetCellSize) {
        List<Instruction> ret = new ArrayList<>();
        if (arg < 128 && arg >= -128) {
            ret.add(new SimpleInstruction("push"));
            ret.add(new LiteralValueInstruction((byte)arg));
        } else {
            ret.add(new SimpleInstruction("pushw"));
            for (int i = 0; i < targetCellSize; ++i) {
                ret.add(new LiteralValueInstruction((byte)((this.arg >> i * 8) & 0xFF)));
            }
        }
        return ret;
    }

}
