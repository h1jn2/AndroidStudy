for (value in 0..3) {
    println(value)
}

for (value in 0..10 step 2) {
    println(value)
}

for (value in 5 downTo 2) {
    println(value)
}

val numbers = listOf<Int>(1, 2, 3, 4)
for (number in numbers) {
    println(number)
}

for (number in 0 until numbers.size) {
    println(number)
}

for ((index, number) in numbers.withIndex()) {
    println("" + index + " | " + number)
}

numbers.forEach { num ->
    println(num)
}