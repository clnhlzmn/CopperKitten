

#include "incremental_gc.h"
#include "c_static_assert.h"
#include <stdio.h>
#include <assert.h>
#include <limits.h>
#include <string.h>

//number of objects to scan each increment
#define GC_K (2)

//number of bits in a intptr_t
#define UINTPTR_BIT (CHAR_BIT * sizeof(intptr_t))

//layout of gc object meta data
struct gc_object {
    //total size of the allocation (in units of intptr_t)
    intptr_t size      :UINTPTR_BIT - 1;
    //layout is a forward ptr if set
    intptr_t forward   :1;
    //pointer to layout function (or forward)
    intptr_t layout;
    //user data
    intptr_t user[];
};

//size of gc object meta
#define META_SIZE (sizeof(struct gc_object) / sizeof(intptr_t))

//make sure it's right
C_STATIC_ASSERT(META_SIZE == 2, incr_copy_gc);

//convert a user pointer to an allocation pointer
static inline struct gc_object *get_gc_ptr(intptr_t *user) {
    return (struct gc_object *)(user - META_SIZE);
}

//gets the size of an allocation returned from gc_alloc_*
intptr_t gc_get_size(intptr_t *ref) {
    assert(ref);
    return get_gc_ptr(ref)->size - META_SIZE;
}

//determine if a user ptr points to a white object
//obj is white if it's in from-space
//i.e. not in to-space
static bool gc_obj_is_white(struct gc *self, intptr_t *ref) {
    assert(self);
    assert(ref);
    //get a gc pointer, but not a gc_object
    intptr_t *obj = (intptr_t*)get_gc_ptr(ref);
    //assert obj is in heap
    assert(obj >= self->a_space && obj < self->a_space + 2 * self->size);
    return obj < self->begin || obj >= self->end;
}

//determine if a user ptr points to a grey object
//grey objects are above scan and below alloc
static bool gc_obj_is_grey(struct gc *self, intptr_t *ref) {
    assert(self);
    assert(ref);
    //get a gc pointer, but not a gc_object
    intptr_t *obj = (intptr_t*)get_gc_ptr(ref);
    //assert obj is in heap
    assert(obj >= self->a_space && obj < self->a_space + 2 * self->size);
    return obj >= self->scan && obj < self->end;
}

//determine if a user ptr points to a black object
//black objects are above begin and below scan
static bool gc_obj_is_black(struct gc *self, intptr_t *ref) {
    assert(self);
    assert(ref);
    //get a gc pointer, but not a gc_object
    intptr_t *obj = (intptr_t*)get_gc_ptr(ref);
    //assert obj is in heap
    assert(obj >= self->a_space && obj < self->a_space + 2 * self->size);
    return obj >= self->begin && obj < self->scan;
}

//initialize alloc, scan, and end
static inline void gc_swap_spaces(struct gc *self) {
#ifndef NDEBUG
    self->last_alloc = self->alloc;
#endif
    if (self->begin == self->a_space) {
        self->begin = self->b_space;
        self->end = self->b_space + self->size;
    } else {
        self->begin = self->a_space;
        self->end = self->a_space + self->size;
    }
    self->alloc = self->begin;
    self->scan = self->begin;
}

#ifndef NDEBUG

static inline void gc_print_object(intptr_t *heap_begin, struct gc_object *obj) {
    if (obj->forward) {
        printf("{addr:%lld, size:%llu, forward:%lld}[\r\n",
            (intptr_t*)obj - heap_begin,
            obj->size,
            (intptr_t*)obj->forward - heap_begin
        );
    } else {
        const char *layout = 
            obj->layout == (intptr_t)gc_layout_ref_array 
                        ? "refs" 
                        : obj->layout == (intptr_t)gc_alloc_int_array
                                      ? "ints" 
                                      : "?";
        printf("{addr:%lld, size:%llu, layout:%s}[\r\n",
            (intptr_t*)obj - heap_begin,
            obj->size,
            layout
        );
    }
    for (intptr_t i = 0; i < gc_get_size(obj->user); ++i) {
        printf("    %p\r\n", (void*)obj->user[i]);
    }
    printf("]\r\n");
}

