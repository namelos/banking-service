package account

import org.scalatest.FunSpec
import AccountCalculationService._

class AccountCalculationServiceSpec extends FunSpec {
  val a1 = Account.savingsAccount("a-0001", "IBM", Balance(Amount(100000)), 0.1, today).get
  val a2 = Account.savingsAccount("a-0002", "Google", Balance(Amount(200000)), 0.1, today).get
  val a3 = Account.savingsAccount("a-0003", "Chase", Balance(Amount(125000)), 0.1, today).get

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
