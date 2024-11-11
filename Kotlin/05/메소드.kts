// 함수를 선언하는 방법
fun plusNumbers(firstNum: Int, secondNum: Int): Int {
    val result: Int = firstNum + secondNum
    return result
}

val result1: Int = plusNumbers(10, 20)
println(result1)

// 기본값이 있는 함수를 선언하는 방법
fun plusNumbersWithDefault(firstNum: Int, secondNum: Int = 10): Int {
    return firstNum + secondNum
}

val result2: Int = plusNumbersWithDefault(10)
val result3: Int = plusNumbersWithDefault(10, 20)
println(result2)
println(result3)

// 반환값이 없는 함수를 선언하는 방법
fun plusNumbersWithNoReturn(firstNum: Int, secondNum: Int): Unit {
    val result: Int = firstNum + secondNum
    println(result)
}
plusNumbersWithNoReturn(1, 2)

// 간단하게 함수 선언을 하는 방법
fun simplePlusNumbers(firstNum: Int, secondNum: Int) = firstNum + secondNum
simplePlusNumbers(2, 4)

// 가변 인자를 갖는 함수를 선언하는 방법
fun plusMultiplePlusNumbers(vararg numbers: Int) {
    for (number in numbers)
        println(number)
}

plusMultiplePlusNumbers(1, 2, 3, 4, 5)