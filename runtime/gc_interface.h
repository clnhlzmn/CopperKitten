

#ifndef GC_INTERFACE_H
#define GC_INTERFACE_H

#ifdef __cplusplus
extern "C" {
#endif

#include <stdbool.h>
#include <stddef.h>
#include <stdint.h>

//this is intended as a user (pl implementation) 
//interface to a generic memory allocator/manager

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

//function type used by gc_* functions to access
//user's root references and to access references
//in allocated objects.
typedef void (*foreach_t)(
    void (*cb)(intptr_t **it, void *ctx),
    void *cb_ctx,
    void *foreach_ctx);

//forward declaration of gc instance
struct gc;

//initialize gc with pointer to self, pointer to memory, and size of memory
void gc_init(struct gc *self, intptr_t *mem, size_t size);

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

//to call on pointers that are read from memory
//may change the pointer
void gc_read_barrier(struct gc *, intptr_t **);

//to call on objects that have had 
//managed pointers written to them
void gc_write_barrier(struct gc *, intptr_t *);

//example layout for dynamic language with tagged ints
static inline void gc_layout_example_tagged_ints(
    void (*cb)(intptr_t**, void*), 
    void *cb_ctx, 
    void *layout_ctx) 
{
    //call callback for each element if it's a reference
    intptr_t *user_ptr = layout_ctx;
    for (intptr_t i = 0; i < gc_get_size(user_ptr); ++i) {
        //check tag
        if ((user_ptr[i] & 1) == 0) {
            //it's a pointer
            cb((intptr_t**)&user_ptr[i], cb_ctx);
        }
    }
}

//builtin layout for array of references
static inline void gc_layout_ref_array(
    void (*cb)(intptr_t**, void*), 
    void *cb_ctx, 
    void *layout_ctx) 
{
    //call callback for each element
    intptr_t *user_ptr = layout_ctx;
    for (intptr_t i = 0; i < gc_get_size(user_ptr); ++i) {
        cb((intptr_t**)&user_ptr[i], cb_ctx);
    }
}

//builtin layout for integer arrays
static inline void gc_layout_int_array(
    void (*cb)(intptr_t**, void*), 
    void *cb_ctx, 
    void *layout_ctx) 
{
    //does nothing because int array has no references
    (void)cb;
    (void)cb_ctx;
    (void)layout_ctx;
    (void)gc_layout_example_tagged_ints;
}

#ifdef __cplusplus
}
#endif

#endif //GC_INTERFACE_H

