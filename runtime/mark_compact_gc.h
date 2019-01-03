

#ifndef MARK_COMPACT_GC_H
#define MARK_COMPACT_GC_H

#ifdef __cplusplus
extern "C" {
#endif

#include "gc_interface.h"

struct gc {
    uintptr_t *heap;
    size_t size;
    uintptr_t *alloc_ptr;
    uintptr_t *alloc_end;
};

#ifdef __cplusplus
}
#endif

#endif //MARK_COMPACT_GC_H

