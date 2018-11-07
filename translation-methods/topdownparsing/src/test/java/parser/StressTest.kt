package parser

import org.junit.Test
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.test.assertEquals

class StressTest {

    @Test
    fun test() {
        for (id in 1..10) {
            val path = Paths.get("src/test/java/parser/tests/test$id.txt")
            val list = Files.readAllLines(path)

            val parser = Parser()
            val parserList = parser.parsePath(path).map { it -> it.toString() }

            assertEquals(list, parserList)
            println("test$id: OK")
        }
    }
}
