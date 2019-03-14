// Generated from /Users/colinholzman/Documents/CopperKitten/compiler/ckc/grammar/ck.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ckParser extends Parser {
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
	public static final int
		RULE_file = 0, RULE_decl = 1, RULE_expr = 2, RULE_args = 3, RULE_sequence = 4, 
		RULE_param = 5, RULE_params = 6, RULE_type = 7, RULE_simpleType = 8, RULE_funType = 9, 
		RULE_types = 10;
	private static String[] makeRuleNames() {
		return new String[] {
			"file", "decl", "expr", "args", "sequence", "param", "params", "type", 
			"simpleType", "funType", "types"
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

	@Override
	public String getGrammarFileName() { return "ck.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ckParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class FileContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(ckParser.EOF, 0); }
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public FileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).enterFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).exitFile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckVisitor ) return ((ckVisitor<? extends T>)visitor).visitFile(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FileContext file() throws RecognitionException {
		FileContext _localctx = new FileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_file);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(25);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(22);
				decl();
				}
				}
				setState(27);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(29);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__3) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << T__33) | (1L << T__34) | (1L << NATURAL) | (1L << ID))) != 0)) {
				{
				setState(28);
				expr(0);
				}
			}

			setState(31);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclContext extends ParserRuleContext {
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).enterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).exitDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckVisitor ) return ((ckVisitor<? extends T>)visitor).visitDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_decl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33);
			match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ApplyExprContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public ApplyExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).enterApplyExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).exitApplyExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckVisitor ) return ((ckVisitor<? extends T>)visitor).visitApplyExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FunExprContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public FunExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).enterFunExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).exitFunExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckVisitor ) return ((ckVisitor<? extends T>)visitor).visitFunExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CondExprContext extends ExprContext {
		public ExprContext cond;
		public ExprContext con;
		public ExprContext alt;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public CondExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).enterCondExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).exitCondExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckVisitor ) return ((ckVisitor<? extends T>)visitor).visitCondExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OrExprContext extends ExprContext {
		public ExprContext lhs;
		public ExprContext rhs;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public OrExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).enterOrExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).exitOrExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckVisitor ) return ((ckVisitor<? extends T>)visitor).visitOrExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SequenceExprContext extends ExprContext {
		public SequenceContext sequence() {
			return getRuleContext(SequenceContext.class,0);
		}
		public SequenceExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).enterSequenceExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).exitSequenceExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckVisitor ) return ((ckVisitor<? extends T>)visitor).visitSequenceExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BreakExprContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public BreakExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).enterBreakExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).exitBreakExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckVisitor ) return ((ckVisitor<? extends T>)visitor).visitBreakExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MultExprContext extends ExprContext {
		public ExprContext lhs;
		public Token op;
		public ExprContext rhs;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public MultExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).enterMultExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).exitMultExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckVisitor ) return ((ckVisitor<? extends T>)visitor).visitMultExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LetExprContext extends ExprContext {
		public ExprContext value;
		public TerminalNode ID() { return getToken(ckParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public LetExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).enterLetExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).exitLetExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckVisitor ) return ((ckVisitor<? extends T>)visitor).visitLetExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BitXorExprContext extends ExprContext {
		public ExprContext lhs;
		public ExprContext rhs;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public BitXorExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).enterBitXorExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).exitBitXorExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckVisitor ) return ((ckVisitor<? extends T>)visitor).visitBitXorExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CFunExprContext extends ExprContext {
		public TerminalNode ID() { return getToken(ckParser.ID, 0); }
		public FunTypeContext funType() {
			return getRuleContext(FunTypeContext.class,0);
		}
		public CFunExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).enterCFunExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).exitCFunExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckVisitor ) return ((ckVisitor<? extends T>)visitor).visitCFunExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ShiftExprContext extends ExprContext {
		public ExprContext lhs;
		public Token op;
		public ExprContext rhs;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ShiftExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).enterShiftExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).exitShiftExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckVisitor ) return ((ckVisitor<? extends T>)visitor).visitShiftExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BitOrExprContext extends ExprContext {
		public ExprContext lhs;
		public ExprContext rhs;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public BitOrExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).enterBitOrExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).exitBitOrExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckVisitor ) return ((ckVisitor<? extends T>)visitor).visitBitOrExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnaryExprContext extends ExprContext {
		public Token op;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public UnaryExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).enterUnaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).exitUnaryExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckVisitor ) return ((ckVisitor<? extends T>)visitor).visitUnaryExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WhileExprContext extends ExprContext {
		public ExprContext cond;
		public ExprContext body;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public WhileExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).enterWhileExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).exitWhileExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckVisitor ) return ((ckVisitor<? extends T>)visitor).visitWhileExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AddExprContext extends ExprContext {
		public ExprContext lhs;
		public Token op;
		public ExprContext rhs;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public AddExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).enterAddExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).exitAddExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckVisitor ) return ((ckVisitor<? extends T>)visitor).visitAddExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RefExprContext extends ExprContext {
		public TerminalNode ID() { return getToken(ckParser.ID, 0); }
		public RefExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).enterRefExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).exitRefExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckVisitor ) return ((ckVisitor<? extends T>)visitor).visitRefExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfExprContext extends ExprContext {
		public ExprContext cond;
		public ExprContext csq;
		public ExprContext alt;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public IfExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).enterIfExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).exitIfExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckVisitor ) return ((ckVisitor<? extends T>)visitor).visitIfExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NaturalExprContext extends ExprContext {
		public TerminalNode NATURAL() { return getToken(ckParser.NATURAL, 0); }
		public NaturalExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).enterNaturalExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).exitNaturalExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckVisitor ) return ((ckVisitor<? extends T>)visitor).visitNaturalExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RelExprContext extends ExprContext {
		public ExprContext lhs;
		public Token op;
		public ExprContext rhs;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public RelExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).enterRelExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).exitRelExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckVisitor ) return ((ckVisitor<? extends T>)visitor).visitRelExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BitAndExprContext extends ExprContext {
		public ExprContext lhs;
		public ExprContext rhs;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public BitAndExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).enterBitAndExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).exitBitAndExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckVisitor ) return ((ckVisitor<? extends T>)visitor).visitBitAndExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignExprContext extends ExprContext {
		public ExprContext target;
		public ExprContext value;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public AssignExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).enterAssignExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).exitAssignExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckVisitor ) return ((ckVisitor<? extends T>)visitor).visitAssignExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnitExprContext extends ExprContext {
		public UnitExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).enterUnitExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).exitUnitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckVisitor ) return ((ckVisitor<? extends T>)visitor).visitUnitExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EqualityExprContext extends ExprContext {
		public ExprContext lhs;
		public Token op;
		public ExprContext rhs;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public EqualityExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).enterEqualityExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).exitEqualityExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckVisitor ) return ((ckVisitor<? extends T>)visitor).visitEqualityExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AndExprContext extends ExprContext {
		public ExprContext lhs;
		public ExprContext rhs;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public AndExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).enterAndExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).exitAndExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckVisitor ) return ((ckVisitor<? extends T>)visitor).visitAndExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				_localctx = new SequenceExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(36);
				match(T__1);
				setState(37);
				sequence();
				setState(38);
				match(T__2);
				}
				break;
			case 2:
				{
				_localctx = new UnitExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(40);
				match(T__3);
				setState(41);
				match(T__4);
				}
				break;
			case 3:
				{
				_localctx = new NaturalExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(42);
				match(NATURAL);
				}
				break;
			case 4:
				{
				_localctx = new RefExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(43);
				match(ID);
				}
				break;
			case 5:
				{
				_localctx = new UnaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(44);
				((UnaryExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7))) != 0)) ) {
					((UnaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(45);
				expr(19);
				}
				break;
			case 6:
				{
				_localctx = new FunExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(46);
				match(T__3);
				setState(48);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(47);
					params();
					}
				}

				setState(50);
				match(T__4);
				setState(51);
				match(T__27);
				setState(53);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(52);
					type();
					}
					break;
				}
				setState(55);
				expr(6);
				}
				break;
			case 7:
				{
				_localctx = new CFunExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(56);
				match(T__29);
				setState(57);
				match(ID);
				setState(58);
				funType();
				}
				break;
			case 8:
				{
				_localctx = new LetExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(59);
				match(T__30);
				setState(60);
				match(ID);
				setState(61);
				match(T__28);
				setState(62);
				((LetExprContext)_localctx).value = expr(4);
				}
				break;
			case 9:
				{
				_localctx = new IfExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(63);
				match(T__31);
				setState(64);
				match(T__3);
				setState(65);
				((IfExprContext)_localctx).cond = expr(0);
				setState(66);
				match(T__4);
				setState(67);
				((IfExprContext)_localctx).csq = expr(0);
				setState(70);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
				case 1:
					{
					setState(68);
					match(T__32);
					setState(69);
					((IfExprContext)_localctx).alt = expr(0);
					}
					break;
				}
				}
				break;
			case 10:
				{
				_localctx = new WhileExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(72);
				match(T__33);
				setState(73);
				match(T__3);
				setState(74);
				((WhileExprContext)_localctx).cond = expr(0);
				setState(75);
				match(T__4);
				setState(76);
				((WhileExprContext)_localctx).body = expr(2);
				}
				break;
			case 11:
				{
				_localctx = new BreakExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(78);
				match(T__34);
				setState(80);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
				case 1:
					{
					setState(79);
					expr(0);
					}
					break;
				}
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(131);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(129);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
					case 1:
						{
						_localctx = new MultExprContext(new ExprContext(_parentctx, _parentState));
						((MultExprContext)_localctx).lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(84);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(85);
						((MultExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__8) | (1L << T__9) | (1L << T__10))) != 0)) ) {
							((MultExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(86);
						((MultExprContext)_localctx).rhs = expr(19);
						}
						break;
					case 2:
						{
						_localctx = new AddExprContext(new ExprContext(_parentctx, _parentState));
						((AddExprContext)_localctx).lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(87);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(88);
						((AddExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__5 || _la==T__11) ) {
							((AddExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(89);
						((AddExprContext)_localctx).rhs = expr(18);
						}
						break;
					case 3:
						{
						_localctx = new ShiftExprContext(new ExprContext(_parentctx, _parentState));
						((ShiftExprContext)_localctx).lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(90);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(91);
						((ShiftExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__12 || _la==T__13) ) {
							((ShiftExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(92);
						((ShiftExprContext)_localctx).rhs = expr(17);
						}
						break;
					case 4:
						{
						_localctx = new RelExprContext(new ExprContext(_parentctx, _parentState));
						((RelExprContext)_localctx).lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(93);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(94);
						((RelExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18))) != 0)) ) {
							((RelExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(95);
						((RelExprContext)_localctx).rhs = expr(16);
						}
						break;
					case 5:
						{
						_localctx = new EqualityExprContext(new ExprContext(_parentctx, _parentState));
						((EqualityExprContext)_localctx).lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(96);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(97);
						((EqualityExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__19 || _la==T__20) ) {
							((EqualityExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(98);
						((EqualityExprContext)_localctx).rhs = expr(15);
						}
						break;
					case 6:
						{
						_localctx = new BitAndExprContext(new ExprContext(_parentctx, _parentState));
						((BitAndExprContext)_localctx).lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(99);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(100);
						match(T__21);
						setState(101);
						((BitAndExprContext)_localctx).rhs = expr(14);
						}
						break;
					case 7:
						{
						_localctx = new BitXorExprContext(new ExprContext(_parentctx, _parentState));
						((BitXorExprContext)_localctx).lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(102);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(103);
						match(T__22);
						setState(104);
						((BitXorExprContext)_localctx).rhs = expr(13);
						}
						break;
					case 8:
						{
						_localctx = new BitOrExprContext(new ExprContext(_parentctx, _parentState));
						((BitOrExprContext)_localctx).lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(105);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(106);
						match(T__23);
						setState(107);
						((BitOrExprContext)_localctx).rhs = expr(12);
						}
						break;
					case 9:
						{
						_localctx = new AndExprContext(new ExprContext(_parentctx, _parentState));
						((AndExprContext)_localctx).lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(108);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(109);
						match(T__24);
						setState(110);
						((AndExprContext)_localctx).rhs = expr(11);
						}
						break;
					case 10:
						{
						_localctx = new OrExprContext(new ExprContext(_parentctx, _parentState));
						((OrExprContext)_localctx).lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(111);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(112);
						match(T__25);
						setState(113);
						((OrExprContext)_localctx).rhs = expr(10);
						}
						break;
					case 11:
						{
						_localctx = new CondExprContext(new ExprContext(_parentctx, _parentState));
						((CondExprContext)_localctx).cond = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(114);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(115);
						match(T__26);
						setState(116);
						((CondExprContext)_localctx).con = expr(0);
						setState(117);
						match(T__27);
						setState(118);
						((CondExprContext)_localctx).alt = expr(8);
						}
						break;
					case 12:
						{
						_localctx = new AssignExprContext(new ExprContext(_parentctx, _parentState));
						((AssignExprContext)_localctx).target = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(120);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(121);
						match(T__28);
						setState(122);
						((AssignExprContext)_localctx).value = expr(7);
						}
						break;
					case 13:
						{
						_localctx = new ApplyExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(123);
						if (!(precpred(_ctx, 20))) throw new FailedPredicateException(this, "precpred(_ctx, 20)");
						setState(124);
						match(T__3);
						setState(126);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__3) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << T__33) | (1L << T__34) | (1L << NATURAL) | (1L << ID))) != 0)) {
							{
							setState(125);
							args();
							}
						}

						setState(128);
						match(T__4);
						}
						break;
					}
					} 
				}
				setState(133);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ArgsContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).enterArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).exitArgs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckVisitor ) return ((ckVisitor<? extends T>)visitor).visitArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgsContext args() throws RecognitionException {
		ArgsContext _localctx = new ArgsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_args);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			expr(0);
			setState(139);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__35) {
				{
				{
				setState(135);
				match(T__35);
				setState(136);
				expr(0);
				}
				}
				setState(141);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SequenceContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public SequenceContext sequence() {
			return getRuleContext(SequenceContext.class,0);
		}
		public SequenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sequence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).enterSequence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).exitSequence(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckVisitor ) return ((ckVisitor<? extends T>)visitor).visitSequence(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SequenceContext sequence() throws RecognitionException {
		SequenceContext _localctx = new SequenceContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_sequence);
		try {
			setState(147);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(142);
				expr(0);
				setState(143);
				match(T__36);
				setState(144);
				sequence();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(146);
				expr(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(ckParser.ID, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).enterParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).exitParam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckVisitor ) return ((ckVisitor<? extends T>)visitor).visitParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_param);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			match(ID);
			setState(152);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__27) {
				{
				setState(150);
				match(T__27);
				setState(151);
				type();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamsContext extends ParserRuleContext {
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).enterParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).exitParams(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckVisitor ) return ((ckVisitor<? extends T>)visitor).visitParams(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_params);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
			param();
			setState(159);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__35) {
				{
				{
				setState(155);
				match(T__35);
				setState(156);
				param();
				}
				}
				setState(161);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public SimpleTypeContext simpleType() {
			return getRuleContext(SimpleTypeContext.class,0);
		}
		public FunTypeContext funType() {
			return getRuleContext(FunTypeContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckVisitor ) return ((ckVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_type);
		try {
			setState(164);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TYPEID:
				enterOuterAlt(_localctx, 1);
				{
				setState(162);
				simpleType();
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(163);
				funType();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SimpleTypeContext extends ParserRuleContext {
		public TerminalNode TYPEID() { return getToken(ckParser.TYPEID, 0); }
		public SimpleTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).enterSimpleType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).exitSimpleType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckVisitor ) return ((ckVisitor<? extends T>)visitor).visitSimpleType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SimpleTypeContext simpleType() throws RecognitionException {
		SimpleTypeContext _localctx = new SimpleTypeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_simpleType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			match(TYPEID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunTypeContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TypesContext types() {
			return getRuleContext(TypesContext.class,0);
		}
		public FunTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).enterFunType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).exitFunType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckVisitor ) return ((ckVisitor<? extends T>)visitor).visitFunType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunTypeContext funType() throws RecognitionException {
		FunTypeContext _localctx = new FunTypeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_funType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			match(T__3);
			setState(170);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3 || _la==TYPEID) {
				{
				setState(169);
				types();
				}
			}

			setState(172);
			match(T__4);
			setState(173);
			match(T__27);
			setState(174);
			type();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypesContext extends ParserRuleContext {
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public TypesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_types; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).enterTypes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ckListener ) ((ckListener)listener).exitTypes(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ckVisitor ) return ((ckVisitor<? extends T>)visitor).visitTypes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypesContext types() throws RecognitionException {
		TypesContext _localctx = new TypesContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_types);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176);
			type();
			setState(181);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__35) {
				{
				{
				setState(177);
				match(T__35);
				setState(178);
				type();
				}
				}
				setState(183);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 2:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 18);
		case 1:
			return precpred(_ctx, 17);
		case 2:
			return precpred(_ctx, 16);
		case 3:
			return precpred(_ctx, 15);
		case 4:
			return precpred(_ctx, 14);
		case 5:
			return precpred(_ctx, 13);
		case 6:
			return precpred(_ctx, 12);
		case 7:
			return precpred(_ctx, 11);
		case 8:
			return precpred(_ctx, 10);
		case 9:
			return precpred(_ctx, 9);
		case 10:
			return precpred(_ctx, 8);
		case 11:
			return precpred(_ctx, 7);
		case 12:
			return precpred(_ctx, 20);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3,\u00bb\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\3\2\7\2\32\n\2\f\2\16\2\35\13\2\3\2\5\2 \n\2\3\2\3\2\3\3\3"+
		"\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\63\n\4\3\4"+
		"\3\4\3\4\5\48\n\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\5\4I\n\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4S\n\4\5\4U\n\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\u0081\n\4\3\4\7\4\u0084\n\4\f\4\16\4\u0087"+
		"\13\4\3\5\3\5\3\5\7\5\u008c\n\5\f\5\16\5\u008f\13\5\3\6\3\6\3\6\3\6\3"+
		"\6\5\6\u0096\n\6\3\7\3\7\3\7\5\7\u009b\n\7\3\b\3\b\3\b\7\b\u00a0\n\b\f"+
		"\b\16\b\u00a3\13\b\3\t\3\t\5\t\u00a7\n\t\3\n\3\n\3\13\3\13\5\13\u00ad"+
		"\n\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\7\f\u00b6\n\f\f\f\16\f\u00b9\13"+
		"\f\3\f\2\3\6\r\2\4\6\b\n\f\16\20\22\24\26\2\b\3\2\b\n\3\2\13\r\4\2\b\b"+
		"\16\16\3\2\17\20\3\2\21\25\3\2\26\27\2\u00d4\2\33\3\2\2\2\4#\3\2\2\2\6"+
		"T\3\2\2\2\b\u0088\3\2\2\2\n\u0095\3\2\2\2\f\u0097\3\2\2\2\16\u009c\3\2"+
		"\2\2\20\u00a6\3\2\2\2\22\u00a8\3\2\2\2\24\u00aa\3\2\2\2\26\u00b2\3\2\2"+
		"\2\30\32\5\4\3\2\31\30\3\2\2\2\32\35\3\2\2\2\33\31\3\2\2\2\33\34\3\2\2"+
		"\2\34\37\3\2\2\2\35\33\3\2\2\2\36 \5\6\4\2\37\36\3\2\2\2\37 \3\2\2\2 "+
		"!\3\2\2\2!\"\7\2\2\3\"\3\3\2\2\2#$\7\3\2\2$\5\3\2\2\2%&\b\4\1\2&\'\7\4"+
		"\2\2\'(\5\n\6\2()\7\5\2\2)U\3\2\2\2*+\7\6\2\2+U\7\7\2\2,U\7(\2\2-U\7)"+
		"\2\2./\t\2\2\2/U\5\6\4\25\60\62\7\6\2\2\61\63\5\16\b\2\62\61\3\2\2\2\62"+
		"\63\3\2\2\2\63\64\3\2\2\2\64\65\7\7\2\2\65\67\7\36\2\2\668\5\20\t\2\67"+
		"\66\3\2\2\2\678\3\2\2\289\3\2\2\29U\5\6\4\b:;\7 \2\2;<\7)\2\2<U\5\24\13"+
		"\2=>\7!\2\2>?\7)\2\2?@\7\37\2\2@U\5\6\4\6AB\7\"\2\2BC\7\6\2\2CD\5\6\4"+
		"\2DE\7\7\2\2EH\5\6\4\2FG\7#\2\2GI\5\6\4\2HF\3\2\2\2HI\3\2\2\2IU\3\2\2"+
		"\2JK\7$\2\2KL\7\6\2\2LM\5\6\4\2MN\7\7\2\2NO\5\6\4\4OU\3\2\2\2PR\7%\2\2"+
		"QS\5\6\4\2RQ\3\2\2\2RS\3\2\2\2SU\3\2\2\2T%\3\2\2\2T*\3\2\2\2T,\3\2\2\2"+
		"T-\3\2\2\2T.\3\2\2\2T\60\3\2\2\2T:\3\2\2\2T=\3\2\2\2TA\3\2\2\2TJ\3\2\2"+
		"\2TP\3\2\2\2U\u0085\3\2\2\2VW\f\24\2\2WX\t\3\2\2X\u0084\5\6\4\25YZ\f\23"+
		"\2\2Z[\t\4\2\2[\u0084\5\6\4\24\\]\f\22\2\2]^\t\5\2\2^\u0084\5\6\4\23_"+
		"`\f\21\2\2`a\t\6\2\2a\u0084\5\6\4\22bc\f\20\2\2cd\t\7\2\2d\u0084\5\6\4"+
		"\21ef\f\17\2\2fg\7\30\2\2g\u0084\5\6\4\20hi\f\16\2\2ij\7\31\2\2j\u0084"+
		"\5\6\4\17kl\f\r\2\2lm\7\32\2\2m\u0084\5\6\4\16no\f\f\2\2op\7\33\2\2p\u0084"+
		"\5\6\4\rqr\f\13\2\2rs\7\34\2\2s\u0084\5\6\4\ftu\f\n\2\2uv\7\35\2\2vw\5"+
		"\6\4\2wx\7\36\2\2xy\5\6\4\ny\u0084\3\2\2\2z{\f\t\2\2{|\7\37\2\2|\u0084"+
		"\5\6\4\t}~\f\26\2\2~\u0080\7\6\2\2\177\u0081\5\b\5\2\u0080\177\3\2\2\2"+
		"\u0080\u0081\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0084\7\7\2\2\u0083V\3"+
		"\2\2\2\u0083Y\3\2\2\2\u0083\\\3\2\2\2\u0083_\3\2\2\2\u0083b\3\2\2\2\u0083"+
		"e\3\2\2\2\u0083h\3\2\2\2\u0083k\3\2\2\2\u0083n\3\2\2\2\u0083q\3\2\2\2"+
		"\u0083t\3\2\2\2\u0083z\3\2\2\2\u0083}\3\2\2\2\u0084\u0087\3\2\2\2\u0085"+
		"\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086\7\3\2\2\2\u0087\u0085\3\2\2\2"+
		"\u0088\u008d\5\6\4\2\u0089\u008a\7&\2\2\u008a\u008c\5\6\4\2\u008b\u0089"+
		"\3\2\2\2\u008c\u008f\3\2\2\2\u008d\u008b\3\2\2\2\u008d\u008e\3\2\2\2\u008e"+
		"\t\3\2\2\2\u008f\u008d\3\2\2\2\u0090\u0091\5\6\4\2\u0091\u0092\7\'\2\2"+
		"\u0092\u0093\5\n\6\2\u0093\u0096\3\2\2\2\u0094\u0096\5\6\4\2\u0095\u0090"+
		"\3\2\2\2\u0095\u0094\3\2\2\2\u0096\13\3\2\2\2\u0097\u009a\7)\2\2\u0098"+
		"\u0099\7\36\2\2\u0099\u009b\5\20\t\2\u009a\u0098\3\2\2\2\u009a\u009b\3"+
		"\2\2\2\u009b\r\3\2\2\2\u009c\u00a1\5\f\7\2\u009d\u009e\7&\2\2\u009e\u00a0"+
		"\5\f\7\2\u009f\u009d\3\2\2\2\u00a0\u00a3\3\2\2\2\u00a1\u009f\3\2\2\2\u00a1"+
		"\u00a2\3\2\2\2\u00a2\17\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a4\u00a7\5\22\n"+
		"\2\u00a5\u00a7\5\24\13\2\u00a6\u00a4\3\2\2\2\u00a6\u00a5\3\2\2\2\u00a7"+
		"\21\3\2\2\2\u00a8\u00a9\7*\2\2\u00a9\23\3\2\2\2\u00aa\u00ac\7\6\2\2\u00ab"+
		"\u00ad\5\26\f\2\u00ac\u00ab\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00ae\3"+
		"\2\2\2\u00ae\u00af\7\7\2\2\u00af\u00b0\7\36\2\2\u00b0\u00b1\5\20\t\2\u00b1"+
		"\25\3\2\2\2\u00b2\u00b7\5\20\t\2\u00b3\u00b4\7&\2\2\u00b4\u00b6\5\20\t"+
		"\2\u00b5\u00b3\3\2\2\2\u00b6\u00b9\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b7\u00b8"+
		"\3\2\2\2\u00b8\27\3\2\2\2\u00b9\u00b7\3\2\2\2\23\33\37\62\67HRT\u0080"+
		"\u0083\u0085\u008d\u0095\u009a\u00a1\u00a6\u00ac\u00b7";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}