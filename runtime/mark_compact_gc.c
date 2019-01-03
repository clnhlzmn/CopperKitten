

#include "mark_compact_gc.h"
#include <stdio.h>
#include <assert.h>
#include <limits.h>
#include <string.h>

#define UINTPTR_BIT (CHAR_BIT * sizeof(uintptr_t))

struct gc_object {
    uintptr_t size      :UINTPTR_BIT - 1;
    uintptr_t mark      :1;
    uintptr_t layout;
    uintptr_t forward;
    uintptr_t user[];
};

#define META_SIZE (sizeof(struct gc_object) / sizeof(uintptr_t))

_Static_assert(META_SIZE == 3, "struct gc_object wrong size");

//convert a user pointer to an allocation pointer
static inline struct gc_object *get_gc_ptr(uintptr_t *user) {
    return (struct gc_object *)(user - META_SIZE);
}

//gets the size of an allocation returned from gc_alloc_*
uintptr_t gc_get_size(uintptr_t *ref) {
    return get_gc_ptr(ref)->size - META_SIZE;
}

//create a GC instance with the given memory of the given size
void gc_init(struct gc *self, uintptr_t *mem, size_t size) {
    /*printf("gc_init: self = %p\r\n", self);*/
    assert(mem);
    self->size = size;
    self->heap = mem;
    self->alloc_ptr = self->heap;
    self->alloc_end = self->heap + size;
}

//callback for root iterator.
//marks the object pointed to by the 
//reference pointed to by it
static inline void mark_ref_cb(void *it, void *ctx) {
    assert(it);
    (void)ctx;
    uintptr_t *ref = *(uintptr_t **)it;
    struct gc_object *obj = get_gc_ptr(ref);
    if (obj->mark == 0) {
        /*printf("mark_ref_cb: obj = %p\r\n", obj);*/
        obj->mark = 1;
        ((foreach_t) obj->layout)(mark_ref_cb, NULL, obj->user);
    }
}

//callback for root iterator.
//to update the reference pointed to by it
static inline void update_ref_address_cb(void *it, void *ctx) {
    assert(it);
    (void)ctx;
    uintptr_t **ref = (uintptr_t **)it;
    struct gc_object *obj = get_gc_ptr(*ref);
    if (obj->forward) {
        /*printf("update_ref_address_cb: old = %p, new = %p\r\n", obj, (void*)obj->forward);*/
        *ref = ((struct gc_object *)obj->forward)->user;
        ((foreach_t) obj->layout)(update_ref_address_cb, NULL, obj->user);
    }
}

/*static inline void gc_print_heap(struct gc *self) {*/
    /*printf("gc_print_heap\r\n");*/
    /*for (uintptr_t *it = self->heap; it < self->alloc_ptr; ) {*/
        /*struct gc_object *obj = (struct gc_object *) it;*/
        /*printf("obj @ %p\r\n", obj);*/
        /*printf("    marked = %d, forward = %p, size = %llu\r\n", obj->mark, (void*)obj->forward, (uintptr_t)obj->size);*/
        /*it += obj->size;*/
    /*}*/
/*}*/

//perform collection freeing required_size cells
static inline void gc_collect(
    struct gc *self,
    foreach_t root_iter,
    void *root_iter_ctx,
    uintptr_t required_size)
{
    /*printf("gc_collect begin: alloc=%p\r\n", self->alloc_ptr);*/
    /*gc_print_heap(self);*/
    
    //ptr to the beginning of the heap to help with computing new sizes
    uintptr_t *alloc = self->heap;
    
    //recursively call mark on the roots
    root_iter(mark_ref_cb, NULL, root_iter_ctx);
    
    //for each object on the heap if it's marked calculate 
    //it's new address and store in the forward ptr
    for (uintptr_t *it = self->heap; it < self->alloc_ptr; ) {
        struct gc_object *obj = (struct gc_object *) it;
        if (obj->mark) {
            obj->forward = (uintptr_t)alloc;
            alloc += obj->size;
        }
        it += obj->size;
    }
    
    //update the roots with the new addresses (from forward ptr)
    //and recursively update references found in those objects
    root_iter(update_ref_address_cb, NULL, root_iter_ctx);
    
    //move marked objects to their new locations
    for (uintptr_t *it = self->heap; it < self->alloc_ptr; ) {
        struct gc_object *obj = (struct gc_object *) it;
        if (obj->mark) {
            //get forward
            uintptr_t *forward = (uintptr_t*)obj->forward;
            //reset meta
            obj->forward = (uintptr_t)NULL;
            obj->mark = 0;
            //move object
            memcpy(forward, obj, sizeof(uintptr_t) * obj->size);
        }
        it += obj->size;
    }
    
    //update alloc ptr to end of moved objects
    self->alloc_ptr = alloc;
    
    //check success 
    if ((uintptr_t)(self->alloc_end - self->alloc_ptr) < required_size) {
        assert(0 && "out of memory");
    }
    
    /*printf("gc_collect end: alloc=%p\r\n", self->alloc_ptr);*/
    /*gc_print_heap(self);*/
}

//simple allocation of size cells with no checking
static inline uintptr_t *gc_alloc_unchecked(struct gc *self, uintptr_t size) {
    /*printf("gc_alloc_unchecked: ret = %p\r\n", self->alloc_ptr);*/
    uintptr_t *ret = self->alloc_ptr;
    self->alloc_ptr += size;
    return ret;
}

//allocates n Cells, collecting if necessary
static inline uintptr_t *gc_alloc(
    struct gc *self, 
    foreach_t root_iter, 
    void *root_iter_ctx, 
    uintptr_t size) 
{
    //sz is number of Cells to allocate
    if ((uintptr_t)(self->alloc_end - self->alloc_ptr) < size) {
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
        (struct gc_object *)
        gc_alloc(self, root_iter, root_iter_ctx, size + META_SIZE);
    //set size
    ret->size = size + META_SIZE;
    //set the layout pointer
    ret->layout = (uintptr_t)layout;
    ret->forward = (uintptr_t)NULL;
    ret->mark = 0;
    //use the layout to zero the references (giving user ptr as context to layout)
    layout(zero_ref_cb, NULL, ret->user);
    return ret->user;
}

//allocate GC managed array of refs
uintptr_t *gc_alloc_ref_array(
    struct gc *self, 
    foreach_t root_iter, 
    void *root_iter_ctx, 
    uintptr_t size) 
{
    return gc_alloc_with_layout(
        self, root_iter, root_iter_ctx, size, gc_layout_ref_array);
}

//allocate GC managed array of ints (not set to zero)
uintptr_t *gc_alloc_int_array(
    struct gc *self, 
    foreach_t root_iter, 
    void *root_iter_ctx, 
    uintptr_t size) 
{
    return gc_alloc_with_layout(
        self, root_iter, root_iter_ctx, size, gc_layout_int_array);
}
