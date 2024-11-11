fun gugu(num: Int): Array<Int> {
    var arr = Array<Int>(9, {0})
    for (i in 1..9) {
        arr[i-1] = num * i
    }
    return arr
}

gugu(3)