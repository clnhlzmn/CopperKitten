
{
    let read = cfun native_read ():Int;
    let write = cfun native_write (Int):Unit;
    
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
    
    let cons = (a, b): (s): s(a, b);
    let fst = (p): p((a,b): a);
    let snd = (p): p((a,b): b);
    
    let pair = cons((): 60, cons(62, ()));
    
    write(fst(pair)());
    write(fst(snd(pair)))
}