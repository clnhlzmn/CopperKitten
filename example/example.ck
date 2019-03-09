
cfun foo = ():Int

{
    let bar = foo();
    {():Int bar}()
}