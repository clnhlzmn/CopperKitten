

#ifndef COPYING_GC_H
#define COPYING_GC_H

#ifdef __cplusplus
extern "C" {
#endif

#include "gc_interface.h"

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

#ifdef __cplusplus
}
#endif

#endif //COPYING_GC_H

