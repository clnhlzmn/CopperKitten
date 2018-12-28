

#ifndef VM_HPP
#define VM_HPP

#include <stdio.h>
#include <stdint.h>

#include "gc/gc.hpp"

//takes a parameter for the function
//to Deref a uint8_t from a uint8_t*
//this is so, e.g., on avr the program
//can be stored in program memory,
//and different instructions need to be
//used to acces that memory
template<typename Deref>
class VM {
    
    //reference to a GC instance
    GC &gc_;
    
    //instruction pointer
    int8_t *ip_;
    
    //function to dereference instruction pointer
    Deref deref_;
    
    //stack pointer(s)
    intptr_t *sb_;
    intptr_t *sp_;
    
    //stack size
    intptr_t size_;
    
    //pointer to the current frame
    //to be used to create a stable
    //reference for finding locals, 
    //arguments, and captures in 
    //function activations
    intptr_t *frame_;
    
    //pointer to the ref chain
    //the ref chain is a singly linked list
    //embedded in the stack where each 
    //node is the cell below a reference cell
    //to be used to implement the root iterator
    //required by GC
    intptr_t *refs_;
    
public:
    
    VM(Deref deref, GC &gc, intptr_t *stack, intptr_t size)
        : gc_(gc),
        ip_(nullptr), 
        deref_(deref),
        sb_(stack), 
        sp_(stack), 
        size_(size),
        frame_(0),
        refs_(0) {}
    
    enum OpCode : int8_t {
        ADD,        //*(sp-2) = *(sp-2)  + *(sp-1); sp -= 1;
        SUB,        //*(sp-2) = *(sp-2)  - *(sp-1); sp -= 1;
        MUL,        //*(sp-2) = *(sp-2)  * *(sp-1); sp -= 1;
        DIV,        //*(sp-2) = *(sp-2)  / *(sp-1); sp -= 1;
        MOD,        //*(sp-2) = *(sp-2)  % *(sp-1); sp -= 1;
        SHL,        //*(sp-2) = *(sp-2) << *(sp-1); sp -= 1;
        SHR,        //*(sp-2) = *(sp-2) >> *(sp-1); sp -= 1;
        CMP,        //compare top two items on the stack [...|lhs|rhs]->[...|int] where int=-1 if lhs<rhs, 0 if lhs==rhs, 1 if lhs>rhs
        SKIPZ,      //if tos is zero then skip the next instruction
        IP,         //push the current instruction pointer onto the stack
        CALL,       //jump to the instruction pointer on the stack and leave the current instruction pointer on the stack
        RETURN,     //jump to the address on the stack
        PUSH,       //push the next byte in the instruction stream
        PUSHW,      //push the next word in the instruction stream
        DUP,        //duplicate the top value
        POP,        //pop the top value from the stack
        SWAP,       //swap the top two items on the stack
        PUSHREF,    //to indicate the next higher word on the stack is a reference
        POPREF,     //to pop the reference indicator
        ENTER,      //enter a stack frame
        LEAVE,      //leave a stack frame
        IN,         //read a byte from the console
        OUT,        //print a byte to the console
        ALLOC,      //[...|n|nrefs]->[...|ref], allocate n cells with nrefs references
        LOAD,       //[...|ref|index]->[...|value], get the cell at ref+index
        STORE,      //[...|ref|index|value]->[...], set the cell at ref+index
        NCALL,      //[...|N]->[...], call the native function N
        NOP,        //
    };
    
    void Execute(int8_t *code) {
        for (ip_ = code; ip_; ) {
            //increment ip_ here so its before Dispatch gets it (where it might be modified)
            Dispatch(deref_(ip_++)); 
        }
    }
    
private:
    
    //Rooterator implements the root iterator required for GC::Alloc
    class Rooterator {
        
        //head of the root list
        intptr_t *ref_;
        
    public:
    
        //create a Rooterator given the root list head
        Rooterator(intptr_t *ref)
            : ref_(ref) {}
        
        //return a reference to the cell above ref_ cast as a intptr_t*&
        intptr_t *&operator*() {
            return (intptr_t*&)*(ref_ + 1);
        }
        
