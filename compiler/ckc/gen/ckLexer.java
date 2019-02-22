// Generated from C:/code/CopperKitten/compiler/ckc/grammar\ck.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ckLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, NATURAL=37, ID=38, TYPEID=39, 
		WHITESPACE=40, COMMENT=41;
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
			"T__33", "T__34", "T__35", "NATURAL", "ID", "TYPEID", "WHITESPACE", "COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'{'", "'}'", "'let'", "'='", "'for'", "'('", "';'", "')'", "'if'", 
			"'else'", "'return'", "'-'", "'!'", "'~'", "'*'", "'/'", "'%'", "'+'", 
			"'<<'", "'>>'", "'<'", "'<='", "'>'", "'>='", "'<>'", "'=='", "'!='", 
			"'&'", "'^'", "'|'", "'&&'", "'||'", "'?'", "':'", "'in'", "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, "NATURAL", "ID", "TYPEID", "WHITESPACE", "COMMENT"
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


	public ckLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "ck.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2+\u00db\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\3\2\3\2"+
		"\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3"+
		"\t\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24"+
		"\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\31\3\31"+
		"\3\31\3\32\3\32\3\32\3\33\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\36\3\36"+
		"\3\37\3\37\3 \3 \3 \3!\3!\3!\3\"\3\"\3#\3#\3$\3$\3$\3%\3%\3&\6&\u00b6"+
		"\n&\r&\16&\u00b7\3\'\3\'\7\'\u00bc\n\'\f\'\16\'\u00bf\13\'\3(\3(\7(\u00c3"+
		"\n(\f(\16(\u00c6\13(\3)\3)\3)\3)\3*\3*\3*\3*\7*\u00d0\n*\f*\16*\u00d3"+
		"\13*\3*\5*\u00d6\n*\3*\3*\3*\3*\2\2+\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21"+
		"\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30"+
		"/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+\3\2\6\4"+
		"\2aac|\6\2\62;C\\aac|\5\2\13\f\16\17\"\"\4\2\f\f\17\17\2\u00df\2\3\3\2"+
		"\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17"+
		"\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2"+
		"\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3"+
		"\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3"+
		"\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2"+
		"=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3"+
		"\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\3U\3\2\2"+
		"\2\5W\3\2\2\2\7Y\3\2\2\2\t]\3\2\2\2\13_\3\2\2\2\rc\3\2\2\2\17e\3\2\2\2"+
		"\21g\3\2\2\2\23i\3\2\2\2\25l\3\2\2\2\27q\3\2\2\2\31x\3\2\2\2\33z\3\2\2"+
		"\2\35|\3\2\2\2\37~\3\2\2\2!\u0080\3\2\2\2#\u0082\3\2\2\2%\u0084\3\2\2"+
		"\2\'\u0086\3\2\2\2)\u0089\3\2\2\2+\u008c\3\2\2\2-\u008e\3\2\2\2/\u0091"+
		"\3\2\2\2\61\u0093\3\2\2\2\63\u0096\3\2\2\2\65\u0099\3\2\2\2\67\u009c\3"+
		"\2\2\29\u009f\3\2\2\2;\u00a1\3\2\2\2=\u00a3\3\2\2\2?\u00a5\3\2\2\2A\u00a8"+
		"\3\2\2\2C\u00ab\3\2\2\2E\u00ad\3\2\2\2G\u00af\3\2\2\2I\u00b2\3\2\2\2K"+
		"\u00b5\3\2\2\2M\u00b9\3\2\2\2O\u00c0\3\2\2\2Q\u00c7\3\2\2\2S\u00cb\3\2"+
		"\2\2UV\7}\2\2V\4\3\2\2\2WX\7\177\2\2X\6\3\2\2\2YZ\7n\2\2Z[\7g\2\2[\\\7"+
		"v\2\2\\\b\3\2\2\2]^\7?\2\2^\n\3\2\2\2_`\7h\2\2`a\7q\2\2ab\7t\2\2b\f\3"+
		"\2\2\2cd\7*\2\2d\16\3\2\2\2ef\7=\2\2f\20\3\2\2\2gh\7+\2\2h\22\3\2\2\2"+
		"ij\7k\2\2jk\7h\2\2k\24\3\2\2\2lm\7g\2\2mn\7n\2\2no\7u\2\2op\7g\2\2p\26"+
		"\3\2\2\2qr\7t\2\2rs\7g\2\2st\7v\2\2tu\7w\2\2uv\7t\2\2vw\7p\2\2w\30\3\2"+
		"\2\2xy\7/\2\2y\32\3\2\2\2z{\7#\2\2{\34\3\2\2\2|}\7\u0080\2\2}\36\3\2\2"+
		"\2~\177\7,\2\2\177 \3\2\2\2\u0080\u0081\7\61\2\2\u0081\"\3\2\2\2\u0082"+
		"\u0083\7\'\2\2\u0083$\3\2\2\2\u0084\u0085\7-\2\2\u0085&\3\2\2\2\u0086"+
		"\u0087\7>\2\2\u0087\u0088\7>\2\2\u0088(\3\2\2\2\u0089\u008a\7@\2\2\u008a"+
		"\u008b\7@\2\2\u008b*\3\2\2\2\u008c\u008d\7>\2\2\u008d,\3\2\2\2\u008e\u008f"+
		"\7>\2\2\u008f\u0090\7?\2\2\u0090.\3\2\2\2\u0091\u0092\7@\2\2\u0092\60"+
		"\3\2\2\2\u0093\u0094\7@\2\2\u0094\u0095\7?\2\2\u0095\62\3\2\2\2\u0096"+
		"\u0097\7>\2\2\u0097\u0098\7@\2\2\u0098\64\3\2\2\2\u0099\u009a\7?\2\2\u009a"+
		"\u009b\7?\2\2\u009b\66\3\2\2\2\u009c\u009d\7#\2\2\u009d\u009e\7?\2\2\u009e"+
		"8\3\2\2\2\u009f\u00a0\7(\2\2\u00a0:\3\2\2\2\u00a1\u00a2\7`\2\2\u00a2<"+
		"\3\2\2\2\u00a3\u00a4\7~\2\2\u00a4>\3\2\2\2\u00a5\u00a6\7(\2\2\u00a6\u00a7"+
		"\7(\2\2\u00a7@\3\2\2\2\u00a8\u00a9\7~\2\2\u00a9\u00aa\7~\2\2\u00aaB\3"+
		"\2\2\2\u00ab\u00ac\7A\2\2\u00acD\3\2\2\2\u00ad\u00ae\7<\2\2\u00aeF\3\2"+
		"\2\2\u00af\u00b0\7k\2\2\u00b0\u00b1\7p\2\2\u00b1H\3\2\2\2\u00b2\u00b3"+
		"\7.\2\2\u00b3J\3\2\2\2\u00b4\u00b6\4\62;\2\u00b5\u00b4\3\2\2\2\u00b6\u00b7"+
		"\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8L\3\2\2\2\u00b9"+
		"\u00bd\t\2\2\2\u00ba\u00bc\t\3\2\2\u00bb\u00ba\3\2\2\2\u00bc\u00bf\3\2"+
		"\2\2\u00bd\u00bb\3\2\2\2\u00bd\u00be\3\2\2\2\u00beN\3\2\2\2\u00bf\u00bd"+
		"\3\2\2\2\u00c0\u00c4\4C\\\2\u00c1\u00c3\t\3\2\2\u00c2\u00c1\3\2\2\2\u00c3"+
		"\u00c6\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5P\3\2\2\2"+
		"\u00c6\u00c4\3\2\2\2\u00c7\u00c8\t\4\2\2\u00c8\u00c9\3\2\2\2\u00c9\u00ca"+
		"\b)\2\2\u00caR\3\2\2\2\u00cb\u00cc\7\61\2\2\u00cc\u00cd\7\61\2\2\u00cd"+
		"\u00d1\3\2\2\2\u00ce\u00d0\n\5\2\2\u00cf\u00ce\3\2\2\2\u00d0\u00d3\3\2"+
		"\2\2\u00d1\u00cf\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\u00d5\3\2\2\2\u00d3"+
		"\u00d1\3\2\2\2\u00d4\u00d6\7\17\2\2\u00d5\u00d4\3\2\2\2\u00d5\u00d6\3"+
		"\2\2\2\u00d6\u00d7\3\2\2\2\u00d7\u00d8\7\f\2\2\u00d8\u00d9\3\2\2\2\u00d9"+
		"\u00da\b*\2\2\u00daT\3\2\2\2\b\2\u00b7\u00bd\u00c4\u00d1\u00d5\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}