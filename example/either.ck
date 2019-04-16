

//modules not currently implemented
module Either = {
    
    type T = (A, B) left(A) | right(B)
    
    let map = (e, l, r)
        if (is_left(e))
            l(left_0(e))
        else
            r(right_0(e))
    
}

