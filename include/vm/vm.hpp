

#ifndef VM_HPP
#define VM_HPP

#include <stdint.h>

#include "gc/gc.hpp"

//takes a parameter for the function
//to fetch a uint8_t from a uint8_t*
//this is so, e.g., on avr the program
//can be stored in program memory,
//and different instructions need to be
//used to acces that memory
template<typename Fetch>
class VM {
    
    //instruction pointer
    int8_t *ip_;
    
    //function to fetch instruction
    Fetch fetch_;
    
    //stack pointer(s)
    GC::Cell *stack_base_;
    GC::Cell *stack_;
    
    //stack size
    GC::Cell size_;
    
public:
    
    VM(Fetch fetch, GC::Cell *stack, GC::Cell size)
        : fetch_(fetch),
        ip_(nullptr), 
        stack_base_(stack), 
        stack_(stack), 
        size_(size) {}
    
    enum OpCode : int8_t {
        ADD,
        SUB,
        MUL,
        DIV,
        MOD,
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
        FRAME,      //frame must be used in order to store references on the stack
        UNFRAME     //remove a frame from the stack
    };
    
    void Execute(int8_t *code) {
        for (ip_ = code; ip_; ++ip_) {
            Dispatch(fetch_(ip_));
        }
    }
    
    void Dispatch(uint8_t instruction) {
        switch (instruction) {
            case ADD: {
                auto rhs = *stack_--;
                auto lhs = *stack_;
                *stack_ = lhs + rhs;
                break;
            }
            case SUB: {
                auto rhs = *stack_--;
                auto lhs = *stack_;
                *stack_ = lhs - rhs;
                break;
            }
            case MUL: {
                auto rhs = *stack_--;
                auto lhs = *stack_;
                *stack_ = lhs * rhs;
                break;
            }
            case DIV: {
                auto rhs = *stack_--;
                auto lhs = *stack_;
                *stack_ = lhs / rhs;
                break;
            }
            case MOD: {
                auto rhs = *stack_--;
                auto lhs = *stack_;
                *stack_ = lhs % rhs;
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
                ip_ += fetch_(ip_ + 1);
                break;
            case JUMPOZ:
                if (*stack_-- == 0) {
                    ip_ += fetch_(ip_ + 1);
                } else {
                    ip_++;
                }
                break;
            case PUSH:
                *++stack_ = fetch_(++ip_);
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

