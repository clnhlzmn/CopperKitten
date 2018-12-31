

#ifndef VM_HPP
#define VM_HPP

#include <assert.h>
#include <stdio.h>
#include <stdint.h>

#include "gc.h"

#ifndef VM_DEREF_IP
#define VM_DEREF_IP(ip) (*(ip))
#endif

struct vm {
    struct gc *gc;
    int8_t *ip;
    intptr_t *sp;
    intptr_t *sb;
    intptr_t size;
    intptr_t *frame;
};

static inline void vm_init(
    struct vm *self, 
    struct gc *gc, 
    intptr_t *stack, 
    intptr_t size)
{
    assert(self);
    assert(gc);
    assert(stack);
    self->ip = NULL;
    self->gc = gc;
    self->sb = self->sp = stack;
    self->size = size;
    self->frame = NULL;
}

enum vm_op_code {
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
    FP,         //push the current frame pointer onto the stack
    JUMP,       //jump to the address on the stack
    PUSH,       //push the next byte in the instruction stream
    PUSHW,      //push the next word in the instruction stream
    DUP,        //duplicate the top value
    POP,        //pop the top value from the stack
    SWAP,       //swap the top two items on the stack
    ENTER,      //enter a stack frame
    LEAVE,      //leave a stack frame
    IN,         //read a byte from the console
    OUT,        //print a byte to the console
    ALLOC,      //[...|n|layout]->[...|ref], allocate n cells with given layout
    LOAD,       //[...|ref]->[...|value], get the cell at ref
    STORE,      //[...|ref|value]->[...], set the cell at ref
    NCALL,      //[...|N]->[...], call the native function N
    NOP,        //
};

static inline void vm_dispatch(struct vm *self, uint8_t instruction);

static inline void vm_execute(struct vm *self, int8_t *code) {
    for (self->ip = code; self->ip; ) {
        int8_t inst = VM_DEREF_IP(self->ip);
        //increment self->ip here so its before Dispatch gets it (where it might be modified)
        printf("vm_execute %p\r\n", self->ip);
        self->ip++;
        vm_dispatch(self, inst);
    }
}

static inline void vm_dispatch(struct vm *self, uint8_t instruction) {
    switch (instruction) {
        case ADD:
            *(self->sp - 2) = *(self->sp - 2) + *(self->sp - 1);
            self->sp--;
            break;
        case SUB:
            *(self->sp - 2) = *(self->sp - 2) - *(self->sp - 1);
            self->sp--;
            break;
        case MUL: 
            *(self->sp - 2) = *(self->sp - 2) * *(self->sp - 1);
            self->sp--;
            break;
        case DIV:
            *(self->sp - 2) = *(self->sp - 2) / *(self->sp - 1);
            self->sp--;
            break;
        case MOD:
            *(self->sp - 2) = *(self->sp - 2) % *(self->sp - 1);
            self->sp--;
            break;
        case SHL:
            *(self->sp - 2) = *(self->sp - 2) << *(self->sp - 1);
            self->sp--;
            break;
        case SHR:
            *(self->sp - 2) = *(self->sp - 2) >> *(self->sp - 1);
            self->sp--;
            break;
        case CMP: 
            *(self->sp - 2) 
                = *(self->sp - 2) < *(self->sp - 1) 
                                  ? -1 
                                  : *(self->sp - 2) > *(self->sp - 1) 
                                                    ? 1 
                                                    : 0;
            self->sp--;
            break;
        case SKIPZ: 
            if (*(self->sp - 1) == 0) {
                //increment self->ip and check special cases
                switch (VM_DEREF_IP(self->ip++)) {
                    case PUSH:
                        self->ip++;
                        break;
                    case PUSHW:
                        self->ip += sizeof(intptr_t);
                        break;
                    default: break;
                }
            }
            break;
        case IP:
            printf("ip: %p\r\n", self->ip);
            *self->sp = (intptr_t)self->ip;
            self->sp++;
            break;
        case FP:
            *self->sp = (intptr_t)self->frame;
            self->sp++;
            break;
        case JUMP: {
            int8_t *orig = self->ip;
            self->ip = (int8_t*)*(self->sp - 1);
            self->sp--;
            printf("jump: before=%p, after=%p\r\n", orig, self->ip);
            break;
        }
        case PUSH:
            *self->sp = VM_DEREF_IP(self->ip++);
            self->sp++;
            break;
        case PUSHW: {
            intptr_t word = 0;
            for (size_t i = 0; i < sizeof(intptr_t); ++i) {
                uint8_t byte = (uint8_t)VM_DEREF_IP(self->ip++);
                word |= byte << i * 8;
            }
            *self->sp = word;
            self->sp++;
            break;
        }
        case DUP:
            *self->sp = *(self->sp - 1);
            self->sp++;
            break;
        case POP:
            self->sp--;
            break;
        case SWAP:
            *(self->sp - 2) = *(self->sp - 1);
            break;
        case ENTER:
            *self->sp = (intptr_t)self->frame;
            self->frame = self->sp;
            self->sp++;
            *self->sp = 0;
            self->sp++;
            break;
        case LEAVE:
            self->sp--;
            self->frame = (intptr_t*)*(self->sp - 1);
            self->sp--;
            break;
        case IN: {
            char c;
            scanf("%c", &c);
            *self->sp = c;
            self->sp++;
            break;
        }
        case OUT:
            printf("%c", (int)*(self->sp - 1));
            self->sp--;
            break;
        case ALLOC:
            //TODO: foreach_t funtion for this guy
            *(self->sp - 2) = (intptr_t)gc_alloc_with_layout(self->gc, NULL, NULL, *(self->sp - 2), (void*)*(self->sp - 1));
            self->sp--;
            break;
        case LOAD:
            *(self->sp - 1) = *(intptr_t*)*(self->sp - 1);
            break;
        case STORE:
            *(intptr_t*)*(self->sp - 2) = *(self->sp - 1);
            self->sp -= 2;
            break;
        case NCALL: {
            void(*fun)(struct vm *) = (void(*)(struct vm *))*(self->sp-1);
            self->sp--;
            fun(self);
            break;
        }
        case NOP:
            break;
    }
}

#endif //VM_HPP

