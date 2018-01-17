package matroids.A

import java.io.*
import java.util.*

/**
 * Created by Telnov Sergey on 15.12.2017.
 */

private class Task(val deadline: Long, val fine: Long)

fun main(args: Array<String>) {
    val input = FastReader("schedule.in")

    val n = input.nextInt()

    val schedule = Array(n) {
        Task(input.nextLong(), input.nextLong())
    }

    schedule.sortWith(compareBy(Task::deadline, Task::fine))

    var counterOfFines = 0L
    var index = 0
    while (index != schedule.size && schedule[index].deadline == 0L) {
        counterOfFines += schedule[index].fine
        index++
    }

    val queue = PriorityQueue<Long>()

    var numberOfCompletedTasks = 0

    for (i in index until schedule.size) {
        val curText = schedule[i]
        queue.add(curText.fine)
        if (curText.deadline > numberOfCompletedTasks) {
            numberOfCompletedTasks++
        } else {
            counterOfFines += queue.poll()
        }
    }

    val output = FastWriter("schedule.out")
    output.println(counterOfFines)
    output.close()
}

internal class FastReader {
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
internal class FastWriter {

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
