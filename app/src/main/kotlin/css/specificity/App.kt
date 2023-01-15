/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package css.specificity

import css.specificity.tokenizer.*

fun main() {
    println(getSpecificity("*"))
}

private val attributeStringValueMatcher = """(?<!\\)"(?:\\"|[^"])*"""".toRegex()

val matchers = listOf(
    ElementMatcher(),
    ClassMatcher(),
    IdMatcher(),
    AttributeMatcher(),
)

fun getSpecificity(selector: String): Specificity {
    val sanitizedSelector = selector.replace(attributeStringValueMatcher, "1")
    return Tokenizer(matchers).tokenize(sanitizedSelector).sum()
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