parser grammar SysYParser;

options {
    tokenVocab = SysYLexer;
}

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
   : IDENT (L_BRACKT constExp R_BRACKT)* (ASSIGN initVal)?
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

exp
   : L_PAREN exp R_PAREN #expParenthesis
   | lVal #lvalExp
   | number #numberExp
   | IDENT L_PAREN funcRParams? R_PAREN #callFuncExp
   | unaryOp exp #unaryOpExp
   | exp (MUL | DIV | MOD) exp #mulExp
   | exp (PLUS | MINUS) exp #plusExp
   ;

cond
   : exp #expCond
   | cond (LT | GT | LE | GE) cond #ltCond
   | cond (EQ | NEQ) cond #eqCond
   | cond AND cond #andCond
   | cond OR cond #orCond
   ;

lVal
   : IDENT (L_BRACKT exp R_BRACKT)*
   ;

number
   : INTEGER_CONST
   | FLOAT_CONST
   ;

unaryOp
   : PLUS
   | MINUS
   | NOT
   ;

funcRParams
   : param (COMMA param)*
   ;

param
   : exp
   ;

constExp
   : exp
   ;