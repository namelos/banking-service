package account

import java.util.Date

case class Balance(
  amount: BigDecimal,
  ins: Instrument,
  asOf: Date
)


