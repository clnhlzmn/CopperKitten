

#ifndef MARK_COMPACT_GC_H
#define MARK_COMPACT_GC_H

#include <stdint.h>

struct gc {
    uintptr_t *heap;
    size_t size;
    uintptr_t *alloc_ptr;
    uintptr_t *alloc_end;
};

void gc_init(struct gc *self, uintptr_t *mem, size_t size);

//function type used by gc_* functions to access
//user's root references and to access references
//in allocated objects.
typedef void (*foreach_t)(
    void (*cb)(void *it, void *ctx),
    void *cb_ctx,
    void *foreach_ctx);

uintptr_t *gc_alloc_with_layout(
    struct gc *self, 
    foreach_t root_iter, 
    void *root_iter_ctx, 
    uintptr_t size, 
    foreach_t layout);

//allocate GC managed array of refs
uintptr_t *gc_alloc_ref_array(
    struct gc *self,                //instance
    foreach_t root_iter,            //root iterator
    void *root_iter_ctx,            //root iterator context
    uintptr_t size);                 //number of refs

//allocate GC managed array of ints (not set to zero)
uintptr_t *gc_alloc_int_array(
    struct gc *self,                //instance
    foreach_t root_iter,            //root iterator
    void *root_iter_ctx,            //root iterator context
    uintptr_t size);                 //number of ints

uintptr_t gc_get_size(uintptr_t *ref);

#endif //MARK_COMPACT_GC_H

