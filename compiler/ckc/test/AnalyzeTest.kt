import org.antlr.v4.runtime.CharStreams
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class AnalyzeTest {
    @Test
    fun test() {
        var input = "{let a = 42; a}"
        var expr = Parse.expr(CharStreams.fromString(input)).right().get()
        Analyze.analyze(expr, null, null)
        assertEquals(expr.t, Type.Op("Int"))
    }
}