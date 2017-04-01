package account

case class Amount(amount: BigDecimal, currency: Currency) {
  def +(a: Amount): Amount = Amount(amount + a.amount, currency)

  def -(a: Amount): Amount = Amount(amount - a.amount, currency)

  def *(times: BigDecimal): Amount = Amount(amount * times, currency)
}

