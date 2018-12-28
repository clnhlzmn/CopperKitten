

#ifndef GC_H
#define GC_H

#include <stddef.h>
#include <stdint.h>

#ifdef __cplusplus
extern "C" {
#endif

//foreach_t is the type of the callback function that must be passed to
//gc_alloc*. the pointed-to function takes a pointer to a callback function 
//and a pointer to data for that function. additionally the foreach function
//takes its own data pointer
typedef void (*foreach_t)(
    void (*cb)(void *item, void *ctx),
    void *cb_ctx,
    void *foreach_ctx);

struct gc {
    //PRIVATE
    //pointers to semispaces
    intptr_t *a_space;
    intptr_t *b_space;
    //size of each space (equal)
    size_t size;
    //to keep track of allocation and 
    //collection
    intptr_t *alloc_ptr;
    intptr_t *scan_ptr;
    intptr_t *alloc_begin;
    intptr_t *alloc_end;
    //PRIVATE
};

//definitions for layouts
//ref array and int array are predefined layouts
#define LAYOUT_REF_ARRAY 0
#define LAYOUT_INT_ARRAY 1
//to use a bitmap layout create a structure like
//intptr_t my_custom_layout[] = {LAYOUT_BITMAP, 0b01010101, ...};
//and pass my_custom_layout as the layout parameter to gc_alloc_with_layout
//then at every intptr_t position that is marked by a one bit in the layout
//will be considered a reference when a collection is performed
#define LAYOUT_BITMAP 2

//initialize gc with pointer to self, pointer to memory, and size of memory
void gc_init(struct gc *self, intptr_t *mem, size_t size);

//allocate with an explicit layout
intptr_t *gc_alloc_with_layout(
    struct gc *self,                //pointer to instance
    foreach_t root_iter,            //pointer to root iterator
    void *root_iter_ctx,            //root iterator context
    intptr_t size,                  //size of allocation
    intptr_t *layout);              //layout of allocation

//allocate GC managed array of refs
intptr_t *gc_alloc_ref_array(
    struct gc *self,                //instance
    foreach_t root_iter,            //root iterator
    void *root_iter_ctx,            //root iterator context
    intptr_t size);                 //number of refs (uses LAYOUT_REF_ARRAY internally)

//allocate GC managed array of ints (not set to zero)
intptr_t *gc_alloc_int_array(
    struct gc *self,                //instance
    foreach_t root_iter,            //root iterator
    void *root_iter_ctx,            //root iterator context
    intptr_t size);                 //number of ints (uses LAYOUT_INT_ARRAY internally)

//gets the size of an allocation returned from gc_alloc_*
intptr_t gc_get_size(intptr_t *);

#ifdef __cplusplus
}
#endif

#endif //GC_H

