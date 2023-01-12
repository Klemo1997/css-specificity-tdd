/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package css.specificity

import kotlin.test.Test
import kotlin.test.assertEquals

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
        ).forEach {
            (selector, specificity) -> assertEquals(
                getSpecificity(selector),
                specificity,
                "It returns $specificity for '$selector'"
            )
        }
    }

    @Test fun `Tokenizer returns correctly parsed list of specificities`() {
        mapOf(
            "" to Specificity(0, 0, 0),
            "*" to Specificity(0, 0, 0),
            "div" to Specificity(0, 0, 1),
            ".test" to Specificity(0, 1, 0),
            "div.test" to Specificity(0, 1, 1),
            ".test.div" to Specificity(0, 2, 0),
            "#test" to Specificity(1, 0, 0),
            "#test#test" to Specificity(2, 0, 0),
            "#test.test" to Specificity(1, 1, 0),
            "div.test#test" to Specificity(1, 1, 1),
        )
    }
}
