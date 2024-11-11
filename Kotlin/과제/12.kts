fun eat(_num1: Int, _num2: Int) {
    var cnt: Int = 0
    var num2: Int = _num2
    do {
        println("밥을 먹었다")
        num2++
        cnt = _num1 - num2
    } while (cnt > 0)
    println("배가 부르다")
}

eat(3, 1)