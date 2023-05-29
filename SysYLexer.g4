lexer grammar SysYLexer;

CONST : 'const';
INT : 'int';
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

DECIMAL_CONST : '0' | [1-9] [0-9]*;
OCTAL_CONST : '0' [0-7]+;
HEX_CONST : ('0x' | '0X') (DIGIT | [a-fA-F])+ ;

FLOAT_CONST : DECIMAL_CONST+ '.' DECIMAL_CONST* EXPONENT? FLOAT_SUFFIX?
            | '.' DECIMAL_CONST+EXPONENT? FLOAT_SUFFIX? 
            | DECIMAL_CONST+ BINARY_EXPONENT FLOAT_SUFFIX?
            | DECIMAL_CONST+ EXPONENT BINARY_EXPONENT FLOAT_SUFFIX?
            | HEX_FLOAT_CONST
            ;

fragment HEX_DIGIT : [0-9a-fA-F];
FLOAT_SUFFIX : [fFlL];
EXPONENT : [eE] [+-]? DECIMAL_CONST+;
BINARY_EXPONENT : [pP] [+-]? DECIMAL_CONST+;

HEX_FLOAT_CONST : HEX_PREFIX HEX_MANTISSA HEX_EXPONENT FLOAT_SUFFIX?;
HEX_PREFIX : '0' [xX];
HEX_MANTISSA : HEX_DIGIT+ '.' HEX_DIGIT* | '.' HEX_DIGIT+;
HEX_EXPONENT : HEX_FLOAT_EXPONENT_INDICATOR HEX_SIGNED_INTEGER;
HEX_FLOAT_EXPONENT_INDICATOR : [pP];
HEX_SIGNED_INTEGER : [+-]? HEX_DIGIT+;

WS : [ \t\r\n]+ -> skip;
SL_COMMENT : '//' .*? '\n' -> skip;
ML_COMMENT : '/*' .*? '*/' -> skip;
fragment LETTER : [a-zA-Z];
fragment DIGIT : [0-9];