
type List = (A) nil() | cons(A, List(A))

{
    let rec len = (l)
        if (is_nil(l))
            0
        else
            1 + len(cons_1(l));
    let myList = cons(1, cons(2, cons(3, nil())));
    len(myList)
}