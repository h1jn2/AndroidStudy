import kotlin.contracts.contract

class Bank {
    fun save(account: Account, money: Int) {
        if (money >= 0) account.balance += money
        else println("마이너스 금액은 저금할 수 없습니다.")
    }

    fun withDraw(account: Account, money: Int) {
        if (money <= account.checkBalance()) account.balance -= money
        else println("잔액이 부족합니다.")
    }
}

class Account constructor(val initialBalance: Int = 0) {
    var balance: Int

    init {
        balance = initialBalance
    }

    fun checkBalance(): Int {
        return this.balance
    }
}

val account = Account(1000)
val bank = Bank()
println(account.checkBalance())
bank.save(account, -1500)
bank.save(account, 1500)
println(account.checkBalance())
bank.withDraw(account, 3000)
bank.withDraw(account, 2000)
println(account.checkBalance())
