

#ifndef GC_INTERFACE_H
#define GC_INTERFACE_H

#include <stddef.h>
#include <stdint.h>

#ifdef __cplusplus
extern "C" {
#endif

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
    void (*cb)(uintptr_t **it, void *ctx),
    void *cb_ctx,
    void *foreach_ctx);

//forward declaration of the instance struct
struct gc;

//initialize gc with pointer to self, pointer to memory, and size of memory
void gc_init(struct gc *self, uintptr_t *mem, size_t size);

//see gc.c for example layout functions 
//layout_ref_array and layout_int_array
//and layout_example_tagged_ints

//allocate with an explicit layout
uintptr_t *gc_alloc_with_layout(
    struct gc *self,                //pointer to instance
    foreach_t root_iter,            //pointer to root iterator
    void *root_iter_ctx,            //root iterator context
    uintptr_t size,                  //size of allocation
    foreach_t layout);              //layout of allocation

//allocate GC managed array of refs
uintptr_t *gc_alloc_ref_array(
    struct gc *self,                //instance
    foreach_t root_iter,            //root iterator
    void *root_iter_ctx,            //root iterator context
    uintptr_t size);                 //number of refs

//allocate GC managed array of ints (not set to zero)
uintptr_t *gc_alloc_int_array(
    struct gc *self,                //instance
    foreach_t root_iter,            //root iterator
    void *root_iter_ctx,            //root iterator context
    uintptr_t size);                 //number of ints

//gets the size of an allocation returned from gc_alloc_*
uintptr_t gc_get_size(uintptr_t *);

//to call on pointers that are read from memory
//may change the pointer
void gc_read_barrier(struct gc *, uintptr_t **);

//to call on objects that have had 
//managed pointers written to them
void gc_write_barrier(struct gc *, uintptr_t *);

//builtin layout for an array of references
void gc_layout_ref_array(
    void (*cb)(uintptr_t**, void*), 
    void *cb_ctx, 
    void *layout_ctx);

//builtin layout for integer arrays
void gc_layout_int_array(
    void (*cb)(uintptr_t**, void*), 
    void *cb_ctx, 
    void *layout_ctx);

//example layout for dynamic language with tagged ints
void gc_layout_example_tagged_ints(
    void (*cb)(uintptr_t**, void*), 
    void *cb_ctx, 
    void *layout_ctx);

#ifdef __cplusplus
}
#endif

#endif //GC_INTERFACE_H

