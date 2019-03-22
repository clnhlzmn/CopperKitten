
{
    let read = cfun native_read ():Int;
    let write = cfun native_write (Int):Unit;
    let id = (a): a;
    while (1) {
        write(62);              //>
        write(id(read()));      //id: (Int):Int
        write(id(read)())       //id: (():Int):():Int
    }
}