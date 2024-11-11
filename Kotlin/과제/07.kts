fun sumValue(value: Int): Int {
    var result: Int = 0
    for (i in 1..value)
        result += i
    return result
}

sumValue(10)