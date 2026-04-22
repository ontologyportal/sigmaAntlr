// Generated from Suokif.g4 by ANTLR 4.9.3
package com.articulate.sigma.parsing;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SuokifLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, FUNWORD=12, IDENTIFIER=13, NUMBER=14, NUMIDENTIFIER=15, 
		WORDCHAR=16, STRING=17, COMMENT=18, REGVAR=19, ROWVAR=20, EXPONENT=21, 
		LETTER=22, DIGIT=23, WHITESPACE=24;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "FUNWORD", "IDENTIFIER", "NUMBER", "NUMIDENTIFIER", 
			"WORDCHAR", "STRING", "COMMENT", "REGVAR", "ROWVAR", "EXPONENT", "LETTER", 
			"DIGIT", "WHITESPACE"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "'exists'", "')'", "'forall'", "'<=>'", "'=>'", "'and'", 
			"'or'", "'xor'", "'not'", "'equal'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			"FUNWORD", "IDENTIFIER", "NUMBER", "NUMIDENTIFIER", "WORDCHAR", "STRING", 
			"COMMENT", "REGVAR", "ROWVAR", "EXPONENT", "LETTER", "DIGIT", "WHITESPACE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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


	public SuokifLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Suokif.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\32\u00c9\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3"+
		"\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\7\rd\n"+
		"\r\f\r\16\rg\13\r\3\r\3\r\3\r\3\16\3\16\7\16n\n\16\f\16\16\16q\13\16\3"+
		"\17\5\17t\n\17\3\17\6\17w\n\17\r\17\16\17x\3\17\3\17\6\17}\n\17\r\17\16"+
		"\17~\5\17\u0081\n\17\3\17\5\17\u0084\n\17\3\20\3\20\7\20\u0088\n\20\f"+
		"\20\16\20\u008b\13\20\3\20\3\20\7\20\u008f\n\20\f\20\16\20\u0092\13\20"+
		"\3\21\3\21\3\21\5\21\u0097\n\21\3\22\3\22\6\22\u009b\n\22\r\22\16\22\u009c"+
		"\3\22\3\22\3\23\3\23\7\23\u00a3\n\23\f\23\16\23\u00a6\13\23\3\23\3\23"+
		"\3\24\3\24\6\24\u00ac\n\24\r\24\16\24\u00ad\3\25\3\25\6\25\u00b2\n\25"+
		"\r\25\16\25\u00b3\3\26\3\26\5\26\u00b8\n\26\3\26\6\26\u00bb\n\26\r\26"+
		"\16\26\u00bc\3\27\3\27\3\30\3\30\3\31\6\31\u00c4\n\31\r\31\16\31\u00c5"+
		"\3\31\3\31\2\2\32\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31"+
		"\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\3\2\t\4\2"+
		"..\60\60\4\2//aa\3\2$$\4\2\f\f\17\17\4\2C\\c|\3\2\62;\5\2\13\f\17\17\""+
		"\"\2\u00da\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2"+
		"\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2"+
		"\2/\3\2\2\2\2\61\3\2\2\2\3\63\3\2\2\2\5\65\3\2\2\2\7<\3\2\2\2\t>\3\2\2"+
		"\2\13E\3\2\2\2\rI\3\2\2\2\17L\3\2\2\2\21P\3\2\2\2\23S\3\2\2\2\25W\3\2"+
		"\2\2\27[\3\2\2\2\31a\3\2\2\2\33k\3\2\2\2\35s\3\2\2\2\37\u0085\3\2\2\2"+
		"!\u0096\3\2\2\2#\u0098\3\2\2\2%\u00a0\3\2\2\2\'\u00a9\3\2\2\2)\u00af\3"+
		"\2\2\2+\u00b5\3\2\2\2-\u00be\3\2\2\2/\u00c0\3\2\2\2\61\u00c3\3\2\2\2\63"+
		"\64\7*\2\2\64\4\3\2\2\2\65\66\7g\2\2\66\67\7z\2\2\678\7k\2\289\7u\2\2"+
		"9:\7v\2\2:;\7u\2\2;\6\3\2\2\2<=\7+\2\2=\b\3\2\2\2>?\7h\2\2?@\7q\2\2@A"+
		"\7t\2\2AB\7c\2\2BC\7n\2\2CD\7n\2\2D\n\3\2\2\2EF\7>\2\2FG\7?\2\2GH\7@\2"+
		"\2H\f\3\2\2\2IJ\7?\2\2JK\7@\2\2K\16\3\2\2\2LM\7c\2\2MN\7p\2\2NO\7f\2\2"+
		"O\20\3\2\2\2PQ\7q\2\2QR\7t\2\2R\22\3\2\2\2ST\7z\2\2TU\7q\2\2UV\7t\2\2"+
		"V\24\3\2\2\2WX\7p\2\2XY\7q\2\2YZ\7v\2\2Z\26\3\2\2\2[\\\7g\2\2\\]\7s\2"+
		"\2]^\7w\2\2^_\7c\2\2_`\7n\2\2`\30\3\2\2\2ae\5-\27\2bd\5!\21\2cb\3\2\2"+
		"\2dg\3\2\2\2ec\3\2\2\2ef\3\2\2\2fh\3\2\2\2ge\3\2\2\2hi\7H\2\2ij\7p\2\2"+
		"j\32\3\2\2\2ko\5-\27\2ln\5!\21\2ml\3\2\2\2nq\3\2\2\2om\3\2\2\2op\3\2\2"+
		"\2p\34\3\2\2\2qo\3\2\2\2rt\7/\2\2sr\3\2\2\2st\3\2\2\2tv\3\2\2\2uw\5/\30"+
		"\2vu\3\2\2\2wx\3\2\2\2xv\3\2\2\2xy\3\2\2\2y\u0080\3\2\2\2z|\t\2\2\2{}"+
		"\5/\30\2|{\3\2\2\2}~\3\2\2\2~|\3\2\2\2~\177\3\2\2\2\177\u0081\3\2\2\2"+
		"\u0080z\3\2\2\2\u0080\u0081\3\2\2\2\u0081\u0083\3\2\2\2\u0082\u0084\5"+
		"+\26\2\u0083\u0082\3\2\2\2\u0083\u0084\3\2\2\2\u0084\36\3\2\2\2\u0085"+
		"\u0089\5/\30\2\u0086\u0088\5!\21\2\u0087\u0086\3\2\2\2\u0088\u008b\3\2"+
		"\2\2\u0089\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u008c\3\2\2\2\u008b"+
		"\u0089\3\2\2\2\u008c\u0090\5-\27\2\u008d\u008f\5!\21\2\u008e\u008d\3\2"+
		"\2\2\u008f\u0092\3\2\2\2\u0090\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091"+
		" \3\2\2\2\u0092\u0090\3\2\2\2\u0093\u0097\5-\27\2\u0094\u0097\5/\30\2"+
		"\u0095\u0097\t\3\2\2\u0096\u0093\3\2\2\2\u0096\u0094\3\2\2\2\u0096\u0095"+
		"\3\2\2\2\u0097\"\3\2\2\2\u0098\u009a\7$\2\2\u0099\u009b\n\4\2\2\u009a"+
		"\u0099\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009a\3\2\2\2\u009c\u009d\3\2"+
		"\2\2\u009d\u009e\3\2\2\2\u009e\u009f\7$\2\2\u009f$\3\2\2\2\u00a0\u00a4"+
		"\7=\2\2\u00a1\u00a3\n\5\2\2\u00a2\u00a1\3\2\2\2\u00a3\u00a6\3\2\2\2\u00a4"+
		"\u00a2\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a7\3\2\2\2\u00a6\u00a4\3\2"+
		"\2\2\u00a7\u00a8\b\23\2\2\u00a8&\3\2\2\2\u00a9\u00ab\7A\2\2\u00aa\u00ac"+
		"\5!\21\2\u00ab\u00aa\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ad"+
		"\u00ae\3\2\2\2\u00ae(\3\2\2\2\u00af\u00b1\7B\2\2\u00b0\u00b2\5!\21\2\u00b1"+
		"\u00b0\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b3\u00b4\3\2"+
		"\2\2\u00b4*\3\2\2\2\u00b5\u00b7\7g\2\2\u00b6\u00b8\7/\2\2\u00b7\u00b6"+
		"\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00ba\3\2\2\2\u00b9\u00bb\5/\30\2\u00ba"+
		"\u00b9\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bc\u00bd\3\2"+
		"\2\2\u00bd,\3\2\2\2\u00be\u00bf\t\6\2\2\u00bf.\3\2\2\2\u00c0\u00c1\t\7"+
		"\2\2\u00c1\60\3\2\2\2\u00c2\u00c4\t\b\2\2\u00c3\u00c2\3\2\2\2\u00c4\u00c5"+
		"\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7"+
		"\u00c8\b\31\2\2\u00c8\62\3\2\2\2\24\2eosx~\u0080\u0083\u0089\u0090\u0096"+
		"\u009c\u00a4\u00ad\u00b3\u00b7\u00bc\u00c5\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}