fun printDice() {
    for (i in 1..6) {
        for (j in 1..6) {
            if (i + j == 6)
                println("[" + i + "," + j + "]")
            else
                continue
        }
    }
}

printDice()