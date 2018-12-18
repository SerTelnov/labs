// Generated from C:/pro/homework/labs/translation-methods/parsergenerator/src/main/antlr4\GrammarParser.g4 by ANTLR 4.7

    package ru.telnov.labs.translationmethods.parsergenerator;

    import ru.telnov.labs.translationmethods.parsergenerator.tokens.*;

    import java.util.Collections;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GrammarParser}.
 */
public interface GrammarParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GrammarParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(GrammarParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(GrammarParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#notTerminal}.
	 * @param ctx the parse tree
	 */
	void enterNotTerminal(GrammarParser.NotTerminalContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#notTerminal}.
	 * @param ctx the parse tree
	 */
	void exitNotTerminal(GrammarParser.NotTerminalContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#initRules}.
	 * @param ctx the parse tree
	 */
	void enterInitRules(GrammarParser.InitRulesContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#initRules}.
	 * @param ctx the parse tree
	 */
	void exitInitRules(GrammarParser.InitRulesContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#rrule}.
	 * @param ctx the parse tree
	 */
	void enterRrule(GrammarParser.RruleContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#rrule}.
	 * @param ctx the parse tree
	 */
	void exitRrule(GrammarParser.RruleContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#nextToken}.
	 * @param ctx the parse tree
	 */
	void enterNextToken(GrammarParser.NextTokenContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#nextToken}.
	 * @param ctx the parse tree
	 */
	void exitNextToken(GrammarParser.NextTokenContext ctx);
}