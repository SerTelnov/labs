package parser

import parser.exception.LexicalException
import parser.exception.ParserException

class LexicalAnalyzer(input: String) {

    private val line = "$input$"
    private var index = 0
    private var currChar: Char = '\u0000'
    private lateinit var currToken: Token

    fun getIndex() = index - 1
    fun getCurrToken() = currToken

    fun hasNext() = index < line.length && line[index] != '$'

    fun nextToken(): Token {
        while (nextChar().isWhitespace());

        currToken = when (currChar) {
            in ('a'..'z') -> Token.VARIABLE
            '(' -> Token.OPEN_BRACKET
            ')' -> Token.CLOSE_BRACKET
            '|' -> Token.OR
            '^' -> Token.XOR
            '&' -> Token.AND
            '!' -> Token.NEGATE
            '$' -> Token.END
            else -> throw LexicalException("invalid token: '$currChar'")
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