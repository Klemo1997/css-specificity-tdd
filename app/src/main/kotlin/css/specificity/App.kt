/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package css.specificity

fun main() {
    println(getSpecificity("*"))
}

typealias Specificity = Triple<Int, Int, Int>

fun getSpecificity(selector: String) = Specificity(0,0,0)
