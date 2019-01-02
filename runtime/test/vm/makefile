
#to make mingw use proper printf format strings like %lld
CFLAGS = -Wall -Wextra -Werror -std=c99 -g -D__USE_MINGW_ANSI_STDIO=1 -fmax-errors=5
LDFLAGS = -g

test: test_vm.o ../gc/gc.o
	gcc $(LDFLAGS) -o test test_vm.o ../gc/gc.o

test_vm.o: test_vm.c ../../vm.h
	gcc $(CFLAGS) -I../../ -c test_vm.c -o test_vm.o

clean:
	rm -rf test_vm.o
	rm -rf test.exe
	rm -rf test
