package css.specificity.tokenizer

class PseudoElementMatcher: Matcher {
    override fun isValid(selector: String): Boolean = selector.startsWith("::")

    override fun getSpecificity(): Specificity = Specificity(0, 0, 1)
}
