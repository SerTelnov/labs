package util.io.kotlin

import java.io.BufferedWriter
import java.io.Closeable
import java.io.FileOutputStream
import java.nio.file.Files
import java.nio.file.Paths
import java.io.OutputStreamWriter

/**
 * Created by Telnov Sergey on 16.03.2018.
 */

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
