package account

import org.scalatest.FunSpec

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
  }
}
