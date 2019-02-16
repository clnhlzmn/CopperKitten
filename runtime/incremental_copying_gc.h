

#ifndef INCREMENTAL_COPYING_GC_H
#define INCREMENTAL_COPYING_GC_H

#ifdef __cplusplus
extern "C" {
#endif

#include "gc_interface.h"

struct gc {
    //PRIVATE
    //pointers to semispaces
    intptr_t *a_space;
    intptr_t *b_space;
    //size of each space (equal)
    size_t size;
    //to keep track of allocation and 
    //collection
#ifndef NDEBUG
	//for printing the heap
	intptr_t *last_alloc;
#endif
    intptr_t *alloc;
    intptr_t *scan;
    intptr_t *begin;
    intptr_t *end;
    //to know what to do during alloc
    bool collecting;
    //PRIVATE
};

#ifdef __cplusplus
}
#endif

#endif //INCREMENTAL_COPYING_GC_H