static inline void gc_print_heap(struct gc *self) {
    if (self->last_alloc) {
        printf("from-space\r\n");
        //get the other ptr
        intptr_t *begin = self->begin == self->a_space 
                                       ? self->b_space 
                                       : self->a_space;
        //for each objects
        for (intptr_t *it = begin; it < self->last_alloc; ) {
            struct gc_object *obj = (struct gc_object *) it;
            gc_print_object(self->a_space, obj);
            it += obj->size;
        }
    }
    printf("to-space\r\n");
    for (intptr_t *it = self->begin; it < self->alloc; ) {
        struct gc_object *obj = (struct gc_object *) it;
        if (it == self->scan) {
            printf("scan:\r\n");
        }
        gc_print_object(self->a_space, obj);
        it += obj->size;
    }
}

#endif

//create a GC instance with the given memory of the given size
void gc_init(struct gc *self, intptr_t *mem, size_t size) {
    /*printf("gc_init: self = %p\r\n", self);*/
    assert(mem);
    self->size = size / 2;
    self->a_space = mem;
    self->b_space = mem + self->size;
    self->collecting = false;
    self->begin = NULL;
    self->alloc = NULL;
    gc_swap_spaces(self);
}

//true if enough size
#define GC_CHECK_SIZE(self, size) \
    ((self)->alloc + (size) <= (self)->end)

//simple allocation of size cells with no checking
static inline intptr_t *gc_alloc_unchecked(struct gc *self, intptr_t size) {
    intptr_t *ret = self->alloc;
    self->alloc += size;
    assert(self->alloc >= self->begin && self->alloc <= self->end);
    return ret;
}

//forward the allocation pointed to by cp
//takes and returns gc pointers
static inline struct gc_object *gc_forward(
    struct gc *self, 
    struct gc_object *obj) 
{
    /*printf("gc_forward: self = %p, cp = %p\r\n", self, cp);*/
    if (obj == NULL) {
        //nothing to do
        return obj;
    } else if (obj->forward) {
        //obj points to an allocation that is already forwarded
        //return the forwarding address
        return (struct gc_object *)obj->layout;
    } else {
        //obj needs to be forwarded
        if (!GC_CHECK_SIZE(self, obj->size)) {
            //not enough space
            assert(0 && "");
            //TODO: signal self that forwarding has 
            //and how to resume if it's possible to 
            //increase memory size, if memory can't
            //be increased then allocation can't continue.
            //will be tricky because alloc doesn't call 
            //gc_forward directly, but through a 
            //callback given to a foreach_t function
            //iterating the roots or an object's refs
        }
        if (!gc_obj_is_white(self, obj->user)) {
            /*gc_print_heap(self);
            printf("hmm");*/
            //don't forward unless obj is white?
            //TODO: explain this
            //forwarding paints a white object grey
            //don't have to forward grey or black objects
            return obj;
        }
        //copy size intptr_t from old to new space
        memcpy(self->alloc, obj, obj->size * sizeof(intptr_t));
        //set the forward flag at obj
        obj->forward = 1;
        //store the forward pointer
        obj->layout = (intptr_t)self->alloc;
        //bump pointer
        return (struct gc_object *)gc_alloc_unchecked(self, obj->size);
    }
}

//a callback function for foreach_t to traverse 
//the object graph and assert all objs are black 
static inline void integrity_check_cb(intptr_t **it, void *ctx) {
    assert(it);
    assert(ctx);
    struct gc *self = (struct gc *)ctx;
    //if ref is null, nothing to do
    if (*it == NULL) { return; }
    //check this object is black
    if (!gc_obj_is_black(self, *it)) {
        printf("integrity_check_cb:found non black object\r\n");
        assert(0);
    }
    struct gc_object *obj = get_gc_ptr(*it);
    //get the layout
    foreach_t layout = (foreach_t)obj->layout;
    //use the layout to check integrity of contained refs
    layout(integrity_check_cb, self, *it);
}

