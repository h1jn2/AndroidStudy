fun killArmy(firstList: List<String>, secondList: List<String>, cnt: Int): List<List<String>>? {
    if (firstList.size < cnt || secondList.size < cnt) return null

    var firstResult = mutableListOf<String>()
    var secondResult = mutableListOf<String>()

    for ((index, num) in firstList.withIndex()) {
        if (index != cnt) firstResult.add(firstList[index])
    }
    for ((index, num) in secondList.withIndex()) {
        if (index != cnt) secondResult.add(secondList[index])

    }

    return listOf(firstResult, secondResult)
}

killArmy(listOf("A", "B", "C", "D", "E"), listOf("A", "B", "C"), 2)