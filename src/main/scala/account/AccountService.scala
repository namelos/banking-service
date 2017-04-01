package account

import scala.util.Try

trait AccountService {
  def calculateInterest[A <: InterestBearingAccount]: A => Amount =
      a => a.balance.amount * a.rateOfInterest

  def deductTax: BigDecimal => BigDecimal =
    interest =>
      if (interest < 1000)
        interest
      else
        interest - 0.1 * interest

  def getCurrencyBalance(a: Account): Try[Amount] = ???

  def getAccountFrom(no: String): Try[Account] = ???

  def calculateNetAssetValue(a: Account, balance: Amount): Try[Amount] = ???

  def operations: Try[(Account, Amount)] = for {
    s <- getAccountFrom("a1")
    b <- getCurrencyBalance(s)
    v <- calculateNetAssetValue(s, b)
  } yield (s, v)
}

object AccountService extends AccountService
