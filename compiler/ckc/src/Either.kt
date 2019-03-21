

sealed class Either<A, B> {

    data class Left<A, B>(val left: A): Either<A, B>() {

    }

    data class Right<A, B>(val right: B): Either<A, B>() {

    }

    companion object {

        fun<A, B> left(l: A): Either<A, B> = Left(l)

        fun<A, B> right(r: B): Either<A, B> = Right(r)

    }

    fun isLeft(): Boolean = this is Left

    fun isRight(): Boolean = this is Right

    fun left(): A? =
        when (this) {
            is Left -> left
            else -> null
        }

    fun right(): B? =
        when (this) {
            is Right -> right
            else -> null
        }

    fun<C> mapRight(f: (B) -> C): Either<A, C> =
        when (this) {
            is Left -> Left(left)
            is Right -> Right(f(right))
        }

    fun<C> mapLeft(f: (A) -> C): Either<C, B> =
        when (this) {
            is Left -> Left(f(left))
            is Right -> Right(right)
        }

    fun fold(l: (A) -> Either<A, B>, r: (B) -> Either<A, B>): Either<A, B> =
        when (this) {
            is Left -> l(left)
            is Right -> r(right)
        }

}