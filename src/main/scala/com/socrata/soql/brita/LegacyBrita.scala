package com.socrata.soql.brita

@deprecated("Use LegacyIdentifierFilter instead")
object LegacyBrita {
  def apply(xs: Iterable[String]) = LegacyIdentifierFilter(xs)
  def apply(x: String) = LegacyIdentifierFilter(x)
}
