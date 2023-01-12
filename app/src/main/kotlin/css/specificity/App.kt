/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package css.specificity

fun main() {
    println(getSpecificity("*"))
}

typealias Specificity = Triple<Int, Int, Int>

private val delimiter = """[\s>+~]+""".toRegex()

fun getSpecificity(selector: String): Specificity {
    val size = selector.trim().split(delimiter).fold(0) { acc, subSelector -> acc + valueOf(subSelector) }

    return Specificity(0, 0, size)
}

fun valueOf(selector: String): Int = when (selector.trim()) {
    "*" -> 0
    else -> 1
}
