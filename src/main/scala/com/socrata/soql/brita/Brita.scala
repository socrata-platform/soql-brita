package com.socrata.soql.brita

@deprecated("Use IdentifierFilter instead", "1.1.0")
object Brita {
  def apply(xs: Iterable[String]) = IdentifierFilter(xs)
  def apply(x: String) = IdentifierFilter(x)
}
