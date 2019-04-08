

module Either = {
    
    type T = (A, B) left(A) | right(B)
    
    let map = (e, l, r)
        if (_Either_is_left(e))
            l(_Either_left_0(e))
        else
            r(_Either_right_0(e))
    
}

