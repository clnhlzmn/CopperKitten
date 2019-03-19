

sealed class Result<L, R> {

    data class Error<L, R>(val value: L): Result<L, R>()

    data class Value<L, R>(val value: R): Result<L, R>()

    inline infix fun <L, R> Result<L, R>.map(f: (R) -> R): Result<L, R> =
        when(this) {
            is Error -> this
            is Result.Value -> Result.Value(f(this.value))
        }


}

