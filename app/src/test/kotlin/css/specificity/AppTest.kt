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
            "div" to Specificity(0, 0, 1),
        ).forEach { (selector, specificity) -> assertEquals(getSpecificity(selector), specificity) }
    }
}
