// Generated from C:/code/ck/compiler/ckc/grammar\ck.g4 by ANTLR 4.7.2
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
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, NATURAL=38, 
		ID=39, TYPEID=40, WHITESPACE=41, COMMENT=42;
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
			"T__33", "T__34", "T__35", "T__36", "NATURAL", "ID", "TYPEID", "WHITESPACE", 
			"COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'decl'", "'{'", "'}'", "'('", "')'", "'-'", "'!'", "'~'", "'*'", 
			"'/'", "'%'", "'+'", "'<<'", "'>>'", "'<'", "'<='", "'>'", "'>='", "'<>'", 
			"'=='", "'!='", "'&'", "'^'", "'|'", "'&&'", "'||'", "'?'", "':'", "'='", 
			"'cfun'", "'let'", "'if'", "'else'", "'while'", "'break'", "','", "';'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "NATURAL", "ID", "TYPEID", "WHITESPACE", "COMMENT"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2,\u00e5\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\3"+
		"\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t"+
		"\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3"+
		"\20\3\20\3\21\3\21\3\21\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\24\3\25\3"+
		"\25\3\25\3\26\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\32\3"+
		"\33\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3"+
		" \3 \3 \3 \3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3$\3$\3$\3$"+
		"\3$\3$\3%\3%\3&\3&\3\'\6\'\u00c0\n\'\r\'\16\'\u00c1\3(\3(\7(\u00c6\n("+
		"\f(\16(\u00c9\13(\3)\3)\7)\u00cd\n)\f)\16)\u00d0\13)\3*\3*\3*\3*\3+\3"+
		"+\3+\3+\7+\u00da\n+\f+\16+\u00dd\13+\3+\5+\u00e0\n+\3+\3+\3+\3+\2\2,\3"+
		"\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37"+
		"\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37="+
		" ?!A\"C#E$G%I&K\'M(O)Q*S+U,\3\2\6\4\2aac|\6\2\62;C\\aac|\5\2\13\f\16\17"+
		"\"\"\4\2\f\f\17\17\2\u00e9\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2"+
		"\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2"+
		"\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3"+
		"\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2"+
		"\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67"+
		"\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2"+
		"\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2"+
		"\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\3W\3\2\2\2\5\\\3\2\2\2\7^\3\2\2\2\t"+
		"`\3\2\2\2\13b\3\2\2\2\rd\3\2\2\2\17f\3\2\2\2\21h\3\2\2\2\23j\3\2\2\2\25"+
		"l\3\2\2\2\27n\3\2\2\2\31p\3\2\2\2\33r\3\2\2\2\35u\3\2\2\2\37x\3\2\2\2"+
		"!z\3\2\2\2#}\3\2\2\2%\177\3\2\2\2\'\u0082\3\2\2\2)\u0085\3\2\2\2+\u0088"+
		"\3\2\2\2-\u008b\3\2\2\2/\u008d\3\2\2\2\61\u008f\3\2\2\2\63\u0091\3\2\2"+
		"\2\65\u0094\3\2\2\2\67\u0097\3\2\2\29\u0099\3\2\2\2;\u009b\3\2\2\2=\u009d"+
		"\3\2\2\2?\u00a2\3\2\2\2A\u00a6\3\2\2\2C\u00a9\3\2\2\2E\u00ae\3\2\2\2G"+
		"\u00b4\3\2\2\2I\u00ba\3\2\2\2K\u00bc\3\2\2\2M\u00bf\3\2\2\2O\u00c3\3\2"+
		"\2\2Q\u00ca\3\2\2\2S\u00d1\3\2\2\2U\u00d5\3\2\2\2WX\7f\2\2XY\7g\2\2YZ"+
		"\7e\2\2Z[\7n\2\2[\4\3\2\2\2\\]\7}\2\2]\6\3\2\2\2^_\7\177\2\2_\b\3\2\2"+
		"\2`a\7*\2\2a\n\3\2\2\2bc\7+\2\2c\f\3\2\2\2de\7/\2\2e\16\3\2\2\2fg\7#\2"+
		"\2g\20\3\2\2\2hi\7\u0080\2\2i\22\3\2\2\2jk\7,\2\2k\24\3\2\2\2lm\7\61\2"+
		"\2m\26\3\2\2\2no\7\'\2\2o\30\3\2\2\2pq\7-\2\2q\32\3\2\2\2rs\7>\2\2st\7"+
		">\2\2t\34\3\2\2\2uv\7@\2\2vw\7@\2\2w\36\3\2\2\2xy\7>\2\2y \3\2\2\2z{\7"+
		">\2\2{|\7?\2\2|\"\3\2\2\2}~\7@\2\2~$\3\2\2\2\177\u0080\7@\2\2\u0080\u0081"+
		"\7?\2\2\u0081&\3\2\2\2\u0082\u0083\7>\2\2\u0083\u0084\7@\2\2\u0084(\3"+
		"\2\2\2\u0085\u0086\7?\2\2\u0086\u0087\7?\2\2\u0087*\3\2\2\2\u0088\u0089"+
		"\7#\2\2\u0089\u008a\7?\2\2\u008a,\3\2\2\2\u008b\u008c\7(\2\2\u008c.\3"+
		"\2\2\2\u008d\u008e\7`\2\2\u008e\60\3\2\2\2\u008f\u0090\7~\2\2\u0090\62"+
		"\3\2\2\2\u0091\u0092\7(\2\2\u0092\u0093\7(\2\2\u0093\64\3\2\2\2\u0094"+
		"\u0095\7~\2\2\u0095\u0096\7~\2\2\u0096\66\3\2\2\2\u0097\u0098\7A\2\2\u0098"+
		"8\3\2\2\2\u0099\u009a\7<\2\2\u009a:\3\2\2\2\u009b\u009c\7?\2\2\u009c<"+
		"\3\2\2\2\u009d\u009e\7e\2\2\u009e\u009f\7h\2\2\u009f\u00a0\7w\2\2\u00a0"+
		"\u00a1\7p\2\2\u00a1>\3\2\2\2\u00a2\u00a3\7n\2\2\u00a3\u00a4\7g\2\2\u00a4"+
		"\u00a5\7v\2\2\u00a5@\3\2\2\2\u00a6\u00a7\7k\2\2\u00a7\u00a8\7h\2\2\u00a8"+
		"B\3\2\2\2\u00a9\u00aa\7g\2\2\u00aa\u00ab\7n\2\2\u00ab\u00ac\7u\2\2\u00ac"+
		"\u00ad\7g\2\2\u00adD\3\2\2\2\u00ae\u00af\7y\2\2\u00af\u00b0\7j\2\2\u00b0"+
		"\u00b1\7k\2\2\u00b1\u00b2\7n\2\2\u00b2\u00b3\7g\2\2\u00b3F\3\2\2\2\u00b4"+
		"\u00b5\7d\2\2\u00b5\u00b6\7t\2\2\u00b6\u00b7\7g\2\2\u00b7\u00b8\7c\2\2"+
		"\u00b8\u00b9\7m\2\2\u00b9H\3\2\2\2\u00ba\u00bb\7.\2\2\u00bbJ\3\2\2\2\u00bc"+
		"\u00bd\7=\2\2\u00bdL\3\2\2\2\u00be\u00c0\4\62;\2\u00bf\u00be\3\2\2\2\u00c0"+
		"\u00c1\3\2\2\2\u00c1\u00bf\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2N\3\2\2\2"+
		"\u00c3\u00c7\t\2\2\2\u00c4\u00c6\t\3\2\2\u00c5\u00c4\3\2\2\2\u00c6\u00c9"+
		"\3\2\2\2\u00c7\u00c5\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8P\3\2\2\2\u00c9"+
		"\u00c7\3\2\2\2\u00ca\u00ce\4C\\\2\u00cb\u00cd\t\3\2\2\u00cc\u00cb\3\2"+
		"\2\2\u00cd\u00d0\3\2\2\2\u00ce\u00cc\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf"+
		"R\3\2\2\2\u00d0\u00ce\3\2\2\2\u00d1\u00d2\t\4\2\2\u00d2\u00d3\3\2\2\2"+
		"\u00d3\u00d4\b*\2\2\u00d4T\3\2\2\2\u00d5\u00d6\7\61\2\2\u00d6\u00d7\7"+
		"\61\2\2\u00d7\u00db\3\2\2\2\u00d8\u00da\n\5\2\2\u00d9\u00d8\3\2\2\2\u00da"+
		"\u00dd\3\2\2\2\u00db\u00d9\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc\u00df\3\2"+
		"\2\2\u00dd\u00db\3\2\2\2\u00de\u00e0\7\17\2\2\u00df\u00de\3\2\2\2\u00df"+
		"\u00e0\3\2\2\2\u00e0\u00e1\3\2\2\2\u00e1\u00e2\7\f\2\2\u00e2\u00e3\3\2"+
		"\2\2\u00e3\u00e4\b+\2\2\u00e4V\3\2\2\2\b\2\u00c1\u00c7\u00ce\u00db\u00df"+
		"\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}