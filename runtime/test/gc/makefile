
test: test_gc.o gc.o
	gcc -g -o test test_gc.o gc.o

gc.o: ../../gc.c ../../gc.h
	gcc -Wall -Wextra -g -I../../ -std=c99 -c ../../gc.c -o gc.o

test_gc.o: test_gc.c gc.o
	gcc -Wall -Wextra -g -I../../ -std=c99 -c test_gc.c -o test_gc.o

clean:
	rm -rf test_gc.o
	rm -rf test.exe
	rm -rf test
