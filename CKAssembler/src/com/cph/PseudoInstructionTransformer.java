package com.cph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PseudoInstructionTransformer {

    int targetWordSize;

    PseudoInstructionTransformer(int targetWordSize, Map<String, Integer> labels) {
        this.targetWordSize = targetWordSize;
    }

    public List<Instruction> transform(List<PseudoInstruction> in) {
        List<Instruction> ret = new ArrayList<>();
        return ret;
    }


}
