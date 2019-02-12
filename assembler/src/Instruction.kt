import sun.java2d.pipe.SpanShapeRenderer
import java.util.*

open class Instruction

data class SimpleInstruction(val name: String) : Instruction()

data class PushInstruction(val data: Int) : Instruction()

data class JumpInstruction(val type: String, val target: String) : Instruction()

data class LayoutInstruction(val layout: List<Int>) : Instruction()

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
        val layoutString = if (layout.isEmpty()) "" else ", ${layout.map { i -> i.toString() }.reduce { acc, s -> "$acc, $s" }}"
        return "[$size$layoutString]"
    }

}

data class AllocInstruction(val layout: AllocLayout) : Instruction()
