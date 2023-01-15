package css.specificity.tokenizer

import org.junit.Test
import kotlin.test.assertEquals

class ElementMatcherTest {
    @Test
    fun `it correctly recognizes valid element selectors from invalid ones`() {
        listOf(
            "" to false,
            "*" to false,
            ".test" to false,
            ".another" to false,
            "test" to true,
            """\.test""" to true,
            """\a""" to true,
        ).forEach { (selector, isValid) ->  assertEquals(
            isValid,
            ElementMatcher().isValid(selector),
            "Selector '$selector' is treated as " + if (isValid) "a valid" else "an invalid" + " selector"
        ) }
    }
}