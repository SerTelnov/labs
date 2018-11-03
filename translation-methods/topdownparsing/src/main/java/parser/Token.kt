package parser

enum class Token {
    VARIABLE,
    AND,
    OR,
    XOR,
    NEGATE,
    OPEN_BRACKET,
    CLOSE_BRACKET,
    END
}

fun Token.isExpression() = this != Token.END

fun Token.isBinOperation() = this == Token.AND || this == Token.OR || this == Token.XOR

fun Token.isOperation() = this.isBinOperation() || this == Token.NEGATE

fun Token.isEnd() = this == Token.END

fun Token.isOpenBracket() = this == Token.OPEN_BRACKET
fun Token.isCloseBracket() = this == Token.CLOSE_BRACKET