val numbers = listOf<Int>(1, 2, 3)
try {
    numbers.get(5)
} catch (exception: Exception) {
    println(exception)
}
try {
    numbers.get(5)
} catch (exception: ArrayIndexOutOfBoundsException) {
    println("예외발생")
} catch (exception: IllegalArgumentException) {
    println("A")
} finally {
    println("finally")
}