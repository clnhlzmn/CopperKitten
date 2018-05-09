// Generated from C:/code/CopperKitten/CKAssembler/src\ckasm.g4 by ANTLR 4.7
package com.cph;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ckasmParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, LABEL=29, INTEGER=30, WHITESPACE=31, 
		COMMENT=32;
	public static final int
		RULE_file = 0, RULE_instruction = 1;
	public static final String[] ruleNames = {
		"file", "instruction"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "':'", "'add'", "'sub'", "'mul'", "'div'", "'mod'", "'cmp'", "'call'", 
		"'return'", "'jump'", "'jumpz'", "'jumpo'", "'jumpoz'", "'push'", "'pushw'", 
		"'dup'", "'pop'", "'swap'", "'halt'", "'pushref'", "'popref'", "'enter'", 
		"'leave'", "'in'", "'out'", "'alloc'", "'refget'", "'refset'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, "LABEL", "INTEGER", "WHITESPACE", "COMMENT"
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
	public String getGrammarFileName() { return "ckasm.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ckasmParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class FileContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(ckasmParser.EOF, 0); }
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public FileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckasmVisitor ) return ((ckasmVisitor<? extends T>)visitor).visitFile(this);
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
			setState(7);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << LABEL))) != 0)) {
				{
				{
				setState(4);
				instruction();
				}
				}
				setState(9);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(10);
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
	public static class JumpzContext extends InstructionContext {
		public JumpzContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckasmVisitor ) return ((ckasmVisitor<? extends T>)visitor).visitJumpz(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SubContext extends InstructionContext {
		public SubContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckasmVisitor ) return ((ckasmVisitor<? extends T>)visitor).visitSub(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ModContext extends InstructionContext {
		public ModContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckasmVisitor ) return ((ckasmVisitor<? extends T>)visitor).visitMod(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MulContext extends InstructionContext {
		public MulContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckasmVisitor ) return ((ckasmVisitor<? extends T>)visitor).visitMul(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OutIntegerContext extends InstructionContext {
		public TerminalNode INTEGER() { return getToken(ckasmParser.INTEGER, 0); }
		public OutIntegerContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckasmVisitor ) return ((ckasmVisitor<? extends T>)visitor).visitOutInteger(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CmpContext extends InstructionContext {
		public CmpContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckasmVisitor ) return ((ckasmVisitor<? extends T>)visitor).visitCmp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ModIntegerContext extends InstructionContext {
		public TerminalNode INTEGER() { return getToken(ckasmParser.INTEGER, 0); }
		public ModIntegerContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckasmVisitor ) return ((ckasmVisitor<? extends T>)visitor).visitModInteger(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RefgetIntegerContext extends InstructionContext {
		public TerminalNode INTEGER() { return getToken(ckasmParser.INTEGER, 0); }
		public RefgetIntegerContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckasmVisitor ) return ((ckasmVisitor<? extends T>)visitor).visitRefgetInteger(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class JumpoContext extends InstructionContext {
		public TerminalNode INTEGER() { return getToken(ckasmParser.INTEGER, 0); }
		public JumpoContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckasmVisitor ) return ((ckasmVisitor<? extends T>)visitor).visitJumpo(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PoprefContext extends InstructionContext {
		public PoprefContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckasmVisitor ) return ((ckasmVisitor<? extends T>)visitor).visitPopref(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RefsetIntegerContext extends InstructionContext {
		public TerminalNode INTEGER() { return getToken(ckasmParser.INTEGER, 0); }
		public RefsetIntegerContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckasmVisitor ) return ((ckasmVisitor<? extends T>)visitor).visitRefsetInteger(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OutContext extends InstructionContext {
		public OutContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckasmVisitor ) return ((ckasmVisitor<? extends T>)visitor).visitOut(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DivContext extends InstructionContext {
		public DivContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckasmVisitor ) return ((ckasmVisitor<? extends T>)visitor).visitDiv(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PopContext extends InstructionContext {
		public PopContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckasmVisitor ) return ((ckasmVisitor<? extends T>)visitor).visitPop(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LeaveContext extends InstructionContext {
		public LeaveContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckasmVisitor ) return ((ckasmVisitor<? extends T>)visitor).visitLeave(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AllocIntIntContext extends InstructionContext {
		public List<TerminalNode> INTEGER() { return getTokens(ckasmParser.INTEGER); }
		public TerminalNode INTEGER(int i) {
			return getToken(ckasmParser.INTEGER, i);
		}
		public AllocIntIntContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckasmVisitor ) return ((ckasmVisitor<? extends T>)visitor).visitAllocIntInt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DivIntegerContext extends InstructionContext {
		public TerminalNode INTEGER() { return getToken(ckasmParser.INTEGER, 0); }
		public DivIntegerContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckasmVisitor ) return ((ckasmVisitor<? extends T>)visitor).visitDivInteger(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RefsetIntIntContext extends InstructionContext {
		public List<TerminalNode> INTEGER() { return getTokens(ckasmParser.INTEGER); }
		public TerminalNode INTEGER(int i) {
			return getToken(ckasmParser.INTEGER, i);
		}
		public RefsetIntIntContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckasmVisitor ) return ((ckasmVisitor<? extends T>)visitor).visitRefsetIntInt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EnterContext extends InstructionContext {
		public EnterContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckasmVisitor ) return ((ckasmVisitor<? extends T>)visitor).visitEnter(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class JumpozContext extends InstructionContext {
		public TerminalNode INTEGER() { return getToken(ckasmParser.INTEGER, 0); }
		public JumpozContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckasmVisitor ) return ((ckasmVisitor<? extends T>)visitor).visitJumpoz(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class JumpzLabelContext extends InstructionContext {
		public TerminalNode LABEL() { return getToken(ckasmParser.LABEL, 0); }
		public JumpzLabelContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckasmVisitor ) return ((ckasmVisitor<? extends T>)visitor).visitJumpzLabel(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class JumpContext extends InstructionContext {
		public JumpContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckasmVisitor ) return ((ckasmVisitor<? extends T>)visitor).visitJump(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PushrefContext extends InstructionContext {
		public PushrefContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckasmVisitor ) return ((ckasmVisitor<? extends T>)visitor).visitPushref(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AddContext extends InstructionContext {
		public AddContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckasmVisitor ) return ((ckasmVisitor<? extends T>)visitor).visitAdd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MulIntegerContext extends InstructionContext {
		public TerminalNode INTEGER() { return getToken(ckasmParser.INTEGER, 0); }
		public MulIntegerContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckasmVisitor ) return ((ckasmVisitor<? extends T>)visitor).visitMulInteger(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CmpIntegerContext extends InstructionContext {
		public TerminalNode INTEGER() { return getToken(ckasmParser.INTEGER, 0); }
		public CmpIntegerContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckasmVisitor ) return ((ckasmVisitor<? extends T>)visitor).visitCmpInteger(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class JumpLabelContext extends InstructionContext {
		public TerminalNode LABEL() { return getToken(ckasmParser.LABEL, 0); }
		public JumpLabelContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckasmVisitor ) return ((ckasmVisitor<? extends T>)visitor).visitJumpLabel(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RefgetContext extends InstructionContext {
		public RefgetContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckasmVisitor ) return ((ckasmVisitor<? extends T>)visitor).visitRefget(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SwapContext extends InstructionContext {
		public SwapContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckasmVisitor ) return ((ckasmVisitor<? extends T>)visitor).visitSwap(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class InContext extends InstructionContext {
		public InContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckasmVisitor ) return ((ckasmVisitor<? extends T>)visitor).visitIn(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CallLabelContext extends InstructionContext {
		public TerminalNode LABEL() { return getToken(ckasmParser.LABEL, 0); }
		public CallLabelContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckasmVisitor ) return ((ckasmVisitor<? extends T>)visitor).visitCallLabel(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RefsetContext extends InstructionContext {
		public RefsetContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckasmVisitor ) return ((ckasmVisitor<? extends T>)visitor).visitRefset(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LabelContext extends InstructionContext {
		public TerminalNode LABEL() { return getToken(ckasmParser.LABEL, 0); }
		public LabelContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckasmVisitor ) return ((ckasmVisitor<? extends T>)visitor).visitLabel(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PushContext extends InstructionContext {
		public TerminalNode INTEGER() { return getToken(ckasmParser.INTEGER, 0); }
		public PushContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckasmVisitor ) return ((ckasmVisitor<? extends T>)visitor).visitPush(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AddIntegerContext extends InstructionContext {
		public TerminalNode INTEGER() { return getToken(ckasmParser.INTEGER, 0); }
		public AddIntegerContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckasmVisitor ) return ((ckasmVisitor<? extends T>)visitor).visitAddInteger(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CallContext extends InstructionContext {
		public CallContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckasmVisitor ) return ((ckasmVisitor<? extends T>)visitor).visitCall(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class HaltContext extends InstructionContext {
		public HaltContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckasmVisitor ) return ((ckasmVisitor<? extends T>)visitor).visitHalt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SubIntegerContext extends InstructionContext {
		public TerminalNode INTEGER() { return getToken(ckasmParser.INTEGER, 0); }
		public SubIntegerContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckasmVisitor ) return ((ckasmVisitor<? extends T>)visitor).visitSubInteger(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PushwContext extends InstructionContext {
		public TerminalNode INTEGER() { return getToken(ckasmParser.INTEGER, 0); }
		public PushwContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckasmVisitor ) return ((ckasmVisitor<? extends T>)visitor).visitPushw(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AllocContext extends InstructionContext {
		public AllocContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckasmVisitor ) return ((ckasmVisitor<? extends T>)visitor).visitAlloc(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AllocIntegerContext extends InstructionContext {
		public TerminalNode INTEGER() { return getToken(ckasmParser.INTEGER, 0); }
		public AllocIntegerContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckasmVisitor ) return ((ckasmVisitor<? extends T>)visitor).visitAllocInteger(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ReturnContext extends InstructionContext {
		public ReturnContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckasmVisitor ) return ((ckasmVisitor<? extends T>)visitor).visitReturn(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DupContext extends InstructionContext {
		public DupContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckasmVisitor ) return ((ckasmVisitor<? extends T>)visitor).visitDup(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstructionContext instruction() throws RecognitionException {
		InstructionContext _localctx = new InstructionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_instruction);
		try {
			setState(77);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				_localctx = new LabelContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(12);
				match(LABEL);
				setState(13);
				match(T__0);
				}
				break;
			case 2:
				_localctx = new AddContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(14);
				match(T__1);
				}
				break;
			case 3:
				_localctx = new AddIntegerContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(15);
				match(T__1);
				setState(16);
				match(INTEGER);
				}
				break;
			case 4:
				_localctx = new SubContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(17);
				match(T__2);
				}
				break;
			case 5:
				_localctx = new SubIntegerContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(18);
				match(T__2);
				setState(19);
				match(INTEGER);
				}
				break;
			case 6:
				_localctx = new MulContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(20);
				match(T__3);
				}
				break;
			case 7:
				_localctx = new MulIntegerContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(21);
				match(T__3);
				setState(22);
				match(INTEGER);
				}
				break;
			case 8:
				_localctx = new DivContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(23);
				match(T__4);
				}
				break;
			case 9:
				_localctx = new DivIntegerContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(24);
				match(T__4);
				setState(25);
				match(INTEGER);
				}
				break;
			case 10:
				_localctx = new ModContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(26);
				match(T__5);
				}
				break;
			case 11:
				_localctx = new ModIntegerContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(27);
				match(T__5);
				setState(28);
				match(INTEGER);
				}
				break;
			case 12:
				_localctx = new CmpContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(29);
				match(T__6);
				}
				break;
			case 13:
				_localctx = new CmpIntegerContext(_localctx);
				enterOuterAlt(_localctx, 13);
				{
				setState(30);
				match(T__6);
				setState(31);
				match(INTEGER);
				}
				break;
			case 14:
				_localctx = new CallContext(_localctx);
				enterOuterAlt(_localctx, 14);
				{
				setState(32);
				match(T__7);
				}
				break;
			case 15:
				_localctx = new CallLabelContext(_localctx);
				enterOuterAlt(_localctx, 15);
				{
				setState(33);
				match(T__7);
				setState(34);
				match(LABEL);
				}
				break;
			case 16:
				_localctx = new ReturnContext(_localctx);
				enterOuterAlt(_localctx, 16);
				{
				setState(35);
				match(T__8);
				}
				break;
			case 17:
				_localctx = new JumpContext(_localctx);
				enterOuterAlt(_localctx, 17);
				{
				setState(36);
				match(T__9);
				}
				break;
			case 18:
				_localctx = new JumpzContext(_localctx);
				enterOuterAlt(_localctx, 18);
				{
				setState(37);
				match(T__10);
				}
				break;
			case 19:
				_localctx = new JumpoContext(_localctx);
				enterOuterAlt(_localctx, 19);
				{
				setState(38);
				match(T__11);
				setState(39);
				match(INTEGER);
				}
				break;
			case 20:
				_localctx = new JumpozContext(_localctx);
				enterOuterAlt(_localctx, 20);
				{
				setState(40);
				match(T__12);
				setState(41);
				match(INTEGER);
				}
				break;
			case 21:
				_localctx = new JumpLabelContext(_localctx);
				enterOuterAlt(_localctx, 21);
				{
				setState(42);
				match(T__9);
				setState(43);
				match(LABEL);
				}
				break;
			case 22:
				_localctx = new JumpzLabelContext(_localctx);
				enterOuterAlt(_localctx, 22);
				{
				setState(44);
				match(T__10);
				setState(45);
				match(LABEL);
				}
				break;
			case 23:
				_localctx = new PushContext(_localctx);
				enterOuterAlt(_localctx, 23);
				{
				setState(46);
				match(T__13);
				setState(47);
				match(INTEGER);
				}
				break;
			case 24:
				_localctx = new PushwContext(_localctx);
				enterOuterAlt(_localctx, 24);
				{
				setState(48);
				match(T__14);
				setState(49);
				match(INTEGER);
				}
				break;
			case 25:
				_localctx = new DupContext(_localctx);
				enterOuterAlt(_localctx, 25);
				{
				setState(50);
				match(T__15);
				}
				break;
			case 26:
				_localctx = new PopContext(_localctx);
				enterOuterAlt(_localctx, 26);
				{
				setState(51);
				match(T__16);
				}
				break;
			case 27:
				_localctx = new SwapContext(_localctx);
				enterOuterAlt(_localctx, 27);
				{
				setState(52);
				match(T__17);
				}
				break;
			case 28:
				_localctx = new HaltContext(_localctx);
				enterOuterAlt(_localctx, 28);
				{
				setState(53);
				match(T__18);
				}
				break;
			case 29:
				_localctx = new PushrefContext(_localctx);
				enterOuterAlt(_localctx, 29);
				{
				setState(54);
				match(T__19);
				}
				break;
			case 30:
				_localctx = new PoprefContext(_localctx);
				enterOuterAlt(_localctx, 30);
				{
				setState(55);
				match(T__20);
				}
				break;
			case 31:
				_localctx = new EnterContext(_localctx);
				enterOuterAlt(_localctx, 31);
				{
				setState(56);
				match(T__21);
				}
				break;
			case 32:
				_localctx = new LeaveContext(_localctx);
				enterOuterAlt(_localctx, 32);
				{
				setState(57);
				match(T__22);
				}
				break;
			case 33:
				_localctx = new InContext(_localctx);
				enterOuterAlt(_localctx, 33);
				{
				setState(58);
				match(T__23);
				}
				break;
			case 34:
				_localctx = new OutContext(_localctx);
				enterOuterAlt(_localctx, 34);
				{
				setState(59);
				match(T__24);
				}
				break;
			case 35:
				_localctx = new OutIntegerContext(_localctx);
				enterOuterAlt(_localctx, 35);
				{
				setState(60);
				match(T__24);
				setState(61);
				match(INTEGER);
				}
				break;
			case 36:
				_localctx = new AllocContext(_localctx);
				enterOuterAlt(_localctx, 36);
				{
				setState(62);
				match(T__25);
				}
				break;
			case 37:
				_localctx = new AllocIntegerContext(_localctx);
				enterOuterAlt(_localctx, 37);
				{
				setState(63);
				match(T__25);
				setState(64);
				match(INTEGER);
				}
				break;
			case 38:
				_localctx = new AllocIntIntContext(_localctx);
				enterOuterAlt(_localctx, 38);
				{
				setState(65);
				match(T__25);
				setState(66);
				match(INTEGER);
				setState(67);
				match(INTEGER);
				}
				break;
			case 39:
				_localctx = new RefgetContext(_localctx);
				enterOuterAlt(_localctx, 39);
				{
				setState(68);
				match(T__26);
				}
				break;
			case 40:
				_localctx = new RefgetIntegerContext(_localctx);
				enterOuterAlt(_localctx, 40);
				{
				setState(69);
				match(T__26);
				setState(70);
				match(INTEGER);
				}
				break;
			case 41:
				_localctx = new RefsetContext(_localctx);
				enterOuterAlt(_localctx, 41);
				{
				setState(71);
				match(T__27);
				}
				break;
			case 42:
				_localctx = new RefsetIntegerContext(_localctx);
				enterOuterAlt(_localctx, 42);
				{
				setState(72);
				match(T__27);
				setState(73);
				match(INTEGER);
				}
				break;
			case 43:
				_localctx = new RefsetIntIntContext(_localctx);
				enterOuterAlt(_localctx, 43);
				{
				setState(74);
				match(T__27);
				setState(75);
				match(INTEGER);
				setState(76);
				match(INTEGER);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\"R\4\2\t\2\4\3\t"+
		"\3\3\2\7\2\b\n\2\f\2\16\2\13\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\5\3P\n\3\3\3\2\2\4\2\4\2\2\2z\2\t\3\2\2\2\4O\3"+
		"\2\2\2\6\b\5\4\3\2\7\6\3\2\2\2\b\13\3\2\2\2\t\7\3\2\2\2\t\n\3\2\2\2\n"+
		"\f\3\2\2\2\13\t\3\2\2\2\f\r\7\2\2\3\r\3\3\2\2\2\16\17\7\37\2\2\17P\7\3"+
		"\2\2\20P\7\4\2\2\21\22\7\4\2\2\22P\7 \2\2\23P\7\5\2\2\24\25\7\5\2\2\25"+
		"P\7 \2\2\26P\7\6\2\2\27\30\7\6\2\2\30P\7 \2\2\31P\7\7\2\2\32\33\7\7\2"+
		"\2\33P\7 \2\2\34P\7\b\2\2\35\36\7\b\2\2\36P\7 \2\2\37P\7\t\2\2 !\7\t\2"+
		"\2!P\7 \2\2\"P\7\n\2\2#$\7\n\2\2$P\7\37\2\2%P\7\13\2\2&P\7\f\2\2\'P\7"+
		"\r\2\2()\7\16\2\2)P\7 \2\2*+\7\17\2\2+P\7 \2\2,-\7\f\2\2-P\7\37\2\2./"+
		"\7\r\2\2/P\7\37\2\2\60\61\7\20\2\2\61P\7 \2\2\62\63\7\21\2\2\63P\7 \2"+
		"\2\64P\7\22\2\2\65P\7\23\2\2\66P\7\24\2\2\67P\7\25\2\28P\7\26\2\29P\7"+
		"\27\2\2:P\7\30\2\2;P\7\31\2\2<P\7\32\2\2=P\7\33\2\2>?\7\33\2\2?P\7 \2"+
		"\2@P\7\34\2\2AB\7\34\2\2BP\7 \2\2CD\7\34\2\2DE\7 \2\2EP\7 \2\2FP\7\35"+
		"\2\2GH\7\35\2\2HP\7 \2\2IP\7\36\2\2JK\7\36\2\2KP\7 \2\2LM\7\36\2\2MN\7"+
		" \2\2NP\7 \2\2O\16\3\2\2\2O\20\3\2\2\2O\21\3\2\2\2O\23\3\2\2\2O\24\3\2"+
		"\2\2O\26\3\2\2\2O\27\3\2\2\2O\31\3\2\2\2O\32\3\2\2\2O\34\3\2\2\2O\35\3"+
		"\2\2\2O\37\3\2\2\2O \3\2\2\2O\"\3\2\2\2O#\3\2\2\2O%\3\2\2\2O&\3\2\2\2"+
		"O\'\3\2\2\2O(\3\2\2\2O*\3\2\2\2O,\3\2\2\2O.\3\2\2\2O\60\3\2\2\2O\62\3"+
		"\2\2\2O\64\3\2\2\2O\65\3\2\2\2O\66\3\2\2\2O\67\3\2\2\2O8\3\2\2\2O9\3\2"+
		"\2\2O:\3\2\2\2O;\3\2\2\2O<\3\2\2\2O=\3\2\2\2O>\3\2\2\2O@\3\2\2\2OA\3\2"+
		"\2\2OC\3\2\2\2OF\3\2\2\2OG\3\2\2\2OI\3\2\2\2OJ\3\2\2\2OL\3\2\2\2P\5\3"+
		"\2\2\2\4\tO";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}