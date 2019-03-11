

#include "vm.h"

void native_read(struct vm *vm) {
    //read char
    char c;
    scanf("%c", &c);
    //return as int
    *vm->sp = c;
    vm->sp++;
}

void native_write(struct vm *vm) {
    //print arg 0 as char
    vm_aload(vm, 0);
    printf("%c", (int)vm->sp[-1]);
    //return unit
}

