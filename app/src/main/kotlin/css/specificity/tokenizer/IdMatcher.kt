package css.specificity.tokenizer

class IdMatcher: Matcher {
    override fun isValid(selector: String): Boolean = selector.startsWith("#")

    override fun getSpecificity(): Specificity = Specificity(1, 0, 0)
}