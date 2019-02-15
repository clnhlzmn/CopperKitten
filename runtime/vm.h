

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
    uint8_t *ip;
    uintptr_t *sp;
    uintptr_t *sb;
    uintptr_t size;
    uintptr_t *frame;
};

static inline void vm_init(
    struct vm *self, 
    struct gc *gc, 
    uintptr_t *stack, 
    uintptr_t size)
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
    ADD,        //[...|lhs|rhs]->[...|lhs+rhs]
    SUB,        //[...|lhs|rhs]->[...|lhs-rhs]
    MUL,        //[...|lhs|rhs]->[...|lhs*rhs]
    DIV,        //[...|lhs|rhs]->[...|lhs/rhs]
    MOD,        //[...|lhs|rhs]->[...|lhs%rhs]
    SHL,        //[...|lhs|rhs]->[...|lhs<<rhs]
    SHR,        //[...|lhs|rhs]->[...|lhs>>rhs]
    CMP,        //[...|lhs|rhs]->[...|lhs<rhs?-1:lhs>rhs?1:0]
    CALL,       //jump to the address on the stack and push current address
    RET,        //pop address from the stack and jump to it
    JUMP,       //jump to the address formed from pc+offset where offset is the word following JMP
    JUMPZ,      //same as JMP if tos is zero
    JUMPNZ,     //same as JMP if toz is not zero
    PUSH,       //push the next word in the instruction stream
    DUP,        //duplicate the top value
    POP,        //pop the top value from the stack
    SWAP,       //swap the top two items on the stack
    ENTER,      //enter a stack frame
    LEAVE,      //leave a stack frame
    IN,         //read a byte from the console
    OUT,        //print a byte to the console
    LAYOUT,     //[...]->[...], set the frame layout from the next word in the program
    ALLOC,      //[...]->[...|ref], allocate n cells with the given layout where n is the next word in the instruction stream and layout is after that
    FLOAD,      //[...|index]->[...|value], get the word at fp+index
    FSTORE,     //[...|index|value]->[...], set the word at fp+index to the given value
    RLOAD,      //[...|ref|index]->[...|value], get the word at ref+index
    RSTORE,     //[...|ref|index|value]->[...], set the word at ref+index to the given value
    NCALL,      //[...|N]->[...], call the native function N i.e. (void(*)(void))*(sp-1)();
    NOP,        //
};

static inline void vm_dispatch(struct vm *self, uint8_t instruction);

static inline void vm_execute(struct vm *self, uint8_t *code) {
    for (self->ip = code; self->ip; ) {
        uint8_t inst = VM_DEREF_IP(self->ip);
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
                        self->ip += sizeof(uintptr_t);
                        break;
                    default: break;
                }
            }
            break;
        case IP:
            printf("ip: %p\r\n", self->ip);
            *self->sp = (uintptr_t)self->ip;
            self->sp++;
            break;
        case FP:
            *self->sp = (uintptr_t)self->frame;
            self->sp++;
            break;
        case JUMP: {
            uint8_t *orig = self->ip;
            self->ip = (uint8_t*)*(self->sp - 1);
            self->sp--;
            printf("jump: before=%p, after=%p\r\n", orig, self->ip);
            break;
        }
        case PUSH: {
            uintptr_t word = 0;
            for (size_t i = 0; i < sizeof(uintptr_t); ++i) {
                uint8_t byte = (uint8_t)VM_DEREF_IP(self->ip++);
                word |= (intptr_t)byte << i * 8;
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
            *self->sp = (uintptr_t)self->frame;
            self->frame = self->sp;
            self->sp++;
            *self->sp = 0;
            self->sp++;
            break;
        case LEAVE:
            self->sp--;
            self->frame = (uintptr_t*)*(self->sp - 1);
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
        case LAYOUT:
            //set the first cell in the frame to the layout function pointer at tos
            *(self->frame + 1) = *(self->sp - 1)
            self->sp--;
            break;
        case ALLOC:
            //TODO: foreach_t funtion for this guy
            *(self->sp - 2) = (uintptr_t)gc_alloc_with_layout(self->gc, NULL, NULL, *(self->sp - 2), (void*)*(self->sp - 1));
            self->sp--;
            break;
        case FPLOAD:
            *(self->sp - 1) = *(self->fp + *(self->sp - 1));
            self->sp--;
            break;
        case FPSTORE:
            *(self->fp + *(self->sp - 2)) = *(self->sp - 1);
            self->sp -= 2;
            break;
        case RLOAD:
            //TODO: gc_read_barrier
            *(self->sp - 2) = *((uintptr_t*)*(self->sp - 2) + *(self->sp - 1));
            self->sp--;
            break;
        case RSTORE:
            //TODO: gc_write_barrier
            *((uintptr_t*)*(self->sp - 3) + *(self->sp - 2)) = *(self->sp - 1);
            self->sp -= 3;
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

