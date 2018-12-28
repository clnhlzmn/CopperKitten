

//Memory is allocated in units of intptr_t.
//Each allocation is acompanied by a foreach_t
//function that gives the gc access to the refs
//of that allocation.
//An allocation looks like this
//           ptr
//            |
//            V
//[size|layout|user0|...|usern-1]
//size in the case above is n + 2
//layout is a foreach_t function
//that calls the callback for 
//each user0-usern-1 that is a 
//reference. The callback expects
//that it's first argument is an intptr_t**
//See examples below.

#include <assert.h>
#include <stdio.h>
#include "gc.h"

//builtin layout for array of references
static void layout_ref_array(
    void (*cb)(void*, void*), 
    void *cb_ctx, 
    void *layout_ctx) 
{
    //call callback for each element
    struct layout_context *lc = layout_ctx;
    for (int i = 0; i < gc_get_size(lc->user_ptr); ++i) {
        cb(&lc->user_ptr[i], cb_ctx);
    }
}

//builtin layout for integer arrays
static void layout_int_array(
    void (*cb)(void*, void*), 
    void *cb_ctx, 
    void *layout_ctx) 
{
    //does nothing because int array has no references
    (void)cb;
    (void)cb_ctx;
    (void)layout_ctx;
}

//example layout for dynamic language with tagged ints
static void layout_example_tagged_ints(
    void (*cb)(void*, void*), 
    void *cb_ctx, 
    void *layout_ctx) 
{
    //call callback for each element if it's a reference
    struct layout_context *lc = layout_ctx;
    for (int i = 0; i < gc_get_size(lc->user_ptr); ++i) {
        //check tag
        if ((lc->user_ptr[i] & 1) == 0) {
            //it's a pointer
            cb(&lc->user_ptr[i], cb_ctx);
        }
    }
}

//meta flag meanings***********************************************************
//refs means the high order bits are a layout pointer
#define LAYOUT 0
//forward means that a forwarding pointer is held in the high order bits
#define FORWARD 1

//an allocation looks like ****************************************************
//[size|type|ref0|ref1|...|refm-1|bytes0|bytes1|...|bytesn-1]
//where size is the size (in units of intptr_t) of the entire allocation
//the type field is layed out as follows
//[data|flag]
//where flag is one bit to indicate the meaning of data
//if flag is LAYOUT then data == (ref_count << 1)
//where ref_count is the number of references following the type intptr_t
//the remaining intptr_ts are non references

//allocation indices***********************************************************
#define SIZE 0
#define META 1
#define USER 2

//size of allocation meta******************************************************
#define META_SIZE 2

//flag meta********************************************************************
#define FLAG_BITS 1
#define FLAG_MASK 0x01

//utility functions************************************************************

//get the size from an allocation pointer
static inline intptr_t get_alloc_size(intptr_t *cp) {
    return cp[SIZE];
}

//set the size of an allocation
static inline void set_alloc_size(intptr_t *cp, intptr_t size) {
    cp[SIZE] = size;
}

//get the value of the flag bit
static inline intptr_t get_meta_flag(intptr_t *cp) {
    return cp[META] & FLAG_MASK;
}

//set flag bit
static inline void set_meta_flag(intptr_t *cp, intptr_t flag) {
    //clear existing type code
    cp[META] &= ~FLAG_MASK;
    //set new one
    cp[META] |= (flag & FLAG_MASK);
}

//an int from the meta field
static inline intptr_t get_meta_int(intptr_t *cp) {
    return cp[META] >> FLAG_BITS;
}

//set an int in the meta field
static inline void set_meta_int(intptr_t *cp, intptr_t meta_int) {
    cp[META] &= FLAG_MASK;
    cp[META] |= (meta_int << FLAG_BITS);
}

//get pointer from the meta field
static inline void *get_meta_ptr(intptr_t *cp) {
    return (intptr_t*)(cp[META] & ~FLAG_MASK);
}

//set a pointer in the meta field
static inline void set_meta_ptr(intptr_t *cp, void *fp) {
    cp[META] &= FLAG_MASK;
    cp[META] |= ((intptr_t)fp & ~FLAG_MASK);
}

//convert an allocation pointer to a user pointer
static inline intptr_t *get_user_ptr(intptr_t *gc) {
    return gc + USER;
}

//convert a user pointer to an allocation pointer
static inline intptr_t *get_gc_ptr(intptr_t *user) {
    return user - USER;
}

//methods**********************************************************************

static void gc_init_pointers(struct gc *, intptr_t *, intptr_t *);

//create a GC instance with the given memory of the given size
void gc_init(struct gc *self, intptr_t *mem, size_t size) {
    //because it's not used anywhere else
    (void)layout_example_tagged_ints;
    /*printf("gc_init: self = %p\r\n", self);*/
    assert(mem);
    self->size = size / 2;
    self->a_space = mem;
    self->b_space = mem + self->size;
    gc_init_pointers(self, self->a_space, self->a_space + self->size);
}

