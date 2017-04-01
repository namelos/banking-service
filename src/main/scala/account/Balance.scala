package account

import org.joda.time.DateTime

case class Balance(
  amount: Amount,
  ins: Instrument = USD,
  asOf: DateTime = today
)


