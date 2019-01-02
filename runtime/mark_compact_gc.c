

#include <limits.h>
#include <stdint.h>

#define UINTPTR_BIT (CHAR_BIT * sizeof(uintptr_t))

struct gc_object {
    uintptr_t size      :UINTPTR_BIT - 1;
    uintptr_t mark      :1;
    uintptr_t layout;
    uintptr_t forward;
    uintptr_t user[];
};

#define META_SIZE (sizeof(struct gc_object) / sizeof(uintptr_t))

_Static_assert(META_SIZE == 3 * sizeof(uintptr_t), "struct gc_object wrong size");

//convert a user pointer to an allocation pointer
static inline struct gc_object *get_gc_ptr(uintptr_t *user) {
    return (struct gc_object *)(user - META_SIZE);
}

struct gc {
    uuintptr_t *heap;
    size_t size;
    uintptr_t *alloc_ptr;
    uintptr_t *alloc_end;
};

//create a GC instance with the given memory of the given size
void gc_init(struct gc *self, uintptr_t *mem, size_t size) {
    /*printf("gc_init: self = %p\r\n", self);*/
    assert(mem);
    self->size = size;
    self->heap = mem;
    self->alloc_ptr = self->heap;
    self->alloc_end = self->heap + size;
}

//callback for foreach_t. zeros the reference pointed to by it.
//doesn't care about context
static inline void mark_ref_cb(void *it, void *ctx) {
    assert(it);
    struct gc *gc = ctx;
    uintptr_t *ref = *(uintptr_t **)it;
    gc_mark(gc, ref);
}

static inline void gc_mark(struct gc *self, uintptr_t *ref) {
    struct gc_object *obj = get_gc_ptr(ref);
    if (obj->mark == 0) {
        obj->mark = 1;
        ((foreach_t)
    }
}

//a callback function for foreach_t to forward the reference
//pointed to by it. takes struct gc* as ctx
static inline void forward_ref_cb(void *it, void *ctx) {
    assert(it);
    assert(ctx);
    struct gc *gc = (struct gc *)ctx;
    uintptr_t **ref = (uintptr_t **)it;
    /*printf("forward_ref_cb: self = %p, root = %p\r\n", data, it);*/
    
}

//perform collection freeing required_size cells
static inline void gc_collect(
    struct gc *self,
    foreach_t root_iter,
    void *root_iter_ctx,
    intptr_t required_size)
{
    //recursively call mark on the roots
    //for each object on the heap if it's marked calculate it's new address and store in the forward ptr
    //update the roots with the new addresses (from forward ptr)
    //for each object on the heap if it's marked first update
    //    it's references with forwarding addresses then move it to it's new location
    
}

//simple allocation of size cells with no checking
static inline void *gc_alloc_unchecked(struct gc *self, uintptr_t size) {
    uintptr_t *ret = self->alloc_ptr;
    self->alloc_ptr += size;
    return ret;
}

//allocates n Cells, collecting if necessary
static inline void *gc_alloc(
    struct gc *self, 
    foreach_t root_iter, 
    void *root_iter_ctx, 
    uintptr_t size) 
{
    //sz is number of Cells to allocate
    if ((self->alloc_end - self->alloc_ptr) < size) {
        gc_collect(self, root_iter, root_iter_ctx, size);
    }
    return gc_alloc_unchecked(self, size);
}

//callback for foreach_t. zeros the reference pointed to by it.
//doesn't care about context
static inline void zero_ref_cb(void *it, void *ctx) {
    (void)ctx;
    assert(it);
    *(uintptr_t**)it = NULL;
}

//allocate GC managed memory with custom layout
uintptr_t *gc_alloc_with_layout(
    struct gc *self, 
    foreach_t root_iter, 
    void *root_iter_ctx, 
    uintptr_t size, 
    foreach_t layout) 
{
    assert(self);
    assert(root_iter);
    assert(layout);
    //get gc_object
    struct gc_object *ret = 
        gc_alloc(self, root_iter, root_iter_ctx, size + META_SIZE);
    //set size
    ret->size = size + META_SIZE;
    //set the layout pointer
    ret->layout = layout;
    ret->forward = NULL;
    ret->mark = 0;
    //use the layout to zero the references (giving struct gc_object * as context)
    layout(zero_ref_cb, NULL, ret);
    return ret->user;
}
