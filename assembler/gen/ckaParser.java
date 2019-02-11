// Generated from C:/code/CopperKitten/assembler/grammar\cka.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ckaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, NATURAL=33, LABEL=34, NL=35, WHITESPACE=36, COMMENT=37;
	public static final int
		RULE_file = 0, RULE_instructions = 1, RULE_instruction = 2, RULE_frameLayout = 3, 
		RULE_allocLayout = 4;
	private static String[] makeRuleNames() {
		return new String[] {
			"file", "instructions", "instruction", "frameLayout", "allocLayout"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'add'", "'sub'", "'mul'", "'div'", "'mod'", "'shl'", "'shr'", 
			"'cmp'", "'skipz'", "'ip'", "'fp'", "'jump'", "'push'", "'dup'", "'pop'", 
			"'swap'", "'enter'", "'leave'", "'in'", "'out'", "'layout'", "'alloc'", 
			"'fpload'", "'fpstore'", "'rload'", "'rstore'", "'ncall'", "'nop'", "'['", 
			"']'", "','", "'*'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, "NATURAL", "LABEL", 
			"NL", "WHITESPACE", "COMMENT"
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
	public String getGrammarFileName() { return "cka.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ckaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class FileContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(ckaParser.EOF, 0); }
		public List<TerminalNode> NL() { return getTokens(ckaParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(ckaParser.NL, i);
		}
		public InstructionsContext instructions() {
			return getRuleContext(InstructionsContext.class,0);
		}
		public FileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).enterFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).exitFile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckaVisitor ) return ((ckaVisitor<? extends T>)visitor).visitFile(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FileContext file() throws RecognitionException {
		FileContext _localctx = new FileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_file);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(13);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(10);
					match(NL);
					}
					} 
				}
				setState(15);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(17);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27))) != 0)) {
				{
				setState(16);
				instructions();
				}
			}

			setState(22);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(19);
				match(NL);
				}
				}
				setState(24);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(25);
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

	public static class InstructionsContext extends ParserRuleContext {
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public List<TerminalNode> NL() { return getTokens(ckaParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(ckaParser.NL, i);
		}
		public InstructionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instructions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).enterInstructions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).exitInstructions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckaVisitor ) return ((ckaVisitor<? extends T>)visitor).visitInstructions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstructionsContext instructions() throws RecognitionException {
		InstructionsContext _localctx = new InstructionsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_instructions);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(27);
			instruction();
			setState(36);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(29); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(28);
						match(NL);
						}
						}
						setState(31); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==NL );
					setState(33);
					instruction();
					}
					} 
				}
				setState(38);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
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

	public static class InstructionContext extends ParserRuleContext {
		public TerminalNode NATURAL() { return getToken(ckaParser.NATURAL, 0); }
		public FrameLayoutContext frameLayout() {
			return getRuleContext(FrameLayoutContext.class,0);
		}
		public AllocLayoutContext allocLayout() {
			return getRuleContext(AllocLayoutContext.class,0);
		}
		public InstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).enterInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).exitInstruction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckaVisitor ) return ((ckaVisitor<? extends T>)visitor).visitInstruction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstructionContext instruction() throws RecognitionException {
		InstructionContext _localctx = new InstructionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_instruction);
		try {
			setState(70);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(39);
				match(T__0);
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(40);
				match(T__1);
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 3);
				{
				setState(41);
				match(T__2);
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 4);
				{
				setState(42);
				match(T__3);
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 5);
				{
				setState(43);
				match(T__4);
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 6);
				{
				setState(44);
				match(T__5);
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 7);
				{
				setState(45);
				match(T__6);
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 8);
				{
				setState(46);
				match(T__7);
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 9);
				{
				setState(47);
				match(T__8);
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 10);
				{
				setState(48);
				match(T__9);
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 11);
				{
				setState(49);
				match(T__10);
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 12);
				{
				setState(50);
				match(T__11);
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 13);
				{
				setState(51);
				match(T__12);
				setState(52);
				match(NATURAL);
				}
				break;
			case T__13:
				enterOuterAlt(_localctx, 14);
				{
				setState(53);
				match(T__13);
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 15);
				{
				setState(54);
				match(T__14);
				}
				break;
			case T__15:
				enterOuterAlt(_localctx, 16);
				{
				setState(55);
				match(T__15);
				}
				break;
			case T__16:
				enterOuterAlt(_localctx, 17);
				{
				setState(56);
				match(T__16);
				}
				break;
			case T__17:
				enterOuterAlt(_localctx, 18);
				{
				setState(57);
				match(T__17);
				}
				break;
			case T__18:
				enterOuterAlt(_localctx, 19);
				{
				setState(58);
				match(T__18);
				}
				break;
			case T__19:
				enterOuterAlt(_localctx, 20);
				{
				setState(59);
				match(T__19);
				}
				break;
			case T__20:
				enterOuterAlt(_localctx, 21);
				{
				setState(60);
				match(T__20);
				setState(61);
				frameLayout();
				}
				break;
			case T__21:
				enterOuterAlt(_localctx, 22);
				{
				setState(62);
				match(T__21);
				setState(63);
				allocLayout();
				}
				break;
			case T__22:
				enterOuterAlt(_localctx, 23);
				{
				setState(64);
				match(T__22);
				}
				break;
			case T__23:
				enterOuterAlt(_localctx, 24);
				{
				setState(65);
				match(T__23);
				}
				break;
			case T__24:
				enterOuterAlt(_localctx, 25);
				{
				setState(66);
				match(T__24);
				}
				break;
			case T__25:
				enterOuterAlt(_localctx, 26);
				{
				setState(67);
				match(T__25);
				}
				break;
			case T__26:
				enterOuterAlt(_localctx, 27);
				{
				setState(68);
				match(T__26);
				}
				break;
			case T__27:
				enterOuterAlt(_localctx, 28);
				{
				setState(69);
				match(T__27);
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

	public static class FrameLayoutContext extends ParserRuleContext {
		public List<TerminalNode> NATURAL() { return getTokens(ckaParser.NATURAL); }
		public TerminalNode NATURAL(int i) {
			return getToken(ckaParser.NATURAL, i);
		}
		public FrameLayoutContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_frameLayout; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).enterFrameLayout(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).exitFrameLayout(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckaVisitor ) return ((ckaVisitor<? extends T>)visitor).visitFrameLayout(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FrameLayoutContext frameLayout() throws RecognitionException {
		FrameLayoutContext _localctx = new FrameLayoutContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_frameLayout);
		int _la;
		try {
			setState(84);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(72);
				match(T__28);
				setState(73);
				match(T__29);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(74);
				match(T__28);
				setState(75);
				match(NATURAL);
				setState(80);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__30) {
					{
					{
					setState(76);
					match(T__30);
					setState(77);
					match(NATURAL);
					}
					}
					setState(82);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(83);
				match(T__29);
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

	public static class AllocLayoutContext extends ParserRuleContext {
		public AllocLayoutContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_allocLayout; }
	 
		public AllocLayoutContext() { }
		public void copyFrom(AllocLayoutContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class RefArrayLayoutContext extends AllocLayoutContext {
		public TerminalNode NATURAL() { return getToken(ckaParser.NATURAL, 0); }
		public RefArrayLayoutContext(AllocLayoutContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).enterRefArrayLayout(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).exitRefArrayLayout(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckaVisitor ) return ((ckaVisitor<? extends T>)visitor).visitRefArrayLayout(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CustomLayoutContext extends AllocLayoutContext {
		public List<TerminalNode> NATURAL() { return getTokens(ckaParser.NATURAL); }
		public TerminalNode NATURAL(int i) {
			return getToken(ckaParser.NATURAL, i);
		}
		public CustomLayoutContext(AllocLayoutContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).enterCustomLayout(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).exitCustomLayout(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckaVisitor ) return ((ckaVisitor<? extends T>)visitor).visitCustomLayout(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AllocLayoutContext allocLayout() throws RecognitionException {
		AllocLayoutContext _localctx = new AllocLayoutContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_allocLayout);
		int _la;
		try {
			setState(101);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				_localctx = new RefArrayLayoutContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(86);
				match(T__28);
				setState(87);
				match(NATURAL);
				setState(88);
				match(T__30);
				setState(89);
				match(T__31);
				setState(90);
				match(T__29);
				}
				break;
			case 2:
				_localctx = new CustomLayoutContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(91);
				match(T__28);
				setState(92);
				match(NATURAL);
				setState(97);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__30) {
					{
					{
					setState(93);
					match(T__30);
					setState(94);
					match(NATURAL);
					}
					}
					setState(99);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(100);
				match(T__29);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\'j\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\7\2\16\n\2\f\2\16\2\21\13\2\3\2\5\2\24"+
		"\n\2\3\2\7\2\27\n\2\f\2\16\2\32\13\2\3\2\3\2\3\3\3\3\6\3 \n\3\r\3\16\3"+
		"!\3\3\7\3%\n\3\f\3\16\3(\13\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\5\4I\n\4\3\5\3\5\3\5\3\5\3\5\3\5\7\5Q\n\5\f\5\16\5T\13"+
		"\5\3\5\5\5W\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6b\n\6\f\6\16\6"+
		"e\13\6\3\6\5\6h\n\6\3\6\2\2\7\2\4\6\b\n\2\2\2\u0088\2\17\3\2\2\2\4\35"+
		"\3\2\2\2\6H\3\2\2\2\bV\3\2\2\2\ng\3\2\2\2\f\16\7%\2\2\r\f\3\2\2\2\16\21"+
		"\3\2\2\2\17\r\3\2\2\2\17\20\3\2\2\2\20\23\3\2\2\2\21\17\3\2\2\2\22\24"+
		"\5\4\3\2\23\22\3\2\2\2\23\24\3\2\2\2\24\30\3\2\2\2\25\27\7%\2\2\26\25"+
		"\3\2\2\2\27\32\3\2\2\2\30\26\3\2\2\2\30\31\3\2\2\2\31\33\3\2\2\2\32\30"+
		"\3\2\2\2\33\34\7\2\2\3\34\3\3\2\2\2\35&\5\6\4\2\36 \7%\2\2\37\36\3\2\2"+
		"\2 !\3\2\2\2!\37\3\2\2\2!\"\3\2\2\2\"#\3\2\2\2#%\5\6\4\2$\37\3\2\2\2%"+
		"(\3\2\2\2&$\3\2\2\2&\'\3\2\2\2\'\5\3\2\2\2(&\3\2\2\2)I\7\3\2\2*I\7\4\2"+
		"\2+I\7\5\2\2,I\7\6\2\2-I\7\7\2\2.I\7\b\2\2/I\7\t\2\2\60I\7\n\2\2\61I\7"+
		"\13\2\2\62I\7\f\2\2\63I\7\r\2\2\64I\7\16\2\2\65\66\7\17\2\2\66I\7#\2\2"+
		"\67I\7\20\2\28I\7\21\2\29I\7\22\2\2:I\7\23\2\2;I\7\24\2\2<I\7\25\2\2="+
		"I\7\26\2\2>?\7\27\2\2?I\5\b\5\2@A\7\30\2\2AI\5\n\6\2BI\7\31\2\2CI\7\32"+
		"\2\2DI\7\33\2\2EI\7\34\2\2FI\7\35\2\2GI\7\36\2\2H)\3\2\2\2H*\3\2\2\2H"+
		"+\3\2\2\2H,\3\2\2\2H-\3\2\2\2H.\3\2\2\2H/\3\2\2\2H\60\3\2\2\2H\61\3\2"+
		"\2\2H\62\3\2\2\2H\63\3\2\2\2H\64\3\2\2\2H\65\3\2\2\2H\67\3\2\2\2H8\3\2"+
		"\2\2H9\3\2\2\2H:\3\2\2\2H;\3\2\2\2H<\3\2\2\2H=\3\2\2\2H>\3\2\2\2H@\3\2"+
		"\2\2HB\3\2\2\2HC\3\2\2\2HD\3\2\2\2HE\3\2\2\2HF\3\2\2\2HG\3\2\2\2I\7\3"+
		"\2\2\2JK\7\37\2\2KW\7 \2\2LM\7\37\2\2MR\7#\2\2NO\7!\2\2OQ\7#\2\2PN\3\2"+
		"\2\2QT\3\2\2\2RP\3\2\2\2RS\3\2\2\2SU\3\2\2\2TR\3\2\2\2UW\7 \2\2VJ\3\2"+
		"\2\2VL\3\2\2\2W\t\3\2\2\2XY\7\37\2\2YZ\7#\2\2Z[\7!\2\2[\\\7\"\2\2\\h\7"+
		" \2\2]^\7\37\2\2^c\7#\2\2_`\7!\2\2`b\7#\2\2a_\3\2\2\2be\3\2\2\2ca\3\2"+
		"\2\2cd\3\2\2\2df\3\2\2\2ec\3\2\2\2fh\7 \2\2gX\3\2\2\2g]\3\2\2\2h\13\3"+
		"\2\2\2\f\17\23\30!&HRVcg";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}