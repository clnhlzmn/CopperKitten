package com.cph;

import java.util.ArrayList;
import java.util.List;

public interface Instruction extends PseudoInstruction {

    //get the actual bytes for this instruction
    //may need to pass more info
    List<Byte> getBytes();

}
