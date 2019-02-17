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
		T__31=32, T__32=33, T__33=34, NATURAL=35, LABEL=36, NL=37, WHITESPACE=38;
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
			"T__25", "T__26", "T__27", "T__28", "T__29", "T__30", "T__31", "T__32", 
			"T__33", "NATURAL", "LABEL", "NL", "WHITESPACE"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "':'", "'layout'", "'alloc'", "'push'", "'jump'", "'jumpz'", "'jumpnz'", 
			"'lload'", "'lstore'", "'rload'", "'rstore'", "'add'", "'sub'", "'mul'", 
			"'div'", "'mod'", "'shl'", "'shr'", "'cmp'", "'call'", "'ret'", "'dup'", 
			"'pop'", "'swap'", "'enter'", "'leave'", "'in'", "'out'", "'nop'", "'['", 
			"']'", "','", "'*'", "'-'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, "NATURAL", 
			"LABEL", "NL", "WHITESPACE"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2(\u00fb\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\3\2\3\2\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6"+
		"\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3"+
		"\17\3\17\3\17\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3"+
		"\22\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3"+
		"\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\31\3\31\3"+
		"\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3"+
		"\33\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\37\3\37\3"+
		" \3 \3!\3!\3\"\3\"\3#\3#\3$\6$\u00e8\n$\r$\16$\u00e9\3%\3%\7%\u00ee\n"+
		"%\f%\16%\u00f1\13%\3&\3&\3&\5&\u00f6\n&\3\'\3\'\3\'\3\'\2\2(\3\3\5\4\7"+
		"\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22"+
		"#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C"+
		"#E$G%I&K\'M(\3\2\4\6\2\62;C\\aac|\5\2\13\13\16\16\"\"\2\u00fd\2\3\3\2"+
		"\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17"+
		"\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2"+
		"\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3"+
		"\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3"+
		"\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2"+
		"=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3"+
		"\2\2\2\2K\3\2\2\2\2M\3\2\2\2\3O\3\2\2\2\5Q\3\2\2\2\7X\3\2\2\2\t^\3\2\2"+
		"\2\13c\3\2\2\2\rh\3\2\2\2\17n\3\2\2\2\21u\3\2\2\2\23{\3\2\2\2\25\u0082"+
		"\3\2\2\2\27\u0088\3\2\2\2\31\u008f\3\2\2\2\33\u0093\3\2\2\2\35\u0097\3"+
		"\2\2\2\37\u009b\3\2\2\2!\u009f\3\2\2\2#\u00a3\3\2\2\2%\u00a7\3\2\2\2\'"+
		"\u00ab\3\2\2\2)\u00af\3\2\2\2+\u00b4\3\2\2\2-\u00b8\3\2\2\2/\u00bc\3\2"+
		"\2\2\61\u00c0\3\2\2\2\63\u00c5\3\2\2\2\65\u00cb\3\2\2\2\67\u00d1\3\2\2"+
		"\29\u00d4\3\2\2\2;\u00d8\3\2\2\2=\u00dc\3\2\2\2?\u00de\3\2\2\2A\u00e0"+
		"\3\2\2\2C\u00e2\3\2\2\2E\u00e4\3\2\2\2G\u00e7\3\2\2\2I\u00eb\3\2\2\2K"+
		"\u00f5\3\2\2\2M\u00f7\3\2\2\2OP\7<\2\2P\4\3\2\2\2QR\7n\2\2RS\7c\2\2ST"+
		"\7{\2\2TU\7q\2\2UV\7w\2\2VW\7v\2\2W\6\3\2\2\2XY\7c\2\2YZ\7n\2\2Z[\7n\2"+
		"\2[\\\7q\2\2\\]\7e\2\2]\b\3\2\2\2^_\7r\2\2_`\7w\2\2`a\7u\2\2ab\7j\2\2"+
		"b\n\3\2\2\2cd\7l\2\2de\7w\2\2ef\7o\2\2fg\7r\2\2g\f\3\2\2\2hi\7l\2\2ij"+
		"\7w\2\2jk\7o\2\2kl\7r\2\2lm\7|\2\2m\16\3\2\2\2no\7l\2\2op\7w\2\2pq\7o"+
		"\2\2qr\7r\2\2rs\7p\2\2st\7|\2\2t\20\3\2\2\2uv\7n\2\2vw\7n\2\2wx\7q\2\2"+
		"xy\7c\2\2yz\7f\2\2z\22\3\2\2\2{|\7n\2\2|}\7u\2\2}~\7v\2\2~\177\7q\2\2"+
		"\177\u0080\7t\2\2\u0080\u0081\7g\2\2\u0081\24\3\2\2\2\u0082\u0083\7t\2"+
		"\2\u0083\u0084\7n\2\2\u0084\u0085\7q\2\2\u0085\u0086\7c\2\2\u0086\u0087"+
		"\7f\2\2\u0087\26\3\2\2\2\u0088\u0089\7t\2\2\u0089\u008a\7u\2\2\u008a\u008b"+
		"\7v\2\2\u008b\u008c\7q\2\2\u008c\u008d\7t\2\2\u008d\u008e\7g\2\2\u008e"+
		"\30\3\2\2\2\u008f\u0090\7c\2\2\u0090\u0091\7f\2\2\u0091\u0092\7f\2\2\u0092"+
		"\32\3\2\2\2\u0093\u0094\7u\2\2\u0094\u0095\7w\2\2\u0095\u0096\7d\2\2\u0096"+
		"\34\3\2\2\2\u0097\u0098\7o\2\2\u0098\u0099\7w\2\2\u0099\u009a\7n\2\2\u009a"+
		"\36\3\2\2\2\u009b\u009c\7f\2\2\u009c\u009d\7k\2\2\u009d\u009e\7x\2\2\u009e"+
		" \3\2\2\2\u009f\u00a0\7o\2\2\u00a0\u00a1\7q\2\2\u00a1\u00a2\7f\2\2\u00a2"+
		"\"\3\2\2\2\u00a3\u00a4\7u\2\2\u00a4\u00a5\7j\2\2\u00a5\u00a6\7n\2\2\u00a6"+
		"$\3\2\2\2\u00a7\u00a8\7u\2\2\u00a8\u00a9\7j\2\2\u00a9\u00aa\7t\2\2\u00aa"+
		"&\3\2\2\2\u00ab\u00ac\7e\2\2\u00ac\u00ad\7o\2\2\u00ad\u00ae\7r\2\2\u00ae"+
		"(\3\2\2\2\u00af\u00b0\7e\2\2\u00b0\u00b1\7c\2\2\u00b1\u00b2\7n\2\2\u00b2"+
		"\u00b3\7n\2\2\u00b3*\3\2\2\2\u00b4\u00b5\7t\2\2\u00b5\u00b6\7g\2\2\u00b6"+
		"\u00b7\7v\2\2\u00b7,\3\2\2\2\u00b8\u00b9\7f\2\2\u00b9\u00ba\7w\2\2\u00ba"+
		"\u00bb\7r\2\2\u00bb.\3\2\2\2\u00bc\u00bd\7r\2\2\u00bd\u00be\7q\2\2\u00be"+
		"\u00bf\7r\2\2\u00bf\60\3\2\2\2\u00c0\u00c1\7u\2\2\u00c1\u00c2\7y\2\2\u00c2"+
		"\u00c3\7c\2\2\u00c3\u00c4\7r\2\2\u00c4\62\3\2\2\2\u00c5\u00c6\7g\2\2\u00c6"+
		"\u00c7\7p\2\2\u00c7\u00c8\7v\2\2\u00c8\u00c9\7g\2\2\u00c9\u00ca\7t\2\2"+
		"\u00ca\64\3\2\2\2\u00cb\u00cc\7n\2\2\u00cc\u00cd\7g\2\2\u00cd\u00ce\7"+
		"c\2\2\u00ce\u00cf\7x\2\2\u00cf\u00d0\7g\2\2\u00d0\66\3\2\2\2\u00d1\u00d2"+
		"\7k\2\2\u00d2\u00d3\7p\2\2\u00d38\3\2\2\2\u00d4\u00d5\7q\2\2\u00d5\u00d6"+
		"\7w\2\2\u00d6\u00d7\7v\2\2\u00d7:\3\2\2\2\u00d8\u00d9\7p\2\2\u00d9\u00da"+
		"\7q\2\2\u00da\u00db\7r\2\2\u00db<\3\2\2\2\u00dc\u00dd\7]\2\2\u00dd>\3"+
		"\2\2\2\u00de\u00df\7_\2\2\u00df@\3\2\2\2\u00e0\u00e1\7.\2\2\u00e1B\3\2"+
		"\2\2\u00e2\u00e3\7,\2\2\u00e3D\3\2\2\2\u00e4\u00e5\7/\2\2\u00e5F\3\2\2"+
		"\2\u00e6\u00e8\4\62;\2\u00e7\u00e6\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9\u00e7"+
		"\3\2\2\2\u00e9\u00ea\3\2\2\2\u00eaH\3\2\2\2\u00eb\u00ef\4C\\\2\u00ec\u00ee"+
		"\t\2\2\2\u00ed\u00ec\3\2\2\2\u00ee\u00f1\3\2\2\2\u00ef\u00ed\3\2\2\2\u00ef"+
		"\u00f0\3\2\2\2\u00f0J\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f2\u00f6\7\f\2\2"+
		"\u00f3\u00f4\7\17\2\2\u00f4\u00f6\7\f\2\2\u00f5\u00f2\3\2\2\2\u00f5\u00f3"+
		"\3\2\2\2\u00f6L\3\2\2\2\u00f7\u00f8\t\3\2\2\u00f8\u00f9\3\2\2\2\u00f9"+
		"\u00fa\b\'\2\2\u00faN\3\2\2\2\6\2\u00e9\u00ef\u00f5\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}