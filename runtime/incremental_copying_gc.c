

#include "incremental_copying_gc.h"
#include "c_static_assert.h"
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
    uintptr_t layout;                       //pointer to layout function (or forward)
    uintptr_t user[];                       //user data
};

//size of gc object meta
#define META_SIZE (sizeof(struct gc_object) / sizeof(uintptr_t))

//make sure it's right
//C_STATIC_ASSERT(META_SIZE == 2, incr_copy_gc);

//convert a user pointer to an allocation pointer
static inline struct gc_object *get_gc_ptr(uintptr_t *user) {
    return (struct gc_object *)(user - META_SIZE);
}

//gets the size of an allocation returned from gc_alloc_*
uintptr_t gc_get_size(uintptr_t *ref) {
    assert(ref);
    return get_gc_ptr(ref)->size - META_SIZE;
}

//take user pointers, determines color
static bool gc_obj_is_white(struct gc *self, uintptr_t *ref);
static bool gc_obj_is_grey(struct gc *self, uintptr_t *ref);
static bool gc_obj_is_black(struct gc *self, uintptr_t *ref);

//determine if a reference points to a white object
static bool gc_obj_is_white(struct gc *self, uintptr_t *ref) {
    (void)gc_obj_is_grey;
    (void)gc_obj_is_black;
    assert(self);
    assert(ref);
    //get a gc pointer, but not a gc_object
    uintptr_t *obj = (uintptr_t*)get_gc_ptr(ref);
    //obj is white if it's in from-space
    //i.e. not in to-space
    return obj < self->begin || obj >= self->end;
}

//determine if a reference points to a grey object
static bool gc_obj_is_grey(struct gc *self, uintptr_t *ref) {
    assert(self);
    assert(ref);
    //get a gc pointer, but not a gc_object
    uintptr_t *obj = (uintptr_t*)get_gc_ptr(ref);
    //obj is grey if in to-space and above scan
    //white objects aren't in to-space
    return !gc_obj_is_white(self, ref) && obj >= self->scan;
}

//determine if a reference points to a black object
static bool gc_obj_is_black(struct gc *self, uintptr_t *ref) {
    assert(self);
    assert(ref);
    //others are black
    return !gc_obj_is_white(self, ref) && !gc_obj_is_grey(self, ref);
}

//initialize alloc, scan, and end
static inline void gc_swap_spaces(struct gc *self) {
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

static inline void gc_print_heap(struct gc *self) {
    printf("gc_print_heap\r\n");
    for (uintptr_t *it = self->begin; it < self->alloc; ) {
        struct gc_object *obj = (struct gc_object *) it;
        const char *which = obj->forward ? "forward" : "layout";
        printf("{addr:%p, size:%llu, %s:%p}[\r\n", 
            obj, 
            obj->size, 
            which, 
            (void*)obj->layout
        );
        for (uintptr_t i = 0; i < gc_get_size(obj->user); ++i) {
            printf("    %p\r\n", (void*)obj->user[i]);
        }
        printf("]\r\n");
        it += obj->size;
    }
}

//create a GC instance with the given memory of the given size
void gc_init(struct gc *self, uintptr_t *mem, size_t size) {
    /*printf("gc_init: self = %p\r\n", self);*/
    assert(mem);
    self->size = size / 2;
    self->a_space = mem;
    self->b_space = mem + self->size;
    self->collecting = false;
    self->begin = NULL;
    gc_swap_spaces(self);
}

//true if enough size
#define GC_CHECK_SIZE(self, size) \
    ((self)->alloc + (size) <= (self)->end)

//asserts enough size
#define GC_ASSERT_SIZE(self, size) \
    assert((self)->alloc + (size) <= (self)->end)

//simple allocation of size cells with no checking
static inline uintptr_t *gc_alloc_unchecked(struct gc *self, uintptr_t size) {
    uintptr_t *ret = self->alloc;
    self->alloc += size;
    assert(self->alloc >= self->begin && self->alloc <= self->end);
    return ret;
}

//forward the allocation pointed to by cp
//takes and returns gc pointers, that means
//pointers must be converted when forwarding
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
        //make sure enough size
        GC_ASSERT_SIZE(self, obj->size);
        //copy size uintptr_t from old to new space
        memcpy(self->alloc, obj, obj->size * sizeof(uintptr_t));
        //set the forward flag at obj
        obj->forward = 1;
        //store the forward pointer
        obj->layout = (uintptr_t)self->alloc;
        //bump pointer
        return (struct gc_object *)gc_alloc_unchecked(self, obj->size);
    }
}

