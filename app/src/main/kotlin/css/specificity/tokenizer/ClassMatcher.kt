package css.specificity.tokenizer

class ClassMatcher: Matcher {
    override fun isValid(selector: String): Boolean = selector.startsWith(".")
}