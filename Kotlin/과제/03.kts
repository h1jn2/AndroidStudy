val num: Int = 80

fun gradeReturn(score: Int): String {
    if (score >= 90) return "A"
    else if (score >= 80 && score < 90) return "B"
    else if (score >= 70 && score < 80) return "C"
    else return "F"
}

gradeReturn(num)