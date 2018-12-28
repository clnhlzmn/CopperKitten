

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
    void (*cb)(void *it, void *ctx),
    void *cb_ctx,
    void *foreach_ctx);

//the foreach function pointer type is also 
//used for finding references in layouts
//inside each allocated object is a pointer
//to a foreach function that allows the
//gc to do things to the cells that are roots
//inside the object
//the layout function should take a struct layout_context
//as it's foreach_ctx parameter and call the cb parameter
//with a pointer to each reference cell in user_ptr array
//from user_ptr we can get allocation size with gc_get_size

//see gc.c for example layout functions 
//layout_ref_array and layout_int_array

struct layout_context {
    //the user pointer to the allocation
    //of interest. to get its size call
    //gc_get_size(user_ptr)
    intptr_t *user_ptr;
};

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

