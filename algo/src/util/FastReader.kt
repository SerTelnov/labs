package util

import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.*

/**
 * Created by Telnov Sergey on 16.03.2018.
 */

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
