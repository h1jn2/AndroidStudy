class Calculator (num1: Int, num2: Int) {
    val num1: Int
    val num2: Int

    init {
        this.num1 = num1
        this.num2 = num2
    }
    fun plus(): Int{
        val result: Int = this.num1 + this.num2
        return result
    }

    fun minus(): Int {
        val result: Int = this.num1 - this.num2
        return result
    }

    fun multiply(): Int {
        val result: Int = this.num1 * this.num2
        return result
    }

    fun divide(): Int {
        val result: Int = this.num1 / this.num2
        return result
    }
}

val cal1 = Calculator(30, 10)
println(cal1.plus())
println(cal1.minus())
println(cal1.multiply())
println(cal1.divide())