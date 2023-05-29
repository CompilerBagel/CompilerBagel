// Generated from /home/cardigan/Compiler/CompilerBagel/SysYLexer.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SysYLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		CONST=1, INT=2, VOID=3, IF=4, ELSE=5, WHILE=6, BREAK=7, CONTINUE=8, RETURN=9, 
		PLUS=10, MINUS=11, MUL=12, DIV=13, MOD=14, ASSIGN=15, EQ=16, NEQ=17, LT=18, 
		GT=19, LE=20, GE=21, NOT=22, AND=23, OR=24, L_PAREN=25, R_PAREN=26, L_BRACE=27, 
		R_BRACE=28, L_BRACKT=29, R_BRACKT=30, COMMA=31, SEMICOLON=32, IDENT=33, 
		INTEGR_CONST=34, FLOAT_CONST=35, WS=36, SL_COMMENT=37, ML_COMMENT=38;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"CONST", "INT", "VOID", "IF", "ELSE", "WHILE", "BREAK", "CONTINUE", "RETURN", 
			"PLUS", "MINUS", "MUL", "DIV", "MOD", "ASSIGN", "EQ", "NEQ", "LT", "GT", 
			"LE", "GE", "NOT", "AND", "OR", "L_PAREN", "R_PAREN", "L_BRACE", "R_BRACE", 
			"L_BRACKT", "R_BRACKT", "COMMA", "SEMICOLON", "IDENT", "INTEGR_CONST", 
			"DECIMAL", "OCTAL", "HEXADECIMAL", "EXPONENT", "BINARY_EXPONENT", "FLOAT_SUFFIX", 
			"FLOAT_CONST", "WS", "SL_COMMENT", "ML_COMMENT", "LETTER", "DIGIT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'const'", "'int'", "'void'", "'if'", "'else'", "'while'", "'break'", 
			"'continue'", "'return'", "'+'", "'-'", "'*'", "'/'", "'%'", "'='", "'=='", 
			"'!='", "'<'", "'>'", "'<='", "'>='", "'!'", "'&&'", "'||'", "'('", "')'", 
			"'{'", "'}'", "'['", "']'", "','", "';'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "CONST", "INT", "VOID", "IF", "ELSE", "WHILE", "BREAK", "CONTINUE", 
			"RETURN", "PLUS", "MINUS", "MUL", "DIV", "MOD", "ASSIGN", "EQ", "NEQ", 
			"LT", "GT", "LE", "GE", "NOT", "AND", "OR", "L_PAREN", "R_PAREN", "L_BRACE", 
			"R_BRACE", "L_BRACKT", "R_BRACKT", "COMMA", "SEMICOLON", "IDENT", "INTEGR_CONST", 
			"FLOAT_CONST", "WS", "SL_COMMENT", "ML_COMMENT"
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


	public SysYLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "SysYLexer.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2(\u0160\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4"+
		"\3\4\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3"+
		"\20\3\21\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\25\3"+
		"\26\3\26\3\26\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\33\3"+
		"\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\5\"\u00c9"+
		"\n\"\3\"\3\"\3\"\7\"\u00ce\n\"\f\"\16\"\u00d1\13\"\3#\3#\3#\5#\u00d6\n"+
		"#\3$\3$\3$\7$\u00db\n$\f$\16$\u00de\13$\5$\u00e0\n$\3%\3%\6%\u00e4\n%"+
		"\r%\16%\u00e5\3&\3&\3&\3&\5&\u00ec\n&\3&\3&\6&\u00f0\n&\r&\16&\u00f1\3"+
		"\'\3\'\5\'\u00f6\n\'\3\'\6\'\u00f9\n\'\r\'\16\'\u00fa\3(\3(\5(\u00ff\n"+
		"(\3(\6(\u0102\n(\r(\16(\u0103\3)\3)\3*\6*\u0109\n*\r*\16*\u010a\3*\3*"+
		"\7*\u010f\n*\f*\16*\u0112\13*\3*\5*\u0115\n*\3*\5*\u0118\n*\3*\3*\6*\u011c"+
		"\n*\r*\16*\u011d\3*\5*\u0121\n*\3*\5*\u0124\n*\3*\6*\u0127\n*\r*\16*\u0128"+
		"\3*\3*\5*\u012d\n*\3*\6*\u0130\n*\r*\16*\u0131\3*\3*\3*\5*\u0137\n*\5"+
		"*\u0139\n*\3+\6+\u013c\n+\r+\16+\u013d\3+\3+\3,\3,\3,\3,\7,\u0146\n,\f"+
		",\16,\u0149\13,\3,\3,\3,\3,\3-\3-\3-\3-\7-\u0153\n-\f-\16-\u0156\13-\3"+
		"-\3-\3-\3-\3-\3.\3.\3/\3/\4\u0147\u0154\2\60\3\3\5\4\7\5\t\6\13\7\r\b"+
		"\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26"+
		"+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G\2I\2K\2M\2O\2"+
		"Q\2S%U&W\'Y([\2]\2\3\2\f\3\2\63;\3\2\62;\3\2\629\4\2CHch\4\2GGgg\4\2-"+
		"-//\4\2RRrr\6\2HHNNhhnn\5\2\13\f\17\17\"\"\4\2C\\c|\2\u0178\2\3\3\2\2"+
		"\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3"+
		"\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2"+
		"\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2"+
		"\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2"+
		"\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3"+
		"\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2S\3\2\2\2\2U\3\2\2"+
		"\2\2W\3\2\2\2\2Y\3\2\2\2\3_\3\2\2\2\5e\3\2\2\2\7i\3\2\2\2\tn\3\2\2\2\13"+
		"q\3\2\2\2\rv\3\2\2\2\17|\3\2\2\2\21\u0082\3\2\2\2\23\u008b\3\2\2\2\25"+
		"\u0092\3\2\2\2\27\u0094\3\2\2\2\31\u0096\3\2\2\2\33\u0098\3\2\2\2\35\u009a"+
		"\3\2\2\2\37\u009c\3\2\2\2!\u009e\3\2\2\2#\u00a1\3\2\2\2%\u00a4\3\2\2\2"+
		"\'\u00a6\3\2\2\2)\u00a8\3\2\2\2+\u00ab\3\2\2\2-\u00ae\3\2\2\2/\u00b0\3"+
		"\2\2\2\61\u00b3\3\2\2\2\63\u00b6\3\2\2\2\65\u00b8\3\2\2\2\67\u00ba\3\2"+
		"\2\29\u00bc\3\2\2\2;\u00be\3\2\2\2=\u00c0\3\2\2\2?\u00c2\3\2\2\2A\u00c4"+
		"\3\2\2\2C\u00c8\3\2\2\2E\u00d5\3\2\2\2G\u00df\3\2\2\2I\u00e1\3\2\2\2K"+
		"\u00eb\3\2\2\2M\u00f3\3\2\2\2O\u00fc\3\2\2\2Q\u0105\3\2\2\2S\u0138\3\2"+
		"\2\2U\u013b\3\2\2\2W\u0141\3\2\2\2Y\u014e\3\2\2\2[\u015c\3\2\2\2]\u015e"+
		"\3\2\2\2_`\7e\2\2`a\7q\2\2ab\7p\2\2bc\7u\2\2cd\7v\2\2d\4\3\2\2\2ef\7k"+
		"\2\2fg\7p\2\2gh\7v\2\2h\6\3\2\2\2ij\7x\2\2jk\7q\2\2kl\7k\2\2lm\7f\2\2"+
		"m\b\3\2\2\2no\7k\2\2op\7h\2\2p\n\3\2\2\2qr\7g\2\2rs\7n\2\2st\7u\2\2tu"+
		"\7g\2\2u\f\3\2\2\2vw\7y\2\2wx\7j\2\2xy\7k\2\2yz\7n\2\2z{\7g\2\2{\16\3"+
		"\2\2\2|}\7d\2\2}~\7t\2\2~\177\7g\2\2\177\u0080\7c\2\2\u0080\u0081\7m\2"+
		"\2\u0081\20\3\2\2\2\u0082\u0083\7e\2\2\u0083\u0084\7q\2\2\u0084\u0085"+
		"\7p\2\2\u0085\u0086\7v\2\2\u0086\u0087\7k\2\2\u0087\u0088\7p\2\2\u0088"+
		"\u0089\7w\2\2\u0089\u008a\7g\2\2\u008a\22\3\2\2\2\u008b\u008c\7t\2\2\u008c"+
		"\u008d\7g\2\2\u008d\u008e\7v\2\2\u008e\u008f\7w\2\2\u008f\u0090\7t\2\2"+
		"\u0090\u0091\7p\2\2\u0091\24\3\2\2\2\u0092\u0093\7-\2\2\u0093\26\3\2\2"+
		"\2\u0094\u0095\7/\2\2\u0095\30\3\2\2\2\u0096\u0097\7,\2\2\u0097\32\3\2"+
		"\2\2\u0098\u0099\7\61\2\2\u0099\34\3\2\2\2\u009a\u009b\7\'\2\2\u009b\36"+
		"\3\2\2\2\u009c\u009d\7?\2\2\u009d \3\2\2\2\u009e\u009f\7?\2\2\u009f\u00a0"+
		"\7?\2\2\u00a0\"\3\2\2\2\u00a1\u00a2\7#\2\2\u00a2\u00a3\7?\2\2\u00a3$\3"+
		"\2\2\2\u00a4\u00a5\7>\2\2\u00a5&\3\2\2\2\u00a6\u00a7\7@\2\2\u00a7(\3\2"+
		"\2\2\u00a8\u00a9\7>\2\2\u00a9\u00aa\7?\2\2\u00aa*\3\2\2\2\u00ab\u00ac"+
		"\7@\2\2\u00ac\u00ad\7?\2\2\u00ad,\3\2\2\2\u00ae\u00af\7#\2\2\u00af.\3"+
		"\2\2\2\u00b0\u00b1\7(\2\2\u00b1\u00b2\7(\2\2\u00b2\60\3\2\2\2\u00b3\u00b4"+
		"\7~\2\2\u00b4\u00b5\7~\2\2\u00b5\62\3\2\2\2\u00b6\u00b7\7*\2\2\u00b7\64"+
		"\3\2\2\2\u00b8\u00b9\7+\2\2\u00b9\66\3\2\2\2\u00ba\u00bb\7}\2\2\u00bb"+
		"8\3\2\2\2\u00bc\u00bd\7\177\2\2\u00bd:\3\2\2\2\u00be\u00bf\7]\2\2\u00bf"+
		"<\3\2\2\2\u00c0\u00c1\7_\2\2\u00c1>\3\2\2\2\u00c2\u00c3\7.\2\2\u00c3@"+
		"\3\2\2\2\u00c4\u00c5\7=\2\2\u00c5B\3\2\2\2\u00c6\u00c9\5[.\2\u00c7\u00c9"+
		"\7a\2\2\u00c8\u00c6\3\2\2\2\u00c8\u00c7\3\2\2\2\u00c9\u00cf\3\2\2\2\u00ca"+
		"\u00ce\5[.\2\u00cb\u00ce\5]/\2\u00cc\u00ce\7a\2\2\u00cd\u00ca\3\2\2\2"+
		"\u00cd\u00cb\3\2\2\2\u00cd\u00cc\3\2\2\2\u00ce\u00d1\3\2\2\2\u00cf\u00cd"+
		"\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0D\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d2"+
		"\u00d6\5I%\2\u00d3\u00d6\5K&\2\u00d4\u00d6\5G$\2\u00d5\u00d2\3\2\2\2\u00d5"+
		"\u00d3\3\2\2\2\u00d5\u00d4\3\2\2\2\u00d6F\3\2\2\2\u00d7\u00e0\7\62\2\2"+
		"\u00d8\u00dc\t\2\2\2\u00d9\u00db\t\3\2\2\u00da\u00d9\3\2\2\2\u00db\u00de"+
		"\3\2\2\2\u00dc\u00da\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd\u00e0\3\2\2\2\u00de"+
		"\u00dc\3\2\2\2\u00df\u00d7\3\2\2\2\u00df\u00d8\3\2\2\2\u00e0H\3\2\2\2"+
		"\u00e1\u00e3\7\62\2\2\u00e2\u00e4\t\4\2\2\u00e3\u00e2\3\2\2\2\u00e4\u00e5"+
		"\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6J\3\2\2\2\u00e7"+
		"\u00e8\7\62\2\2\u00e8\u00ec\7z\2\2\u00e9\u00ea\7\62\2\2\u00ea\u00ec\7"+
		"Z\2\2\u00eb\u00e7\3\2\2\2\u00eb\u00e9\3\2\2\2\u00ec\u00ef\3\2\2\2\u00ed"+
		"\u00f0\5]/\2\u00ee\u00f0\t\5\2\2\u00ef\u00ed\3\2\2\2\u00ef\u00ee\3\2\2"+
		"\2\u00f0\u00f1\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2L"+
		"\3\2\2\2\u00f3\u00f5\t\6\2\2\u00f4\u00f6\t\7\2\2\u00f5\u00f4\3\2\2\2\u00f5"+
		"\u00f6\3\2\2\2\u00f6\u00f8\3\2\2\2\u00f7\u00f9\5G$\2\u00f8\u00f7\3\2\2"+
		"\2\u00f9\u00fa\3\2\2\2\u00fa\u00f8\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fbN"+
		"\3\2\2\2\u00fc\u00fe\t\b\2\2\u00fd\u00ff\t\7\2\2\u00fe\u00fd\3\2\2\2\u00fe"+
		"\u00ff\3\2\2\2\u00ff\u0101\3\2\2\2\u0100\u0102\5G$\2\u0101\u0100\3\2\2"+
		"\2\u0102\u0103\3\2\2\2\u0103\u0101\3\2\2\2\u0103\u0104\3\2\2\2\u0104P"+
		"\3\2\2\2\u0105\u0106\t\t\2\2\u0106R\3\2\2\2\u0107\u0109\5G$\2\u0108\u0107"+
		"\3\2\2\2\u0109\u010a\3\2\2\2\u010a\u0108\3\2\2\2\u010a\u010b\3\2\2\2\u010b"+
		"\u010c\3\2\2\2\u010c\u0110\7\60\2\2\u010d\u010f\5G$\2\u010e\u010d\3\2"+
		"\2\2\u010f\u0112\3\2\2\2\u0110\u010e\3\2\2\2\u0110\u0111\3\2\2\2\u0111"+
		"\u0114\3\2\2\2\u0112\u0110\3\2\2\2\u0113\u0115\5M\'\2\u0114\u0113\3\2"+
		"\2\2\u0114\u0115\3\2\2\2\u0115\u0117\3\2\2\2\u0116\u0118\5Q)\2\u0117\u0116"+
		"\3\2\2\2\u0117\u0118\3\2\2\2\u0118\u0139\3\2\2\2\u0119\u011b\7\60\2\2"+
		"\u011a\u011c\5G$\2\u011b\u011a\3\2\2\2\u011c\u011d\3\2\2\2\u011d\u011b"+
		"\3\2\2\2\u011d\u011e\3\2\2\2\u011e\u0120\3\2\2\2\u011f\u0121\5M\'\2\u0120"+
		"\u011f\3\2\2\2\u0120\u0121\3\2\2\2\u0121\u0123\3\2\2\2\u0122\u0124\5Q"+
		")\2\u0123\u0122\3\2\2\2\u0123\u0124\3\2\2\2\u0124\u0139\3\2\2\2\u0125"+
		"\u0127\5G$\2\u0126\u0125\3\2\2\2\u0127\u0128\3\2\2\2\u0128\u0126\3\2\2"+
		"\2\u0128\u0129\3\2\2\2\u0129\u012a\3\2\2\2\u012a\u012c\5O(\2\u012b\u012d"+
		"\5Q)\2\u012c\u012b\3\2\2\2\u012c\u012d\3\2\2\2\u012d\u0139\3\2\2\2\u012e"+
		"\u0130\5G$\2\u012f\u012e\3\2\2\2\u0130\u0131\3\2\2\2\u0131\u012f\3\2\2"+
		"\2\u0131\u0132\3\2\2\2\u0132\u0133\3\2\2\2\u0133\u0134\5M\'\2\u0134\u0136"+
		"\5O(\2\u0135\u0137\5Q)\2\u0136\u0135\3\2\2\2\u0136\u0137\3\2\2\2\u0137"+
		"\u0139\3\2\2\2\u0138\u0108\3\2\2\2\u0138\u0119\3\2\2\2\u0138\u0126\3\2"+
		"\2\2\u0138\u012f\3\2\2\2\u0139T\3\2\2\2\u013a\u013c\t\n\2\2\u013b\u013a"+
		"\3\2\2\2\u013c\u013d\3\2\2\2\u013d\u013b\3\2\2\2\u013d\u013e\3\2\2\2\u013e"+
		"\u013f\3\2\2\2\u013f\u0140\b+\2\2\u0140V\3\2\2\2\u0141\u0142\7\61\2\2"+
		"\u0142\u0143\7\61\2\2\u0143\u0147\3\2\2\2\u0144\u0146\13\2\2\2\u0145\u0144"+
		"\3\2\2\2\u0146\u0149\3\2\2\2\u0147\u0148\3\2\2\2\u0147\u0145\3\2\2\2\u0148"+
		"\u014a\3\2\2\2\u0149\u0147\3\2\2\2\u014a\u014b\7\f\2\2\u014b\u014c\3\2"+
		"\2\2\u014c\u014d\b,\2\2\u014dX\3\2\2\2\u014e\u014f\7\61\2\2\u014f\u0150"+
		"\7,\2\2\u0150\u0154\3\2\2\2\u0151\u0153\13\2\2\2\u0152\u0151\3\2\2\2\u0153"+
		"\u0156\3\2\2\2\u0154\u0155\3\2\2\2\u0154\u0152\3\2\2\2\u0155\u0157\3\2"+
		"\2\2\u0156\u0154\3\2\2\2\u0157\u0158\7,\2\2\u0158\u0159\7\61\2\2\u0159"+
		"\u015a\3\2\2\2\u015a\u015b\b-\2\2\u015bZ\3\2\2\2\u015c\u015d\t\13\2\2"+
		"\u015d\\\3\2\2\2\u015e\u015f\t\3\2\2\u015f^\3\2\2\2 \2\u00c8\u00cd\u00cf"+
		"\u00d5\u00dc\u00df\u00e5\u00eb\u00ef\u00f1\u00f5\u00fa\u00fe\u0103\u010a"+
		"\u0110\u0114\u0117\u011d\u0120\u0123\u0128\u012c\u0131\u0136\u0138\u013d"+
		"\u0147\u0154\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}