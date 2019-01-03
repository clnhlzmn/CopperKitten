

#include "gc_interface.h"

//builtin layout functions. 
//user pointer to object is passed as context to layout function

//builtin layout for array of references
void gc_layout_ref_array(
    void (*cb)(uintptr_t**, void*), 
    void *cb_ctx, 
    void *layout_ctx) 
{
    //call callback for each element
    uintptr_t *user_ptr = layout_ctx;
    for (uintptr_t i = 0; i < gc_get_size(user_ptr); ++i) {
        cb((uintptr_t**)&user_ptr[i], cb_ctx);
    }
}

//builtin layout for integer arrays
void gc_layout_int_array(
    void (*cb)(uintptr_t**, void*), 
    void *cb_ctx, 
    void *layout_ctx) 
{
    //does nothing because int array has no references
    (void)cb;
    (void)cb_ctx;
    (void)layout_ctx;
    (void)gc_layout_example_tagged_ints;
}

//example layout for dynamic language with tagged ints
void gc_layout_example_tagged_ints(
    void (*cb)(uintptr_t**, void*), 
    void *cb_ctx, 
    void *layout_ctx) 
{
    //call callback for each element if it's a reference
    uintptr_t *user_ptr = layout_ctx;
    for (uintptr_t i = 0; i < gc_get_size(user_ptr); ++i) {
        //check tag
        if ((user_ptr[i] & 1) == 0) {
            //it's a pointer
            cb((uintptr_t**)&user_ptr[i], cb_ctx);
        }
    }
}