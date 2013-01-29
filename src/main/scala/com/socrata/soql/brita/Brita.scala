package com.socrata.soql.brita

@deprecated("Use IdentifierFilter instead")
object Brita {
  def apply(xs: Iterable[String]) = IdentifierFilter(xs)
  def apply(x: String) = IdentifierFilter(x)
}
