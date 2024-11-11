class Calculator {
    fun calc(list1: List<Char>, list2: List<Int>): Int{
        var result: Int = 0
        for (i in 0 until list1.size) {
            when (list1[i]) {
                '+' -> result += list2[i]
                '-' -> result -= list2[i]
                '*' -> result *= list2[i]
                '/' -> result /= list2[i]
            }
        }
        return result
    }
}

val cal1 = Calculator()
println(cal1.calc(listOf('+', '-'), listOf(10, 20)))