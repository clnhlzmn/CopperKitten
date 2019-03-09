#include "vm.h"
#include "mark_compact_gc.h"

#define MEM_SIZE 1000
intptr_t gc_mem[MEM_SIZE];
struct gc gc_instance;

#define STACK_SIZE 100
intptr_t stack_mem[STACK_SIZE];
struct vm vm_instance;


static inline void gen_layout(void (*cb)(intptr_t **it, void *ctx), void *cb_ctx, void *foreach_ctx) {
	intptr_t **base_ptr = (intptr_t**)foreach_ctx;
}

static inline void gen_layout_0_1(void (*cb)(intptr_t **it, void *ctx), void *cb_ctx, void *foreach_ctx) {
	intptr_t **base_ptr = (intptr_t**)foreach_ctx;
	cb(&base_ptr[0], cb_ctx);
	cb(&base_ptr[1], cb_ctx);
}
foreach_t layouts[] = {
	gen_layout,
	gen_layout_0_1,
	NULL,
};

uint8_t program[] = {
	ENTER,
	PUSH,
	0,
	0,
	0,
	0,
	PUSH,
	42,
	0,
	0,
	0,
	LSTORE,
	0,
	0,
	0,
	0,
	LAYOUT,
	0,
	0,
	0,
	0,
	PUSH,
	2,
	0,
	0,
	0,
	ALLOC,
	0,
	0,
	0,
	0,
	DUP,
	PUSH,
	53,
	0,
	0,
	0,
	RSTORE,
	0,
	0,
	0,
	0,
	DUP,
	RSTORE,
	0,
	0,
	0,
	0,
	JUMP,
	62,
	0,
	0,
	0,
	ENTER,
	CLOAD,
	0,
	0,
	0,
	0,
	STORE,
	LEAVE,
	RETURN,
	DUP,
	RLOAD,
	0,
	0,
	0,
	0,
	LAYOUT,
	1,
	0,
	0,
	0,
	CALL,
	POP,
	LOAD,
	STORE,
	POP,
	LEAVE,
	HALT,
};

int main(void) {
	gc_init(&gc_instance, gc_mem, MEM_SIZE);
	vm_init(&vm_instance, &gc_instance, stack_mem, layouts);
	vm_execute(&vm_instance, program);
	return 0;
}