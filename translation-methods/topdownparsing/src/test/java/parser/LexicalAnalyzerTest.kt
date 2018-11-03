package parser

import org.junit.Test
import parser.exception.LexicalException
import parser.exception.ParserException
import kotlin.test.assertEquals

class LexicalAnalyzerTest {

    @Test
    fun analyzeTest() {
        val input = "a&a^a|(!a)"
        val tokens = arrayOf(Token.VARIABLE,
                Token.AND, Token.VARIABLE,
                Token.XOR, Token.VARIABLE,
                Token.OR, Token.OPEN_BRACKET,
                Token.NEGATE, Token.VARIABLE,
                Token.CLOSE_BRACKET)

        val lexicalAnalyzer = LexicalAnalyzer(input)

        for (i in 0 until tokens.size) {
            assertEquals(tokens[i], lexicalAnalyzer.nextToken())
            assertEquals(tokens[i], lexicalAnalyzer.getCurrToken())
            assertEquals(i, lexicalAnalyzer.getIndex())
        }
    }

    @Test(expected = LexicalException::class)
    fun invalidOperation() {
        val input = "a@a"
        val lexicalAnalyzer = LexicalAnalyzer(input)
        for (i in 0 until input.length) {
            lexicalAnalyzer.nextToken()
        }
    }

    @Test(expected = LexicalException::class)
    fun invalidVariable() {
        val input = "Aaa"
        val analyzer = LexicalAnalyzer(input)
        for (i in 0 until input.length) {
            analyzer.nextToken()
        }
    }

    @Test(expected = ParserException::class)
    fun outOrRangeTest() {
        val input = "a&a"
        val analyzer = LexicalAnalyzer(input)
        for (i in 0 until input.length * 2) {
            analyzer.nextToken()
        }
    }
}