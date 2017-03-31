package account

import org.scalatest.FunSpec
import AccountService._

class AccountServiceSpec extends FunSpec {
  describe("AndThen") {
    it("returns same answer as map") {
      val a1 = SavingAccount("a-0001", "IBM", Balance(100000, USD, today), today, 0.1)
      val a2 = SavingAccount("a-0002", "Google", Balance(200000, USD, today), today, 0.1)
      val a3 = SavingAccount("a-0003", "Chase", Balance(125000, USD, today), today, 0.1)

      val accounts = List(a1, a2, a3)

      val expected = accounts.map(calculateInterest).map(deductTax)

      val actual = accounts.map(calculateInterest andThen deductTax)

      assert(expected == actual)
    }
  }
}
