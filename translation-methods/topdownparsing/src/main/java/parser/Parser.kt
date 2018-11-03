package parser

import parser.exception.ParserException
import parser.exception.UnexpectedActionException
import parser.tree.Tree
import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Collectors

class Parser {

    private lateinit var lexer: LexicalAnalyzer
    private lateinit var input: String
    private var bracketBalance = 0

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
        bracketBalance = 0
        lexer.nextToken()

        val tree = expression()
        if (lexer.hasNext()) {
            throw ParserException(input, "didn't parse all expression", lexer.getIndex())
        }
        return tree
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

        val prev = lexer.getCurrToken()
        val curr = lexer.nextToken()

        if (prev.isOpenBracket()) {
            bracketBalance++
        } else if (curr.isCloseBracket()) {
            if (bracketBalance == 0) {
                throw ParserException(input, "incorrect bracket sequence", lexer.getIndex())
            }
            bracketBalance--
        }

        if ((lexer.hasNext() || prev.isBinOperation()) && curr.isEnd()) {
            throw ParserException(input, "missed expression", lexer.getIndex())
        } else if (prev.isBinOperation() && curr.isBinOperation()) {
            throw ParserException(input, "unexpected token: '$curr'", lexer.getIndex())
        } else if (prev.isOpenBracket() && curr.isCloseBracket()) {
            throw ParserException(input, "missed expression in brackets", lexer.getIndex() - 1)
        }
    }

    private fun test(token: Token): Boolean {
        if (token != lexer.getCurrToken()) {
            throw ParserException(input, "expected: ')'", lexer.getIndex())
        }
        return true
    }
}