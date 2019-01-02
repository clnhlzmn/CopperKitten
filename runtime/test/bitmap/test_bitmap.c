

#include <stdio.h>
#include <assert.h>
#include "bitmap.h"

intptr_t bitmap[] = {0b0000000000000000000000001010101010101010101010101010101010101010, 0b0000000000000000000000001010101010101010101010101010101010101010};

void bitmap_cb(intptr_t i, void *ctx) {
    (void)ctx;
    printf("%lld\r\n", i);
}

int main() {
    printf("sizeof(intptr_t)=%lld\r\n", sizeof(intptr_t));
    bitmap_foreach(bitmap, 128, bitmap_cb, NULL);
}