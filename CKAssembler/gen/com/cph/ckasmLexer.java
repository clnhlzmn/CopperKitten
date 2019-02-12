// Generated from /Users/colinholzman/Documents/CopperKitten/CKAssembler/src/ckasm.g4 by ANTLR 4.7
package com.cph;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ckasmLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, MNEMONIC=2, LABEL=3, INTEGER=4, WHITESPACE=5, COMMENT=6;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "MNEMONIC", "LABEL", "INTEGER", "WHITESPACE", "COMMENT"
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


	public ckasmLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "ckasm.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\b<\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\3\2\3\2\3\3\6\3\23\n\3\r\3\16\3"+
		"\24\3\4\3\4\7\4\31\n\4\f\4\16\4\34\13\4\3\5\5\5\37\n\5\3\5\6\5\"\n\5\r"+
		"\5\16\5#\3\6\6\6\'\n\6\r\6\16\6(\3\6\3\6\3\7\3\7\3\7\3\7\7\7\61\n\7\f"+
		"\7\16\7\64\13\7\3\7\5\7\67\n\7\3\7\3\7\3\7\3\7\2\2\b\3\3\5\4\7\5\t\6\13"+
		"\7\r\b\3\2\5\6\2\62;C\\aac|\5\2\13\f\17\17\"\"\4\2\f\f\17\17\2B\2\3\3"+
		"\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\3"+
		"\17\3\2\2\2\5\22\3\2\2\2\7\26\3\2\2\2\t\36\3\2\2\2\13&\3\2\2\2\r,\3\2"+
		"\2\2\17\20\7<\2\2\20\4\3\2\2\2\21\23\4c|\2\22\21\3\2\2\2\23\24\3\2\2\2"+
		"\24\22\3\2\2\2\24\25\3\2\2\2\25\6\3\2\2\2\26\32\4C\\\2\27\31\t\2\2\2\30"+
		"\27\3\2\2\2\31\34\3\2\2\2\32\30\3\2\2\2\32\33\3\2\2\2\33\b\3\2\2\2\34"+
		"\32\3\2\2\2\35\37\7/\2\2\36\35\3\2\2\2\36\37\3\2\2\2\37!\3\2\2\2 \"\4"+
		"\62;\2! \3\2\2\2\"#\3\2\2\2#!\3\2\2\2#$\3\2\2\2$\n\3\2\2\2%\'\t\3\2\2"+
		"&%\3\2\2\2\'(\3\2\2\2(&\3\2\2\2()\3\2\2\2)*\3\2\2\2*+\b\6\2\2+\f\3\2\2"+
		"\2,-\7\61\2\2-.\7\61\2\2.\62\3\2\2\2/\61\n\4\2\2\60/\3\2\2\2\61\64\3\2"+
		"\2\2\62\60\3\2\2\2\62\63\3\2\2\2\63\66\3\2\2\2\64\62\3\2\2\2\65\67\7\17"+
		"\2\2\66\65\3\2\2\2\66\67\3\2\2\2\678\3\2\2\289\7\f\2\29:\3\2\2\2:;\b\7"+
		"\2\2;\16\3\2\2\2\n\2\24\32\36#(\62\66\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}