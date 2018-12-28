
test: test_gc.o
	gcc -g -o test test_gc.o

test_gc.o: test_gc.c ../../include/gc/gc.c
	gcc -Wall -Wextra -g -I../../include -std=c99 -c test_gc.c -o test_gc.o

clean:
	rm -rf test_gc.o
	rm -rf test.exe
	rm -rf test
