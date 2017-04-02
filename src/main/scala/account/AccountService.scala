package account

import scala.util._

trait AccountService {
  def calculateInterest[A <: InterestBearingAccount](a: A): Try[Amount] =
    if (a.rateOfInterest == 0) Failure(new Exception("Interest Rate not found"))
    else Success(a.balance.amount * a.rateOfInterest)

  def deductTax(interest: Amount): Try[Amount] =
    if (interest.amount < 1000)
      Success(interest)
    else
      Success(interest - (interest * 0.1))

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
