package flow.C

import java.io.*
import java.util.*
import kotlin.collections.HashMap

/**
 * Created by Telnov Sergey on 08.04.2018.
 */

private val writer = FastWriter("C:\\pro\\homework\\labs\\algo\\src\\flow\\C\\snails.exit")
//private val writer = FastWriter("snails.exit")

fun printFlow(curr: Int, end: Int, flows: Array<IntArray>, graph: Array<HashMap<Int, Int>>): Boolean {
    writer.print(curr + 1)

    if (curr != end) {
        for (next in graph[curr]) {
            if (flows[curr][next.key] > 0 && flows[next.key][curr] < 0 && next.value >= 1) {
                graph[curr][next.key] = next.value - 1
                if (printFlow(next.key, end, flows, graph)) {
                    return true
                }
            }
        }
    } else {
        return true
    }

    return false
}

fun getFlows(network: Array<HashMap<Int, Int>>, s: Int, t: Int): Pair<Int, Array<IntArray>> {
    val flows = Array(network.size, { IntArray(network.size) })
    val path = IntArray(network.size)
    val queue = LinkedList<Int>()

    while (true) {
        path.fill(-1)
        queue.clear()
        queue.add(s)

        path[s] = 0
        while (queue.isNotEmpty()) {
            val curr = queue.pollFirst()!!
            if (curr == t) {
                break
            }

            for (next in network[curr]) {
                if (next.value - flows[curr][next.key] > 0) {
                    if (path[next.key] == -1) {
                        path[next.key] = curr
                        queue.add(next.key)
                    }
                }
            }
        }

        var curr = t
        if (path[curr] == -1) {
            break
        }

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

            if (!network[curr].containsKey(prev)) {
                network[curr][prev] = 0
            }

            if (network[curr][prev]!! - flows[curr][prev] > 0) {
                network[curr][prev] = network[curr][prev]!! - flows[curr][prev]
            }

            curr = prev
        }
    }
    val flow = (0 until network.size)
            .filter { network[0].contains(it) }
            .sumBy { flows[0][it] }

    return Pair(flow, flows)
}

fun main(args: Array<String>) {
    val reader = FastReader("C:\\pro\\homework\\labs\\algo\\src\\flow\\C\\snails.entry")
//    val reader = FastReader("snails.entry")

    val (n, m) = Pair(
            reader.nextInt(),
            reader.nextInt())

    val (s, t) = Pair(
            reader.nextVertex(),
            reader.nextVertex())

    val graph = Array(n, { HashMap<Int, Int>() })
    for (i in 0 until m) {
        val from = reader.nextVertex()
        val to = reader.nextVertex()

        if (from != to) {
            graph[from][to] = if (graph[from].containsKey(to)) {
                graph[from][to]!! + 1
            } else {
                1
            }
        }
    }

    val (value, flows) = getFlows(graph, s, t)

    if (value < 2) {
        writer.println("NO")
    } else {
        writer.println("YES")
        printFlow(s, t, flows, graph)
        writer.println()
        printFlow(s, t, flows, graph)
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