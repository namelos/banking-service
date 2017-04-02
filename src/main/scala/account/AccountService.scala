package account

import scala.util._

trait AccountService {
  def calculateInterest[A <: InterestBearingAccount](a: A, balance: Amount): Try[Amount] =
    if (a.rateOfInterest == 0) Failure(new Exception("Interest Rate not found"))
    else Success(balance * a.rateOfInterest)

  def deductTax(interest: Amount): Try[Amount] =
    if (interest.amount < 1000)
      Success(interest)
    else
      Success(interest - (interest * 0.1))

  def getCurrencyBalance(a: Account): Try[Amount] = Success(a.balance.amount)

  def getAccountFrom(no: String): Try[Account] = Success(
    SavingsAccount(no, "some-account-name", Balance(Amount(1000L)), today, 0.15))

  def calculateNetAssetValue(a: Account, balance: Amount, interest: Amount): Try[Amount] =
    Success(balance + interest)

  def calculateAccountNetAssetsValue(no: String): Try[(Account, Amount)] = for {
    account <- getAccountFrom(no)
    balance <- getCurrencyBalance(account)
    interest <- calculateInterest(account, balance)
    result <- calculateNetAssetValue(account, balance, interest)
  } yield (account, result)
}

object AccountService extends AccountService
