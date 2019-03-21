import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class EitherTest {

    @Test
    fun eitherTest() {

        var either = Either.left<String, Int>("oops")
        assertEquals(either.left()!!, "oops")
        assertEquals(either.right(), null)

        assertEquals(either.map({l -> l.toUpperCase()}, { r -> r * 2}).left()!!, "OOPS")
        assertEquals(either.map({l -> l.toUpperCase()}, { r -> r * 2}).right(), null)

        either = Either.right(42)
        assertEquals(either.right()!!, 42)
        assertEquals(either.left(), null)

        assertEquals(either.map({l -> l.toUpperCase()}, { r -> r * 2}).right()!!, 84)
        assertEquals(either.map({l -> l.toUpperCase()}, { r -> r * 2}).left(), null)

        var items = listOf<Either<String, Int>>(Either.right(42), Either.right(2), Either.left("oops"))
        var res = items.foldRightEither(
            { l -> "error" },
            { r -> r[0] + r[1] }
        )
        assertEquals(res.left()!!, "error")

        items = listOf(Either.right(42), Either.right(2), Either.right(3))
        res = items.foldRightEither(
            { l -> "error" },
            { r -> r.sum() }
        )
        assertEquals(res.right()!!, 47)
    }

}