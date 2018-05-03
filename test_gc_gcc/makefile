
test: test_gc.o
	g++ -g -o test test_gc.o

test_gc.o: ../test_gc.cpp ../gc.hpp
	g++ -Wall -Wextra -g -std=c++11 -c ../test_gc.cpp -o test_gc.o

clean:
	rm -rf test_gc.o
	rm -rf test.exe
	rm -rf test
