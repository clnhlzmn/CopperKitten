

#include "incremental_copying_gc.h"
#include <stdio.h>
#include <assert.h>
#include <limits.h>
#include <string.h>

//number of objects to scan each increment
#define GC_K (3)

//number of bits in a uintptr_t
#define UINTPTR_BIT (CHAR_BIT * sizeof(uintptr_t))

//layout of gc object meta data
struct gc_object {
    uintptr_t size      :UINTPTR_BIT - 1;   //total size of the allocation
    uintptr_t forward   :1;                 //layout is a forward ptr if set
    uintptr_t layout;                       //pointer to layout function
    uintptr_t user[];                       //user data
};

//size of gc object meta
#define META_SIZE (sizeof(struct gc_object) / sizeof(uintptr_t))

//make sure it's right
_Static_assert(META_SIZE == 2, "struct gc_object wrong size");

//convert a user pointer to an allocation pointer
static inline struct gc_object *get_gc_ptr(uintptr_t *user) {
    return (struct gc_object *)(user - META_SIZE);
}

//gets the size of an allocation returned from gc_alloc_*
uintptr_t gc_get_size(uintptr_t *ref) {
    return get_gc_ptr(ref)->size - META_SIZE;
}

//determine if a reference points to a white object
static bool gc_obj_is_white(struct gc *self, uintptr_t *ref) {
    uintptr_t obj = get_gc_ptr(ref);
    //obj is white if it's in from-space
    //i.e. not in to-space
    return obj < self->begin || obj >= self->end;
}

//determine if a reference points to a grey object
static bool gc_is_grey(struct gc *self, uintptr_t *ref) {
    uintptr_t obj = get_gc_ptr(ref);
    //obj is grey if in to-space and above scan
    //white objects aren't in to-space
    return !gc_obj_is_white(self, ref) && obj >= self->scan;
}

//determine if a reference points to a black object
static bool gc_obj_is_black(struct gc *self, uintptr_t *ref) {
    //others are black
    return !gc_obj_is_white(self, ref) && !gc_obj_is_grey(self, ref);
}

//initialize alloc, scan, and end
static inline void gc_swap_spaces(struct gc *self) {
    if (self->end == self->a_space + self->size) {
        self->alloc = self->b_space;
        self->end = self->b_space + self->size;
    } else {
        self->alloc = self->a_space;
        self->end = self->a_space + self->size;
    }
    self->scan = self->alloc;
}

//create a GC instance with the given memory of the given size
void gc_init(struct gc *self, uintptr_t *mem, size_t size) {
    /*printf("gc_init: self = %p\r\n", self);*/
    assert(mem);
    self->size = size / 2;
    self->a_space = mem;
    self->b_space = mem + self->size;
    self->collecting = false;
    gc_swap_spaces(self);
}

//simple allocation of size cells with no checking
static inline uintptr_t *gc_alloc_unchecked(struct gc *self, uintptr_t size) {
    uintptr_t *ret = self->alloc;
    self->alloc += size;
    return ret;
}

//true if enough size
#define GC_CHECK_SIZE(self, size) \
    (self)->alloc + (size) <= (self)->end;

//asserts enough size
#define GC_ASSERT_SIZE(self, size) \
    assert((self)->alloc + (size) <= (self)->end);

//forward the allocation pointed to by cp
//takes and returns gc pointers, that means
//pointers must be converted when forwarding
static inline struct gc *gc_forward(struct gc *self, struct gc *obj) {
    /*printf("gc_forward: self = %p, cp = %p\r\n", self, cp);*/
    if (obj == NULL) {
        //nothing to do
        return obj;
    } else if (obj->forward) {
        //obj points to an allocation that is already forwarded
        //return the forwarding address
        return (struct gc *)obj->layout;
    } else {
        //obj needs to be forwarded
        //make sure enough size
        GC_ASSERT_SIZE(self, obj->size);
        //copy size uintptr_t from old to new space
        memcpy(self->alloc, obj, obj->size * sizeof(uintptr_t));
        //set the forward flag at obj
        obj->forward = true;
        //store the forward pointer
        obj->layout = (uintptr_t)self->alloc;
        //bump pointer
        return (struct gc *)gc_alloc_unchecked(self, obj->size);
    }
}

//a callback function for foreach_t to forward the reference
//pointed to by it. takes struct gc* as ctx
static inline void forward_ref_cb(uintptr_t **it, void *ctx) {
    assert(it);
    assert(ctx);
    /*printf("forward_ref_cb: self = %p, root = %p\r\n", data, it);*/
    *(uintptr_t**)it 
        = get_user_ptr(
            gc_forward((struct gc*)ctx, get_gc_ptr(*(uintptr_t**)it)));
}

//allocates n Cells, collecting if necessary
static inline uintptr_t *gc_alloc(
    struct gc *self, 
    foreach_t root_iter, 
    void *root_iter_ctx, 
    uintptr_t size) 
{
    if (self->collecting) {
        //scan K grey objects
        for (uintptr_t i = 0; i < GC_K && self->scan < self->alloc; ++i) {
            struct gc_object *obj = (struct gc_object *)self->scan;
            //these objects are in to-space; should not have forward bit set
            assert(!obj->forward);
            //get the layout
            foreach_t layout = (foreach_t) obj->layout;
            //use the layout to forward refs
            layout(forward_ref_cb, self, obj->user);
            //effectively mark black
            self->scan += obj->size;
        }
        //done collecting? (no more grey objects)
        if (self->scan >= self->alloc) {
            self->collecting = false;
            //and then fill up the rest of to-space
        }
        //check size
        GC_ASSERT_SIZE(self, size);
        //alloc
        return gc_alloc_unchecked(self, size);
    } else {
        if (GC_CHECK_SIZE(self, size)) {
            //enough space
            return gc_alloc_unchecked(self, size);
        } else {
            //not enough room
            //begin collection cycle
            self->collecting = true;
            //effectively paint all current objects white
            gc_swap_spaces(self);
            //forward roots
            //maybe this can be done incrementally too?
            root_iter(forward_ref_cb, self, root_iter_ctx);\
            //check size
            GC_ASSERT_SIZE(self, size);
            //alloc
            return gc_alloc_unchecked(self, size);
        }
    }
}

//callback for foreach_t. zeros the reference pointed to by it.
//doesn't care about context
static inline void zero_ref_cb(uintptr_t **it, void *ctx) {
    (void)ctx;
    assert(it);
    *(uintptr_t**)it = NULL;
}

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
    struct gc_object *ret = 
        (struct gc_object *)
        gc_alloc(self, root_iter, root_iter_ctx, size + META_SIZE);
    ret->size = size + META_SIZE;
    //set the layout pointer
    ret->layout = (uintptr_t)layout;
    ret->forward = false;
    //use the layout to zero the references
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

//to call on pointers that are read from memory
//may change the pointer
void gc_read_barrier(struct gc *self, uintptr_t **ref) {
    assert(ref);
    if (gc_obj_is_white(*ref)) {
        *ptr = gc_forward(*ref);
    }
}

