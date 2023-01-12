/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package css.specificity

import css.specificity.tokenizer.*

fun main() {
    println(getSpecificity("*"))
}

private val delimiter = """[\s>+~]+""".toRegex()

fun getSpecificity(selector: String): Specificity {
    val subSelectors = selector.trim().split(delimiter)
    val tokenizer = Tokenizer()
    return subSelectors.map { tokenizer.tokenize(it).sum() }.sum()
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