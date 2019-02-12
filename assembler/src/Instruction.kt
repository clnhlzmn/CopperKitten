import sun.java2d.pipe.SpanShapeRenderer

open class Instruction

data class SimpleInstruction(val name: String) : Instruction()

data class PushInstruction(val name: String, val data: Int) : Instruction()


