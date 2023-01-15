package css.specificity.tokenizer

interface Matcher {
    fun isValid(selector: String): Boolean

    fun getSpecificity(): Specificity
}