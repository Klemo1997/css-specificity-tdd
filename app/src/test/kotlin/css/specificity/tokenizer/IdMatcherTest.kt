package css.specificity.tokenizer

import org.junit.Test
import kotlin.test.assertEquals

class IdMatcherTest {
    @Test
    fun `it correctly recognizes valid id selectors from invalid ones`() {
        mapOf(
            "" to false,
            "*" to false,
            "test" to false,
            """\.test""" to false,
            """\#test""" to false,
            "#test" to true,
            "#another" to true,
        ).forEach { (selector, isValid) ->  assertEquals(isValid, IdMatcher().isValid(selector), selector) }
    }
}