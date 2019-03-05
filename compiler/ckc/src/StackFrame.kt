import kotlin.math.max

class StackFrame {

    private val locals = ArrayList<Boolean>()
    private var maxLocals = 0
    private val temps = ArrayList<Boolean>()

    fun getLayout(): List<Int> {
        val ret = ArrayList<Int>()
        locals.forEachIndexed { index, b -> if (b) ret.add(index) }
        temps.forEachIndexed { index, b -> if (b) ret.add(index) }
        return ret
    }

    fun pushLocal(b: Boolean) {
        locals.add(b)
        maxLocals = max(locals.size, maxLocals)
    }

    fun popLocal() {
        locals.removeAt(locals.size - 1)
    }

    fun pushTemp(b:Boolean) {
        temps.add(b)
    }

    fun popTemp() {
        temps.removeAt(temps.size - 1)
    }

    fun maxLocals():Int {
        return maxLocals
    }

}

