package com.cph;

import java.util.ArrayList;
import java.util.List;

public class ConcreteInstruction implements AbstractInstruction {

    @Override
    public List<ConcreteInstruction> getConcreteInstructions() {
        return new ArrayList<>();
    }
}
