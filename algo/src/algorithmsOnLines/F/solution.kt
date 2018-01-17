package algorithmsOnLines.F

import java.io.*
import java.util.*

/**
 * Created by Telnov Sergey on 22.12.2017.
 */

data class SuffixArray(val lines: Array<String>) {
    val ALPHABET_SIZE = 30
    val NEUTRAL_CHAR = 'a'.toInt() - 1

    val s = buildString {
        for (i in 0 until lines.size - 1) {
            append(lines[i])
            append(('z'.toInt() + 1).toChar())
//            append(NEUTRAL_CHAR.toChar())
        }
        append(lines.last())
        append(NEUTRAL_CHAR.toChar())
    }

    val length = s.length
    val suffixes = IntArray(length)
    val lcp = IntArray(length)

    fun getCommonLine(): String {
        val mask = IntArray(lines.size) { 0 }

        fun getLineIndex(index: Int) : Int {
            var length = 0

            for (i in lines.indices) {
                length += lines[i].length + 1
                if (index < length) {
                    return i
                }
            }

            return -1
        }

        fun maskIsFilled(): Boolean {
            mask.forEach {
                if (it != 1)
                    return false
            }
            return true
        }

        val lastLinesIndices = ArrayDeque<Int>(lines.size)

        for (i in 1..lines.size) {
            val currLineIndex = getLineIndex(suffixes[i])

            mask[currLineIndex]++
            lastLinesIndices.add(currLineIndex)
        }

        var maxLCP = -1
        var lineIndex = 0

        if (maskIsFilled()) {
            var initLCP = Int.MAX_VALUE
            var initLineIndex = 0
            (1 until lines.size).forEach {
                if (initLCP > lcp[it]) {
                    initLCP = lcp[it]
                    initLineIndex = suffixes[it]
                }
            }
            maxLCP = initLCP
            lineIndex = initLineIndex
        }

        for (i in lines.size + 1 until suffixes.size) {
            mask[lastLinesIndices.pollFirst()]--

            val currLineIndex = getLineIndex(suffixes[i])
            mask[currLineIndex]++
            lastLinesIndices.add(currLineIndex)

            if (maskIsFilled()) {
                var currLCP = Int.MAX_VALUE
                var currSuffixIndex = 0
                (i - lines.size + 1 until i).forEach {
                    if (currLCP > lcp[it]) {
                        currLCP = lcp[it]
                        currSuffixIndex = suffixes[it]
                    }
                }

                if (maxLCP <= currLCP) {
                    maxLCP = currLCP
                    lineIndex = currSuffixIndex
                }
            }
        }
        return if (maxLCP != -1) s.substring(lineIndex, lineIndex + maxLCP) else ""
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
    val scanner = FastReader("substr3.in")

    val k = scanner.nextInt()

    val writer = FastWriter("substr3.out")
    writer.println(if (k == 1) {
        scanner.next()
    } else {
        val lines = Array(k) { scanner.next() }
        val sa = SuffixArray(lines)
        sa.getCommonLine()
    })
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
