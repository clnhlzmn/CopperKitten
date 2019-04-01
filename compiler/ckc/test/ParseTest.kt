import ck.ast.Type
import ck.ast.node.Expr
import ck.grammar.Parse
import org.antlr.v4.runtime.CharStreams
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class ParseTest {

    @Test
    fun expr() {

        var input = "a"
        var expr = Parse.expr(CharStreams.fromString(input))
        assertEquals(expr.right()!!, Expr.Ref("a", Type.newVar()))

        input = "Oops"
        expr = Parse.expr(CharStreams.fromString(input))
        assertTrue(expr.isLeft())

        input = "(a): a"
        expr = Parse.expr(CharStreams.fromString(input))
        assertEquals(
            expr.right()!!,
            Expr.Fun(
                listOf(Expr.Fun.Param("a", Type.newVar())),
                Expr.Ref("a", Type.newVar()),
                Type.newVar()
            )
        )

        input = "(oops)-> a"
        expr = Parse.expr(CharStreams.fromString(input))
        assertTrue(expr.isLeft())
    }

    @Test
    fun file() {

    }

}