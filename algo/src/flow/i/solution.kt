package flow.i

import java.io.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

const val MAX_N = 100
const val MAX_COUNT = MAX_N * MAX_N + MAX_N

val network = Array(MAX_COUNT) { HashMap<Int, Int>() }
val teams = IntArray(MAX_N + 10)
var counter = 0

fun set(): Int {
    return counter++
}

fun setTeam(i: Int) = if (teams[i] != 0) {
    teams[i]
} else {
    teams[i] = counter
    set()
}

fun getSymbol(value: Int) = when (value) {
    3 -> 'W'
    2 -> 'w'
    1 -> 'l'
    else -> 'L'
}

fun main(args: Array<String>) {
    val reader = FastReader()

    val n = reader.nextInt()
    val scores = IntArray(n)

    val s = set()
    val games = Array(n) { ArrayList<Pair<Int, Int>>() }
    val result = Array(n) { CharArray(n) }
    for (i in 0 until n) {
        val line = reader.next()
        for (j in 0 until n) {
            result[i][j] = line[j]
            if (i > j) {
                if (line[j] == '.') {
                    val curr = set()
                    network[s][curr] = 3

                    val team1 = setTeam(i)
                    val team2 = setTeam(j)

                    network[curr][team1] = 3
                    network[curr][team2] = 3
                    if (i > j) {
                        games[j].add(Pair(curr, i))
                    } else {
                        games[i].add(Pair(curr, j))
                    }
                } else {
                    scores[i] -= when (line[j]) {
                        'W' -> 3
                        'w' -> 2
                        'l' -> 1
                        else -> 0
                    }
                    scores[j] -= when (line[j]) {
                        'W' -> 0
                        'w' -> 1
                        'l' -> 2
                        else -> 3
                    }
                }
            }
        }
    }

    val t = set()
    for (team in 0 until n) {
        val value = reader.nextInt()
        network[teams[team]][t] = value + scores[team]
    }

    val path = IntArray(counter)
    val queue = ArrayDeque<Int>()
    val flows = Array(counter) { IntArray(counter) }

    while (true) {
        path.fill(-1)
        queue.clear()
        queue.add(s)

        path[s] = 0
        while (!queue.isEmpty()) {
            val curr = queue.pollFirst()!!
            if (curr == t) {
                break
            }

            for (el in network[curr]) {
                val next = el.key
                if (el.value - flows[curr][next] > 0) {
                    if (path[next] == -1) {
                        path[next] = curr
                        queue.add(next)
                    }
                }
            }
        }

        if (path[t] == -1)
            break

        var curr = t
        var min = Int.MAX_VALUE

        while (curr != s) {
            val prev = path[curr]
            if (min > network[prev][curr]!! - flows[prev][curr]) {
                min = network[prev][curr]!! - flows[prev][curr]
            }
            curr = prev
        }

        curr = t
        while (curr != s) {
            val prev = path[curr]
            flows[prev][curr] += min
            flows[curr][prev] -= min

            val value = network[curr][prev] ?: 0
            if (value - flows[curr][prev] > 0) {
                network[curr][prev] = value - flows[curr][prev]
            }

            curr = prev
        }
    }

    val writer = FastWriter()
    for (i in 0 until n) {
        for ((game, j) in games[i]) {
            val team1 = teams[i]
            val team2 = teams[j]
            result[i][j] = getSymbol(flows[game][team1])
            result[j][i] = getSymbol(flows[game][team2])
        }
        writer.println(result[i])
    }
    writer.close()
}

class FastReader {
    internal var br: BufferedReader
    internal var st: StringTokenizer? = null

    constructor() {
        br = BufferedReader(InputStreamReader(System.`in`))
    }

    constructor(fileName: String) {
        br = BufferedReader(InputStreamReader(
                FileInputStream(fileName))
        )
    }

    operator fun next(): String {
        while (st == null || !st!!.hasMoreElements()) {
            st = StringTokenizer(br.readLine())
        }
        return st!!.nextToken()
    }

    fun nextInt(): Int {
        return next().toInt()
    }

    fun nextLong(): Long {
        return next().toLong()
    }

    fun nextVertex(): Int {
        return nextInt() - 1
    }

    operator fun hasNext(): Boolean {
        if (st == null) {
            st = StringTokenizer(br.readLine())
        } else if (!st!!.hasMoreElements()) {
            val nextString = br.readLine() ?: return false
            st = StringTokenizer(nextString)
        }
        return st!!.hasMoreElements()
    }
}

class FastWriter {
    private var bw: BufferedWriter

    constructor() {
        bw = BufferedWriter(
                OutputStreamWriter(System.out)
        )
    }

    constructor(fileName: String) {
        bw = BufferedWriter(
                OutputStreamWriter(FileOutputStream(fileName))
        )
    }

    private val sb: StringBuilder = StringBuilder()

    fun println(i: Int) {
        sb.append(i).append("\n")
    }

    fun println(l: Long) {
        sb.append(l).append("\n")
    }

    fun println(s: String) {
        sb.append(s).append("\n")
    }

    fun println() {
        sb.append("\n")
    }

    fun println(d: Double) {
        sb.append(d).append("\n")
    }

    fun println(chArr: CharArray) {
        sb.append(chArr).append("\n")
    }

    fun print(ch: Char) {
        sb.append(ch)
    }

    fun print(s: String) {
        sb.append(s).append(" ")
    }

    fun print(i: Int) {
        sb.append(i).append(" ")
    }

    fun print(d: Double) {
        sb.append(d).append(" ")
    }

    fun close() {
        bw.write(sb.toString())
        bw.flush()
        bw.close()
    }
}
