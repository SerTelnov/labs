package parser

import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

private val random = Random()

fun main(args: Array<String>) {
    for (i in 1..9) {
        Files.newBufferedWriter(Paths.get("topdownparsing\\src\\test\\java\\parser\\tests\\test$i.txt"))
                .use {
                    for (j in 0..1000) {
                        it.write(makeExpression(100))
                        it.write("\n")
                    }
                }
    }
}

fun makeExpression(numberOfArgs: Int): String {
    return buildString {
        append("a")
        var count = 1
        while (count < numberOfArgs) {
            var increment = 2
            when (random.nextInt(320)) {
                in 0 until 100 -> append("&a")
                in 100 until 200 -> append("|a")
                in 200 until 300 -> append("^a")
                else -> {
                    replace(length - 1, length, "!a")
                    increment = 1
                }
            }
            count += increment
        }
    }
}