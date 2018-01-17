package algorithmsOnLines.F

import java.util.*

/**
 * Created by Telnov Sergey on 29.12.2017.
 */

fun main(args: Array<String>) {
    val random = Random()

    val lines = Array(4) {
        buildString {
            (0..3).forEach {
                append(((Math.abs(random.nextInt()) % 20) + 'a'.toInt()).toChar())
            }
        }
    }

    println(lines.size)
    lines.forEach {
        println(it)
    }
}