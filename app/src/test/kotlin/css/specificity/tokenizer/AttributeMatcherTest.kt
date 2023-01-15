package css.specificity.tokenizer

import org.junit.Test
import kotlin.test.assertEquals

class AttributeMatcherTest {
    @Test
    fun `it correctly recognizes valid class selectors from invalid ones`() {
        listOf(
            "" to false,
            "*" to false,
            "test" to false,
            """\.test""" to false,
            ".test" to false,
            "[a]" to true,
            "[a=1]" to true,
            "[ a]" to true,
        ).forEach { (selector, isValid) ->  assertEquals(
            isValid,
            AttributeMatcher().isValid(selector),
            "Selector '$selector' is treated as " + if (isValid) "a valid" else "an invalid" + " selector"
        ) }
    }
}