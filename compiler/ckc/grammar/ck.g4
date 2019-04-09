

grammar ck;

//basic CK

file 
    : decl* expr? EOF
    ;

decl
    : 'type' ID '=' ('(' typeParams? ')')? sum                          #typeDecl
    | 'module' ID '=' ('(' typeParams? ')')? '{' decl* '}'              #moduleDecl
    ;

sum
    : product ( '|' product )*
    ;

product
    : ID '(' types? ')'
    ;

expr
    : '{' sequence '}'                                                  #sequenceExpr
    | '(' exprs? ')'                                                    #tupleExpr
    | NATURAL                                                           #naturalExpr
    | TEXT                                                              #textExpr
    | ID                                                                #refExpr
    | expr '(' exprs? ')'                                               #applyExpr
    | <assoc=right> op=( '-' | '!' | '~' ) expr                         #unaryExpr
    | lhs=expr op=( '*' | '/' | '%' ) rhs=expr                          #multExpr
    | lhs=expr op=( '+' | '-' ) rhs=expr                                #addExpr
    | lhs=expr op=( '<<' | '>>' ) rhs=expr                              #shiftExpr
    | lhs=expr op=( '<' | '<=' | '>' | '>=' | '<>' ) rhs=expr           #relExpr
    | lhs=expr op=( '==' | '!=' ) rhs=expr                              #equalityExpr
    | lhs=expr '&' rhs=expr                                             #bitAndExpr
    | lhs=expr '^' rhs=expr                                             #bitXorExpr
    | lhs=expr '|' rhs=expr                                             #bitOrExpr
    | lhs=expr '&&' rhs=expr                                            #andExpr
    | lhs=expr '||' rhs=expr                                            #orExpr
    | <assoc=right> cond=expr '?' con=expr ':' alt=expr                 #condExpr
    | <assoc=right> target=expr '=' value=expr                          #assignExpr
    | '(' params? ')' (':' type)? expr                                  #funExpr
    | 'cfun' ID type                                                    #cFunExpr
    | 'let' binding                                                     #letExpr
    | 'let' 'rec' binding ('and' binding)*                              #letRecExpr
    | 'if' '(' cond=expr ')' csq=expr ( 'else' alt=expr )?              #ifExpr
    ;

binding
    : ID (':' type)? '=' expr
    ;

exprs
    : expr (',' expr)*
    ;

sequence
    : expr ';' sequence
    | expr
    ;

param
    : ID (':' type)?
    ;

params
    : param ( ',' param )*
    ;

type
    : ID                        #simpleType
    | '(' types? ')' type       #funType
    | '(' types? ')'            #tupleType
    | ID '(' types ')'          #ctorType
    ;

typeParams
    : ID ( ',' ID )*
    ;

types
    : type ( ',' type )*
    ;

NATURAL : ('0'..'9')+ ;

TEXT
    :  '"' ( EscapeSequence | ~('\\'|'"') )* '"'
    ;

fragment
EscapeSequence
    : '\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\\')
    | HexEscape
    | UnicodeEscape
    ;

fragment
HexDigit : ('0'..'9'|'a'..'f'|'A'..'F') ;

fragment
HexEscape : '\\' 'x' HexDigit HexDigit ;

fragment
UnicodeEscape
    : '\\' 'u' HexDigit HexDigit HexDigit HexDigit
    | '\\' 'u' HexDigit HexDigit HexDigit HexDigit HexDigit HexDigit HexDigit HexDigit
    ;

ID
    : ('_'|'a'..'z'|'A'..'Z') ('_'|'0'..'9'|'a'..'z'|'A'..'Z')*
    | '`' ~('`')+ '`'
    ;

WHITESPACE : (' '|'\t'|'\u000C'|'\n'|'\r') -> skip ;
COMMENT : '//' ~('\n'|'\r')* '\r'? '\n' -> skip ;
