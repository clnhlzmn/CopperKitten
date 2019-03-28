
{
    //native read and write funs for characters
    let read = cfun native_read ():Int;
    let write = cfun native_write (Int):Unit;
    
    //church encoding of either
    let left = (a): (l, r): l(a);
    let right = (b): (l, r): r(b);
    let map = (e, l, r): e(l, r);
    
    let char = read();
    
    let either = 
        if (char < 97)
            left(():60)
        else
            right(62);

    map(either, (f): write(f()), (i): write(i));
    write(map(either, (f): f(), (i): i));
    
    //church encoding of pair
    let cons = (a, b): (s): s(a, b);
    let fst = (p): p((a,b): a);
    let snd = (p): p((a,b): b);
    
    let pair = cons((): 60, cons(62, ()));
    
    write(fst(pair)());
    write(fst(snd(pair)));
    
    //recursive factorial
    let rec fact = (n): if (n < 2) 1 else n * fact(n - 1);
    write(fact(4) + 97);
    
    //recursive even/odd
    let rec even = (n): if (n == 0) 1 else odd(n - 1)
    and odd = (n): !even(n);
    
    if (even(read())) write(97+4) else write(97+14)
}