// Generated from C:/pro/homework/labs/translation-methods/ANTLR/src/main/antlr4\LiteJavaParser2.g4 by ANTLR 4.7

    package ru.telnov.labs.translationmethods.antlr.gen;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link LiteJavaParser2}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface LiteJavaParser2Visitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link LiteJavaParser2#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(LiteJavaParser2.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link LiteJavaParser2#classDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDeclaration(LiteJavaParser2.ClassDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link LiteJavaParser2#methods}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethods(LiteJavaParser2.MethodsContext ctx);
	/**
	 * Visit a parse tree produced by {@link LiteJavaParser2#method}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethod(LiteJavaParser2.MethodContext ctx);
	/**
	 * Visit a parse tree produced by {@link LiteJavaParser2#methodInit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodInit(LiteJavaParser2.MethodInitContext ctx);
	/**
	 * Visit a parse tree produced by {@link LiteJavaParser2#methodArgs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodArgs(LiteJavaParser2.MethodArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link LiteJavaParser2#methodArg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodArg(LiteJavaParser2.MethodArgContext ctx);
	/**
	 * Visit a parse tree produced by {@link LiteJavaParser2#body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBody(LiteJavaParser2.BodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link LiteJavaParser2#statements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatements(LiteJavaParser2.StatementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link LiteJavaParser2#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(LiteJavaParser2.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link LiteJavaParser2#loop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoop(LiteJavaParser2.LoopContext ctx);
	/**
	 * Visit a parse tree produced by {@link LiteJavaParser2#loopInit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoopInit(LiteJavaParser2.LoopInitContext ctx);
	/**
	 * Visit a parse tree produced by {@link LiteJavaParser2#forInit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForInit(LiteJavaParser2.ForInitContext ctx);
	/**
	 * Visit a parse tree produced by {@link LiteJavaParser2#forStep}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStep(LiteJavaParser2.ForStepContext ctx);
	/**
	 * Visit a parse tree produced by {@link LiteJavaParser2#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(LiteJavaParser2.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LiteJavaParser2#conditionInit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionInit(LiteJavaParser2.ConditionInitContext ctx);
	/**
	 * Visit a parse tree produced by {@link LiteJavaParser2#atom1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom1(LiteJavaParser2.Atom1Context ctx);
	/**
	 * Visit a parse tree produced by {@link LiteJavaParser2#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(LiteJavaParser2.AtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link LiteJavaParser2#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(LiteJavaParser2.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link LiteJavaParser2#initExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitExpr(LiteJavaParser2.InitExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link LiteJavaParser2#plusMinus}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlusMinus(LiteJavaParser2.PlusMinusContext ctx);
	/**
	 * Visit a parse tree produced by {@link LiteJavaParser2#mulExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulExpr(LiteJavaParser2.MulExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link LiteJavaParser2#mulDiv}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDiv(LiteJavaParser2.MulDivContext ctx);
	/**
	 * Visit a parse tree produced by {@link LiteJavaParser2#var}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar(LiteJavaParser2.VarContext ctx);
	/**
	 * Visit a parse tree produced by {@link LiteJavaParser2#initVariable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitVariable(LiteJavaParser2.InitVariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link LiteJavaParser2#initString}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitString(LiteJavaParser2.InitStringContext ctx);
	/**
	 * Visit a parse tree produced by {@link LiteJavaParser2#numPrim}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumPrim(LiteJavaParser2.NumPrimContext ctx);
	/**
	 * Visit a parse tree produced by {@link LiteJavaParser2#boolBinOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolBinOp(LiteJavaParser2.BoolBinOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link LiteJavaParser2#modifiers}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModifiers(LiteJavaParser2.ModifiersContext ctx);
}