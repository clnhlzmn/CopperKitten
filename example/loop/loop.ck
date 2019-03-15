
{
    let read = cfun native_read ():Int;
    let write = cfun native_write (Int):Unit;
    while (1) {
        {(): write(read() + 1)}()
    }
}