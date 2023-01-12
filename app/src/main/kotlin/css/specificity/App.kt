/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package css.specificity

fun main() {
    println(getSpecificity("*"))
}

typealias Specificity = Triple<Int, Int, Int>

private val delimiter = """[\s\>\+\~]+""".toRegex()

fun getSpecificity(selector: String): Specificity {
    val size = selector.split(delimiter).size

    return when(selector) {
        "*" -> Specificity(0, 0, 0)
        else -> Specificity(0, 0, size)
    }
}
