package matroids.C

import java.io.*
import java.util.*

/**
 * Created by Telnov Sergey on 18.12.2017.
 */

internal lateinit var used : Array<Boolean>
internal lateinit var matching : Array<Int>
internal lateinit var answer : Array<Int>
internal lateinit var graph : Array<ArrayList<Int>>

internal fun kuhn(curr: Int) : Boolean {
    if (!used[curr]) {
        used[curr] = true

        for (next in graph[curr]) {
            if (matching[next] == -1 || kuhn(matching[next])) {
                answer[curr] = next
                matching[next] = curr
                return true
            }
        }
    }
    return false
}

data class Vertex(val weight: Int, val index: Int)

fun main(args: Array<String>) {
//    val scanner = FastReader("matching.in");
    val scanner = FastReader("src\\matroids\\C\\matching.in")

    val n = scanner.nextInt()

    val vertices = Array(n) {
        Vertex(scanner.nextInt(), it)
    }

    vertices.sortBy { -it.weight }

    graph = Array(n) {
        val currSize = scanner.nextInt()

        val list = ArrayList<Int>(currSize)

        (0 until currSize).forEach {
            list.add(scanner.nextVertex())
        }

        list
    }

    used = Array(n) {false}
    matching = Array(n) {-1}
    answer = Array(n) {-1}

    for (v in vertices) {
        kuhn(v.index)
        used.fill(false)
    }

    val writer = FastWriter("matching.out")
    for (v in answer) {
        writer.print(v + 1)
    }
    writer.close()
}

private class FastReader {
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
private class FastWriter {

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
