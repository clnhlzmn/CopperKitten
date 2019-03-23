import ck.ast.Type.Companion.i
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import util.either.Either
import util.either.foldRightEither

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
            { "error" },
            { r -> r[0] + r[1] }
        )
        assertEquals(res.left()!!, "error")

        items = listOf(Either.right(42), Either.right(2), Either.right(3))
        res = items.foldRightEither(
            { "error" },
            { (a, b, c) -> a + b + c }
        )
        assertEquals(res.right()!!, 47)

        (Either.right<String, Int>(42)
            compose Either.right<String, String>("42"))
            .map (
                { assertFalse(true) },
                { (i, s) ->
                    assertEquals(i, 42)
                    assertEquals(s, "42")
                }
            )

        (Either.right<String, Int>(42)
            compose Either.right<String, String>("42")
            compose Either.right<String, Long>(84))
            .map (
                { assertFalse(true) },
                { (f, l) ->
                    val (i, s) = f
                    assertEquals(i, 42)
                    assertEquals(s, "42")
                    assertEquals(l, 84)
                }
            )

        (Either.right<String, Int>(42)
            compose Either.left<String, String>("oops")
            compose Either.right<String, Long>(84))
            .map (
                { assertEquals(it, "oops") },
                { (f, l) ->
                    val (i, s) = f
                    assertEquals(i, 42)
                    assertEquals(s, "42")
                    assertEquals(l, 84)
                }
            )


    }

}