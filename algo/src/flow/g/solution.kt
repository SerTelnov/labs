package flow.g

import java.io.*
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import kotlin.collections.HashSet

private const val MAX_CELLS_NUMBER = 50 * 50
val changedCells = Array(MAX_CELLS_NUMBER) { BooleanArray(MAX_CELLS_NUMBER) }

private var verticesCounter = 0
private var buildCellsCounter = 0
private val network = Array(MAX_CELLS_NUMBER) { IntArray(MAX_CELLS_NUMBER) }
private val cells = Array(MAX_CELLS_NUMBER) { IntArray(MAX_CELLS_NUMBER) { -1 } }
private var world: Array<String>? = null
private val graph = Array(MAX_CELLS_NUMBER) { HashSet<Int>() }
private val buildCells = IntArray(MAX_CELLS_NUMBER * 3)

private var s = -1
private var t = -1
private var n = 0
private var m = 0

private val used = BooleanArray(MAX_CELLS_NUMBER)

private fun fillSet(curr: Int) {
    if (curr == t || used[curr])
        return

    used[curr] = true
    for (next in graph[curr]) {
        fillSet(next)
    }
}

fun changeWorld(): Int {
    val size = verticesCounter
    val flows = Array(size, { IntArray(size) })
    val path = IntArray(size)
    val mins = IntArray(size)
    val queue = IntArray(verticesCounter * 2)

    while (true) {
        path.fill(-1)
        mins.fill(Int.MAX_VALUE)
        var tail = 0
        var head = 0

        queue[tail++] = s
        path[s] = Int.MAX_VALUE

        while (head < tail) {
            val curr = queue[head++]
            if (curr == t) {
                break
            }

            for (next in graph[curr]) {
                if (path[next] == -1) {
                    path[next] = curr
                    val value = network[curr][next]
                    mins[next] = Math.min(
                            mins[curr],
                            if (value == Int.MAX_VALUE)
                                Int.MAX_VALUE
                            else
                                value - flows[curr][next])

                    queue[tail++] = next
                }
            }
        }

        if (path[t] == -1)
            break

        val min = mins[t]

        if (min != 1) {
            return -1
        }

        var curr = t
        while (curr != s) {
            val prev = path[curr]

            flows[prev][curr] += min
            flows[curr][prev] -= min

            var value = network[prev][curr]
            if (value != Int.MAX_VALUE && value - flows[prev][curr] == 0) {
                graph[prev].remove(curr)
            }

            value = network[curr][prev]
            if (value - flows[curr][prev] != 0) {
                graph[curr].add(prev)
            }

            curr = prev
        }
    }

    fillSet(s - 1)

    var count = 0
    for (i in 0 until buildCellsCounter) {
        val curr = buildCells[i * 3]
        val x = buildCells[i * 3 + 1]
        val y = buildCells[i * 3 + 2]
        if (used[curr] && !used[curr + 1]) {
            changedCells[x][y] = true
            count++
        }
    }

    return count
}

private fun getValue(i: Int, j: Int) = when (world!![i][j]) {
    '.' -> 1
    '#' -> -1
    else -> Int.MAX_VALUE
}

fun link(entry: Int, dEntry: Int) {
    graph[entry + 1].add(dEntry)
    network[entry + 1][dEntry] = Int.MAX_VALUE
    graph[dEntry + 1].add(entry)
    network[dEntry + 1][entry] = Int.MAX_VALUE
}

private fun getCell(x: Int, y: Int, value: Int = Int.MAX_VALUE): Int {
    if (cells[x][y] != -1)
        return cells[x][y]

    val entry = verticesCounter++
    val exit = verticesCounter++

    cells[x][y] = entry
    graph[entry].add(exit)
    network[entry][exit] = value

    if (value == 1) {
        buildCells[buildCellsCounter * 3] = entry
        buildCells[buildCellsCounter * 3 + 1] = x
        buildCells[buildCellsCounter * 3 + 2] = y
        buildCellsCounter++
    }

    return entry
}

private val sequence = IntArray(4)

private fun putCell(i: Int, j: Int, count: Int): Int {
    if (i in (0 until n) && j in (0 until m)) {
        val value = getValue(i, j)
        if (value != -1) {
            sequence[count] = getCell(i, j, value)
            return 1
        }
    }
    return 0
}

private fun getNearCell(x: Int, y: Int): Int {
    var count = 0

    count += putCell(x, y + 1, count)
    count += putCell(x, y - 1, count)
    count += putCell(x + 1, y, count)
    count += putCell(x - 1, y, count)
    return count
}

fun init(n: Int, m: Int, world: Array<String>) {
    for (i in 0 until n) {
        for (j in 0 until m) {
            val value = getValue(i, j)
            if (value != -1) {
                val curr = getCell(i, j, value)

                val count = getNearCell(i, j)
                for (next in 0 until count) {
                    link(curr, sequence[next])
                }

                if (world[i][j] == 'A') {
                    s = curr + 1
                } else if (world[i][j] == 'B') {
                    t = curr
                }
            }
        }
    }
}

fun main(args: Array<String>) {
    val reader = FastReader()
//    val reader = FastReader("C:\\pro\\homework\\labs\\algo\\src\\flow\\g\\test.in")

    n = reader.nextInt()
    m = reader.nextInt()

    world = Array(n) {
        reader.next()
    }

    init(n, m, world!!)
    val flow = changeWorld()

    val writer = FastWriter()

    writer.println(flow)
    if (flow >= 0) {
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (changedCells[i][j]) {
                    writer.print('+')
                } else {
                    writer.print(world!![i][j])
                }
            }
            writer.println()
        }
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
    private val sb: StringBuilder = StringBuilder(MAX_CELLS_NUMBER * MAX_CELLS_NUMBER)

    constructor() {
        bw = BufferedWriter(
                OutputStreamWriter(System.out))
    }

    constructor(fileName: String) {
        bw = Files.newBufferedWriter(Paths.get(fileName))
    }

    fun println(i: Int) {
        sb.append(i).append('\n')
    }

    fun println(l: Long) {
        sb.append(l).append('\n')
    }

    fun println(s: String) {
        sb.append(s).append('\n')
    }

    fun println() {
        sb.append('\n')
    }

    fun println(d: Double) {
        sb.append(d).append('\n')
    }

    fun println(chArr: CharArray) {
        sb.append(chArr).append('\n')
    }

    fun print(ch: Char) {
        sb.append(ch)
    }

    fun print(s: String) {
        sb.append(s).append(' ')
    }

    fun print(i: Int) {
        sb.append(i).append(' ')
    }

    fun print(d: Double) {
        sb.append(d).append(' ')
    }

    fun close() {
        bw.write(sb.toString())
        bw.flush()
        bw.close()
    }
}
