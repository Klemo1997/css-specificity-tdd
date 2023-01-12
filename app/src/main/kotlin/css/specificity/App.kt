/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package css.specificity

import java.lang.StringBuilder

fun main() {
    println(getSpecificity("*"))
}

typealias Specificity = Triple<Int, Int, Int>

val chainDelimiter = """\.""".toRegex()
private val delimiter = """[\s>+~]+""".toRegex()

fun getSpecificity(selector: String): Specificity {
    val subSelectors = selector.trim().split(delimiter)

    return subSelectors.map { tokenize(it).sum() }.sum()
}

fun valueOf(selector: String): Specificity = when {
    selector == "*" -> Specificity(0, 0, 0)
    isClass(selector) -> Specificity(0, 1, 0)
    else -> Specificity(0, 0, 1)
}

fun isClass(selector: String): Boolean = selector.startsWith(".")

fun tokenize(selector: String): List<Specificity> {
    val tokens = mutableListOf<Specificity>()

    val buffer = StringBuilder()

    for (char in selector) {
        if (buffer.isNotEmpty() && char.toString().matches(chainDelimiter)) {
            tokens.add(valueOf(buffer.toString()))
            buffer.clear()
        }

        buffer.append(char)
    }

    if (buffer.isNotEmpty()) {
        tokens.add(valueOf(buffer.toString()))
    }

    return tokens
}

fun List<Specificity>.sum() =
    this.fold(Specificity(0, 0, 0)) { acc, specificity -> acc.add(specificity) }

fun Specificity.add(other: Specificity): Specificity {
    return Specificity(
        this.first + other.first,
        this.second + other.second,
        this.third + other.third,
    )
}