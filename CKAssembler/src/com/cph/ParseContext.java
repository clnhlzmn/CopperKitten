package com.cph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParseContext {

    public ParseContext(Map<String, Integer> labels, List<PseudoInstruction> instructions) {
        this.labels = labels;
        this.instructions = instructions;
    }

    public Map<String, Integer> labels = new HashMap<String, Integer>();

    public List<PseudoInstruction> instructions = new ArrayList<>();

}
