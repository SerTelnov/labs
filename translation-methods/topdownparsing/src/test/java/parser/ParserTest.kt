package parser

import org.junit.Test
import parser.exception.ParserException
import parser.exception.UnexpectedActionException
import kotlin.test.assertEquals


class ParserTest {

    private val parser = Parser()

    @Test
    fun simpleTest() {
        assertEquals("a", parser.parse("a").toString())
    }

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
    fun invalidBracketSequenceTest01() {
        parser.parse("(a")
    }

    @Test(expected = ParserException::class)
    fun invalidBracketSequenceTest02() {
        parser.parse(")a")
    }

    @Test(expected = ParserException::class)
    fun invalidBracketSequenceTest03() {
        parser.parse("((a)")
    }

    @Test(expected = ParserException::class)
    fun invalidBracketSequenceTest04() {
        parser.parse("a)")
    }

    @Test(expected = UnexpectedActionException::class)
    fun invalidExpression01() {
        parser.parse("aa")
    }

    @Test(expected = ParserException::class)
    fun invalidExpression02() {
        parser.parse("a&")
    }

    @Test(expected = UnexpectedActionException::class)
    fun invalidExpression03() {
        parser.parse("&a")
    }

    @Test(expected = ParserException::class)
    fun invalidExpression04() {
        parser.parse("()")
    }

    @Test(expected = ParserException::class)
    fun invalidExpression05() {
        parser.parse("a&^|")
    }
}