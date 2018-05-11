

#include <iostream>
#include "vm/vm.hpp"

constexpr intptr_t HEAPSIZE = 1000;
constexpr intptr_t STACKSIZE = 100;

intptr_t heap[HEAPSIZE];
intptr_t stack[STACKSIZE];

GC gc(heap, HEAPSIZE);

auto deref = [](int8_t*ip){return *ip;};

typedef decltype(deref) Deref;

typedef VM<Deref> TargetVM;

TargetVM vm(deref, gc, stack, STACKSIZE);

int main() {
    int8_t program[] = {TargetVM::PUSHW, (int8_t)((intptr_t)(program+8) >> 0), (int8_t)((intptr_t)(program+8) >> 8), (int8_t)((intptr_t)(program+8) >> 16), (int8_t)((intptr_t)(program+8) >> 24), TargetVM::CALL, TargetVM::JUMPO, -7, TargetVM::PUSH, 2, TargetVM::PUSH, 97, TargetVM::ADD, TargetVM::OUT, TargetVM::RETURN,};
    vm.Execute(program);
    std::cout<<"success"<<std::endl;
}


