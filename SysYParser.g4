parser grammar SysYParser;

program
   : compUnit
   ;

compUnit
   : (funcDef | decl)+ EOF
   ;

decl
   : constDecl
   | varDecl
   ;

constDecl
   : CONST bType constDef (COMMA constDef)* SEMICOLON
   ;

bType
   : INT
   | FLOAT
   ;

constDef
   : IDENT (L_BRACKT constExp R_BRACKT)* ASSIGN constInitVal
   ;

constInitVal
   : constExp
   | L_BRACE (constInitVal (COMMA constInitVal)*)? R_BRACE
   ;

varDecl
   : bType varDef (COMMA varDef)* SEMICOLON
   ;

varDef
   :  IDENT (L_BRACKT constExp R_BRACKT)* (ASSIGN initVal)?
   ;

initVal
   : exp
   | L_BRACE (initVal (COMMA initVal)*)? R_BRACE
   ;

funcDef
   : funcType IDENT L_PAREN funcFParams? R_PAREN block
   ;

funcType
   : VOID
   | INT
   | FLOAT
   ;

funcFParams
   : funcFParam (COMMA funcFParam)*
   ;

funcFParam
   : bType IDENT (L_BRACKT R_BRACKT (L_BRACKT exp R_BRACKT)*)?
   ;

block
   : L_BRACE blockItem* R_BRACE
   ;

blockItem
   : decl
   | stmt
   ;

stmt
   : lVal ASSIGN exp SEMICOLON  #assignStmt
   | exp? SEMICOLON #expStmt
   | block #blockStmt
   | IF L_PAREN cond R_PAREN stmt (ELSE stmt)? #conditionStmt
   | WHILE L_PAREN cond R_PAREN stmt #whileStmt
   | BREAK SEMICOLON #breakStmt
   | CONTINUE SEMICOLON #continueStmt
   | RETURN (exp)? SEMICOLON #returnStmt
   ;

exp:  addExp;

cond: lOrExp;

lVal
   : IDENT (L_BRACKT exp R_BRACKT)*
   ;

primaryExp
    : L_PAREN exp R_PAREN
    | lVal
    | number
    ;

number
   : INTEGER_CONST
   | FLOAT_CONST
   ;

unaryExp
    : primaryExp
    | IDENT L_PAREN (L_BRACKT funcRParams R_BRACKT)? R_PAREN
    | unaryOp unaryExp
    ;

unaryOp
   : PLUS
   | MINUS
   | NOT
   ;

funcRParams
   : funcRparam (COMMA funcRparam)*
   ;

funcRparam
   : exp
   ;

mulExp
    : unaryExp
    | mulExp (MUL | DIV | MOD) unaryExp
    ;

addExp
    : mulExp
    | addExp (PLUS | MINUS) mulExp
    ;

relExp
    : addExp
    | relExp (LT | GT | LE | GE) addExp
    ;

eqExp
    : relExp
    | eqExp (EQ | NEQ) relExp
    ;

lAndExp
    : eqExp
    | lAndExp AND eqExp
    ;

lOrExp
    : lAndExp
    | lOrExp OR lAndExp
    ;

constExp
   : addExp
   ;

