package parser.exception

open class ParserException : RuntimeException {
    constructor(message: String) : super(message)

    constructor(input: String, info : String, index: Int) : super(buildMessage(input, info, index))
}

private fun buildMessage(input: String, info: String, index: Int) = buildString {
    append('\n')
    val strHint = "For string: '"
    append("$strHint$input' ")
    append(info)
    append('\n')

    for (i in 0 until index + strHint.length) {
        append('~')
    }
    append('^')
}