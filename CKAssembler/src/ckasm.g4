

grammar ckasm;

file
    : instruction* EOF
    ;

instruction
    : LABEL ':'                         #label
    | MNEMONIC                          #mnemonic
    | MNEMONIC INTEGER                  #mnemonicInteger
    | MNEMONIC LABEL                    #mnemonicLabel
    ;

MNEMONIC
    : ('a'..'z')+
    ;

LABEL
    : ('A'..'Z') ('a'..'z' | 'A'..'Z' | '0'..'9' | '_' )*
    ;

INTEGER : '-'? '0'..'9'+ ;

//ignore

WHITESPACE : (' ' | '\n' | '\t' | '\r') + -> skip ;

COMMENT : '//' ~('\n'|'\r')* '\r'? '\n' -> skip ;
