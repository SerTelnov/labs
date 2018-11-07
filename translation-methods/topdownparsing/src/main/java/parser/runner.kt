package parser

import java.util.*

private fun execute(input: String) {
    val parser = Parser()
    val tree = parser.parse(input)
    println("Parsed expression: '$tree'")
    tree.toTree()
}

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Print any expression, example: 'a&a^(!a)")
        val scanner = Scanner(System.`in`)
        execute(scanner.next())
    } else {
        val input = buildString {
            for (arg in args) {
                append(arg)
            }
        }
        execute(input)
    }
}