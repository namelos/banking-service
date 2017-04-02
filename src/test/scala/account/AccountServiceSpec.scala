package account

import org.scalatest.FunSpec
import AccountService._

class AccountServiceSpec extends FunSpec {
  val a1 = SavingsAccount("a-0001", "IBM", Balance(Amount(100000)), today, 0.1)
  val a2 = SavingsAccount("a-0002", "Google", Balance(Amount(200000)), today, 0.1)
  val a3 = SavingsAccount("a-0003", "Chase", Balance(Amount(125000)), today, 0.1)

  describe("AndThen") {
    it("returns same answer as map") {
      val accounts = List(a1, a2, a3)

      val expected = accounts
        .map(calculateInterest)
        .map(_ flatMap deductTax)

      val actual = accounts.map(
        calculateInterest _
          andThen (_ flatMap deductTax))

      assert(expected == actual)
    }
  }
}
