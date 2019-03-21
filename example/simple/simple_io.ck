
{
    let read = cfun native_read ():Int;
    let write = cfun native_write (Int):Unit;
    write(read());
    write(read());
    write(read());
    write(read())
}