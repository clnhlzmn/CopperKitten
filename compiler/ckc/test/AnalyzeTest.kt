import org.antlr.v4.runtime.CharStreams
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class AnalyzeTest {

    companion object {
        fun getType(input: String): Type {
            val expr = Parse.expr(CharStreams.fromString(input)).right()!!
            Analyze.analyze(expr, null, null)
            return Analyze.prune(expr.t)
        }
    }

    @Test
    fun test() {
        assertEquals(getType("()"), Type.Op("Unit"))
        assertEquals(getType("{let a = 42; a}"), Type.Op("Int"))
        assertEquals(getType("{let id = (a): a; id(42)}"), Type.Op("Int"))
    }
}