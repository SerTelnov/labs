package algorithmsOnLines

/**
 * Created by Telnov Sergey on 07.12.2017.
 */


class SuffixArray(s: String) {
    val ALPHABET_SIZE = 29
    val data = Array(s.length) {
        s[it] - 'a' + 1
    }

    val suffix = {
        val count = Array(Math.max(data.size, ALPHABET_SIZE), {0})
        data.forEach {
            count[it]++
        }

        for (i in 1 until data.size) {
            count[i] = count[i - 1]
        }

        val result = Array(data.size) { 0 }
    }
}