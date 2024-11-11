open class Warrior(name: String, power: Int, type: String) {
    open fun attack() {
        println("공격")
    }
}

class DefenseWarrior(name: String, power: Int): Warrior(name, power, "고블린") {
    override fun attack() {
        println("으악")
    }
    fun defense() {
        println("방어")
    }
}

class HardAttackWarrior: Warrior {
    constructor(name: String, power: Int, bonusPower: Int): super(name, power, "골렘")


    fun hardAttack() {
        println("강한 공격")
    }
}

val defenseWarrior1 = DefenseWarrior("똑똑이", 100)
defenseWarrior1.attack()
val hardAttackWarrior1 = HardAttackWarrior("고올", 100, 20)
hardAttackWarrior1.attack()