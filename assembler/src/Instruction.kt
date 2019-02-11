import sun.java2d.pipe.SpanShapeRenderer

open class Instruction

data class SimpleInstruction(val name: String) : Instruction()

data class PushInstruction(val name: String, val data: Int) : Instruction()

val add = SimpleInstruction("add")
val sub = SimpleInstruction("sub")
val mul = SimpleInstruction("mul")
val div = SimpleInstruction("div")
val mod = SimpleInstruction("mod")
val shl = SimpleInstruction("shl")
val shr = SimpleInstruction("shr")
val cmp = SimpleInstruction("cmp")
val skipz = SimpleInstruction("skipz")
val ip = SimpleInstruction("ip")
val fp = SimpleInstruction("fp")
val jump = SimpleInstruction("jump")
val dup = SimpleInstruction("dup")
val pop = SimpleInstruction("pop")
val swap = SimpleInstruction("swap")
val enter = SimpleInstruction("enter")
val `in` = SimpleInstruction("in")
val out = SimpleInstruction("out")
val layout = SimpleInstruction("layout")
val alloc = SimpleInstruction("alloc")
val fpload = SimpleInstruction("fpload")
val fpstore = SimpleInstruction("fpstore")
val rload = SimpleInstruction("rload")
val rstore = SimpleInstruction("rstore")
val ncall = SimpleInstruction("ncall")
val nop = SimpleInstruction("nop")
