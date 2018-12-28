

//GC is meant to provide a generic interface for
//allocating managed memory.

//Memory is allocated in units of intptr_t.
//Allocated memory can be declared reference or not.
//A reference in this case refers to a pointer
//to another GC allocation. GC uses two intptr_t
//to maintain allocation state. One is for the 
//size (in intptr_t) and the other contains a single
//bit flag to indicate if the intptr_t contains a 
//count of references, or a forwarding pointer.
//All references must be in the lower memory address 
//area of the allocation like below
//ptr->|                ptr+6->|
//     [ref|ref|ref|bytes|bytes|bytes]
//in the case above the ref count would be 3 and
//the allocation size is 6 (not including metadata)

#include <assert.h>
#include <stdio.h>

#include "gc.h"

//definitions for layouts*************************************************************
intptr_t layout_ref_array[] = {LAYOUT_REF_ARRAY};

intptr_t layout_int_array[] = {LAYOUT_INT_ARRAY};

intptr_t layout_bitmap_example[] = {LAYOUT_BITMAP};

//meta flag meanings*****************************************************************
//refs means the high order bits are a layout pointer
#define LAYOUT 0
//forward means that a forwarding pointer is held in the high order bits
#define FORWARD 1

//an allocation looks like *********************************************************
//[size|type|ref0|ref1|...|refm-1|bytes0|bytes1|...|bytesn-1]
//where size is the size (in units of intptr_t) of the entire allocation
//the type field is layed out as follows
//[data|flag]
//where flag is one bit to indicate the meaning of data
//if flag is LAYOUT then data == (ref_count << 1)
//where ref_count is the number of references following the type intptr_t
//the remaining intptr_ts are non references

//allocation indices****************************************************************
#define SIZE 0
#define META 1
#define USER 2

//size of allocation meta
#define META_SIZE 2

//flag meta
#define FLAG_BITS 1
#define FLAG_MASK 0x01

//get the size from an allocation pointer
static inline intptr_t get_alloc_size(intptr_t *cp) {
    return cp[SIZE];
}

//set the size of an allocation
static inline void set_alloc_size(intptr_t *cp, intptr_t size) {
    cp[SIZE] = size;
}

//get the value of the flag bit
static inline intptr_t get_meta_flag(intptr_t *cp) {
    return cp[META] & FLAG_MASK;
}

//set flag bit
static inline void set_meta_flag(intptr_t *cp, intptr_t flag) {
    //clear existing type code
    cp[META] &= ~FLAG_MASK;
    //set new one
    cp[META] |= (flag & FLAG_MASK);
}

//an int from the meta field
static inline intptr_t get_meta_int(intptr_t *cp) {
    return cp[META] >> FLAG_BITS;
}

//set an int in the meta field
static inline void set_meta_int(intptr_t *cp, intptr_t ref_count) {
    cp[META] &= FLAG_MASK;
    cp[META] |= (ref_count << FLAG_BITS);
}

//get pointer from the meta field
static inline intptr_t *get_meta_ptr(intptr_t *cp) {
    return (intptr_t*)(cp[META] & ~FLAG_MASK);
}

//set a pointer in the meta field
static inline void set_meta_ptr(intptr_t *cp, intptr_t *fp) {
    cp[META] &= FLAG_MASK;
    cp[META] |= ((intptr_t)fp & ~FLAG_MASK);
}

//convert an allocation pointer to a user pointer
static inline intptr_t *get_user_ptr(intptr_t *gc) {
    return gc + USER;
}

//convert a user pointer to an allocation pointer
static inline intptr_t *get_gc_ptr(intptr_t *user) {
    return user - USER;
}

static void gc_init_pointers(struct gc *, intptr_t *, intptr_t *);

//create a GC instance with the given memory of the given size
void gc_init(struct gc *self, intptr_t *mem, size_t size) {
    printf("gc_init: self = %p\r\n", self);
    assert(mem);
    self->size = size / 2;
    self->a_space = mem;
    self->b_space = mem + self->size;
    gc_init_pointers(self, self->a_space, self->a_space + self->size);
}

//forward the allocation pointed to by cp
//takes and returns gc pointers, that means
//pointers must be converted when forwarding
static inline intptr_t *gc_forward(struct gc *self, intptr_t *cp) {
    printf("gc_forward: self = %p, cp = %p\r\n", self, cp);
    if (cp == NULL) {
        //nothing to do
        return cp;
    } else if (get_meta_flag(cp) == FORWARD) {
        //cp points to an allocation that is already forwarded
        //return the forwarding address
        return get_meta_ptr(cp);
    } else {
        //cp needs to be forwarded
        //get size
        intptr_t size = get_alloc_size(cp);
        //copy size Cells from old to new space
        for (intptr_t i = 0; i < size; ++i) {
            self->alloc_ptr[i] = cp[i];
        }
        //set the forward flag at cp
        set_meta_flag(cp, FORWARD);
        //set forward pointer to new space
        set_meta_ptr(cp, self->alloc_ptr);
        //save the location
        intptr_t *ret = self->alloc_ptr;
        //bump pointer for next call
        self->alloc_ptr += size;
        //return new location
        return ret;
    }
}

