package account

import scala.util._

trait AccountCalculationService {
  def calculateInterest[A <: InterestBearingAccount](a: A): Try[Amount] =
    if (a.rateOfInterest == 0) Failure(new Exception("Interest Rate not found"))
    else Success(a.balance.amount * a.rateOfInterest)

  def deductTax(interest: Amount): Try[Amount] =
    if (interest.amount < 1000)
      Success(interest)
    else
      Success(interest - (interest * 0.1))

  def getCurrencyBalance(a: Account): Try[Amount] = Success(a.balance.amount)

  def getAccountFrom(no: String): Try[InterestBearingAccount] = Success(
    Account.savingsAccount(no, "some-account-name", Balance(Amount(1000L)), 0.15, today).get)

  def calculateNetAssetValue(a: Account, interest: Amount): Try[Amount] =
    Success(a.balance.amount + interest)

  def calculateAccountNetAssetsValue(no: String): Try[(Account, Amount)] = for {
    account <- getAccountFrom(no)
    interest <- calculateInterest(account)
    result <- calculateNetAssetValue(account, interest)
  } yield (account, result)
}

object AccountCalculationService extends AccountCalculationService
