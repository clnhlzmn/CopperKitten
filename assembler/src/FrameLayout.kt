//typedef void (*foreach_t)(
//    void (*cb)(uintptr_t **it, void *ctx),
//    void *cb_ctx,
//    void *foreach_ctx);

data class FrameLayout(val layout: List<Int>) {
    fun name(): String {
        return "frame_layout${layout.map { i -> i.toString() }.fold("") { acc, s -> "${acc}_$s" }}"
    }

    override fun toString(): String {
        return "void ${name()}(void (*cb)(intptr_t **it, void *ctx), void *cb_ctx, void *foreach_ctx) {\n" +
            "\tintptr_t **frame_ptr = (intptr_t**)foreach_ctx;\n" +
            layout.map { i -> "\tcb(&frame_ptr[${i + 1}], cb_ctx);\n" }.fold(""){acc, s -> "$acc$s"} +
            "}\n"
    }
}