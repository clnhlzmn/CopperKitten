import java.lang.Math.pow

fun checkSize(i: Long, tc: TargetContext): Boolean {
    val max = pow(2.0, (tc.wordSize.toDouble() * 8) - 1) - 1
    val min = -pow(2.0, (tc.wordSize.toDouble() * 8) - 1)
    return i >= min && i <= max
}

interface Instruction {
    fun size(tc: TargetContext): Int
    fun emit(pc: ParseContext, tc: TargetContext, oc: OutputContext)
}

data class SimpleInstruction(val mnemonic: String) : Instruction {
    override fun size(tc: TargetContext): Int {
        return 1;
    }

    override fun emit(pc: ParseContext, tc: TargetContext, oc: OutputContext) {
        oc.program.add(tc.convert(mnemonic))
    }
}

data class LiteralIntInstruction(val mnemonic: String, val data: Long) : Instruction {

    override fun size(tc: TargetContext): Int {
        return 1 + tc.wordSize
    }

    override fun emit(pc: ParseContext, tc: TargetContext, oc: OutputContext) {
        if (!checkSize(data, tc)) {
            //TODO handle this better
            throw RuntimeException("literal too large")
        }
        oc.program.add(tc.convert(mnemonic))
        oc.program.addAll(
            (0 until tc.wordSize)
                .map { i -> (data shr i * 8 and 0xFF).toString() }
        )
    }
}

//to be used for push Label and jumpxx Label
data class LiteralLabelInstruction(val mnemonic: String, val label: String) : Instruction {
    override fun size(tc: TargetContext): Int {
        return 1 + tc.wordSize
    }

    override fun emit(pc: ParseContext, tc: TargetContext, oc: OutputContext) {
        val targetIndex = pc.labels[label]!!
        //add the sizes of all the instructions from 0 until targetIndex
        val actualTargetIndex =
            pc.instructions
                .slice(0 until targetIndex)
                .map { inst -> inst.size(tc) }
                .fold(0) { acc, i -> acc + i }
                .toLong()
        if (!checkSize(actualTargetIndex, tc)) {
            //TODO handle this better
            throw RuntimeException("label index too large")
        }
        oc.program.add(tc.convert(mnemonic))
        oc.program.addAll(
            (0 until tc.wordSize)
                .map { i -> (actualTargetIndex shr i * 8 and 0xFF).toString() }
        )
    }
}

//layout <layoutptr>
//or
//alloc <layoutptr>
data class LayoutInstruction(val mnemonic: String, val layout: Function) : Instruction {
    override fun size(tc: TargetContext): Int {
        return 1 + tc.wordSize
    }

    override fun emit(pc: ParseContext, tc: TargetContext, oc: OutputContext) {
        val layoutIndex = oc.addFunction(layout)
        oc.program.add(tc.convert(mnemonic))
        oc.program.addAll(
            (0 until tc.wordSize)
                .map { i -> (layoutIndex shr i * 8 and 0xFF).toString() }
        )
    }
}

data class NCallInstruction(val id: String) : Instruction {
    override fun size(tc: TargetContext): Int =
        1 + tc.wordSize

    override fun emit(pc: ParseContext, tc: TargetContext, oc: OutputContext) {
        val functionIndex = oc.addFunction(NativeFunction(id))
        oc.program.add(tc.convert("ncall"))
        oc.program.addAll(
            (0 until tc.wordSize)
                .map { i -> (functionIndex shr i * 8 and 0xFF).toString() }
        )
    }

}

