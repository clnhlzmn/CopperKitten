

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
    
    //instruction pointer
    int8_t *ip_;
    
    //function to dereference instruction pointer
    Deref deref_;
    
    //stack pointer(s)
    GC::Cell *stack_base_;
    GC::Cell *stack_;
    
    //stack size
    GC::Cell size_;
    
    //pointer to the current register frame
    GC::Cell frame_;
    
    //registers, first half are for references
    static constexpr GC::Cell NREFS = 16;
    static constexpr GC::Cell NREGISTERS = 32;
    GC::Cell r_[NREGISTERS];
    
public:
    
    VM(Deref Deref, GC::Cell *stack, GC::Cell size)
        : deref_(Deref),
        ip_(nullptr), 
        stack_base_(stack), 
        stack_(stack), 
        size_(size) {
            for (auto &r : r_) {
                r = 0;
            }
        }
    
    enum OpCode : int8_t {
        ADD,        //rd=rl+rr
        SUB,        //rd=rl-rr
        MUL,        //rd=rl*rr
        DIV,        //rd=rl/rr
        MOD,        //rd=rl%rr
        CMP,        //compare top items on stack -1 if s-2<s-1 0 if s-2==s-1 and 1 if s-2>s-1
        CALL,       //jump to the instruction pointer on the stack and leave the current instruction pointer there
        RETURN,     //jump to the return address on the stack
        JUMP,       //unconditional jump to the address on the top of the stack
        JUMPZ,      //jump to the address second on the stack if the first value is zero
        JUMPO,      //jump to an offset stored in the next byte
        JUMPOZ,     //jump to an offset stored in the next byte if the top of stack is zero
        PUSH,       //push the next byte in the instruction stream onto the stack
        PUSHW,      //push the next word (Cell) in the instruction stream onto the stack
        POP,        //pop the top value from the stack
        HALT,       //stop execution
        PUSHREFS,   //push the current refs pointer and make a new one pointing there
        POPREFS     //pop the refs pointer
    };
    
    void Execute(int8_t *code) {
        for (ip_ = code; ip_; ++ip_) {
            Dispatch(deref_(ip_));
        }
    }
    
private:
    
    
    //Instruction encoding
    //first byte, opcode
    //opcode specific arguments are in the next bytes
    
    void Dispatch(uint8_t instruction) {
        switch (instruction) {
            case ADD: {
                auto dest = deref_(++ip_);
                auto lhs = deref_(++ip_);
                auto rhs = deref_(++ip_);
                r_[dest] = r_[lhs] + r_[rhs];
                break;
            }
            case SUB: {
                auto dest = deref_(++ip_);
                auto lhs = deref_(++ip_);
                auto rhs = deref_(++ip_);
                r_[dest] = r_[lhs] - r_[rhs];
                break;
            }
            case MUL: {
                auto dest = deref_(++ip_);
                auto lhs = deref_(++ip_);
                auto rhs = deref_(++ip_);
                r_[dest] = r_[lhs] * r_[rhs];
                break;
            }
            case DIV: {
                auto dest = deref_(++ip_);
                auto lhs = deref_(++ip_);
                auto rhs = deref_(++ip_);
                r_[dest] = r_[lhs] / r_[rhs];
                break;
            }
            case MOD: {
                auto dest = deref_(++ip_);
                auto lhs = deref_(++ip_);
                auto rhs = deref_(++ip_);
                r_[dest] = r_[lhs] % r_[rhs];
                break;
            }
            case CMP: {
                auto rhs = *stack_--;
                auto lhs = *stack_;
                *stack_ = lhs < rhs ? -1 : lhs > rhs ? 1 : 0;
                break;
            }
            case CALL: {
                auto temp = ip_;
                ip_ = (uint8_t*)*stack_;
                *stack_ = temp;
                break;
            }
            case RETURN:
            case JUMP:
                ip_ = (uint8_t*)*stack_--;
                break;
            case JUMPZ:
                if (*stack_-- == 0) {
                    ip_ = (uint8_t*)*stack_--;
                } else {
                    stack_--;
                }
                break;
            case JUMPO:
                ip_ += deref_(ip_ + 1);
                break;
            case JUMPOZ:
                if (*stack_-- == 0) {
                    ip_ += deref_(ip_ + 1);
                } else {
                    ip_++;
                }
                break;
            case PUSH:
                *++stack_ = deref_(++ip_);
                break;
            case PUSHW:
                //TODO: make a Cell from bytes in the instruction stream
                break;
            case POP:
                stack_--;
                break;
            case HALT: 
                ip_ = nullptr;
                break;
        }
    }
    
};

#endif //VM_HPP

