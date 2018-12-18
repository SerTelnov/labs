// Generated from C:/pro/homework/labs/translation-methods/parsergenerator/src/main/antlr4\GrammarLexer.g4 by ANTLR 4.7

    package ru.telnov.labs.translationmethods.parsergenerator;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		LPAREN=1, RPAREN=2, LBRACE=3, RBRACE=4, LBRACK=5, RBRACK=6, SEMI=7, COMMA=8, 
		DOT=9, MARK=10, ARRAY=11, DOUBLE_DOT=12, OVER_RULE=13, TERMINAL=14, NOT_TERMINAL=15, 
		TOKEN_VALUE=16, REGEX_VALUE=17, WS=18, COMMENT=19, LINE_COMMENT=20;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"LPAREN", "RPAREN", "LBRACE", "RBRACE", "LBRACK", "RBRACK", "SEMI", "COMMA", 
		"DOT", "MARK", "ARRAY", "DOUBLE_DOT", "OVER_RULE", "TERMINAL", "NOT_TERMINAL", 
		"TOKEN_VALUE", "REGEX_VALUE", "WS", "COMMENT", "LINE_COMMENT"
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


	public GrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "GrammarLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\26\u008a\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3"+
		"\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3"+
		"\16\3\16\3\17\3\17\7\17I\n\17\f\17\16\17L\13\17\3\20\3\20\7\20P\n\20\f"+
		"\20\16\20S\13\20\3\21\3\21\6\21W\n\21\r\21\16\21X\3\21\3\21\3\22\3\22"+
		"\6\22_\n\22\r\22\16\22`\3\22\3\22\5\22e\n\22\6\22g\n\22\r\22\16\22h\3"+
		"\23\6\23l\n\23\r\23\16\23m\3\23\3\23\3\24\3\24\3\24\3\24\7\24v\n\24\f"+
		"\24\16\24y\13\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\7\25\u0084"+
		"\n\25\f\25\16\25\u0087\13\25\3\25\3\25\3w\2\26\3\3\5\4\7\5\t\6\13\7\r"+
		"\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25"+
		")\26\3\2\t\3\2C\\\5\2\62;C\\aa\3\2c|\5\2\62;C\\c|\4\2\f\f\17\17\4\2,-"+
		"AA\5\2\13\f\17\17\"\"\2\u0092\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t"+
		"\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2"+
		"\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2"+
		"\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\3"+
		"+\3\2\2\2\5-\3\2\2\2\7/\3\2\2\2\t\61\3\2\2\2\13\63\3\2\2\2\r\65\3\2\2"+
		"\2\17\67\3\2\2\2\219\3\2\2\2\23;\3\2\2\2\25=\3\2\2\2\27?\3\2\2\2\31B\3"+
		"\2\2\2\33D\3\2\2\2\35F\3\2\2\2\37M\3\2\2\2!T\3\2\2\2#f\3\2\2\2%k\3\2\2"+
		"\2\'q\3\2\2\2)\177\3\2\2\2+,\7*\2\2,\4\3\2\2\2-.\7+\2\2.\6\3\2\2\2/\60"+
		"\7}\2\2\60\b\3\2\2\2\61\62\7\177\2\2\62\n\3\2\2\2\63\64\7]\2\2\64\f\3"+
		"\2\2\2\65\66\7_\2\2\66\16\3\2\2\2\678\7=\2\28\20\3\2\2\29:\7.\2\2:\22"+
		"\3\2\2\2;<\7\60\2\2<\24\3\2\2\2=>\7$\2\2>\26\3\2\2\2?@\7]\2\2@A\7_\2\2"+
		"A\30\3\2\2\2BC\7<\2\2C\32\3\2\2\2DE\7~\2\2E\34\3\2\2\2FJ\t\2\2\2GI\t\3"+
		"\2\2HG\3\2\2\2IL\3\2\2\2JH\3\2\2\2JK\3\2\2\2K\36\3\2\2\2LJ\3\2\2\2MQ\t"+
		"\4\2\2NP\t\5\2\2ON\3\2\2\2PS\3\2\2\2QO\3\2\2\2QR\3\2\2\2R \3\2\2\2SQ\3"+
		"\2\2\2TV\7)\2\2UW\n\6\2\2VU\3\2\2\2WX\3\2\2\2XV\3\2\2\2XY\3\2\2\2YZ\3"+
		"\2\2\2Z[\7)\2\2[\"\3\2\2\2\\^\7]\2\2]_\n\6\2\2^]\3\2\2\2_`\3\2\2\2`^\3"+
		"\2\2\2`a\3\2\2\2ab\3\2\2\2bd\7_\2\2ce\t\7\2\2dc\3\2\2\2de\3\2\2\2eg\3"+
		"\2\2\2f\\\3\2\2\2gh\3\2\2\2hf\3\2\2\2hi\3\2\2\2i$\3\2\2\2jl\t\b\2\2kj"+
		"\3\2\2\2lm\3\2\2\2mk\3\2\2\2mn\3\2\2\2no\3\2\2\2op\b\23\2\2p&\3\2\2\2"+
		"qr\7\61\2\2rs\7,\2\2sw\3\2\2\2tv\13\2\2\2ut\3\2\2\2vy\3\2\2\2wx\3\2\2"+
		"\2wu\3\2\2\2xz\3\2\2\2yw\3\2\2\2z{\7,\2\2{|\7\61\2\2|}\3\2\2\2}~\b\24"+
		"\2\2~(\3\2\2\2\177\u0080\7\61\2\2\u0080\u0081\7\61\2\2\u0081\u0085\3\2"+
		"\2\2\u0082\u0084\n\6\2\2\u0083\u0082\3\2\2\2\u0084\u0087\3\2\2\2\u0085"+
		"\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0088\3\2\2\2\u0087\u0085\3\2"+
		"\2\2\u0088\u0089\b\25\2\2\u0089*\3\2\2\2\f\2JQX`dhmw\u0085\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}