package account

case class Amount(amount: BigDecimal, currency: Currency) {
  def +(a: Amount): Amount = Amount(amount + a.amount, currency)

  def -(a: Amount): Amount = Amount(amount - a.amount, currency)
  def -(a: BigDecimal): Amount = Amount(amount - a, currency)

  def *(times: BigDecimal): Amount = Amount(amount * times, currency)
}

