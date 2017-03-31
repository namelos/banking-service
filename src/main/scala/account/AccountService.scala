package account

trait AccountService {
  def calculateInterest[A <: InterestBearingAccount]: A => BigDecimal =
      a => a.balance.amount * a.rateOfInterest

  def deductTax: BigDecimal => BigDecimal =
    interest =>
      if (interest < 1000)
        interest
      else
        interest - 0.1 * interest
}

object AccountService extends AccountService
