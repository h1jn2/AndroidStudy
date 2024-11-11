val number: Int = 10
val numberString: String = number.toString()
val number2: Int = numberString.toInt()

open class Warrior(name: String, power: Int, type: String) {
    open fun attack() {
        println("공격")
    }
}

class DefenseWarrior(name: String, power: Int): Warrior(name, power, "고블린") {
    fun defense() {
        println("방어")
    }
}

val warrior: Warrior = DefenseWarrior("방어형", 10)
//warrior.defense()     // 사용 불가
if (warrior is DefenseWarrior) {
    // 스마트캐스팅: if문 안에서만 defenseWarrior를 사용할 수 있도록 해줌 (val만 사용가능)
    warrior.defense()
}

val warrior2: DefenseWarrior = warrior as DefenseWarrior