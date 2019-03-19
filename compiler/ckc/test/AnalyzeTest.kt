import org.antlr.v4.runtime.CharStreams
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class AnalyzeTest {
    @Test
    fun test() {
        var input = "()"
        var expr = Parse.expr(CharStreams.fromString(input)).right().get()
        Analyze.analyze(expr, null, null)
        assertEquals(Analyze.prune(expr.t), Type.Op("Unit"))

        input = "{let a = 42; a}"
        expr = Parse.expr(CharStreams.fromString(input)).right().get()
        Analyze.analyze(expr, null, null)
        assertEquals(Analyze.prune(expr.t), Type.Op("Int"))

        input = "{let id = (a): a; id(42)}"
        expr = Parse.expr(CharStreams.fromString(input)).right().get()
        Analyze.analyze(expr, null, null)
        assertEquals(Analyze.prune(expr.t), Type.Op("Int"))
    }
}