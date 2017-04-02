package account

import scala.util.Try

trait AccountService {
  def calculateInterest[A <: InterestBearingAccount](a: A): Amount =
    a.balance.amount * a.rateOfInterest

  def deductTax(interest: Amount): Amount =
      if (interest.amount < 1000)
        interest
      else
        interest - (interest * 0.1)

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
