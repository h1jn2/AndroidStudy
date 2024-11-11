class FootballPlayer (uniform: String, ball: String) {
    val uniform: String
    val ball: String

    init {
        this.uniform = uniform
        this.ball = ball
    }

    fun kick() {
        println("공을 찼다")
    }
    fun pass() {
        println("패스")
    }
}

val player = FootballPlayer("빨간색", "축구공")
println(player.uniform)
player.kick()
player.pass()