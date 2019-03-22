class StackFrame {

    private val locals = ArrayList<Pair<String, Type>>()

    fun getLayout(): List<Int> {
        val ret = ArrayList<Int>()
        locals.forEachIndexed { index, b -> if (b.second.isRefType()) ret.add(index) }
        return ret
    }

    fun push(id: String, isRef: Type): Int {
        locals.add(Pair(id, isRef))
        return locals.size - 1
    }

    fun lookup(id: String, type: Type): Int? {
        val i = locals.indexOfLast { l -> l.first == id && l.second == type  }
        return if (i == -1) null else i
    }

    fun pop() {
        locals.removeAt(locals.size - 1)
    }

}

