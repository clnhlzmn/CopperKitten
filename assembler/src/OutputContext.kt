
//represents areas of output c file in which to
//emit various parts of the compiled program
class OutputContext {
    //where generated c functions go
    val functions = StringBuilder()
    //where the program array goes
    val program = StringBuilder()

    override fun toString(): String {
        return "uint8_t program[] = {$program};"
    }
}