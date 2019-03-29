import java.lang.Math.pow

fun checkSize(i: Long, tc: OutputContext): Boolean {
    val max = pow(2.0, (tc.wordSize.toDouble() * 8) - 1) - 1
    val min = -pow(2.0, (tc.wordSize.toDouble() * 8) - 1)
    return i >= min && i <= max
}

fun checkSizeByte(i: Long): Boolean {
    val max = pow(2.0, 7.0) - 1
    val min = -pow(2.0, 7.0)
    return i >= min && i <= max
}

interface Instruction {
    fun size(tc: OutputContext): Int
    fun emit(pc: ParseContext, oc: OutputContext)
}

data class SimpleInstruction(val mnemonic: String) : Instruction {
    override fun size(tc: OutputContext): Int {
        return 1;
    }

    override fun emit(pc: ParseContext, oc: OutputContext) {
        oc.program.add(oc.convert(mnemonic))
    }
}

data class LiteralIntInstruction(val mnemonic: String, val data: Long) : Instruction {

    override fun size(tc: OutputContext): Int {
        if (checkSizeByte(data)) {
            return 2
        } else {
            return 1 + tc.wordSize
        }
    }

    override fun emit(pc: ParseContext, oc: OutputContext) {
        if (!checkSize(data, oc)) {
            //TODO handle this better
            throw RuntimeException("literal too large")
        }
        if (checkSizeByte(data)) {
            oc.program.add(oc.convert("${mnemonic}b"))
            //I think the following works
            oc.program.add(data.toString())
        } else {
            oc.program.add(oc.convert(mnemonic))
            oc.program.addAll(
                (0 until oc.wordSize)
                    .map { i -> (data shr i * 8 and 0xFF).toString() }
            )
        }
    }
}

data class LiteralStringInstruction(val mnemonic: String, val data: String) : Instruction {

    override fun size(tc: OutputContext): Int {
        return 1 + tc.wordSize
    }

    override fun emit(pc: ParseContext, oc: OutputContext) {
        val stringIndex = oc.addString(data)
        if (!checkSize(stringIndex, oc)) {
            //TODO handle this better
            throw RuntimeException("literal too large")
        }
        oc.program.add(oc.convert(mnemonic))
        oc.program.addAll(
            (0 until oc.wordSize)
                .map { i -> (stringIndex shr i * 8 and 0xFF).toString() }
        )
    }
}

//to be used for push Label and jumpxx Label
data class LiteralLabelInstruction(val mnemonic: String, val label: String) : Instruction {
    override fun size(tc: OutputContext): Int {
        return 1 + tc.wordSize
    }

    override fun emit(pc: ParseContext, oc: OutputContext) {
        val targetIndex = pc.labels[label]!!
        //add the sizes of all the instructions from 0 until targetIndex
        val actualTargetIndex =
            pc.instructions
                .slice(0 until targetIndex)
                .map { inst -> inst.size(oc) }
                .fold(0) { acc, i -> acc + i }
                .toLong()
        if (!checkSize(actualTargetIndex, oc)) {
            //TODO handle this better
            throw RuntimeException("label index too large")
        }
        oc.program.add(oc.convert(mnemonic))
        oc.program.addAll(
            (0 until oc.wordSize)
                .map { i -> (actualTargetIndex shr i * 8 and 0xFF).toString() }
        )
    }
}

//layout <layoutptr>
//or
//alloc <layoutptr>
data class LayoutInstruction(val mnemonic: String, val layout: Function) : Instruction {

    override fun size(tc: OutputContext): Int {
        val layoutIndex = tc.addFunction(layout)
        return if (checkSizeByte(layoutIndex))
            2
        else
            1 + tc.wordSize
    }

    override fun emit(pc: ParseContext, oc: OutputContext) {
        val layoutIndex = oc.addFunction(layout)
        if (checkSizeByte(layoutIndex)) {
            oc.program.add(oc.convert(mnemonic + "b"))
            oc.program.add(layoutIndex.toString())
        } else {
            oc.program.add(oc.convert(mnemonic))
            oc.program.addAll(
                (0 until oc.wordSize)
                    .map { i -> (layoutIndex shr i * 8 and 0xFF).toString() }
            )
        }
    }
}

data class NCallInstruction(val id: String) : Instruction {
    override fun size(tc: OutputContext): Int =
        1 + tc.wordSize

    override fun emit(pc: ParseContext, oc: OutputContext) {
        val functionIndex = oc.addFunction(NativeFunction(id))
        oc.program.add(oc.convert("ncall"))
        oc.program.addAll(
            (0 until oc.wordSize)
                .map { i -> (functionIndex shr i * 8 and 0xFF).toString() }
        )
    }

}