        //equality
        bool operator== (const Rooterator &other) const {
            return ref_ == other.ref_;
        }
        
        //not equality
        bool operator!= (const Rooterator &other) const {
            return !(*this == other);
        }
        
        //next root (singly linked list traversal)
        Rooterator &operator++ () {
            ref_ = (intptr_t*)*ref_;
            return *this;
        }
        
    };
    
    void Dispatch(uint8_t instruction) {
        switch (instruction) {
            case ADD:
                *(sp_ - 2) = *(sp_ - 2) + *(sp_ - 1);
                sp_--;
                break;
            case SUB:
                *(sp_ - 2) = *(sp_ - 2) - *(sp_ - 1);
                sp_--;
                break;
            case MUL: 
                *(sp_ - 2) = *(sp_ - 2) * *(sp_ - 1);
                sp_--;
                break;
            case DIV:
                *(sp_ - 2) = *(sp_ - 2) / *(sp_ - 1);
                sp_--;
                break;
            case MOD:
                *(sp_ - 2) = *(sp_ - 2) % *(sp_ - 1);
                sp_--;
                break;
            case SHL:
                *(sp_ - 2) = *(sp_ - 2) << *(sp_ - 1);
                sp_--;
                break;
            case SHR:
                *(sp_ - 2) = *(sp_ - 2) >> *(sp_ - 1);
                sp_--;
                break;
            case CMP: 
                *(sp_ - 2) = *(sp_ - 2) < *(sp_ - 1) 
                                        ? -1 
                                        : *(sp_ - 2) > *(sp_ - 1) 
                                                     ? 1 
                                                     : 0;
                sp_--;
                break;
            case CALL: {
                auto temp = ip_;
                ip_ = (int8_t*)*(sp_ - 1);
                *(sp_ - 1) = (intptr_t)temp;
                break;
            }
            case RETURN:
                ip_ = (int8_t*)*(sp_ - 1);
                sp_--;
                break;
            case PUSH:
                *sp_ = deref_(ip_++);
                sp_++;
                break;
            case PUSHW: {
                intptr_t word = 0;
                for (int i = 0; i < sizeof(intptr_t); ++i) {
                    auto byte = (uint8_t)deref_(ip_++);
                    word |= byte << i * 8;
                }
                *sp_ = word;
                sp_++;
                break;
            }
            case DUP:
                *sp_ = *(sp_ - 1);
                sp_++;
                break;
            case POP:
                sp_--;
                break;
            case SWAP:
                *(sp_ - 2) = *(sp_ - 1);
                break;
            case HALT: 
                ip_ = nullptr;
                break;
            case PUSHREF:
                *sp_ = (intptr_t)refs_;
                refs_ = sp_;
                sp_++;
                break;
            case POPREF:
                refs_ = (intptr_t*)*(sp_ - 1);
                sp_--;
                break;
            case ENTER:
                *sp_ = (intptr_t)frame_;
                frame_ = sp_;
                sp_++;
                break;
            case LEAVE:
                frame_ = (intptr_t*)*(sp_ - 1);
                sp_--;
                break;
            case IN: {
                char c;
                scanf("%c", &c);
                *sp_ = c;
                sp_++;
                break;
            }
            case OUT:
                printf("%c", *(sp_ - 1));
                sp_--;
                break;
            case ALLOC: {
                auto begin = Rooterator(refs_);
                auto end = Rooterator(nullptr);
                *(sp_ - 2) = (intptr_t)gc_.Alloc(begin, end, *(sp_ - 2), *(sp_ - 1));
                sp_--;
                break;
            }
            case LOAD:
                *(sp_ - 2) = ((intptr_t*)*(sp_ - 2))[*(sp_ - 1)];
                sp_--;
                break;
            case STORE:
                ((intptr_t*)*(sp_ - 3))[*(sp_ - 2)] = *(sp_ - 1);
                sp_ -= 3;
                break;
            case NCALL: {
                auto fun = (void(*)(VM&))*(sp_-1);
                sp_--;
                fun(*this);
                break;
            }
            case NOP:
                break;
        }
    }
    
};

#endif //VM_HPP

