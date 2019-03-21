

sealed class Either<A, B> {

    abstract fun left(): A?
    abstract fun right(): B?

    abstract fun isLeft(): Boolean
    abstract fun isRight(): Boolean

    abstract fun<C> mapLeft(f: (A) -> C): Either<C, B>
    abstract fun<C> mapRight(f: (B) -> C): Either<A, C>
    abstract fun fold(l: (A) -> Either<A, B>, r: (B) -> Either<A, B>): Either<A, B>

    data class Left<A, B>(val left: A): Either<A, B>() {
        override fun left(): A? = left
        override fun right(): B? = null
        override fun isLeft(): Boolean = true
        override fun isRight(): Boolean = false
        override fun <C> mapLeft(f: (A) -> C): Either<C, B> = Left(f(left))
        override fun <C> mapRight(f: (B) -> C): Either<A, C> = Left(left)
        override fun fold(l: (A) -> Either<A, B>, r: (B) -> Either<A, B>): Either<A, B> = l(left)
    }

    data class Right<A, B>(val right: B): Either<A, B>() {
        override fun left(): A? = null
        override fun right(): B? = right
        override fun isLeft(): Boolean = false
        override fun isRight(): Boolean = true
        override fun <C> mapLeft(f: (A) -> C): Either<C, B> = Right(right)
        override fun <C> mapRight(f: (B) -> C): Either<A, C> = Right(f(right))
        override fun fold(l: (A) -> Either<A, B>, r: (B) -> Either<A, B>): Either<A, B> = r(right)
    }

    companion object {
        fun<A, B> left(l: A): Either<A, B> = Left(l)
        fun<A, B> right(r: B): Either<A, B> = Right(r)
    }

}