package account

import org.scalacheck.Properties
import org.scalacheck.Prop.forAll
import scala.util.Success

import AccountService._

class AccountServiceSpec extends Properties("AccountService") {
//  property("Equal credit & debit in sequence retain the same balance") =
//    forAll((a: Account, m: Amount) => {
//      val Success((before, after)) = for {
//        b <- balance(a)
//        c <- credit(a, m)
//        d <- debit(c, m)
//      } yield (b, d.balance)
//      before == after
//    })
}
