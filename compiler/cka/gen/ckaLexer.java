// Generated from C:/code/CopperKitten/compiler/cka/grammar\cka.g4 by ANTLR 4.7.2
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
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, NATURAL=40, LABEL=41, WHITESPACE=42, COMMENT=43;
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
			"T__33", "T__34", "T__35", "T__36", "T__37", "T__38", "NATURAL", "LABEL", 
			"WHITESPACE", "COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "':'", "'layout'", "'alloc'", "'push'", "'jump'", "'jumpz'", "'jumpnz'", 
			"'aload'", "'astore'", "'lload'", "'lstore'", "'rload'", "'rstore'", 
			"'cload'", "'cstore'", "'add'", "'sub'", "'mul'", "'div'", "'mod'", "'shl'", 
			"'shr'", "'cmp'", "'call'", "'ret'", "'dup'", "'pop'", "'swap'", "'enter'", 
			"'leave'", "'in'", "'out'", "'nop'", "'halt'", "'['", "']'", "','", "'*'", 
			"'-'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, "NATURAL", "LABEL", "WHITESPACE", "COMMENT"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2-\u0132\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3"+
		"\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\22\3\22"+
		"\3\22\3\22\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25"+
		"\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\31\3\31"+
		"\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\34\3\34\3\34"+
		"\3\34\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37"+
		"\3\37\3\37\3\37\3\37\3 \3 \3 \3!\3!\3!\3!\3\"\3\"\3\"\3\"\3#\3#\3#\3#"+
		"\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\6)\u0111\n)\r)\16)\u0112\3*\3*"+
		"\7*\u0117\n*\f*\16*\u011a\13*\3+\3+\3+\5+\u011f\n+\3+\3+\3,\3,\3,\3,\7"+
		",\u0127\n,\f,\16,\u012a\13,\3,\5,\u012d\n,\3,\3,\3,\3,\2\2-\3\3\5\4\7"+
		"\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22"+
		"#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C"+
		"#E$G%I&K\'M(O)Q*S+U,W-\3\2\5\6\2\62;C\\aac|\5\2\13\f\16\16\"\"\4\2\f\f"+
		"\17\17\2\u0136\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3"+
		"\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2"+
		"\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3"+
		"\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2"+
		"\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\2"+
		"9\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3"+
		"\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2"+
		"\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\3Y\3\2\2\2\5[\3\2\2\2\7b\3\2\2\2\t"+
		"h\3\2\2\2\13m\3\2\2\2\rr\3\2\2\2\17x\3\2\2\2\21\177\3\2\2\2\23\u0085\3"+
		"\2\2\2\25\u008c\3\2\2\2\27\u0092\3\2\2\2\31\u0099\3\2\2\2\33\u009f\3\2"+
		"\2\2\35\u00a6\3\2\2\2\37\u00ac\3\2\2\2!\u00b3\3\2\2\2#\u00b7\3\2\2\2%"+
		"\u00bb\3\2\2\2\'\u00bf\3\2\2\2)\u00c3\3\2\2\2+\u00c7\3\2\2\2-\u00cb\3"+
		"\2\2\2/\u00cf\3\2\2\2\61\u00d3\3\2\2\2\63\u00d8\3\2\2\2\65\u00dc\3\2\2"+
		"\2\67\u00e0\3\2\2\29\u00e4\3\2\2\2;\u00e9\3\2\2\2=\u00ef\3\2\2\2?\u00f5"+
		"\3\2\2\2A\u00f8\3\2\2\2C\u00fc\3\2\2\2E\u0100\3\2\2\2G\u0105\3\2\2\2I"+
		"\u0107\3\2\2\2K\u0109\3\2\2\2M\u010b\3\2\2\2O\u010d\3\2\2\2Q\u0110\3\2"+
		"\2\2S\u0114\3\2\2\2U\u011e\3\2\2\2W\u0122\3\2\2\2YZ\7<\2\2Z\4\3\2\2\2"+
		"[\\\7n\2\2\\]\7c\2\2]^\7{\2\2^_\7q\2\2_`\7w\2\2`a\7v\2\2a\6\3\2\2\2bc"+
		"\7c\2\2cd\7n\2\2de\7n\2\2ef\7q\2\2fg\7e\2\2g\b\3\2\2\2hi\7r\2\2ij\7w\2"+
		"\2jk\7u\2\2kl\7j\2\2l\n\3\2\2\2mn\7l\2\2no\7w\2\2op\7o\2\2pq\7r\2\2q\f"+
		"\3\2\2\2rs\7l\2\2st\7w\2\2tu\7o\2\2uv\7r\2\2vw\7|\2\2w\16\3\2\2\2xy\7"+
		"l\2\2yz\7w\2\2z{\7o\2\2{|\7r\2\2|}\7p\2\2}~\7|\2\2~\20\3\2\2\2\177\u0080"+
		"\7c\2\2\u0080\u0081\7n\2\2\u0081\u0082\7q\2\2\u0082\u0083\7c\2\2\u0083"+
		"\u0084\7f\2\2\u0084\22\3\2\2\2\u0085\u0086\7c\2\2\u0086\u0087\7u\2\2\u0087"+
		"\u0088\7v\2\2\u0088\u0089\7q\2\2\u0089\u008a\7t\2\2\u008a\u008b\7g\2\2"+
		"\u008b\24\3\2\2\2\u008c\u008d\7n\2\2\u008d\u008e\7n\2\2\u008e\u008f\7"+
		"q\2\2\u008f\u0090\7c\2\2\u0090\u0091\7f\2\2\u0091\26\3\2\2\2\u0092\u0093"+
		"\7n\2\2\u0093\u0094\7u\2\2\u0094\u0095\7v\2\2\u0095\u0096\7q\2\2\u0096"+
		"\u0097\7t\2\2\u0097\u0098\7g\2\2\u0098\30\3\2\2\2\u0099\u009a\7t\2\2\u009a"+
		"\u009b\7n\2\2\u009b\u009c\7q\2\2\u009c\u009d\7c\2\2\u009d\u009e\7f\2\2"+
		"\u009e\32\3\2\2\2\u009f\u00a0\7t\2\2\u00a0\u00a1\7u\2\2\u00a1\u00a2\7"+
		"v\2\2\u00a2\u00a3\7q\2\2\u00a3\u00a4\7t\2\2\u00a4\u00a5\7g\2\2\u00a5\34"+
		"\3\2\2\2\u00a6\u00a7\7e\2\2\u00a7\u00a8\7n\2\2\u00a8\u00a9\7q\2\2\u00a9"+
		"\u00aa\7c\2\2\u00aa\u00ab\7f\2\2\u00ab\36\3\2\2\2\u00ac\u00ad\7e\2\2\u00ad"+
		"\u00ae\7u\2\2\u00ae\u00af\7v\2\2\u00af\u00b0\7q\2\2\u00b0\u00b1\7t\2\2"+
		"\u00b1\u00b2\7g\2\2\u00b2 \3\2\2\2\u00b3\u00b4\7c\2\2\u00b4\u00b5\7f\2"+
		"\2\u00b5\u00b6\7f\2\2\u00b6\"\3\2\2\2\u00b7\u00b8\7u\2\2\u00b8\u00b9\7"+
		"w\2\2\u00b9\u00ba\7d\2\2\u00ba$\3\2\2\2\u00bb\u00bc\7o\2\2\u00bc\u00bd"+
		"\7w\2\2\u00bd\u00be\7n\2\2\u00be&\3\2\2\2\u00bf\u00c0\7f\2\2\u00c0\u00c1"+
		"\7k\2\2\u00c1\u00c2\7x\2\2\u00c2(\3\2\2\2\u00c3\u00c4\7o\2\2\u00c4\u00c5"+
		"\7q\2\2\u00c5\u00c6\7f\2\2\u00c6*\3\2\2\2\u00c7\u00c8\7u\2\2\u00c8\u00c9"+
		"\7j\2\2\u00c9\u00ca\7n\2\2\u00ca,\3\2\2\2\u00cb\u00cc\7u\2\2\u00cc\u00cd"+
		"\7j\2\2\u00cd\u00ce\7t\2\2\u00ce.\3\2\2\2\u00cf\u00d0\7e\2\2\u00d0\u00d1"+
		"\7o\2\2\u00d1\u00d2\7r\2\2\u00d2\60\3\2\2\2\u00d3\u00d4\7e\2\2\u00d4\u00d5"+
		"\7c\2\2\u00d5\u00d6\7n\2\2\u00d6\u00d7\7n\2\2\u00d7\62\3\2\2\2\u00d8\u00d9"+
		"\7t\2\2\u00d9\u00da\7g\2\2\u00da\u00db\7v\2\2\u00db\64\3\2\2\2\u00dc\u00dd"+
		"\7f\2\2\u00dd\u00de\7w\2\2\u00de\u00df\7r\2\2\u00df\66\3\2\2\2\u00e0\u00e1"+
		"\7r\2\2\u00e1\u00e2\7q\2\2\u00e2\u00e3\7r\2\2\u00e38\3\2\2\2\u00e4\u00e5"+
		"\7u\2\2\u00e5\u00e6\7y\2\2\u00e6\u00e7\7c\2\2\u00e7\u00e8\7r\2\2\u00e8"+
		":\3\2\2\2\u00e9\u00ea\7g\2\2\u00ea\u00eb\7p\2\2\u00eb\u00ec\7v\2\2\u00ec"+
		"\u00ed\7g\2\2\u00ed\u00ee\7t\2\2\u00ee<\3\2\2\2\u00ef\u00f0\7n\2\2\u00f0"+
		"\u00f1\7g\2\2\u00f1\u00f2\7c\2\2\u00f2\u00f3\7x\2\2\u00f3\u00f4\7g\2\2"+
		"\u00f4>\3\2\2\2\u00f5\u00f6\7k\2\2\u00f6\u00f7\7p\2\2\u00f7@\3\2\2\2\u00f8"+
		"\u00f9\7q\2\2\u00f9\u00fa\7w\2\2\u00fa\u00fb\7v\2\2\u00fbB\3\2\2\2\u00fc"+
		"\u00fd\7p\2\2\u00fd\u00fe\7q\2\2\u00fe\u00ff\7r\2\2\u00ffD\3\2\2\2\u0100"+
		"\u0101\7j\2\2\u0101\u0102\7c\2\2\u0102\u0103\7n\2\2\u0103\u0104\7v\2\2"+
		"\u0104F\3\2\2\2\u0105\u0106\7]\2\2\u0106H\3\2\2\2\u0107\u0108\7_\2\2\u0108"+
		"J\3\2\2\2\u0109\u010a\7.\2\2\u010aL\3\2\2\2\u010b\u010c\7,\2\2\u010cN"+
		"\3\2\2\2\u010d\u010e\7/\2\2\u010eP\3\2\2\2\u010f\u0111\4\62;\2\u0110\u010f"+
		"\3\2\2\2\u0111\u0112\3\2\2\2\u0112\u0110\3\2\2\2\u0112\u0113\3\2\2\2\u0113"+
		"R\3\2\2\2\u0114\u0118\4C\\\2\u0115\u0117\t\2\2\2\u0116\u0115\3\2\2\2\u0117"+
		"\u011a\3\2\2\2\u0118\u0116\3\2\2\2\u0118\u0119\3\2\2\2\u0119T\3\2\2\2"+
		"\u011a\u0118\3\2\2\2\u011b\u011f\t\3\2\2\u011c\u011d\7\17\2\2\u011d\u011f"+
		"\7\f\2\2\u011e\u011b\3\2\2\2\u011e\u011c\3\2\2\2\u011f\u0120\3\2\2\2\u0120"+
		"\u0121\b+\2\2\u0121V\3\2\2\2\u0122\u0123\7\61\2\2\u0123\u0124\7\61\2\2"+
		"\u0124\u0128\3\2\2\2\u0125\u0127\n\4\2\2\u0126\u0125\3\2\2\2\u0127\u012a"+
		"\3\2\2\2\u0128\u0126\3\2\2\2\u0128\u0129\3\2\2\2\u0129\u012c\3\2\2\2\u012a"+
		"\u0128\3\2\2\2\u012b\u012d\7\17\2\2\u012c\u012b\3\2\2\2\u012c\u012d\3"+
		"\2\2\2\u012d\u012e\3\2\2\2\u012e\u012f\7\f\2\2\u012f\u0130\3\2\2\2\u0130"+
		"\u0131\b,\2\2\u0131X\3\2\2\2\b\2\u0112\u0118\u011e\u0128\u012c\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}