

type List = (A): nil() | cons(A, List(A))

type Either = (A, B): left(A) | right(B)

{
    //native read and write funs for chars
    let read = cfun native_read ():Int;
    let write = cfun native_write (Int):Unit;
        
    let char = read();
    
    let either = 
        if (char < 97)
            left(():60)
        else
            right(62);
    
    let map = (e, l, r):
        if (_Either_is_left(e))
            l(_Either_left_0(e))
        else
            r(_Either_right_0(e));

    map(either, (f): write(f()), (i): write(i));
    write(10);
    map(either, (f): write(f()), (i): write(i));
    write(10);
    
    let rec readIntImpl = (acc): {
        let char = read();
        if (char == 10 || char == 13) 
            right(acc)
        else if (char >= 48 && char <= 57)
            readIntImpl(acc * 10 + char - 48)
        else
            left(-1)
    };
    
    let rec writeIntImpl = (i): {
        if (i / 10)
            writeIntImpl(i / 10);
        write(i % 10 + 48)
    };
    
    let writeInt = (i):
        if (i < 0) {
            write(45);
            writeIntImpl(-i)
        } else
            writeIntImpl(i);
    
    let readInt = (): readIntImpl(0);
    
    map(readInt(), (l): write(97+4), (r): writeInt(r));
    write(10);
    map(readInt(), (l): write(97+4), (r): writeInt(r));
    write(10);
    
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