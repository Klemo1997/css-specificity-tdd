package css.specificity.tokenizer

class IdMatcher: Matcher {
    override fun isValid(selector: String): Boolean = selector.startsWith("#")
}