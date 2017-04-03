package account

import lib.Lens
import org.scalatest.FunSpec

case class Address(no: String, street: String, city: String, state: String, zip: String)
case class Customer(no: Int, name: String, address: Address)

class LensesSpec extends FunSpec {
  describe("lenses") {
    val addressNoLens = Lens[Address, String]  (
      get = _.no,
      set = (o, v) => o.copy(no = v)
    )

    val address = Address(no = "B-12", street = "Monroe Street", city = "Denver", state = "CO", zip = "80231")

    it("should get address number") { assert(addressNoLens.get(address) == "B-12") }

    it("should set address number") {
      val modifiedAddress = addressNoLens.set(address, "A-10")

      assert(addressNoLens.get(modifiedAddress) == "A-10")
    }

    val custAddressLens = Lens[Customer, Address] (
      get = _.address,
      set = (o, v) => o.copy(address = v)
    )

    val custAddrNoLens: Lens[Customer, String] = Lens.compose(custAddressLens, addressNoLens)

    val customer = Customer(2, "John D Cook", address)

    it("should get address number from customer") {
      assert(custAddrNoLens.get(customer) == "B-12")
    }

    it("should set address number of customer") {
      val modifiedCustomer = custAddrNoLens.set(customer, "A-10")

      assert(custAddrNoLens.get(modifiedCustomer) == "A-10")
    }
  }
}
