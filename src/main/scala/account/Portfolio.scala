package account

import org.joda.time.DateTime

case class Money(amount: Amount)
case class Position(account: Account, ccy: Currency, balance: Money)
case class Portfolio(pos: Seq[Position], asOf: DateTime) {
  def balance(a: Account, ccy: Currency) =
    pos.find(p => p.account == a && p.ccy == ccy)
      .map(_.balance.amount).getOrElse(0)

  def balance(ccy: Currency) =
    pos.filter(_.ccy == ccy)
      .map(_.balance.amount).foldLeft(Amount(0))(_ + _)
}
