

fun compileTopLevel(expr: Expr) {

}

class ToCKAVisitor : ASTVisitor<Unit> {
    //to translate top level expression to cka:
    //treat the top level expr as body in the following expr
    //{():Unit body}()

    //
    //enter         //create top level frame
    //layout [0]    //first local is ref

    //push 1        //push size of function array
    //alloc []      //alloc array for function (no captures)
    //push Begin    //push address of entry point

    //leave



}