//a callback function for foreach_t to forward the reference
//pointed to by it. takes struct gc* as ctx
static inline void integrity_check_cb(uintptr_t **it, void *ctx) {
    assert(it);
    assert(ctx);
    struct gc *self = (struct gc *)ctx;
    //if ref is null, nothing to do
    if (*it == NULL) { return; }
    //check this object is black
    assert(gc_obj_is_black(self, *it));
    struct gc_object *obj = get_gc_ptr(*it);
    //get the layout
    foreach_t layout = (foreach_t)obj->layout;
    //use the layout to check integrity of contained refs
    layout(integrity_check_cb, self, *it);
}

//walk the object graph and assert that all objects are black
static inline void gc_integrity_check(
    struct gc *self, 
    foreach_t root_iter,
    void *root_iter_ctx)
{
    root_iter(integrity_check_cb, self, root_iter_ctx);
}

//a callback function for foreach_t to forward the reference
//pointed to by it. takes struct gc* as ctx
static inline void forward_ref_cb(uintptr_t **it, void *ctx) {
    assert(it);
    assert(ctx);
    /*printf("forward_ref_cb: self = %p, root = %p\r\n", data, it);*/
    if (*it == NULL) { return; }
    *it = gc_forward((struct gc*)ctx, get_gc_ptr(*it))->user;
}

//allocates n Cells, collecting if necessary
static inline uintptr_t *gc_alloc(
    struct gc *self, 
    foreach_t root_iter, 
    void *root_iter_ctx, 
    uintptr_t size) 
{
    /*printf("alloc begin\r\n");
    gc_print_heap(self);*/
    if (self->collecting) {
        //scan K grey objects
        //keep original alloc here, it's liable to change while we scan objs
        uintptr_t *alloc = self->alloc;
        for (uintptr_t i = 0; i < GC_K && self->scan < alloc; ++i) {
            struct gc_object *obj = (struct gc_object *)self->scan;
            if (obj->forward) {
                printf("hmm\r\n");
                gc_print_heap(self);
                assert(0);
            }
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
            //check all objects reachable from roots are black
            gc_integrity_check(self, root_iter, root_iter_ctx);
            //and then fill up the rest of to-space
            //printf("done collecting\r\n");
            //gc_print_heap(self);
        }
        //check size
        GC_ASSERT_SIZE(self, size);
    } else {
        //not collecting
        if (!GC_CHECK_SIZE(self, size)) {
            //not enough room
            //printf("alloc not enough space (not collecting)\r\n");
            //gc_print_heap(self);
            //begin collection cycle
            self->collecting = true;
            //effectively paint all current objects white
            gc_swap_spaces(self);
            //forward roots
            //maybe this can be done incrementally too?
            root_iter(forward_ref_cb, self, root_iter_ctx);
            //check size
            GC_ASSERT_SIZE(self, size);
        }
    }
    /*printf("alloc end\r\n");
    gc_print_heap(self);*/
    uintptr_t *ret = gc_alloc_unchecked(self, size);
    if (!gc_obj_is_grey(self, ((struct gc_object *)ret)->user)) {
        assert(0);
    }
    return ret;
}

//callback for foreach_t. zeros the reference pointed to by it.
//doesn't care about context
static inline void zero_ref_cb(uintptr_t **it, void *ctx) {
    (void)ctx;
    assert(it);
    *it = NULL;
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
    assert((uintptr_t*)ret < self->end);
    assert((uintptr_t*)ret >= self->begin);
    ret->size = size + META_SIZE;
    //set the layout pointer
    ret->layout = (uintptr_t)layout;
    ret->forward = 0;
    //use the layout to zero the references
    layout(zero_ref_cb, NULL, ret->user);
    assert(gc_get_size(ret->user) == size);
    assert(gc_obj_is_grey(self, ret->user));
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
    assert(self);
    assert(ref);
    if (*ref == NULL) { return; }
    if (gc_obj_is_white(self, *ref)) {
        *ref = gc_forward(self, get_gc_ptr(*ref))->user;
    }
}

