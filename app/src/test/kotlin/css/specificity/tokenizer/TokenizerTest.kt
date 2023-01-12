package css.specificity.tokenizer

import org.junit.Test

class TokenizerTest {
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