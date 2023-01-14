package css.specificity.tokenizer

import java.lang.StringBuilder
import java.util.*

typealias Specificity = Triple<Int, Int, Int>

class Tokenizer {
    private val delimiter = """[\s>+~]+""".toRegex()
    private val chainDelimiter = """(\.|#|\[)""".toRegex()

    private fun valueOf(selector: String): Specificity = when {
        ElementMatcher().isValid(selector) -> Specificity(0, 0, 1)
        ClassMatcher().isValid(selector) -> Specificity(0, 1, 0)
        IdMatcher().isValid(selector) -> Specificity(1, 0, 0)
        AttributeMatcher().isValid(selector) -> Specificity(0, 1, 0)
        else -> Specificity(0, 0, 0)
    }

    fun tokenize(selector: String): List<Specificity> {
        val tokens = mutableListOf<Specificity>()
        val s = Stack<String>()

        s.push(selector.trim())

        while (s.isNotEmpty()) {
            val current = s.pop()
            val subSelectors = current.trim().split(delimiter)

            if (subSelectors.size > 1) {
                subSelectors.forEach { s.push(it) }
                continue
            }

            val buffer = StringBuilder()
            val chainedSelectors = arrayListOf<String>()

            for (char in current) {
                if (buffer.isNotEmpty() && char.toString().matches(chainDelimiter)) {
                    chainedSelectors.add(buffer.toString())
                    buffer.clear()
                }

                buffer.append(char)
            }

            if (buffer.isNotEmpty()) {
                chainedSelectors.add(buffer.toString())
            }

            chainedSelectors.forEach { tokens.add(valueOf(it)) }
        }

        return tokens
    }
}