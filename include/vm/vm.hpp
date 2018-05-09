

#ifndef VM_HPP
#define VM_HPP

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
    GC::Cell *stack_base_;
    GC::Cell *sp_;
    
    //stack size
    GC::Cell size_;
    
    //pointer to the current frame
    GC::Cell frame_;
    
    //pointer to the ref chain
    GC::Cell refs_;
    
public:
    
    VM(Deref Deref, GC &gc, GC::Cell *stack, GC::Cell size)
        : deref_(Deref),
        gc_(gc),
        ip_(nullptr), 
        stack_base_(stack), 
        sp_(stack), 
        size_(size),
        frame_(nullptr),
        refs_(nullptr) {
            for (auto &r : r_) {
                r = 0;
            }
        }
    
    enum OpCode : int8_t {
        ADD,        //
        SUB,        //rd=rl-rr
        MUL,        //rd=rl*rr
        DIV,        //rd=rl/rr
        MOD,        //rd=rl%rr
        CMP,        //compare top two items on the stack [...|lhs|rhs]->[...|int] where int=-1 if lhs<rhs, 0 if lhs==rhs, 1 if lhs>rhs
        CALL,       //jump to the instruction pointer on the stack and leave the current instruction pointer on the stack
        RETURN,     //jump to the address on the stack
        JUMP,       //jump to the address on the stack
        JUMPZ,      //[...|a|t]->[...] if t==0 ip=a
        PUSH,       //push the next byte in the instruction stream
        PUSHW,      //push the next word in the instruction stream
        POP,        //pop the top value from the stack
        HALT,       //stop execution
        REF,        //to indicate the next higher word on the stack is a reference
        ENTER,      //enter a stack frame
        LEAVE,      //leave a stack frame
        IN,         //read a byte from the console
        OUT,        //print a byte to the console
    };
    
    void Execute(int8_t *code) {
        for (ip_ = code; ip_; ) {
            //increment ip_ here so its before Dispatch gets it (where it might be modified)
            Dispatch(deref_(ip_++)); 
        }
    }
    
private:
    
    
    //Instruction encoding
    //first byte, opcode
    //opcode specific arguments are in the next bytes
    
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
            case CMP: {
                auto lhs = *(sp_ - 2);
                auto rhs = *(sp_ - 1);
                *(sp_ - 2) = lhs < rhs ? -1 : lhs > rhs ? 1 : 0;
                sp_--;
                break;
            }
            case CALL: {
                auto temp = ip_;
                ip_ = (uint8_t*)*(sp_ - 1);
                *(sp_ - 1) = temp;
                break;
            }
            case RETURN:
            case JUMP:
                ip_ = (uint8_t*)*(sp_ - 1);
                sp_--;
                break;
            case JUMPZ:
                if (*(sp_ - 1) == 0) {
                    ip_ = (uint8_t*)*(sp_ - 2);
                }
                sp_ -= 2;
                break;
            case PUSH:
                *sp_ = deref_(ip_++);
                sp_++;
                break;
            case PUSHW: {
                GC::Cell word;
                for (int i = 0; i < sizeof(GC::Cell); ++i) {
                    word <<= 8;
                    word |= deref_(ip_++);
                }
                *sp_ = word;
                sp_++;
                break;
            }
            case POP:
                sp_--;
                break;
            case HALT: 
                ip_ = nullptr;
                break;
            case ENTER:
                break;
            case LEAVE:
                break;
            
        }
    }
    
};

#endif //VM_HPP

