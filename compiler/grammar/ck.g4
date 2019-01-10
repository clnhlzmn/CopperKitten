

grammar ck;

//basic CK

file 
    : statements? EOF
    ;

statement
    : '{' statements? '}'                                                               #blockStatement
    | 'let' ID '=' expr                                                                 #letStatement
    | 'for' '(' init=statement? ';' cond=expr ';' fin=expr? ')' block=statement         #forStatement
    | 'if' '(' expr ')' con=statement ( 'else' alt=statement )                          #ifStatement
    | 'return' expr?                                                                    #returnStatement
    | expr                                                                              #exprStatement
    ;

statements
    : statement ';'?
    | statement ';' statements 
    ;

expr
    : NATURAL                                                   #naturalExpr
    | '(' expr ')'                                              #subExpr
    | ID                                                        #refExpr
    | expr '(' exprs? ')'                                       #applyExpr
    | <assoc=right> op=( '-' | '!' | '~' ) expr                 #unaryExpr
    | lhs=expr op=( '*' | '/' | '%' ) rhs=expr                  #multExpr
    | lhs=expr op=( '+' | '-' ) rhs=expr                        #addExpr
    | lhs=expr op=( '<<' | '>>' ) rhs=expr                      #shiftExpr
    | lhs=expr op=( '<' | '<=' | '>' | '>=' | '<>' ) rhs=expr   #relExpr
    | lhs=expr op=( '==' | '!=' ) rhs=expr                      #equalityExpr
    | lhs=expr '&' rhs=expr                                     #bitAndExpr
    | lhs=expr '^' rhs=expr                                     #bitXorExpr
    | lhs=expr '|' rhs=expr                                     #bitOrExpr
    | lhs=expr '&&' rhs=expr                                    #andExpr
    | lhs=expr '||' rhs=expr                                    #orExpr
    | <assoc=right> cond=expr '?' con=expr ':' alt=expr         #condExpr
    | <assoc=right> target=expr '=' value=expr                  #assignExpr
    | '(' params? ')' '->' TYPEID? statement                    #funExpr
    | 'let' ID '=' value=expr 'in' body=expr                    #letExpr
    ;

exprs : expr ( ',' expr )* ;

param
    : ID ':' TYPEID
    ;

params : param ( ',' param )* ;

NATURAL : ('0'..'9')+ ;

ID : ('_'|'a'..'z') ('_'|'0'..'9'|'a'..'z'|'A'..'Z')* ;
TYPEID : ('A'..'Z') ('_'|'0'..'9'|'a'..'z'|'A'..'Z')* ;

WHITESPACE : (' '|'\t'|'\u000C'|'\n'|'\r') -> skip ;
COMMENT : '//' ~('\n'|'\r')* '\r'? '\n' -> skip ;
