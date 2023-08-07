lexer grammar SysYLexer;

CONST : 'const';
INT : 'int';
FLOAT : 'float';
VOID : 'void';
IF : 'if';
ELSE : 'else';
WHILE : 'while';
BREAK : 'break';
CONTINUE : 'continue';
RETURN : 'return';

PLUS : '+';
MINUS : '-';
MUL : '*';
DIV : '/';
MOD : '%';
ASSIGN : '=';
EQ : '==';
NEQ : '!=';
LT : '<';
GT : '>';
LE : '<=';
GE : '>=';
NOT : '!';

AND : '&&';
OR : '||';

L_PAREN : '(';
R_PAREN : ')';
L_BRACE : '{';
R_BRACE : '}';
L_BRACKT : '[';
R_BRACKT : ']';
COMMA : ',';
SEMICOLON : ';';

IDENT : (LETTER | '_') (LETTER | DIGIT | '_')* ;

fragment
DECIMAL_CONST : '0' | [1-9] [0-9]*;

fragment
OCTAL_CONST : '0' [0-7]+;

fragment
HEX_CONST : ('0x' | '0X') (DIGIT | [a-fA-F])+ ;

INTEGER_CONST : (DECIMAL_CONST |
                OCTAL_CONST |
                HEX_CONST)
                ;

FLOAT_CONST : DecimalFloatingConstant | HexadecimalFloatingConstant;

DecimalFloatingConstant
    :   FractionalConstant ExponentPart?
    |   DigitSequence ExponentPart
    ;

HexadecimalFloatingConstant
    :   HexadecimalPrefix HexadecimalFractionalConstant BinaryExponentPart
    |   HexadecimalPrefix HexadecimalDigitSequence BinaryExponentPart
    ;

fragment
FractionalConstant
    :   DigitSequence? '.' DigitSequence
    |   DigitSequence '.'
    ;

fragment
ExponentPart
    :   'e' Sign? DigitSequence
    |   'E' Sign? DigitSequence
    ;

fragment
Sign
    :   '+'
    |   '-'
    ;

fragment DigitSequence: DIGIT+;

fragment
HexadecimalDigit
    :   [0-9a-fA-F]
    ;

fragment
HexadecimalPrefix
    :   '0x'
    |   '0X'
    ;

fragment
HexadecimalFractionalConstant
    :   HexadecimalDigitSequence? '.' HexadecimalDigitSequence
    |   HexadecimalDigitSequence '.'
    ;

fragment
HexadecimalDigitSequence
    :   HexadecimalDigit+
    ;

fragment
BinaryExponentPart
    :   'P' Sign? DigitSequence
    |   'p' Sign? DigitSequence
    ;

WS : [ \t\r\n]+ -> skip;
SL_COMMENT : '//' .*? '\n' -> skip;
ML_COMMENT : '/*' .*? '*/' -> skip;
fragment LETTER : [a-zA-Z];
fragment DIGIT : [0-9];