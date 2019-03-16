

class Env<T> private constructor(private val id:String, private val value: T, private val env: Env<T>?) {

    companion object {

        fun<T> empty(): Env<T>? = null

        fun<T> extend(id:String, value: T, e: Env<T>?) = Env(id, value, e)

        fun<T> lookup(id: String, e: Env<T>?): T? =
            when {
                e == null -> null
                e.id == id -> e.value
                else -> lookup(id, e.env)
            }

    }

}

