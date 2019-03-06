
import org.antlr.v4.runtime.BaseErrorListener
import org.antlr.v4.runtime.RecognitionException
import org.antlr.v4.runtime.Recognizer

class DescriptiveErrorListener(val error: Array<Boolean>) : BaseErrorListener() {

    override fun syntaxError(
        recognizer: Recognizer<*, *>?, offendingSymbol: Any?,
        line: Int, charPositionInLine: Int,
        msg: String?, e: RecognitionException?
    ) {

        var sourceName = recognizer!!.inputStream.sourceName
        if (!sourceName.isEmpty()) {
            sourceName = String.format("%s:%d:%d: ", sourceName, line, charPositionInLine)
        }

        System.err.println("$sourceName line $line:$charPositionInLine $msg")
        error[0] = true
    }
}