static inline void gc_forward_root(intptr_t **it, void *data) {
    printf("gc_forward_root: self = %p, root = %p\r\n", data, it);
    *it = get_user_ptr(gc_forward((struct gc*)data, get_gc_ptr(*it)));
}

//perform collection freeing required_size cells
static inline void gc_collect(struct gc *self, roots_foreach_t roots_foreach, void *data, intptr_t required_size) {
    //switch space
    if (self->alloc_end == self->a_space + self->size) {
        gc_init_pointers(self, self->b_space, self->b_space + self->size);
    } else {
        gc_init_pointers(self, self->a_space, self->a_space + self->size);
    }
    //forward roots
    /*for (auto it = begin; it != end; ++it) {*/
        /*//it is a user pointer, gc_forward takes GC pointers*/
        /**it = get_user_ptr(gc_forward(get_gc_ptr(*it)));*/
    /*}*/
    roots_foreach(gc_forward_root, self, data);
    //forward references between scan_ptr_ and alloc_ptr_
    while (self->scan_ptr < self->alloc_ptr) {
        if (get_meta_flag(self->scan_ptr) == LAYOUT) {
            //something to do, check for references
            if (get_meta_ptr(self->scan_ptr) == layout_ref_array) {
                //for each ref in ref array
                for (intptr_t i = 0; i < get_alloc_size(self->scan_ptr) - META_SIZE; ++i) {
                    //get the user pointer from the ref area at scan_ptr_
                    intptr_t *user = (intptr_t*)get_user_ptr(self->scan_ptr)[i];
                    //then get the gc pointer for it, forward it and return the forwarded
                    //value to its place in the scan_ptr_allocation
                    get_user_ptr(self->scan_ptr)[i] = (intptr_t)get_user_ptr(gc_forward(self, get_gc_ptr(user)));
                }
            } else if (get_meta_ptr(self->scan_ptr)[0] == LAYOUT_BITMAP) {
                assert(0);
                //TODO: forward refs found using bitmap
            } //TODO: other layout options
            //else there are no refs to forward because scan_ptr_ points to something with no refs
        }
        //done with refs so bump scan_ptr_ to next allocation
        self->scan_ptr += get_alloc_size(self->scan_ptr);
    }
    //check if collection freed enough space
    if ((self->alloc_end - self->alloc_ptr) < required_size) {
        //out of memory
        assert(0);
    }
}

//simple allocation of size cells with no checking
static inline intptr_t *gc_alloc_unchecked(struct gc *self, intptr_t size) {
    intptr_t *ret = self->alloc_ptr;
    self->alloc_ptr += size;
    return ret;
}

//allocates n Cells, collecting if necessary
static inline intptr_t *gc_alloc(struct gc *self, roots_foreach_t foreach, void *data, intptr_t size) {
    //sz is number of Cells to allocate
    if ((self->alloc_end - self->alloc_ptr) < size) {
        gc_collect(self, foreach, data, size);
    }
    return gc_alloc_unchecked(self, size);
}

//allocate GC managed memory with custom layout
intptr_t *gc_alloc_with_layout(struct gc *self, roots_foreach_t foreach, void *data, intptr_t size, intptr_t *layout) {
    intptr_t *ret = gc_alloc(self, foreach, data, size + META_SIZE);
    set_alloc_size(ret, size + META_SIZE);
    set_meta_flag(ret, LAYOUT);
    set_meta_ptr(ret, layout);
    //clear refs using locations in layouts
    if (layout == layout_ref_array) {
        for (intptr_t i = 0; i < size; ++i) {
            get_user_ptr(ret)[i] = 0;
        }
    } else if (layout[0] == LAYOUT_BITMAP) {
        assert(0);
    }//TODO: clear refs using other layout types
    return get_user_ptr(ret);
}

//allocate GC managed array of refs
intptr_t *gc_alloc_ref_array(struct gc *self, roots_foreach_t foreach, void *data, intptr_t size) {
    return gc_alloc_with_layout(self, foreach, data, size, layout_ref_array);
}

//allocate GC managed array of ints (not set to zero)
intptr_t *gc_alloc_int_array(struct gc *self, roots_foreach_t foreach, void *data, intptr_t size) {
    return gc_alloc_with_layout(self, foreach, data, size, layout_int_array);
}

//get the size of user data from a pointer
//returned by gc_alloc
intptr_t gc_get_size(intptr_t *cp) {
    return get_gc_ptr(cp)[SIZE] - META_SIZE;
}

//initialize alloc_ptr_, scan_ptr_, alloc_begin_ and alloc_end_
//with the given memory range
static inline void gc_init_pointers(struct gc *self, intptr_t *begin, intptr_t *end) {
    self->alloc_ptr = self->scan_ptr = self->alloc_begin = begin;
    self->alloc_end = end;
}

