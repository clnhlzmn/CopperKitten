

data class Error(val what: String)

class Checker {

    fun check(statement: Statement): List<Error> {
        return ArrayList()
    }

    fun check(statements: BlockStatement): List<Error> {
        val ret = ArrayList<Error>()
        for (statement in statements.statements) {
            ret.addAll(check(statement))
            if (ret.isNotEmpty())
                return ret;
        }
        return ret
    }

}

