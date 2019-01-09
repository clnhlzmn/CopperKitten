
grammar ck;

//basic CK

file 
    : statements? EOF
    ;

statement
    : '{' statements? '}'                                                               #blockStatement
    | 'let' ID '=' expr                                                                 #letStatement
    | 'do' statement 'while' '(' expr ')'                                               #doStatement
    | 'while' '(' expr ')' statement                                                    #whileStatement
    | 'for' '(' statement? ';' expr ';' expr? ')' statement                             #forStatement
    | 'if' '(' expr ')' statement ( 'else' statement )                                  #ifStatement
    | 'match' '(' expr ')' ( 'with' expr ':' statement )* ( 'default' ':' statement )?  #matchStatement
    | 'return' expr?                                                                    #returnStatement
    | expr                                                                              #exprStatement
    ;

statements
    : statement ';'?
    | statement ';' statements ;

expr
    : NATURAL                                       #naturalExpr
    | '(' expr ')'                                  #subExpr
    | ID                                            #refExpr
    | expr '(' exprs? ')'                           #applyExpr
    | <assoc=right> ( '-' | '!' | '~' ) expr        #unaryExpr
    | expr ( '*' | '/' | '%' ) expr                 #multExpr
    | expr ( '+' | '-' ) expr                       #addExpr
    | expr ( '<<' | '>>' ) expr                     #shiftExpr
    | expr ( '<' | '<=' | '>' | '>=' | '<>' ) expr  #relExpr
    | expr ( '==' | '!=' ) expr                     #equalityExpr
    | expr '&' expr                                 #bitAndExpr
    | expr '^' expr                                 #bitXorExpr
    | expr '|' expr                                 #bitOrExpr
    | expr '&&' expr                                #andExpr
    | expr '||' expr                                #orExpr
    | <assoc=right> expr '?' expr ':' expr          #condExpr
    | <assoc=right> expr '=' expr                   #assignExpr
    | '(' params? ')' '->' TYPEID? statement        #funExpr
    | 'let' ID '=' expr 'in' expr                   #letExpr
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
