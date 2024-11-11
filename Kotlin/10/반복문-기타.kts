val numbers = intArrayOf(5, 10, 15)

// 값이 필요할 때 (foreach)
for (number in numbers) {
    println(number)
}
// 값과 인덱스가 필요할 때 (foreachindexed)
for ((index, number) in numbers.withIndex()) {
    println("" + index + " | " + number)
}
// 인덱스가 필요할 때
for (number in numbers.indices) {
    println(number)
}

numbers.forEachIndexed { index, value ->
    
}