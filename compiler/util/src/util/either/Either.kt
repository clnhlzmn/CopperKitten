package util.either

sealed class Either<A, B> {

    abstract fun left(): A?
    abstract fun right(): B?

    abstract fun isLeft(): Boolean
    abstract fun isRight(): Boolean

    abstract fun <C> mapLeft(f: (A) -> C): Either<C, B>
    abstract fun <C> mapRight(f: (B) -> C): Either<A, C>
    abstract fun <C, D> map(l: (A) -> C, r: (B) -> D): Either<C, D>

    infix fun <C> compose(rhs: Either<A, C>): Either<A, Pair<B, C>> {
        return when {
            isLeft() -> Either.left(left()!!)
            rhs.isLeft() -> Either.left(rhs.left()!!)
            else -> Either.right(Pair(right()!!, rhs.right()!!))
        }
    }

    data class Left<A, B>(val left: A) : Either<A, B>() {
        override fun left(): A? = left
        override fun right(): B? = null
        override fun isLeft(): Boolean = true
        override fun isRight(): Boolean = false
        override fun <C> mapLeft(f: (A) -> C): Either<C, B> = Left(f(left))
        override fun <C> mapRight(f: (B) -> C): Either<A, C> = Left(left)
        override fun <C, D> map(l: (A) -> C, r: (B) -> D): Either<C, D> = Left(l(left))
    }

    data class Right<A, B>(val right: B) : Either<A, B>() {
        override fun left(): A? = null
        override fun right(): B? = right
        override fun isLeft(): Boolean = false
        override fun isRight(): Boolean = true
        override fun <C> mapLeft(f: (A) -> C): Either<C, B> = Right(right)
        override fun <C> mapRight(f: (B) -> C): Either<A, C> = Right(f(right))
        override fun <C, D> map(l: (A) -> C, r: (B) -> D): Either<C, D> = Right(r(right))
    }

    companion object {
        fun <A, B> left(l: A): Either<A, B> = Left(l)
        fun <A, B> right(r: B): Either<A, B> = Right(r)
    }

}

fun <A, B> Collection<Either<A, B>>.foldRightEither(l: (A) -> A, r: (List<B>) -> B): Either<A, B> {
    val acc = arrayListOf<B>()
    for (item in this)
        if (item.isRight())
            acc.add(item.right()!!)
        else
            return Either.left(l(item.left()!!))
    return Either.right(r(acc))
}

fun <A, B> Collection<Either<A, B>>.foldLeftEither(l: (List<A>) -> A, r: (B) -> B): Either<A, B> {
    val acc = arrayListOf<A>()
    for (item in this)
        if (item.isLeft())
            acc.add(item.left()!!)
        else
            return Either.right(r(item.right()!!))
    return Either.left(l(acc))
}

