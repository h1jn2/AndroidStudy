val number: Int? = null
//val number2: Int = null

val num1: Int = 3 + 5
val num2: Int = 10
val num3: Int = num1 + num2
println(num3)

val number2: Int? = 3 + 5
val number3: Int? = 10
//val number4: Int? = number2 + number3     // nullable 변수는 연산 불가능
val number4: Int? = number2!! + number3!!
println(number4)

// 비교연산은 가능
if (null == null) {
    println("same")
} else {
    println("not same")
}
