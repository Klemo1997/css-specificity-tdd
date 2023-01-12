package css.specificity.tokenizer

import org.junit.Test
import kotlin.test.assertEquals

class ElementMatcherTest {
    @Test
    fun `it correctly recognizes valid element selectors from invalid ones`() {
        mapOf(
            "" to false,
            "*" to false,
            ".test" to false,
            ".another" to false,
            "test" to true,
            """\.test""" to true,
            """\a""" to true,
        ).forEach { (selector, isValid) ->  assertEquals(isValid, ElementMatcher().isValid(selector), selector) }
    }
}