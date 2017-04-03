package account

import org.joda.time.DateTime

import scala.util.{Failure, Success, Try}

sealed trait Account {
  def number: String
  def name: String
  def balance: Balance
  def dateOfOpening: DateTime
  def dateOfClose: Option[DateTime]
}

final case class CheckingAccount(number: String, name: String, balance: Balance,
  dateOfOpening: DateTime, dateOfClose: Option[DateTime] = None
) extends Account

sealed trait InterestBearingAccount extends Account {
  def rateOfInterest: BigDecimal
}

final case class SavingsAccount(number: String, name: String, balance: Balance,
  rateOfInterest: BigDecimal, dateOfOpening: DateTime, dateOfClose: Option[DateTime] = None
) extends InterestBearingAccount

final case class MoneyMarketingAccount(number: String, name: String, balance: Balance,
  rateOfInterest: BigDecimal, dateOfOpening: DateTime, dateOfClose: Option[DateTime] = None
) extends InterestBearingAccount

object Account {
  def checkingAccount(number: String, name: String, balance: Balance,
    dateOfOpening: DateTime, dateOfClose: Option[DateTime]): Try[Account] = ???

  def savingsAccount(number: String, name: String, balance: Balance,
    rateOfInterest: BigDecimal, dateOfOpening: DateTime, dateOfClose: Option[DateTime] = None) = {
      closeDateCheck(dateOfOpening, dateOfClose) map { d =>
        if (rateOfInterest <= BigDecimal(0))
          throw new Exception(s"Interest rate $rateOfInterest must be > 0")
        else
          SavingsAccount(number, name, balance, rateOfInterest, d._1, d._2)
      }
  }

  private def closeDateCheck(openDate: DateTime, closeDate: Option[DateTime]):
    Try[(DateTime, Option[DateTime])] = {
      closeDate map { cd =>
        if (cd isBefore openDate)
          Failure { new Exception(s"Close date [$cd] cannot be earlier than open date [$openDate]") }
        else
          Success { (openDate, Some(openDate)) }
      } getOrElse {
        Success{ (openDate, Some(openDate)) }
      }
    }
}


