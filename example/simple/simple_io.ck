
{
    let read = cfun native_read ():Int;
    let foo = (): read;
    let bar = foo;
    bar
}
