

#include "vm.h"

void foo(struct vm *vm) {
    *vm->sp = 42;
    vm->sp++;
}

