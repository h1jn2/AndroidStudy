val number1: Int = 10
val number2: Int? = null

val nullableList: List<Int?> = listOf<Int?>(1, 2, 3, null)
var result: Int = 0
nullableList.forEach {
    result += it
}