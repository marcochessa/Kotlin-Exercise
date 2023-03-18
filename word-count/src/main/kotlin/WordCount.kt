object WordCount {

    fun phrase(phrase: String): Map<String, Int> {
        val wordMap : MutableMap<String,Int> = mutableMapOf()
        phrase.lowercase().replace(Regex("[^\\w\\d ']")," ")
            .split(" ").filter {s->s!=""}.forEach {
                var s =it.trim('\'')
                wordMap[s] = (wordMap[s]?:0)+1
            }
        return wordMap
    }
}
