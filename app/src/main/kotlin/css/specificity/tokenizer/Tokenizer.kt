package css.specificity.tokenizer

import css.specificity.chainDelimiter
import java.lang.StringBuilder

typealias Specificity = Triple<Int, Int, Int>

class Tokenizer {
    private fun valueOf(selector: String): Specificity = when {
        ElementMatcher().isValid(selector) -> Specificity(0, 0, 1)
        ClassMatcher().isValid(selector) -> Specificity(0, 1, 0)
        IdMatcher().isValid(selector) -> Specificity(1, 0, 0)
        else -> Specificity(0, 0, 0)
    }

    fun tokenize(selector: String): List<Specificity> {
        val tokens = mutableListOf<Specificity>()

        val buffer = StringBuilder()

        for (char in selector) {
            if (buffer.isNotEmpty() && char.toString().matches(chainDelimiter)) {
                tokens.add(valueOf(buffer.toString()))
                buffer.clear()
            }

            buffer.append(char)
        }

        if (buffer.isNotEmpty()) {
            tokens.add(valueOf(buffer.toString()))
        }

        return tokens
    }
}