//forward the allocation pointed to by cp
//takes and returns gc pointers, that means
//pointers must be converted when forwarding
static inline intptr_t *gc_forward(struct gc *self, intptr_t *cp) {
    /*printf("gc_forward: self = %p, cp = %p\r\n", self, cp);*/
    if (cp == NULL) {
        //nothing to do
        return cp;
    } else if (get_meta_flag(cp) == FORWARD) {
        //cp points to an allocation that is already forwarded
        //return the forwarding address
        return get_meta_ptr(cp);
    } else {
        //cp needs to be forwarded
        //get size
        intptr_t size = get_alloc_size(cp);
        //copy size Cells from old to new space
        for (intptr_t i = 0; i < size; ++i) {
            self->alloc_ptr[i] = cp[i];
        }
        //set the forward flag at cp
        set_meta_flag(cp, FORWARD);
        //set forward pointer to new space
        set_meta_ptr(cp, self->alloc_ptr);
        //save the location
        intptr_t *ret = self->alloc_ptr;
        //bump pointer for next call
        self->alloc_ptr += size;
        //return new location
        return ret;
    }
}

//a callback function for foreach_t to forward the reference
//pointed to by it. takes struct gc* as ctx
static inline void forward_ref_cb(void *it, void *ctx) {
    assert(it);
    assert(ctx);
    /*printf("forward_ref_cb: self = %p, root = %p\r\n", data, it);*/
    *(intptr_t**)it 
        = get_user_ptr(
            gc_forward((struct gc*)ctx, get_gc_ptr(*(intptr_t**)it)));
}

//perform collection freeing required_size cells
static inline void gc_collect(
    struct gc *self,
    foreach_t root_iter,
    void *root_iter_ctx,
    intptr_t required_size)
{
    //switch space
    if (self->alloc_end == self->a_space + self->size) {
        gc_init_pointers(self, self->b_space, self->b_space + self->size);
    } else {
        gc_init_pointers(self, self->a_space, self->a_space + self->size);
    }
    //forward roots
    root_iter(forward_ref_cb, self, root_iter_ctx);
    //forward references between scan_ptr_ and alloc_ptr_
    while (self->scan_ptr < self->alloc_ptr) {
        if (get_meta_flag(self->scan_ptr) == LAYOUT) {
            //use the layout function to get the references
            foreach_t ref_iter = (foreach_t)get_meta_ptr(self->scan_ptr);
            struct layout_context lc = { 
                .user_ptr = get_user_ptr(self->scan_ptr) 
            };
            ref_iter(forward_ref_cb, self, &lc);
        }
        //done with refs so bump scan_ptr_ to next allocation
        self->scan_ptr += get_alloc_size(self->scan_ptr);
    }
    //check if collection freed enough space
    if ((self->alloc_end - self->alloc_ptr) < required_size) {
        //out of memory
        assert(0);
    }
}

//simple allocation of size cells with no checking
static inline intptr_t *gc_alloc_unchecked(struct gc *self, intptr_t size) {
    intptr_t *ret = self->alloc_ptr;
    self->alloc_ptr += size;
    return ret;
}

//allocates n Cells, collecting if necessary
static inline intptr_t *gc_alloc(
    struct gc *self, 
    foreach_t root_iter, 
    void *root_iter_ctx, 
    intptr_t size) 
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
    *(intptr_t**)it = NULL;
}

//allocate GC managed memory with custom layout
intptr_t *gc_alloc_with_layout(
    struct gc *self, 
    foreach_t root_iter, 
    void *root_iter_ctx, 
    intptr_t size, 
    foreach_t layout) 
{
    assert(self);
    assert(root_iter);
    assert(layout);
    intptr_t *ret = 
        gc_alloc(self, root_iter, root_iter_ctx, size + META_SIZE);
    set_alloc_size(ret, size + META_SIZE);
    //set the layout pointer
    set_meta_flag(ret, LAYOUT);
    set_meta_ptr(ret, layout);
    //create layout context
    struct layout_context lc = {
        .user_ptr = get_user_ptr(ret)
    };
    //use the layout to zero the references
    layout(zero_ref_cb, NULL, &lc);
    return get_user_ptr(ret);
}

//allocate GC managed array of refs
intptr_t *gc_alloc_ref_array(
    struct gc *self, 
    foreach_t root_iter, 
    void *root_iter_ctx, 
    intptr_t size) 
{
    return gc_alloc_with_layout(
        self, root_iter, root_iter_ctx, size, layout_ref_array);
}

//allocate GC managed array of ints (not set to zero)
intptr_t *gc_alloc_int_array(
    struct gc *self, 
    foreach_t root_iter, 
    void *root_iter_ctx, 
    intptr_t size) 
{
    return gc_alloc_with_layout(
        self, root_iter, root_iter_ctx, size, layout_int_array);
}

//get the size of user data from a pointer
//returned by gc_alloc
intptr_t gc_get_size(intptr_t *cp) {
    return get_gc_ptr(cp)[SIZE] - META_SIZE;
}

//initialize alloc_ptr_, scan_ptr_, alloc_begin_ and alloc_end_
//with the given memory range
static inline void gc_init_pointers(
    struct gc *self, 
    intptr_t *begin, 
    intptr_t *end) 
{
    self->alloc_ptr = self->scan_ptr = self->alloc_begin = begin;
    self->alloc_end = end;
}

