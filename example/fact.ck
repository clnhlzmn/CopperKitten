{
    let write = cfun native_write (Int):Unit;
    let rec fact = (n): if (n < 2) 1 else n * fact(n - 1);
    write(fact(3))
}