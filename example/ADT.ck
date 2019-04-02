

type List(a) = empty | cons(a, List(a))

//generates

//empty constructor
let empty: (): List(a) = (): //compiler generated

//cons constructor
let cons: (a, List(a)): List(a) = (a, l): //compiler generated

//empty predicate
let isEmpty: (List(a)): Boolean = (l): //compiler generated

//cons predicate
let isCons: (List(a)): Boolean = (l): //compiler generated

//cons accessor 0
let cons_0: (List(a)): a = (l): //compiler generated

//cons accessor 1
let cons_1: (List(a)): List(a) = (l): //compiler generated

