package account

import java.util.Date

import org.joda.time.DateTime

trait Account {
  def number: String
  def name: String
  def balance: Balance
  def dateOfOpening: DateTime
}

case class CheckingAccount(
  number: String,
  name: String,
  balance: Balance,
  dateOfOpening: DateTime
) extends Account

trait InterestBearingAccount extends Account {
  def rateOfInterest: BigDecimal
}

case class SavingsAccount(
  number: String,
  name: String,
  balance: Balance,
  dateOfOpening: DateTime,
  rateOfInterest: BigDecimal
) extends InterestBearingAccount

case class MoneyMarketingAccount(
  number: String,
  name: String,
  balance: Balance,
  dateOfOpening: DateTime,
  rateOfInterest: BigDecimal
) extends InterestBearingAccount



