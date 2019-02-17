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
		T__31=32, T__32=33, T__33=34, T__34=35, NATURAL=36, LABEL=37, WHITESPACE=38, 
		COMMENT=39;
	public static final int
		RULE_file = 0, RULE_instructions = 1, RULE_instruction = 2, RULE_literalLabelMnemonic = 3, 
		RULE_literalIntMnemonic = 4, RULE_simpleInstruction = 5, RULE_frameLayout = 6, 
		RULE_allocLayout = 7, RULE_integer = 8;
	private static String[] makeRuleNames() {
		return new String[] {
			"file", "instructions", "instruction", "literalLabelMnemonic", "literalIntMnemonic", 
			"simpleInstruction", "frameLayout", "allocLayout", "integer"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "':'", "'layout'", "'alloc'", "'push'", "'jump'", "'jumpz'", "'jumpnz'", 
			"'lload'", "'lstore'", "'rload'", "'rstore'", "'add'", "'sub'", "'mul'", 
			"'div'", "'mod'", "'shl'", "'shr'", "'cmp'", "'call'", "'ret'", "'dup'", 
			"'pop'", "'swap'", "'enter'", "'leave'", "'in'", "'out'", "'nop'", "'halt'", 
			"'['", "']'", "','", "'*'", "'-'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			"NATURAL", "LABEL", "WHITESPACE", "COMMENT"
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
			enterOuterAlt(_localctx, 1);
			{
			setState(19);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << LABEL))) != 0)) {
				{
				setState(18);
				instructions();
				}
			}

			setState(21);
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
			enterOuterAlt(_localctx, 1);
			{
			setState(23);
			instruction();
			setState(27);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << LABEL))) != 0)) {
				{
				{
				setState(24);
				instruction();
				}
				}
				setState(29);
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
	public static class LiteralLabelInstContext extends InstructionContext {
		public LiteralLabelMnemonicContext literalLabelMnemonic() {
			return getRuleContext(LiteralLabelMnemonicContext.class,0);
		}
		public TerminalNode LABEL() { return getToken(ckaParser.LABEL, 0); }
		public LiteralLabelInstContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).enterLiteralLabelInst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).exitLiteralLabelInst(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckaVisitor ) return ((ckaVisitor<? extends T>)visitor).visitLiteralLabelInst(this);
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
	public static class LiteralIntInstContext extends InstructionContext {
		public LiteralIntMnemonicContext literalIntMnemonic() {
			return getRuleContext(LiteralIntMnemonicContext.class,0);
		}
		public IntegerContext integer() {
			return getRuleContext(IntegerContext.class,0);
		}
		public LiteralIntInstContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).enterLiteralIntInst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).exitLiteralIntInst(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckaVisitor ) return ((ckaVisitor<? extends T>)visitor).visitLiteralIntInst(this);
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
			setState(43);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				_localctx = new LabelInstContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(30);
				match(LABEL);
				setState(31);
				match(T__0);
				}
				break;
			case 2:
				_localctx = new SimpleInstContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(32);
				simpleInstruction();
				}
				break;
			case 3:
				_localctx = new LiteralIntInstContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(33);
				literalIntMnemonic();
				setState(34);
				integer();
				}
				break;
			case 4:
				_localctx = new LiteralLabelInstContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(36);
				literalLabelMnemonic();
				setState(37);
				match(LABEL);
				}
				break;
			case 5:
				_localctx = new LayoutInstContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(39);
				match(T__1);
				setState(40);
				frameLayout();
				}
				break;
			case 6:
				_localctx = new AllocInstContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(41);
				match(T__2);
				setState(42);
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

	public static class LiteralLabelMnemonicContext extends ParserRuleContext {
		public LiteralLabelMnemonicContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literalLabelMnemonic; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).enterLiteralLabelMnemonic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).exitLiteralLabelMnemonic(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckaVisitor ) return ((ckaVisitor<? extends T>)visitor).visitLiteralLabelMnemonic(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralLabelMnemonicContext literalLabelMnemonic() throws RecognitionException {
		LiteralLabelMnemonicContext _localctx = new LiteralLabelMnemonicContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_literalLabelMnemonic);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6))) != 0)) ) {
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

	public static class LiteralIntMnemonicContext extends ParserRuleContext {
		public LiteralIntMnemonicContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literalIntMnemonic; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).enterLiteralIntMnemonic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).exitLiteralIntMnemonic(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckaVisitor ) return ((ckaVisitor<? extends T>)visitor).visitLiteralIntMnemonic(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralIntMnemonicContext literalIntMnemonic() throws RecognitionException {
		LiteralIntMnemonicContext _localctx = new LiteralIntMnemonicContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_literalIntMnemonic);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10))) != 0)) ) {
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
		enterRule(_localctx, 10, RULE_simpleInstruction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29))) != 0)) ) {
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
		enterRule(_localctx, 12, RULE_frameLayout);
		int _la;
		try {
			setState(63);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				_localctx = new EmptyFrameLayoutContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(51);
				match(T__30);
				setState(52);
				match(T__31);
				}
				break;
			case 2:
				_localctx = new NonEmptyFrameLayoutContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(53);
				match(T__30);
				setState(54);
				match(NATURAL);
				setState(59);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__32) {
					{
					{
					setState(55);
					match(T__32);
					setState(56);
					match(NATURAL);
					}
					}
					setState(61);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(62);
				match(T__31);
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
	public static class EmptyCustomLayoutContext extends AllocLayoutContext {
		public EmptyCustomLayoutContext(AllocLayoutContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).enterEmptyCustomLayout(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).exitEmptyCustomLayout(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckaVisitor ) return ((ckaVisitor<? extends T>)visitor).visitEmptyCustomLayout(this);
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
		enterRule(_localctx, 14, RULE_allocLayout);
		int _la;
		try {
			setState(80);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				_localctx = new RefArrayLayoutContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(65);
				match(T__30);
				setState(66);
				match(T__33);
				setState(67);
				match(T__31);
				}
				break;
			case 2:
				_localctx = new EmptyCustomLayoutContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(68);
				match(T__30);
				setState(69);
				match(T__31);
				}
				break;
			case 3:
				_localctx = new CustomLayoutContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(70);
				match(T__30);
				setState(71);
				match(NATURAL);
				setState(76);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__32) {
					{
					{
					setState(72);
					match(T__32);
					setState(73);
					match(NATURAL);
					}
					}
					setState(78);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(79);
				match(T__31);
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
		enterRule(_localctx, 16, RULE_integer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__34) {
				{
				setState(82);
				match(T__34);
				}
			}

			setState(85);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3)Z\4\2\t\2\4\3\t\3"+
		"\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\5\2\26\n"+
		"\2\3\2\3\2\3\3\3\3\7\3\34\n\3\f\3\16\3\37\13\3\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4.\n\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\7\b<\n\b\f\b\16\b?\13\b\3\b\5\bB\n\b\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\7\tM\n\t\f\t\16\tP\13\t\3\t\5\tS\n\t\3\n\5\nV\n\n"+
		"\3\n\3\n\3\n\2\2\13\2\4\6\b\n\f\16\20\22\2\5\3\2\6\t\4\2\6\6\n\r\3\2\16"+
		" \2]\2\25\3\2\2\2\4\31\3\2\2\2\6-\3\2\2\2\b/\3\2\2\2\n\61\3\2\2\2\f\63"+
		"\3\2\2\2\16A\3\2\2\2\20R\3\2\2\2\22U\3\2\2\2\24\26\5\4\3\2\25\24\3\2\2"+
		"\2\25\26\3\2\2\2\26\27\3\2\2\2\27\30\7\2\2\3\30\3\3\2\2\2\31\35\5\6\4"+
		"\2\32\34\5\6\4\2\33\32\3\2\2\2\34\37\3\2\2\2\35\33\3\2\2\2\35\36\3\2\2"+
		"\2\36\5\3\2\2\2\37\35\3\2\2\2 !\7\'\2\2!.\7\3\2\2\".\5\f\7\2#$\5\n\6\2"+
		"$%\5\22\n\2%.\3\2\2\2&\'\5\b\5\2\'(\7\'\2\2(.\3\2\2\2)*\7\4\2\2*.\5\16"+
		"\b\2+,\7\5\2\2,.\5\20\t\2- \3\2\2\2-\"\3\2\2\2-#\3\2\2\2-&\3\2\2\2-)\3"+
		"\2\2\2-+\3\2\2\2.\7\3\2\2\2/\60\t\2\2\2\60\t\3\2\2\2\61\62\t\3\2\2\62"+
		"\13\3\2\2\2\63\64\t\4\2\2\64\r\3\2\2\2\65\66\7!\2\2\66B\7\"\2\2\678\7"+
		"!\2\28=\7&\2\29:\7#\2\2:<\7&\2\2;9\3\2\2\2<?\3\2\2\2=;\3\2\2\2=>\3\2\2"+
		"\2>@\3\2\2\2?=\3\2\2\2@B\7\"\2\2A\65\3\2\2\2A\67\3\2\2\2B\17\3\2\2\2C"+
		"D\7!\2\2DE\7$\2\2ES\7\"\2\2FG\7!\2\2GS\7\"\2\2HI\7!\2\2IN\7&\2\2JK\7#"+
		"\2\2KM\7&\2\2LJ\3\2\2\2MP\3\2\2\2NL\3\2\2\2NO\3\2\2\2OQ\3\2\2\2PN\3\2"+
		"\2\2QS\7\"\2\2RC\3\2\2\2RF\3\2\2\2RH\3\2\2\2S\21\3\2\2\2TV\7%\2\2UT\3"+
		"\2\2\2UV\3\2\2\2VW\3\2\2\2WX\7&\2\2X\23\3\2\2\2\n\25\35-=ANRU";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}