package css.specificity.tokenizer

class AttributeMatcher: Matcher {
    override fun isValid(selector: String): Boolean = selector.startsWith("[") && selector.endsWith("]")
}
