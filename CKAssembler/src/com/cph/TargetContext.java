package com.cph;

import java.util.function.Function;

public class TargetContext {

    int cellSize;

    String baseProgramAddress;

    Function<String, String> mnemonicConverter;

    public TargetContext(int cellSize, String baseProgramAddress, Function<String, String> mnemonicConverter) {
        this.cellSize = cellSize;
        this.baseProgramAddress = baseProgramAddress;
        this.mnemonicConverter = mnemonicConverter;
    }

}
