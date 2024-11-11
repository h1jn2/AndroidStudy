fun scoreFunc(scoreArr: IntArray): BooleanArray {
    val resultArr = BooleanArray(10)
    for ((index, score) in scoreArr.withIndex()) {
        if (score >= 80) {
            resultArr[index] = true
        }
        else {
            resultArr[index] = false
        }
    }
    return resultArr
}

for (value in scoreFunc(intArrayOf(70, 74, 80, 77, 90, 72, 75, 88, 60, 99)))
    println(value)