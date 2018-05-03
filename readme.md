
# CopperKitten

## A modular/generic/general purpose virtual machine to act as a runtime for programming language implementations

### Why

For fun mostly. I originally intended for this project to act as a runtime for a toy language that I wanted to be able to run on 8-bit Atmel AVR microcontrollers. In the process of developing this code I have learned a lot about virtual machines and garbage collection systems. Maybe this project will be useful to someone else looking to learn about these things.

### What

#### Virtual machine

Not yet implemented. I think this will be a simple stack based bytecode interpreter that will interface with the garbage collector described below.

#### Garbage collector

CopperKitten, at this point, consists of a very simple copying garbage collector. My goal was to make the garbage collection process as generic as possible. I think I have achieved that. The garbage collector doesn't need to know about the virtual machine or language design. The types of objects that can be allocated are slightly restricted, but should allow for all common language constructs. Users of the garbage collector are required to lay out objects in a certain manner, allocations are done in units of intptr\_t, and are required to pass a range of root references (as c++ iterators) to the gc in order to perform allocation.























