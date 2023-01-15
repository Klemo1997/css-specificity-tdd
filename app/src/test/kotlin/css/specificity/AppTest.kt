/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package css.specificity

import kotlin.test.Test
import kotlin.test.assertEquals

typealias Specificity = Triple<Int, Int, Int>

class AppTest {
    @Test fun `It returns correct speficicity`() {
        mapOf(
            "*" to Specificity(0, 0, 0),
            " * div " to Specificity(0, 0, 1),
            "* div" to Specificity(0, 0, 1),
            "div" to Specificity(0, 0, 1),
            " div " to Specificity(0, 0, 1),
            "body div" to Specificity(0, 0, 2),
            "body div li" to Specificity(0, 0, 3),
            "body  div" to Specificity(0, 0, 2),
            "body	div" to Specificity(0, 0, 2),
            """body\tdiv""" to Specificity(0, 0, 1),
            """body\%div""" to Specificity(0, 0, 1),
            "body>div" to Specificity(0, 0, 2),
            "body > div" to Specificity(0, 0, 2),
            """body \> div""" to Specificity(0, 0, 3),
            "body + div" to Specificity(0, 0, 2),
            "body ~ div" to Specificity(0, 0, 2),
            ".test" to Specificity(0, 1, 0),
            ".test .test2" to Specificity(0, 2, 0),
            "body .test .test2" to Specificity(0, 2, 1),
            ".test.test" to Specificity(0, 2, 0),
            "#test #test" to Specificity(2, 0, 0),
            "#test#test" to Specificity(2, 0, 0),
            ".test#test#test" to Specificity(2, 1, 0),
            "test[a=1] test" to Specificity(0, 1, 2),
            "test[a~=1]" to Specificity(0, 1, 1),
            "test[a|=1]" to Specificity(0, 1, 1),
            "test[a^=1]" to Specificity(0, 1, 1),
            "test[a$=1]" to Specificity(0, 1, 1),
            "test[a*=1]" to Specificity(0, 1, 1),
        ).forEach {
            (selector, specificity) -> assertEquals(
                specificity,
                getSpecificity(selector),
                "It returns $specificity for '$selector'"
            )
        }
    }
}
