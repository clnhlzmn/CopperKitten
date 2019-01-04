
#include <stdio.h>
#include <assert.h>
#include "timer.h"
#include "incremental_copying_gc.h"

//forwards yields one root pointed to by root
void roots_foreach(void (*cb)(uintptr_t **item, void *ctx), void *cb_ctx, void *foreach_ctx) {
    uintptr_t **it = foreach_ctx;
    cb(it, cb_ctx);
}

uintptr_t mem[1000000];

#define OBJ_SIZE (100)

int main() {
    
    //printf("%d", __LINE__);
    clock_t begin = timer_begin();
    //memory for gc
    struct gc gc_inst;
    //gc instance
    gc_init(&gc_inst, mem, 1000000);
    for (int i = 0; i < 10; ++i) {
        //printf("%d", __LINE__);
        //alloc one ref cell (no roots yet)
        uintptr_t *root = NULL;
        /*printf("main: &root = %p\r\n", &root);*/
        root = gc_alloc_ref_array(&gc_inst, roots_foreach, &root, OBJ_SIZE);
        assert(root);
        //printf("%d", __LINE__);
        assert(gc_get_size(root) == OBJ_SIZE);
        //printf("%d", __LINE__);
        //store a value in non ref cell
        for (int i = 0; i < OBJ_SIZE; ++i) {
            root[i] = (uintptr_t)gc_alloc_int_array(&gc_inst, roots_foreach, &root, OBJ_SIZE);
            assert(root[i]);
            assert(gc_get_size((uintptr_t*)root[i]) == OBJ_SIZE);
            for (unsigned j = 0; j < OBJ_SIZE; ++j) {
                ((uintptr_t*)root[i])[j] = j;
            }
        }
        //allocate a bunch
        for (int i = 0; i < 1000000; ++i) {
            //alloc chunks of 10 cells, no refs
            uintptr_t *a = gc_alloc_int_array(&gc_inst, roots_foreach, &root, 10);
            assert(a);
            assert(gc_get_size(a) == 10);
            /*printf("%d : %d\r\n", __LINE__, i);*/
            //overwrite memory a little
            for (int i = 0; i < 10; ++i) {
                a[i] = i;
                //std::cout << __LINE__ << ":" << i << std::endl; 
            }
        }
        //check that our two allocations survived
        assert(root);
        for (int i = 0; i < OBJ_SIZE; ++i) {
            assert(root[i]);
            assert(gc_get_size((uintptr_t*)root[i]) == OBJ_SIZE);
            for (unsigned j = 0; j < OBJ_SIZE; ++j) {
                assert(((uintptr_t*)root[i])[j] == j);
            }
        }
    }
    /*printf("%lld\r\n", ((uintptr_t*)root[0])[0]);*/
    printf("elapsed = %f\r\n", timer_end(begin));
}


