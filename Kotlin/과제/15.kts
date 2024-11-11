fun numFunc(numList: List<Int>): Map<String, List<Int>> {
    var resultMap = mutableMapOf<String, List<Int>>()
    var evenList = mutableListOf<Int>()
    var oddList = mutableListOf<Int>()
    for (i in 0 until numList.size) {
        if (numList[i] % 2 == 0) {
            evenList.add(numList[i])
        }
        else {
            oddList.add(numList[i])
        }
    }
    resultMap.put("짝수", evenList)
    resultMap.put("홀수", oddList)

    return resultMap
}

numFunc(listOf<Int>(5, 4, 3, 7, 8, 13, 323, 434, 44))