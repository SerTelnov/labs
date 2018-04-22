package flow.g

import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import kotlin.collections.ArrayList

class TestMaker(val n: Int, val m: Int) {

    private val field = Array(n) { CharArray(m) }
    private val random = Random()
    private lateinit var startCell: Cell
    private lateinit var endCell: Cell

    private lateinit var cells: ArrayList<Pair<Int, Int>>
    private lateinit var stopZone: ArrayList<Cell>
    private lateinit var noBuildZone: ArrayList<Cell>
    private lateinit var buildZone: ArrayList<Cell>

    private fun getCount(count: Int) = Math.abs(random.nextInt() % count) / 2

    fun generateTest() {
        var count = n * m
        cells = ArrayList(count)
        for (i in 0 until n) {
            for (j in 0 until m) {
                cells.add(Pair(i, j))
            }
        }
        cells.shuffle()

        startCell = Cell(cells[0].first, cells[0].second)
        field[cells[0].first][cells[0].second] = 'A'

        endCell = Cell(cells[1].first, cells[1].second)
        field[cells[1].first][cells[1].second] = 'B'

        count -= 2
        val stopZoneCount = getCount(count)
        count -= stopZoneCount

        stopZone = ArrayList(stopZoneCount)
        for (i in 0 until stopZoneCount) {
            val (x, y) = cells[i + 2]
            stopZone.add(Cell(x, y))
            field[x][y] = '#'
        }

        val noBuildZoneCount = getCount(count)
        count -= noBuildZoneCount

        noBuildZone = ArrayList(noBuildZoneCount)
        for (i in 0 until noBuildZoneCount) {
            val (x, y) = cells[i + 2 + stopZoneCount]
            noBuildZone.add(Cell(x, y, -1))
            field[x][y] = '-'
        }

        buildZone = ArrayList(count)
        for (i in 0 until count) {
            val (x, y) = cells[i + 2 + stopZoneCount + noBuildZoneCount]
            buildZone.add(Cell(x, y, 1))
            field[x][y] = '.'
        }
    }

    fun printTest() {
        val path = Paths.get("C:\\pro\\homework\\labs\\algo\\src\\flow\\g\\test.in")

        val writer = Files.newBufferedWriter(path)
        writer.write("$n $m\n")
        for (i in 0 until n) {
            writer.write(field[i])
            writer.write("\n")
        }
        writer.close()
    }

    data class Cell(val x: Int, val y: Int, val value: Int = Int.MAX_VALUE)
}

fun main(args: Array<String>) {
    val maker = TestMaker(50, 50)
    maker.generateTest()
    maker.printTest()
}