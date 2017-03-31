package account

import com.github.nscala_time.time.Imports._

trait Account {
  def number: String
  def name: String
  def balance: Balance
}

case class CheckingAccount(
  number: String,
  name: String,
  balance: Balance
) extends Account

trait InterestBearingAccount extends Account {
  def rateOfInterest: BigDecimal
}

case class SavingAccount(
  number: String,
  name: String,
  rateOfInterest: BigDecimal,
  balance: Balance
) extends InterestBearingAccount

case class MoneyMarketingAccount(
  number: String,
  name: String,
  rateOfInterest: BigDecimal,
  balance: Balance
) extends InterestBearingAccount

trait AccountService {
  def calculateInterest[A <: InterestBearingAccount]
  (account: A, period: Period): BigDecimal = ???
}

