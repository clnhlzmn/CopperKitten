
#include <stdio.h>
#include <assert.h>
#include "gc.h"

//forwards yields one root pointed to by root
void roots_foreach(void (*cb)(void *item, void *ctx), void *cb_ctx, void *foreach_ctx) {
    intptr_t **it = foreach_ctx;
    cb(it, cb_ctx);
}

int main() {
    //printf("%d", __LINE__);
    //memory for gc
    intptr_t mem[1000];
    struct gc gc_inst;
    //gc instance
    gc_init(&gc_inst, mem, 1000);
    //printf("%d", __LINE__);
    //alloc one ref cell (no roots yet)
    intptr_t *root = NULL;
    /*printf("main: &root = %p\r\n", &root);*/
    root = gc_alloc_ref_array(&gc_inst, roots_foreach, &root, 1);
    //printf("%d", __LINE__);
    assert(gc_get_size(root) == 1);
    //printf("%d", __LINE__);
    //alloc one non ref cell (passing iterator to roots)
    intptr_t *a = gc_alloc_int_array(&gc_inst, roots_foreach, &root, 1);
    //printf("%d", __LINE__);
    assert(gc_get_size(a) == 1);
    //printf("%d", __LINE__);
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
        intptr_t *a = gc_alloc_int_array(&gc_inst, roots_foreach, &root, 10);
        assert(a);
        /*printf("%d : %d\r\n", __LINE__, i);*/
        //overwrite memory a little
        for (int i = 0; i < 10; ++i) {
            a[i] = i;
            //std::cout << __LINE__ << ":" << i << std::endl; 
        }
    }
    //check that our two allocations survived
    assert(((intptr_t*)root[0])[0] == 42);
    printf("%lld\r\n", ((intptr_t*)root[0])[0]);
}


