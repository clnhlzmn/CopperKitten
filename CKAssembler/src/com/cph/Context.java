package com.cph;

import java.util.HashMap;
import java.util.Map;

public class Context {

    //current index of pseudo instruction
    public int currentIndex = 0;

    public Map<String, Integer> labels = new HashMap<String, Integer>();

}
