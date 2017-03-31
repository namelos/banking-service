package account

import java.util.Date

import com.github.nscala_time.time.Imports._

trait Account {
  def number: String
  def name: String
  def balance: Balance
  def dateOfOpening: Date
}

case class CheckingAccount(
  number: String,
  name: String,
  balance: Balance,
  dateOfOpening: Date
) extends Account

trait InterestBearingAccount extends Account {
  def rateOfInterest: BigDecimal
}

case class SavingAccount(
  number: String,
  name: String,
  balance: Balance,
  dateOfOpening: Date,
  rateOfInterest: BigDecimal
) extends InterestBearingAccount

case class MoneyMarketingAccount(
  number: String,
  name: String,
  balance: Balance,
  dateOfOpening: Date,
  rateOfInterest: BigDecimal
) extends InterestBearingAccount

trait AccountService {
  def calculateInterest[A <: InterestBearingAccount]
  (account: A, period: Period): BigDecimal = ???
}

