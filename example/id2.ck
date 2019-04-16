
{
    let id = (a) a;
    let id2 = id;
    let foo = (a) id(a);
    id2(42);
    id2(() 42);
    foo(42);
    {(a) id(a)}(42)
}