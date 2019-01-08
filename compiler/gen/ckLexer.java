// Generated from C:/code/CopperKitten/compiler/grammar\ck.g4 by ANTLR 4.7.2
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
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, NATURAL=43, ID=44, TYPEID=45, 
		WHITESPACE=46, COMMENT=47;
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
			"T__33", "T__34", "T__35", "T__36", "T__37", "T__38", "T__39", "T__40", 
			"T__41", "NATURAL", "ID", "TYPEID", "WHITESPACE", "COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'{'", "'}'", "'let'", "'='", "'do'", "'while'", "'('", "')'", 
			"'for'", "';'", "'if'", "'else'", "'match'", "'with'", "':'", "'default'", 
			"'return'", "'-'", "'!'", "'~'", "'*'", "'/'", "'%'", "'+'", "'<<'", 
			"'>>'", "'<'", "'<='", "'>'", "'>='", "'<>'", "'=='", "'!='", "'&'", 
			"'^'", "'|'", "'&&'", "'||'", "'?'", "'\\'", "'in'", "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, "NATURAL", "ID", "TYPEID", 
			"WHITESPACE", "COMMENT"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\61\u0105\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3"+
		"\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n"+
		"\3\n\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\24\3\24\3\25"+
		"\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\32\3\33\3\33"+
		"\3\33\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\37\3\37\3\37\3 \3 \3 \3!\3"+
		"!\3!\3\"\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3&\3\'\3\'\3\'\3(\3(\3)\3)\3"+
		"*\3*\3*\3+\3+\3,\6,\u00e0\n,\r,\16,\u00e1\3-\3-\7-\u00e6\n-\f-\16-\u00e9"+
		"\13-\3.\3.\7.\u00ed\n.\f.\16.\u00f0\13.\3/\3/\3/\3/\3\60\3\60\3\60\3\60"+
		"\7\60\u00fa\n\60\f\60\16\60\u00fd\13\60\3\60\5\60\u0100\n\60\3\60\3\60"+
		"\3\60\3\60\2\2\61\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31"+
		"\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65"+
		"\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61\3\2\6\4\2"+
		"aac|\6\2\62;C\\aac|\5\2\13\f\16\17\"\"\4\2\f\f\17\17\2\u0109\2\3\3\2\2"+
		"\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3"+
		"\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2"+
		"\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2"+
		"\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2"+
		"\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3"+
		"\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2"+
		"\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2"+
		"W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\3a\3\2\2\2\5c\3"+
		"\2\2\2\7e\3\2\2\2\ti\3\2\2\2\13k\3\2\2\2\rn\3\2\2\2\17t\3\2\2\2\21v\3"+
		"\2\2\2\23x\3\2\2\2\25|\3\2\2\2\27~\3\2\2\2\31\u0081\3\2\2\2\33\u0086\3"+
		"\2\2\2\35\u008c\3\2\2\2\37\u0091\3\2\2\2!\u0093\3\2\2\2#\u009b\3\2\2\2"+
		"%\u00a2\3\2\2\2\'\u00a4\3\2\2\2)\u00a6\3\2\2\2+\u00a8\3\2\2\2-\u00aa\3"+
		"\2\2\2/\u00ac\3\2\2\2\61\u00ae\3\2\2\2\63\u00b0\3\2\2\2\65\u00b3\3\2\2"+
		"\2\67\u00b6\3\2\2\29\u00b8\3\2\2\2;\u00bb\3\2\2\2=\u00bd\3\2\2\2?\u00c0"+
		"\3\2\2\2A\u00c3\3\2\2\2C\u00c6\3\2\2\2E\u00c9\3\2\2\2G\u00cb\3\2\2\2I"+
		"\u00cd\3\2\2\2K\u00cf\3\2\2\2M\u00d2\3\2\2\2O\u00d5\3\2\2\2Q\u00d7\3\2"+
		"\2\2S\u00d9\3\2\2\2U\u00dc\3\2\2\2W\u00df\3\2\2\2Y\u00e3\3\2\2\2[\u00ea"+
		"\3\2\2\2]\u00f1\3\2\2\2_\u00f5\3\2\2\2ab\7}\2\2b\4\3\2\2\2cd\7\177\2\2"+
		"d\6\3\2\2\2ef\7n\2\2fg\7g\2\2gh\7v\2\2h\b\3\2\2\2ij\7?\2\2j\n\3\2\2\2"+
		"kl\7f\2\2lm\7q\2\2m\f\3\2\2\2no\7y\2\2op\7j\2\2pq\7k\2\2qr\7n\2\2rs\7"+
		"g\2\2s\16\3\2\2\2tu\7*\2\2u\20\3\2\2\2vw\7+\2\2w\22\3\2\2\2xy\7h\2\2y"+
		"z\7q\2\2z{\7t\2\2{\24\3\2\2\2|}\7=\2\2}\26\3\2\2\2~\177\7k\2\2\177\u0080"+
		"\7h\2\2\u0080\30\3\2\2\2\u0081\u0082\7g\2\2\u0082\u0083\7n\2\2\u0083\u0084"+
		"\7u\2\2\u0084\u0085\7g\2\2\u0085\32\3\2\2\2\u0086\u0087\7o\2\2\u0087\u0088"+
		"\7c\2\2\u0088\u0089\7v\2\2\u0089\u008a\7e\2\2\u008a\u008b\7j\2\2\u008b"+
		"\34\3\2\2\2\u008c\u008d\7y\2\2\u008d\u008e\7k\2\2\u008e\u008f\7v\2\2\u008f"+
		"\u0090\7j\2\2\u0090\36\3\2\2\2\u0091\u0092\7<\2\2\u0092 \3\2\2\2\u0093"+
		"\u0094\7f\2\2\u0094\u0095\7g\2\2\u0095\u0096\7h\2\2\u0096\u0097\7c\2\2"+
		"\u0097\u0098\7w\2\2\u0098\u0099\7n\2\2\u0099\u009a\7v\2\2\u009a\"\3\2"+
		"\2\2\u009b\u009c\7t\2\2\u009c\u009d\7g\2\2\u009d\u009e\7v\2\2\u009e\u009f"+
		"\7w\2\2\u009f\u00a0\7t\2\2\u00a0\u00a1\7p\2\2\u00a1$\3\2\2\2\u00a2\u00a3"+
		"\7/\2\2\u00a3&\3\2\2\2\u00a4\u00a5\7#\2\2\u00a5(\3\2\2\2\u00a6\u00a7\7"+
		"\u0080\2\2\u00a7*\3\2\2\2\u00a8\u00a9\7,\2\2\u00a9,\3\2\2\2\u00aa\u00ab"+
		"\7\61\2\2\u00ab.\3\2\2\2\u00ac\u00ad\7\'\2\2\u00ad\60\3\2\2\2\u00ae\u00af"+
		"\7-\2\2\u00af\62\3\2\2\2\u00b0\u00b1\7>\2\2\u00b1\u00b2\7>\2\2\u00b2\64"+
		"\3\2\2\2\u00b3\u00b4\7@\2\2\u00b4\u00b5\7@\2\2\u00b5\66\3\2\2\2\u00b6"+
		"\u00b7\7>\2\2\u00b78\3\2\2\2\u00b8\u00b9\7>\2\2\u00b9\u00ba\7?\2\2\u00ba"+
		":\3\2\2\2\u00bb\u00bc\7@\2\2\u00bc<\3\2\2\2\u00bd\u00be\7@\2\2\u00be\u00bf"+
		"\7?\2\2\u00bf>\3\2\2\2\u00c0\u00c1\7>\2\2\u00c1\u00c2\7@\2\2\u00c2@\3"+
		"\2\2\2\u00c3\u00c4\7?\2\2\u00c4\u00c5\7?\2\2\u00c5B\3\2\2\2\u00c6\u00c7"+
		"\7#\2\2\u00c7\u00c8\7?\2\2\u00c8D\3\2\2\2\u00c9\u00ca\7(\2\2\u00caF\3"+
		"\2\2\2\u00cb\u00cc\7`\2\2\u00ccH\3\2\2\2\u00cd\u00ce\7~\2\2\u00ceJ\3\2"+
		"\2\2\u00cf\u00d0\7(\2\2\u00d0\u00d1\7(\2\2\u00d1L\3\2\2\2\u00d2\u00d3"+
		"\7~\2\2\u00d3\u00d4\7~\2\2\u00d4N\3\2\2\2\u00d5\u00d6\7A\2\2\u00d6P\3"+
		"\2\2\2\u00d7\u00d8\7^\2\2\u00d8R\3\2\2\2\u00d9\u00da\7k\2\2\u00da\u00db"+
		"\7p\2\2\u00dbT\3\2\2\2\u00dc\u00dd\7.\2\2\u00ddV\3\2\2\2\u00de\u00e0\4"+
		"\62;\2\u00df\u00de\3\2\2\2\u00e0\u00e1\3\2\2\2\u00e1\u00df\3\2\2\2\u00e1"+
		"\u00e2\3\2\2\2\u00e2X\3\2\2\2\u00e3\u00e7\t\2\2\2\u00e4\u00e6\t\3\2\2"+
		"\u00e5\u00e4\3\2\2\2\u00e6\u00e9\3\2\2\2\u00e7\u00e5\3\2\2\2\u00e7\u00e8"+
		"\3\2\2\2\u00e8Z\3\2\2\2\u00e9\u00e7\3\2\2\2\u00ea\u00ee\4C\\\2\u00eb\u00ed"+
		"\t\3\2\2\u00ec\u00eb\3\2\2\2\u00ed\u00f0\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ee"+
		"\u00ef\3\2\2\2\u00ef\\\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f1\u00f2\t\4\2\2"+
		"\u00f2\u00f3\3\2\2\2\u00f3\u00f4\b/\2\2\u00f4^\3\2\2\2\u00f5\u00f6\7\61"+
		"\2\2\u00f6\u00f7\7\61\2\2\u00f7\u00fb\3\2\2\2\u00f8\u00fa\n\5\2\2\u00f9"+
		"\u00f8\3\2\2\2\u00fa\u00fd\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fb\u00fc\3\2"+
		"\2\2\u00fc\u00ff\3\2\2\2\u00fd\u00fb\3\2\2\2\u00fe\u0100\7\17\2\2\u00ff"+
		"\u00fe\3\2\2\2\u00ff\u0100\3\2\2\2\u0100\u0101\3\2\2\2\u0101\u0102\7\f"+
		"\2\2\u0102\u0103\3\2\2\2\u0103\u0104\b\60\2\2\u0104`\3\2\2\2\b\2\u00e1"+
		"\u00e7\u00ee\u00fb\u00ff\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}