// Generated from /Users/colinholzman/Documents/CopperKitten/compiler/ckc/grammar/ck.g4 by ANTLR 4.7.2
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
		NATURAL=39, TEXT=40, ID=41, WHITESPACE=42, COMMENT=43;
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
			"T__33", "T__34", "T__35", "T__36", "T__37", "NATURAL", "TEXT", "EscapeSequence", 
			"HexDigit", "HexEscape", "UnicodeEscape", "ID", "WHITESPACE", "COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'type'", "'='", "'('", "')'", "'module'", "'{'", "'}'", "'|'", 
			"'-'", "'!'", "'~'", "'*'", "'/'", "'%'", "'+'", "'<<'", "'>>'", "'<'", 
			"'<='", "'>'", "'>='", "'<>'", "'=='", "'!='", "'&'", "'^'", "'&&'", 
			"'||'", "'?'", "':'", "'cfun'", "'let'", "'rec'", "'and'", "'if'", "'else'", 
			"','", "';'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, "NATURAL", "TEXT", "ID", "WHITESPACE", "COMMENT"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2-\u011f\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3"+
		"\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13"+
		"\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\21\3\22"+
		"\3\22\3\22\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\26\3\26\3\26\3\27\3\27"+
		"\3\27\3\30\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\34"+
		"\3\35\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3 \3 \3 \3!\3!\3!\3!\3\"\3\""+
		"\3\"\3\"\3#\3#\3#\3#\3$\3$\3$\3%\3%\3%\3%\3%\3&\3&\3\'\3\'\3(\6(\u00cd"+
		"\n(\r(\16(\u00ce\3)\3)\3)\7)\u00d4\n)\f)\16)\u00d7\13)\3)\3)\3*\3*\3*"+
		"\3*\5*\u00df\n*\3+\3+\3,\3,\3,\3,\3,\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-"+
		"\3-\3-\3-\3-\3-\3-\3-\5-\u00fa\n-\3.\3.\7.\u00fe\n.\f.\16.\u0101\13.\3"+
		".\3.\6.\u0105\n.\r.\16.\u0106\3.\5.\u010a\n.\3/\3/\3/\3/\3\60\3\60\3\60"+
		"\3\60\7\60\u0114\n\60\f\60\16\60\u0117\13\60\3\60\5\60\u011a\n\60\3\60"+
		"\3\60\3\60\3\60\2\2\61\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27"+
		"\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33"+
		"\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S\2U\2W\2Y\2[+],_-\3\2\n\4"+
		"\2$$^^\t\2$$^^ddhhppttvv\5\2\62;CHch\5\2C\\aac|\6\2\62;C\\aac|\3\2bb\5"+
		"\2\13\f\16\17\"\"\4\2\f\f\17\17\2\u0125\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3"+
		"\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2"+
		"\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35"+
		"\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)"+
		"\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2"+
		"\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2"+
		"A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3"+
		"\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\3a\3\2\2"+
		"\2\5f\3\2\2\2\7h\3\2\2\2\tj\3\2\2\2\13l\3\2\2\2\rs\3\2\2\2\17u\3\2\2\2"+
		"\21w\3\2\2\2\23y\3\2\2\2\25{\3\2\2\2\27}\3\2\2\2\31\177\3\2\2\2\33\u0081"+
		"\3\2\2\2\35\u0083\3\2\2\2\37\u0085\3\2\2\2!\u0087\3\2\2\2#\u008a\3\2\2"+
		"\2%\u008d\3\2\2\2\'\u008f\3\2\2\2)\u0092\3\2\2\2+\u0094\3\2\2\2-\u0097"+
		"\3\2\2\2/\u009a\3\2\2\2\61\u009d\3\2\2\2\63\u00a0\3\2\2\2\65\u00a2\3\2"+
		"\2\2\67\u00a4\3\2\2\29\u00a7\3\2\2\2;\u00aa\3\2\2\2=\u00ac\3\2\2\2?\u00ae"+
		"\3\2\2\2A\u00b3\3\2\2\2C\u00b7\3\2\2\2E\u00bb\3\2\2\2G\u00bf\3\2\2\2I"+
		"\u00c2\3\2\2\2K\u00c7\3\2\2\2M\u00c9\3\2\2\2O\u00cc\3\2\2\2Q\u00d0\3\2"+
		"\2\2S\u00de\3\2\2\2U\u00e0\3\2\2\2W\u00e2\3\2\2\2Y\u00f9\3\2\2\2[\u0109"+
		"\3\2\2\2]\u010b\3\2\2\2_\u010f\3\2\2\2ab\7v\2\2bc\7{\2\2cd\7r\2\2de\7"+
		"g\2\2e\4\3\2\2\2fg\7?\2\2g\6\3\2\2\2hi\7*\2\2i\b\3\2\2\2jk\7+\2\2k\n\3"+
		"\2\2\2lm\7o\2\2mn\7q\2\2no\7f\2\2op\7w\2\2pq\7n\2\2qr\7g\2\2r\f\3\2\2"+
		"\2st\7}\2\2t\16\3\2\2\2uv\7\177\2\2v\20\3\2\2\2wx\7~\2\2x\22\3\2\2\2y"+
		"z\7/\2\2z\24\3\2\2\2{|\7#\2\2|\26\3\2\2\2}~\7\u0080\2\2~\30\3\2\2\2\177"+
		"\u0080\7,\2\2\u0080\32\3\2\2\2\u0081\u0082\7\61\2\2\u0082\34\3\2\2\2\u0083"+
		"\u0084\7\'\2\2\u0084\36\3\2\2\2\u0085\u0086\7-\2\2\u0086 \3\2\2\2\u0087"+
		"\u0088\7>\2\2\u0088\u0089\7>\2\2\u0089\"\3\2\2\2\u008a\u008b\7@\2\2\u008b"+
		"\u008c\7@\2\2\u008c$\3\2\2\2\u008d\u008e\7>\2\2\u008e&\3\2\2\2\u008f\u0090"+
		"\7>\2\2\u0090\u0091\7?\2\2\u0091(\3\2\2\2\u0092\u0093\7@\2\2\u0093*\3"+
		"\2\2\2\u0094\u0095\7@\2\2\u0095\u0096\7?\2\2\u0096,\3\2\2\2\u0097\u0098"+
		"\7>\2\2\u0098\u0099\7@\2\2\u0099.\3\2\2\2\u009a\u009b\7?\2\2\u009b\u009c"+
		"\7?\2\2\u009c\60\3\2\2\2\u009d\u009e\7#\2\2\u009e\u009f\7?\2\2\u009f\62"+
		"\3\2\2\2\u00a0\u00a1\7(\2\2\u00a1\64\3\2\2\2\u00a2\u00a3\7`\2\2\u00a3"+
		"\66\3\2\2\2\u00a4\u00a5\7(\2\2\u00a5\u00a6\7(\2\2\u00a68\3\2\2\2\u00a7"+
		"\u00a8\7~\2\2\u00a8\u00a9\7~\2\2\u00a9:\3\2\2\2\u00aa\u00ab\7A\2\2\u00ab"+
		"<\3\2\2\2\u00ac\u00ad\7<\2\2\u00ad>\3\2\2\2\u00ae\u00af\7e\2\2\u00af\u00b0"+
		"\7h\2\2\u00b0\u00b1\7w\2\2\u00b1\u00b2\7p\2\2\u00b2@\3\2\2\2\u00b3\u00b4"+
		"\7n\2\2\u00b4\u00b5\7g\2\2\u00b5\u00b6\7v\2\2\u00b6B\3\2\2\2\u00b7\u00b8"+
		"\7t\2\2\u00b8\u00b9\7g\2\2\u00b9\u00ba\7e\2\2\u00baD\3\2\2\2\u00bb\u00bc"+
		"\7c\2\2\u00bc\u00bd\7p\2\2\u00bd\u00be\7f\2\2\u00beF\3\2\2\2\u00bf\u00c0"+
		"\7k\2\2\u00c0\u00c1\7h\2\2\u00c1H\3\2\2\2\u00c2\u00c3\7g\2\2\u00c3\u00c4"+
		"\7n\2\2\u00c4\u00c5\7u\2\2\u00c5\u00c6\7g\2\2\u00c6J\3\2\2\2\u00c7\u00c8"+
		"\7.\2\2\u00c8L\3\2\2\2\u00c9\u00ca\7=\2\2\u00caN\3\2\2\2\u00cb\u00cd\4"+
		"\62;\2\u00cc\u00cb\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00cc\3\2\2\2\u00ce"+
		"\u00cf\3\2\2\2\u00cfP\3\2\2\2\u00d0\u00d5\7$\2\2\u00d1\u00d4\5S*\2\u00d2"+
		"\u00d4\n\2\2\2\u00d3\u00d1\3\2\2\2\u00d3\u00d2\3\2\2\2\u00d4\u00d7\3\2"+
		"\2\2\u00d5\u00d3\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d8\3\2\2\2\u00d7"+
		"\u00d5\3\2\2\2\u00d8\u00d9\7$\2\2\u00d9R\3\2\2\2\u00da\u00db\7^\2\2\u00db"+
		"\u00df\t\3\2\2\u00dc\u00df\5W,\2\u00dd\u00df\5Y-\2\u00de\u00da\3\2\2\2"+
		"\u00de\u00dc\3\2\2\2\u00de\u00dd\3\2\2\2\u00dfT\3\2\2\2\u00e0\u00e1\t"+
		"\4\2\2\u00e1V\3\2\2\2\u00e2\u00e3\7^\2\2\u00e3\u00e4\7z\2\2\u00e4\u00e5"+
		"\5U+\2\u00e5\u00e6\5U+\2\u00e6X\3\2\2\2\u00e7\u00e8\7^\2\2\u00e8\u00e9"+
		"\7w\2\2\u00e9\u00ea\5U+\2\u00ea\u00eb\5U+\2\u00eb\u00ec\5U+\2\u00ec\u00ed"+
		"\5U+\2\u00ed\u00fa\3\2\2\2\u00ee\u00ef\7^\2\2\u00ef\u00f0\7w\2\2\u00f0"+
		"\u00f1\5U+\2\u00f1\u00f2\5U+\2\u00f2\u00f3\5U+\2\u00f3\u00f4\5U+\2\u00f4"+
		"\u00f5\5U+\2\u00f5\u00f6\5U+\2\u00f6\u00f7\5U+\2\u00f7\u00f8\5U+\2\u00f8"+
		"\u00fa\3\2\2\2\u00f9\u00e7\3\2\2\2\u00f9\u00ee\3\2\2\2\u00faZ\3\2\2\2"+
		"\u00fb\u00ff\t\5\2\2\u00fc\u00fe\t\6\2\2\u00fd\u00fc\3\2\2\2\u00fe\u0101"+
		"\3\2\2\2\u00ff\u00fd\3\2\2\2\u00ff\u0100\3\2\2\2\u0100\u010a\3\2\2\2\u0101"+
		"\u00ff\3\2\2\2\u0102\u0104\7b\2\2\u0103\u0105\n\7\2\2\u0104\u0103\3\2"+
		"\2\2\u0105\u0106\3\2\2\2\u0106\u0104\3\2\2\2\u0106\u0107\3\2\2\2\u0107"+
		"\u0108\3\2\2\2\u0108\u010a\7b\2\2\u0109\u00fb\3\2\2\2\u0109\u0102\3\2"+
		"\2\2\u010a\\\3\2\2\2\u010b\u010c\t\b\2\2\u010c\u010d\3\2\2\2\u010d\u010e"+
		"\b/\2\2\u010e^\3\2\2\2\u010f\u0110\7\61\2\2\u0110\u0111\7\61\2\2\u0111"+
		"\u0115\3\2\2\2\u0112\u0114\n\t\2\2\u0113\u0112\3\2\2\2\u0114\u0117\3\2"+
		"\2\2\u0115\u0113\3\2\2\2\u0115\u0116\3\2\2\2\u0116\u0119\3\2\2\2\u0117"+
		"\u0115\3\2\2\2\u0118\u011a\7\17\2\2\u0119\u0118\3\2\2\2\u0119\u011a\3"+
		"\2\2\2\u011a\u011b\3\2\2\2\u011b\u011c\7\f\2\2\u011c\u011d\3\2\2\2\u011d"+
		"\u011e\b\60\2\2\u011e`\3\2\2\2\r\2\u00ce\u00d3\u00d5\u00de\u00f9\u00ff"+
		"\u0106\u0109\u0115\u0119\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}