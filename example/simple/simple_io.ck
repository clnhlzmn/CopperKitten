

type List = (A): nil() | cons(A, List(A))

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
    
    let rec readIntImpl = (acc): {
        let char = read();
        if (char == 10 || char == 13) 
            right(acc)
        else if (char >= 48 && char <= 57)
            readIntImpl(acc * 10 + char - 48)
        else
            left(-1)
    };
    
    let rec writeInt = (i): {
        if (i != 0) {
            write(i % 10 + 48);
            writeInt(i / 10)
        }
    };
    
    let readInt = (): {
        readIntImpl(0)
    };
    
    map(readInt(), (l): write(97+4), (r): writeInt(r));
    
    let rec forever = (f): {
        f();
        forever(f)
    };
    
    let myList = cons(1, cons(2, cons(3, nil())));
    
    let rec len = (l): 
        if (_List_is_nil(l))
            0
        else
            1 + len(_List_cons_1(l));
    
    writeInt(len(myList))
    
    //forever((): write(97 + 5))
    
}