package com.cph;

public class Main {

    public static void main(String[] args) {

        args = new String[] { "C:\\code\\CopperKitten\\ck\\example2.cka" };
//        args = new String[] { "/Users/colinholzman/Documents/CopperKitten/ck/example2.cka" };

        new Cli(args).parse();
    }
}
