var arr1 = arrayOf(true, "11", 1, 0.2, null)    // 자료형 생략 시 non-null, nullable 모두 올 수 있음
var arr2 = arrayOf<Int?>(1, 2, 3, 4, null)
var arr3 = intArrayOf(1, 2, 3, 4)

var arr4 = Array(10, { 0 })
var arr5 = IntArray(10, { 0 })
//var arr6 = StringArray(10, {"Aa"})    // 불가능

var arr7 = Array<Int>(10, { 0 })
var arr8 = Array<String>(10, { "" })

var arr9 = arrayOf<Int>(10, 20, 30, 40)
println(arr9.get(0))
println(arr9[0])

arr9.set(0, 100)
println(arr9.get(0))

val examScore1: Int = 100
val examScore2: Int = 90
val examScore3: Int = 80
var examScores = arrayOf<Int>(examScore1, examScore2, examScore3)

val examScore4: Int = examScores[0]