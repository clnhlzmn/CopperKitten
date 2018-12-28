
#include <iostream>
#include "gc/gc.hpp"

int main() {
    std::cout << "hi" << std::endl;
    //memory for gc
    intptr_t mem[1000];
    //gc instance
    GC gc(mem, 1000);
    std::cout << __LINE__ << std::endl;
    //alloc one ref cell (no roots yet)
    auto root = gc.AllocRefArray((intptr_t**)0, (intptr_t**)0, 1);
    std::cout << __LINE__ << std::endl;
    assert(GC::GetSize(root) == 1);
    std::cout << __LINE__ << std::endl;
    //alloc one non ref cell (passing iterator to roots)
    auto a = gc.AllocIntArray(&root, &root + 1, 1);
    std::cout << __LINE__ << std::endl;
    assert(GC::GetSize(a) == 1);
    std::cout << __LINE__ << std::endl;
    //store a value in non ref cell
    a[0] = 42;
    //store a in root
    root[0] = (intptr_t)a;
    //now object map looks like
    //root->[*]
    //       |->[42]
    //allocate a bunch
    for (int i = 0; i < 10000; ++i) {
        //alloc chunks of 10 cells, no refs
        auto a = gc.AllocIntArray(&root, (&root)+1, 10);
        assert(a);
        std::cout << __LINE__ << ":" << i << std::endl;
        //overwrite memory a little
        for (int i = 0; i < 10; ++i) {
            a[i] = i;
            //std::cout << __LINE__ << ":" << i << std::endl; 
        }
    }
    //check that our two allocations survived
    assert(((intptr_t*)root[0])[0] == 42);
    std::cout << ((intptr_t*)root[0])[0] << std::endl;
}


