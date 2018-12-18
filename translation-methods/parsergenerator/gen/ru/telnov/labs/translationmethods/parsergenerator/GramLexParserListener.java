// Generated from C:/pro/homework/labs/translation-methods/parsergenerator/src/main/antlr4\GramLexParser.g4 by ANTLR 4.7

    package ru.telnov.labs.translationmethods.parsergenerator;

    import ru.telnov.labs.translationmethods.parsergenerator.tokens.Terminal;
    import java.util.Collections;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GramLexParser}.
 */
public interface GramLexParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GramLexParser#parseTokens}.
	 * @param ctx the parse tree
	 */
	void enterParseTokens(GramLexParser.ParseTokensContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramLexParser#parseTokens}.
	 * @param ctx the parse tree
	 */
	void exitParseTokens(GramLexParser.ParseTokensContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramLexParser#terminal}.
	 * @param ctx the parse tree
	 */
	void enterTerminal(GramLexParser.TerminalContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramLexParser#terminal}.
	 * @param ctx the parse tree
	 */
	void exitTerminal(GramLexParser.TerminalContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramLexParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(GramLexParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramLexParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(GramLexParser.ValueContext ctx);
}