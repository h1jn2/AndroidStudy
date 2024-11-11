class Calculator(num1: Int = 0) {
    val num1: Int

    init {
        this.num1 = num1
    }

    fun calc(giho: Char, num2: Int): String {
        var returnStr: String = ""

        when (giho) {
            '+' -> returnStr = "결과: ${this.num1 + num2}"
            '-' -> returnStr = "결과: ${this.num1 - num2}"
            '*' -> returnStr = "결과: ${this.num1 * num2}"
            '/' -> returnStr = "결과: ${this.num1 / num2}"
            else -> returnStr = "잘못된 연산"
        }
        return returnStr
    }

}

val cal1 = Calculator()
println(cal1.calc('+', 30))