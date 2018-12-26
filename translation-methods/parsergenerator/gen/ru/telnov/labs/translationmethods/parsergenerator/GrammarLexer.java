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
		DOT=9, MARK=10, ARRAY=11, DOUBLE_DOT=12, OVER_RULE=13, RETURNS=14, TERMINAL=15, 
		JNAME_DEF=16, JAVA_CLASS_NAME=17, TOKEN_VALUE=18, REGEX_VALUE=19, JCODE=20, 
		IN_VALUE=21, WS=22, COMMENT=23, LINE_COMMENT=24;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"LPAREN", "RPAREN", "LBRACE", "RBRACE", "LBRACK", "RBRACK", "SEMI", "COMMA", 
		"DOT", "MARK", "ARRAY", "DOUBLE_DOT", "OVER_RULE", "RETURNS", "TERMINAL", 
		"JNAME_DEF", "JAVA_CLASS_NAME", "TOKEN_VALUE", "REGEX_VALUE", "JCODE", 
		"IN_VALUE", "WS", "COMMENT", "LINE_COMMENT"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\32\u00b4\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t"+
		"\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\20\3\20\7\20Y\n\20\f\20\16\20\\\13\20\3\21\3\21\7"+
		"\21`\n\21\f\21\16\21c\13\21\3\22\3\22\7\22g\n\22\f\22\16\22j\13\22\3\23"+
		"\3\23\6\23n\n\23\r\23\16\23o\3\23\3\23\3\24\3\24\6\24v\n\24\r\24\16\24"+
		"w\3\24\3\24\7\24|\n\24\f\24\16\24\177\13\24\6\24\u0081\n\24\r\24\16\24"+
		"\u0082\3\25\3\25\6\25\u0087\n\25\r\25\16\25\u0088\3\25\3\25\3\26\3\26"+
		"\6\26\u008f\n\26\r\26\16\26\u0090\3\26\3\26\3\27\6\27\u0096\n\27\r\27"+
		"\16\27\u0097\3\27\3\27\3\30\3\30\3\30\3\30\7\30\u00a0\n\30\f\30\16\30"+
		"\u00a3\13\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\7\31\u00ae\n"+
		"\31\f\31\16\31\u00b1\13\31\3\31\3\31\3\u00a1\2\32\3\3\5\4\7\5\t\6\13\7"+
		"\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25"+
		")\26+\27-\30/\31\61\32\3\2\t\3\2C\\\5\2\62;C\\aa\3\2c|\5\2\62;C\\c|\4"+
		"\2\f\f\17\17\5\2##,-AA\5\2\13\f\17\17\"\"\2\u00bf\2\3\3\2\2\2\2\5\3\2"+
		"\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\3\63\3"+
		"\2\2\2\5\65\3\2\2\2\7\67\3\2\2\2\t9\3\2\2\2\13;\3\2\2\2\r=\3\2\2\2\17"+
		"?\3\2\2\2\21A\3\2\2\2\23C\3\2\2\2\25E\3\2\2\2\27G\3\2\2\2\31J\3\2\2\2"+
		"\33L\3\2\2\2\35N\3\2\2\2\37V\3\2\2\2!]\3\2\2\2#d\3\2\2\2%k\3\2\2\2\'\u0080"+
		"\3\2\2\2)\u0084\3\2\2\2+\u008c\3\2\2\2-\u0095\3\2\2\2/\u009b\3\2\2\2\61"+
		"\u00a9\3\2\2\2\63\64\7*\2\2\64\4\3\2\2\2\65\66\7+\2\2\66\6\3\2\2\2\67"+
		"8\7}\2\28\b\3\2\2\29:\7\177\2\2:\n\3\2\2\2;<\7]\2\2<\f\3\2\2\2=>\7_\2"+
		"\2>\16\3\2\2\2?@\7=\2\2@\20\3\2\2\2AB\7.\2\2B\22\3\2\2\2CD\7\60\2\2D\24"+
		"\3\2\2\2EF\7$\2\2F\26\3\2\2\2GH\7]\2\2HI\7_\2\2I\30\3\2\2\2JK\7<\2\2K"+
		"\32\3\2\2\2LM\7~\2\2M\34\3\2\2\2NO\7t\2\2OP\7g\2\2PQ\7v\2\2QR\7w\2\2R"+
		"S\7t\2\2ST\7p\2\2TU\7u\2\2U\36\3\2\2\2VZ\t\2\2\2WY\t\3\2\2XW\3\2\2\2Y"+
		"\\\3\2\2\2ZX\3\2\2\2Z[\3\2\2\2[ \3\2\2\2\\Z\3\2\2\2]a\t\4\2\2^`\t\5\2"+
		"\2_^\3\2\2\2`c\3\2\2\2a_\3\2\2\2ab\3\2\2\2b\"\3\2\2\2ca\3\2\2\2dh\t\2"+
		"\2\2eg\t\5\2\2fe\3\2\2\2gj\3\2\2\2hf\3\2\2\2hi\3\2\2\2i$\3\2\2\2jh\3\2"+
		"\2\2km\7)\2\2ln\n\6\2\2ml\3\2\2\2no\3\2\2\2om\3\2\2\2op\3\2\2\2pq\3\2"+
		"\2\2qr\7)\2\2r&\3\2\2\2su\7]\2\2tv\n\6\2\2ut\3\2\2\2vw\3\2\2\2wu\3\2\2"+
		"\2wx\3\2\2\2xy\3\2\2\2y}\7_\2\2z|\t\7\2\2{z\3\2\2\2|\177\3\2\2\2}{\3\2"+
		"\2\2}~\3\2\2\2~\u0081\3\2\2\2\177}\3\2\2\2\u0080s\3\2\2\2\u0081\u0082"+
		"\3\2\2\2\u0082\u0080\3\2\2\2\u0082\u0083\3\2\2\2\u0083(\3\2\2\2\u0084"+
		"\u0086\7}\2\2\u0085\u0087\n\6\2\2\u0086\u0085\3\2\2\2\u0087\u0088\3\2"+
		"\2\2\u0088\u0086\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u008a\3\2\2\2\u008a"+
		"\u008b\7\177\2\2\u008b*\3\2\2\2\u008c\u008e\7>\2\2\u008d\u008f\n\6\2\2"+
		"\u008e\u008d\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u008e\3\2\2\2\u0090\u0091"+
		"\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0093\7@\2\2\u0093,\3\2\2\2\u0094\u0096"+
		"\t\b\2\2\u0095\u0094\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0095\3\2\2\2\u0097"+
		"\u0098\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u009a\b\27\2\2\u009a.\3\2\2\2"+
		"\u009b\u009c\7\61\2\2\u009c\u009d\7,\2\2\u009d\u00a1\3\2\2\2\u009e\u00a0"+
		"\13\2\2\2\u009f\u009e\3\2\2\2\u00a0\u00a3\3\2\2\2\u00a1\u00a2\3\2\2\2"+
		"\u00a1\u009f\3\2\2\2\u00a2\u00a4\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a4\u00a5"+
		"\7,\2\2\u00a5\u00a6\7\61\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a8\b\30\2\2"+
		"\u00a8\60\3\2\2\2\u00a9\u00aa\7\61\2\2\u00aa\u00ab\7\61\2\2\u00ab\u00af"+
		"\3\2\2\2\u00ac\u00ae\n\6\2\2\u00ad\u00ac\3\2\2\2\u00ae\u00b1\3\2\2\2\u00af"+
		"\u00ad\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b2\3\2\2\2\u00b1\u00af\3\2"+
		"\2\2\u00b2\u00b3\b\31\2\2\u00b3\62\3\2\2\2\17\2Zahow}\u0082\u0088\u0090"+
		"\u0097\u00a1\u00af\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}