//a callback function for foreach_t to forward the reference
//pointed to by it. takes struct gc* as ctx
static inline void forward_ref_cb(intptr_t **it, void *ctx) {
    assert(it);
    assert(ctx);
    /*printf("forward_ref_cb: self = %p, root = %p\r\n", data, it);*/
    if (*it == NULL) { return; }
    *it = gc_forward((struct gc*)ctx, get_gc_ptr(*it))->user;
}

//allocates n Cells, collecting if necessary
static inline struct gc_object *gc_alloc(
    struct gc *self, 
    foreach_t root_iter, 
    void *root_iter_ctx, 
    intptr_t size) 
{
    /*printf("alloc begin\r\n");*/
    if (self->collecting) {
        //scan K grey objects
        //keep original alloc here, it's liable to change while we scan objs
        //TODO: scan X bytes instead? maybe X related to size?
        intptr_t *alloc = self->alloc;
        for (intptr_t i = 0; i < GC_K && self->scan < alloc; ++i) {
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
#ifndef NDEBUG
            //check all objects reachable from roots are black
            root_iter(integrity_check_cb, self, root_iter_ctx);
#endif
            //and then fill up the rest of to-space
            //printf("done collecting\r\n");
            //gc_print_heap(self);
        }
        //check size
        if (!GC_CHECK_SIZE(self, size)) {
            return NULL;
        }
    } else {
        //not collecting
        if (!GC_CHECK_SIZE(self, size)) {
            //not enough room
            //begin collection cycle
            self->collecting = true;
            //effectively paint all current objects white
            gc_swap_spaces(self);
            //forward roots
            //TODO: maybe this can be done incrementally too?
            root_iter(forward_ref_cb, self, root_iter_ctx);
            //check size
            if (!GC_CHECK_SIZE(self, size)) {
                return NULL;
            }
        }
    }
    struct gc_object *ret = (struct gc_object *)gc_alloc_unchecked(self, size);
    assert(gc_obj_is_grey(self, ret->user));
    return ret;
}

//callback for foreach_t. zeros the reference pointed to by it.
//doesn't care about context
static inline void zero_ref_cb(intptr_t **it, void *ctx) {
    (void)ctx;
    assert(it);
    *it = NULL;
}

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
    struct gc_object *ret =
        (struct gc_object *)
        gc_alloc(self, root_iter, root_iter_ctx, size + META_SIZE);
    if (ret == NULL) {
        return NULL;
    }
    //set size
    ret->size = size + META_SIZE;
    //set the layout pointer
    ret->layout = (intptr_t)layout;
    ret->forward = 0;
    //use the layout to zero the references
    layout(zero_ref_cb, NULL, ret->user);
    assert(gc_get_size(ret->user) == size);
    return ret->user;
}

//allocate GC managed array of refs
intptr_t *gc_alloc_ref_array(
    struct gc *self, 
    foreach_t root_iter, 
    void *root_iter_ctx, 
    intptr_t size) 
{
    return gc_alloc_with_layout(
        self, root_iter, root_iter_ctx, size, gc_layout_ref_array);
}

//allocate GC managed array of ints (not set to zero)
intptr_t *gc_alloc_int_array(
    struct gc *self, 
    foreach_t root_iter, 
    void *root_iter_ctx, 
    intptr_t size) 
{
    return gc_alloc_with_layout(
        self, root_iter, root_iter_ctx, size, gc_layout_int_array);
}

//to call on pointers that are read from memory
//may change the pointer
void gc_read_barrier(struct gc *self, intptr_t **ref) {
    assert(self);
    assert(ref);
    if (*ref == NULL) { return; }
    if (gc_obj_is_white(self, *ref)) {
        *ref = gc_forward(self, get_gc_ptr(*ref))->user;
    }
}

void gc_write_barrier(struct gc *gc, intptr_t *ref) {
    (void)gc;
    (void)ref;
}

