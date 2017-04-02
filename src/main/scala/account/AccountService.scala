package account

import org.joda.time.DateTime

import scala.util.{Failure, Success, Try}

trait AccountService[Account, Amount, Balance] {
  def open(no: String, name: String, openDate: Option[DateTime]): Try[Account]
  def close(account: Account, closeDate: Option[DateTime]): Try[Account]
  def debit(account: Account, amount: Amount): Try[Account]
  def credit(account: Account, amount: Amount): Try[Account]
  def balance(account: Account): Try[Balance]

  def transfer(from: Account, to: Account, amount: Amount): Try[(Account, Account, Amount)] = for {
    a <- debit(from, amount)
    b <- credit(to, amount)
  } yield (a, b, amount)
}

object AccountService extends AccountService[Account, Amount, Balance] {
  def open(no: String, name: String, openDate: Option[DateTime]) =
    if (no.isEmpty || name.isEmpty)
      Failure(new Exception(s"Account no or name cannot be blank"))
    else if (openDate.getOrElse(today) isBefore today)
      Failure(new Exception(s"Cannot open account in the past"))
    else
      Success(SavingsAccount(no, name, Balance(Amount(0)), 0, openDate getOrElse today))

  def close(account: Account, closeDate: Option[DateTime]) = {
    val cd = closeDate getOrElse today

    if (cd isBefore account.dateOfOpening)
      Failure(new Exception(s"Close date $cd cannot be before opening date ${account.dateOfOpening}"))
    else
      account match {
        case a: CheckingAccount =>
          Success { a.copy(dateOfClose = Some(cd)) }
        case a: SavingsAccount =>
          Success { a.copy(dateOfClose = Some(cd)) }
        case a: MoneyMarketingAccount =>
          Success { a.copy(dateOfClose = Some(cd)) }
        case _ =>
          Failure { new Exception(s"No matching account type to close") }
      }
  }

  def debit(account: Account, amount: Amount) = {
    if (account.balance.amount < amount)
      Failure { new Exception(s"Insufficient balance") }
    else
      account match {
        case a: CheckingAccount =>
          Success { a.copy(balance = Balance(a.balance.amount - amount)) }
        case a: SavingsAccount =>
          Success { a.copy(balance = Balance(a.balance.amount - amount)) }
        case a: MoneyMarketingAccount =>
          Success { a.copy(balance = Balance(a.balance.amount - amount)) }
        case _ =>
          Failure { new Exception(s"No matching account type to debit") }
      }
  }

  def credit(account: Account, amount: Amount) =
    account match {
      case a: CheckingAccount =>
        Success { a.copy(balance = Balance(a.balance.amount + amount)) }
      case a: SavingsAccount =>
        Success { a.copy(balance = Balance(a.balance.amount + amount)) }
      case a: MoneyMarketingAccount =>
        Success { a.copy(balance = Balance(a.balance.amount + amount)) }
      case _ =>
        Failure { new Exception(s"No matching account type to credit") }
    }

  def balance(account: Account) = Success { account.balance }
}