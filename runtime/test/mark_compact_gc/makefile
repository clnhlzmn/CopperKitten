
#to make mingw use proper printf format strings like %lld
CFLAGS = -Wall -Wextra -Werror -std=c99 -g -D__USE_MINGW_ANSI_STDIO=1 -fmax-errors=5 -Wno-error=unused-command-line-argument
LDFLAGS = -g

test: test_gc.o gc.o
	@echo test
	gcc $(LDFLAGS) -o test test_gc.o gc.o

gc.o: ../../mark_compact_gc.c ../../mark_compact_gc.h
	@echo gc.o
	gcc $(CFLAGS) -I../../ -c ../../mark_compact_gc.c -o gc.o

test_gc.o: test_gc.c gc.o
	@echo test_gc.o
	gcc $(CFLAGS) -I../../ -c test_gc.c -o test_gc.o

clean:
	rm -rf test_gc.o
	rm -rf gc.o
	rm -rf test.exe
	rm -rf test
