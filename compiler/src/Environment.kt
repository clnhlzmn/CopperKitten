import java.util.*
import kotlin.collections.HashMap

class Environment<T> : ArrayDeque<MutableMap<String, T>>() {

    //push a new scope
    fun push() = addLast(HashMap())

    //lookup an id in the environment
    fun lookup(id: String): T? {
        for (scope in this.reversed()) {
            if (scope.containsKey(id)) {
                return scope[id]
            }
        }
        return null
    }

}

