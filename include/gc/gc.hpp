

#ifndef GC_HPP
#define GC_HPP

#include <assert.h>
#include <stddef.h>
#include <stdint.h>

class GC {

public:

    //allocation is done in units of uintptr_t
    typedef intptr_t Cell;
    
private:

    //low order bit flag
    //refs means the high order bits are a count of refs
    static constexpr Cell REFS = 0;
    //forward means that a forwarding pointer is held in the high order bits
    static constexpr Cell FORWARD = 1;
    
    //an allocation looks like 
    //[size|type|ref0|ref1|...|refm-1|bytes0|bytes1|...|bytesn-1]
    //where size is the size (in units of Cell) of the entire allocation
    //the type field is layed out as follows
    //[data|flag]
    //where flag is one bit to indicate the meaning of data
    //if flag is REFS then data == (ref_count << 1)
    //where ref_count is the number of references following the type cell
    //the remaining cells are non references
   
    //allocation indices
    static constexpr Cell SIZE = 0;
    static constexpr Cell META = 1;
    static constexpr Cell USER = 2;
    
    //size of allocation meta
    static constexpr Cell META_SIZE = 2;
    
    //flag meta
    static constexpr Cell FLAG_BITS = 1;
    static constexpr Cell FLAG_MASK = 0x01;

    //get the size from an allocation pointer
    static Cell GetSize(Cell *cp) {
        return cp[SIZE];
    }
    
    //set the size of an allocation
    static void SetSize(Cell *cp, Cell size) {
        cp[SIZE] = size;
    }
    
    static Cell GetFlag(Cell *cp) {
        return cp[META] & FLAG_MASK;
    }
    
    static void SetFlag(Cell *cp, Cell flag) {
        //clear existing type code
        cp[META] &= ~FLAG_MASK;
        //set new one
        cp[META] |= (flag & FLAG_MASK);
    }
    
    static Cell GetRefCount(Cell *cp) {
        return cp[META] >> FLAG_BITS;
    }
    
    static void SetRefCount(Cell *cp, Cell ref_count) {
        cp[META] &= FLAG_MASK;
        cp[META] |= (ref_count << FLAG_BITS);
    }
    
    static Cell *GetForward(Cell *cp) {
        return (Cell*)(cp[META] & ~FLAG_MASK);
    }
    
    static void SetForward(Cell *cp, Cell *fp) {
        cp[META] &= FLAG_MASK;
        cp[META] |= ((Cell)fp & ~FLAG_MASK);
    }
    
    //convert an allocation pointer to a user pointer
    static Cell *GetUser(Cell *gc) {
        return &gc[USER];
    }
    
    //convert a user pointer to an allocation pointer
    static Cell *GetGC(Cell *user) {
        return &user[-USER];
    }
    
public:
    
    //get the size of user data from a pointer
    //returned by Alloc
    static Cell GetUserSize(Cell *cp) {
        return GetGC(cp)[SIZE] - META_SIZE;
    }
    
    //create a GC instance with the given memory of the given size
    GC(Cell *mem, size_t size) {
        size_ = size / 2;
        a_space_ = mem;
        b_space_ = mem + size_;
        InitPointers(a_space_, a_space_ + size_);
    }
    
    //allocate GC managed memory with the given size of user data and number of references
    template<typename RootIterator>
    Cell *Alloc(RootIterator begin, RootIterator end, Cell size, Cell ref_count) {
        auto ret = Alloc(begin, end, size + META_SIZE);
        SetSize(ret, size + META_SIZE);
        SetFlag(ret, REFS);
        SetRefCount(ret, ref_count);
        for (Cell i = 0; i < ref_count; ++i) {
            GetUser(ret)[i] = 0;
        }
        return GetUser(ret);
    }
    
private:

    //simple allocation of size cells with no checking
    Cell *JustAlloc(Cell size) {
        auto ret = alloc_ptr_;
        alloc_ptr_ += size;
        return ret;
    }

    //allocates n Cells, collecting if necessary
    template<typename RootIterator>
    Cell *Alloc(RootIterator begin, RootIterator end, Cell size) {
        //sz is number of Cells to allocate
        if ((alloc_end_ - alloc_ptr_) < size) {
            Collect(begin, end, size);
        }
        return JustAlloc(size);
    }
    
    //forward the allocation pointed to by cp
    //takes and returns gc pointers, that means
    //pointers must be converted when forwarding
    Cell *Forward(Cell *cp) {
        if (cp == nullptr) {
            return cp;
        } else if (GetFlag(cp) == FORWARD) {
            //cp points to an allocation that is already forwarded
            //return the forwarding address
            return GetForward(cp);
        } else {
            //cp needs to be forwarded
            //get size
            auto size = GetSize(cp);
            //copy size Cells from old to new space
            for (Cell i = 0; i < size; ++i) {
                alloc_ptr_[i] = cp[i];
            }
            //set the forward flag at cp
            SetFlag(cp, FORWARD);
            //set forward pointer to new space
            SetForward(cp, alloc_ptr_);
            auto ret = alloc_ptr_;
            //bump pointer in new space
            alloc_ptr_ += size;
            //return new location
            return ret;
        }
    }
    
    //perform collection freeing required_size cells
    template<typename RootIterator>
    void Collect(RootIterator begin, RootIterator end, Cell required_size) {
        //switch space
        if (alloc_end_ == a_space_ + size_) {
            InitPointers(b_space_, b_space_ + size_);
        } else {
            InitPointers(a_space_, a_space_ + size_);
        }
        //forward roots
        for (auto it = begin; it != end; ++it) {
            //*it is a user pointer, Forward takes GC pointers
            //so we must convert
            *it = GetUser(Forward(GetGC(*it)));
        }
        //forward references between scan_ptr_ and alloc_ptr_
        while (scan_ptr_ < alloc_ptr_) {
            for (Cell i = 0; i < GetRefCount(scan_ptr_); ++i) {
                auto user = (Cell*)GetUser(scan_ptr_)[i];
                GetUser(scan_ptr_)[i] = (Cell)GetUser(Forward(GetGC(user)));
            }
            scan_ptr_ += GetSize(scan_ptr_);
        }
        //check if collection freed enough space
        if ((alloc_end_ - alloc_ptr_) < required_size) {
            //out of memory
            assert(0);
        }
    }
    
    //initialize alloc_ptr_, scan_ptr_, alloc_begin_ and alloc_end_
    //with the given memory range
    void InitPointers(Cell *begin, Cell *end) {
        alloc_ptr_ = scan_ptr_ = alloc_begin_ = begin;
        alloc_end_ = end;
    }

    //from space and to space
    Cell *a_space_;
    Cell *b_space_;
    //size of each space (equal)
    size_t size_;
    
    //to keep track of allocation and 
    //collection
    Cell *alloc_ptr_ = nullptr;
    Cell *scan_ptr_ = nullptr;
    Cell *alloc_begin_ = nullptr;
    Cell *alloc_end_ = nullptr;
    
};

#endif //GC_HPP

