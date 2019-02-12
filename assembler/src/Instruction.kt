import sun.java2d.pipe.SpanShapeRenderer
import java.util.*

open class Instruction

data class SimpleInstruction(val name: String) : Instruction()

data class PushInstruction(val data: Int) : Instruction()

data class JumpInstruction(val type: String, val target: String) : Instruction()

data class LayoutInstruction(val layout: Array<Int>) : Instruction() {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LayoutInstruction

        if (!Arrays.equals(layout, other.layout)) return false

        return true
    }

    override fun hashCode(): Int {
        return Arrays.hashCode(layout)
    }

}

//TODO: better representation of frame layout

//TODO: alloc instruction
