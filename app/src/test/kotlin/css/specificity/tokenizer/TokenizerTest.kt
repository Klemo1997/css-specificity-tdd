package css.specificity.tokenizer

import css.specificity.matchers
import org.junit.Test
import kotlin.test.assertEquals

class TokenizerTest {
    @Test fun `Tokenizer returns correctly parsed list of specificities`() {
        listOf(
            "" to listOf(),
            "*" to listOf(Specificity(0, 0, 0)),
            "div" to listOf(Specificity(0, 0, 1)),
            ".test" to listOf(Specificity(0, 1, 0)),
            "div.test" to listOf(Specificity(0, 0, 1), Specificity(0, 1, 0)),
            ".test.div" to listOf(Specificity(0, 1, 0), Specificity(0, 1, 0)),
            "#test" to listOf(Specificity(1, 0, 0)),
            "#test#test" to listOf(Specificity(1, 0, 0), Specificity(1, 0, 0)),
            "#test.test" to listOf(Specificity(1, 0, 0), Specificity(0, 1, 0)),
            "div.test#test" to listOf(
                Specificity(0, 0, 1),
                Specificity(0, 1, 0),
                Specificity(1, 0, 0)),
            "test[a]" to listOf(Specificity(0, 0, 1), Specificity(0, 1, 0)),
            "test[a=1]" to listOf(Specificity(0, 0, 1), Specificity(0, 1, 0)),
        ).forEach { (selector, tokens) -> assertEquals(
            tokens,
            Tokenizer(matchers).tokenize(selector),
            "Tokenizer evaluates the selector: '$selector' to have specificity: ${tokens.joinToString(", ")}",
        ) }
    }

    @Test fun `Tokenizer should return selector with delimiters with guaranteed order`() {
        listOf(
            "*" to listOf(Specificity(0, 0, 0)),
            "* div" to listOf(Specificity(0, 0, 0), Specificity(0, 0, 1)),
            "div" to listOf(Specificity(0, 0, 1)),
            "body div" to listOf(Specificity(0, 0, 1), Specificity(0, 0, 1)),
            "body div li" to listOf(Specificity(0, 0, 1), Specificity(0, 0, 1), Specificity(0, 0, 1)),
            ".test" to listOf(Specificity(0, 1, 0)),
            "body .test" to listOf(Specificity(0, 0, 1), Specificity(0, 1, 0)),
            ".test.test" to listOf(Specificity(0, 1, 0), Specificity(0, 1, 0)),
            "#test #test" to listOf(Specificity(1, 0, 0), Specificity(1, 0, 0)),
            "#test#test" to listOf(Specificity(1, 0, 0), Specificity(1, 0, 0)),
            ".test#test#test" to listOf(Specificity(0, 1, 0), Specificity(1, 0, 0), Specificity(1, 0, 0)),
            "test[a=1] test" to listOf(Specificity(0, 0, 1), Specificity(0, 1, 0), Specificity(0, 0, 1)),
        ).forEach { (selector, tokens) -> assertEquals(
            tokens,
            Tokenizer(matchers).tokenize(selector),
            "Tokenizer evaluates the selector: '$selector' to have specificity: ${tokens.joinToString(", ")}",
        ) }
    }
}