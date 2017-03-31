package account

import com.github.nscala_time.time.Imports._

trait Account {
  def number: String
  def name: String
}

case class CheckingAccount(number: String, name: String) extends Account

trait InterestBearingAccount extends Account {
  def rateOfInterest: BigDecimal
}

case class SavingAccount(
  number: String,
  name: String,
  rateOfInterest: BigDecimal
) extends InterestBearingAccount

case class MoneyMarketingAccount(
  number: String,
  name: String,
  rateOfInterest: BigDecimal
) extends InterestBearingAccount

trait AccountService {
  def calculateInterest[A <: InterestBearingAccount]
  (account: A, period: Period): BigDecimal = ???
}

