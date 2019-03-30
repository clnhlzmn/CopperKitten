// Generated from /Users/colinholzman/Documents/CopperKitten/compiler/cka/grammar/cka.g4 by ANTLR 4.7.2
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
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45, 
		T__45=46, T__46=47, T__47=48, T__48=49, T__49=50, T__50=51, T__51=52, 
		T__52=53, T__53=54, T__54=55, T__55=56, T__56=57, T__57=58, NATURAL=59, 
		ID=60, LABEL=61, STRING=62, WHITESPACE=63, COMMENT=64;
	public static final int
		RULE_file = 0, RULE_instructions = 1, RULE_instruction = 2, RULE_literalLabelMnemonic = 3, 
		RULE_literalIntMnemonic = 4, RULE_literalStringMnemonic = 5, RULE_simpleInstruction = 6, 
		RULE_frameLayout = 7, RULE_allocLayout = 8, RULE_integer = 9;
	private static String[] makeRuleNames() {
		return new String[] {
			"file", "instructions", "instruction", "literalLabelMnemonic", "literalIntMnemonic", 
			"literalStringMnemonic", "simpleInstruction", "frameLayout", "allocLayout", 
			"integer"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "':'", "'layout'", "'alloc'", "'ncall'", "'push'", "'jump'", "'jumpz'", 
			"'jumpnz'", "'aload'", "'astore'", "'lload'", "'lstore'", "'rload'", 
			"'rstore'", "'cload'", "'cstore'", "'debugpush'", "'add'", "'sub'", "'mul'", 
			"'div'", "'mod'", "'shl'", "'shr'", "'neg'", "'not'", "'bitnot'", "'bitand'", 
			"'bitxor'", "'bitor'", "'lt'", "'lte'", "'gt'", "'gte'", "'eq'", "'neq'", 
			"'cmp'", "'call'", "'return'", "'dup'", "'pop'", "'swap'", "'enter'", 
			"'leave'", "'nop'", "'halt'", "'load'", "'store'", "'rbarrier'", "'wbarrier'", 
			"'debugpop'", "'clear'", "'goto'", "'['", "']'", "','", "'*'", "'-'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, "NATURAL", 
			"ID", "LABEL", "STRING", "WHITESPACE", "COMMENT"
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
			setState(21);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << T__38) | (1L << T__39) | (1L << T__40) | (1L << T__41) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << T__45) | (1L << T__46) | (1L << T__47) | (1L << T__48) | (1L << T__49) | (1L << T__50) | (1L << T__51) | (1L << T__52) | (1L << LABEL))) != 0)) {
				{
				setState(20);
				instructions();
				}
			}

			setState(23);
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
			setState(25);
			instruction();
			setState(29);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << T__38) | (1L << T__39) | (1L << T__40) | (1L << T__41) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << T__45) | (1L << T__46) | (1L << T__47) | (1L << T__48) | (1L << T__49) | (1L << T__50) | (1L << T__51) | (1L << T__52) | (1L << LABEL))) != 0)) {
				{
				{
				setState(26);
				instruction();
				}
				}
				setState(31);
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
	public static class LiteralStringInstContext extends InstructionContext {
		public LiteralStringMnemonicContext literalStringMnemonic() {
			return getRuleContext(LiteralStringMnemonicContext.class,0);
		}
		public TerminalNode STRING() { return getToken(ckaParser.STRING, 0); }
		public LiteralStringInstContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).enterLiteralStringInst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).exitLiteralStringInst(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckaVisitor ) return ((ckaVisitor<? extends T>)visitor).visitLiteralStringInst(this);
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
	public static class NcallInstContext extends InstructionContext {
		public TerminalNode ID() { return getToken(ckaParser.ID, 0); }
		public NcallInstContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).enterNcallInst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).exitNcallInst(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckaVisitor ) return ((ckaVisitor<? extends T>)visitor).visitNcallInst(this);
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
			setState(50);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				_localctx = new LabelInstContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(32);
				match(LABEL);
				setState(33);
				match(T__0);
				}
				break;
			case 2:
				_localctx = new SimpleInstContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(34);
				simpleInstruction();
				}
				break;
			case 3:
				_localctx = new LiteralIntInstContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(35);
				literalIntMnemonic();
				setState(36);
				integer();
				}
				break;
			case 4:
				_localctx = new LiteralLabelInstContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(38);
				literalLabelMnemonic();
				setState(39);
				match(LABEL);
				}
				break;
			case 5:
				_localctx = new LiteralStringInstContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(41);
				literalStringMnemonic();
				setState(42);
				match(STRING);
				}
				break;
			case 6:
				_localctx = new LayoutInstContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(44);
				match(T__1);
				setState(45);
				frameLayout();
				}
				break;
			case 7:
				_localctx = new AllocInstContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(46);
				match(T__2);
				setState(47);
				allocLayout();
				}
				break;
			case 8:
				_localctx = new NcallInstContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(48);
				match(T__3);
				setState(49);
				match(ID);
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
			setState(52);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7))) != 0)) ) {
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
			setState(54);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15))) != 0)) ) {
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

	public static class LiteralStringMnemonicContext extends ParserRuleContext {
		public LiteralStringMnemonicContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literalStringMnemonic; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).enterLiteralStringMnemonic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).exitLiteralStringMnemonic(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckaVisitor ) return ((ckaVisitor<? extends T>)visitor).visitLiteralStringMnemonic(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralStringMnemonicContext literalStringMnemonic() throws RecognitionException {
		LiteralStringMnemonicContext _localctx = new LiteralStringMnemonicContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_literalStringMnemonic);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			match(T__16);
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
		enterRule(_localctx, 12, RULE_simpleInstruction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << T__38) | (1L << T__39) | (1L << T__40) | (1L << T__41) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << T__45) | (1L << T__46) | (1L << T__47) | (1L << T__48) | (1L << T__49) | (1L << T__50) | (1L << T__51) | (1L << T__52))) != 0)) ) {
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
		enterRule(_localctx, 14, RULE_frameLayout);
		int _la;
		try {
			setState(72);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				_localctx = new EmptyFrameLayoutContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(60);
				match(T__53);
				setState(61);
				match(T__54);
				}
				break;
			case 2:
				_localctx = new NonEmptyFrameLayoutContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(62);
				match(T__53);
				setState(63);
				match(NATURAL);
				setState(68);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__55) {
					{
					{
					setState(64);
					match(T__55);
					setState(65);
					match(NATURAL);
					}
					}
					setState(70);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(71);
				match(T__54);
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
		enterRule(_localctx, 16, RULE_allocLayout);
		int _la;
		try {
			setState(89);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				_localctx = new RefArrayLayoutContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(74);
				match(T__53);
				setState(75);
				match(T__56);
				setState(76);
				match(T__54);
				}
				break;
			case 2:
				_localctx = new EmptyCustomLayoutContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(77);
				match(T__53);
				setState(78);
				match(T__54);
				}
				break;
			case 3:
				_localctx = new CustomLayoutContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(79);
				match(T__53);
				setState(80);
				match(NATURAL);
				setState(85);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__55) {
					{
					{
					setState(81);
					match(T__55);
					setState(82);
					match(NATURAL);
					}
					}
					setState(87);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(88);
				match(T__54);
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
		enterRule(_localctx, 18, RULE_integer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__57) {
				{
				setState(91);
				match(T__57);
				}
			}

			setState(94);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3Bc\4\2\t\2\4\3\t\3"+
		"\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\3\2"+
		"\5\2\30\n\2\3\2\3\2\3\3\3\3\7\3\36\n\3\f\3\16\3!\13\3\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\65\n\4\3"+
		"\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\7\tE\n\t\f\t\16"+
		"\tH\13\t\3\t\5\tK\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\7\nV\n\n\f\n"+
		"\16\nY\13\n\3\n\5\n\\\n\n\3\13\5\13_\n\13\3\13\3\13\3\13\2\2\f\2\4\6\b"+
		"\n\f\16\20\22\24\2\5\3\2\7\n\4\2\7\7\13\22\3\2\24\67\2g\2\27\3\2\2\2\4"+
		"\33\3\2\2\2\6\64\3\2\2\2\b\66\3\2\2\2\n8\3\2\2\2\f:\3\2\2\2\16<\3\2\2"+
		"\2\20J\3\2\2\2\22[\3\2\2\2\24^\3\2\2\2\26\30\5\4\3\2\27\26\3\2\2\2\27"+
		"\30\3\2\2\2\30\31\3\2\2\2\31\32\7\2\2\3\32\3\3\2\2\2\33\37\5\6\4\2\34"+
		"\36\5\6\4\2\35\34\3\2\2\2\36!\3\2\2\2\37\35\3\2\2\2\37 \3\2\2\2 \5\3\2"+
		"\2\2!\37\3\2\2\2\"#\7?\2\2#\65\7\3\2\2$\65\5\16\b\2%&\5\n\6\2&\'\5\24"+
		"\13\2\'\65\3\2\2\2()\5\b\5\2)*\7?\2\2*\65\3\2\2\2+,\5\f\7\2,-\7@\2\2-"+
		"\65\3\2\2\2./\7\4\2\2/\65\5\20\t\2\60\61\7\5\2\2\61\65\5\22\n\2\62\63"+
		"\7\6\2\2\63\65\7>\2\2\64\"\3\2\2\2\64$\3\2\2\2\64%\3\2\2\2\64(\3\2\2\2"+
		"\64+\3\2\2\2\64.\3\2\2\2\64\60\3\2\2\2\64\62\3\2\2\2\65\7\3\2\2\2\66\67"+
		"\t\2\2\2\67\t\3\2\2\289\t\3\2\29\13\3\2\2\2:;\7\23\2\2;\r\3\2\2\2<=\t"+
		"\4\2\2=\17\3\2\2\2>?\78\2\2?K\79\2\2@A\78\2\2AF\7=\2\2BC\7:\2\2CE\7=\2"+
		"\2DB\3\2\2\2EH\3\2\2\2FD\3\2\2\2FG\3\2\2\2GI\3\2\2\2HF\3\2\2\2IK\79\2"+
		"\2J>\3\2\2\2J@\3\2\2\2K\21\3\2\2\2LM\78\2\2MN\7;\2\2N\\\79\2\2OP\78\2"+
		"\2P\\\79\2\2QR\78\2\2RW\7=\2\2ST\7:\2\2TV\7=\2\2US\3\2\2\2VY\3\2\2\2W"+
		"U\3\2\2\2WX\3\2\2\2XZ\3\2\2\2YW\3\2\2\2Z\\\79\2\2[L\3\2\2\2[O\3\2\2\2"+
		"[Q\3\2\2\2\\\23\3\2\2\2]_\7<\2\2^]\3\2\2\2^_\3\2\2\2_`\3\2\2\2`a\7=\2"+
		"\2a\25\3\2\2\2\n\27\37\64FJW[^";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}