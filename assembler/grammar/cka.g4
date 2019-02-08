

grammar cka;

//basic CK

file 
    : instructions? NL* EOF
    ;

instructions
    : instruction ( NL+ instruction )*
    ;

instruction
    : ID
    | LABEL ':' instruction
    | ID LABEL
    | ID NATURAL
    ;

NATURAL : ('0'..'9')+ ;

ID : ('_'|'a'..'z') ('_'|'0'..'9'|'a'..'z'|'A'..'Z')* ;
LABEL : ('A'..'Z') ('_'|'0'..'9'|'a'..'z'|'A'..'Z')* ;

NL : '\n'|'\r\n' ;

WHITESPACE : (' '|'\t'|'\u000C') -> skip ;
COMMENT : '//' ~('\n'|'\r')* '\r'? '\n' -> skip ;
