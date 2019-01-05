
grammar CK;

//basic CK

file 
    : expr EOF
    ;

param
    : ID ':' TYPEID
    ;

params : param ( ',' param )* ;

expr
    : NATURAL                                   #naturalExpr
    | '(' expr ')'                              #subExpr
    | ID                                        #refExpr
    | '\\' '(' params? ')' ':' TYPEID expr      #funExpr
    | expr '(' ( exprList )? ')'                #applyExpr
    | <assoc=right> ( '-' | '!' | '~' ) expr    #unaryExpr
    | expr ( '*' | '/' | '%' ) expr             #multExpr
    | expr ( '+' | '-' ) expr                   #addExpr
    | expr ( '<<' | '>>' ) expr                 #shiftExpr
    | expr ( '<' | '<=' | '>' | '>=' ) expr     #relExpr
    | expr ( '==' | '!=' | '<>' ) expr          #equalityExpr
    | expr '&' expr                             #bitAndExpr
    | expr '^' expr                             #bitXorExpr
    | expr '|' expr                             #bitOrExpr
    | expr '&&' expr                            #andExpr
    | expr '||' expr                            #orExpr
    | <assoc=right> expr '?' expr ':' expr      #condExpr
    | <assoc=right> expr '=' expr               #assignExpr
    | expr ',' expr                             #commaExpr
    | 'let' ID '=' expr 'in' expr               #letExpr
    ;

//others
NATURAL : ('0'..'9')+ ;

ID : ('_'|'a'..'z') ('_'|'0'..'9'|'a'..'z'|'A'..'Z')* ;
TYPEID : ('A'..'Z') ('_'|'0'..'9'|'a'..'z'|'A'..'Z')* ;

WHITESPACE : (' '|'\r'|'\t'|'\u000C'|'\n') {Skip();} ;
COMMENT : '//' ~('\n'|'\r')* '\r'? '\n' {Skip();} ;
