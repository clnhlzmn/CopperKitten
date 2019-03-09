//LayoutFunction represents a generated function that the ck garbage collector
//will use to find object references either in the runtime stack or in gc managed objects
//on the heap.
//A layout function could be a custom layout where specific objects are marked as references
//or it could be a ref array layout where all indices are refs and the number of such indices
//is only known at runtime

//each the interface that the garbage collector uses to enumerate the object references is
//typedef void (*foreach_t)(
//    void (*cb)(uintptr_t **it, void *ctx),
//    void *cb_ctx,
//    void *foreach_ctx);
//where foreach_t is the type of the layout function
//such a layout function will be stored in each stack frame and in each heap object
//the garbage collector will then call those functions when it needs to find object references

//when the gc calls a stack frame layout function it will pass the frame local area address as foreach_ctx
//the layout function can then pass &frame_ptr[index_of_object_ref] to cb.
//cb is provided by the gc and is where it sees the address of the object reference
//cb_ctx is also provided by the gc as a way to get information into cb

//when the gc calls a heap object layout function the heap object user address is passed as foreach_ctx
interface Function {
    fun name(): String
    fun declare(): String
    fun define(): String
}

data class CustomLayoutFunction(val layout: List<Int>) : Function {

    override fun name(): String {
        return "gen_layout${layout.map { i -> i.toString() }.fold("") { acc, s -> "${acc}_$s" }}"
    }

    override fun declare(): String {
        return "static inline void ${name()}(void (*)(intptr_t **, void *), void *, void *);"
    }

    override fun define(): String {
        return "static inline void ${name()}(void (*cb)(intptr_t **it, void *ctx), void *cb_ctx, void *foreach_ctx) {\n" +
            "\tintptr_t **base_ptr = (intptr_t**)foreach_ctx;\n" +
            layout.map { i -> "\tcb(&base_ptr[${i}], cb_ctx);\n" }.fold("") { acc, s -> "$acc$s" } +
            "}\n"
    }
}

object RefArrayLayoutFunction : Function {

    override fun name(): String {
        return "gc_layout_ref_array"
    }

    override fun declare(): String {
        return "static inline void ${name()}(void (*)(intptr_t **, void *), void *, void *);"
    }

    override fun define(): String {
        //don't have to implement this function it's implemented in gc_interface.h
        return ""
    }
}

data class NativeFunction(val id: String) : Function {
    override fun name(): String {
        return id
    }

    override fun declare(): String {
        return "void ${name()}(struct vm *);"
    }

    override fun define(): String {
        //implemented elsewhere
        return ""
    }

}