package css.specificity.tokenizer

import org.junit.Test
import kotlin.test.assertEquals

class PseudoElementMatcherTest {
    @Test
    fun `it correctly recognizes valid element selectors from invalid ones`() {
        listOf(
            "" to false,
            "*" to false,
            ".test" to false,
            ".another" to false,
            "test" to false,
            """\a""" to false,
            """\:\:""" to false,
            """::\a""" to true,
            """::before""" to true,
        ).forEach { (selector, isValid) ->  assertEquals(
            isValid,
            PseudoElementMatcher().isValid(selector),
            "Selector '$selector' is treated as " + if (isValid) "a valid" else "an invalid" + " selector"
        ) }
    }
}