

//typedef void (*foreach_t)(
//    void (*cb)(uintptr_t **it, void *ctx),
//    void *cb_ctx,
//    void *foreach_ctx);

data class LayoutFunction(val layout: List<Int>) {
    fun name(): String {
        //todo: name uniquely
        //todo: don't duplicate layout functions
        return "layout_${hashCode()}"
    }
    override fun toString(): String {
        return "void ${name()}(void (*cb)(uintptr_t **it, void *ctx), void *cb_ctx, void *foreach_ctx) {\n" +
                "\t\n" +
                "}\n"
    }
}