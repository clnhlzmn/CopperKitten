
test: test_vm.o
	g++ -g -o test test_vm.o

test_vm.o: test_vm.cpp ../../include/vm/vm.hpp
	g++ -Wall -Wextra -g -I../../include -std=c++11 -c test_vm.cpp -o test_vm.o

clean:
	rm -rf test_vm.o
	rm -rf test.exe
	rm -rf test
