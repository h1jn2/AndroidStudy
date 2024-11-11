var numbers = listOf<Int>(1, 2, 3)
var numbers1 = mutableListOf<Int>(1, 2, 3)

var numbers2 = setOf<Int>(1, 2, 3, 1)
var numbers3 = mutableSetOf<Int>(1, 2, 3, 1, 3)

var numbers4 = mapOf<Int, String>(1 to "one", 2 to "two")
var numbers5 = mapOf<Int, String>(Pair(1, "one"), Pair(2, "two"))
println(numbers5)