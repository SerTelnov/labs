package matroids.E

import java.io.*
import java.util.*
import kotlin.collections.HashSet

/**
 * Created by Telnov Sergey on 18.12.2017.
 */

fun main(args: Array<String>) {
    val scanner = FastReader("src\\matroids\\E\\cycles.in")
//    val scanner = FastReader("cycles.in")

    val (n, m) = Pair(scanner.nextInt(), scanner.nextInt())

    val weight = IntArray(n) {
        scanner.nextInt()
    }

    val cycles = IntArray(m) {
        val currSize = scanner.nextInt()

        var mask = 0
        (1..currSize).forEach {
            mask = mask or (1 shl scanner.nextVertex())
        }
        mask
    }

    val sets = ArrayList<Many>(1 shl n)

    var mask = 0
    var maxSize = -1
    while (mask < (1 shl n)) {
        if (!containsAny(cycles, mask)) {
            val curSize = Integer.bitCount(mask)
            if (maxSize <= curSize) {
                maxSize = curSize
                sets.add(Many(mask, curSize))
            }
        }
        mask++
    }

    var maxWeight = 0

    sets.forEach {
        if (it.size == maxSize) {
            val currWeight = (0 until n)
                    .filter { i -> it.mask and (1 shl i) != 0 }
                    .sumBy { weight[it] }
            if (maxWeight < currWeight) {
                maxWeight = currWeight
            }
        }
    }

    val writer = FastWriter("cycles.out")
    writer.println(maxWeight)
    writer.close()
}

data class Many(val mask: Int, val size: Int)

fun containsAny(set: IntArray, element: Int) = set.any { it and element == it }

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
