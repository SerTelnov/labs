// Generated from C:/pro/homework/labs/translation-methods/parsergenerator/src/main/antlr4\GrammarParser.g4 by ANTLR 4.7

    package ru.telnov.labs.translationmethods.parsergenerator;

    import ru.telnov.labs.translationmethods.parsergenerator.generator.builders.Arg;
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
	 * Enter a parse tree produced by {@link GrammarParser#args}.
	 * @param ctx the parse tree
	 */
	void enterArgs(GrammarParser.ArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#args}.
	 * @param ctx the parse tree
	 */
	void exitArgs(GrammarParser.ArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#arg}.
	 * @param ctx the parse tree
	 */
	void enterArg(GrammarParser.ArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#arg}.
	 * @param ctx the parse tree
	 */
	void exitArg(GrammarParser.ArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(GrammarParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(GrammarParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(GrammarParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(GrammarParser.NameContext ctx);
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
	 * Enter a parse tree produced by {@link GrammarParser#rules}.
	 * @param ctx the parse tree
	 */
	void enterRules(GrammarParser.RulesContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#rules}.
	 * @param ctx the parse tree
	 */
	void exitRules(GrammarParser.RulesContext ctx);
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