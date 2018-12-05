// Generated from C:/pro/homework/labs/translation-methods/ANTLR/src/main/antlr4\LiteJavaParser2.g4 by ANTLR 4.7

    package ru.telnov.labs.translationmethods.antlr.gen;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;
import ru.telnov.labs.translationmethods.antlr.tokens.ClassToken;
import ru.telnov.labs.translationmethods.antlr.tokens.Expression;
import ru.telnov.labs.translationmethods.antlr.tokens.Method;
import ru.telnov.labs.translationmethods.antlr.tokens.Statement;
import ru.telnov.labs.translationmethods.antlr.tokens.condition.BinConditionExpression;
import ru.telnov.labs.translationmethods.antlr.tokens.condition.Condition;
import ru.telnov.labs.translationmethods.antlr.tokens.expressions.*;
import ru.telnov.labs.translationmethods.antlr.tokens.expressions.Number;
import ru.telnov.labs.translationmethods.antlr.tokens.loop.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LiteJavaParser2 extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		LPAREN=1, RPAREN=2, LBRACE=3, RBRACE=4, LBRACK=5, RBRACK=6, SEMI=7, COMMA=8, 
		DOT=9, MARK=10, ARRAY=11, ASSIGN=12, GT=13, LT=14, BANG=15, TILDE=16, 
		QUESTION=17, COLON=18, EQUAL=19, LE=20, GE=21, NOTEQUAL=22, AND=23, OR=24, 
		PLUS=25, MINUS=26, DIV=27, MUL=28, CLASS=29, STATIC=30, VOID=31, MAIN=32, 
		STRING=33, SYSTEM=34, OUT=35, PRINTLN=36, IF=37, ELSE=38, FOR=39, WHILE=40, 
		INT=41, LONG=42, SHORT=43, PUBLIC=44, PROTECTED=45, PRIVATE=46, WS=47, 
		COMMENT=48, LINE_COMMENT=49, UN_OPERATORS=50, UN_ASSIGN_OPERATORS=51, 
		DIGIT=52, VARIABLE=53;
	public static final int
		RULE_start = 0, RULE_classDeclaration = 1, RULE_methods = 2, RULE_method = 3, 
		RULE_methodInit = 4, RULE_methodArgs = 5, RULE_methodArg = 6, RULE_body = 7, 
		RULE_statements = 8, RULE_statement = 9, RULE_loop = 10, RULE_loopInit = 11, 
		RULE_forInit = 12, RULE_forStep = 13, RULE_condition = 14, RULE_conditionInit = 15, 
		RULE_atom1 = 16, RULE_atom = 17, RULE_expr = 18, RULE_initExpr = 19, RULE_plusMinus = 20, 
		RULE_mulExpr = 21, RULE_mulDiv = 22, RULE_var = 23, RULE_initVariable = 24, 
		RULE_initString = 25, RULE_numPrim = 26, RULE_boolBinOp = 27, RULE_modifiers = 28;
	public static final String[] ruleNames = {
		"start", "classDeclaration", "methods", "method", "methodInit", "methodArgs", 
		"methodArg", "body", "statements", "statement", "loop", "loopInit", "forInit", 
		"forStep", "condition", "conditionInit", "atom1", "atom", "expr", "initExpr", 
		"plusMinus", "mulExpr", "mulDiv", "var", "initVariable", "initString", 
		"numPrim", "boolBinOp", "modifiers"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "'{'", "'}'", "'['", "']'", "';'", "','", "'.'", "'\"'", 
		"'[]'", "'='", "'>'", "'<'", "'!'", "'~'", "'?'", "':'", "'=='", "'<='", 
		"'>='", "'!='", "'&&'", "'||'", "'+'", "'-'", "'/'", "'*'", "'class'", 
		"'static'", "'void'", "'main'", "'String'", "'System'", "'out'", "'println'", 
		"'if'", "'else'", "'for'", "'while'", "'int'", "'long'", "'short'", "'public'", 
		"'protected'", "'private'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "LPAREN", "RPAREN", "LBRACE", "RBRACE", "LBRACK", "RBRACK", "SEMI", 
		"COMMA", "DOT", "MARK", "ARRAY", "ASSIGN", "GT", "LT", "BANG", "TILDE", 
		"QUESTION", "COLON", "EQUAL", "LE", "GE", "NOTEQUAL", "AND", "OR", "PLUS", 
		"MINUS", "DIV", "MUL", "CLASS", "STATIC", "VOID", "MAIN", "STRING", "SYSTEM", 
		"OUT", "PRINTLN", "IF", "ELSE", "FOR", "WHILE", "INT", "LONG", "SHORT", 
		"PUBLIC", "PROTECTED", "PRIVATE", "WS", "COMMENT", "LINE_COMMENT", "UN_OPERATORS", 
		"UN_ASSIGN_OPERATORS", "DIGIT", "VARIABLE"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "LiteJavaParser2.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public LiteJavaParser2(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class StartContext extends ParserRuleContext {
		public ClassToken cl;
		public ClassDeclarationContext classDeclaration;
		public ClassDeclarationContext classDeclaration() {
			return getRuleContext(ClassDeclarationContext.class,0);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).exitStart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LiteJavaParser2Visitor) return ((LiteJavaParser2Visitor<? extends T>)visitor).visitStart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			((StartContext)_localctx).classDeclaration = classDeclaration();
			 ((StartContext)_localctx).cl =  ((StartContext)_localctx).classDeclaration.cl;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassDeclarationContext extends ParserRuleContext {
		public ClassToken cl;
		public Token VARIABLE;
		public MethodsContext methods;
		public TerminalNode CLASS() { return getToken(LiteJavaParser2.CLASS, 0); }
		public TerminalNode VARIABLE() { return getToken(LiteJavaParser2.VARIABLE, 0); }
		public TerminalNode LBRACE() { return getToken(LiteJavaParser2.LBRACE, 0); }
		public MethodsContext methods() {
			return getRuleContext(MethodsContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(LiteJavaParser2.RBRACE, 0); }
		public ClassDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).enterClassDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).exitClassDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LiteJavaParser2Visitor) return ((LiteJavaParser2Visitor<? extends T>)visitor).visitClassDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassDeclarationContext classDeclaration() throws RecognitionException {
		ClassDeclarationContext _localctx = new ClassDeclarationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_classDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			match(CLASS);
			setState(62);
			((ClassDeclarationContext)_localctx).VARIABLE = match(VARIABLE);
			setState(63);
			match(LBRACE);
			setState(64);
			((ClassDeclarationContext)_localctx).methods = methods();
			setState(65);
			match(RBRACE);
			 ((ClassDeclarationContext)_localctx).cl =  new ClassToken((((ClassDeclarationContext)_localctx).VARIABLE!=null?((ClassDeclarationContext)_localctx).VARIABLE.getText():null));
			 _localctx.cl.setMethods(((ClassDeclarationContext)_localctx).methods.ms);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodsContext extends ParserRuleContext {
		public List<Method> ms;
		public MethodContext method;
		public List<MethodContext> method() {
			return getRuleContexts(MethodContext.class);
		}
		public MethodContext method(int i) {
			return getRuleContext(MethodContext.class,i);
		}
		public MethodsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methods; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).enterMethods(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).exitMethods(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LiteJavaParser2Visitor) return ((LiteJavaParser2Visitor<? extends T>)visitor).visitMethods(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodsContext methods() throws RecognitionException {
		MethodsContext _localctx = new MethodsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_methods);
		int _la;
		try {
			setState(81);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VOID:
			case PUBLIC:
			case PROTECTED:
			case PRIVATE:
				enterOuterAlt(_localctx, 1);
				{
				setState(69);
				((MethodsContext)_localctx).method = method();
				 ((MethodsContext)_localctx).ms =  new ArrayList<>();
				 _localctx.ms.add(((MethodsContext)_localctx).method.m);
				setState(77);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << VOID) | (1L << PUBLIC) | (1L << PROTECTED) | (1L << PRIVATE))) != 0)) {
					{
					{
					setState(72);
					((MethodsContext)_localctx).method = method();
					 _localctx.ms.add(((MethodsContext)_localctx).method.m);
					}
					}
					setState(79);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case RBRACE:
				enterOuterAlt(_localctx, 2);
				{
				 ((MethodsContext)_localctx).ms =  Collections.emptyList();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodContext extends ParserRuleContext {
		public Method m;
		public MethodInitContext methodInit;
		public BodyContext body;
		public MethodInitContext methodInit() {
			return getRuleContext(MethodInitContext.class,0);
		}
		public TerminalNode LBRACE() { return getToken(LiteJavaParser2.LBRACE, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(LiteJavaParser2.RBRACE, 0); }
		public MethodContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_method; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).enterMethod(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).exitMethod(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LiteJavaParser2Visitor) return ((LiteJavaParser2Visitor<? extends T>)visitor).visitMethod(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodContext method() throws RecognitionException {
		MethodContext _localctx = new MethodContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_method);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			((MethodContext)_localctx).methodInit = methodInit();
			setState(84);
			match(LBRACE);
			setState(85);
			((MethodContext)_localctx).body = body();
			setState(86);
			match(RBRACE);
			 ((MethodContext)_localctx).m =  ((MethodContext)_localctx).methodInit.m;
			 _localctx.m.setStatements(((MethodContext)_localctx).body.st);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodInitContext extends ParserRuleContext {
		public Method m;
		public Token VARIABLE;
		public ModifiersContext modifiers;
		public MethodArgsContext methodArgs;
		public TerminalNode PUBLIC() { return getToken(LiteJavaParser2.PUBLIC, 0); }
		public TerminalNode STATIC() { return getToken(LiteJavaParser2.STATIC, 0); }
		public TerminalNode VOID() { return getToken(LiteJavaParser2.VOID, 0); }
		public TerminalNode MAIN() { return getToken(LiteJavaParser2.MAIN, 0); }
		public TerminalNode LPAREN() { return getToken(LiteJavaParser2.LPAREN, 0); }
		public TerminalNode STRING() { return getToken(LiteJavaParser2.STRING, 0); }
		public TerminalNode ARRAY() { return getToken(LiteJavaParser2.ARRAY, 0); }
		public TerminalNode VARIABLE() { return getToken(LiteJavaParser2.VARIABLE, 0); }
		public TerminalNode RPAREN() { return getToken(LiteJavaParser2.RPAREN, 0); }
		public MethodArgsContext methodArgs() {
			return getRuleContext(MethodArgsContext.class,0);
		}
		public ModifiersContext modifiers() {
			return getRuleContext(ModifiersContext.class,0);
		}
		public MethodInitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodInit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).enterMethodInit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).exitMethodInit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LiteJavaParser2Visitor) return ((LiteJavaParser2Visitor<? extends T>)visitor).visitMethodInit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodInitContext methodInit() throws RecognitionException {
		MethodInitContext _localctx = new MethodInitContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_methodInit);
		int _la;
		try {
			setState(111);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(90);
				match(PUBLIC);
				setState(91);
				match(STATIC);
				setState(92);
				match(VOID);
				setState(93);
				match(MAIN);
				setState(94);
				match(LPAREN);
				setState(95);
				match(STRING);
				setState(96);
				match(ARRAY);
				setState(97);
				((MethodInitContext)_localctx).VARIABLE = match(VARIABLE);
				setState(98);
				match(RPAREN);
				 Method.Arg arg = new Method.Arg("String[]", (((MethodInitContext)_localctx).VARIABLE!=null?((MethodInitContext)_localctx).VARIABLE.getText():null));
				 ((MethodInitContext)_localctx).m =  new Method("public static", "void", "main", arg);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(102);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PUBLIC) | (1L << PROTECTED) | (1L << PRIVATE))) != 0)) {
					{
					setState(101);
					((MethodInitContext)_localctx).modifiers = modifiers();
					}
				}

				setState(104);
				match(VOID);
				setState(105);
				((MethodInitContext)_localctx).VARIABLE = match(VARIABLE);
				setState(106);
				match(LPAREN);
				setState(107);
				((MethodInitContext)_localctx).methodArgs = methodArgs();
				setState(108);
				match(RPAREN);
				 ((MethodInitContext)_localctx).m =  new Method(((MethodInitContext)_localctx).modifiers.s, "void", (((MethodInitContext)_localctx).VARIABLE!=null?((MethodInitContext)_localctx).VARIABLE.getText():null), ((MethodInitContext)_localctx).methodArgs.args);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodArgsContext extends ParserRuleContext {
		public List<Method.Arg> args;
		public MethodArgContext methodArg;
		public MethodArgContext methodArg() {
			return getRuleContext(MethodArgContext.class,0);
		}
		public MethodArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodArgs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).enterMethodArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).exitMethodArgs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LiteJavaParser2Visitor) return ((LiteJavaParser2Visitor<? extends T>)visitor).visitMethodArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodArgsContext methodArgs() throws RecognitionException {
		MethodArgsContext _localctx = new MethodArgsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_methodArgs);
		try {
			setState(117);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
			case LONG:
				enterOuterAlt(_localctx, 1);
				{
				setState(113);
				((MethodArgsContext)_localctx).methodArg = methodArg();
				 ((MethodArgsContext)_localctx).args =  ((MethodArgsContext)_localctx).methodArg.args;
				}
				break;
			case RPAREN:
				enterOuterAlt(_localctx, 2);
				{
				 ((MethodArgsContext)_localctx).args =  Collections.emptyList();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodArgContext extends ParserRuleContext {
		public List<Method.Arg> args;
		public NumPrimContext numPrim;
		public Token VARIABLE;
		public MethodArgContext methodArg;
		public NumPrimContext numPrim() {
			return getRuleContext(NumPrimContext.class,0);
		}
		public TerminalNode VARIABLE() { return getToken(LiteJavaParser2.VARIABLE, 0); }
		public TerminalNode COMMA() { return getToken(LiteJavaParser2.COMMA, 0); }
		public MethodArgContext methodArg() {
			return getRuleContext(MethodArgContext.class,0);
		}
		public MethodArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodArg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).enterMethodArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).exitMethodArg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LiteJavaParser2Visitor) return ((LiteJavaParser2Visitor<? extends T>)visitor).visitMethodArg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodArgContext methodArg() throws RecognitionException {
		MethodArgContext _localctx = new MethodArgContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_methodArg);
		try {
			setState(131);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(119);
				((MethodArgContext)_localctx).numPrim = numPrim();
				setState(120);
				((MethodArgContext)_localctx).VARIABLE = match(VARIABLE);
				setState(121);
				match(COMMA);
				setState(122);
				((MethodArgContext)_localctx).methodArg = methodArg();
				 ((MethodArgContext)_localctx).args =  new ArrayList<>();
				 _localctx.args.add(new Method.Arg(((MethodArgContext)_localctx).numPrim.t, (((MethodArgContext)_localctx).VARIABLE!=null?((MethodArgContext)_localctx).VARIABLE.getText():null)));
				 _localctx.args.addAll(((MethodArgContext)_localctx).methodArg.args);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(127);
				((MethodArgContext)_localctx).numPrim = numPrim();
				setState(128);
				((MethodArgContext)_localctx).VARIABLE = match(VARIABLE);
				 ((MethodArgContext)_localctx).args =  Collections.singletonList(new Method.Arg(((MethodArgContext)_localctx).numPrim.t, (((MethodArgContext)_localctx).VARIABLE!=null?((MethodArgContext)_localctx).VARIABLE.getText():null)));
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BodyContext extends ParserRuleContext {
		public List<Statement> st;
		public StatementsContext statements;
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public BodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).enterBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).exitBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LiteJavaParser2Visitor) return ((LiteJavaParser2Visitor<? extends T>)visitor).visitBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BodyContext body() throws RecognitionException {
		BodyContext _localctx = new BodyContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_body);
		try {
			setState(137);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
			case IF:
			case FOR:
			case WHILE:
			case INT:
			case LONG:
			case VARIABLE:
				enterOuterAlt(_localctx, 1);
				{
				setState(133);
				((BodyContext)_localctx).statements = statements();
				 ((BodyContext)_localctx).st =  ((BodyContext)_localctx).statements.st;
				}
				break;
			case RBRACE:
				enterOuterAlt(_localctx, 2);
				{
				 ((BodyContext)_localctx).st =  Collections.emptyList();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementsContext extends ParserRuleContext {
		public List<Statement> st;
		public StatementContext statement;
		public StatementsContext statements;
		public LoopContext loop;
		public ConditionContext condition;
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(LiteJavaParser2.SEMI, 0); }
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public LoopContext loop() {
			return getRuleContext(LoopContext.class,0);
		}
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public StatementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).enterStatements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).exitStatements(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LiteJavaParser2Visitor) return ((LiteJavaParser2Visitor<? extends T>)visitor).visitStatements(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementsContext statements() throws RecognitionException {
		StatementsContext _localctx = new StatementsContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_statements);
		try {
			setState(156);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(139);
				((StatementsContext)_localctx).statement = statement();
				setState(140);
				match(SEMI);
				 ((StatementsContext)_localctx).st =  new ArrayList<>();
				 _localctx.st.add(((StatementsContext)_localctx).statement.s);
				setState(143);
				((StatementsContext)_localctx).statements = statements();
				 _localctx.st.addAll(((StatementsContext)_localctx).statements.st);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(146);
				((StatementsContext)_localctx).statement = statement();
				setState(147);
				match(SEMI);
				 ((StatementsContext)_localctx).st =  Collections.singletonList(((StatementsContext)_localctx).statement.s);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(150);
				((StatementsContext)_localctx).loop = loop();
				 ((StatementsContext)_localctx).st =  Collections.singletonList(((StatementsContext)_localctx).loop.lp);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(153);
				((StatementsContext)_localctx).condition = condition();
				 ((StatementsContext)_localctx).st =  Collections.singletonList(((StatementsContext)_localctx).condition.cd);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public Statement s;
		public InitVariableContext initVariable;
		public ExprContext expr;
		public InitVariableContext initVariable() {
			return getRuleContext(InitVariableContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LiteJavaParser2Visitor) return ((LiteJavaParser2Visitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_statement);
		try {
			setState(164);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
			case INT:
			case LONG:
				enterOuterAlt(_localctx, 1);
				{
				setState(158);
				((StatementContext)_localctx).initVariable = initVariable();
				 ((StatementContext)_localctx).s =  ((StatementContext)_localctx).initVariable.v;
				}
				break;
			case VARIABLE:
				enterOuterAlt(_localctx, 2);
				{
				setState(161);
				((StatementContext)_localctx).expr = expr();
				 ((StatementContext)_localctx).s =  ((StatementContext)_localctx).expr.s;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LoopContext extends ParserRuleContext {
		public Loop lp;
		public LoopInitContext loopInit;
		public BodyContext body;
		public LoopInitContext loopInit() {
			return getRuleContext(LoopInitContext.class,0);
		}
		public TerminalNode LBRACE() { return getToken(LiteJavaParser2.LBRACE, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(LiteJavaParser2.RBRACE, 0); }
		public LoopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).enterLoop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).exitLoop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LiteJavaParser2Visitor) return ((LiteJavaParser2Visitor<? extends T>)visitor).visitLoop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LoopContext loop() throws RecognitionException {
		LoopContext _localctx = new LoopContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_loop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			((LoopContext)_localctx).loopInit = loopInit();
			setState(167);
			match(LBRACE);
			setState(168);
			((LoopContext)_localctx).body = body();
			setState(169);
			match(RBRACE);
			 ((LoopContext)_localctx).lp =  ((LoopContext)_localctx).loopInit.lp;
			 _localctx.lp.setStatements(((LoopContext)_localctx).body.st);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LoopInitContext extends ParserRuleContext {
		public Loop lp;
		public ForInitContext forInit;
		public ConditionInitContext conditionInit;
		public ForStepContext forStep;
		public TerminalNode FOR() { return getToken(LiteJavaParser2.FOR, 0); }
		public TerminalNode LPAREN() { return getToken(LiteJavaParser2.LPAREN, 0); }
		public ForInitContext forInit() {
			return getRuleContext(ForInitContext.class,0);
		}
		public List<TerminalNode> SEMI() { return getTokens(LiteJavaParser2.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(LiteJavaParser2.SEMI, i);
		}
		public ConditionInitContext conditionInit() {
			return getRuleContext(ConditionInitContext.class,0);
		}
		public ForStepContext forStep() {
			return getRuleContext(ForStepContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(LiteJavaParser2.RPAREN, 0); }
		public TerminalNode WHILE() { return getToken(LiteJavaParser2.WHILE, 0); }
		public LoopInitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loopInit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).enterLoopInit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).exitLoopInit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LiteJavaParser2Visitor) return ((LiteJavaParser2Visitor<? extends T>)visitor).visitLoopInit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LoopInitContext loopInit() throws RecognitionException {
		LoopInitContext _localctx = new LoopInitContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_loopInit);
		try {
			setState(189);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FOR:
				enterOuterAlt(_localctx, 1);
				{
				setState(173);
				match(FOR);
				setState(174);
				match(LPAREN);
				setState(175);
				((LoopInitContext)_localctx).forInit = forInit();
				setState(176);
				match(SEMI);
				setState(177);
				((LoopInitContext)_localctx).conditionInit = conditionInit();
				setState(178);
				match(SEMI);
				setState(179);
				((LoopInitContext)_localctx).forStep = forStep();
				setState(180);
				match(RPAREN);
				 ((LoopInitContext)_localctx).lp =  new ForLoop(((LoopInitContext)_localctx).forInit.v, ((LoopInitContext)_localctx).conditionInit.exp, ((LoopInitContext)_localctx).forStep.exp);
				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 2);
				{
				setState(183);
				match(WHILE);
				setState(184);
				match(LPAREN);
				setState(185);
				((LoopInitContext)_localctx).conditionInit = conditionInit();
				setState(186);
				match(RPAREN);
				 ((LoopInitContext)_localctx).lp =  new WhileLoop(((LoopInitContext)_localctx).conditionInit.exp);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForInitContext extends ParserRuleContext {
		public InitVariable v;
		public Token VARIABLE;
		public InitExprContext initExpr;
		public TerminalNode INT() { return getToken(LiteJavaParser2.INT, 0); }
		public TerminalNode VARIABLE() { return getToken(LiteJavaParser2.VARIABLE, 0); }
		public TerminalNode ASSIGN() { return getToken(LiteJavaParser2.ASSIGN, 0); }
		public InitExprContext initExpr() {
			return getRuleContext(InitExprContext.class,0);
		}
		public ForInitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forInit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).enterForInit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).exitForInit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LiteJavaParser2Visitor) return ((LiteJavaParser2Visitor<? extends T>)visitor).visitForInit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForInitContext forInit() throws RecognitionException {
		ForInitContext _localctx = new ForInitContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_forInit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
			match(INT);
			setState(192);
			((ForInitContext)_localctx).VARIABLE = match(VARIABLE);
			setState(193);
			match(ASSIGN);
			setState(194);
			((ForInitContext)_localctx).initExpr = initExpr();
			 ((ForInitContext)_localctx).v =  new InitVariable("int", (((ForInitContext)_localctx).VARIABLE!=null?((ForInitContext)_localctx).VARIABLE.getText():null), ((ForInitContext)_localctx).initExpr.exp);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForStepContext extends ParserRuleContext {
		public Expression exp;
		public Token VARIABLE;
		public Token UN_OPERATORS;
		public TerminalNode VARIABLE() { return getToken(LiteJavaParser2.VARIABLE, 0); }
		public TerminalNode UN_OPERATORS() { return getToken(LiteJavaParser2.UN_OPERATORS, 0); }
		public ForStepContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forStep; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).enterForStep(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).exitForStep(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LiteJavaParser2Visitor) return ((LiteJavaParser2Visitor<? extends T>)visitor).visitForStep(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForStepContext forStep() throws RecognitionException {
		ForStepContext _localctx = new ForStepContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_forStep);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(197);
			((ForStepContext)_localctx).VARIABLE = match(VARIABLE);
			setState(198);
			((ForStepContext)_localctx).UN_OPERATORS = match(UN_OPERATORS);
			 ((ForStepContext)_localctx).exp =  new UnExpression((((ForStepContext)_localctx).VARIABLE!=null?((ForStepContext)_localctx).VARIABLE.getText():null), (((ForStepContext)_localctx).UN_OPERATORS!=null?((ForStepContext)_localctx).UN_OPERATORS.getText():null));
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionContext extends ParserRuleContext {
		public Condition cd;
		public ConditionInitContext conditionInit;
		public BodyContext body;
		public TerminalNode IF() { return getToken(LiteJavaParser2.IF, 0); }
		public TerminalNode LPAREN() { return getToken(LiteJavaParser2.LPAREN, 0); }
		public ConditionInitContext conditionInit() {
			return getRuleContext(ConditionInitContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(LiteJavaParser2.RPAREN, 0); }
		public TerminalNode LBRACE() { return getToken(LiteJavaParser2.LBRACE, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(LiteJavaParser2.RBRACE, 0); }
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).exitCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LiteJavaParser2Visitor) return ((LiteJavaParser2Visitor<? extends T>)visitor).visitCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(201);
			match(IF);
			setState(202);
			match(LPAREN);
			setState(203);
			((ConditionContext)_localctx).conditionInit = conditionInit();
			setState(204);
			match(RPAREN);
			setState(205);
			match(LBRACE);
			setState(206);
			((ConditionContext)_localctx).body = body();
			setState(207);
			match(RBRACE);
			 ((ConditionContext)_localctx).cd =  new Condition(((ConditionContext)_localctx).conditionInit.exp, ((ConditionContext)_localctx).body.st);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionInitContext extends ParserRuleContext {
		public Expression exp;
		public AtomContext atom;
		public BoolBinOpContext boolBinOp;
		public Atom1Context atom1;
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public BoolBinOpContext boolBinOp() {
			return getRuleContext(BoolBinOpContext.class,0);
		}
		public Atom1Context atom1() {
			return getRuleContext(Atom1Context.class,0);
		}
		public ConditionInitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditionInit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).enterConditionInit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).exitConditionInit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LiteJavaParser2Visitor) return ((LiteJavaParser2Visitor<? extends T>)visitor).visitConditionInit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionInitContext conditionInit() throws RecognitionException {
		ConditionInitContext _localctx = new ConditionInitContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_conditionInit);
		try {
			setState(218);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(210);
				((ConditionInitContext)_localctx).atom = atom();
				 ((ConditionInitContext)_localctx).exp =  ((ConditionInitContext)_localctx).atom.e;
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(213);
				((ConditionInitContext)_localctx).atom = atom();
				setState(214);
				((ConditionInitContext)_localctx).boolBinOp = boolBinOp();
				setState(215);
				((ConditionInitContext)_localctx).atom1 = atom1();
				 ((ConditionInitContext)_localctx).exp =  new BinConditionExpression(
				                ((ConditionInitContext)_localctx).atom.e,
				                ((ConditionInitContext)_localctx).atom1.e,
				                ((ConditionInitContext)_localctx).boolBinOp.op);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Atom1Context extends ParserRuleContext {
		public Expression e;
		public AtomContext atom;
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public Atom1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).enterAtom1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).exitAtom1(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LiteJavaParser2Visitor) return ((LiteJavaParser2Visitor<? extends T>)visitor).visitAtom1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Atom1Context atom1() throws RecognitionException {
		Atom1Context _localctx = new Atom1Context(_ctx, getState());
		enterRule(_localctx, 32, RULE_atom1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(220);
			((Atom1Context)_localctx).atom = atom();
			 ((Atom1Context)_localctx).e =  ((Atom1Context)_localctx).atom.e;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AtomContext extends ParserRuleContext {
		public Expression e;
		public Token VARIABLE;
		public InitExprContext initExpr;
		public TerminalNode VARIABLE() { return getToken(LiteJavaParser2.VARIABLE, 0); }
		public InitExprContext initExpr() {
			return getRuleContext(InitExprContext.class,0);
		}
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).exitAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LiteJavaParser2Visitor) return ((LiteJavaParser2Visitor<? extends T>)visitor).visitAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_atom);
		try {
			setState(228);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(223);
				((AtomContext)_localctx).VARIABLE = match(VARIABLE);
				 ((AtomContext)_localctx).e =  new Variable((((AtomContext)_localctx).VARIABLE!=null?((AtomContext)_localctx).VARIABLE.getText():null));
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(225);
				((AtomContext)_localctx).initExpr = initExpr();
				 ((AtomContext)_localctx).e =  ((AtomContext)_localctx).initExpr.exp;
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public Statement s;
		public Token VARIABLE;
		public InitExprContext initExpr;
		public Token UN_ASSIGN_OPERATORS;
		public TerminalNode VARIABLE() { return getToken(LiteJavaParser2.VARIABLE, 0); }
		public TerminalNode ASSIGN() { return getToken(LiteJavaParser2.ASSIGN, 0); }
		public InitExprContext initExpr() {
			return getRuleContext(InitExprContext.class,0);
		}
		public TerminalNode UN_ASSIGN_OPERATORS() { return getToken(LiteJavaParser2.UN_ASSIGN_OPERATORS, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LiteJavaParser2Visitor) return ((LiteJavaParser2Visitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_expr);
		try {
			setState(240);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(230);
				((ExprContext)_localctx).VARIABLE = match(VARIABLE);
				setState(231);
				match(ASSIGN);
				setState(232);
				((ExprContext)_localctx).initExpr = initExpr();
				 ((ExprContext)_localctx).s =  new Assign((((ExprContext)_localctx).VARIABLE!=null?((ExprContext)_localctx).VARIABLE.getText():null), ((ExprContext)_localctx).initExpr.exp);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(235);
				((ExprContext)_localctx).VARIABLE = match(VARIABLE);
				setState(236);
				((ExprContext)_localctx).UN_ASSIGN_OPERATORS = match(UN_ASSIGN_OPERATORS);
				setState(237);
				((ExprContext)_localctx).initExpr = initExpr();
				 ((ExprContext)_localctx).s =  new Assign((((ExprContext)_localctx).VARIABLE!=null?((ExprContext)_localctx).VARIABLE.getText():null), ((ExprContext)_localctx).initExpr.exp, (((ExprContext)_localctx).UN_ASSIGN_OPERATORS!=null?((ExprContext)_localctx).UN_ASSIGN_OPERATORS.getText():null));
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InitExprContext extends ParserRuleContext {
		public Expression exp;
		public MulExprContext mulExpr;
		public PlusMinusContext plusMinus;
		public InitExprContext initExpr;
		public MulExprContext mulExpr() {
			return getRuleContext(MulExprContext.class,0);
		}
		public PlusMinusContext plusMinus() {
			return getRuleContext(PlusMinusContext.class,0);
		}
		public InitExprContext initExpr() {
			return getRuleContext(InitExprContext.class,0);
		}
		public InitExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).enterInitExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).exitInitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LiteJavaParser2Visitor) return ((LiteJavaParser2Visitor<? extends T>)visitor).visitInitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InitExprContext initExpr() throws RecognitionException {
		InitExprContext _localctx = new InitExprContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_initExpr);
		try {
			setState(250);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(242);
				((InitExprContext)_localctx).mulExpr = mulExpr();
				setState(243);
				((InitExprContext)_localctx).plusMinus = plusMinus();
				setState(244);
				((InitExprContext)_localctx).initExpr = initExpr();
				 ((InitExprContext)_localctx).exp =  new BinExpression(((InitExprContext)_localctx).mulExpr.exp, ((InitExprContext)_localctx).initExpr.exp, ((InitExprContext)_localctx).plusMinus.op);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(247);
				((InitExprContext)_localctx).mulExpr = mulExpr();
				 ((InitExprContext)_localctx).exp =  ((InitExprContext)_localctx).mulExpr.exp;
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PlusMinusContext extends ParserRuleContext {
		public String op;
		public TerminalNode PLUS() { return getToken(LiteJavaParser2.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(LiteJavaParser2.MINUS, 0); }
		public PlusMinusContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_plusMinus; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).enterPlusMinus(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).exitPlusMinus(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LiteJavaParser2Visitor) return ((LiteJavaParser2Visitor<? extends T>)visitor).visitPlusMinus(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PlusMinusContext plusMinus() throws RecognitionException {
		PlusMinusContext _localctx = new PlusMinusContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_plusMinus);
		try {
			setState(256);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PLUS:
				enterOuterAlt(_localctx, 1);
				{
				setState(252);
				match(PLUS);
				 ((PlusMinusContext)_localctx).op =  "+";
				}
				break;
			case MINUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(254);
				match(MINUS);
				 ((PlusMinusContext)_localctx).op =  "-";
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MulExprContext extends ParserRuleContext {
		public Expression exp;
		public VarContext var;
		public MulDivContext mulDiv;
		public MulExprContext mulExpr;
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
		public MulDivContext mulDiv() {
			return getRuleContext(MulDivContext.class,0);
		}
		public MulExprContext mulExpr() {
			return getRuleContext(MulExprContext.class,0);
		}
		public MulExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mulExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).enterMulExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).exitMulExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LiteJavaParser2Visitor) return ((LiteJavaParser2Visitor<? extends T>)visitor).visitMulExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MulExprContext mulExpr() throws RecognitionException {
		MulExprContext _localctx = new MulExprContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_mulExpr);
		try {
			setState(266);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(258);
				((MulExprContext)_localctx).var = var();
				setState(259);
				((MulExprContext)_localctx).mulDiv = mulDiv();
				setState(260);
				((MulExprContext)_localctx).mulExpr = mulExpr();
				 ((MulExprContext)_localctx).exp =  new BinExpression(((MulExprContext)_localctx).var.exp, ((MulExprContext)_localctx).mulExpr.exp, ((MulExprContext)_localctx).mulDiv.op);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(263);
				((MulExprContext)_localctx).var = var();
				 ((MulExprContext)_localctx).exp =  ((MulExprContext)_localctx).var.exp;
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MulDivContext extends ParserRuleContext {
		public String op;
		public TerminalNode MUL() { return getToken(LiteJavaParser2.MUL, 0); }
		public TerminalNode DIV() { return getToken(LiteJavaParser2.DIV, 0); }
		public MulDivContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mulDiv; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).enterMulDiv(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).exitMulDiv(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LiteJavaParser2Visitor) return ((LiteJavaParser2Visitor<? extends T>)visitor).visitMulDiv(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MulDivContext mulDiv() throws RecognitionException {
		MulDivContext _localctx = new MulDivContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_mulDiv);
		try {
			setState(272);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MUL:
				enterOuterAlt(_localctx, 1);
				{
				setState(268);
				match(MUL);
				 ((MulDivContext)_localctx).op =  "*";
				}
				break;
			case DIV:
				enterOuterAlt(_localctx, 2);
				{
				setState(270);
				match(DIV);
				 ((MulDivContext)_localctx).op =  "/";
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarContext extends ParserRuleContext {
		public Expression exp;
		public InitExprContext initExpr;
		public Token VARIABLE;
		public Token DIGIT;
		public TerminalNode LPAREN() { return getToken(LiteJavaParser2.LPAREN, 0); }
		public InitExprContext initExpr() {
			return getRuleContext(InitExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(LiteJavaParser2.RPAREN, 0); }
		public TerminalNode VARIABLE() { return getToken(LiteJavaParser2.VARIABLE, 0); }
		public TerminalNode DIGIT() { return getToken(LiteJavaParser2.DIGIT, 0); }
		public VarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).enterVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).exitVar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LiteJavaParser2Visitor) return ((LiteJavaParser2Visitor<? extends T>)visitor).visitVar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarContext var() throws RecognitionException {
		VarContext _localctx = new VarContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_var);
		try {
			setState(283);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPAREN:
				enterOuterAlt(_localctx, 1);
				{
				setState(274);
				match(LPAREN);
				setState(275);
				((VarContext)_localctx).initExpr = initExpr();
				setState(276);
				match(RPAREN);
				 ((VarContext)_localctx).exp =  ((VarContext)_localctx).initExpr.exp;
				}
				break;
			case VARIABLE:
				enterOuterAlt(_localctx, 2);
				{
				setState(279);
				((VarContext)_localctx).VARIABLE = match(VARIABLE);
				 ((VarContext)_localctx).exp =  new Variable((((VarContext)_localctx).VARIABLE!=null?((VarContext)_localctx).VARIABLE.getText():null));
				}
				break;
			case DIGIT:
				enterOuterAlt(_localctx, 3);
				{
				setState(281);
				((VarContext)_localctx).DIGIT = match(DIGIT);
				 ((VarContext)_localctx).exp =  new Number((((VarContext)_localctx).DIGIT!=null?((VarContext)_localctx).DIGIT.getText():null));
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InitVariableContext extends ParserRuleContext {
		public InitVariable v;
		public NumPrimContext numPrim;
		public Token VARIABLE;
		public InitExprContext initExpr;
		public InitStringContext initString;
		public NumPrimContext numPrim() {
			return getRuleContext(NumPrimContext.class,0);
		}
		public TerminalNode VARIABLE() { return getToken(LiteJavaParser2.VARIABLE, 0); }
		public TerminalNode ASSIGN() { return getToken(LiteJavaParser2.ASSIGN, 0); }
		public InitExprContext initExpr() {
			return getRuleContext(InitExprContext.class,0);
		}
		public TerminalNode STRING() { return getToken(LiteJavaParser2.STRING, 0); }
		public InitStringContext initString() {
			return getRuleContext(InitStringContext.class,0);
		}
		public InitVariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initVariable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).enterInitVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).exitInitVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LiteJavaParser2Visitor) return ((LiteJavaParser2Visitor<? extends T>)visitor).visitInitVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InitVariableContext initVariable() throws RecognitionException {
		InitVariableContext _localctx = new InitVariableContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_initVariable);
		try {
			setState(304);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(285);
				((InitVariableContext)_localctx).numPrim = numPrim();
				setState(286);
				((InitVariableContext)_localctx).VARIABLE = match(VARIABLE);
				 ((InitVariableContext)_localctx).v =  new InitVariable(((InitVariableContext)_localctx).numPrim.t, (((InitVariableContext)_localctx).VARIABLE!=null?((InitVariableContext)_localctx).VARIABLE.getText():null));
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(289);
				((InitVariableContext)_localctx).numPrim = numPrim();
				setState(290);
				((InitVariableContext)_localctx).VARIABLE = match(VARIABLE);
				setState(291);
				match(ASSIGN);
				setState(292);
				((InitVariableContext)_localctx).initExpr = initExpr();
				 ((InitVariableContext)_localctx).v =  new InitVariable(((InitVariableContext)_localctx).numPrim.t, (((InitVariableContext)_localctx).VARIABLE!=null?((InitVariableContext)_localctx).VARIABLE.getText():null), ((InitVariableContext)_localctx).initExpr.exp);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(295);
				match(STRING);
				setState(296);
				((InitVariableContext)_localctx).VARIABLE = match(VARIABLE);
				 ((InitVariableContext)_localctx).v =  new InitVariable("String", (((InitVariableContext)_localctx).VARIABLE!=null?((InitVariableContext)_localctx).VARIABLE.getText():null));
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(298);
				match(STRING);
				setState(299);
				((InitVariableContext)_localctx).VARIABLE = match(VARIABLE);
				setState(300);
				match(ASSIGN);
				setState(301);
				((InitVariableContext)_localctx).initString = initString();
				 ((InitVariableContext)_localctx).v =  new InitVariable("String", (((InitVariableContext)_localctx).VARIABLE!=null?((InitVariableContext)_localctx).VARIABLE.getText():null), ((InitVariableContext)_localctx).initString.s);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InitStringContext extends ParserRuleContext {
		public String s;
		public Token VARIABLE;
		public List<TerminalNode> MARK() { return getTokens(LiteJavaParser2.MARK); }
		public TerminalNode MARK(int i) {
			return getToken(LiteJavaParser2.MARK, i);
		}
		public TerminalNode VARIABLE() { return getToken(LiteJavaParser2.VARIABLE, 0); }
		public InitStringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initString; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).enterInitString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).exitInitString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LiteJavaParser2Visitor) return ((LiteJavaParser2Visitor<? extends T>)visitor).visitInitString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InitStringContext initString() throws RecognitionException {
		InitStringContext _localctx = new InitStringContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_initString);
		try {
			setState(313);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(306);
				match(MARK);
				setState(307);
				match(MARK);
				 ((InitStringContext)_localctx).s =  "\"\"";
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(309);
				match(MARK);
				setState(310);
				((InitStringContext)_localctx).VARIABLE = match(VARIABLE);
				setState(311);
				match(MARK);
				 ((InitStringContext)_localctx).s =  "\"" + (((InitStringContext)_localctx).VARIABLE!=null?((InitStringContext)_localctx).VARIABLE.getText():null) + "\"";
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumPrimContext extends ParserRuleContext {
		public String t;
		public TerminalNode INT() { return getToken(LiteJavaParser2.INT, 0); }
		public TerminalNode LONG() { return getToken(LiteJavaParser2.LONG, 0); }
		public NumPrimContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numPrim; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).enterNumPrim(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).exitNumPrim(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LiteJavaParser2Visitor) return ((LiteJavaParser2Visitor<? extends T>)visitor).visitNumPrim(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumPrimContext numPrim() throws RecognitionException {
		NumPrimContext _localctx = new NumPrimContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_numPrim);
		try {
			setState(319);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(315);
				match(INT);
				 ((NumPrimContext)_localctx).t =  "int";
				}
				break;
			case LONG:
				enterOuterAlt(_localctx, 2);
				{
				setState(317);
				match(LONG);
				 ((NumPrimContext)_localctx).t =  "long";
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoolBinOpContext extends ParserRuleContext {
		public String op;
		public TerminalNode GT() { return getToken(LiteJavaParser2.GT, 0); }
		public TerminalNode LT() { return getToken(LiteJavaParser2.LT, 0); }
		public TerminalNode EQUAL() { return getToken(LiteJavaParser2.EQUAL, 0); }
		public TerminalNode LE() { return getToken(LiteJavaParser2.LE, 0); }
		public TerminalNode GE() { return getToken(LiteJavaParser2.GE, 0); }
		public TerminalNode NOTEQUAL() { return getToken(LiteJavaParser2.NOTEQUAL, 0); }
		public TerminalNode AND() { return getToken(LiteJavaParser2.AND, 0); }
		public TerminalNode OR() { return getToken(LiteJavaParser2.OR, 0); }
		public BoolBinOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolBinOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).enterBoolBinOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).exitBoolBinOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LiteJavaParser2Visitor) return ((LiteJavaParser2Visitor<? extends T>)visitor).visitBoolBinOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolBinOpContext boolBinOp() throws RecognitionException {
		BoolBinOpContext _localctx = new BoolBinOpContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_boolBinOp);
		try {
			setState(337);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case GT:
				enterOuterAlt(_localctx, 1);
				{
				setState(321);
				match(GT);
				 ((BoolBinOpContext)_localctx).op =  ">";
				}
				break;
			case LT:
				enterOuterAlt(_localctx, 2);
				{
				setState(323);
				match(LT);
				 ((BoolBinOpContext)_localctx).op =  "<";
				}
				break;
			case EQUAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(325);
				match(EQUAL);
				 ((BoolBinOpContext)_localctx).op =  "==";
				}
				break;
			case LE:
				enterOuterAlt(_localctx, 4);
				{
				setState(327);
				match(LE);
				 ((BoolBinOpContext)_localctx).op =  "<=";
				}
				break;
			case GE:
				enterOuterAlt(_localctx, 5);
				{
				setState(329);
				match(GE);
				 ((BoolBinOpContext)_localctx).op =  ">=";
				}
				break;
			case NOTEQUAL:
				enterOuterAlt(_localctx, 6);
				{
				setState(331);
				match(NOTEQUAL);
				 ((BoolBinOpContext)_localctx).op =  "!=";
				}
				break;
			case AND:
				enterOuterAlt(_localctx, 7);
				{
				setState(333);
				match(AND);
				 ((BoolBinOpContext)_localctx).op =  "&&";
				}
				break;
			case OR:
				enterOuterAlt(_localctx, 8);
				{
				setState(335);
				match(OR);
				 ((BoolBinOpContext)_localctx).op =  "||";
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ModifiersContext extends ParserRuleContext {
		public String s;
		public TerminalNode PUBLIC() { return getToken(LiteJavaParser2.PUBLIC, 0); }
		public TerminalNode PRIVATE() { return getToken(LiteJavaParser2.PRIVATE, 0); }
		public TerminalNode PROTECTED() { return getToken(LiteJavaParser2.PROTECTED, 0); }
		public ModifiersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_modifiers; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).enterModifiers(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LiteJavaParser2Listener) ((LiteJavaParser2Listener)listener).exitModifiers(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LiteJavaParser2Visitor) return ((LiteJavaParser2Visitor<? extends T>)visitor).visitModifiers(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModifiersContext modifiers() throws RecognitionException {
		ModifiersContext _localctx = new ModifiersContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_modifiers);
		try {
			setState(345);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PUBLIC:
				enterOuterAlt(_localctx, 1);
				{
				setState(339);
				match(PUBLIC);
				 ((ModifiersContext)_localctx).s =  "public";    
				}
				break;
			case PRIVATE:
				enterOuterAlt(_localctx, 2);
				{
				setState(341);
				match(PRIVATE);
				 ((ModifiersContext)_localctx).s =  "private";   
				}
				break;
			case PROTECTED:
				enterOuterAlt(_localctx, 3);
				{
				setState(343);
				match(PROTECTED);
				 ((ModifiersContext)_localctx).s =  "protected"; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\67\u015e\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\3\2\3\2\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\7\4N\n\4\f\4\16\4Q\13"+
		"\4\3\4\5\4T\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\5\6i\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6r\n\6\3"+
		"\7\3\7\3\7\3\7\5\7x\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\5\b\u0086\n\b\3\t\3\t\3\t\3\t\5\t\u008c\n\t\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u009f\n\n\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\5\13\u00a7\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00c0\n\r"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21"+
		"\u00dd\n\21\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\5\23\u00e7\n\23\3"+
		"\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\5\24\u00f3\n\24\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\5\25\u00fd\n\25\3\26\3\26\3\26\3\26"+
		"\5\26\u0103\n\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\5\27\u010d\n"+
		"\27\3\30\3\30\3\30\3\30\5\30\u0113\n\30\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\5\31\u011e\n\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\5\32\u0133\n\32"+
		"\3\33\3\33\3\33\3\33\3\33\3\33\3\33\5\33\u013c\n\33\3\34\3\34\3\34\3\34"+
		"\5\34\u0142\n\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\3\35\3\35\5\35\u0154\n\35\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\5\36\u015c\n\36\3\36\2\2\37\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \""+
		"$&(*,.\60\62\64\668:\2\2\2\u0163\2<\3\2\2\2\4?\3\2\2\2\6S\3\2\2\2\bU\3"+
		"\2\2\2\nq\3\2\2\2\fw\3\2\2\2\16\u0085\3\2\2\2\20\u008b\3\2\2\2\22\u009e"+
		"\3\2\2\2\24\u00a6\3\2\2\2\26\u00a8\3\2\2\2\30\u00bf\3\2\2\2\32\u00c1\3"+
		"\2\2\2\34\u00c7\3\2\2\2\36\u00cb\3\2\2\2 \u00dc\3\2\2\2\"\u00de\3\2\2"+
		"\2$\u00e6\3\2\2\2&\u00f2\3\2\2\2(\u00fc\3\2\2\2*\u0102\3\2\2\2,\u010c"+
		"\3\2\2\2.\u0112\3\2\2\2\60\u011d\3\2\2\2\62\u0132\3\2\2\2\64\u013b\3\2"+
		"\2\2\66\u0141\3\2\2\28\u0153\3\2\2\2:\u015b\3\2\2\2<=\5\4\3\2=>\b\2\1"+
		"\2>\3\3\2\2\2?@\7\37\2\2@A\7\67\2\2AB\7\5\2\2BC\5\6\4\2CD\7\6\2\2DE\b"+
		"\3\1\2EF\b\3\1\2F\5\3\2\2\2GH\5\b\5\2HI\b\4\1\2IO\b\4\1\2JK\5\b\5\2KL"+
		"\b\4\1\2LN\3\2\2\2MJ\3\2\2\2NQ\3\2\2\2OM\3\2\2\2OP\3\2\2\2PT\3\2\2\2Q"+
		"O\3\2\2\2RT\b\4\1\2SG\3\2\2\2SR\3\2\2\2T\7\3\2\2\2UV\5\n\6\2VW\7\5\2\2"+
		"WX\5\20\t\2XY\7\6\2\2YZ\b\5\1\2Z[\b\5\1\2[\t\3\2\2\2\\]\7.\2\2]^\7 \2"+
		"\2^_\7!\2\2_`\7\"\2\2`a\7\3\2\2ab\7#\2\2bc\7\r\2\2cd\7\67\2\2de\7\4\2"+
		"\2ef\b\6\1\2fr\b\6\1\2gi\5:\36\2hg\3\2\2\2hi\3\2\2\2ij\3\2\2\2jk\7!\2"+
		"\2kl\7\67\2\2lm\7\3\2\2mn\5\f\7\2no\7\4\2\2op\b\6\1\2pr\3\2\2\2q\\\3\2"+
		"\2\2qh\3\2\2\2r\13\3\2\2\2st\5\16\b\2tu\b\7\1\2ux\3\2\2\2vx\b\7\1\2ws"+
		"\3\2\2\2wv\3\2\2\2x\r\3\2\2\2yz\5\66\34\2z{\7\67\2\2{|\7\n\2\2|}\5\16"+
		"\b\2}~\b\b\1\2~\177\b\b\1\2\177\u0080\b\b\1\2\u0080\u0086\3\2\2\2\u0081"+
		"\u0082\5\66\34\2\u0082\u0083\7\67\2\2\u0083\u0084\b\b\1\2\u0084\u0086"+
		"\3\2\2\2\u0085y\3\2\2\2\u0085\u0081\3\2\2\2\u0086\17\3\2\2\2\u0087\u0088"+
		"\5\22\n\2\u0088\u0089\b\t\1\2\u0089\u008c\3\2\2\2\u008a\u008c\b\t\1\2"+
		"\u008b\u0087\3\2\2\2\u008b\u008a\3\2\2\2\u008c\21\3\2\2\2\u008d\u008e"+
		"\5\24\13\2\u008e\u008f\7\t\2\2\u008f\u0090\b\n\1\2\u0090\u0091\b\n\1\2"+
		"\u0091\u0092\5\22\n\2\u0092\u0093\b\n\1\2\u0093\u009f\3\2\2\2\u0094\u0095"+
		"\5\24\13\2\u0095\u0096\7\t\2\2\u0096\u0097\b\n\1\2\u0097\u009f\3\2\2\2"+
		"\u0098\u0099\5\26\f\2\u0099\u009a\b\n\1\2\u009a\u009f\3\2\2\2\u009b\u009c"+
		"\5\36\20\2\u009c\u009d\b\n\1\2\u009d\u009f\3\2\2\2\u009e\u008d\3\2\2\2"+
		"\u009e\u0094\3\2\2\2\u009e\u0098\3\2\2\2\u009e\u009b\3\2\2\2\u009f\23"+
		"\3\2\2\2\u00a0\u00a1\5\62\32\2\u00a1\u00a2\b\13\1\2\u00a2\u00a7\3\2\2"+
		"\2\u00a3\u00a4\5&\24\2\u00a4\u00a5\b\13\1\2\u00a5\u00a7\3\2\2\2\u00a6"+
		"\u00a0\3\2\2\2\u00a6\u00a3\3\2\2\2\u00a7\25\3\2\2\2\u00a8\u00a9\5\30\r"+
		"\2\u00a9\u00aa\7\5\2\2\u00aa\u00ab\5\20\t\2\u00ab\u00ac\7\6\2\2\u00ac"+
		"\u00ad\b\f\1\2\u00ad\u00ae\b\f\1\2\u00ae\27\3\2\2\2\u00af\u00b0\7)\2\2"+
		"\u00b0\u00b1\7\3\2\2\u00b1\u00b2\5\32\16\2\u00b2\u00b3\7\t\2\2\u00b3\u00b4"+
		"\5 \21\2\u00b4\u00b5\7\t\2\2\u00b5\u00b6\5\34\17\2\u00b6\u00b7\7\4\2\2"+
		"\u00b7\u00b8\b\r\1\2\u00b8\u00c0\3\2\2\2\u00b9\u00ba\7*\2\2\u00ba\u00bb"+
		"\7\3\2\2\u00bb\u00bc\5 \21\2\u00bc\u00bd\7\4\2\2\u00bd\u00be\b\r\1\2\u00be"+
		"\u00c0\3\2\2\2\u00bf\u00af\3\2\2\2\u00bf\u00b9\3\2\2\2\u00c0\31\3\2\2"+
		"\2\u00c1\u00c2\7+\2\2\u00c2\u00c3\7\67\2\2\u00c3\u00c4\7\16\2\2\u00c4"+
		"\u00c5\5(\25\2\u00c5\u00c6\b\16\1\2\u00c6\33\3\2\2\2\u00c7\u00c8\7\67"+
		"\2\2\u00c8\u00c9\7\64\2\2\u00c9\u00ca\b\17\1\2\u00ca\35\3\2\2\2\u00cb"+
		"\u00cc\7\'\2\2\u00cc\u00cd\7\3\2\2\u00cd\u00ce\5 \21\2\u00ce\u00cf\7\4"+
		"\2\2\u00cf\u00d0\7\5\2\2\u00d0\u00d1\5\20\t\2\u00d1\u00d2\7\6\2\2\u00d2"+
		"\u00d3\b\20\1\2\u00d3\37\3\2\2\2\u00d4\u00d5\5$\23\2\u00d5\u00d6\b\21"+
		"\1\2\u00d6\u00dd\3\2\2\2\u00d7\u00d8\5$\23\2\u00d8\u00d9\58\35\2\u00d9"+
		"\u00da\5\"\22\2\u00da\u00db\b\21\1\2\u00db\u00dd\3\2\2\2\u00dc\u00d4\3"+
		"\2\2\2\u00dc\u00d7\3\2\2\2\u00dd!\3\2\2\2\u00de\u00df\5$\23\2\u00df\u00e0"+
		"\b\22\1\2\u00e0#\3\2\2\2\u00e1\u00e2\7\67\2\2\u00e2\u00e7\b\23\1\2\u00e3"+
		"\u00e4\5(\25\2\u00e4\u00e5\b\23\1\2\u00e5\u00e7\3\2\2\2\u00e6\u00e1\3"+
		"\2\2\2\u00e6\u00e3\3\2\2\2\u00e7%\3\2\2\2\u00e8\u00e9\7\67\2\2\u00e9\u00ea"+
		"\7\16\2\2\u00ea\u00eb\5(\25\2\u00eb\u00ec\b\24\1\2\u00ec\u00f3\3\2\2\2"+
		"\u00ed\u00ee\7\67\2\2\u00ee\u00ef\7\65\2\2\u00ef\u00f0\5(\25\2\u00f0\u00f1"+
		"\b\24\1\2\u00f1\u00f3\3\2\2\2\u00f2\u00e8\3\2\2\2\u00f2\u00ed\3\2\2\2"+
		"\u00f3\'\3\2\2\2\u00f4\u00f5\5,\27\2\u00f5\u00f6\5*\26\2\u00f6\u00f7\5"+
		"(\25\2\u00f7\u00f8\b\25\1\2\u00f8\u00fd\3\2\2\2\u00f9\u00fa\5,\27\2\u00fa"+
		"\u00fb\b\25\1\2\u00fb\u00fd\3\2\2\2\u00fc\u00f4\3\2\2\2\u00fc\u00f9\3"+
		"\2\2\2\u00fd)\3\2\2\2\u00fe\u00ff\7\33\2\2\u00ff\u0103\b\26\1\2\u0100"+
		"\u0101\7\34\2\2\u0101\u0103\b\26\1\2\u0102\u00fe\3\2\2\2\u0102\u0100\3"+
		"\2\2\2\u0103+\3\2\2\2\u0104\u0105\5\60\31\2\u0105\u0106\5.\30\2\u0106"+
		"\u0107\5,\27\2\u0107\u0108\b\27\1\2\u0108\u010d\3\2\2\2\u0109\u010a\5"+
		"\60\31\2\u010a\u010b\b\27\1\2\u010b\u010d\3\2\2\2\u010c\u0104\3\2\2\2"+
		"\u010c\u0109\3\2\2\2\u010d-\3\2\2\2\u010e\u010f\7\36\2\2\u010f\u0113\b"+
		"\30\1\2\u0110\u0111\7\35\2\2\u0111\u0113\b\30\1\2\u0112\u010e\3\2\2\2"+
		"\u0112\u0110\3\2\2\2\u0113/\3\2\2\2\u0114\u0115\7\3\2\2\u0115\u0116\5"+
		"(\25\2\u0116\u0117\7\4\2\2\u0117\u0118\b\31\1\2\u0118\u011e\3\2\2\2\u0119"+
		"\u011a\7\67\2\2\u011a\u011e\b\31\1\2\u011b\u011c\7\66\2\2\u011c\u011e"+
		"\b\31\1\2\u011d\u0114\3\2\2\2\u011d\u0119\3\2\2\2\u011d\u011b\3\2\2\2"+
		"\u011e\61\3\2\2\2\u011f\u0120\5\66\34\2\u0120\u0121\7\67\2\2\u0121\u0122"+
		"\b\32\1\2\u0122\u0133\3\2\2\2\u0123\u0124\5\66\34\2\u0124\u0125\7\67\2"+
		"\2\u0125\u0126\7\16\2\2\u0126\u0127\5(\25\2\u0127\u0128\b\32\1\2\u0128"+
		"\u0133\3\2\2\2\u0129\u012a\7#\2\2\u012a\u012b\7\67\2\2\u012b\u0133\b\32"+
		"\1\2\u012c\u012d\7#\2\2\u012d\u012e\7\67\2\2\u012e\u012f\7\16\2\2\u012f"+
		"\u0130\5\64\33\2\u0130\u0131\b\32\1\2\u0131\u0133\3\2\2\2\u0132\u011f"+
		"\3\2\2\2\u0132\u0123\3\2\2\2\u0132\u0129\3\2\2\2\u0132\u012c\3\2\2\2\u0133"+
		"\63\3\2\2\2\u0134\u0135\7\f\2\2\u0135\u0136\7\f\2\2\u0136\u013c\b\33\1"+
		"\2\u0137\u0138\7\f\2\2\u0138\u0139\7\67\2\2\u0139\u013a\7\f\2\2\u013a"+
		"\u013c\b\33\1\2\u013b\u0134\3\2\2\2\u013b\u0137\3\2\2\2\u013c\65\3\2\2"+
		"\2\u013d\u013e\7+\2\2\u013e\u0142\b\34\1\2\u013f\u0140\7,\2\2\u0140\u0142"+
		"\b\34\1\2\u0141\u013d\3\2\2\2\u0141\u013f\3\2\2\2\u0142\67\3\2\2\2\u0143"+
		"\u0144\7\17\2\2\u0144\u0154\b\35\1\2\u0145\u0146\7\20\2\2\u0146\u0154"+
		"\b\35\1\2\u0147\u0148\7\25\2\2\u0148\u0154\b\35\1\2\u0149\u014a\7\26\2"+
		"\2\u014a\u0154\b\35\1\2\u014b\u014c\7\27\2\2\u014c\u0154\b\35\1\2\u014d"+
		"\u014e\7\30\2\2\u014e\u0154\b\35\1\2\u014f\u0150\7\31\2\2\u0150\u0154"+
		"\b\35\1\2\u0151\u0152\7\32\2\2\u0152\u0154\b\35\1\2\u0153\u0143\3\2\2"+
		"\2\u0153\u0145\3\2\2\2\u0153\u0147\3\2\2\2\u0153\u0149\3\2\2\2\u0153\u014b"+
		"\3\2\2\2\u0153\u014d\3\2\2\2\u0153\u014f\3\2\2\2\u0153\u0151\3\2\2\2\u0154"+
		"9\3\2\2\2\u0155\u0156\7.\2\2\u0156\u015c\b\36\1\2\u0157\u0158\7\60\2\2"+
		"\u0158\u015c\b\36\1\2\u0159\u015a\7/\2\2\u015a\u015c\b\36\1\2\u015b\u0155"+
		"\3\2\2\2\u015b\u0157\3\2\2\2\u015b\u0159\3\2\2\2\u015c;\3\2\2\2\31OSh"+
		"qw\u0085\u008b\u009e\u00a6\u00bf\u00dc\u00e6\u00f2\u00fc\u0102\u010c\u0112"+
		"\u011d\u0132\u013b\u0141\u0153\u015b";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}