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
		CONST=1, INT=2, FLOAT=3, VOID=4, IF=5, ELSE=6, WHILE=7, BREAK=8, CONTINUE=9, 
		RETURN=10, PLUS=11, MINUS=12, MUL=13, DIV=14, MOD=15, ASSIGN=16, EQ=17, 
		NEQ=18, LT=19, GT=20, LE=21, GE=22, NOT=23, AND=24, OR=25, L_PAREN=26, 
		R_PAREN=27, L_BRACE=28, R_BRACE=29, L_BRACKT=30, R_BRACKT=31, COMMA=32, 
		SEMICOLON=33, IDENT=34, DECIMAL_CONST=35, OCTAL_CONST=36, HEX_CONST=37, 
		FLOAT_CONST=38, FLOAT_SUFFIX=39, EXPONENT=40, BINARY_EXPONENT=41, HEX_FLOAT_CONST=42, 
		HEX_PREFIX=43, HEX_MANTISSA=44, HEX_EXPONENT=45, HEX_FLOAT_EXPONENT_INDICATOR=46, 
		HEX_SIGNED_INTEGER=47, WS=48, SL_COMMENT=49, ML_COMMENT=50;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"CONST", "INT", "FLOAT", "VOID", "IF", "ELSE", "WHILE", "BREAK", "CONTINUE", 
			"RETURN", "PLUS", "MINUS", "MUL", "DIV", "MOD", "ASSIGN", "EQ", "NEQ", 
			"LT", "GT", "LE", "GE", "NOT", "AND", "OR", "L_PAREN", "R_PAREN", "L_BRACE", 
			"R_BRACE", "L_BRACKT", "R_BRACKT", "COMMA", "SEMICOLON", "IDENT", "DECIMAL_CONST", 
			"OCTAL_CONST", "HEX_CONST", "FLOAT_CONST", "HEX_DIGIT", "FLOAT_SUFFIX", 
			"EXPONENT", "BINARY_EXPONENT", "HEX_FLOAT_CONST", "HEX_PREFIX", "HEX_MANTISSA", 
			"HEX_EXPONENT", "HEX_FLOAT_EXPONENT_INDICATOR", "HEX_SIGNED_INTEGER", 
			"WS", "SL_COMMENT", "ML_COMMENT", "LETTER", "DIGIT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'const'", "'int'", "'float'", "'void'", "'if'", "'else'", "'while'", 
			"'break'", "'continue'", "'return'", "'+'", "'-'", "'*'", "'/'", "'%'", 
			"'='", "'=='", "'!='", "'<'", "'>'", "'<='", "'>='", "'!'", "'&&'", "'||'", 
			"'('", "')'", "'{'", "'}'", "'['", "']'", "','", "';'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "CONST", "INT", "FLOAT", "VOID", "IF", "ELSE", "WHILE", "BREAK", 
			"CONTINUE", "RETURN", "PLUS", "MINUS", "MUL", "DIV", "MOD", "ASSIGN", 
			"EQ", "NEQ", "LT", "GT", "LE", "GE", "NOT", "AND", "OR", "L_PAREN", "R_PAREN", 
			"L_BRACE", "R_BRACE", "L_BRACKT", "R_BRACKT", "COMMA", "SEMICOLON", "IDENT", 
			"DECIMAL_CONST", "OCTAL_CONST", "HEX_CONST", "FLOAT_CONST", "FLOAT_SUFFIX", 
			"EXPONENT", "BINARY_EXPONENT", "HEX_FLOAT_CONST", "HEX_PREFIX", "HEX_MANTISSA", 
			"HEX_EXPONENT", "HEX_FLOAT_EXPONENT_INDICATOR", "HEX_SIGNED_INTEGER", 
			"WS", "SL_COMMENT", "ML_COMMENT"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\64\u019c\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\4\66\t\66\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3"+
		"\7\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3"+
		"\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3"+
		"\24\3\24\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\31\3\31\3"+
		"\31\3\32\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3"+
		" \3 \3!\3!\3\"\3\"\3#\3#\5#\u00dd\n#\3#\3#\3#\7#\u00e2\n#\f#\16#\u00e5"+
		"\13#\3$\3$\3$\7$\u00ea\n$\f$\16$\u00ed\13$\5$\u00ef\n$\3%\3%\6%\u00f3"+
		"\n%\r%\16%\u00f4\3&\3&\3&\3&\5&\u00fb\n&\3&\3&\6&\u00ff\n&\r&\16&\u0100"+
		"\3\'\6\'\u0104\n\'\r\'\16\'\u0105\3\'\3\'\7\'\u010a\n\'\f\'\16\'\u010d"+
		"\13\'\3\'\5\'\u0110\n\'\3\'\5\'\u0113\n\'\3\'\3\'\6\'\u0117\n\'\r\'\16"+
		"\'\u0118\3\'\5\'\u011c\n\'\3\'\5\'\u011f\n\'\3\'\6\'\u0122\n\'\r\'\16"+
		"\'\u0123\3\'\3\'\5\'\u0128\n\'\3\'\6\'\u012b\n\'\r\'\16\'\u012c\3\'\3"+
		"\'\3\'\5\'\u0132\n\'\3\'\5\'\u0135\n\'\3(\3(\3)\3)\3*\3*\5*\u013d\n*\3"+
		"*\6*\u0140\n*\r*\16*\u0141\3+\3+\5+\u0146\n+\3+\6+\u0149\n+\r+\16+\u014a"+
		"\3,\3,\3,\3,\5,\u0151\n,\3-\3-\3-\3.\6.\u0157\n.\r.\16.\u0158\3.\3.\7"+
		".\u015d\n.\f.\16.\u0160\13.\3.\3.\6.\u0164\n.\r.\16.\u0165\5.\u0168\n"+
		".\3/\3/\3/\3\60\3\60\3\61\5\61\u0170\n\61\3\61\6\61\u0173\n\61\r\61\16"+
		"\61\u0174\3\62\6\62\u0178\n\62\r\62\16\62\u0179\3\62\3\62\3\63\3\63\3"+
		"\63\3\63\7\63\u0182\n\63\f\63\16\63\u0185\13\63\3\63\3\63\3\63\3\63\3"+
		"\64\3\64\3\64\3\64\7\64\u018f\n\64\f\64\16\64\u0192\13\64\3\64\3\64\3"+
		"\64\3\64\3\64\3\65\3\65\3\66\3\66\4\u0183\u0190\2\67\3\3\5\4\7\5\t\6\13"+
		"\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'"+
		"\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'"+
		"M(O\2Q)S*U+W,Y-[.]/_\60a\61c\62e\63g\64i\2k\2\3\2\16\3\2\63;\3\2\62;\3"+
		"\2\629\4\2CHch\5\2\62;CHch\6\2HHNNhhnn\4\2GGgg\4\2--//\4\2RRrr\4\2ZZz"+
		"z\5\2\13\f\17\17\"\"\4\2C\\c|\2\u01bf\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2"+
		"\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2"+
		"\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3"+
		"\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3"+
		"\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65"+
		"\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3"+
		"\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2"+
		"\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2"+
		"]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\3m\3"+
		"\2\2\2\5s\3\2\2\2\7w\3\2\2\2\t}\3\2\2\2\13\u0082\3\2\2\2\r\u0085\3\2\2"+
		"\2\17\u008a\3\2\2\2\21\u0090\3\2\2\2\23\u0096\3\2\2\2\25\u009f\3\2\2\2"+
		"\27\u00a6\3\2\2\2\31\u00a8\3\2\2\2\33\u00aa\3\2\2\2\35\u00ac\3\2\2\2\37"+
		"\u00ae\3\2\2\2!\u00b0\3\2\2\2#\u00b2\3\2\2\2%\u00b5\3\2\2\2\'\u00b8\3"+
		"\2\2\2)\u00ba\3\2\2\2+\u00bc\3\2\2\2-\u00bf\3\2\2\2/\u00c2\3\2\2\2\61"+
		"\u00c4\3\2\2\2\63\u00c7\3\2\2\2\65\u00ca\3\2\2\2\67\u00cc\3\2\2\29\u00ce"+
		"\3\2\2\2;\u00d0\3\2\2\2=\u00d2\3\2\2\2?\u00d4\3\2\2\2A\u00d6\3\2\2\2C"+
		"\u00d8\3\2\2\2E\u00dc\3\2\2\2G\u00ee\3\2\2\2I\u00f0\3\2\2\2K\u00fa\3\2"+
		"\2\2M\u0134\3\2\2\2O\u0136\3\2\2\2Q\u0138\3\2\2\2S\u013a\3\2\2\2U\u0143"+
		"\3\2\2\2W\u014c\3\2\2\2Y\u0152\3\2\2\2[\u0167\3\2\2\2]\u0169\3\2\2\2_"+
		"\u016c\3\2\2\2a\u016f\3\2\2\2c\u0177\3\2\2\2e\u017d\3\2\2\2g\u018a\3\2"+
		"\2\2i\u0198\3\2\2\2k\u019a\3\2\2\2mn\7e\2\2no\7q\2\2op\7p\2\2pq\7u\2\2"+
		"qr\7v\2\2r\4\3\2\2\2st\7k\2\2tu\7p\2\2uv\7v\2\2v\6\3\2\2\2wx\7h\2\2xy"+
		"\7n\2\2yz\7q\2\2z{\7c\2\2{|\7v\2\2|\b\3\2\2\2}~\7x\2\2~\177\7q\2\2\177"+
		"\u0080\7k\2\2\u0080\u0081\7f\2\2\u0081\n\3\2\2\2\u0082\u0083\7k\2\2\u0083"+
		"\u0084\7h\2\2\u0084\f\3\2\2\2\u0085\u0086\7g\2\2\u0086\u0087\7n\2\2\u0087"+
		"\u0088\7u\2\2\u0088\u0089\7g\2\2\u0089\16\3\2\2\2\u008a\u008b\7y\2\2\u008b"+
		"\u008c\7j\2\2\u008c\u008d\7k\2\2\u008d\u008e\7n\2\2\u008e\u008f\7g\2\2"+
		"\u008f\20\3\2\2\2\u0090\u0091\7d\2\2\u0091\u0092\7t\2\2\u0092\u0093\7"+
		"g\2\2\u0093\u0094\7c\2\2\u0094\u0095\7m\2\2\u0095\22\3\2\2\2\u0096\u0097"+
		"\7e\2\2\u0097\u0098\7q\2\2\u0098\u0099\7p\2\2\u0099\u009a\7v\2\2\u009a"+
		"\u009b\7k\2\2\u009b\u009c\7p\2\2\u009c\u009d\7w\2\2\u009d\u009e\7g\2\2"+
		"\u009e\24\3\2\2\2\u009f\u00a0\7t\2\2\u00a0\u00a1\7g\2\2\u00a1\u00a2\7"+
		"v\2\2\u00a2\u00a3\7w\2\2\u00a3\u00a4\7t\2\2\u00a4\u00a5\7p\2\2\u00a5\26"+
		"\3\2\2\2\u00a6\u00a7\7-\2\2\u00a7\30\3\2\2\2\u00a8\u00a9\7/\2\2\u00a9"+
		"\32\3\2\2\2\u00aa\u00ab\7,\2\2\u00ab\34\3\2\2\2\u00ac\u00ad\7\61\2\2\u00ad"+
		"\36\3\2\2\2\u00ae\u00af\7\'\2\2\u00af \3\2\2\2\u00b0\u00b1\7?\2\2\u00b1"+
		"\"\3\2\2\2\u00b2\u00b3\7?\2\2\u00b3\u00b4\7?\2\2\u00b4$\3\2\2\2\u00b5"+
		"\u00b6\7#\2\2\u00b6\u00b7\7?\2\2\u00b7&\3\2\2\2\u00b8\u00b9\7>\2\2\u00b9"+
		"(\3\2\2\2\u00ba\u00bb\7@\2\2\u00bb*\3\2\2\2\u00bc\u00bd\7>\2\2\u00bd\u00be"+
		"\7?\2\2\u00be,\3\2\2\2\u00bf\u00c0\7@\2\2\u00c0\u00c1\7?\2\2\u00c1.\3"+
		"\2\2\2\u00c2\u00c3\7#\2\2\u00c3\60\3\2\2\2\u00c4\u00c5\7(\2\2\u00c5\u00c6"+
		"\7(\2\2\u00c6\62\3\2\2\2\u00c7\u00c8\7~\2\2\u00c8\u00c9\7~\2\2\u00c9\64"+
		"\3\2\2\2\u00ca\u00cb\7*\2\2\u00cb\66\3\2\2\2\u00cc\u00cd\7+\2\2\u00cd"+
		"8\3\2\2\2\u00ce\u00cf\7}\2\2\u00cf:\3\2\2\2\u00d0\u00d1\7\177\2\2\u00d1"+
		"<\3\2\2\2\u00d2\u00d3\7]\2\2\u00d3>\3\2\2\2\u00d4\u00d5\7_\2\2\u00d5@"+
		"\3\2\2\2\u00d6\u00d7\7.\2\2\u00d7B\3\2\2\2\u00d8\u00d9\7=\2\2\u00d9D\3"+
		"\2\2\2\u00da\u00dd\5i\65\2\u00db\u00dd\7a\2\2\u00dc\u00da\3\2\2\2\u00dc"+
		"\u00db\3\2\2\2\u00dd\u00e3\3\2\2\2\u00de\u00e2\5i\65\2\u00df\u00e2\5k"+
		"\66\2\u00e0\u00e2\7a\2\2\u00e1\u00de\3\2\2\2\u00e1\u00df\3\2\2\2\u00e1"+
		"\u00e0\3\2\2\2\u00e2\u00e5\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e3\u00e4\3\2"+
		"\2\2\u00e4F\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e6\u00ef\7\62\2\2\u00e7\u00eb"+
		"\t\2\2\2\u00e8\u00ea\t\3\2\2\u00e9\u00e8\3\2\2\2\u00ea\u00ed\3\2\2\2\u00eb"+
		"\u00e9\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ec\u00ef\3\2\2\2\u00ed\u00eb\3\2"+
		"\2\2\u00ee\u00e6\3\2\2\2\u00ee\u00e7\3\2\2\2\u00efH\3\2\2\2\u00f0\u00f2"+
		"\7\62\2\2\u00f1\u00f3\t\4\2\2\u00f2\u00f1\3\2\2\2\u00f3\u00f4\3\2\2\2"+
		"\u00f4\u00f2\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5J\3\2\2\2\u00f6\u00f7\7"+
		"\62\2\2\u00f7\u00fb\7z\2\2\u00f8\u00f9\7\62\2\2\u00f9\u00fb\7Z\2\2\u00fa"+
		"\u00f6\3\2\2\2\u00fa\u00f8\3\2\2\2\u00fb\u00fe\3\2\2\2\u00fc\u00ff\5k"+
		"\66\2\u00fd\u00ff\t\5\2\2\u00fe\u00fc\3\2\2\2\u00fe\u00fd\3\2\2\2\u00ff"+
		"\u0100\3\2\2\2\u0100\u00fe\3\2\2\2\u0100\u0101\3\2\2\2\u0101L\3\2\2\2"+
		"\u0102\u0104\5G$\2\u0103\u0102\3\2\2\2\u0104\u0105\3\2\2\2\u0105\u0103"+
		"\3\2\2\2\u0105\u0106\3\2\2\2\u0106\u0107\3\2\2\2\u0107\u010b\7\60\2\2"+
		"\u0108\u010a\5G$\2\u0109\u0108\3\2\2\2\u010a\u010d\3\2\2\2\u010b\u0109"+
		"\3\2\2\2\u010b\u010c\3\2\2\2\u010c\u010f\3\2\2\2\u010d\u010b\3\2\2\2\u010e"+
		"\u0110\5S*\2\u010f\u010e\3\2\2\2\u010f\u0110\3\2\2\2\u0110\u0112\3\2\2"+
		"\2\u0111\u0113\5Q)\2\u0112\u0111\3\2\2\2\u0112\u0113\3\2\2\2\u0113\u0135"+
		"\3\2\2\2\u0114\u0116\7\60\2\2\u0115\u0117\5G$\2\u0116\u0115\3\2\2\2\u0117"+
		"\u0118\3\2\2\2\u0118\u0116\3\2\2\2\u0118\u0119\3\2\2\2\u0119\u011b\3\2"+
		"\2\2\u011a\u011c\5S*\2\u011b\u011a\3\2\2\2\u011b\u011c\3\2\2\2\u011c\u011e"+
		"\3\2\2\2\u011d\u011f\5Q)\2\u011e\u011d\3\2\2\2\u011e\u011f\3\2\2\2\u011f"+
		"\u0135\3\2\2\2\u0120\u0122\5G$\2\u0121\u0120\3\2\2\2\u0122\u0123\3\2\2"+
		"\2\u0123\u0121\3\2\2\2\u0123\u0124\3\2\2\2\u0124\u0125\3\2\2\2\u0125\u0127"+
		"\5U+\2\u0126\u0128\5Q)\2\u0127\u0126\3\2\2\2\u0127\u0128\3\2\2\2\u0128"+
		"\u0135\3\2\2\2\u0129\u012b\5G$\2\u012a\u0129\3\2\2\2\u012b\u012c\3\2\2"+
		"\2\u012c\u012a\3\2\2\2\u012c\u012d\3\2\2\2\u012d\u012e\3\2\2\2\u012e\u012f"+
		"\5S*\2\u012f\u0131\5U+\2\u0130\u0132\5Q)\2\u0131\u0130\3\2\2\2\u0131\u0132"+
		"\3\2\2\2\u0132\u0135\3\2\2\2\u0133\u0135\5W,\2\u0134\u0103\3\2\2\2\u0134"+
		"\u0114\3\2\2\2\u0134\u0121\3\2\2\2\u0134\u012a\3\2\2\2\u0134\u0133\3\2"+
		"\2\2\u0135N\3\2\2\2\u0136\u0137\t\6\2\2\u0137P\3\2\2\2\u0138\u0139\t\7"+
		"\2\2\u0139R\3\2\2\2\u013a\u013c\t\b\2\2\u013b\u013d\t\t\2\2\u013c\u013b"+
		"\3\2\2\2\u013c\u013d\3\2\2\2\u013d\u013f\3\2\2\2\u013e\u0140\5G$\2\u013f"+
		"\u013e\3\2\2\2\u0140\u0141\3\2\2\2\u0141\u013f\3\2\2\2\u0141\u0142\3\2"+
		"\2\2\u0142T\3\2\2\2\u0143\u0145\t\n\2\2\u0144\u0146\t\t\2\2\u0145\u0144"+
		"\3\2\2\2\u0145\u0146\3\2\2\2\u0146\u0148\3\2\2\2\u0147\u0149\5G$\2\u0148"+
		"\u0147\3\2\2\2\u0149\u014a\3\2\2\2\u014a\u0148\3\2\2\2\u014a\u014b\3\2"+
		"\2\2\u014bV\3\2\2\2\u014c\u014d\5Y-\2\u014d\u014e\5[.\2\u014e\u0150\5"+
		"]/\2\u014f\u0151\5Q)\2\u0150\u014f\3\2\2\2\u0150\u0151\3\2\2\2\u0151X"+
		"\3\2\2\2\u0152\u0153\7\62\2\2\u0153\u0154\t\13\2\2\u0154Z\3\2\2\2\u0155"+
		"\u0157\5O(\2\u0156\u0155\3\2\2\2\u0157\u0158\3\2\2\2\u0158\u0156\3\2\2"+
		"\2\u0158\u0159\3\2\2\2\u0159\u015a\3\2\2\2\u015a\u015e\7\60\2\2\u015b"+
		"\u015d\5O(\2\u015c\u015b\3\2\2\2\u015d\u0160\3\2\2\2\u015e\u015c\3\2\2"+
		"\2\u015e\u015f\3\2\2\2\u015f\u0168\3\2\2\2\u0160\u015e\3\2\2\2\u0161\u0163"+
		"\7\60\2\2\u0162\u0164\5O(\2\u0163\u0162\3\2\2\2\u0164\u0165\3\2\2\2\u0165"+
		"\u0163\3\2\2\2\u0165\u0166\3\2\2\2\u0166\u0168\3\2\2\2\u0167\u0156\3\2"+
		"\2\2\u0167\u0161\3\2\2\2\u0168\\\3\2\2\2\u0169\u016a\5_\60\2\u016a\u016b"+
		"\5a\61\2\u016b^\3\2\2\2\u016c\u016d\t\n\2\2\u016d`\3\2\2\2\u016e\u0170"+
		"\t\t\2\2\u016f\u016e\3\2\2\2\u016f\u0170\3\2\2\2\u0170\u0172\3\2\2\2\u0171"+
		"\u0173\5O(\2\u0172\u0171\3\2\2\2\u0173\u0174\3\2\2\2\u0174\u0172\3\2\2"+
		"\2\u0174\u0175\3\2\2\2\u0175b\3\2\2\2\u0176\u0178\t\f\2\2\u0177\u0176"+
		"\3\2\2\2\u0178\u0179\3\2\2\2\u0179\u0177\3\2\2\2\u0179\u017a\3\2\2\2\u017a"+
		"\u017b\3\2\2\2\u017b\u017c\b\62\2\2\u017cd\3\2\2\2\u017d\u017e\7\61\2"+
		"\2\u017e\u017f\7\61\2\2\u017f\u0183\3\2\2\2\u0180\u0182\13\2\2\2\u0181"+
		"\u0180\3\2\2\2\u0182\u0185\3\2\2\2\u0183\u0184\3\2\2\2\u0183\u0181\3\2"+
		"\2\2\u0184\u0186\3\2\2\2\u0185\u0183\3\2\2\2\u0186\u0187\7\f\2\2\u0187"+
		"\u0188\3\2\2\2\u0188\u0189\b\63\2\2\u0189f\3\2\2\2\u018a\u018b\7\61\2"+
		"\2\u018b\u018c\7,\2\2\u018c\u0190\3\2\2\2\u018d\u018f\13\2\2\2\u018e\u018d"+
		"\3\2\2\2\u018f\u0192\3\2\2\2\u0190\u0191\3\2\2\2\u0190\u018e\3\2\2\2\u0191"+
		"\u0193\3\2\2\2\u0192\u0190\3\2\2\2\u0193\u0194\7,\2\2\u0194\u0195\7\61"+
		"\2\2\u0195\u0196\3\2\2\2\u0196\u0197\b\64\2\2\u0197h\3\2\2\2\u0198\u0199"+
		"\t\r\2\2\u0199j\3\2\2\2\u019a\u019b\t\3\2\2\u019bl\3\2\2\2&\2\u00dc\u00e1"+
		"\u00e3\u00eb\u00ee\u00f4\u00fa\u00fe\u0100\u0105\u010b\u010f\u0112\u0118"+
		"\u011b\u011e\u0123\u0127\u012c\u0131\u0134\u013c\u0141\u0145\u014a\u0150"+
		"\u0158\u015e\u0165\u0167\u016f\u0174\u0179\u0183\u0190\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}