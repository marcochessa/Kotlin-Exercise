
class Forth {

    private val number = Regex("-?[0-9]+")
    private val doubleOperator = Regex("[*+/-]|over|swap")
    private val singleOperator = Regex("dup|drop")
    private lateinit var stack: ArrayDeque<String>;

    fun evaluate(vararg line: String): List<Int> {
        val stackTmp = ArrayDeque<String>();
        val mutableDict = hashMapOf<String,String>() //alternative <String,List<String>> without then using joinToString
        line.forEach { it ->
            if(it.startsWith(":")) {
                val rules: MutableList<String> = it.lowercase().split(" ").toMutableList()
                rules.removeFirst() // remove ":"
                rules.removeLast() // remove ";"
                val key = rules.removeFirst()
                if (key matches (number))
                    throw Exception("illegal operation")
                val rulesTranslate: List<String> = rules.map{ s->if(mutableDict.containsKey(s)) mutableDict[s].toString() else s}
                mutableDict[key] = rulesTranslate.joinToString(" ")
            } else {
                val commandToTranslate: List<String> = it.lowercase().split(" ")
                val commandTranslate = commandToTranslate.map{ s->if(mutableDict.containsKey(s)) mutableDict[s].toString() else s}
                val command = commandTranslate.joinToString(" ")
                stack = ArrayDeque(command.split(" "))
            }

        }

        if(stack.isEmpty())
            throw Exception("empty stack")

        while (stack.size>0){
            if (!(stack.last() matches (number)))
                operate()
            else
                stackTmp.addFirst(stack.removeLast())
        }

        return stackTmp.toList().map { it.toInt() }
    }

    fun operate(){
        val operator = stack.removeLast()

        if((operator matches (singleOperator)) or (operator matches (doubleOperator))) {
            if (stack.isEmpty())
                throw Exception("empty stack")
        } else throw Exception("undefined operation")

        if(operator matches (doubleOperator))
            if(stack.size<2)
                throw Exception("only one value on the stack")

        when(operator) {
            "+" -> {
                val first = stack.removeLast().toInt()
                if(!(stack.last() matches (number)))
                    operate()

                val second = stack.removeLast().toInt()
                stack.add((second+first).toString())
            }

            "-" -> {
                val first = stack.removeLast().toInt()
                if(!(stack.last() matches (number)))
                    operate()
                val second = stack.removeLast().toInt()
                stack.add((second-first).toString())
            }

            "*" -> {
                val first = stack.removeLast().toInt()
                if(!(stack.last() matches (number)))
                    operate()
                val second = stack.removeLast().toInt()
                stack.add((second*first).toString())
            }

            "/" -> {
                val first = stack.removeLast().toInt()
                if(first == 0)
                    throw Exception("divide by zero")
                if(!(stack.last() matches (number)))
                    operate()
                val second = stack.removeLast().toInt()
                stack.add((second/first).toString())
            }

            "dup" ->  {
                if(!(stack.last() matches (number)))
                    operate()
                stack.add(stack.last())
            }

            "drop" -> {
                if(!(stack.last() matches (number)))
                    operate()
                stack.removeLast()
            }

            "swap" -> {
                val first = stack.removeLast()
                if(!(stack.last() matches (number)))
                    operate()
                val second = stack.removeLast()
                stack.add((first))
                stack.add((second))
            }

            "over" -> {
                if(!(stack.last() matches (number)))
                    operate()
                val first = stack.removeLast()
                val second = stack.last()
                stack.add((first))
                stack.add((second))
            }
        }

    }

}
