package parser

import parser.exception.ParserException
import parser.exception.UnexpectedActionException
import parser.tree.Tree
import java.nio.file.Files
import java.nio.file.Path
import java.util.function.Supplier
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

        val tree = expression()
        if (lexer.hasNext()) {
            throw ParserException(input, "didn't parse all expression", lexer.getIndex())
        }
        return tree
    }

    private fun expression() = notTerminal("E", arrayOf(Token.END),
            arrayOf(Supplier { or() }, Supplier { subExpression() }))

    private fun subExpression(): Tree {
        val curr = Tree("E'")
        when (lexer.getCurrToken()) {
            Token.OR -> {
                putTerminal(curr, "|")
                curr.addChild(or())
                curr.addChild(subExpression())
            }

            Token.CLOSE_BRACKET, Token.END -> {
            }
            else -> throw UnexpectedActionException(lexer.getCurrToken(), curr.name)
        }
        return curr
    }

    private fun or() = notTerminal("O", arrayOf(Token.END, Token.OR),
            arrayOf(Supplier { xor() }, Supplier { subOr() }))

    private fun subOr(): Tree {
        val curr = Tree("O'")
        when (lexer.getCurrToken()) {
            Token.XOR -> {
                putTerminal(curr, "^")
                curr.addChild(xor())
                curr.addChild(subOr())
            }

            Token.OR, Token.CLOSE_BRACKET, Token.END -> {
            }
            else -> throw UnexpectedActionException(lexer.getCurrToken(), curr.name)
        }
        return curr
    }

    private fun xor() = notTerminal("X", arrayOf(Token.XOR, Token.OR, Token.END),
            arrayOf(Supplier { and() }, Supplier { subXor() }))

    private fun subXor(): Tree {
        val curr = Tree("X'")
        when (lexer.getCurrToken()) {
            Token.AND -> {
                putTerminal(curr, "&")
                curr.addChild(and())
                curr.addChild(subXor())
            }

            Token.XOR, Token.OR, Token.CLOSE_BRACKET, Token.END -> {
            }
            else -> throw UnexpectedActionException(lexer.getCurrToken(), curr.name)
        }
        return curr
    }

    private fun and() = notTerminal("A",
                arrayOf(Token.AND, Token.XOR, Token.OR, Token.END),
                arrayOf(Supplier { shift() }, Supplier { subAnd() }))

    private fun subAnd(): Tree {
        val curr = Tree("A'")
        when (lexer.getCurrToken()) {
            Token.LEFT_SHIFT, Token.RIGHT_SHIFT -> {
                putTerminal(curr, lexer.getCurrToken().toString())
                curr.addChild(shift())
                curr.addChild(subAnd())
            }

            Token.AND, Token.XOR, Token.OR, Token.CLOSE_BRACKET, Token.END -> {
            }
            else -> throw UnexpectedActionException(lexer.getCurrToken(), curr.name)
        }
        return curr
    }

    private fun shift(): Tree {
        val curr = Tree("S")
        when (lexer.getCurrToken()) {
            Token.VARIABLE -> putTerminal(curr, "a")
            Token.OPEN_BRACKET -> {
                putTerminal(curr, "(")
                curr.addChild(expression())

                test(Token.CLOSE_BRACKET)
                putTerminal(curr, ")")
            }
            Token.NEGATE -> {
                putTerminal(curr, "!")
                curr.addChild(expression())
            }

            Token.XOR, Token.OR, Token.AND, Token.END -> {
            }
            else -> throw UnexpectedActionException(lexer.getCurrToken(), curr.name)
        }
        return curr
    }

    private fun notTerminal(name: String, follow: Array<Token>,
                            nextNotTerminals: Array<Supplier<Tree>>): Tree {
        val curr = Tree(name)
        val currToken = lexer.getCurrToken()
        if (currToken in arrayOf(Token.VARIABLE, Token.OPEN_BRACKET, Token.NEGATE)) {
            nextNotTerminals.forEach {
                curr.addChild(it.get())
            }
        } else if (currToken !in follow) {
            throw UnexpectedActionException(lexer.getCurrToken(), curr.name)
        }

        return curr
    }

    private fun putTerminal(parent: Tree, terminal: String) {
        parent.addChild(Tree(terminal))

        val prev = lexer.getCurrToken()
        val curr = lexer.nextToken()

        if ((lexer.hasNext() || prev.isBinOperation()) && curr.isEnd()) {
            throw ParserException(input, "missed expression", lexer.getIndex())
        } else if (prev.isBinOperation() && curr.isBinOperation()) {
            throw ParserException(input, "unexpected token: '$curr'", lexer.getIndex())
        } else if (prev.isOpenBracket() && curr.isCloseBracket()) {
            throw ParserException(input, "missed expression in brackets", lexer.getIndex() - 1)
        }
    }

    private fun test(token: Token) {
        if (token != lexer.getCurrToken()) {
            throw ParserException(input, "expected: '$token'", lexer.getIndex())
        }
    }
}
