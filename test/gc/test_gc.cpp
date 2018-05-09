

#include "gc.hpp"

typedef GC::Cell Cell;


int main() {
    //memory for gc
    Cell mem[1000];
    //gc instance
    GC gc(mem, 1000);
    //alloc one ref cell (no roots yet)
    auto root = gc.Alloc((Cell**)0, (Cell**)0, 1, 1);
    assert(GC::GetSize(root) == 1);
    //alloc one non ref cell (passing iterator to roots)
    auto a = gc.Alloc(&root, &root + 1, 1, 0);
    assert(GC::GetSize(a) == 1);
    //store a value in non ref cell
    a[0] = 42;
    //store a in root
    root[0] = (Cell)a;
    //now object map looks like
    //root->[*]
    //       |->[42]
    //allocate a bunch
    for (int i = 0; i < 10000; ++i) {
        //alloc chunks of 10 cells, no refs
        auto a = gc.Alloc(&root, (&root)+1, 10, 0);
        assert(a);
        //overwrite memory a little
        for (int i = 0; i < 10; ++i) {
            a[i] = i;
        }
    }
    //check that our two allocations survived
    assert(((Cell*)root[0])[0] == 42);
}


