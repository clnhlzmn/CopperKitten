#include "vm.h"
#include "mark_compact_gc.h"

#define MEM_SIZE 1000
intptr_t gc_mem[MEM_SIZE];
struct gc gc_instance;

#define STACK_SIZE 100
intptr_t stack_mem[STACK_SIZE];
struct vm vm_instance;

foreach_t layouts[] = {
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
	LLOAD,
	0,
	0,
	0,
	0,
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