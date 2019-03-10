import kotlin.math.max

class StackFrame {

    private val locals = ArrayList<Pair<String, Boolean>>()
    private var maxLocals = 0
    private val temps = ArrayList<Boolean>()

    fun getLayout(): List<Int> {
        val ret = ArrayList<Int>()
        locals.forEachIndexed { index, b -> if (b.second) ret.add(index) }
        temps.forEachIndexed { index, b -> if (b) ret.add(index + locals.size) }
        return ret
    }

    fun pushLocal(id: String, isRef: Boolean):Int {
        locals.add(Pair(id, isRef))
        maxLocals = max(locals.size, maxLocals)
        return locals.size - 1
    }

    fun lookupLocal(id: String): Int? {
        val i = locals.indexOfLast { l -> l.first == id }
        return if (i == -1) null else i
    }

    fun popLocal() {
        locals.removeAt(locals.size - 1)
    }

    fun pushTemp(isRef:Boolean) {
        temps.add(isRef)
    }

    fun popTemp() {
        temps.removeAt(temps.size - 1)
    }

    fun maxLocals():Int {
        return maxLocals
    }

}

