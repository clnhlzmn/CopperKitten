import org.antlr.v4.runtime.CharStreams
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class ParseTest {

    @Test
    fun expr() {
        var input = "a"
        var expr = Parse.expr(CharStreams.fromString(input))
        assertTrue(expr.isRight())
        assertEquals(expr.right().get(), Expr.Ref("a", Type.newVar()))
        input = "Oops"
        expr = Parse.expr(CharStreams.fromString(input))
        assertTrue(expr.isLeft())
    }

    @Test
    fun file() {

    }

}