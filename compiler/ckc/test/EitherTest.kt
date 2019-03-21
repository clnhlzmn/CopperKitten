import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class EitherTest {

    @Test
    fun eitherTest() {
        var items = listOf<Either<String, Int>>(Either.right(42), Either.right(2), Either.left("oops"))
        var res = items.foldEither(
            { l -> "error" },
            { r -> r[0] + r[1] }
        )
        assertEquals(res.left()!!, "error")

        items = listOf(Either.right(42), Either.right(2), Either.right(3))
        res = items.foldEither(
            { l -> "error" },
            { r -> r.sum() }
        )
        assertEquals(res.right()!!, 47)
    }

}