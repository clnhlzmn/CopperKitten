import ck.ast.visitors.CompilationVisitor
import ck.ast.visitors.ScopeLinkingVisitor
import ck.grammar.Parse
import org.antlr.v4.runtime.CharStreams
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class CompilationVisitorTest {
    @Test
    fun testNaturalExpr() {
        val visitor = CompilationVisitor()
        val expr = Parse.expr(CharStreams.fromString("42")).right()!!
        val res = expr.accept(visitor)
        assert(visitor.frame.locals.size == 1)
    }
    @Test
    fun testBinExpr() {
        var visitor = CompilationVisitor()
        var expr = Parse.expr(CharStreams.fromString("43 < 42")).right()!!
        var res = expr.accept(visitor)
        assert(visitor.frame.locals.size == 1)

        visitor = CompilationVisitor()
        expr = Parse.expr(CharStreams.fromString("43 && 42")).right()!!
        res = expr.accept(visitor)
        assert(visitor.frame.locals.size == 1)

        visitor = CompilationVisitor()
        expr = Parse.expr(CharStreams.fromString("43 || 42")).right()!!
        res = expr.accept(visitor)
        assert(visitor.frame.locals.size == 1)
    }
    @Test
    fun testSequenceExpr() {
        val visitor = CompilationVisitor()
        val expr = Parse.expr(CharStreams.fromString("{42; 43}")).right()!!
        val res = expr.accept(visitor)
        assert(visitor.frame.locals.size == 1)
    }
    @Test
    fun testLetExpr() {
        val visitor = CompilationVisitor()
        val expr = Parse.expr(CharStreams.fromString("{let a = 42; a}")).right()!!
        expr.accept(ScopeLinkingVisitor())
        val res = expr.accept(visitor)
        assert(visitor.frame.locals.size == 1)
    }
    @Test
    fun testIfExpr() {
        val visitor = CompilationVisitor()
        val expr = Parse.expr(CharStreams.fromString("if (1) 42 else 43")).right()!!
        expr.accept(ScopeLinkingVisitor())
        val res = expr.accept(visitor)
        assert(visitor.frame.locals.size == 1)
    }
    @Test
    fun testFunExpr() {
        val visitor = CompilationVisitor()
        val expr = Parse.expr(CharStreams.fromString("(a): a")).right()!!
        expr.accept(ScopeLinkingVisitor())
        val res = expr.accept(visitor)
        assert(visitor.frame.locals.size == 1)
    }
    @Test
    fun testApplyExpr() {
        var visitor = CompilationVisitor()
        var expr = Parse.expr(CharStreams.fromString("{(a): a}(42)")).right()!!
        expr.accept(ScopeLinkingVisitor())
        var res = expr.accept(visitor)
        assert(visitor.frame.locals.size == 1)

        visitor = CompilationVisitor()
        expr = Parse.expr(CharStreams.fromString("let id = (a): a; id(42)")).right()!!
        expr.accept(ScopeLinkingVisitor())
        res = expr.accept(visitor)
        assert(visitor.frame.locals.size == 1)
    }
}