class Person {

}

class User1 constructor(name: String) {
    val userName: String

    init {
        println(name)
        userName = name
    }
}

class User2 constructor(name: String) {
    val userName: String = name

}

class User3(name: String) {
    val userName: String = name

}

class User4(name: String = "김아무개") {
    val userName: String = name
}

class User5 constructor(age: Int, name: String) {
    val age: Int
    val name: String

    init {
        this.age = age
        this.name = name
    }
}
class User6 constructor(name: String) {
    val name: String
    var age: Int = 0

    init {
        this.name = name
        println("init")
    }

    constructor(name: String, age: Int): this(name) {
        this.age = age
        println("second")
    }
}

class User7 {
    val age: Int
    val name: String

    constructor(age: Int, name: String) {
        this.age = age
        this.name = name
    }
}

val user1 = User1("홍길동")
val user2 = User2("홍길동")
val user3 = User3("홍길동")
val user4 = User4()
val user5 = User5(20, "홍길동")
//val user6 = User6("가나다")
val user66 = User6("가나다", 20)
val user7 = User7(10, "가가가")