

grammar cka;

//basic CK

file 
    : NL* instructions? NL* EOF
    ;

instructions
    : instruction ( NL+ instruction )*
    ;

instruction
    : 'add'
    | 'sub'
    | 'mul'
    | 'div'
    | 'mod'
    | 'shl'
    | 'shr'
    | 'cmp'
    | 'skipz'
    | 'ip'
    | 'fp'
    | 'jump'
    | 'push' NATURAL
    | 'dup'
    | 'pop'
    | 'swap'
    | 'enter'
    | 'leave'
    | 'in'
    | 'out'
    | 'layout' frameLayout
    | 'alloc' allocLayout
    | 'load'
    | 'store'
    | 'ncall'
    | 'nop'
    | LABEL ':' instruction
    ;

frameLayout
    : '[' ']'
    | '[' NATURAL ( ',' NATURAL )* ']'
    ;

allocLayout
    : '[' NATURAL ',' '*' ']'               #refArrayLayout
    | '[' NATURAL ( ',' NATURAL )* ']'      #customLayout
    ;

NATURAL : ('0'..'9')+ ;

//ID : ('_'|'a'..'z') ('_'|'0'..'9'|'a'..'z'|'A'..'Z')* ;
LABEL : ('A'..'Z') ('_'|'0'..'9'|'a'..'z'|'A'..'Z')* ;

NL : '\n'|'\r\n' ;

WHITESPACE : (' '|'\t'|'\u000C') -> skip ;
COMMENT : '//' ~('\n'|'\r')* '\r'? '\n' -> skip ;
