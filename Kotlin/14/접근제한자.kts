var number: Int = 10

fun changeNumber() {
    number = 20 // 하위 스코프에서 상위 스코프의 멤버에 접근할 수 있음
    var number: Int = 100
    println(number)
}

changeNumber()
println(number)


class Numbers(private var number: Int = 10) {
    fun changeNumber() {
        this.number = 100
    }
    fun getNumber(): Int {
        return this.number
    }
}

val numbers = Numbers()
numbers.changeNumber()
println(numbers.getNumber())

//println(numbers.number)
//numbers.number = 100
//println(numbers.number)