class StackFrame {

    private val locals = ArrayList<Pair<String, Boolean>>()

    fun getLayout(): List<Int> {
        val ret = ArrayList<Int>()
        locals.forEachIndexed { index, b -> if (b.second) ret.add(index) }
        return ret
    }

    fun push(id: String, isRef: Boolean): Int {
        locals.add(Pair(id, isRef))
        return locals.size - 1
    }

    fun lookup(id: String): Int? {
        val i = locals.indexOfLast { l -> l.first == id }
        return if (i == -1) null else i
    }

    fun pop() {
        locals.removeAt(locals.size - 1)
    }

}

