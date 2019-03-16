//map: \/a, b: ([a], ([a]):[b]): [b] 

let map = (l, f): 
    if (l == ()) 
        () 
    else 
        cons(f(first(l)), map(rest(l), f))