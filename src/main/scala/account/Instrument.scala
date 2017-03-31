package account

import java.util.Date

trait Instrument

case class Equity(
  isin: String,
  name: String,
  dateOfIssue: Date
) extends Instrument

case class FixedIncome(
  isin: String,
  name: String,
  dateOfIssue: Date,
  issueCurrency: Currency,
  nominal: BigDecimal
) extends Instrument


