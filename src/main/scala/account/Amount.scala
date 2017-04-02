package account

case class Amount(amount: BigDecimal, currency: Currency = USD) {
  def +(a: Amount): Amount = Amount(amount + a.amount, currency)

  def -(a: Amount): Amount = Amount(amount - a.amount, currency)
  def -(a: BigDecimal): Amount = Amount(amount - a, currency)

  def *(times: BigDecimal): Amount = Amount(amount * times, currency)

  def <(a: Amount): Boolean = amount < a.amount
}

