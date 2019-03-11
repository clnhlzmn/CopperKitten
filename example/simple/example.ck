
{
    let read = cfun native_read ():Int;
    let write = cfun native_write (Int):Unit;
    write(read() + 1)
}