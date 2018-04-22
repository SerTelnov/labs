package flow.B

import java.io.*
import java.util.*
import kotlin.collections.HashSet

/**
 * Created by Telnov Sergey on 08.04.2018.
 */

private val set = HashSet<Int>()

fun setNetwork(curr: Int, network: Array<IntArray>, flows: Array<IntArray>, used: BooleanArray) {
    if (curr == network.size - 1 || used[curr])
        return

    set.add(curr)
    used[curr] = true
    for (next in 0 until network.size) {
        if (network[curr][next] - flows[curr][next] > 0) {
            setNetwork(next, network, flows, used)
        }
    }
}

data class Edge(val from: Int, val to: Int, val value: Int)

fun main(args: Array<String>) {
//    val reader = FastReader("C:\\pro\\homework\\labs\\algo\\src\\flow\\B\\cut.entry")
    val reader = FastReader("cut.entry")

    val (n, m) = Pair(reader.nextInt(), reader.nextInt())

    val network = Array(n, { IntArray(n) })
    val edges = Array<Edge?>(m, {null})

    for (i in 0 until m) {
        val from = reader.nextVertex()
        val (to, value) = Pair(reader.nextVertex(), reader.nextInt())
        edges[i] = Edge(from, to, value)

        network[from][to] = value
        network[to][from] = value
    }

    val flows = Array(n, { IntArray(n) })
    val path = IntArray(n)
    val queue = LinkedList<Int>()

    while (true) {
        path.fill(-1)
        queue.add(0)

        while (queue.isNotEmpty()) {
            val curr = queue.pollFirst()!!
            if (curr == n - 1) {
                break;
            }
            for (next in 0 until n) {
                if (network[curr][next] - flows[curr][next] > 0) {
                    if (path[next] == -1) {
                        path[next] = curr
                        if (next != n - 1) {
                            queue.add(next)
                        }
                    }
                }
            }
        }

        var curr = n - 1
        if (path[curr] == -1) {
            break;
        }

        var min = Int.MAX_VALUE
        while (curr != 0) {
            if (min > network[path[curr]][curr] - flows[path[curr]][curr]) {
                min = network[path[curr]][curr] - flows[path[curr]][curr]
            }
            curr = path[curr]
        }

        curr = n - 1
        while (curr != 0) {
            val prev = path[curr]
            flows[prev][curr] += min
            flows[curr][prev] -= min
            curr = prev
        }
    }

//    val writer = FastWriter("C:\\pro\\homework\\labs\\algo\\src\\flow\\B\\cut.exit")
    val writer = FastWriter("cut.exit")

    val used = BooleanArray(n)
    setNetwork(0, network, flows, used)

    val setEdges = TreeSet<Int>()

    for (i in 0 until m) {
        val curr = edges[i]!!

        if (set.contains(curr.from) && !set.contains(curr.to) ||
                !set.contains(curr.from) && set.contains(curr.to)) {
            setEdges.add(i)
        }
    }

    writer.print(setEdges.size)

    val flow = (0 until n)
            .filter { network[0][it] > 0 }
            .sumBy { flows[0][it] }

    writer.println(flow)

    setEdges.forEach {
        writer.print(it + 1)
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