

sealed class Result<A, B> {
    data class Failure<A, B>(val a: A): Result<A, B>()
    data class Success<A, B>(val b: B): Result<A, B>()
}

