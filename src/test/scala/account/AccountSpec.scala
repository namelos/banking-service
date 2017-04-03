package account

import org.scalatest.FunSpec

class AccountSpec extends FunSpec {
  describe("Case class") {
    it("are immutable ADTs which can be test equal") {
      val a1 = Account.savingsAccount("a-0001", "IBM", Balance(Amount(100000)), 0.1, today).get

      val a2 = a1.copy(rateOfInterest = 0.15)

      assert(a2 == Account.savingsAccount("a-0001", "IBM", Balance(Amount(100000)), 0.15, today).get)
    }
  }
}

