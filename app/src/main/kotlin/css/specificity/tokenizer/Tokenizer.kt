package css.specificity.tokenizer

import java.lang.StringBuilder
import java.util.*

typealias Specificity = Triple<Int, Int, Int>

class Tokenizer(private val matchers: List<Matcher>) {
    private val delimiter = """(?<!\\)[\s>+~]+(?!=)""".toRegex()
    private val chainDelimiter = """(?<!\\)(\.|#|\[)""".toRegex()

    private fun valueOf(selector: String): Specificity {
        return matchers.find { it.isValid(selector) }?.getSpecificity() ?: Specificity(0, 0, 0)
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