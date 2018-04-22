package flow.D

import java.io.*
import java.util.*

/**
 * Created by Telnov Sergey on 08.04.2018.
 */

private var graph: Array<ArrayList<Int>>? = null
private var used: BooleanArray? = null
private var matching: IntArray? = null

fun foundMatching(curr: Int): Boolean {
    if (used!![curr])
        return false
    used!![curr] = true
    for (next in graph!![curr]) {
        if (matching!![next] == -1 || 
            foundMatching(matching!![next])) {
            matching!![next] = curr
            return true
        }
    }
    return false
}

fun main(args: Array<String>) {
    val reader = FastReader();
    val (n, m) = Pair(
            reader.nextInt(),
            reader.nextInt()
    )
    val size = Math.max(n, m)
    graph = Array(size) { ArrayList<Int>() }

    for (i in 0 until n) {
        while (true) {
            val curr = reader.nextVertex()
            if (curr < 0) {
                break
            }

            graph!![i].add(curr)
        }
    }

    matching = IntArray(size, { -1 })
    used = BooleanArray(size)

    for (i in 0 until n) {
        used!!.fill(false)
        foundMatching(i)
    }

    val set = LinkedList<Pair<Int, Int>>()
    for (i in 0 until matching!!.size) {
        if (matching!![i] != -1) {
            set.add(Pair(matching!![i] + 1, i + 1))
        }
    }

    val writer = FastWriter()
    writer.println(set.size)

    set.forEach {
        writer.print(it.first)
        writer.println(it.second)
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