fun plusNumbers(num1: Int?, num2: Int?): Int {
    val _num1: Int = if (num1 == null) 0 else num1
    val _num2: Int = if (num2 == null) 0 else num2


    return _num1 + _num2
}

val result: Int = plusNumbers(null, 3)
println(result)