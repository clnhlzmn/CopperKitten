

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

#ifndef GC_HPP
#define GC_HPP

#include <assert.h>
#include <stddef.h>
#include <stdint.h>

class GC {

    //low order bit flag
    //refs means the high order bits are a count of refs
    static constexpr intptr_t REFS = 0;
    //forward means that a forwarding pointer is held in the high order bits
    static constexpr intptr_t FORWARD = 1;
    
    //an allocation looks like 
    //[size|type|ref0|ref1|...|refm-1|bytes0|bytes1|...|bytesn-1]
    //where size is the size (in units of intptr_t) of the entire allocation
    //the type field is layed out as follows
    //[data|flag]
    //where flag is one bit to indicate the meaning of data
    //if flag is REFS then data == (ref_count << 1)
    //where ref_count is the number of references following the type intptr_t
    //the remaining intptr_ts are non references
   
    //allocation indices
    static constexpr intptr_t SIZE = 0;     //meta
    static constexpr intptr_t META = 1;     //meta
    static constexpr intptr_t USER = 2;     //user data
    
    //size of allocation meta
    static constexpr intptr_t META_SIZE = 2;
    
    //flag meta
    static constexpr intptr_t FLAG_BITS = 1;
    static constexpr intptr_t FLAG_MASK = 0x01;

    //get the size from an allocation pointer
    static intptr_t GetAllocSize(intptr_t *cp) {
        return cp[SIZE];
    }
    
    //set the size of an allocation
    static void SetAllocSize(intptr_t *cp, intptr_t size) {
        cp[SIZE] = size;
    }
    
    //get the value of the flag bit
    static intptr_t GetFlag(intptr_t *cp) {
        return cp[META] & FLAG_MASK;
    }
    
    //set flag bit
    static void SetFlag(intptr_t *cp, intptr_t flag) {
        //clear existing type code
        cp[META] &= ~FLAG_MASK;
        //set new one
        cp[META] |= (flag & FLAG_MASK);
    }
    
    //get ref count value (flag == REFS)
    static intptr_t GetRefCount(intptr_t *cp) {
        return cp[META] >> FLAG_BITS;
    }
    
    //set ref count (flas == REFS)
    static void SetRefCount(intptr_t *cp, intptr_t ref_count) {
        cp[META] &= FLAG_MASK;
        cp[META] |= (ref_count << FLAG_BITS);
    }
    
    //get forwarding pointer (flag == FORWARD)
    static intptr_t *GetForward(intptr_t *cp) {
        return (intptr_t*)(cp[META] & ~FLAG_MASK);
    }
    
    //set forwarding pointer (flag == FORWARD)
    static void SetForward(intptr_t *cp, intptr_t *fp) {
        cp[META] &= FLAG_MASK;
        cp[META] |= ((intptr_t)fp & ~FLAG_MASK);
    }
    
    //convert an allocation pointer to a user pointer
    static intptr_t *GetUserPtr(intptr_t *gc) {
        return gc + USER;
    }
    
    //convert a user pointer to an allocation pointer
    static intptr_t *GetGCPtr(intptr_t *user) {
        return user - USER;
    }
    
public:
    
    //create a GC instance with the given memory of the given size
    GC(intptr_t *mem, size_t size) {
        size_ = size / 2;
        a_space_ = mem;
        b_space_ = mem + size_;
        InitPointers(a_space_, a_space_ + size_);
    }
    
    //allocate GC managed memory with the given size of user data and number of references
    template<typename RootIterator>
    intptr_t *Alloc(RootIterator begin, RootIterator end, intptr_t size, intptr_t ref_count) {
        auto ret = Alloc(begin, end, size + META_SIZE);
        SetAllocSize(ret, size + META_SIZE);
        SetFlag(ret, REFS);
        SetRefCount(ret, ref_count);
        for (intptr_t i = 0; i < ref_count; ++i) {
            GetUserPtr(ret)[i] = 0;
        }
        return GetUserPtr(ret);
    }
    
