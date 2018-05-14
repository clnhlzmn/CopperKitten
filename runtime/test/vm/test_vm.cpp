

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
    int8_t program[] = {TargetVM::IN, TargetVM::PUSH, 97, TargetVM::SUB, TargetVM::JUMPOZ, 8, TargetVM::PUSH, 97, TargetVM::OUT, TargetVM::PUSH, 10, TargetVM::OUT, TargetVM::HALT, TargetVM::PUSH, 97, TargetVM::PUSH, 25, TargetVM::ADD, TargetVM::OUT, TargetVM::PUSH, 10, TargetVM::OUT, TargetVM::HALT,};
    vm.Execute(program);
    std::cout<<"success"<<std::endl;
}


