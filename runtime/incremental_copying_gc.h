

#ifndef INCREMENTAL_COPYING_GC_H
#define INCREMENTAL_COPYING_GC_H

#ifdef __cplusplus
extern "C" {
#endif

#include "gc_interface.h"

struct gc {
    //PRIVATE
    //pointers to semispaces
    uintptr_t *a_space;
    uintptr_t *b_space;
    //size of each space (equal)
    size_t size;
    //to keep track of allocation and 
    //collection
    uintptr_t *alloc_ptr;
    uintptr_t *scan_ptr;
    uintptr_t *alloc_begin;
    uintptr_t *alloc_end;
    //PRIVATE
};

#ifdef __cplusplus
}
#endif

#endif //INCREMENTAL_COPYING_GC_H

