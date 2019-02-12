// Generated from /Users/colinholzman/Documents/CopperKitten/assembler/grammar/cka.g4 by ANTLR 4.7
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
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, NATURAL=9, 
		ID=10, LABEL=11, NL=12, WHITESPACE=13, COMMENT=14;
	public static final int
		RULE_file = 0, RULE_instructions = 1, RULE_instruction = 2, RULE_frameLayout = 3, 
		RULE_allocLayout = 4, RULE_integer = 5;
	public static final String[] ruleNames = {
		"file", "instructions", "instruction", "frameLayout", "allocLayout", "integer"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "':'", "'layout'", "'alloc'", "'['", "']'", "','", "'*'", "'-'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, "NATURAL", "ID", 
		"LABEL", "NL", "WHITESPACE", "COMMENT"
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
			setState(15);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(12);
					match(NL);
					}
					} 
				}
				setState(17);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(19);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__7) | (1L << NATURAL) | (1L << ID) | (1L << LABEL))) != 0)) {
				{
				setState(18);
				instructions();
				}
			}

			setState(24);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(21);
				match(NL);
				}
				}
				setState(26);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(27);
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
			setState(29);
			instruction();
			setState(38);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(31); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(30);
						match(NL);
						}
						}
						setState(33); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==NL );
					setState(35);
					instruction();
					}
					} 
				}
				setState(40);
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
	public static class PushInstContext extends InstructionContext {
		public TerminalNode ID() { return getToken(ckaParser.ID, 0); }
		public IntegerContext integer() {
			return getRuleContext(IntegerContext.class,0);
		}
		public PushInstContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).enterPushInst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).exitPushInst(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckaVisitor ) return ((ckaVisitor<? extends T>)visitor).visitPushInst(this);
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
		public TerminalNode ID() { return getToken(ckaParser.ID, 0); }
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
	public static class IntInstContext extends InstructionContext {
		public IntegerContext integer() {
			return getRuleContext(IntegerContext.class,0);
		}
		public IntInstContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).enterIntInst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckaListener ) ((ckaListener)listener).exitIntInst(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckaVisitor ) return ((ckaVisitor<? extends T>)visitor).visitIntInst(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SimpleInstContext extends InstructionContext {
		public TerminalNode ID() { return getToken(ckaParser.ID, 0); }
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
			setState(53);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				_localctx = new LabelInstContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(41);
				match(LABEL);
				setState(42);
				match(T__0);
				}
				break;
			case 2:
				_localctx = new IntInstContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(43);
				integer();
				}
				break;
			case 3:
				_localctx = new SimpleInstContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(44);
				match(ID);
				}
				break;
			case 4:
				_localctx = new JumpInstContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(45);
				match(ID);
				setState(46);
				match(LABEL);
				}
				break;
			case 5:
				_localctx = new PushInstContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(47);
				match(ID);
				setState(48);
				integer();
				}
				break;
			case 6:
				_localctx = new LayoutInstContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(49);
				match(T__1);
				setState(50);
				frameLayout();
				}
				break;
			case 7:
				_localctx = new AllocInstContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(51);
				match(T__2);
				setState(52);
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
		enterRule(_localctx, 6, RULE_frameLayout);
		int _la;
		try {
			setState(67);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				_localctx = new EmptyFrameLayoutContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(55);
				match(T__3);
				setState(56);
				match(T__4);
				}
				break;
			case 2:
				_localctx = new NonEmptyFrameLayoutContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(57);
				match(T__3);
				setState(58);
				match(NATURAL);
				setState(63);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__5) {
					{
					{
					setState(59);
					match(T__5);
					setState(60);
					match(NATURAL);
					}
					}
					setState(65);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(66);
				match(T__4);
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
			setState(84);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				_localctx = new RefArrayLayoutContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(69);
				match(T__3);
				setState(70);
				match(NATURAL);
				setState(71);
				match(T__5);
				setState(72);
				match(T__6);
				setState(73);
				match(T__4);
				}
				break;
			case 2:
				_localctx = new CustomLayoutContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(74);
				match(T__3);
				setState(75);
				match(NATURAL);
				setState(80);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__5) {
					{
					{
					setState(76);
					match(T__5);
					setState(77);
					match(NATURAL);
					}
					}
					setState(82);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(83);
				match(T__4);
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
		enterRule(_localctx, 10, RULE_integer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(86);
				match(T__7);
				}
			}

			setState(89);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\20^\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\3\2\7\2\20\n\2\f\2\16\2\23\13\2\3\2"+
		"\5\2\26\n\2\3\2\7\2\31\n\2\f\2\16\2\34\13\2\3\2\3\2\3\3\3\3\6\3\"\n\3"+
		"\r\3\16\3#\3\3\7\3\'\n\3\f\3\16\3*\13\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\5\48\n\4\3\5\3\5\3\5\3\5\3\5\3\5\7\5@\n\5\f\5\16\5"+
		"C\13\5\3\5\5\5F\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6Q\n\6\f\6\16"+
		"\6T\13\6\3\6\5\6W\n\6\3\7\5\7Z\n\7\3\7\3\7\3\7\2\2\b\2\4\6\b\n\f\2\2\2"+
		"g\2\21\3\2\2\2\4\37\3\2\2\2\6\67\3\2\2\2\bE\3\2\2\2\nV\3\2\2\2\fY\3\2"+
		"\2\2\16\20\7\16\2\2\17\16\3\2\2\2\20\23\3\2\2\2\21\17\3\2\2\2\21\22\3"+
		"\2\2\2\22\25\3\2\2\2\23\21\3\2\2\2\24\26\5\4\3\2\25\24\3\2\2\2\25\26\3"+
		"\2\2\2\26\32\3\2\2\2\27\31\7\16\2\2\30\27\3\2\2\2\31\34\3\2\2\2\32\30"+
		"\3\2\2\2\32\33\3\2\2\2\33\35\3\2\2\2\34\32\3\2\2\2\35\36\7\2\2\3\36\3"+
		"\3\2\2\2\37(\5\6\4\2 \"\7\16\2\2! \3\2\2\2\"#\3\2\2\2#!\3\2\2\2#$\3\2"+
		"\2\2$%\3\2\2\2%\'\5\6\4\2&!\3\2\2\2\'*\3\2\2\2(&\3\2\2\2()\3\2\2\2)\5"+
		"\3\2\2\2*(\3\2\2\2+,\7\r\2\2,8\7\3\2\2-8\5\f\7\2.8\7\f\2\2/\60\7\f\2\2"+
		"\608\7\r\2\2\61\62\7\f\2\2\628\5\f\7\2\63\64\7\4\2\2\648\5\b\5\2\65\66"+
		"\7\5\2\2\668\5\n\6\2\67+\3\2\2\2\67-\3\2\2\2\67.\3\2\2\2\67/\3\2\2\2\67"+
		"\61\3\2\2\2\67\63\3\2\2\2\67\65\3\2\2\28\7\3\2\2\29:\7\6\2\2:F\7\7\2\2"+
		";<\7\6\2\2<A\7\13\2\2=>\7\b\2\2>@\7\13\2\2?=\3\2\2\2@C\3\2\2\2A?\3\2\2"+
		"\2AB\3\2\2\2BD\3\2\2\2CA\3\2\2\2DF\7\7\2\2E9\3\2\2\2E;\3\2\2\2F\t\3\2"+
		"\2\2GH\7\6\2\2HI\7\13\2\2IJ\7\b\2\2JK\7\t\2\2KW\7\7\2\2LM\7\6\2\2MR\7"+
		"\13\2\2NO\7\b\2\2OQ\7\13\2\2PN\3\2\2\2QT\3\2\2\2RP\3\2\2\2RS\3\2\2\2S"+
		"U\3\2\2\2TR\3\2\2\2UW\7\7\2\2VG\3\2\2\2VL\3\2\2\2W\13\3\2\2\2XZ\7\n\2"+
		"\2YX\3\2\2\2YZ\3\2\2\2Z[\3\2\2\2[\\\7\13\2\2\\\r\3\2\2\2\r\21\25\32#("+
		"\67AERVY";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}