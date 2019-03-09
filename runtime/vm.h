

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
    struct gc *gc;          //pointer to gc instance
    uint8_t *program;       //pointer to program
    uint8_t *ip;            //pointer to next instruction
    intptr_t *sp;           //pointer to one above tos
    intptr_t *fp;           //pointer to stack frame
    void **functions;       //pointer to array of functions (some layout, some external)
    intptr_t temp;          //temporary register (not a root)
};

static inline void vm_init(
    struct vm *self,
    struct gc *gc,
    intptr_t *stack,
    void **functions)
{
    assert(self);
    assert(gc);
    assert(stack);
    self->program = NULL;
    self->ip = NULL;
    self->sp = stack;
    self->gc = gc;
    self->fp = NULL;
    self->functions = functions;
}

enum vm_op_code {
    ADD,        //[...|lhs|rhs]->[...|lhs+rhs]
    SUB,        //[...|lhs|rhs]->[...|lhs-rhs]
    MUL,        //[...|lhs|rhs]->[...|lhs*rhs]
    DIV,        //[...|lhs|rhs]->[...|lhs/rhs]
    MOD,        //[...|lhs|rhs]->[...|lhs%rhs]
    SHL,        //[...|lhs|rhs]->[...|lhs<<rhs]
    SHR,        //[...|lhs|rhs]->[...|lhs>>rhs]
    NEG,        //[...|op]->[...|-op]
    NOT,        //[...|op]->[...|!op]
    BITNOT,     //[...|op]->[...|~op]
    BITAND,     //[...|lhs|rhs]->[...|lhs&rhs]
    BITXOR,     //[...|lhs|rhs]->[...|lhs^rhs]
    BITOR,      //[...|lhs|rhs]->[...|lhs|rhs]
    LT,         //[...|lhs|rhs]->[...|lhs<rhs]
    LTE,        //[...|lhs|rhs]->[...|lhs<=rhs]
    GT,         //[...|lhs|rhs]->[...|lhs>rhs]
    GTE,        //[...|lhs|rhs]->[...|lhs>=rhs]
    EQ,         //[...|lhs|rhs]->[...|lhs==rhs]
    NEQ,        //[...|lhs|rhs]->[...|lhs!=rhs]
    CMP,        //[...|lhs|rhs]->[...|lhs<rhs?-1:lhs>rhs?1:0]
    CALL,       //jump to the address on the stack and push current address
    RETURN,        //pop address from the stack and jump to it
    JUMP,       //jump to the address following JMP
    JUMPZ,      //same as JMP if tos is zero
    JUMPNZ,     //same as JMP if toz is not zero
    PUSH,       //push the next word in the instruction stream
    DUP,        //duplicate the top value
    POP,        //pop the top value from the stack
    SWAP,       //swap the top two items on the stack
    ENTER,      //enter a stack frame
    LEAVE,      //leave a stack frame
    LAYOUT,     //[...]->[...], set the frame layout from the next word in the program
    ALLOC,      //[...|size]->[...|ref], allocate n cells with the given layout. size is on stack, layout is in instruction stream
    LOAD,       //[...]->[...|value], load value from temp register
    STORE,      //[...|value]->[...], store value in temp register
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
    HALT,       //halt execution
};

static inline void vm_dispatch(struct vm *self, uint8_t instruction);

static inline void vm_execute(struct vm *self, uint8_t *code) {
    self->program = code;
    for (self->ip = code; self->ip; ) {
        uint8_t inst = VM_DEREF_IP(self->ip);
        //increment self->ip here so its before Dispatch gets it (where it might be modified)
        //printf("vm_execute %p\r\n", self->ip);
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
    switch ((enum vm_op_code)instruction) {
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
        case NEG:
            *(self->sp - 1) = -*(self->sp - 1);
            break;
        case NOT:
            *(self->sp - 1) = !*(self->sp - 1);
            break;
        case BITNOT:
            *(self->sp - 1) = ~*(self->sp - 1);
            break;
        case BITAND:
            *(self->sp - 2) = *(self->sp - 2) & *(self->sp - 1);
            self->sp--;
            break;
        case BITXOR:
            *(self->sp - 2) = *(self->sp - 2) ^ *(self->sp - 1);
            self->sp--;
            break;
        case BITOR:
            *(self->sp - 2) = *(self->sp - 2) | *(self->sp - 1);
            self->sp--;
            break;
        case LT:
            *(self->sp - 2) = *(self->sp - 2) < *(self->sp - 1);
            self->sp--;
            break;
        case LTE:
            *(self->sp - 2) = *(self->sp - 2) <= *(self->sp - 1);
            self->sp--;
            break;
        case GT:
            *(self->sp - 2) = *(self->sp - 2) > *(self->sp - 1);
            self->sp--;
            break;
        case GTE:
            *(self->sp - 2) = *(self->sp - 2) >= *(self->sp - 1);
            self->sp--;
            break;
        case EQ:
            *(self->sp - 2) = *(self->sp - 2) == *(self->sp - 1);
            self->sp--;
            break;
        case NEQ:
            *(self->sp - 2) = *(self->sp - 2) != *(self->sp - 1);
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
        case CALL: {
            intptr_t ip = (intptr_t)self->ip;
            self->ip = (uint8_t*)*(self->sp - 1);
            *(self->sp - 1) = ip;
            break;
        }
        case RETURN:
            self->ip = (uint8_t*)*(self->sp - 1);
            self->sp--;
            break;
        case JUMP: 
            self->ip = self->program + vm_get_word(self);
            break;
        case JUMPZ:
            if (*(self->sp - 1) == 0) {
                self->ip = self->program + vm_get_word(self);
            } else {
                vm_get_word(self);
            }
            self->sp--;
            break;
        case JUMPNZ:
            if (*(self->sp - 1) != 0) {
                self->ip = self->program + vm_get_word(self);
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
        case LAYOUT:
            //set the first cell in the frame to the layout function found using the index in the word following ip
            *(self->fp + 1) = (intptr_t)self->functions[vm_get_word(self)];
            break;
        case ALLOC:
            //TODO: foreach_t funtion for this guy: traverse frames and call frame layout function for each
            *(self->sp - 1) = (intptr_t)gc_alloc_with_layout(self->gc, NULL, NULL, *(self->sp - 1), self->functions[vm_get_word(self)]);
            break;
        case LOAD:
            *self->sp = self->temp;
            self->sp++;
            break;
        case STORE:
            self->temp = *(self->sp - 1);
            self->sp--;
            break;
        case LLOAD:
            *self->sp = *(self->fp + vm_get_word(self) + 2);
            self->sp++;
            break;
        case LSTORE:
            *(self->fp + vm_get_word(self) + 2) = *(self->sp - 1);
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
        case ALOAD:
            //TODO
            break;
        case ASTORE:
            //TODO
            break;
        case CLOAD:
            //TODO
            break;
        case CSTORE:
            //TODO
            break;
        case NCALL: {
            void(*fun)(struct vm *) = (void(*)(struct vm *))self->functions[vm_get_word(self)];
            fun(self);
            break;
        }
        case NOP:
            break;
        case HALT:
            self->ip = NULL;
            break;
    }
}

#endif //VM_HPP

