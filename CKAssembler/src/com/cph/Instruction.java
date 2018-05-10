package com.cph;

import java.util.List;

//represents a concrete instruction
public interface Instruction extends PseudoInstruction {

    List<Byte> getBytes(int targetCellSize);

}
