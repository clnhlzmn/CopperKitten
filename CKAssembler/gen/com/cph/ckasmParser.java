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
		T__0=1, MNEMONIC=2, LABEL=3, INTEGER=4, WHITESPACE=5, COMMENT=6;
	public static final int
		RULE_file = 0, RULE_instruction = 1;
	public static final String[] ruleNames = {
		"file", "instruction"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "':'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, "MNEMONIC", "LABEL", "INTEGER", "WHITESPACE", "COMMENT"
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
			while (_la==MNEMONIC || _la==LABEL) {
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
	public static class MnemonicLabelContext extends InstructionContext {
		public TerminalNode MNEMONIC() { return getToken(ckasmParser.MNEMONIC, 0); }
		public TerminalNode LABEL() { return getToken(ckasmParser.LABEL, 0); }
		public MnemonicLabelContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckasmVisitor ) return ((ckasmVisitor<? extends T>)visitor).visitMnemonicLabel(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MnemonicIntegerContext extends InstructionContext {
		public TerminalNode MNEMONIC() { return getToken(ckasmParser.MNEMONIC, 0); }
		public TerminalNode INTEGER() { return getToken(ckasmParser.INTEGER, 0); }
		public MnemonicIntegerContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckasmVisitor ) return ((ckasmVisitor<? extends T>)visitor).visitMnemonicInteger(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MnemonicContext extends InstructionContext {
		public TerminalNode MNEMONIC() { return getToken(ckasmParser.MNEMONIC, 0); }
		public MnemonicContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckasmVisitor ) return ((ckasmVisitor<? extends T>)visitor).visitMnemonic(this);
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

	public final InstructionContext instruction() throws RecognitionException {
		InstructionContext _localctx = new InstructionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_instruction);
		try {
			setState(19);
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
				_localctx = new MnemonicContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(14);
				match(MNEMONIC);
				}
				break;
			case 3:
				_localctx = new MnemonicIntegerContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(15);
				match(MNEMONIC);
				setState(16);
				match(INTEGER);
				}
				break;
			case 4:
				_localctx = new MnemonicLabelContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(17);
				match(MNEMONIC);
				setState(18);
				match(LABEL);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\b\30\4\2\t\2\4\3"+
		"\t\3\3\2\7\2\b\n\2\f\2\16\2\13\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\5\3\26\n\3\3\3\2\2\4\2\4\2\2\2\31\2\t\3\2\2\2\4\25\3\2\2\2\6\b\5\4"+
		"\3\2\7\6\3\2\2\2\b\13\3\2\2\2\t\7\3\2\2\2\t\n\3\2\2\2\n\f\3\2\2\2\13\t"+
		"\3\2\2\2\f\r\7\2\2\3\r\3\3\2\2\2\16\17\7\5\2\2\17\26\7\3\2\2\20\26\7\4"+
		"\2\2\21\22\7\4\2\2\22\26\7\6\2\2\23\24\7\4\2\2\24\26\7\5\2\2\25\16\3\2"+
		"\2\2\25\20\3\2\2\2\25\21\3\2\2\2\25\23\3\2\2\2\26\5\3\2\2\2\4\t\25";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}