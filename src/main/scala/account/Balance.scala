package account

import org.joda.time.DateTime

case class Balance(
  amount: BigDecimal,
  ins: Instrument = USD,
  asOf: DateTime = today
)


