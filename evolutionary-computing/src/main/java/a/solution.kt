package a

import java.io.*
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import kotlin.collections.HashMap

fun main(args: Array<String>) {
    val reader = FastReader("mutation.in")

    val len = reader.nextInt()
    val n = reader.nextInt()

    val fail: Double = (1 / len.toDouble())
    val success: Double = 1 - fail
    val map = HashMap<Double, String>()

    FastWriter("mutation.out").use {
        for (i in 0 until n) {
            val s1 = reader.next()
            val s2 = reader.next()

            var res = 1.0
            for (j in 0 until len) {
                res *= if (s1[j] != s2[j]) {
                    fail
                } else {
                    success
                }
            }

            val s = if (map.containsKey(res)) {
                map[res]!!
            } else {
                val s = "%.9f".format(res)
                map[res] = s
                s
            }

            it.println(s)
        }
    }
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
class FastWriter : Closeable {
    private var bw: BufferedWriter
    private val sb: StringBuilder = StringBuilder()

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

    override fun close() {
        bw.write(sb.toString())
        bw.flush()
        bw.close()
    }
}