    //get the size of user data from a pointer
    //returned by Alloc
    static intptr_t GetSize(intptr_t *cp) {
        return GetGCPtr(cp)[SIZE] - META_SIZE;
    }
    
private:

    //simple allocation of size cells with no checking
    intptr_t *Alloc(intptr_t size) {
        auto ret = alloc_ptr_;
        alloc_ptr_ += size;
        return ret;
    }

    //allocates n Cells, collecting if necessary
    template<typename RootIterator>
    intptr_t *Alloc(RootIterator begin, RootIterator end, intptr_t size) {
        //sz is number of Cells to allocate
        if ((alloc_end_ - alloc_ptr_) < size) {
            Collect(begin, end, size);
        }
        return Alloc(size);
    }
    
    //forward the allocation pointed to by cp
    //takes and returns gc pointers, that means
    //pointers must be converted when forwarding
    intptr_t *Forward(intptr_t *cp) {
        if (cp == nullptr) {
            //nothing to do
            return cp;
        } else if (GetFlag(cp) == FORWARD) {
            //cp points to an allocation that is already forwarded
            //return the forwarding address
            return GetForward(cp);
        } else {
            //cp needs to be forwarded
            //get size
            auto size = GetAllocSize(cp);
            //copy size Cells from old to new space
            for (intptr_t i = 0; i < size; ++i) {
                alloc_ptr_[i] = cp[i];
            }
            //set the forward flag at cp
            SetFlag(cp, FORWARD);
            //set forward pointer to new space
            SetForward(cp, alloc_ptr_);
            //save the location
            auto ret = alloc_ptr_;
            //bump pointer for next call
            alloc_ptr_ += size;
            //return new location
            return ret;
        }
    }
    
    //perform collection freeing required_size cells
    template<typename RootIterator>
    void Collect(RootIterator begin, RootIterator end, intptr_t required_size) {
        //switch space
        if (alloc_end_ == a_space_ + size_) {
            InitPointers(b_space_, b_space_ + size_);
        } else {
            InitPointers(a_space_, a_space_ + size_);
        }
        //forward roots
        for (auto it = begin; it != end; ++it) {
            //*it is a user pointer, Forward takes GC pointers
            *it = GetUserPtr(Forward(GetGCPtr(*it)));
        }
        //forward references between scan_ptr_ and alloc_ptr_
        while (scan_ptr_ < alloc_ptr_) {
            //for each ref in user data
            for (intptr_t i = 0; i < GetRefCount(scan_ptr_); ++i) {
                //get the user pointer from the ref area at scan_ptr_
                auto user = (intptr_t*)GetUserPtr(scan_ptr_)[i];
                //then get the gc pointer for it, forward it and return the forwarded
                //value to its place in the scan_ptr_allocation
                GetUserPtr(scan_ptr_)[i] = (intptr_t)GetUserPtr(Forward(GetGCPtr(user)));
            }
            //done with refs so bump scan_ptr_ to next allocation
            scan_ptr_ += GetAllocSize(scan_ptr_);
        }
        //check if collection freed enough space
        if ((alloc_end_ - alloc_ptr_) < required_size) {
            //out of memory
            assert(0);
        }
    }
    
    //initialize alloc_ptr_, scan_ptr_, alloc_begin_ and alloc_end_
    //with the given memory range
    void InitPointers(intptr_t *begin, intptr_t *end) {
        alloc_ptr_ = scan_ptr_ = alloc_begin_ = begin;
        alloc_end_ = end;
    }

    //from space and to space
    intptr_t *a_space_;
    intptr_t *b_space_;
    //size of each space (equal)
    size_t size_;
    
    //to keep track of allocation and 
    //collection
    intptr_t *alloc_ptr_ = nullptr;
    intptr_t *scan_ptr_ = nullptr;
    intptr_t *alloc_begin_ = nullptr;
    intptr_t *alloc_end_ = nullptr;
    
};

#endif //GC_HPP

