object Flattener {
    fun flatten(source: Collection<Any?>): List<Any> {
        return recursive(source)
    }

    fun recursive (source: Collection<Any?>):List<Any>{
        return source.flatMap {
            if (it is List<*>)
                recursive(it)
            else
                listOf(it)
        }.filterNotNull()
    }
}
