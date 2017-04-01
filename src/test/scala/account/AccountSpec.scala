package account

import org.scalatest.FunSpec

class AccountSpec extends FunSpec {
  describe("Case class") {
    it("are immutable ADTs which can be test equal") {
      val day = today

      val a1 = SavingAccount("a-0001", "IBM", Balance(100000, USD, day), day, 0.1)

      val a2 = a1.copy(rateOfInterest = 0.15)

      assert(a2 == SavingAccount("a-0001", "IBM", Balance(100000, USD, day), day, 0.15))
    }
  }
}
