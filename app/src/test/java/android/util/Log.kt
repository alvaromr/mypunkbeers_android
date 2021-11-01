package android.util

@Suppress("unused")
object Log {
    private const val print = false

    @JvmStatic
    fun println(priority: Int, tag: String, msg: String): Int {
        if (print) {
            println("DEBUG: ($priority) $tag: $msg")
        }
        return 0
    }
}