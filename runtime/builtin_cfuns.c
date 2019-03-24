

#include "vm.h"

void native_read(struct vm *vm) {
    //read char
    char c;
    scanf("%c", &c);
    //return as int
    vm_push(vm, 1);
    vm_alloc(vm, gc_layout_int_array);
    vm_push(vm, c);
    vm_rstore(vm, 0);
}

void native_write(struct vm *vm) {
    //print arg 0 as char
    vm_aload(vm, 0);
    vm_rload(vm, 0);
    printf("%c", (int)vm_pop(vm));
    //return unit
    vm_push(vm, 0);
}

