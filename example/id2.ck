
{
    let id = (a): a;        //id is polymorphic
    let id2 = id;           //id2 is also poly, same type as id
    let foo = (a): id(a);   //foo is poly, id reference is of unknown type
    id2(42);                //here id2 type is known
    id2(():42);             //here id2 type is known
    foo(42);                //here foo type, and internal id reference type is known
    {(a): id(a)}(42)
}