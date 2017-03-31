package account

import org.scalatest.FunSpec
import AccountService._

class AccountServiceSpec extends FunSpec {
<<<<<<< 5125b4cad0e149de282e549eaef57234765ed409
  describe("AndThen") {
    it("returns same answer as map") {
      val a1 = SavingAccount("a-0001", "IBM", Balance(100000, USD, today), today, 0.1)
      val a2 = SavingAccount("a-0002", "Google", Balance(200000, USD, today), today, 0.1)
      val a3 = SavingAccount("a-0003", "Chase", Balance(125000, USD, today), today, 0.1)

      val accounts = List(a1, a2, a3)

      val expected = accounts.map(calculateInterest).map(deductTax)

=======
  val a1 = SavingAccount("a-0001", "IBM", Balance(100000, USD, today), today, 0.1)
  val a2 = SavingAccount("a-0002", "Google", Balance(200000, USD, today), today, 0.1)
  val a3 = SavingAccount("a-0003", "Chase", Balance(125000, USD, today), today, 0.1)

  describe("AndThen") {
    it("returns same answer as map") {
      val accounts = List(a1, a2, a3)

      val expected = accounts.map(calculateInterest).map(deductTax)
>>>>>>> add andThen test cases with account service
      val actual = accounts.map(calculateInterest andThen deductTax)

      assert(expected == actual)
    }
  }
}
