package account

case class Amount(amount: BigDecimal, currency: Currency) {
  def +(amount: Amount): Amount = ???
}

