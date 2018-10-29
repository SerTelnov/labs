package parser

import parser.exception.ParserException
import parser.exception.UnexpectedActionException
import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Collectors

class Parser {

    private lateinit var lexer: LexicalAnalyzer
    private lateinit var input: String

    fun parsePath(path: Path): List<Tree> {
        return Files.lines(path)
                .map { parse(it) }
                .collect(Collectors.toList())
    }

    fun parse(input: String): Tree {
        this.input = input
        lexer = LexicalAnalyzer(input)
        return parse()
    }

    private fun parse(): Tree {
        lexer.nextToken()
        return expression()
    }

    private fun expression(): Tree {
        val curr = Tree("E")
        when (lexer.getCurrToken()) {
//            FIRST
            Token.VARIABLE, Token.OPEN_BRACKET, Token.NEGATE -> {
                curr.addChild(or())
                curr.addChild(subExpression())
            }
//            FOLLOW
            Token.CLOSE_BRACKET, Token.END -> {
            }
            else -> throw UnexpectedActionException(lexer.getCurrToken(), curr.name)
        }
        return curr
    }

    private fun subExpression(): Tree {
        val curr = Tree("E'")
        when (lexer.getCurrToken()) {
//            FIRST
            Token.OR -> {
                curr.addChild(Tree("|"))
                lexer.nextToken()
                curr.addChild(or())
                curr.addChild(subExpression())
            }
//            FOLLOW
            Token.CLOSE_BRACKET, Token.END -> {
            }
            else -> throw UnexpectedActionException(lexer.getCurrToken(), curr.name)
        }
        return curr
    }

    private fun or(): Tree {
        val curr = Tree("O")
        when (lexer.getCurrToken()) {
//            FIRST
            Token.VARIABLE, Token.OPEN_BRACKET, Token.NEGATE -> {
                curr.addChild(xor())
                curr.addChild(subOr())
            }
//            FOLLOW
            Token.OR, Token.CLOSE_BRACKET, Token.END -> {
            }
            else -> throw UnexpectedActionException(lexer.getCurrToken(), curr.name)
        }

        return curr
    }

    private fun subOr(): Tree {
        val curr = Tree("O'")
        when (lexer.getCurrToken()) {
//            FIRST
            Token.XOR -> {
                putTerminal(curr, "^")
                curr.addChild(xor())
                curr.addChild(subOr())
            }
//            FOLLOW
            Token.OR, Token.CLOSE_BRACKET, Token.END -> {
            }
            else -> throw UnexpectedActionException(lexer.getCurrToken(), curr.name)
        }
        return curr
    }

    private fun xor(): Tree {
        val curr = Tree("X")
        when (lexer.getCurrToken()) {
//            FIRST
            Token.VARIABLE, Token.OPEN_BRACKET, Token.NEGATE -> {
                curr.addChild(and())
                curr.addChild(subXor())
            }
//            FOLLOW
            Token.XOR, Token.OR, Token.END, Token.CLOSE_BRACKET -> {
            }

            else -> throw UnexpectedActionException(lexer.getCurrToken(), curr.name)
        }

        return curr
    }

    private fun subXor(): Tree {
        val curr = Tree("X'")
        when (lexer.getCurrToken()) {
//            FIRST
            Token.AND -> {
                putTerminal(curr, "&")
                curr.addChild(and())
                curr.addChild(subXor())
            }
//            FOLLOW
            Token.XOR, Token.OR, Token.END, Token.CLOSE_BRACKET -> {
            }

            else -> throw UnexpectedActionException(lexer.getCurrToken(), curr.name)
        }
        return curr
    }

    private fun and(): Tree {
        val curr = Tree("A")
        when (lexer.getCurrToken()) {
//            FIRST
            Token.VARIABLE -> putTerminal(curr, "a")
            Token.OPEN_BRACKET -> {
                putTerminal(curr, "(")
                curr.addChild(expression())

                if (test(Token.CLOSE_BRACKET)) {
                    putTerminal(curr, ")")
                }
            }
            Token.NEGATE -> {
                putTerminal(curr, "!")
                curr.addChild(expression())
            }
//            FOLLOW
            Token.XOR, Token.OR, Token.AND, Token.END -> {
            }
            else -> throw UnexpectedActionException(lexer.getCurrToken(), curr.name)
        }
        return curr
    }

    private fun putTerminal(parent: Tree, terminal: String) {
        parent.addChild(Tree(terminal))
        lexer.nextToken()
    }

    private fun test(token: Token): Boolean {
        if (token != lexer.getCurrToken()) {
            val message = buildString {
                append('\n')
                val info = "expected: ')' in string: "
                append(info)
                append(input)
                append('\n')
                for (i in 0 until info.length + lexer.getIndex() - 1) {
                    append('~')
                }
                append('^')
            }
            throw ParserException(message)
        }
        return true
    }
}