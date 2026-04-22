// Generated from Suokif.g4 by ANTLR 4.9.3
package com.articulate.sigma.parsing;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SuokifParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, FUNWORD=12, IDENTIFIER=13, NUMBER=14, NUMIDENTIFIER=15, 
		WORDCHAR=16, STRING=17, COMMENT=18, REGVAR=19, ROWVAR=20, EXPONENT=21, 
		LETTER=22, DIGIT=23, WHITESPACE=24;
	public static final int
		RULE_file = 0, RULE_sentence = 1, RULE_quantsent = 2, RULE_exists = 3, 
		RULE_forall = 4, RULE_logsent = 5, RULE_iff = 6, RULE_implies = 7, RULE_andsent = 8, 
		RULE_orsent = 9, RULE_xorsent = 10, RULE_notsent = 11, RULE_eqsent = 12, 
		RULE_funterm = 13, RULE_relsent = 14, RULE_argument = 15, RULE_term = 16, 
		RULE_number = 17, RULE_string = 18, RULE_comment = 19, RULE_variable = 20;
	private static String[] makeRuleNames() {
		return new String[] {
			"file", "sentence", "quantsent", "exists", "forall", "logsent", "iff", 
			"implies", "andsent", "orsent", "xorsent", "notsent", "eqsent", "funterm", 
			"relsent", "argument", "term", "number", "string", "comment", "variable"
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

	@Override
	public String getGrammarFileName() { return "Suokif.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SuokifParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class FileContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(SuokifParser.EOF, 0); }
		public List<SentenceContext> sentence() {
			return getRuleContexts(SentenceContext.class);
		}
		public SentenceContext sentence(int i) {
			return getRuleContext(SentenceContext.class,i);
		}
		public List<CommentContext> comment() {
			return getRuleContexts(CommentContext.class);
		}
		public CommentContext comment(int i) {
			return getRuleContext(CommentContext.class,i);
		}
		public FileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SuokifListener ) ((SuokifListener)listener).enterFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SuokifListener ) ((SuokifListener)listener).exitFile(this);
		}
	}

	public final FileContext file() throws RecognitionException {
		FileContext _localctx = new FileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_file);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(44);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__0:
				case REGVAR:
				case ROWVAR:
					{
					setState(42);
					sentence();
					}
					break;
				case COMMENT:
					{
					setState(43);
					comment();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(46); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << COMMENT) | (1L << REGVAR) | (1L << ROWVAR))) != 0) );
			setState(48);
			match(EOF);
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

	public static class SentenceContext extends ParserRuleContext {
		public RelsentContext relsent() {
			return getRuleContext(RelsentContext.class,0);
		}
		public LogsentContext logsent() {
			return getRuleContext(LogsentContext.class,0);
		}
		public QuantsentContext quantsent() {
			return getRuleContext(QuantsentContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public SentenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sentence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SuokifListener ) ((SuokifListener)listener).enterSentence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SuokifListener ) ((SuokifListener)listener).exitSentence(this);
		}
	}

	public final SentenceContext sentence() throws RecognitionException {
		SentenceContext _localctx = new SentenceContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_sentence);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(50);
				relsent();
				}
				break;
			case 2:
				{
				setState(51);
				logsent();
				}
				break;
			case 3:
				{
				setState(52);
				quantsent();
				}
				break;
			case 4:
				{
				setState(53);
				variable();
				}
				break;
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

	public static class QuantsentContext extends ParserRuleContext {
		public ForallContext forall() {
			return getRuleContext(ForallContext.class,0);
		}
		public ExistsContext exists() {
			return getRuleContext(ExistsContext.class,0);
		}
		public QuantsentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quantsent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SuokifListener ) ((SuokifListener)listener).enterQuantsent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SuokifListener ) ((SuokifListener)listener).exitQuantsent(this);
		}
	}

	public final QuantsentContext quantsent() throws RecognitionException {
		QuantsentContext _localctx = new QuantsentContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_quantsent);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(56);
				forall();
				}
				break;
			case 2:
				{
				setState(57);
				exists();
				}
				break;
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

	public static class ExistsContext extends ParserRuleContext {
		public SentenceContext sentence() {
			return getRuleContext(SentenceContext.class,0);
		}
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public ExistsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exists; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SuokifListener ) ((SuokifListener)listener).enterExists(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SuokifListener ) ((SuokifListener)listener).exitExists(this);
		}
	}

	public final ExistsContext exists() throws RecognitionException {
		ExistsContext _localctx = new ExistsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_exists);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
			match(T__0);
			setState(61);
			match(T__1);
			setState(62);
			match(T__0);
			setState(64); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(63);
				variable();
				}
				}
				setState(66); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==REGVAR || _la==ROWVAR );
			setState(68);
			match(T__2);
			setState(69);
			sentence();
			setState(70);
			match(T__2);
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

	public static class ForallContext extends ParserRuleContext {
		public SentenceContext sentence() {
			return getRuleContext(SentenceContext.class,0);
		}
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public ForallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SuokifListener ) ((SuokifListener)listener).enterForall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SuokifListener ) ((SuokifListener)listener).exitForall(this);
		}
	}

	public final ForallContext forall() throws RecognitionException {
		ForallContext _localctx = new ForallContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_forall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(T__0);
			setState(73);
			match(T__3);
			setState(74);
			match(T__0);
			setState(76); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(75);
				variable();
				}
				}
				setState(78); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==REGVAR || _la==ROWVAR );
			setState(80);
			match(T__2);
			setState(81);
			sentence();
			setState(82);
			match(T__2);
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

	public static class LogsentContext extends ParserRuleContext {
		public NotsentContext notsent() {
			return getRuleContext(NotsentContext.class,0);
		}
		public AndsentContext andsent() {
			return getRuleContext(AndsentContext.class,0);
		}
		public OrsentContext orsent() {
			return getRuleContext(OrsentContext.class,0);
		}
		public XorsentContext xorsent() {
			return getRuleContext(XorsentContext.class,0);
		}
		public ImpliesContext implies() {
			return getRuleContext(ImpliesContext.class,0);
		}
		public IffContext iff() {
			return getRuleContext(IffContext.class,0);
		}
		public EqsentContext eqsent() {
			return getRuleContext(EqsentContext.class,0);
		}
		public LogsentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logsent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SuokifListener ) ((SuokifListener)listener).enterLogsent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SuokifListener ) ((SuokifListener)listener).exitLogsent(this);
		}
	}

	public final LogsentContext logsent() throws RecognitionException {
		LogsentContext _localctx = new LogsentContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_logsent);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(84);
				notsent();
				}
				break;
			case 2:
				{
				setState(85);
				andsent();
				}
				break;
			case 3:
				{
				setState(86);
				orsent();
				}
				break;
			case 4:
				{
				setState(87);
				xorsent();
				}
				break;
			case 5:
				{
				setState(88);
				implies();
				}
				break;
			case 6:
				{
				setState(89);
				iff();
				}
				break;
			case 7:
				{
				setState(90);
				eqsent();
				}
				break;
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

	public static class IffContext extends ParserRuleContext {
		public List<SentenceContext> sentence() {
			return getRuleContexts(SentenceContext.class);
		}
		public SentenceContext sentence(int i) {
			return getRuleContext(SentenceContext.class,i);
		}
		public IffContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_iff; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SuokifListener ) ((SuokifListener)listener).enterIff(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SuokifListener ) ((SuokifListener)listener).exitIff(this);
		}
	}

	public final IffContext iff() throws RecognitionException {
		IffContext _localctx = new IffContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_iff);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			match(T__0);
			setState(94);
			match(T__4);
			setState(95);
			sentence();
			setState(96);
			sentence();
			setState(97);
			match(T__2);
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

	public static class ImpliesContext extends ParserRuleContext {
		public List<SentenceContext> sentence() {
			return getRuleContexts(SentenceContext.class);
		}
		public SentenceContext sentence(int i) {
			return getRuleContext(SentenceContext.class,i);
		}
		public ImpliesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_implies; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SuokifListener ) ((SuokifListener)listener).enterImplies(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SuokifListener ) ((SuokifListener)listener).exitImplies(this);
		}
	}

	public final ImpliesContext implies() throws RecognitionException {
		ImpliesContext _localctx = new ImpliesContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_implies);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			match(T__0);
			setState(100);
			match(T__5);
			setState(101);
			sentence();
			setState(102);
			sentence();
			setState(103);
			match(T__2);
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

	public static class AndsentContext extends ParserRuleContext {
		public List<SentenceContext> sentence() {
			return getRuleContexts(SentenceContext.class);
		}
		public SentenceContext sentence(int i) {
			return getRuleContext(SentenceContext.class,i);
		}
		public AndsentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_andsent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SuokifListener ) ((SuokifListener)listener).enterAndsent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SuokifListener ) ((SuokifListener)listener).exitAndsent(this);
		}
	}

	public final AndsentContext andsent() throws RecognitionException {
		AndsentContext _localctx = new AndsentContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_andsent);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			match(T__0);
			setState(106);
			match(T__6);
			setState(107);
			sentence();
			setState(109); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(108);
				sentence();
				}
				}
				setState(111); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << REGVAR) | (1L << ROWVAR))) != 0) );
			setState(113);
			match(T__2);
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

	public static class OrsentContext extends ParserRuleContext {
		public List<SentenceContext> sentence() {
			return getRuleContexts(SentenceContext.class);
		}
		public SentenceContext sentence(int i) {
			return getRuleContext(SentenceContext.class,i);
		}
		public OrsentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orsent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SuokifListener ) ((SuokifListener)listener).enterOrsent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SuokifListener ) ((SuokifListener)listener).exitOrsent(this);
		}
	}

	public final OrsentContext orsent() throws RecognitionException {
		OrsentContext _localctx = new OrsentContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_orsent);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115);
			match(T__0);
			setState(116);
			match(T__7);
			setState(117);
			sentence();
			setState(119); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(118);
				sentence();
				}
				}
				setState(121); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << REGVAR) | (1L << ROWVAR))) != 0) );
			setState(123);
			match(T__2);
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

	public static class XorsentContext extends ParserRuleContext {
		public List<SentenceContext> sentence() {
			return getRuleContexts(SentenceContext.class);
		}
		public SentenceContext sentence(int i) {
			return getRuleContext(SentenceContext.class,i);
		}
		public XorsentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_xorsent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SuokifListener ) ((SuokifListener)listener).enterXorsent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SuokifListener ) ((SuokifListener)listener).exitXorsent(this);
		}
	}

	public final XorsentContext xorsent() throws RecognitionException {
		XorsentContext _localctx = new XorsentContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_xorsent);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			match(T__0);
			setState(126);
			match(T__8);
			setState(127);
			sentence();
			setState(129); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(128);
				sentence();
				}
				}
				setState(131); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << REGVAR) | (1L << ROWVAR))) != 0) );
			setState(133);
			match(T__2);
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

	public static class NotsentContext extends ParserRuleContext {
		public SentenceContext sentence() {
			return getRuleContext(SentenceContext.class,0);
		}
		public NotsentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_notsent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SuokifListener ) ((SuokifListener)listener).enterNotsent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SuokifListener ) ((SuokifListener)listener).exitNotsent(this);
		}
	}

	public final NotsentContext notsent() throws RecognitionException {
		NotsentContext _localctx = new NotsentContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_notsent);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135);
			match(T__0);
			setState(136);
			match(T__9);
			setState(137);
			sentence();
			setState(138);
			match(T__2);
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

	public static class EqsentContext extends ParserRuleContext {
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public EqsentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eqsent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SuokifListener ) ((SuokifListener)listener).enterEqsent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SuokifListener ) ((SuokifListener)listener).exitEqsent(this);
		}
	}

	public final EqsentContext eqsent() throws RecognitionException {
		EqsentContext _localctx = new EqsentContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_eqsent);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(140);
			match(T__0);
			setState(141);
			match(T__10);
			setState(142);
			term();
			setState(143);
			term();
			setState(144);
			match(T__2);
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

	public static class FuntermContext extends ParserRuleContext {
		public TerminalNode FUNWORD() { return getToken(SuokifParser.FUNWORD, 0); }
		public List<ArgumentContext> argument() {
			return getRuleContexts(ArgumentContext.class);
		}
		public ArgumentContext argument(int i) {
			return getRuleContext(ArgumentContext.class,i);
		}
		public FuntermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funterm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SuokifListener ) ((SuokifListener)listener).enterFunterm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SuokifListener ) ((SuokifListener)listener).exitFunterm(this);
		}
	}

	public final FuntermContext funterm() throws RecognitionException {
		FuntermContext _localctx = new FuntermContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_funterm);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			match(T__0);
			setState(147);
			match(FUNWORD);
			setState(149); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(148);
				argument();
				}
				}
				setState(151); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << FUNWORD) | (1L << IDENTIFIER) | (1L << NUMBER) | (1L << NUMIDENTIFIER) | (1L << STRING) | (1L << REGVAR) | (1L << ROWVAR))) != 0) );
			setState(153);
			match(T__2);
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

	public static class RelsentContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(SuokifParser.IDENTIFIER, 0); }
		public TerminalNode NUMIDENTIFIER() { return getToken(SuokifParser.NUMIDENTIFIER, 0); }
		public List<ArgumentContext> argument() {
			return getRuleContexts(ArgumentContext.class);
		}
		public ArgumentContext argument(int i) {
			return getRuleContext(ArgumentContext.class,i);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public RelsentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relsent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SuokifListener ) ((SuokifListener)listener).enterRelsent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SuokifListener ) ((SuokifListener)listener).exitRelsent(this);
		}
	}

	public final RelsentContext relsent() throws RecognitionException {
		RelsentContext _localctx = new RelsentContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_relsent);
		int _la;
		try {
			setState(173);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(155);
				match(T__0);
				setState(156);
				_la = _input.LA(1);
				if ( !(_la==IDENTIFIER || _la==NUMIDENTIFIER) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(158); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(157);
					argument();
					}
					}
					setState(160); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << FUNWORD) | (1L << IDENTIFIER) | (1L << NUMBER) | (1L << NUMIDENTIFIER) | (1L << STRING) | (1L << REGVAR) | (1L << ROWVAR))) != 0) );
				setState(162);
				match(T__2);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(164);
				match(T__0);
				setState(165);
				variable();
				setState(167); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(166);
					argument();
					}
					}
					setState(169); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << FUNWORD) | (1L << IDENTIFIER) | (1L << NUMBER) | (1L << NUMIDENTIFIER) | (1L << STRING) | (1L << REGVAR) | (1L << ROWVAR))) != 0) );
				setState(171);
				match(T__2);
				}
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

	public static class ArgumentContext extends ParserRuleContext {
		public SentenceContext sentence() {
			return getRuleContext(SentenceContext.class,0);
		}
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public ArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SuokifListener ) ((SuokifListener)listener).enterArgument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SuokifListener ) ((SuokifListener)listener).exitArgument(this);
		}
	}

	public final ArgumentContext argument() throws RecognitionException {
		ArgumentContext _localctx = new ArgumentContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_argument);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				setState(175);
				sentence();
				}
				break;
			case 2:
				{
				setState(176);
				term();
				}
				break;
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

	public static class TermContext extends ParserRuleContext {
		public FuntermContext funterm() {
			return getRuleContext(FuntermContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public TerminalNode FUNWORD() { return getToken(SuokifParser.FUNWORD, 0); }
		public TerminalNode IDENTIFIER() { return getToken(SuokifParser.IDENTIFIER, 0); }
		public TerminalNode NUMIDENTIFIER() { return getToken(SuokifParser.NUMIDENTIFIER, 0); }
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SuokifListener ) ((SuokifListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SuokifListener ) ((SuokifListener)listener).exitTerm(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_term);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				{
				setState(179);
				funterm();
				}
				break;
			case REGVAR:
			case ROWVAR:
				{
				setState(180);
				variable();
				}
				break;
			case STRING:
				{
				setState(181);
				string();
				}
				break;
			case NUMBER:
				{
				setState(182);
				number();
				}
				break;
			case FUNWORD:
				{
				setState(183);
				match(FUNWORD);
				}
				break;
			case IDENTIFIER:
				{
				setState(184);
				match(IDENTIFIER);
				}
				break;
			case NUMIDENTIFIER:
				{
				setState(185);
				match(NUMIDENTIFIER);
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class NumberContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(SuokifParser.NUMBER, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SuokifListener ) ((SuokifListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SuokifListener ) ((SuokifListener)listener).exitNumber(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			match(NUMBER);
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

	public static class StringContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(SuokifParser.STRING, 0); }
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SuokifListener ) ((SuokifListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SuokifListener ) ((SuokifListener)listener).exitString(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_string);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(190);
			match(STRING);
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

	public static class CommentContext extends ParserRuleContext {
		public TerminalNode COMMENT() { return getToken(SuokifParser.COMMENT, 0); }
		public CommentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SuokifListener ) ((SuokifListener)listener).enterComment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SuokifListener ) ((SuokifListener)listener).exitComment(this);
		}
	}

	public final CommentContext comment() throws RecognitionException {
		CommentContext _localctx = new CommentContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_comment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(192);
			match(COMMENT);
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

	public static class VariableContext extends ParserRuleContext {
		public TerminalNode REGVAR() { return getToken(SuokifParser.REGVAR, 0); }
		public TerminalNode ROWVAR() { return getToken(SuokifParser.ROWVAR, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SuokifListener ) ((SuokifListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SuokifListener ) ((SuokifListener)listener).exitVariable(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_variable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(194);
			_la = _input.LA(1);
			if ( !(_la==REGVAR || _la==ROWVAR) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\32\u00c7\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\6\2/\n\2\r\2\16\2\60"+
		"\3\2\3\2\3\3\3\3\3\3\3\3\5\39\n\3\3\4\3\4\5\4=\n\4\3\5\3\5\3\5\3\5\6\5"+
		"C\n\5\r\5\16\5D\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\6\6O\n\6\r\6\16\6P\3\6"+
		"\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7^\n\7\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\6\np\n\n\r\n\16\nq\3\n\3"+
		"\n\3\13\3\13\3\13\3\13\6\13z\n\13\r\13\16\13{\3\13\3\13\3\f\3\f\3\f\3"+
		"\f\6\f\u0084\n\f\r\f\16\f\u0085\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\17\3\17\3\17\6\17\u0098\n\17\r\17\16\17\u0099\3"+
		"\17\3\17\3\20\3\20\3\20\6\20\u00a1\n\20\r\20\16\20\u00a2\3\20\3\20\3\20"+
		"\3\20\3\20\6\20\u00aa\n\20\r\20\16\20\u00ab\3\20\3\20\5\20\u00b0\n\20"+
		"\3\21\3\21\5\21\u00b4\n\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u00bd"+
		"\n\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\26\2\2\27\2\4\6\b\n\f"+
		"\16\20\22\24\26\30\32\34\36 \"$&(*\2\4\4\2\17\17\21\21\3\2\25\26\2\u00cd"+
		"\2.\3\2\2\2\48\3\2\2\2\6<\3\2\2\2\b>\3\2\2\2\nJ\3\2\2\2\f]\3\2\2\2\16"+
		"_\3\2\2\2\20e\3\2\2\2\22k\3\2\2\2\24u\3\2\2\2\26\177\3\2\2\2\30\u0089"+
		"\3\2\2\2\32\u008e\3\2\2\2\34\u0094\3\2\2\2\36\u00af\3\2\2\2 \u00b3\3\2"+
		"\2\2\"\u00bc\3\2\2\2$\u00be\3\2\2\2&\u00c0\3\2\2\2(\u00c2\3\2\2\2*\u00c4"+
		"\3\2\2\2,/\5\4\3\2-/\5(\25\2.,\3\2\2\2.-\3\2\2\2/\60\3\2\2\2\60.\3\2\2"+
		"\2\60\61\3\2\2\2\61\62\3\2\2\2\62\63\7\2\2\3\63\3\3\2\2\2\649\5\36\20"+
		"\2\659\5\f\7\2\669\5\6\4\2\679\5*\26\28\64\3\2\2\28\65\3\2\2\28\66\3\2"+
		"\2\28\67\3\2\2\29\5\3\2\2\2:=\5\n\6\2;=\5\b\5\2<:\3\2\2\2<;\3\2\2\2=\7"+
		"\3\2\2\2>?\7\3\2\2?@\7\4\2\2@B\7\3\2\2AC\5*\26\2BA\3\2\2\2CD\3\2\2\2D"+
		"B\3\2\2\2DE\3\2\2\2EF\3\2\2\2FG\7\5\2\2GH\5\4\3\2HI\7\5\2\2I\t\3\2\2\2"+
		"JK\7\3\2\2KL\7\6\2\2LN\7\3\2\2MO\5*\26\2NM\3\2\2\2OP\3\2\2\2PN\3\2\2\2"+
		"PQ\3\2\2\2QR\3\2\2\2RS\7\5\2\2ST\5\4\3\2TU\7\5\2\2U\13\3\2\2\2V^\5\30"+
		"\r\2W^\5\22\n\2X^\5\24\13\2Y^\5\26\f\2Z^\5\20\t\2[^\5\16\b\2\\^\5\32\16"+
		"\2]V\3\2\2\2]W\3\2\2\2]X\3\2\2\2]Y\3\2\2\2]Z\3\2\2\2][\3\2\2\2]\\\3\2"+
		"\2\2^\r\3\2\2\2_`\7\3\2\2`a\7\7\2\2ab\5\4\3\2bc\5\4\3\2cd\7\5\2\2d\17"+
		"\3\2\2\2ef\7\3\2\2fg\7\b\2\2gh\5\4\3\2hi\5\4\3\2ij\7\5\2\2j\21\3\2\2\2"+
		"kl\7\3\2\2lm\7\t\2\2mo\5\4\3\2np\5\4\3\2on\3\2\2\2pq\3\2\2\2qo\3\2\2\2"+
		"qr\3\2\2\2rs\3\2\2\2st\7\5\2\2t\23\3\2\2\2uv\7\3\2\2vw\7\n\2\2wy\5\4\3"+
		"\2xz\5\4\3\2yx\3\2\2\2z{\3\2\2\2{y\3\2\2\2{|\3\2\2\2|}\3\2\2\2}~\7\5\2"+
		"\2~\25\3\2\2\2\177\u0080\7\3\2\2\u0080\u0081\7\13\2\2\u0081\u0083\5\4"+
		"\3\2\u0082\u0084\5\4\3\2\u0083\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085"+
		"\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0088\7\5"+
		"\2\2\u0088\27\3\2\2\2\u0089\u008a\7\3\2\2\u008a\u008b\7\f\2\2\u008b\u008c"+
		"\5\4\3\2\u008c\u008d\7\5\2\2\u008d\31\3\2\2\2\u008e\u008f\7\3\2\2\u008f"+
		"\u0090\7\r\2\2\u0090\u0091\5\"\22\2\u0091\u0092\5\"\22\2\u0092\u0093\7"+
		"\5\2\2\u0093\33\3\2\2\2\u0094\u0095\7\3\2\2\u0095\u0097\7\16\2\2\u0096"+
		"\u0098\5 \21\2\u0097\u0096\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u0097\3\2"+
		"\2\2\u0099\u009a\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u009c\7\5\2\2\u009c"+
		"\35\3\2\2\2\u009d\u009e\7\3\2\2\u009e\u00a0\t\2\2\2\u009f\u00a1\5 \21"+
		"\2\u00a0\u009f\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a2\u00a3"+
		"\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a5\7\5\2\2\u00a5\u00b0\3\2\2\2\u00a6"+
		"\u00a7\7\3\2\2\u00a7\u00a9\5*\26\2\u00a8\u00aa\5 \21\2\u00a9\u00a8\3\2"+
		"\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac"+
		"\u00ad\3\2\2\2\u00ad\u00ae\7\5\2\2\u00ae\u00b0\3\2\2\2\u00af\u009d\3\2"+
		"\2\2\u00af\u00a6\3\2\2\2\u00b0\37\3\2\2\2\u00b1\u00b4\5\4\3\2\u00b2\u00b4"+
		"\5\"\22\2\u00b3\u00b1\3\2\2\2\u00b3\u00b2\3\2\2\2\u00b4!\3\2\2\2\u00b5"+
		"\u00bd\5\34\17\2\u00b6\u00bd\5*\26\2\u00b7\u00bd\5&\24\2\u00b8\u00bd\5"+
		"$\23\2\u00b9\u00bd\7\16\2\2\u00ba\u00bd\7\17\2\2\u00bb\u00bd\7\21\2\2"+
		"\u00bc\u00b5\3\2\2\2\u00bc\u00b6\3\2\2\2\u00bc\u00b7\3\2\2\2\u00bc\u00b8"+
		"\3\2\2\2\u00bc\u00b9\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bc\u00bb\3\2\2\2\u00bd"+
		"#\3\2\2\2\u00be\u00bf\7\20\2\2\u00bf%\3\2\2\2\u00c0\u00c1\7\23\2\2\u00c1"+
		"\'\3\2\2\2\u00c2\u00c3\7\24\2\2\u00c3)\3\2\2\2\u00c4\u00c5\t\3\2\2\u00c5"+
		"+\3\2\2\2\22.\608<DP]q{\u0085\u0099\u00a2\u00ab\u00af\u00b3\u00bc";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}