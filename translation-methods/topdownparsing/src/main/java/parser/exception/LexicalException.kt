package parser.exception

open class LexicalException(input: String, info: String, index: Int)
    : ParserException(input, info, index)