class Sum {
    fun sum(): Int {
        return 0
    }
    fun sum(num1: Int): Int {
        return num1 + 10
    }
    fun sum(num1: Int, num2: Int): Int {
        return num1 + num2
    }
}

val sum = Sum()
sum.sum()
sum.sum(2)
sum.sum(2, 22)