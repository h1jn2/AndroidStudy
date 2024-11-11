val number1: Int = 10
val number2: Int = 20
if (number1 == 10) {
    println("10")
} else if (number1 == 20) {
    println("20")
} else {
    println("x")
}

val number3: Int = if (number2 > 30) 40 else 50
println(number3)

val number4: Int = 5
when (number4) {
    5 -> {
        println("5")
    }
    6 -> {
        println("6")
    }
    else -> {
        println("x")
    }
}
