class Book() {
    var title: String = "모름"
        get() {
            return field
        }
        set(value) {
            field = value
        }
}

//val book1 = Book()
//println(book1.title)

class Book2() {
    lateinit var title: String

    fun nextPage() {
        if (::title.isInitialized) {
            println("Next Page")
        }
        else {
            println("초기화 필요")
        }
    }
}

class Book3 {
    val title: String by lazy {
        println("lazy 초기화")
        "책 이름"
    }
}

val book1 = Book3()
println(book1.title)
//book1.title = "책이름"
//println(book1.nextPage())
