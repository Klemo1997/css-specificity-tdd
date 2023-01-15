package css.specificity.tokenizer

class ClassMatcher: Matcher {
    override fun isValid(selector: String): Boolean = selector.startsWith(".")

    override fun getSpecificity(): Specificity = Specificity(0, 1, 0)
}