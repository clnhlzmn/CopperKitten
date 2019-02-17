

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
    | simpleInstruction             #simpleInst
    | literalIntMnemonic integer    #literalIntInst
    | literalLabelMnemonic LABEL    #literalLabelInst
    | 'layout' frameLayout          #layoutInst
    | 'alloc' allocLayout           #allocInst
    ;

literalLabelMnemonic
    : 'push'
    | 'jump'
    | 'jumpz'
    | 'jumpnz'
    ;

literalIntMnemonic
    : 'push'
    | 'lload'
    | 'lstore'
    | 'rload'
    | 'rstore'
    ;

simpleInstruction
    : 'add'
    | 'sub'
    | 'mul'
    | 'div'
    | 'mod'
    | 'shl'
    | 'shr'
    | 'cmp'
    | 'call'
    | 'ret'
    | 'dup'
    | 'pop'
    | 'swap'
    | 'enter'
    | 'leave'
    | 'in'
    | 'out'
    | 'nop'
    ;

frameLayout
    : '[' ']'                               #emptyFrameLayout
    | '[' NATURAL ( ',' NATURAL )* ']'      #nonEmptyFrameLayout
    ;

allocLayout
    : '[' '*' ']'                           #refArrayLayout
    | '[' ']'                               #emptyCustomLayout
    | '[' NATURAL ( ',' NATURAL )* ']'      #customLayout
    ;

integer : '-'? NATURAL ;

NATURAL : ('0'..'9')+ ;

//ID : ('_'|'a'..'z') ('_'|'0'..'9'|'a'..'z'|'A'..'Z')* ;
LABEL : ('A'..'Z') ('_'|'0'..'9'|'a'..'z'|'A'..'Z')* ;

NL : '\n'|'\r\n' ;

WHITESPACE : (' '|'\t'|'\u000C') -> skip ;
