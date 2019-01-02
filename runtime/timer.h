

#ifndef TIMER_H
#define TIMER_H

#include <time.h>

static inline clock_t timer_begin(void) {
    return clock();
}

static inline double timer_end(clock_t begin) {
    clock_t end = clock();
    return (double)(end - begin) / CLOCKS_PER_SEC;
}

#endif //TIMER_H