// Generated from C:/pro/homework/labs/translation-methods/parsergenerator/src/main/antlr4\GramLexParser.g4 by ANTLR 4.7

    package ru.telnov.labs.translationmethods.parsergenerator;

    import ru.telnov.labs.translationmethods.parsergenerator.tokens.Terminal;
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
public class GramLexParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		LPAREN=1, RPAREN=2, LBRACE=3, RBRACE=4, LBRACK=5, RBRACK=6, SEMI=7, COMMA=8, 
		DOT=9, MARK=10, ARRAY=11, DOUBLE_DOT=12, OVER_RULE=13, TERMINAL=14, NOT_TERMINAL=15, 
		TOKEN_VALUE=16, REGEX_VALUE=17, WS=18, COMMENT=19, LINE_COMMENT=20;
	public static final int
		RULE_parseTokens = 0, RULE_terminal = 1, RULE_value = 2;
	public static final String[] ruleNames = {
		"parseTokens", "terminal", "value"
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
	public String getGrammarFileName() { return "GramLexParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public GramLexParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ParseTokensContext extends ParserRuleContext {
		public List<Terminal> trmls;
		public TerminalContext terminal;
		public List<TerminalContext> terminal() {
			return getRuleContexts(TerminalContext.class);
		}
		public TerminalContext terminal(int i) {
			return getRuleContext(TerminalContext.class,i);
		}
		public ParseTokensContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parseTokens; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GramLexParserListener ) ((GramLexParserListener)listener).enterParseTokens(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GramLexParserListener ) ((GramLexParserListener)listener).exitParseTokens(this);
		}
	}

	public final ParseTokensContext parseTokens() throws RecognitionException {
		ParseTokensContext _localctx = new ParseTokensContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_parseTokens);
		int _la;
		try {
			setState(18);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TERMINAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(6);
				((ParseTokensContext)_localctx).terminal = terminal();
				 ((ParseTokensContext)_localctx).trmls =  new ArrayList<>();       
				 _localctx.trmls.add(((ParseTokensContext)_localctx).terminal.t);          
				setState(14);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==TERMINAL) {
					{
					{
					setState(9);
					((ParseTokensContext)_localctx).terminal = terminal();
					 _localctx.trmls.add(((ParseTokensContext)_localctx).terminal.t);          
					}
					}
					setState(16);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case EOF:
				enterOuterAlt(_localctx, 2);
				{
				 ((ParseTokensContext)_localctx).trmls =  Collections.emptyList(); 
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

	public static class TerminalContext extends ParserRuleContext {
		public Terminal t;
		public Token TERMINAL;
		public ValueContext value;
		public TerminalNode TERMINAL() { return getToken(GramLexParser.TERMINAL, 0); }
		public TerminalNode DOUBLE_DOT() { return getToken(GramLexParser.DOUBLE_DOT, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(GramLexParser.SEMI, 0); }
		public TerminalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_terminal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GramLexParserListener ) ((GramLexParserListener)listener).enterTerminal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GramLexParserListener ) ((GramLexParserListener)listener).exitTerminal(this);
		}
	}

	public final TerminalContext terminal() throws RecognitionException {
		TerminalContext _localctx = new TerminalContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_terminal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(20);
			((TerminalContext)_localctx).TERMINAL = match(TERMINAL);
			setState(21);
			match(DOUBLE_DOT);
			setState(22);
			((TerminalContext)_localctx).value = value();
			setState(23);
			match(SEMI);
			 ((TerminalContext)_localctx).t =  new Terminal((((TerminalContext)_localctx).TERMINAL!=null?((TerminalContext)_localctx).TERMINAL.getText():null), ((TerminalContext)_localctx).value.v); 
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

	public static class ValueContext extends ParserRuleContext {
		public String v;
		public Token TOKEN_VALUE;
		public Token REGEX_VALUE;
		public TerminalNode TOKEN_VALUE() { return getToken(GramLexParser.TOKEN_VALUE, 0); }
		public TerminalNode REGEX_VALUE() { return getToken(GramLexParser.REGEX_VALUE, 0); }
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GramLexParserListener ) ((GramLexParserListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GramLexParserListener ) ((GramLexParserListener)listener).exitValue(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_value);
		try {
			setState(30);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TOKEN_VALUE:
				enterOuterAlt(_localctx, 1);
				{
				setState(26);
				((ValueContext)_localctx).TOKEN_VALUE = match(TOKEN_VALUE);
				 ((ValueContext)_localctx).v =  (((ValueContext)_localctx).TOKEN_VALUE!=null?((ValueContext)_localctx).TOKEN_VALUE.getText():null);    
				}
				break;
			case REGEX_VALUE:
				enterOuterAlt(_localctx, 2);
				{
				setState(28);
				((ValueContext)_localctx).REGEX_VALUE = match(REGEX_VALUE);
				 ((ValueContext)_localctx).v =  (((ValueContext)_localctx).REGEX_VALUE!=null?((ValueContext)_localctx).REGEX_VALUE.getText():null);    
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\26#\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\3\2\3\2\3\2\3\2\3\2\3\2\7\2\17\n\2\f\2\16\2\22\13\2\3\2\5\2"+
		"\25\n\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\5\4!\n\4\3\4\2\2\5\2\4"+
		"\6\2\2\2\"\2\24\3\2\2\2\4\26\3\2\2\2\6 \3\2\2\2\b\t\5\4\3\2\t\n\b\2\1"+
		"\2\n\20\b\2\1\2\13\f\5\4\3\2\f\r\b\2\1\2\r\17\3\2\2\2\16\13\3\2\2\2\17"+
		"\22\3\2\2\2\20\16\3\2\2\2\20\21\3\2\2\2\21\25\3\2\2\2\22\20\3\2\2\2\23"+
		"\25\b\2\1\2\24\b\3\2\2\2\24\23\3\2\2\2\25\3\3\2\2\2\26\27\7\20\2\2\27"+
		"\30\7\16\2\2\30\31\5\6\4\2\31\32\7\t\2\2\32\33\b\3\1\2\33\5\3\2\2\2\34"+
		"\35\7\22\2\2\35!\b\4\1\2\36\37\7\23\2\2\37!\b\4\1\2 \34\3\2\2\2 \36\3"+
		"\2\2\2!\7\3\2\2\2\5\20\24 ";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}