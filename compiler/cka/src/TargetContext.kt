//represents information about the target code to be generated
data class TargetContext(
    //to convert mnemonics from cka grammar to opcodes to store in bytecode array
    val convert: (String) -> String,
    //sizeof(intptr_t) on target
    val wordSize: Int
)
