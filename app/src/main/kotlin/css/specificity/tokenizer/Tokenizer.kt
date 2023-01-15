package css.specificity.tokenizer

import java.lang.StringBuilder
import java.util.*

typealias Specificity = Triple<Int, Int, Int>

class Tokenizer {
    private val delimiter = """(?<!\\)[\s>+~]+(?!=)""".toRegex()
    private val chainDelimiter = """(?<!\\)(\.|#|\[)""".toRegex()

    private fun valueOf(selector: String): Specificity = when {
        ElementMatcher().isValid(selector) -> Specificity(0, 0, 1)
        ClassMatcher().isValid(selector) -> Specificity(0, 1, 0)
        IdMatcher().isValid(selector) -> Specificity(1, 0, 0)
        AttributeMatcher().isValid(selector) -> Specificity(0, 1, 0)
        else -> Specificity(0, 0, 0)
    }

    fun tokenize(selector: String): List<Specificity> {
        val tokens = mutableListOf<Specificity>()
        val queue = LinkedList<String>()

        queue.push(selector.trim())

        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            val subSelectors = current.trim().split(delimiter)

            if (subSelectors.size > 1) {
                subSelectors.forEach { queue.addLast(it) }
                continue
            }

            getChainedSelectors(current).forEach { tokens.add(valueOf(it)) }
        }

        return tokens
    }

    private fun getChainedSelectors(current: String): List<String> {
        val buffer = StringBuilder()
        val chainedSelectors = arrayListOf<String>()
        val chainStartIndices = chainDelimiter.findAll(current).map { it.range.first }

        for ((index, char) in current.withIndex()) {
            if (buffer.isNotEmpty() && chainStartIndices.contains(index)) {
                chainedSelectors.add(buffer.toString())
                buffer.clear()
            }

            buffer.append(char)
        }

        if (buffer.isNotEmpty()) {
            chainedSelectors.add(buffer.toString())
        }

        return chainedSelectors
    }
}