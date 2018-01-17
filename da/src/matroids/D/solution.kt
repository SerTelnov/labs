package matroids.D

import java.io.*
import java.util.*
import kotlin.Comparator

/**
 * Created by Telnov Sergey on 16.12.2017.
 */

fun hasSecondProperty(multiplicity: SortedSet<TreeSet<Int>>): Boolean {
    for (set in multiplicity.reversed()) {
        for (el in set) {
            val temp = TreeSet(set)
            temp.remove(el)
            if (!multiplicity.contains(temp)) {
                return false
            }
        }
    }
    return true
}

fun hasThirdProperty(multiplicity: SortedSet<TreeSet<Int>>) : Boolean {
    val I = multiplicity.toTypedArray()
    for (i in 0 until multiplicity.size) {
        val A = I[i]
        for (j in i + 1 until multiplicity.size) {
            val B = TreeSet(I[j])
            if (A.size == B.size)
                continue
            B.removeAll(A)
            var flag = false
            for (el in B) {
                val newA = TreeSet(A)
                newA.add(el)
                if (multiplicity.contains(newA)) {
                    flag = true
                    break
                }
            }
            if (!flag) {
                return false
            }
        }
    }
    return true
}

fun main(args: Array<String>) {
    val scanner = FastReader("check.in")
//    val scanner = FastReader("src\\matroids\\D\\check.in")

    val (n, m) = Pair(scanner.nextInt(), scanner.nextInt())

    val multiplicity = TreeSet<TreeSet<Int>>(CompareBySize())

    (1..m).forEach {
        val size = scanner.nextInt()
        val currHash = TreeSet<Int>()
        (1..size).forEach {
            currHash.add(scanner.nextInt())
        }
        multiplicity.add(currHash)
    }

    val writer = FastWriter("check.out")

    if ((multiplicity.isNotEmpty() && multiplicity.first().isEmpty()) &&
            hasSecondProperty(multiplicity) && hasThirdProperty(multiplicity)) {
        writer.println("YES")
    } else {
        writer.println("NO")
    }
    writer.close()
}

internal class CompareBySize : Comparator<TreeSet<Int>> {
    override fun compare(o1: TreeSet<Int>, o2: TreeSet<Int>) : Int {
        when {
            o1.size > o2.size -> return 1
            o1.size < o2.size -> return -1
            else ->  {
                val el1 = o1.iterator()
                val el2 = o2.iterator()
                while (el1.hasNext()) {
                    val v1 = el1.next()
                    val v2 = el2.next()
                    if (v1 > v2) {
                        return 1
                    } else if (v1 < v2) {
                        return -1
                    }
                }
            }
        }
        return 0
    }
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
