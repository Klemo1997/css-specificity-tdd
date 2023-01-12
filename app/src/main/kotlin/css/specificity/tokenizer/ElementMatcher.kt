package css.specificity.tokenizer

class ElementMatcher: Matcher {
    override fun isValid(selector: String): Boolean = (
        selector.isNotEmpty()
        && (
            selector[0].isLetter()
            || selector.startsWith("""\""")
        )
    )
}