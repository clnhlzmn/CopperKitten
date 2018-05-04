

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
    uint8_t *ip_;
    
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
    
    enum OpCode : uint8_t {
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
        PUSH,       //push the next byte in the instruction stream onto the stack
        PUSHW,      //push the next word (Cell) in the instruction stream onto the stack
        POP,        //pop the top value from the stack
        HALT,       //stop execution
        FRAME,      //frame must be used in order to store references on the stack
        UNFRAME     //remove a frame from the stack
    };
    
    void Execute(uint8_t *code) {
        for (ip_ = code; ip_; ++ip_) {
            uint8_t instruction = fetch_(ip_);
            Dispatch(instruction);
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
            case CALL:
                break;
            case RETURN:
                break;
            case JUMP:
                break;
            case PUSH:
                break;
            case PUSHW:
                break;
            case POP:
                break;
            case HALT: 
                ip_ = nullptr;
                break;
        }
    }
    
};

#endif //VM_HPP

