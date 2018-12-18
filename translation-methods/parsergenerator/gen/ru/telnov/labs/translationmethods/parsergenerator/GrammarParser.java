// Generated from C:/pro/homework/labs/translation-methods/parsergenerator/src/main/antlr4\GrammarParser.g4 by ANTLR 4.7

    package ru.telnov.labs.translationmethods.parsergenerator;

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
		DOT=9, MARK=10, ARRAY=11, DOUBLE_DOT=12, OVER_RULE=13, TERMINAL=14, NOT_TERMINAL=15, 
		TOKEN_VALUE=16, REGEX_VALUE=17, WS=18, COMMENT=19, LINE_COMMENT=20;
	public static final int
		RULE_start = 0, RULE_notTerminal = 1, RULE_initRules = 2, RULE_rrule = 3, 
		RULE_nextToken = 4;
	public static final String[] ruleNames = {
		"start", "notTerminal", "initRules", "rrule", "nextToken"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "'{'", "'}'", "'['", "']'", "';'", "','", "'.'", "'\"'", 
		"'[]'", "':'", "'|'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "LPAREN", "RPAREN", "LBRACE", "RBRACE", "LBRACK", "RBRACK", "SEMI", 
		"COMMA", "DOT", "MARK", "ARRAY", "DOUBLE_DOT", "OVER_RULE", "TERMINAL", 
		"NOT_TERMINAL", "TOKEN_VALUE", "REGEX_VALUE", "WS", "COMMENT", "LINE_COMMENT"
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
			setState(10);
			((StartContext)_localctx).notTerminal = notTerminal();
			 ((StartContext)_localctx).ntList =  new ArrayList<>();  
			 _localctx.ntList.add(((StartContext)_localctx).notTerminal.nt); 
			setState(18);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NOT_TERMINAL) {
				{
				{
				setState(13);
				((StartContext)_localctx).notTerminal = notTerminal();
				 _localctx.ntList.add(((StartContext)_localctx).notTerminal.nt); 
				}
				}
				setState(20);
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
		public Token NOT_TERMINAL;
		public InitRulesContext initRules;
		public TerminalNode NOT_TERMINAL() { return getToken(GrammarParser.NOT_TERMINAL, 0); }
		public TerminalNode DOUBLE_DOT() { return getToken(GrammarParser.DOUBLE_DOT, 0); }
		public InitRulesContext initRules() {
			return getRuleContext(InitRulesContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(GrammarParser.SEMI, 0); }
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
			enterOuterAlt(_localctx, 1);
			{
			setState(21);
			((NotTerminalContext)_localctx).NOT_TERMINAL = match(NOT_TERMINAL);
			setState(22);
			match(DOUBLE_DOT);
			setState(23);
			((NotTerminalContext)_localctx).initRules = initRules();
			setState(24);
			match(SEMI);
			 ((NotTerminalContext)_localctx).nt =  new NotTerminal((((NotTerminalContext)_localctx).NOT_TERMINAL!=null?((NotTerminalContext)_localctx).NOT_TERMINAL.getText():null), ((NotTerminalContext)_localctx).initRules.rules); 
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
		public List<Rule> rules;
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
		enterRule(_localctx, 4, RULE_initRules);
		int _la;
		try {
			setState(40);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(27);
				((InitRulesContext)_localctx).rrule = rrule();
				 ((InitRulesContext)_localctx).rules =  new ArrayList<>();         
				 _localctx.rules.add(((InitRulesContext)_localctx).rrule.r);               
				setState(36);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==OVER_RULE) {
					{
					{
					setState(30);
					match(OVER_RULE);
					setState(31);
					((InitRulesContext)_localctx).rrule = rrule();
					 _localctx.rules.add(((InitRulesContext)_localctx).rrule.r);               
					}
					}
					setState(38);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				 ((InitRulesContext)_localctx).rules =  Collections.emptyList();   
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
		enterRule(_localctx, 6, RULE_rrule);
		int _la;
		try {
			setState(56);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TERMINAL:
			case NOT_TERMINAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(42);
				((RruleContext)_localctx).nextToken = nextToken();
				 List<LexerToken> tokens = new ArrayList<>();  
				 tokens.add(((RruleContext)_localctx).nextToken.t);                     
				setState(50);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==TERMINAL || _la==NOT_TERMINAL) {
					{
					{
					setState(45);
					((RruleContext)_localctx).nextToken = nextToken();
					 tokens.add(((RruleContext)_localctx).nextToken.t);                     
					}
					}
					setState(52);
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
		public LexerToken t;
		public Token TERMINAL;
		public Token NOT_TERMINAL;
		public TerminalNode TERMINAL() { return getToken(GrammarParser.TERMINAL, 0); }
		public TerminalNode NOT_TERMINAL() { return getToken(GrammarParser.NOT_TERMINAL, 0); }
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
		enterRule(_localctx, 8, RULE_nextToken);
		try {
			setState(62);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TERMINAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(58);
				((NextTokenContext)_localctx).TERMINAL = match(TERMINAL);
				 ((NextTokenContext)_localctx).t =  new Terminal((((NextTokenContext)_localctx).TERMINAL!=null?((NextTokenContext)_localctx).TERMINAL.getText():null));            
				}
				break;
			case NOT_TERMINAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(60);
				((NextTokenContext)_localctx).NOT_TERMINAL = match(NOT_TERMINAL);
				 ((NextTokenContext)_localctx).t =  new UnknownToken((((NextTokenContext)_localctx).NOT_TERMINAL!=null?((NextTokenContext)_localctx).NOT_TERMINAL.getText():null));    
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\26C\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\3\2\3\2\3\2\3\2\3\2\7\2\23\n\2\f\2\16\2"+
		"\26\13\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4%\n\4"+
		"\f\4\16\4(\13\4\3\4\5\4+\n\4\3\5\3\5\3\5\3\5\3\5\3\5\7\5\63\n\5\f\5\16"+
		"\5\66\13\5\3\5\3\5\3\5\5\5;\n\5\3\6\3\6\3\6\3\6\5\6A\n\6\3\6\2\2\7\2\4"+
		"\6\b\n\2\2\2C\2\f\3\2\2\2\4\27\3\2\2\2\6*\3\2\2\2\b:\3\2\2\2\n@\3\2\2"+
		"\2\f\r\5\4\3\2\r\16\b\2\1\2\16\24\b\2\1\2\17\20\5\4\3\2\20\21\b\2\1\2"+
		"\21\23\3\2\2\2\22\17\3\2\2\2\23\26\3\2\2\2\24\22\3\2\2\2\24\25\3\2\2\2"+
		"\25\3\3\2\2\2\26\24\3\2\2\2\27\30\7\21\2\2\30\31\7\16\2\2\31\32\5\6\4"+
		"\2\32\33\7\t\2\2\33\34\b\3\1\2\34\5\3\2\2\2\35\36\5\b\5\2\36\37\b\4\1"+
		"\2\37&\b\4\1\2 !\7\17\2\2!\"\5\b\5\2\"#\b\4\1\2#%\3\2\2\2$ \3\2\2\2%("+
		"\3\2\2\2&$\3\2\2\2&\'\3\2\2\2\'+\3\2\2\2(&\3\2\2\2)+\b\4\1\2*\35\3\2\2"+
		"\2*)\3\2\2\2+\7\3\2\2\2,-\5\n\6\2-.\b\5\1\2.\64\b\5\1\2/\60\5\n\6\2\60"+
		"\61\b\5\1\2\61\63\3\2\2\2\62/\3\2\2\2\63\66\3\2\2\2\64\62\3\2\2\2\64\65"+
		"\3\2\2\2\65\67\3\2\2\2\66\64\3\2\2\2\678\b\5\1\28;\3\2\2\29;\b\5\1\2:"+
		",\3\2\2\2:9\3\2\2\2;\t\3\2\2\2<=\7\20\2\2=A\b\6\1\2>?\7\21\2\2?A\b\6\1"+
		"\2@<\3\2\2\2@>\3\2\2\2A\13\3\2\2\2\b\24&*\64:@";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}