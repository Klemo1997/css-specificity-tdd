package css.specificity.tokenizer

import org.junit.Test
import kotlin.test.assertEquals

class TokenizerTest {
    @Test fun `Tokenizer returns correctly parsed list of specificities`() {
        mapOf(
            "" to listOf(),
            "*" to listOf(Specificity(0, 0, 0)),
            "div" to listOf(Specificity(0, 0, 1)),
            ".test" to listOf(Specificity(0, 1, 0)),
            "div.test" to listOf(Specificity(0, 0, 1), Specificity(0, 1, 0)),
            ".test.div" to listOf(Specificity(0, 1, 0), Specificity(0, 1, 0)),
            "#test" to listOf(Specificity(1, 0, 0)),
            "#test#test" to listOf(Specificity(1, 0, 0), Specificity(1, 0, 0)),
            "#test.test" to listOf(Specificity(1, 0, 0), Specificity(0, 1, 0)),
            "div.test#test" to listOf(Specificity(0, 0, 1), Specificity(0, 1, 0), Specificity(1, 0, 0)),
        ).forEach { (selector, specificity) -> assertEquals(specificity, Tokenizer().tokenize(selector)) }
    }
}