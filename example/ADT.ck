

type Test = test(Int, Unit)

//TODO: this should fail, need to check for duplicate type names somehow
type Test = foo()

type Pair = (A, B): cons(A, B)

{   let t = test(42, ());       //t: Test()
    _Test_0(t);                 //access Int field
    _Test_1(t)                  //access Unit field
}