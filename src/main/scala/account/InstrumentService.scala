package account

trait InstrumentService {
  def getMarketValue(e: Equity, a: Amount): Amount

  def getAccruedInterest(i: String): Amount

  def getHolding(account: Account): Amount = account.balance match {
    case Balance(a, c: Currency, _) => Amount(a.amount, c)
    case Balance(a, e: Equity, _) => getMarketValue(e, a)
    case Balance(a, FixedIncome(i, _, _, c, n), _) =>
      Amount(n * a.amount, c) + getAccruedInterest(i)
  }
}
