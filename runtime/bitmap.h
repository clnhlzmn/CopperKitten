

#ifndef BITMAP_H
#define BITMAP_H

#include <limits.h>
#include <stdint.h>

static inline void bitmap_foreach(
    intptr_t *bm,                       //pointer to the bitmap
    intptr_t size,                      //size of the bitmap (in bits)
    void(*cb)(intptr_t i, void *ctx),   //callback to be called with the index of each 1 bit
    void *cb_ctx)                       //pointer to context for the callback
    {
    static intptr_t bits_per_word = CHAR_BIT * sizeof(intptr_t);
    intptr_t bit_index = 0;
    for (intptr_t word_index = 0; bit_index < size; word_index++) {
        bit_index = word_index * bits_per_word;
        if (bm[word_index] == 0) {
            bit_index += bits_per_word;
        } else {
            //not zero, have to find some bits
            for (intptr_t current_word_bit_i = 0, mask = 1;
                current_word_bit_i + word_index * bits_per_word < size
                    && current_word_bit_i < bits_per_word;
                current_word_bit_i++,
                mask <<= 1) {
                //loop through the current word
                if (bm[word_index] & mask) {
                    cb(bit_index, cb_ctx);
                }
                //increment bit_index
                bit_index++;
            }
        }
    }
}

#endif

