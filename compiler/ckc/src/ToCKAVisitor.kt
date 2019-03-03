

class ToCKAVisitor : ASTVisitor<Unit> {
    //to translate top level expression to cka:
    //treat the top level expr as body in the following expr
    //{():Unit body}()
    //enter         //create top level frame
    //layout [0]    //first local is ref
    //alloc [2, 0]  //alloc array for function
    //
}

