
{
    //native read and write funs for chars
    let read = cfun native_read ():Int;
    let write = cfun native_write (Int):Unit;
    
    //church either
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
    
    //church pairs
    let pair = (a, b): (s): s(a, b);
    let fst = (p): p((a,b): a);
    let snd = (p): p((a,b): b);
    
    let myPair = pair((): 60, pair(62, ()));
    
    write(fst(myPair)());
    write(fst(snd(myPair)));
    
    //recursive factorial
    let rec fact = (n): if (n < 2) 1 else n * fact(n - 1);
    write(fact(4) + 97);
    
    //recursive even/odd
    let rec even = (n): if (n == 0) 1 else odd(n - 1)
    and odd = (n): !even(n);
    
    if (even(read())) write(97+4) else write(97+14);
    
    //church booleans
    let true = (a, b): a;
    let false = (a, b): b;
    
    //church lists
    let nil = pair(true, true);
    let isNil = fst;
    let cons = (h, t): pair(false, pair(h, t));
    let head = (l): fst(snd(l));
    let tail = (l): snd(snd(l))
    //let rec len = (l): if (isNil(l)(1, 0)) 0 else len(tail(l));
    //
    //
    //let myLen = len(nil)
    
    //let myList = cons(42, nil)
}