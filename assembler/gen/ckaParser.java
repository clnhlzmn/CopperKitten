// Generated from C:/code/ck/assembler/grammar\cka.g4 by ANTLR 4.7.2
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
		T__31=32, T__32=33, T__33=34, NATURAL=35, ID=36, LABEL=37, NL=38, WHITESPACE=39, 
		COMMENT=40;
	public static final int
		RULE_file = 0, RULE_instructions = 1, RULE_instruction = 2, RULE_jumpMnemonic = 3, 
		RULE_simpleInstruction = 4, RULE_frameLayout = 5, RULE_allocLayout = 6, 
		RULE_integer = 7;
	private static String[] makeRuleNames() {
		return new String[] {
			"file", "instructions", "instruction", "jumpMnemonic", "simpleInstruction", 
			"frameLayout", "allocLayout", "integer"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "':'", "'push'", "'layout'", "'alloc'", "'jump'", "'jumpz'", "'jumpnz'", 
			"'add'", "'sub'", "'mul'", "'div'", "'mod'", "'shl'", "'shr'", "'cmp'", 
			"'call'", "'ret'", "'dup'", "'pop'", "'swap'", "'enter'", "'leave'", 
			"'in'", "'out'", "'fload'", "'fstore'", "'rload'", "'rstore'", "'nop'", 
			"'['", "']'", "','", "'*'", "'-'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, "NATURAL", 
			"ID", "LABEL", "NL", "WHITESPACE", "COMMENT"
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
			setState(19);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(16);
					match(NL);
					}
					} 
				}
				setState(21);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(23);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << LABEL))) != 0)) {
				{
				setState(22);
				instructions();
				}
			}

			setState(28);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(25);
				match(NL);
				}
				}
				setState(30);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(31);
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
			setState(33);
			instruction();
			setState(42);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(35); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(34);
						match(NL);
						}
						}
						setState(37); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==NL );
					setState(39);
					instruction();
					}
					} 
				}
				setState(44);
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
		public InstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruction; }
	 
		public InstructionContext() { }
		public void copyFrom(InstructionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class PushIntInstContext extends InstructionContext {
		public IntegerContext integer() {
			return getRuleContext(IntegerContext.class,0);
		}
		public PushIntInstContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).enterPushIntInst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).exitPushIntInst(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckaVisitor ) return ((ckaVisitor<? extends T>)visitor).visitPushIntInst(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AllocInstContext extends InstructionContext {
		public AllocLayoutContext allocLayout() {
			return getRuleContext(AllocLayoutContext.class,0);
		}
		public AllocInstContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).enterAllocInst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).exitAllocInst(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckaVisitor ) return ((ckaVisitor<? extends T>)visitor).visitAllocInst(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class JumpInstContext extends InstructionContext {
		public JumpMnemonicContext jumpMnemonic() {
			return getRuleContext(JumpMnemonicContext.class,0);
		}
		public TerminalNode LABEL() { return getToken(ckaParser.LABEL, 0); }
		public JumpInstContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).enterJumpInst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).exitJumpInst(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckaVisitor ) return ((ckaVisitor<? extends T>)visitor).visitJumpInst(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LayoutInstContext extends InstructionContext {
		public FrameLayoutContext frameLayout() {
			return getRuleContext(FrameLayoutContext.class,0);
		}
		public LayoutInstContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).enterLayoutInst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).exitLayoutInst(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckaVisitor ) return ((ckaVisitor<? extends T>)visitor).visitLayoutInst(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PushLabelInstContext extends InstructionContext {
		public TerminalNode LABEL() { return getToken(ckaParser.LABEL, 0); }
		public PushLabelInstContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).enterPushLabelInst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).exitPushLabelInst(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckaVisitor ) return ((ckaVisitor<? extends T>)visitor).visitPushLabelInst(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LabelInstContext extends InstructionContext {
		public TerminalNode LABEL() { return getToken(ckaParser.LABEL, 0); }
		public LabelInstContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).enterLabelInst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).exitLabelInst(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckaVisitor ) return ((ckaVisitor<? extends T>)visitor).visitLabelInst(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SimpleInstContext extends InstructionContext {
		public SimpleInstructionContext simpleInstruction() {
			return getRuleContext(SimpleInstructionContext.class,0);
		}
		public SimpleInstContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).enterSimpleInst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).exitSimpleInst(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckaVisitor ) return ((ckaVisitor<? extends T>)visitor).visitSimpleInst(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstructionContext instruction() throws RecognitionException {
		InstructionContext _localctx = new InstructionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_instruction);
		try {
			setState(59);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				_localctx = new LabelInstContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(45);
				match(LABEL);
				setState(46);
				match(T__0);
				}
				break;
			case 2:
				_localctx = new PushIntInstContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(47);
				match(T__1);
				setState(48);
				integer();
				}
				break;
			case 3:
				_localctx = new PushLabelInstContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(49);
				match(T__1);
				setState(50);
				match(LABEL);
				}
				break;
			case 4:
				_localctx = new SimpleInstContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(51);
				simpleInstruction();
				}
				break;
			case 5:
				_localctx = new JumpInstContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(52);
				jumpMnemonic();
				setState(53);
				match(LABEL);
				}
				break;
			case 6:
				_localctx = new LayoutInstContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(55);
				match(T__2);
				setState(56);
				frameLayout();
				}
				break;
			case 7:
				_localctx = new AllocInstContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(57);
				match(T__3);
				setState(58);
				allocLayout();
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

	public static class JumpMnemonicContext extends ParserRuleContext {
		public JumpMnemonicContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jumpMnemonic; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).enterJumpMnemonic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).exitJumpMnemonic(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckaVisitor ) return ((ckaVisitor<? extends T>)visitor).visitJumpMnemonic(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JumpMnemonicContext jumpMnemonic() throws RecognitionException {
		JumpMnemonicContext _localctx = new JumpMnemonicContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_jumpMnemonic);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6))) != 0)) ) {
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

	public static class SimpleInstructionContext extends ParserRuleContext {
		public SimpleInstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleInstruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).enterSimpleInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).exitSimpleInstruction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckaVisitor ) return ((ckaVisitor<? extends T>)visitor).visitSimpleInstruction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SimpleInstructionContext simpleInstruction() throws RecognitionException {
		SimpleInstructionContext _localctx = new SimpleInstructionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_simpleInstruction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28))) != 0)) ) {
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

	public static class FrameLayoutContext extends ParserRuleContext {
		public FrameLayoutContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_frameLayout; }
	 
		public FrameLayoutContext() { }
		public void copyFrom(FrameLayoutContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NonEmptyFrameLayoutContext extends FrameLayoutContext {
		public List<TerminalNode> NATURAL() { return getTokens(ckaParser.NATURAL); }
		public TerminalNode NATURAL(int i) {
			return getToken(ckaParser.NATURAL, i);
		}
		public NonEmptyFrameLayoutContext(FrameLayoutContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).enterNonEmptyFrameLayout(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).exitNonEmptyFrameLayout(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckaVisitor ) return ((ckaVisitor<? extends T>)visitor).visitNonEmptyFrameLayout(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EmptyFrameLayoutContext extends FrameLayoutContext {
		public EmptyFrameLayoutContext(FrameLayoutContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).enterEmptyFrameLayout(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).exitEmptyFrameLayout(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckaVisitor ) return ((ckaVisitor<? extends T>)visitor).visitEmptyFrameLayout(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FrameLayoutContext frameLayout() throws RecognitionException {
		FrameLayoutContext _localctx = new FrameLayoutContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_frameLayout);
		int _la;
		try {
			setState(77);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				_localctx = new EmptyFrameLayoutContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(65);
				match(T__29);
				setState(66);
				match(T__30);
				}
				break;
			case 2:
				_localctx = new NonEmptyFrameLayoutContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(67);
				match(T__29);
				setState(68);
				match(NATURAL);
				setState(73);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__31) {
					{
					{
					setState(69);
					match(T__31);
					setState(70);
					match(NATURAL);
					}
					}
					setState(75);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(76);
				match(T__30);
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
		enterRule(_localctx, 12, RULE_allocLayout);
		int _la;
		try {
			setState(94);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				_localctx = new RefArrayLayoutContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(79);
				match(T__29);
				setState(80);
				match(NATURAL);
				setState(81);
				match(T__31);
				setState(82);
				match(T__32);
				setState(83);
				match(T__30);
				}
				break;
			case 2:
				_localctx = new CustomLayoutContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(84);
				match(T__29);
				setState(85);
				match(NATURAL);
				setState(90);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__31) {
					{
					{
					setState(86);
					match(T__31);
					setState(87);
					match(NATURAL);
					}
					}
					setState(92);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(93);
				match(T__30);
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

	public static class IntegerContext extends ParserRuleContext {
		public TerminalNode NATURAL() { return getToken(ckaParser.NATURAL, 0); }
		public IntegerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).enterInteger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).exitInteger(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckaVisitor ) return ((ckaVisitor<? extends T>)visitor).visitInteger(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntegerContext integer() throws RecognitionException {
		IntegerContext _localctx = new IntegerContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_integer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__33) {
				{
				setState(96);
				match(T__33);
				}
			}

			setState(99);
			match(NATURAL);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3*h\4\2\t\2\4\3\t\3"+
		"\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\7\2\24\n\2\f\2\16"+
		"\2\27\13\2\3\2\5\2\32\n\2\3\2\7\2\35\n\2\f\2\16\2 \13\2\3\2\3\2\3\3\3"+
		"\3\6\3&\n\3\r\3\16\3\'\3\3\7\3+\n\3\f\3\16\3.\13\3\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4>\n\4\3\5\3\5\3\6\3\6\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\7\7J\n\7\f\7\16\7M\13\7\3\7\5\7P\n\7\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\7\b[\n\b\f\b\16\b^\13\b\3\b\5\ba\n\b\3\t\5\td\n\t"+
		"\3\t\3\t\3\t\2\2\n\2\4\6\b\n\f\16\20\2\4\3\2\7\t\3\2\n\37\2o\2\25\3\2"+
		"\2\2\4#\3\2\2\2\6=\3\2\2\2\b?\3\2\2\2\nA\3\2\2\2\fO\3\2\2\2\16`\3\2\2"+
		"\2\20c\3\2\2\2\22\24\7(\2\2\23\22\3\2\2\2\24\27\3\2\2\2\25\23\3\2\2\2"+
		"\25\26\3\2\2\2\26\31\3\2\2\2\27\25\3\2\2\2\30\32\5\4\3\2\31\30\3\2\2\2"+
		"\31\32\3\2\2\2\32\36\3\2\2\2\33\35\7(\2\2\34\33\3\2\2\2\35 \3\2\2\2\36"+
		"\34\3\2\2\2\36\37\3\2\2\2\37!\3\2\2\2 \36\3\2\2\2!\"\7\2\2\3\"\3\3\2\2"+
		"\2#,\5\6\4\2$&\7(\2\2%$\3\2\2\2&\'\3\2\2\2\'%\3\2\2\2\'(\3\2\2\2()\3\2"+
		"\2\2)+\5\6\4\2*%\3\2\2\2+.\3\2\2\2,*\3\2\2\2,-\3\2\2\2-\5\3\2\2\2.,\3"+
		"\2\2\2/\60\7\'\2\2\60>\7\3\2\2\61\62\7\4\2\2\62>\5\20\t\2\63\64\7\4\2"+
		"\2\64>\7\'\2\2\65>\5\n\6\2\66\67\5\b\5\2\678\7\'\2\28>\3\2\2\29:\7\5\2"+
		"\2:>\5\f\7\2;<\7\6\2\2<>\5\16\b\2=/\3\2\2\2=\61\3\2\2\2=\63\3\2\2\2=\65"+
		"\3\2\2\2=\66\3\2\2\2=9\3\2\2\2=;\3\2\2\2>\7\3\2\2\2?@\t\2\2\2@\t\3\2\2"+
		"\2AB\t\3\2\2B\13\3\2\2\2CD\7 \2\2DP\7!\2\2EF\7 \2\2FK\7%\2\2GH\7\"\2\2"+
		"HJ\7%\2\2IG\3\2\2\2JM\3\2\2\2KI\3\2\2\2KL\3\2\2\2LN\3\2\2\2MK\3\2\2\2"+
		"NP\7!\2\2OC\3\2\2\2OE\3\2\2\2P\r\3\2\2\2QR\7 \2\2RS\7%\2\2ST\7\"\2\2T"+
		"U\7#\2\2Ua\7!\2\2VW\7 \2\2W\\\7%\2\2XY\7\"\2\2Y[\7%\2\2ZX\3\2\2\2[^\3"+
		"\2\2\2\\Z\3\2\2\2\\]\3\2\2\2]_\3\2\2\2^\\\3\2\2\2_a\7!\2\2`Q\3\2\2\2`"+
		"V\3\2\2\2a\17\3\2\2\2bd\7$\2\2cb\3\2\2\2cd\3\2\2\2de\3\2\2\2ef\7%\2\2"+
		"f\21\3\2\2\2\r\25\31\36\',=KO\\`c";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}