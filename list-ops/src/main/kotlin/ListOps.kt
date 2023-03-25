fun <T> List<T>.customAppend(list: List<T>): List<T> {
    val tempList = mutableListOf<T>()
    this.forEach{
        tempList.add(it)
    } // or tempList = this.toMutableList()
    list.forEach{
        tempList.add(it)
    }
    return tempList.toList()
}

fun List<Any>.customConcat(): List<Any> {
    val tempList = mutableListOf<Any>()
    recursiveConcat(tempList, this)
    return tempList
}

fun recursiveConcat (source: MutableList<Any>, list: List<Any>){
     list.forEach{
        if (it is List<*>)
            recursiveConcat(source, it as List<Any>)
        else
            source.add(it)
    }
}

fun <T> List<T>.customFilter(predicate: (T) -> Boolean): List<T> {
    val tempList = mutableListOf<T>()
    this.forEach{
        if(predicate(it))
            tempList.add(it)
    }
    return tempList
}

val List<Any>.customSize: Int get() {
    var size: Int = 0
    this.forEach { _ -> size += 1 }
    return size
}

fun <T, U> List<T>.customMap(transform: (T) -> U): List<U> {
    val tempList = mutableListOf<U>()
    this.forEach{
        tempList.add(transform(it))
    }
    return tempList
}

fun <T, U> List<T>.customFoldLeft(initial: U, f: (U, T) -> U): U {
    var accumulator: U = initial
    for (element in this) accumulator = f(accumulator, element)
    return accumulator
}

fun <T, U> List<T>.customFoldRight(initial: U, f: (T, U) -> U): U {
    var accumulator: U = initial
    this.customReverse().forEach { accumulator = f(it, accumulator) }
    return accumulator
}

fun <T> List<T>.customReverse(): List<T> {
    val tempList = mutableListOf<T>()
    this.forEach{
        tempList.add(0, it) //Prepend element
    }
    return tempList
}
