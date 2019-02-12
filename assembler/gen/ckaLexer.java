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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		NATURAL=10, ID=11, LABEL=12, NL=13, WHITESPACE=14, COMMENT=15;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"NATURAL", "ID", "LABEL", "NL", "WHITESPACE", "COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "':'", "'push'", "'layout'", "'alloc'", "'['", "']'", "','", "'*'", 
			"'-'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, "NATURAL", 
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\21k\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\3\3\3\3\3"+
		"\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3"+
		"\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\6\13A\n\13\r\13\16\13B\3\f\3\f\7\f"+
		"G\n\f\f\f\16\fJ\13\f\3\r\3\r\7\rN\n\r\f\r\16\rQ\13\r\3\16\3\16\3\16\5"+
		"\16V\n\16\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\7\20`\n\20\f\20\16\20"+
		"c\13\20\3\20\5\20f\n\20\3\20\3\20\3\20\3\20\2\2\21\3\3\5\4\7\5\t\6\13"+
		"\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21\3\2\6\4\2aac"+
		"|\6\2\62;C\\aac|\5\2\13\13\16\16\"\"\4\2\f\f\17\17\2p\2\3\3\2\2\2\2\5"+
		"\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2"+
		"\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33"+
		"\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\3!\3\2\2\2\5#\3\2\2\2\7(\3\2\2\2\t"+
		"/\3\2\2\2\13\65\3\2\2\2\r\67\3\2\2\2\179\3\2\2\2\21;\3\2\2\2\23=\3\2\2"+
		"\2\25@\3\2\2\2\27D\3\2\2\2\31K\3\2\2\2\33U\3\2\2\2\35W\3\2\2\2\37[\3\2"+
		"\2\2!\"\7<\2\2\"\4\3\2\2\2#$\7r\2\2$%\7w\2\2%&\7u\2\2&\'\7j\2\2\'\6\3"+
		"\2\2\2()\7n\2\2)*\7c\2\2*+\7{\2\2+,\7q\2\2,-\7w\2\2-.\7v\2\2.\b\3\2\2"+
		"\2/\60\7c\2\2\60\61\7n\2\2\61\62\7n\2\2\62\63\7q\2\2\63\64\7e\2\2\64\n"+
		"\3\2\2\2\65\66\7]\2\2\66\f\3\2\2\2\678\7_\2\28\16\3\2\2\29:\7.\2\2:\20"+
		"\3\2\2\2;<\7,\2\2<\22\3\2\2\2=>\7/\2\2>\24\3\2\2\2?A\4\62;\2@?\3\2\2\2"+
		"AB\3\2\2\2B@\3\2\2\2BC\3\2\2\2C\26\3\2\2\2DH\t\2\2\2EG\t\3\2\2FE\3\2\2"+
		"\2GJ\3\2\2\2HF\3\2\2\2HI\3\2\2\2I\30\3\2\2\2JH\3\2\2\2KO\4C\\\2LN\t\3"+
		"\2\2ML\3\2\2\2NQ\3\2\2\2OM\3\2\2\2OP\3\2\2\2P\32\3\2\2\2QO\3\2\2\2RV\7"+
		"\f\2\2ST\7\17\2\2TV\7\f\2\2UR\3\2\2\2US\3\2\2\2V\34\3\2\2\2WX\t\4\2\2"+
		"XY\3\2\2\2YZ\b\17\2\2Z\36\3\2\2\2[\\\7\61\2\2\\]\7\61\2\2]a\3\2\2\2^`"+
		"\n\5\2\2_^\3\2\2\2`c\3\2\2\2a_\3\2\2\2ab\3\2\2\2be\3\2\2\2ca\3\2\2\2d"+
		"f\7\17\2\2ed\3\2\2\2ef\3\2\2\2fg\3\2\2\2gh\7\f\2\2hi\3\2\2\2ij\b\20\2"+
		"\2j \3\2\2\2\t\2BHOUae\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}