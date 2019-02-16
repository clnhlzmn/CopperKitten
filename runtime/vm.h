

#ifndef VM_HPP
#define VM_HPP

#include <assert.h>
#include <stdio.h>
#include <stdint.h>

#include "gc_interface.h"

#ifndef VM_DEREF_IP
#define VM_DEREF_IP(ip) (*(ip))
#endif

struct vm {
    struct gc *gc;
    uint8_t *ip;
    intptr_t *sp;
    size_t size;
    intptr_t *fp;
};

static inline void vm_init(
    struct vm *self, 
    struct gc *gc, 
    intptr_t *stack, 
    size_t size)
{
    assert(self);
    assert(gc);
    assert(stack);
    self->ip = NULL;
    self->gc = gc;
    self->size = size;
    self->fp = NULL;
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
    JUMP,       //jump to the address following JMP
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
    ALLOC,      //[...|size]->[...|ref], allocate n cells with the given layout. size is on stack, layout is in instruction stream
    LLOAD,      //[...]->[...|value], get the word at fp+index, index is the word following instruction
    LSTORE,     //[...value]->[...], set the word at fp+index to the given value
    RLOAD,      //[...|ref]->[...|value], get the word at ref+index
    RSTORE,     //[...|ref|value]->[...], set the word at ref+index to the given value
    ALOAD,      //same as lload but for function arguments
    ASTORE,
    CLOAD,      //same as lload but for function captures
    CSTORE,
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

//gets a word from the current instruction pointer
//increments instruction pointer
//technically causes undefined behavior, but should 
//work on machines that use twos complement for ints
static inline intptr_t vm_get_word(struct vm *self) {
    intptr_t word = 0;
    for (size_t i = 0; i < sizeof(intptr_t); ++i) {
        uint8_t byte = VM_DEREF_IP(self->ip++);
        word |= (intptr_t)byte << i * 8;
    }
    return word;
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
        case JUMP: 
            self->ip = (uint8_t*)vm_get_word(self);
            break;
        case JUMPZ:
            if (!*(self->sp - 1)) {
                self->ip = (uint8_t*)vm_get_word(self);
            } else {
                vm_get_word(self);
            }
            self->sp--;
            break;
        case JUMPNZ:
            if (*(self->sp - 1)) {
                self->ip = (uint8_t*)vm_get_word(self);
            } else {
                vm_get_word(self);
            }
            self->sp--;
            break;
        case PUSH: 
            *self->sp = vm_get_word(self);
            self->sp++;
            break;
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
            *self->sp = (intptr_t)self->fp;
            self->fp = self->sp;
            self->sp++;
            *self->sp = 0;
            self->sp++;
            break;
        case LEAVE:
            self->sp--;
            self->fp = (intptr_t*)*(self->sp - 1);
            self->sp--;
            break;
        case IN: {
            char c;
            sscanf_s("%c", &c, 1);
            *self->sp = c;
            self->sp++;
            break;
        }
        case OUT:
            printf("%c", (int)*(self->sp - 1));
            self->sp--;
            break;
        case LAYOUT:
            //set the first cell in the frame to the layout function pointer following ip
            *(self->fp + 1) = vm_get_word(self);
            break;
        case ALLOC:
            //TODO: foreach_t funtion for this guy
            *(self->sp - 2) = (intptr_t)gc_alloc_with_layout(self->gc, NULL, NULL, *(self->sp - 2), (void*)*(self->sp - 1));
            self->sp--;
            break;
        case LLOAD:
            *self->sp = *(self->fp + vm_get_word(self) + 1);
            self->sp++;
            break;
        case LSTORE:
            *(self->fp + vm_get_word(self) + 1) = *(self->sp - 1);
            self->sp--;
            break;
        case RLOAD:
            //TODO: gc_read_barrier
            *(self->sp - 1) = *((intptr_t*)*(self->sp - 1) + vm_get_word(self));
            break;
        case RSTORE:
            //TODO: gc_write_barrier
            *((intptr_t*)*(self->sp - 2) + vm_get_word(self)) = *(self->sp - 1);
            self->sp--;
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

