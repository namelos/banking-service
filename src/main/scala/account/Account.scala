package account

import org.joda.time.DateTime

sealed trait Account {
  def number: String
  def name: String
  def balance: Balance
  def dateOfOpening: DateTime
  def dateOfClose: Option[DateTime]
}

case class CheckingAccount(
  number: String,
  name: String,
  balance: Balance,
  dateOfOpening: DateTime,
  dateOfClose: Option[DateTime] = None
) extends Account

sealed trait InterestBearingAccount extends Account {
  def rateOfInterest: BigDecimal
}

case class SavingsAccount(
  number: String,
  name: String,
  balance: Balance,
  rateOfInterest: BigDecimal,
  dateOfOpening: DateTime,
  dateOfClose: Option[DateTime] = None
) extends InterestBearingAccount

case class MoneyMarketingAccount(
  number: String,
  name: String,
  balance: Balance,
  rateOfInterest: BigDecimal,
  dateOfOpening: DateTime,
  dateOfClose: Option[DateTime] = None
) extends InterestBearingAccount



