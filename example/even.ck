{
    let rec even = (n): if (n == 0) 1 else odd(n - 1)
    and     odd  = (n): if (n == 1) 1 else even(n - 1);
    even(42)
}