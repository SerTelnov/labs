package algorithmsOnLines.O

import java.io.*
import java.util.*

/**
 * Created by Telnov Sergey on 22.12.2017.
 */

data class SuffixArray(val s: String, val m: Int) {
    val ALPHABET_SIZE = m + 2
    val NEUTRAL_CHAR = '0'.toInt() - 1

    val length = s.length
    var suffixes = IntArray(length)
    val lcp = IntArray(length)

    data class Info(val weight: Int, val index: Int, val length: Int)

    var maxWeight = 1
    var maxLength = length - 1

    fun refrain(): String {
        var startIndex = 0

        val stack = Stack<Info>()

        for (i in 1 until length) {
            var x = 1

            while (stack.isNotEmpty() && stack.firstElement().length >= lcp[i]) {
                val el = stack.pop()
                x += el.weight

                if (maxWeight * maxLength < x * el.length) {
                    maxWeight = x
                    maxLength = el.length
                    startIndex = suffixes[el.index]
                }
            }

            if (stack.isEmpty() || stack.peek().length > lcp[i]) {
                stack.push(Info(x, i, lcp[i]))
            }
        }

        return buildString {
            (startIndex until startIndex + maxLength).forEach {
                append(s[it])
                append(" ")
            }
        }
    }

    init {
        val cnt = IntArray(Math.max(ALPHABET_SIZE, length)) { 0 }

        s.forEach { cnt[it.toInt() - NEUTRAL_CHAR]++ }

        fun countSort(arr: IntArray, length: Int = arr.size) {
            var sum = 0
            for (i in 0 until length) {
                sum += arr[i]
                arr[i] = sum - arr[i]
            }
        }

        countSort(cnt)

        for (i in 0 until length) {
            suffixes[cnt[s[i].toInt() - NEUTRAL_CHAR]++] = i
        }

        val classes = IntArray(length) { 0 }

        for (i in 1 until length) {
            classes[suffixes[i]] = classes[suffixes[i - 1]]

            if (s[suffixes[i]] != s[suffixes[i - 1]])
                classes[suffixes[i]]++
        }
        var currClass = classes[suffixes.last()] + 1

        val tempArray = IntArray(length)

        var level = 1
        while (level < length) {
            cnt.fill(0, toIndex = length)

            classes.forEach { cnt[it]++ }

            countSort(cnt, currClass)

            for (i in 0 until length) {
                val pos = (suffixes[i] + length - level ) % length
                tempArray[cnt[classes[pos]]++] = pos
            }

            tempArray.indices.forEach {
                suffixes[it] = tempArray[it]
            }

            tempArray[suffixes.first()] = 0

            for (i in 1 until length) {
                tempArray[suffixes[i]] = tempArray[suffixes[i - 1]]

                if (classes[suffixes[i]] != classes[suffixes[i - 1]]
                        || classes[(suffixes[i] + level) % length] != classes[(suffixes[i - 1] + level) % length])
                    tempArray[suffixes[i]]++
            }

            currClass = tempArray[suffixes.last()] + 1

            tempArray.indices.forEach {
                classes[it] = tempArray[it]
            }

            level *= 2
        }

        val rp = IntArray(length)

        for (i in 0 until length) {
            rp[suffixes[i]] = i
        }

        var x = 0

        for (index in rp) {
            if (index != 0) {
                x = Math.max(x - 1, 0)

                while (suffixes[index] + x < length && suffixes[index - 1] + x < length &&
                        s[suffixes[index] + x] == s[suffixes[index - 1] + x])
                    x++

                lcp[index - 1] = x
            }
        }
    }
}

fun main(args: Array<String>) {
    val scanner = FastReader("refrain.in")
    val n = scanner.nextInt()
    val m = scanner.nextInt()
    val sa = SuffixArray(buildString { (0 until n).forEach { append(scanner.nextInt()) } } + ('0'.toInt() - 1).toChar(), m)

    val writer = FastWriter("refrain.out")

    val res = sa.refrain()

    writer.println(sa.maxWeight * sa.maxLength)
    writer.println(sa.maxLength)

    writer.println(res)

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
