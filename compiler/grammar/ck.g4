
grammar ck;

//basic CK

file 
    : NL* statements? NL* EOF
    ;

statement
    : NL* '{' statements? NL* '}'                                                                                   #blockStatement
    | NL* 'let' NL* ID NL* '=' expr                                                                                 #letStatement
    | NL* 'do' statement NL* 'while' NL* '(' NL* expr NL* ')'                                                       #doStatement
    | NL* 'while' NL* '(' expr NL* ')' statement                                                                    #whileStatement
    | NL* 'for' NL* '(' statement? NL* ';' expr NL* ';' expr? NL* ')' statement                                     #forStatement
    | NL* 'if' NL* '(' expr NL* ')' statement NL* ( NL* 'else' statement )                                          #ifStatement
    | NL* 'match' NL* '(' expr NL* ')' ( NL* 'with' expr NL* ':' statement )* ( NL* 'default' NL* ':' statement )?  #matchStatement
    | NL* 'return' expr?                                                                                            #returnStatement
    | expr                                                                                                          #exprStatement
    ;

statements
    : statement ';'?
    | statement NL*(';'|NL+) statements ;

expr
    : NL* NATURAL                                                       #naturalExpr
    | NL* '(' expr NL* ')'                                              #subExpr
    | NL* ID                                                            #refExpr
    | expr '(' exprs? NL* ')'                                           #applyExpr
    | <assoc=right> ( '-' | '!' | '~' ) expr                            #unaryExpr
    | expr ( '*' | '/' | '%' ) expr                                     #multExpr
    | expr ( '+' | '-' ) expr                                           #addExpr
    | expr ( '<<' | '>>' ) expr                                         #shiftExpr
    | expr ( '<' | '<=' | '>' | '>=' | '<>' ) expr                      #relExpr
    | expr ( '==' | '!=' ) expr                                         #equalityExpr
    | expr '&' expr                                                     #bitAndExpr
    | expr '^' expr                                                     #bitXorExpr
    | expr '|' expr                                                     #bitOrExpr
    | expr '&&' expr                                                    #andExpr
    | expr '||' expr                                                    #orExpr
    | <assoc=right> expr '?' expr ':' expr                              #condExpr
    | <assoc=right> expr '=' expr                                       #assignExpr
    | NL* '\\' NL* '(' params? NL* ')' NL* ':' NL* TYPEID statement     #funExpr
    | NL* 'let' NL* ID NL* '=' expr NL* 'in' expr                       #letExpr
    ;

exprs : expr ( NL* ',' expr )* ;

param
    : NL* ID NL* ':' NL* TYPEID
    ;

params : param ( NL* ',' param )* ;

//others
NATURAL : ('0'..'9')+ ;

ID : ('_'|'a'..'z') ('_'|'0'..'9'|'a'..'z'|'A'..'Z')* ;
TYPEID : ('A'..'Z') ('_'|'0'..'9'|'a'..'z'|'A'..'Z')* ;

NL : '\n' | '\r' '\n' ;

WHITESPACE : (' '|'\t'|'\u000C') -> skip ;
COMMENT : '//' ~('\n'|'\r')* '\r'? '\n' -> skip ;
