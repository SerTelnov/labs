package parser

import parser.exception.LexicalException
import parser.exception.ParserException

class LexicalAnalyzer(private val input: String) {

    private val line = "$input$"
    private var index = 0
    private var currChar: Char = '\u0000'
    private lateinit var currToken: Token

    fun getIndex() = index - 1
    fun getCurrToken() = currToken
    fun hasNext() = index < line.length && line[index] != '$' || currToken.isExpression()

    fun nextToken(): Token {
        while (nextChar().isWhitespace());

        currToken = when {
            currChar in ('a'..'z') -> Token.VARIABLE
            currChar == '(' -> Token.OPEN_BRACKET
            currChar == ')' -> Token.CLOSE_BRACKET
            currChar == '|' -> Token.OR
            currChar == '^' -> Token.XOR
            currChar == '&' -> Token.AND
            currChar == '!' -> Token.NEGATE
            currChar == '$' -> Token.END
            line.startsWith(">>", getIndex()) -> {
                index++
                Token.RIGHT_SHIFT
            }
            line.startsWith("<<", getIndex()) -> {
                index++
                Token.LEFT_SHIFT
            }
            else -> throw LexicalException(input, "invalid token: '$currChar'", getIndex())
        }

        return currToken
    }

    private fun nextChar(): Char {
        if (index >= line.length) {
            throw ParserException("expression expected at index: '$index'")
        }

        currChar = line[index++]
        return currChar
    }
}