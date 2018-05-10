package com.cph;

import java.util.HashMap;
import java.util.Map;

public final class OpCode {

    private OpCode(int value) {
        VALUE = value;
    }

    public final int VALUE;

    //must match runtime/vm/VM::OpCode
    public static final OpCode ADD = new OpCode(0);
    public static final OpCode SUB = new OpCode(1);
    public static final OpCode MUL = new OpCode(2);
    public static final OpCode DIV = new OpCode(3);
    public static final OpCode MOD = new OpCode(4);
    public static final OpCode CMP = new OpCode(5);
    public static final OpCode CALL = new OpCode(6);
    public static final OpCode RETURN = new OpCode(7);
    public static final OpCode JUMP = new OpCode(8);
    public static final OpCode JUMPZ = new OpCode(9);
    public static final OpCode JUMPO = new OpCode(10);
    public static final OpCode JUMPOZ = new OpCode(11);
    public static final OpCode PUSH = new OpCode(12);
    public static final OpCode PUSHW = new OpCode(13);
    public static final OpCode DUP = new OpCode(14);
    public static final OpCode POP = new OpCode(15);
    public static final OpCode SWAP = new OpCode(16);
    public static final OpCode HALT = new OpCode(17);
    public static final OpCode PUSHREF = new OpCode(18);
    public static final OpCode POPREF = new OpCode(19);
    public static final OpCode ENTER = new OpCode(20);
    public static final OpCode LEAVE = new OpCode(21);
    public static final OpCode IN = new OpCode(22);
    public static final OpCode OUT = new OpCode(23);
    public static final OpCode ALLOC = new OpCode(24);
    public static final OpCode REFGET = new OpCode(25);
    public static final OpCode REFSET = new OpCode(26);
    public static final OpCode NOP = new OpCode(27);

    public static final Map<String, OpCode> nameToOpcode = new HashMap<String, OpCode>() {{
        put("add", new OpCode(0));
        put("sub", new OpCode(1));
        put("mul", new OpCode(2));
        put("div", new OpCode(3));
        put("mod", new OpCode(4));
        put("cmp", new OpCode(5));
        put("call", new OpCode(6));
        put("return", new OpCode(7));
        put("jump", new OpCode(8));
        put("jumpz", new OpCode(9));
        put("jumpz", new OpCode(10));
        put("jumpoz", new OpCode(11));
        put("push", new OpCode(12));
        put("pushw", new OpCode(13));
        put("dup", new OpCode(14));
        put("pop", new OpCode(15));
        put("swap", new OpCode(16));
        put("halt", new OpCode(17));
        put("pushref", new OpCode(18));
        put("popref", new OpCode(19));
        put("enter", new OpCode(20));
        put("leave", new OpCode(21));
        put("in", new OpCode(22));
        put("out", new OpCode(23));
        put("alloc", new OpCode(24));
        put("refget", new OpCode(25));
        put("refset", new OpCode(26));
        put("nop", new OpCode(27));
    }};

    static public OpCode getOpCode(String mnemonic) {
        return nameToOpcode.get(mnemonic);
    }

}
