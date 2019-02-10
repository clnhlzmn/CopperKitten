// Generated from C:/code/ck/assembler/grammar\cka.g4 by ANTLR 4.7.2
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
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, NATURAL=33, LABEL=34, NL=35, WHITESPACE=36, COMMENT=37;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
			"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "T__24", 
			"T__25", "T__26", "T__27", "T__28", "T__29", "T__30", "T__31", "NATURAL", 
			"LABEL", "NL", "WHITESPACE", "COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'add'", "'sub'", "'mul'", "'div'", "'mod'", "'shl'", "'shr'", 
			"'cmp'", "'skipz'", "'ip'", "'fp'", "'jump'", "'push'", "'dup'", "'pop'", 
			"'swap'", "'enter'", "'leave'", "'in'", "'out'", "'layout'", "'alloc'", 
			"'load'", "'store'", "'nop'", "'call'", "'return'", "':'", "'['", "']'", 
			"','", "'*'"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\'\u00fa\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3"+
		"\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b"+
		"\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3"+
		"\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17"+
		"\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\25\3\25"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\30"+
		"\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32"+
		"\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35"+
		"\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\6\"\u00d7\n\"\r\"\16\"\u00d8\3#\3"+
		"#\7#\u00dd\n#\f#\16#\u00e0\13#\3$\3$\3$\5$\u00e5\n$\3%\3%\3%\3%\3&\3&"+
		"\3&\3&\7&\u00ef\n&\f&\16&\u00f2\13&\3&\5&\u00f5\n&\3&\3&\3&\3&\2\2\'\3"+
		"\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37"+
		"\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37="+
		" ?!A\"C#E$G%I&K\'\3\2\5\6\2\62;C\\aac|\5\2\13\13\16\16\"\"\4\2\f\f\17"+
		"\17\2\u00fe\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2"+
		"\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2"+
		"\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2"+
		"\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2"+
		"\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\3M\3\2\2\2\5Q\3\2\2\2\7U\3\2\2\2\tY"+
		"\3\2\2\2\13]\3\2\2\2\ra\3\2\2\2\17e\3\2\2\2\21i\3\2\2\2\23m\3\2\2\2\25"+
		"s\3\2\2\2\27v\3\2\2\2\31y\3\2\2\2\33~\3\2\2\2\35\u0083\3\2\2\2\37\u0087"+
		"\3\2\2\2!\u008b\3\2\2\2#\u0090\3\2\2\2%\u0096\3\2\2\2\'\u009c\3\2\2\2"+
		")\u009f\3\2\2\2+\u00a3\3\2\2\2-\u00aa\3\2\2\2/\u00b0\3\2\2\2\61\u00b5"+
		"\3\2\2\2\63\u00bb\3\2\2\2\65\u00bf\3\2\2\2\67\u00c4\3\2\2\29\u00cb\3\2"+
		"\2\2;\u00cd\3\2\2\2=\u00cf\3\2\2\2?\u00d1\3\2\2\2A\u00d3\3\2\2\2C\u00d6"+
		"\3\2\2\2E\u00da\3\2\2\2G\u00e4\3\2\2\2I\u00e6\3\2\2\2K\u00ea\3\2\2\2M"+
		"N\7c\2\2NO\7f\2\2OP\7f\2\2P\4\3\2\2\2QR\7u\2\2RS\7w\2\2ST\7d\2\2T\6\3"+
		"\2\2\2UV\7o\2\2VW\7w\2\2WX\7n\2\2X\b\3\2\2\2YZ\7f\2\2Z[\7k\2\2[\\\7x\2"+
		"\2\\\n\3\2\2\2]^\7o\2\2^_\7q\2\2_`\7f\2\2`\f\3\2\2\2ab\7u\2\2bc\7j\2\2"+
		"cd\7n\2\2d\16\3\2\2\2ef\7u\2\2fg\7j\2\2gh\7t\2\2h\20\3\2\2\2ij\7e\2\2"+
		"jk\7o\2\2kl\7r\2\2l\22\3\2\2\2mn\7u\2\2no\7m\2\2op\7k\2\2pq\7r\2\2qr\7"+
		"|\2\2r\24\3\2\2\2st\7k\2\2tu\7r\2\2u\26\3\2\2\2vw\7h\2\2wx\7r\2\2x\30"+
		"\3\2\2\2yz\7l\2\2z{\7w\2\2{|\7o\2\2|}\7r\2\2}\32\3\2\2\2~\177\7r\2\2\177"+
		"\u0080\7w\2\2\u0080\u0081\7u\2\2\u0081\u0082\7j\2\2\u0082\34\3\2\2\2\u0083"+
		"\u0084\7f\2\2\u0084\u0085\7w\2\2\u0085\u0086\7r\2\2\u0086\36\3\2\2\2\u0087"+
		"\u0088\7r\2\2\u0088\u0089\7q\2\2\u0089\u008a\7r\2\2\u008a \3\2\2\2\u008b"+
		"\u008c\7u\2\2\u008c\u008d\7y\2\2\u008d\u008e\7c\2\2\u008e\u008f\7r\2\2"+
		"\u008f\"\3\2\2\2\u0090\u0091\7g\2\2\u0091\u0092\7p\2\2\u0092\u0093\7v"+
		"\2\2\u0093\u0094\7g\2\2\u0094\u0095\7t\2\2\u0095$\3\2\2\2\u0096\u0097"+
		"\7n\2\2\u0097\u0098\7g\2\2\u0098\u0099\7c\2\2\u0099\u009a\7x\2\2\u009a"+
		"\u009b\7g\2\2\u009b&\3\2\2\2\u009c\u009d\7k\2\2\u009d\u009e\7p\2\2\u009e"+
		"(\3\2\2\2\u009f\u00a0\7q\2\2\u00a0\u00a1\7w\2\2\u00a1\u00a2\7v\2\2\u00a2"+
		"*\3\2\2\2\u00a3\u00a4\7n\2\2\u00a4\u00a5\7c\2\2\u00a5\u00a6\7{\2\2\u00a6"+
		"\u00a7\7q\2\2\u00a7\u00a8\7w\2\2\u00a8\u00a9\7v\2\2\u00a9,\3\2\2\2\u00aa"+
		"\u00ab\7c\2\2\u00ab\u00ac\7n\2\2\u00ac\u00ad\7n\2\2\u00ad\u00ae\7q\2\2"+
		"\u00ae\u00af\7e\2\2\u00af.\3\2\2\2\u00b0\u00b1\7n\2\2\u00b1\u00b2\7q\2"+
		"\2\u00b2\u00b3\7c\2\2\u00b3\u00b4\7f\2\2\u00b4\60\3\2\2\2\u00b5\u00b6"+
		"\7u\2\2\u00b6\u00b7\7v\2\2\u00b7\u00b8\7q\2\2\u00b8\u00b9\7t\2\2\u00b9"+
		"\u00ba\7g\2\2\u00ba\62\3\2\2\2\u00bb\u00bc\7p\2\2\u00bc\u00bd\7q\2\2\u00bd"+
		"\u00be\7r\2\2\u00be\64\3\2\2\2\u00bf\u00c0\7e\2\2\u00c0\u00c1\7c\2\2\u00c1"+
		"\u00c2\7n\2\2\u00c2\u00c3\7n\2\2\u00c3\66\3\2\2\2\u00c4\u00c5\7t\2\2\u00c5"+
		"\u00c6\7g\2\2\u00c6\u00c7\7v\2\2\u00c7\u00c8\7w\2\2\u00c8\u00c9\7t\2\2"+
		"\u00c9\u00ca\7p\2\2\u00ca8\3\2\2\2\u00cb\u00cc\7<\2\2\u00cc:\3\2\2\2\u00cd"+
		"\u00ce\7]\2\2\u00ce<\3\2\2\2\u00cf\u00d0\7_\2\2\u00d0>\3\2\2\2\u00d1\u00d2"+
		"\7.\2\2\u00d2@\3\2\2\2\u00d3\u00d4\7,\2\2\u00d4B\3\2\2\2\u00d5\u00d7\4"+
		"\62;\2\u00d6\u00d5\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d8"+
		"\u00d9\3\2\2\2\u00d9D\3\2\2\2\u00da\u00de\4C\\\2\u00db\u00dd\t\2\2\2\u00dc"+
		"\u00db\3\2\2\2\u00dd\u00e0\3\2\2\2\u00de\u00dc\3\2\2\2\u00de\u00df\3\2"+
		"\2\2\u00dfF\3\2\2\2\u00e0\u00de\3\2\2\2\u00e1\u00e5\7\f\2\2\u00e2\u00e3"+
		"\7\17\2\2\u00e3\u00e5\7\f\2\2\u00e4\u00e1\3\2\2\2\u00e4\u00e2\3\2\2\2"+
		"\u00e5H\3\2\2\2\u00e6\u00e7\t\3\2\2\u00e7\u00e8\3\2\2\2\u00e8\u00e9\b"+
		"%\2\2\u00e9J\3\2\2\2\u00ea\u00eb\7\61\2\2\u00eb\u00ec\7\61\2\2\u00ec\u00f0"+
		"\3\2\2\2\u00ed\u00ef\n\4\2\2\u00ee\u00ed\3\2\2\2\u00ef\u00f2\3\2\2\2\u00f0"+
		"\u00ee\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1\u00f4\3\2\2\2\u00f2\u00f0\3\2"+
		"\2\2\u00f3\u00f5\7\17\2\2\u00f4\u00f3\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5"+
		"\u00f6\3\2\2\2\u00f6\u00f7\7\f\2\2\u00f7\u00f8\3\2\2\2\u00f8\u00f9\b&"+
		"\2\2\u00f9L\3\2\2\2\b\2\u00d8\u00de\u00e4\u00f0\u00f4\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}