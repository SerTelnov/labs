package parser

import parser.exception.ParserException
import parser.exception.UnexpectedActionException
import org.junit.Assert.assertEquals
import org.junit.Test

class ParserTest {

    private val parser = Parser()

    @Test
    fun orTest() {
        val input = "a|a"
        val tree = parser.parse(input)

        assertEquals(input, tree.toString())
    }

    @Test
    fun xorTest() {
        val input = "a^a"
        val tree = parser.parse(input)

        assertEquals(input, tree.toString())
    }

    @Test
    fun andTest() {
        val input = "a&a"
        val tree = parser.parse(input)

        assertEquals(input, tree.toString())
    }

    @Test
    fun inputTest() {
        val input = " (!a | b) & a & (a | !(b ^ c))"
        val tree = parser.parse(input)

        assertEquals(input.replace(" ", "")
                .replace("[a-z]".toRegex(), "a"), tree.toString())
    }

    @Test
    fun negateTest() {
        val input = "a&!a"
        val tree = parser.parse(input)
        assertEquals(input, tree.toString())
    }

    @Test
    fun whitespaceTest() {
        val input = "a         \n  \t\r          &     \n        a"
        val tree = parser.parse(input)
        assertEquals("a&a", tree.toString())
    }

    @Test(expected = ParserException::class)
    fun invalidToken() {
        parser.parse("a@a")
    }

    @Test(expected = ParserException::class)
    fun invalidBracketSequenceTest() {
        parser.parse("((a&a)")
    }

    @Test(expected = UnexpectedActionException::class)
    fun invalidExpression() {
        parser.parse("aa")
    }
}