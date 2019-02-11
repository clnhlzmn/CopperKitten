
open class Instruction

data class SimpleInstruction(val name: String) : Instruction()

val add = SimpleInstruction("add")
val sub = SimpleInstruction("sub")
val mul = SimpleInstruction("mul")
val div = SimpleInstruction("div")
val mod = SimpleInstruction("mod")

