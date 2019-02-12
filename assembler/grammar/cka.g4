

grammar cka;

//basic CK

file 
    : NL* instructions? NL* EOF
    ;

instructions
    : instruction ( NL+ instruction )*
    ;

instruction
    : LABEL ':'                     #labelInst
    | integer                       #intInst
    | ID                            #simpleInst
    | ID LABEL                      #jumpInst
    | ID integer                    #pushInst
    | 'layout' frameLayout          #layoutInst
    | 'alloc' allocLayout           #allocInst
    ;

frameLayout
    : '[' ']'                               #emptyFrameLayout
    | '[' NATURAL ( ',' NATURAL )* ']'      #nonEmptyFrameLayout
    ;

allocLayout
    : '[' NATURAL ',' '*' ']'               #refArrayLayout
    | '[' NATURAL ( ',' NATURAL )* ']'      #customLayout
    ;

integer : '-'? NATURAL ;

NATURAL : ('0'..'9')+ ;

ID : ('_'|'a'..'z') ('_'|'0'..'9'|'a'..'z'|'A'..'Z')* ;
LABEL : ('A'..'Z') ('_'|'0'..'9'|'a'..'z'|'A'..'Z')* ;

NL : '\n'|'\r\n' ;

WHITESPACE : (' '|'\t'|'\u000C') -> skip ;
COMMENT : '//' ~('\n'|'\r')* '\r'? '\n' -> skip ;
