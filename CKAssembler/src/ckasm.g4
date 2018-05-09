

grammar ckasm;

file
    : instruction* EOF
    ;

instruction
    : LABEL ':'                         #label
    | 'add'                             #add
    | 'add' INTEGER                     #addInteger
    | 'sub'                             #sub
    | 'sub' INTEGER                     #subInteger
    | 'mul'                             #mul
    | 'mul' INTEGER                     #mulInteger
    | 'div'                             #div
    | 'div' INTEGER                     #divInteger
    | 'mod'                             #mod
    | 'mod' INTEGER                     #modInteger
    | 'cmp'                             #cmp
    | 'cmp' INTEGER                     #cmpInteger
    | 'call'                            #call
    | 'call' LABEL                      #callLabel
    | 'return'                          #return
    | 'jump'                            #jump
    | 'jumpz'                           #jumpz
    | 'jumpo' INTEGER                   #jumpo
    | 'jumpoz' INTEGER                  #jumpoz
    | 'jump' LABEL                      #jumpLabel
    | 'jumpz' LABEL                     #jumpzLabel
    | 'push' INTEGER                    #push
    | 'pushw' INTEGER                   #pushw
    | 'dup'                             #dup
    | 'pop'                             #pop
    | 'swap'                            #swap
    | 'halt'                            #halt
    | 'pushref'                         #pushref
    | 'popref'                          #popref
    | 'enter'                           #enter
    | 'leave'                           #leave
    | 'in'                              #in
    | 'out'                             #out
    | 'out' INTEGER                     #outInteger
    | 'alloc'                           #alloc
    | 'alloc' INTEGER                   #allocInteger
    | 'alloc' INTEGER INTEGER           #allocIntInt
    | 'refget'                          #refget
    | 'refget' INTEGER                  #refgetInteger
    | 'refset'                          #refset
    | 'refset' INTEGER                  #refsetInteger
    | 'refset' INTEGER INTEGER          #refsetIntInt
    ;

LABEL
    : ('A'..'Z') ('a'..'z' | 'A'..'Z' | '0'..'9' | '_' )*
    ;

INTEGER : '-'? '0'..'9'+ ;

//ignore

WHITESPACE : (' ' | '\n' | '\t' | '\r') + -> skip ;

COMMENT : '//' ~('\n'|'\r')* '\r'? '\n' -> skip ;
