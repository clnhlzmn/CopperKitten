import ck.ast.visitors.CompilationVisitor
import ck.ast.visitors.ComputeCapturesVisitor
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
        expr.accept(visitor)
        assert(visitor.frame.locals.size == 1)
    }
    @Test
    fun testBinExpr() {
        var visitor = CompilationVisitor()
        var expr = Parse.expr(CharStreams.fromString("43 < 42")).right()!!
        expr.accept(visitor)
        assert(visitor.frame.locals.size == 1)

        visitor = CompilationVisitor()
        expr = Parse.expr(CharStreams.fromString("43 && 42")).right()!!
        expr.accept(visitor)
        assert(visitor.frame.locals.size == 1)

        visitor = CompilationVisitor()
        expr = Parse.expr(CharStreams.fromString("43 || 42")).right()!!
        expr.accept(visitor)
        assert(visitor.frame.locals.size == 1)
    }
    @Test
    fun testSequenceExpr() {
        val visitor = CompilationVisitor()
        val expr = Parse.expr(CharStreams.fromString("{42; 43}")).right()!!
        expr.accept(visitor)
        assert(visitor.frame.locals.size == 1)
    }
    @Test
    fun testLetExpr() {
        var visitor = CompilationVisitor()
        var expr = Parse.expr(CharStreams.fromString("{let a = 42; a}")).right()!!
        expr.accept(ScopeLinkingVisitor())
        expr.accept(visitor)
        assert(visitor.frame.locals.size == 1)
        expr = Parse.expr(CharStreams.fromString("{\n" +
            "    let read = cfun native_read ():Int;\n" +
            "    let write = cfun native_write (Int):Unit;\n" +
            "    \n" +
            "    let left = (a): (l, r): l(a);\n" +
            "    let right = (b): (l, r): r(b);\n" +
            "    let map = (e, l, r): e(l, r);\n" +
            "    \n" +
            "    let char = read();\n" +
            "    \n" +
            "    let either = \n" +
            "        if (char < 97)\n" +
            "            left(():60)\n" +
            "        else\n" +
            "            right(62);\n" +
            "    \n" +
            "    map(either, (f): write(f()), (i): write(i));\n" +
            "    //write(map(either, (f): f(), (i): i));\n" +
            "    \n" +
            "    let cons = (a, b): (s): s(a, b);\n" +
            "    let fst = (p): p((a,b): a);\n" +
            "    let snd = (p): p((a,b): b);\n" +
            "    \n" +
            "    let pair = cons((): 60, cons(62, ()));\n" +
            "    \n" +
            "    write(fst(pair)());\n" +
            "    write(fst(snd(pair)))\n" +
            "}")).right()!!
        visitor = CompilationVisitor()
        expr.accept(ScopeLinkingVisitor())
        expr.accept(visitor)
        assert(visitor.frame.locals.size == 1)
    }
    @Test
    fun testIfExpr() {
        val visitor = CompilationVisitor()
        val expr = Parse.expr(CharStreams.fromString("if (1) 42 else 43")).right()!!
        expr.accept(ScopeLinkingVisitor())
        expr.accept(visitor)
        assert(visitor.frame.locals.size == 1)
    }
    @Test
    fun testFunExpr() {
        val visitor = CompilationVisitor()
        val expr = Parse.expr(CharStreams.fromString("(a): a")).right()!!
        expr.accept(ScopeLinkingVisitor())
        expr.accept(visitor)
        assert(visitor.frame.locals.size == 1)
    }
    @Test
    fun testApplyExpr() {
        var visitor = CompilationVisitor()
        var expr = Parse.expr(CharStreams.fromString("{(a): a}(42)")).right()!!
        expr.accept(ScopeLinkingVisitor())
        expr.accept(visitor)
        assert(visitor.frame.locals.size == 1)

        visitor = CompilationVisitor()
        expr = Parse.expr(CharStreams.fromString("let id = (a): a; id(42)")).right()!!
        expr.accept(ScopeLinkingVisitor())
        expr.accept(visitor)
        assert(visitor.frame.locals.size == 1)
    }
}