loop@for (i in 1..3) {
    println("i : " + i)
    for (j in 1..3) {
        if (j == 2) break@loop
        else println("j : " + j)
    }
}

loop@for (i in 1..3) {
    println("i : " + i)
    for (j in 1..3) {
        if (j == 2) continue@loop
        else println("j : " + j)
    }
}

// foreach는 break와 continue 사용 불가
listOf(1, 2, 3).forEach loop@{
    if (it == 2) return@loop
    else println(it)
}