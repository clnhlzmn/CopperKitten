

#include <stdio.h>

#include "vm.h"

#define HEAPSIZE 1000
#define STACKSIZE 100

intptr_t heap[HEAPSIZE];
intptr_t stack[STACKSIZE];

struct gc gc_inst;

struct vm vm_inst;

int main(void) {
    gc_init(&gc_inst, heap, HEAPSIZE);
    vm_init(&vm_inst, &gc_inst, stack, STACKSIZE);
    int8_t program[] = {
        //get input
        IN,
        //process input
        PUSH, 3,
        ADD,
        //print result
        OUT,
        //halt
        PUSH, 0,
        JUMP
    };
    vm_execute(&vm_inst, program);
    printf("success\r\n");
}


