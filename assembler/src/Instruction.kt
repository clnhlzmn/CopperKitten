import java.lang.Math.pow

fun checkSize(i: Int, tc: TargetContext):Boolean {
    val max = pow(2.0, (tc.wordSize.toDouble() * 8) - 1) - 1
    val min = -pow(2.0, (tc.wordSize.toDouble() * 8) - 1)
    return i >= min && i <= max
}

interface Instruction {
    fun size(tc: TargetContext): Int
    fun emit(pc: ParseContext, tc: TargetContext, oc: OutputContext)
}

data class SimpleInstruction(val name: String) : Instruction {
    override fun size(tc: TargetContext): Int {
        return 1;
    }

    override fun emit(pc: ParseContext, tc: TargetContext, oc: OutputContext) {
        oc.program.append(tc.convert(name) + ",")
    }
}

data class LiteralIntInstruction(val mnemonic: String, val data: Int) : Instruction {

    override fun size(tc: TargetContext): Int {
        return 1 + tc.wordSize
    }

    override fun emit(pc: ParseContext, tc: TargetContext, oc: OutputContext) {
        if (!checkSize(data, tc)) {
            //TODO handle this better
            throw RuntimeException("literal too large")
        }
        oc.program.append("${tc.convert(mnemonic)}, " +
            (0 until tc.wordSize)
                .map { i -> (data shr i * 8 and 0xFF).toString() }
                .reduce { acc, s -> "$acc, $s" } + ",")
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
        val adjustedTargetIndex =
            pc.instructions
                .slice(0 until targetIndex)
                .map { inst -> inst.size(tc) }
                .fold(0) { acc, i -> acc + i }
        if (!checkSize(adjustedTargetIndex, tc)) {
            //TODO handle this better
            throw RuntimeException("label index too large")
        }
        oc.program.append("${tc.convert(mnemonic)}, " +
            (0 until tc.wordSize)
                .map { i -> "((${tc.programBaseAddress} + $adjustedTargetIndex) >> ($i * 8)) & 0xFF" }
                .reduce { acc, s -> "$acc, $s" } + ", ")
    }
}

//layout <layoutptr>
data class LayoutInstruction(val layout: List<Int>) : Instruction {
    override fun size(tc: TargetContext): Int {
        return 1 + tc.wordSize
    }

    override fun emit(pc: ParseContext, tc: TargetContext, oc: OutputContext) {
        oc.frameLayouts.add(FrameLayout(layout))
        //todo: emit code
    }
}

data class AllocInstruction(val layout: AllocLayout) : Instruction {
    override fun size(tc: TargetContext): Int {
        //1 for opcode, wordSize for size, wordSize for layout
        //generated code looks like alloc <size> <layout>
        return 1 + 2 * tc.wordSize
    }

    override fun emit(pc: ParseContext, tc: TargetContext, oc: OutputContext) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}


open class AllocLayout(val size: Int) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AllocLayout

        if (size != other.size) return false

        return true
    }

    override fun hashCode(): Int {
        return size.hashCode()
    }

}

class RefArrayLayout(s: Int) : AllocLayout(s) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false
        return true
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun toString(): String {
        return "[$size, *]"
    }

}

class CustomLayout(s: Int, val layout: List<Int>) : AllocLayout(s) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as CustomLayout

        if (layout != other.layout) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + layout.hashCode()
        return result
    }

    override fun toString(): String {
        val layoutString =
            if (layout.isEmpty())
                ""
            else
                ", ${layout.map { i -> i.toString() }.reduce { acc, s -> "$acc, $s" }}"
        return "[$size$layoutString]"
    }

}


