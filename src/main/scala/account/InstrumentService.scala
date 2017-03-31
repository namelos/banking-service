package account

/**
  * Created by namelos on 17-3-31.
  */
trait InstrumentService {
  def getMarketValue(e: Equity, a: BigDecimal): Amount

  def getAccruedInterest(i: String): Amount

  def getHolding(account: Account): Amount = account.balance match {
    case Balance(a, c: Currency, _) => Amount(a, c)
    case Balance(a, e: Equity, _) => getMarketValue(e, a)
    case Balance(a, FixedIncome(i, _, _, c, n), _) =>
      Amount(n * a, c) + getAccruedInterest(i)
  }
}
