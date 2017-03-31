package lib

sealed abstract class Either[+A, +B]

final case class Left[+A, +B](a: A) extends Either[A, B]

final case class Right[+A, +B](b: B) extends Either[A, B]
