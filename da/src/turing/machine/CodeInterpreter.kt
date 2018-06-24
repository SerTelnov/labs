package turing.machine

import java.nio.file.Files
import java.nio.file.Paths

/**
 * Created by Telnov Sergey on 15.06.2018.
 */
class CodeInterpreter(fileName: String) {

    private lateinit var tape: Array<Symbol>
    private val transitions = HashMap<String, HashMap<Symbol, Transition>>()
    private var point = 0
    private var status = "S"
    private lateinit var start: String
    private lateinit var accept: String
    private lateinit var reject: String
    private lateinit var blank: Symbol

    init {
        Files
                .readAllLines(Paths.get(fileName))
                .forEach {
                    val args = it.split(" ")

                    when (args[0]) {
                        "start:" -> start = args[1]
                        "accept:" -> accept = args[1]
                        "reject:" -> reject = args[1]
                        "blank:" -> blank = Symbol(args[1])

                        else -> {
                            transitions.putIfAbsent(args[0], HashMap())
                            transitions[args[0]]!![Symbol(args[1])] =
                                    Transition(args.subList(3, args.size))
                        }
                    }
                }
        if (transitions.size >= 500) {
            throw RuntimeException("to many states: ${transitions.size}")
        }
    }

    fun run(input: String): String {
        val blanksCounter = 5
        tape = Array(input.length + blanksCounter * 2) {
            if (it < blanksCounter || it >= input.length + blanksCounter) {
                blank
            } else {
                Symbol(input[it - blanksCounter].toString())
            }
        }

        point = blanksCounter
        status = start

        while (status != accept && status != reject) {
            try {
                val tr = transitions[status]!![tape[point]]!!
                tape[point] = tr.symbol
                status = tr.state
                point += tr.move
            } catch (e: Exception) {
                throw RuntimeException("can't find transition: $status ${tape[point]}", e)
            }
        }


        val builder = StringBuilder()

        for (i in point until tape.size) {
            if (tape[i] != blank) {
                builder.append(tape[i].toString())
            }
        }

        return builder.toString()
    }

    private data class Symbol(var data: String = "_") {
        override fun toString(): String {
            return if (data != "_") data else ""
        }
    }

    private class Transition(args: List<String>) {
        val state: String = args[0]
        val symbol: Symbol = Symbol(args[1])
        val move = when {
            args[2] == ">" -> 1
            args[2] == "<" -> -1
            else -> 0
        }
    }
}