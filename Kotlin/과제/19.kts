open class Warrior constructor(var hp: Int, var ap: Int, var dp: Int) {
    var isDie = false
    var monsterCnt: Int = 0

    open fun attack(monster: Monster) {
        println("Warrior가 공격합니다.")
        monster.defense(this)
    }

    open fun defense(monster: Monster) {
        val damage: Int = monster.ap - this.dp
        this.hp -= if (damage < 0) 0 else damage

        if (hp > 0) {
            println("Warrior 남은 체력: $hp")
        } else {
            println("Warrior가 죽었습니다.")
        }
    }
}

class Knight() : Warrior() {
    override fun attack() {
        println("Knight가 공격합니다.")
    }
}

class Monster(var hp: Int, var ap: Int, var dp: Int) {
    var isDie = false

    fun attack(warrior: Warrior) {
        println("Monster가 공격합니다.")
        warrior.defense()
    }

    fun defense(warrior: Warrior) {
        val damage: Int = warrior.ap - this.dp
        this.hp -= if (damage < 0) 0 else damage

        if (hp > 0) {
            println("Monster 남은 체력: $hp")
        } else {
            println("Monster가 죽었습니다.")
            warrior.monsterCnt++
        }
    }
}