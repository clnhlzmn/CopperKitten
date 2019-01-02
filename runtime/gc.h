

#ifndef GC_H
#define GC_H

#include <stddef.h>
#include <stdint.h>

#ifdef __cplusplus
extern "C" {
#endif

//this is a simple copying garbage collector
//written in relatively clean c99 in a way
//that is meant to be as generic as possible.

//The methods listed below allow users to 
//allocate memory using the garbage collector.
//In order to do so, sometimes memory must be
//reclaimed. In order to reclaim memory your
//struct gc instance must be able to find 
//and possibly modify references to the memory
//that it manages. This can be done with the 
//root_iter parameter. It is a foreach_t function 
//that must be implemented by the user in such 
//a way as to provide the struct gc instance
//a view of the user's references. An example of 
//this can be seen in test/gc/test_gc.c.

//instance data for garbage collector
//these things should not be touched
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

//initialize gc with pointer to self, pointer to memory, and size of memory
void gc_init(struct gc *self, intptr_t *mem, size_t size);

//function type used by gc_* functions to access
//user's root references and to access references
//in allocated objects.
typedef void (*foreach_t)(
    void (*cb)(void *it, void *ctx),
    void *cb_ctx,
    void *foreach_ctx);

//type of struct that is given as context to the
//foreach_t function acting as layout for an alloc
//This allows the layout function to know about the 
//allocation it's supposed to be informing on
struct layout_context {
    //the user pointer to the allocation
    //of interest. to get its size call
    //gc_get_size(user_ptr)
    intptr_t *user_ptr;
};

//see gc.c for example layout functions 
//layout_ref_array and layout_int_array
//and layout_example_tagged_ints

//allocate with an explicit layout
intptr_t *gc_alloc_with_layout(
    struct gc *self,                //pointer to instance
    foreach_t root_iter,            //pointer to root iterator
    void *root_iter_ctx,            //root iterator context
    intptr_t size,                  //size of allocation
    foreach_t layout);              //layout of allocation

//allocate GC managed array of refs
intptr_t *gc_alloc_ref_array(
    struct gc *self,                //instance
    foreach_t root_iter,            //root iterator
    void *root_iter_ctx,            //root iterator context
    intptr_t size);                 //number of refs

//allocate GC managed array of ints (not set to zero)
intptr_t *gc_alloc_int_array(
    struct gc *self,                //instance
    foreach_t root_iter,            //root iterator
    void *root_iter_ctx,            //root iterator context
    intptr_t size);                 //number of ints

//gets the size of an allocation returned from gc_alloc_*
intptr_t gc_get_size(intptr_t *);

#ifdef __cplusplus
}
#endif

#endif //GC_H

