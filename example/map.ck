//map: \/A, B: ([A], (A):B): [B] 

let map = <A, B>: (l:[A], f:(A):B): [B]
    if (l == ()) 
        () 
    else 
        cons(f(first(l)), map(rest(l), f))