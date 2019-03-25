
{
    //native read and write funs operate on single chars
    let read = cfun native_read ():Int;
    let write = cfun native_write (Int):Unit;
    
    //church encoding of union type
    let left = (a): (l, r): l(a);
    let right = (b): (l, r): r(b);
    let map = (e, l, r): e(l, r);
    
    //read a char
    let char = read();
    
    //if char is less than 'a'
    let either = if (char < 97)
            //create left sum
            left(():60)
        else
            //create right sum
            right(62);
    
    //use map to extract which side
    map(either, (f): write(f()), (i): write(i));
    map(either, (f): write(f()), (i): write(i));
    
    //church encoding of pair
    let cons = (a, b): (s): s(a, b);
    let fst = (p): p((a,b): a);
    let snd = (p): p((a,b): b);
    
    //create a nested pair
    let pair = cons((): 60, cons(62, ()));
    
    //get the function from fst and write its result
    write(fst(pair)());
    //get the int from fst of snd and write it
    write(fst(snd(pair)))
}