import java.lang.Math.pow

interface Instruction {
    fun size(tc: TargetContext): Int
    fun emit(
        pc: ParseContext,
        tc: TargetContext
    ): String
}

data class SimpleInstruction(val name: String) : Instruction {
    override fun size(tc: TargetContext): Int {
        return 1;
    }

    override fun emit(pc: ParseContext, tc: TargetContext): String {
        return tc.convert(name)
    }
}

data class PushIntInstruction(val data: Int) : Instruction {

    override fun size(tc: TargetContext): Int {
        return 1 + tc.wordSize
    }

    override fun emit(
        pc: ParseContext,
        tc: TargetContext
    ): String {
        val max = pow(2.0, (tc.wordSize.toDouble()*8)-1)-1
        val min = -pow(2.0, (tc.wordSize.toDouble()*8)-1)
        if (data < min || data > max) {
            //TODO handle this better
            throw RuntimeException("literal too large")
        }
        return "${tc.convert("push")}, " +
                (0 until tc.wordSize)
                    .map { i -> (data shr i * 8 and 0xFF).toString() }
                    .reduce { acc, s -> "$acc, $s" }
    }
}

data class PushLabelInstruction(val label: String) : Instruction {
    override fun size(tc: TargetContext): Int {
        return 1 + tc.wordSize
    }

    override fun emit(pc: ParseContext, tc: TargetContext): String {
        //TODO: adjust label index according to size of instructions from 0 to index
        //TODO: make sure that label fits in target word size
        val targetIndex = pc.labels[label]
        return "${tc.convert("push")}, " +
            (0 until tc.wordSize)
                .map { i -> "((${tc.programBaseAddress} + $targetIndex) >> ($i * 8)) & 0xFF" }
                .reduce { acc, s -> "$acc, $s" }
    }
}

data class JumpInstruction(val type: String, val target: String) : Instruction {
    override fun size(tc: TargetContext): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun emit(pc: ParseContext, tc: TargetContext): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

data class LayoutInstruction(val layout: List<Int>) : Instruction {
    override fun size(tc: TargetContext): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun emit(pc: ParseContext, tc: TargetContext): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

data class AllocInstruction(val layout: AllocLayout) : Instruction {
    override fun size(tc: TargetContext): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun emit(pc: ParseContext, tc: TargetContext): String {
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


