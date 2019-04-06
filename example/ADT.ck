

type Test = test(Int, Unit)

type List = (A): nil() | cons(A, List(A))

{   let t = test(42, ());       //t: Test()
    _Test_test_0(t);                 //access Int field
    _Test_test_1(t)                  //access Unit field
}