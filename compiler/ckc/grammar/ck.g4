

grammar ck;

//basic CK

file 
    : decl* expr? EOF
    ;

decl
    : 'decl'
    ;

expr
    : '{' sequence '}'                                                  #sequenceExpr
    | '(' ')'                                                           #unitExpr
    | NATURAL                                                           #naturalExpr
    | ID                                                                #refExpr
    | expr '(' args? ')'                                                #applyExpr
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
    | '(' params? ')' ':' type? expr                                    #funExpr
    | 'cfun' ID funType                                                 #cFunExpr
    | 'let' ID '=' value=expr                                           #letExpr
    | 'let' 'rec' ID '=' expr ('and' ID '=' expr)*                      #letRecExpr
    | 'if' '(' cond=expr ')' csq=expr ( 'else' alt=expr )?              #ifExpr
    ;

args
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
    : simpleType
    | funType
    ;

simpleType
    : TYPEID
    ;

funType
    : '(' types? ')' ':' type
    ;

types
    : type ( ',' type )*
    ;

NATURAL : ('0'..'9')+ ;

ID : ('_'|'a'..'z') ('_'|'0'..'9'|'a'..'z'|'A'..'Z')* ;
TYPEID : ('A'..'Z') ('_'|'0'..'9'|'a'..'z'|'A'..'Z')* ;

WHITESPACE : (' '|'\t'|'\u000C'|'\n'|'\r') -> skip ;
COMMENT : '//' ~('\n'|'\r')* '\r'? '\n' -> skip ;
