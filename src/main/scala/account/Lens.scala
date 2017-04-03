package account

case class Lens[O, V] (
  get: O => V,
  set: (O, V) => O
)

case class Address(no: String, street: String, city: String, state: String, zip: String)

