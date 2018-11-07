package parser

import parser.Token.*
import parser.exception.ParserException

enum class Token {
    VARIABLE,
    AND,
    OR,
    XOR,
    NEGATE,
    OPEN_BRACKET,
    CLOSE_BRACKET,
    LEFT_SHIFT,
    RIGHT_SHIFT,
    END;

    override fun toString() = when(this) {
        Token.VARIABLE -> "a"
        Token.AND -> "&"
        Token.OR -> "|"
        Token.XOR -> "^"
        Token.NEGATE -> "!"
        Token.OPEN_BRACKET -> "("
        Token.CLOSE_BRACKET -> ")"
        Token.LEFT_SHIFT -> "<<"
        Token.RIGHT_SHIFT -> ">>"
        else -> throw ParserException("invalid token: '$this'")
    }
}

fun Token.isExpression() = this != END

fun Token.isBinOperation() = this == AND || this == OR ||
        this == XOR || this == RIGHT_SHIFT || this == LEFT_SHIFT

fun Token.isOperation() = this.isBinOperation() || this == NEGATE

fun Token.isEnd() = this == END

fun Token.isOpenBracket() = this == OPEN_BRACKET
fun Token.isCloseBracket() = this == CLOSE_BRACKET

