

#include "incremental_copying_gc.h"
#include <stdio.h>
#include <assert.h>
#include <limits.h>
#include <string.h>

#define UINTPTR_BIT (CHAR_BIT * sizeof(uintptr_t))

struct gc_object {
    uintptr_t size      :UINTPTR_BIT - 2;   //total size of the allocation
    uintptr_t forward   :1;                 //layout is a forward ptr if set
    uintptr_t black     :1;                 //to mark black objects
    uintptr_t layout;                       //pointer to layout function
    uintptr_t user[];                       //user data
};

#define META_SIZE (sizeof(struct gc_object) / sizeof(uintptr_t))

_Static_assert(META_SIZE == 2, "struct gc_object wrong size");

//convert a user pointer to an allocation pointer
static inline struct gc_object *get_gc_ptr(uintptr_t *user) {
    return (struct gc_object *)(user - META_SIZE);
}

//gets the size of an allocation returned from gc_alloc_*
uintptr_t gc_get_size(uintptr_t *ref) {
    return get_gc_ptr(ref)->size - META_SIZE;
}

//initialize alloc_ptr_, scan_ptr_, alloc_begin_ and alloc_end_
//with the given memory range
static inline void gc_init_pointers(
    struct gc *self, 
    uintptr_t *begin, 
    uintptr_t *end) 
{
    self->alloc_ptr = self->scan_ptr = self->alloc_begin = begin;
    self->alloc_end = end;
}

//create a GC instance with the given memory of the given size
void gc_init(struct gc *self, uintptr_t *mem, size_t size) {
    /*printf("gc_init: self = %p\r\n", self);*/
    assert(mem);
    self->size = size / 2;
    self->a_space = mem;
    self->b_space = mem + self->size;
    gc_init_pointers(self, self->a_space, self->a_space + self->size);
}

uintptr_t *gc_alloc_with_layout(
    struct gc *self,
    foreach_t root_iter,
    void *root_iter_ctx,
    uintptr_t size,
    foreach_t layout)
{
    
}

