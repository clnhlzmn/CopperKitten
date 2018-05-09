

#include <iostream>
#include "vm/vm.hpp"

typedef GC::Cell Cell;

constexpr Cell HEAPSIZE = 1000;
constexpr Cell STACKSIZE = 100;

Cell heap[HEAPSIZE];
Cell stack[STACKSIZE];

GC gc(heap, HEAPSIZE);

auto deref = [](int8_t*ip){return *ip;};

typedef decltype(deref) Deref;

typedef VM<Deref> RamVM;

RamVM vm(deref, gc, stack, STACKSIZE);

int main() {
    int8_t program0[] = {RamVM::HALT};
    vm.Execute(program0);
    int8_t program1[] = {RamVM::IN, RamVM::PUSH, 3, RamVM::SUB, RamVM::OUT, RamVM::PUSH, '\n', RamVM::OUT, RamVM::HALT};
    vm.Execute(program1);
    std::cout<<"success"<<std::endl;
}


