fun addNumbers(number1: Int, number2: Int): Int {
    return number1 + number2
}

fun addTenNine(function: (Int, Int) -> Int) {
    val result: Int = function(10, 9)
    println("결과는 $result")
}

addTenNine(::addNumbers)

val addNumbers2: (Int, Int) -> Int = { number1: Int, number2: Int ->
    number1 + number2
}
addTenNine(addNumbers2)

val addNumbers3: (Int, Int) -> Int = { number1, number2 ->
    number1 + number2
}
addTenNine(addNumbers3)

val addNumbers4 = { number1: Int, number2: Int ->
    number1 + number2
}
addTenNine(addNumbers4)

addTenNine { number1, number2 -> number1 + number2 }

val addNumbers5: () -> Int = {
    10 + 9
}

val addNumbers6: (Int) -> Int = {
    10 + 9
}