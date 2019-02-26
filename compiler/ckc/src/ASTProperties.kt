

class ASTProperties {

    private val store = HashMap<ASTNode, HashMap<String, Any>>()

    fun put(node: ASTNode, key: String, value: Any): Any? {
        val nodeStore = store[node]
        return if (nodeStore != null) {
            nodeStore.put(key, value)
        } else {
            val newNodeStore = HashMap<String, Any>()
            newNodeStore[key] = value
            store[node] = newNodeStore
            null
        }
    }

    fun get(node: ASTNode, key: String): Any? {
        val nodeStore = store[node]
        return if (nodeStore != null) {
            nodeStore[key]
        } else {
            null
        }
    }

}
