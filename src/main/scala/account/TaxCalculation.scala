//package account
//
//sealed trait TaxType
//case object Tax extends TaxType
//case object Fee extends TaxType
//case object Commission extends TaxType
//
//sealed trait TransactionType
//case object InterestComputation extends TransactionType
//case object Dividend extends TransactionType
//
//trait TaxCalculationTable {
//  type T <: TransactionType
//
//  val transactionType: T
//
//  def getTaxes: Map[TaxType, BigDecimal] = ???
//}
//
//trait TaxCalculation {
//  type S <: TaxCalculationTable
//
//  val table: S
//
//  def calculate(taxOn: Amount): Amount =
//    table.getTaxes.map { case (t, r) =>
//      doCompute(taxOn, r)
//    }.sum
//
//  protected def doCompute(taxOn: Amount, rate: BigDecimal): Amount =
//    taxOn * rate
//}
//
//trait SingaporeTaxCalculation extends TaxCalculation {
//  def calculateGST(tax: Amount, gstRate: BigDecimal): Amount =
//    tax * gstRate
//}
//
//trait InterestCalculation {
//  type C <: TaxCalculation
//
//  val taxCalculation: C
//
//  def interest(b: Balance): Option[Amount] = Some(b.amount * 0.05)
//
//  def calculate(balance: Balance): Option[Amount] =
//    interest(balance).map { i =>
//      i - taxCalculation.calculate(i)
//    }
//}
//
//object InterestTaxCalculationTable extends TaxCalculationTable {
//  type T = TransactionType
//  val transactionType = InterestComputation
//}
//
//object TaxCalculation extends TaxCalculation {
//  type S = TaxCalculationTable
//  val table = InterestTaxCalculationTable
//}
//
//object InterestCalculation extends InterestCalculation {
//  val C = TaxCalculation
//  val taxCalculation = TaxCalculation
//}
