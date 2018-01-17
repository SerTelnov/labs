package matroids.B

import java.io.*
import java.util.*

/**
 * Created by Telnov Sergey on 16.12.2017.
 */

private data class Edge(val from : Int, val to : Int, val weight : Long, val name : Int,
                var isOnSpanningTree : Boolean = false)

private class DSU(n: Int) {

    private val parents = IntArray(n) {i -> i}

    fun notEqualComponent(u: Int, v: Int) = get(u) != get(v)

    fun union(u: Int, v: Int) {
        val a = get(u)
        val b = get(v)
        if (a == b) {
            return
        }
        parents[b] = a
    }

    internal operator fun get(x: Int): Int {
        var root = x
        while (parents[root] != root) {
            root = parents[root]
        }
        var i = x
        while (parents[i] != i) {
            val temp = parents[i]
            parents[i] = root
            i = temp
        }
        return root
    }
}

fun main(args: Array<String>) {
    val input = FastReader("src\\matroids\\B\\destroy.in")
//    val input = FastReader("destroy.in")
    val (n, m) = Pair(input.nextInt(), input.nextInt())
    var s = input.nextLong()

    val dsu = DSU(n)

    val edges = Array(m) {
        Edge(input.nextVertex(), input.nextVertex(), input.nextLong(), it + 1)
    }

    edges.sortBy { -it.weight }

    edges.forEach {
        if (dsu.notEqualComponent(it.to, it.from)) {
            dsu.union(it.to, it.from)
            it.isOnSpanningTree = true
        }
    }

    val deletedEdges = LinkedList<Int>()

    var index = m - 1

    while (index >= 0) {
        val curr = edges[index]
        if (s - curr.weight < 0)
            break
        if (!curr.isOnSpanningTree) {
            s -= curr.weight
            deletedEdges.add(curr.name)
        }
        index--
    }

    val writer = FastWriter("destroy.out")

    writer.println(deletedEdges.size)

    deletedEdges.forEach {
        writer.print(it)
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
