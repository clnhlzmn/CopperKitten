package com.cph;

import java.util.ArrayList;
import java.util.List;

public interface Instruction extends PseudoInstruction {
    List<Byte> getBytes();
}
