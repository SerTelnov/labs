// Generated from C:/pro/homework/labs/translation-methods/parsergenerator/src/main/antlr4\GrammarParser.g4 by ANTLR 4.7

    package ru.telnov.labs.translationmethods.parsergenerator;

    import ru.telnov.labs.translationmethods.parsergenerator.generator.builders.Arg;
    import ru.telnov.labs.translationmethods.parsergenerator.tokens.*;

    import java.util.Collections;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		LPAREN=1, RPAREN=2, LBRACE=3, RBRACE=4, LBRACK=5, RBRACK=6, SEMI=7, COMMA=8, 
		DOT=9, MARK=10, ARRAY=11, DOUBLE_DOT=12, OVER_RULE=13, RETURNS=14, TERMINAL=15, 
		JNAME_DEF=16, JAVA_CLASS_NAME=17, TOKEN_VALUE=18, REGEX_VALUE=19, JCODE=20, 
		IN_VALUE=21, WS=22, COMMENT=23, LINE_COMMENT=24;
	public static final int
		RULE_start = 0, RULE_notTerminal = 1, RULE_args = 2, RULE_arg = 3, RULE_type = 4, 
		RULE_name = 5, RULE_initRules = 6, RULE_rules = 7, RULE_rrule = 8, RULE_nextToken = 9;
	public static final String[] ruleNames = {
		"start", "notTerminal", "args", "arg", "type", "name", "initRules", "rules", 
		"rrule", "nextToken"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "'{'", "'}'", "'['", "']'", "';'", "','", "'.'", "'\"'", 
		"'[]'", "':'", "'|'", "'returns'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "LPAREN", "RPAREN", "LBRACE", "RBRACE", "LBRACK", "RBRACK", "SEMI", 
		"COMMA", "DOT", "MARK", "ARRAY", "DOUBLE_DOT", "OVER_RULE", "RETURNS", 
		"TERMINAL", "JNAME_DEF", "JAVA_CLASS_NAME", "TOKEN_VALUE", "REGEX_VALUE", 
		"JCODE", "IN_VALUE", "WS", "COMMENT", "LINE_COMMENT"
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
	public String getGrammarFileName() { return "GrammarParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public GrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class StartContext extends ParserRuleContext {
		public List<NotTerminal> ntList;
		public NotTerminalContext notTerminal;
		public List<NotTerminalContext> notTerminal() {
			return getRuleContexts(NotTerminalContext.class);
		}
		public NotTerminalContext notTerminal(int i) {
			return getRuleContext(NotTerminalContext.class,i);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitStart(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(20);
			((StartContext)_localctx).notTerminal = notTerminal();
			 ((StartContext)_localctx).ntList =  new ArrayList<>();  
			 _localctx.ntList.add(((StartContext)_localctx).notTerminal.nt); 
			setState(28);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==JNAME_DEF) {
				{
				{
				setState(23);
				((StartContext)_localctx).notTerminal = notTerminal();
				 _localctx.ntList.add(((StartContext)_localctx).notTerminal.nt); 
				}
				}
				setState(30);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	public static class NotTerminalContext extends ParserRuleContext {
		public NotTerminal nt;
		public Token JNAME_DEF;
		public InitRulesContext initRules;
		public ArgContext arg;
		public ArgsContext args;
		public TerminalNode JNAME_DEF() { return getToken(GrammarParser.JNAME_DEF, 0); }
		public InitRulesContext initRules() {
			return getRuleContext(InitRulesContext.class,0);
		}
		public TerminalNode RETURNS() { return getToken(GrammarParser.RETURNS, 0); }
		public List<TerminalNode> LPAREN() { return getTokens(GrammarParser.LPAREN); }
		public TerminalNode LPAREN(int i) {
			return getToken(GrammarParser.LPAREN, i);
		}
		public ArgContext arg() {
			return getRuleContext(ArgContext.class,0);
		}
		public List<TerminalNode> RPAREN() { return getTokens(GrammarParser.RPAREN); }
		public TerminalNode RPAREN(int i) {
			return getToken(GrammarParser.RPAREN, i);
		}
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public NotTerminalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_notTerminal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterNotTerminal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitNotTerminal(this);
		}
	}

	public final NotTerminalContext notTerminal() throws RecognitionException {
		NotTerminalContext _localctx = new NotTerminalContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_notTerminal);
		try {
			setState(61);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(31);
				((NotTerminalContext)_localctx).JNAME_DEF = match(JNAME_DEF);
				setState(32);
				((NotTerminalContext)_localctx).initRules = initRules();
				 ((NotTerminalContext)_localctx).nt =  new NotTerminal((((NotTerminalContext)_localctx).JNAME_DEF!=null?((NotTerminalContext)_localctx).JNAME_DEF.getText():null), ((NotTerminalContext)_localctx).initRules.rs);                            
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(35);
				((NotTerminalContext)_localctx).JNAME_DEF = match(JNAME_DEF);
				setState(36);
				match(RETURNS);
				setState(37);
				match(LPAREN);
				setState(38);
				((NotTerminalContext)_localctx).arg = arg();
				setState(39);
				match(RPAREN);
				setState(40);
				((NotTerminalContext)_localctx).initRules = initRules();
				 ((NotTerminalContext)_localctx).nt =  new AttributeNotTerminal((((NotTerminalContext)_localctx).JNAME_DEF!=null?((NotTerminalContext)_localctx).JNAME_DEF.getText():null), ((NotTerminalContext)_localctx).initRules.rs, ((NotTerminalContext)_localctx).arg.a);           
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(43);
				((NotTerminalContext)_localctx).JNAME_DEF = match(JNAME_DEF);
				setState(44);
				match(LPAREN);
				setState(45);
				((NotTerminalContext)_localctx).args = args();
				setState(46);
				match(RPAREN);
				setState(47);
				((NotTerminalContext)_localctx).initRules = initRules();
				 ((NotTerminalContext)_localctx).nt =  new AttributeNotTerminal((((NotTerminalContext)_localctx).JNAME_DEF!=null?((NotTerminalContext)_localctx).JNAME_DEF.getText():null), ((NotTerminalContext)_localctx).initRules.rs, ((NotTerminalContext)_localctx).args.as);         
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(50);
				((NotTerminalContext)_localctx).JNAME_DEF = match(JNAME_DEF);
				setState(51);
				match(LPAREN);
				setState(52);
				((NotTerminalContext)_localctx).args = args();
				setState(53);
				match(RPAREN);
				setState(54);
				match(RETURNS);
				setState(55);
				match(LPAREN);
				setState(56);
				((NotTerminalContext)_localctx).arg = arg();
				setState(57);
				match(RPAREN);
				setState(58);
				((NotTerminalContext)_localctx).initRules = initRules();
				 ((NotTerminalContext)_localctx).nt =  new AttributeNotTerminal((((NotTerminalContext)_localctx).JNAME_DEF!=null?((NotTerminalContext)_localctx).JNAME_DEF.getText():null), ((NotTerminalContext)_localctx).initRules.rs, ((NotTerminalContext)_localctx).args.as, ((NotTerminalContext)_localctx).arg.a); 
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

	public static class ArgsContext extends ParserRuleContext {
		public List<Arg> as;
		public ArgContext arg;
		public List<ArgContext> arg() {
			return getRuleContexts(ArgContext.class);
		}
		public ArgContext arg(int i) {
			return getRuleContext(ArgContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(GrammarParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(GrammarParser.COMMA, i);
		}
		public ArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitArgs(this);
		}
	}

	public final ArgsContext args() throws RecognitionException {
		ArgsContext _localctx = new ArgsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_args);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			((ArgsContext)_localctx).arg = arg();
			 ((ArgsContext)_localctx).as =  new ArrayList<>();  
			 _localctx.as.add(((ArgsContext)_localctx).arg.a);          
			setState(72);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(66);
				match(COMMA);
				setState(67);
				((ArgsContext)_localctx).arg = arg();
				 _localctx.as.add(((ArgsContext)_localctx).arg.a);    
				}
				}
				setState(74);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	public static class ArgContext extends ParserRuleContext {
		public Arg a;
		public TypeContext type;
		public NameContext name;
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public ArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitArg(this);
		}
	}

	public final ArgContext arg() throws RecognitionException {
		ArgContext _localctx = new ArgContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_arg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			((ArgContext)_localctx).type = type();
			setState(76);
			((ArgContext)_localctx).name = name();
			 ((ArgContext)_localctx).a =  new Arg(((ArgContext)_localctx).type.s, ((ArgContext)_localctx).name.s);   
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

	public static class TypeContext extends ParserRuleContext {
		public String s;
		public Token JAVA_CLASS_NAME;
		public Token JNAME_DEF;
		public TerminalNode JAVA_CLASS_NAME() { return getToken(GrammarParser.JAVA_CLASS_NAME, 0); }
		public TerminalNode JNAME_DEF() { return getToken(GrammarParser.JNAME_DEF, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_type);
		try {
			setState(83);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case JAVA_CLASS_NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(79);
				((TypeContext)_localctx).JAVA_CLASS_NAME = match(JAVA_CLASS_NAME);
				 ((TypeContext)_localctx).s =  (((TypeContext)_localctx).JAVA_CLASS_NAME!=null?((TypeContext)_localctx).JAVA_CLASS_NAME.getText():null);   
				}
				break;
			case JNAME_DEF:
				enterOuterAlt(_localctx, 2);
				{
				setState(81);
				((TypeContext)_localctx).JNAME_DEF = match(JNAME_DEF);
				 ((TypeContext)_localctx).s =  (((TypeContext)_localctx).JNAME_DEF!=null?((TypeContext)_localctx).JNAME_DEF.getText():null);         
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

	public static class NameContext extends ParserRuleContext {
		public String s;
		public Token JNAME_DEF;
		public TerminalNode JNAME_DEF() { return getToken(GrammarParser.JNAME_DEF, 0); }
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitName(this);
		}
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			((NameContext)_localctx).JNAME_DEF = match(JNAME_DEF);
			 ((NameContext)_localctx).s =  (((NameContext)_localctx).JNAME_DEF!=null?((NameContext)_localctx).JNAME_DEF.getText():null);  
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

	public static class InitRulesContext extends ParserRuleContext {
		public List<Rule> rs;
		public RulesContext rules;
		public TerminalNode DOUBLE_DOT() { return getToken(GrammarParser.DOUBLE_DOT, 0); }
		public RulesContext rules() {
			return getRuleContext(RulesContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(GrammarParser.SEMI, 0); }
		public InitRulesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initRules; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterInitRules(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitInitRules(this);
		}
	}

	public final InitRulesContext initRules() throws RecognitionException {
		InitRulesContext _localctx = new InitRulesContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_initRules);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			match(DOUBLE_DOT);
			setState(89);
			((InitRulesContext)_localctx).rules = rules();
			setState(90);
			match(SEMI);
			 ((InitRulesContext)_localctx).rs =  ((InitRulesContext)_localctx).rules.rs;   
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

	public static class RulesContext extends ParserRuleContext {
		public List<Rule> rs;
		public RruleContext rrule;
		public List<RruleContext> rrule() {
			return getRuleContexts(RruleContext.class);
		}
		public RruleContext rrule(int i) {
			return getRuleContext(RruleContext.class,i);
		}
		public List<TerminalNode> OVER_RULE() { return getTokens(GrammarParser.OVER_RULE); }
		public TerminalNode OVER_RULE(int i) {
			return getToken(GrammarParser.OVER_RULE, i);
		}
		public RulesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rules; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterRules(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitRules(this);
		}
	}

	public final RulesContext rules() throws RecognitionException {
		RulesContext _localctx = new RulesContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_rules);
		int _la;
		try {
			setState(106);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(93);
				((RulesContext)_localctx).rrule = rrule();
				 ((RulesContext)_localctx).rs =  new ArrayList<>();         
				 _localctx.rs.add(((RulesContext)_localctx).rrule.r);               
				setState(102);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==OVER_RULE) {
					{
					{
					setState(96);
					match(OVER_RULE);
					setState(97);
					((RulesContext)_localctx).rrule = rrule();
					 _localctx.rs.add(((RulesContext)_localctx).rrule.r);               
					}
					}
					setState(104);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				 ((RulesContext)_localctx).rs =  Collections.emptyList();   
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

	public static class RruleContext extends ParserRuleContext {
		public Rule r;
		public NextTokenContext nextToken;
		public List<NextTokenContext> nextToken() {
			return getRuleContexts(NextTokenContext.class);
		}
		public NextTokenContext nextToken(int i) {
			return getRuleContext(NextTokenContext.class,i);
		}
		public RruleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rrule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterRrule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitRrule(this);
		}
	}

	public final RruleContext rrule() throws RecognitionException {
		RruleContext _localctx = new RruleContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_rrule);
		int _la;
		try {
			setState(122);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TERMINAL:
			case JNAME_DEF:
			case JCODE:
				enterOuterAlt(_localctx, 1);
				{
				setState(108);
				((RruleContext)_localctx).nextToken = nextToken();
				 List<LexerValue> tokens = new ArrayList<>();  
				 tokens.add(((RruleContext)_localctx).nextToken.v);                     
				setState(116);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TERMINAL) | (1L << JNAME_DEF) | (1L << JCODE))) != 0)) {
					{
					{
					setState(111);
					((RruleContext)_localctx).nextToken = nextToken();
					 tokens.add(((RruleContext)_localctx).nextToken.v);                     
					}
					}
					setState(118);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				 ((RruleContext)_localctx).r =  new Rule(tokens);                        
				}
				break;
			case SEMI:
			case OVER_RULE:
				enterOuterAlt(_localctx, 2);
				{
				 ((RruleContext)_localctx).r =  new Rule();                              
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

	public static class NextTokenContext extends ParserRuleContext {
		public LexerValue v;
		public Token TERMINAL;
		public Token JNAME_DEF;
		public Token IN_VALUE;
		public Token JCODE;
		public TerminalNode TERMINAL() { return getToken(GrammarParser.TERMINAL, 0); }
		public TerminalNode JNAME_DEF() { return getToken(GrammarParser.JNAME_DEF, 0); }
		public TerminalNode IN_VALUE() { return getToken(GrammarParser.IN_VALUE, 0); }
		public TerminalNode JCODE() { return getToken(GrammarParser.JCODE, 0); }
		public NextTokenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nextToken; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterNextToken(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitNextToken(this);
		}
	}

	public final NextTokenContext nextToken() throws RecognitionException {
		NextTokenContext _localctx = new NextTokenContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_nextToken);
		try {
			setState(133);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(124);
				((NextTokenContext)_localctx).TERMINAL = match(TERMINAL);
				 ((NextTokenContext)_localctx).v =  new Terminal((((NextTokenContext)_localctx).TERMINAL!=null?((NextTokenContext)_localctx).TERMINAL.getText():null));                        
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(126);
				((NextTokenContext)_localctx).JNAME_DEF = match(JNAME_DEF);
				 ((NextTokenContext)_localctx).v =  new UnknownToken((((NextTokenContext)_localctx).JNAME_DEF!=null?((NextTokenContext)_localctx).JNAME_DEF.getText():null));                   
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(128);
				((NextTokenContext)_localctx).JNAME_DEF = match(JNAME_DEF);
				setState(129);
				((NextTokenContext)_localctx).IN_VALUE = match(IN_VALUE);
				 ((NextTokenContext)_localctx).v =  new UnknownToken((((NextTokenContext)_localctx).JNAME_DEF!=null?((NextTokenContext)_localctx).JNAME_DEF.getText():null), (((NextTokenContext)_localctx).IN_VALUE!=null?((NextTokenContext)_localctx).IN_VALUE.getText():null));   
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(131);
				((NextTokenContext)_localctx).JCODE = match(JCODE);
				 ((NextTokenContext)_localctx).v =  new JCode((((NextTokenContext)_localctx).JCODE!=null?((NextTokenContext)_localctx).JCODE.getText():null));                              
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\32\u008a\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\3\2\3\2\3\2\3\2\3\2\3\2\7\2\35\n\2\f\2\16\2 \13\2\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3@\n\3\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\7\4I\n\4\f\4\16\4L\13\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\5\6V\n\6"+
		"\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\7\tg\n\t"+
		"\f\t\16\tj\13\t\3\t\5\tm\n\t\3\n\3\n\3\n\3\n\3\n\3\n\7\nu\n\n\f\n\16\n"+
		"x\13\n\3\n\3\n\3\n\5\n}\n\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\5\13\u0088\n\13\3\13\2\2\f\2\4\6\b\n\f\16\20\22\24\2\2\2\u008c\2\26"+
		"\3\2\2\2\4?\3\2\2\2\6A\3\2\2\2\bM\3\2\2\2\nU\3\2\2\2\fW\3\2\2\2\16Z\3"+
		"\2\2\2\20l\3\2\2\2\22|\3\2\2\2\24\u0087\3\2\2\2\26\27\5\4\3\2\27\30\b"+
		"\2\1\2\30\36\b\2\1\2\31\32\5\4\3\2\32\33\b\2\1\2\33\35\3\2\2\2\34\31\3"+
		"\2\2\2\35 \3\2\2\2\36\34\3\2\2\2\36\37\3\2\2\2\37\3\3\2\2\2 \36\3\2\2"+
		"\2!\"\7\22\2\2\"#\5\16\b\2#$\b\3\1\2$@\3\2\2\2%&\7\22\2\2&\'\7\20\2\2"+
		"\'(\7\3\2\2()\5\b\5\2)*\7\4\2\2*+\5\16\b\2+,\b\3\1\2,@\3\2\2\2-.\7\22"+
		"\2\2./\7\3\2\2/\60\5\6\4\2\60\61\7\4\2\2\61\62\5\16\b\2\62\63\b\3\1\2"+
		"\63@\3\2\2\2\64\65\7\22\2\2\65\66\7\3\2\2\66\67\5\6\4\2\678\7\4\2\289"+
		"\7\20\2\29:\7\3\2\2:;\5\b\5\2;<\7\4\2\2<=\5\16\b\2=>\b\3\1\2>@\3\2\2\2"+
		"?!\3\2\2\2?%\3\2\2\2?-\3\2\2\2?\64\3\2\2\2@\5\3\2\2\2AB\5\b\5\2BC\b\4"+
		"\1\2CJ\b\4\1\2DE\7\n\2\2EF\5\b\5\2FG\b\4\1\2GI\3\2\2\2HD\3\2\2\2IL\3\2"+
		"\2\2JH\3\2\2\2JK\3\2\2\2K\7\3\2\2\2LJ\3\2\2\2MN\5\n\6\2NO\5\f\7\2OP\b"+
		"\5\1\2P\t\3\2\2\2QR\7\23\2\2RV\b\6\1\2ST\7\22\2\2TV\b\6\1\2UQ\3\2\2\2"+
		"US\3\2\2\2V\13\3\2\2\2WX\7\22\2\2XY\b\7\1\2Y\r\3\2\2\2Z[\7\16\2\2[\\\5"+
		"\20\t\2\\]\7\t\2\2]^\b\b\1\2^\17\3\2\2\2_`\5\22\n\2`a\b\t\1\2ah\b\t\1"+
		"\2bc\7\17\2\2cd\5\22\n\2de\b\t\1\2eg\3\2\2\2fb\3\2\2\2gj\3\2\2\2hf\3\2"+
		"\2\2hi\3\2\2\2im\3\2\2\2jh\3\2\2\2km\b\t\1\2l_\3\2\2\2lk\3\2\2\2m\21\3"+
		"\2\2\2no\5\24\13\2op\b\n\1\2pv\b\n\1\2qr\5\24\13\2rs\b\n\1\2su\3\2\2\2"+
		"tq\3\2\2\2ux\3\2\2\2vt\3\2\2\2vw\3\2\2\2wy\3\2\2\2xv\3\2\2\2yz\b\n\1\2"+
		"z}\3\2\2\2{}\b\n\1\2|n\3\2\2\2|{\3\2\2\2}\23\3\2\2\2~\177\7\21\2\2\177"+
		"\u0088\b\13\1\2\u0080\u0081\7\22\2\2\u0081\u0088\b\13\1\2\u0082\u0083"+
		"\7\22\2\2\u0083\u0084\7\27\2\2\u0084\u0088\b\13\1\2\u0085\u0086\7\26\2"+
		"\2\u0086\u0088\b\13\1\2\u0087~\3\2\2\2\u0087\u0080\3\2\2\2\u0087\u0082"+
		"\3\2\2\2\u0087\u0085\3\2\2\2\u0088\25\3\2\2\2\13\36?JUhlv|\u0087";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}