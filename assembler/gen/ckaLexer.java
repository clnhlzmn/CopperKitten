// Generated from C:/code/CopperKitten/assembler/grammar\cka.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ckaLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, NATURAL=9, 
		ID=10, LABEL=11, NL=12, WHITESPACE=13, COMMENT=14;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "NATURAL", 
			"ID", "LABEL", "NL", "WHITESPACE", "COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "':'", "'layout'", "'alloc'", "'['", "']'", "','", "'*'", "'-'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, "NATURAL", "ID", 
			"LABEL", "NL", "WHITESPACE", "COMMENT"
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


	public ckaLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "cka.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\20d\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t"+
		"\3\n\6\n:\n\n\r\n\16\n;\3\13\3\13\7\13@\n\13\f\13\16\13C\13\13\3\f\3\f"+
		"\7\fG\n\f\f\f\16\fJ\13\f\3\r\3\r\3\r\5\rO\n\r\3\16\3\16\3\16\3\16\3\17"+
		"\3\17\3\17\3\17\7\17Y\n\17\f\17\16\17\\\13\17\3\17\5\17_\n\17\3\17\3\17"+
		"\3\17\3\17\2\2\20\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31"+
		"\16\33\17\35\20\3\2\6\4\2aac|\6\2\62;C\\aac|\5\2\13\13\16\16\"\"\4\2\f"+
		"\f\17\17\2i\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2"+
		"\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\3\37\3\2\2\2\5!\3\2\2"+
		"\2\7(\3\2\2\2\t.\3\2\2\2\13\60\3\2\2\2\r\62\3\2\2\2\17\64\3\2\2\2\21\66"+
		"\3\2\2\2\239\3\2\2\2\25=\3\2\2\2\27D\3\2\2\2\31N\3\2\2\2\33P\3\2\2\2\35"+
		"T\3\2\2\2\37 \7<\2\2 \4\3\2\2\2!\"\7n\2\2\"#\7c\2\2#$\7{\2\2$%\7q\2\2"+
		"%&\7w\2\2&\'\7v\2\2\'\6\3\2\2\2()\7c\2\2)*\7n\2\2*+\7n\2\2+,\7q\2\2,-"+
		"\7e\2\2-\b\3\2\2\2./\7]\2\2/\n\3\2\2\2\60\61\7_\2\2\61\f\3\2\2\2\62\63"+
		"\7.\2\2\63\16\3\2\2\2\64\65\7,\2\2\65\20\3\2\2\2\66\67\7/\2\2\67\22\3"+
		"\2\2\28:\4\62;\298\3\2\2\2:;\3\2\2\2;9\3\2\2\2;<\3\2\2\2<\24\3\2\2\2="+
		"A\t\2\2\2>@\t\3\2\2?>\3\2\2\2@C\3\2\2\2A?\3\2\2\2AB\3\2\2\2B\26\3\2\2"+
		"\2CA\3\2\2\2DH\4C\\\2EG\t\3\2\2FE\3\2\2\2GJ\3\2\2\2HF\3\2\2\2HI\3\2\2"+
		"\2I\30\3\2\2\2JH\3\2\2\2KO\7\f\2\2LM\7\17\2\2MO\7\f\2\2NK\3\2\2\2NL\3"+
		"\2\2\2O\32\3\2\2\2PQ\t\4\2\2QR\3\2\2\2RS\b\16\2\2S\34\3\2\2\2TU\7\61\2"+
		"\2UV\7\61\2\2VZ\3\2\2\2WY\n\5\2\2XW\3\2\2\2Y\\\3\2\2\2ZX\3\2\2\2Z[\3\2"+
		"\2\2[^\3\2\2\2\\Z\3\2\2\2]_\7\17\2\2^]\3\2\2\2^_\3\2\2\2_`\3\2\2\2`a\7"+
		"\f\2\2ab\3\2\2\2bc\b\17\2\2c\36\3\2\2\2\t\2;AHNZ^\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}