package com.socrata.soql.brita

@deprecated("Use AsciiIdentifierFilter instead")
object LegacyBrita {
  def apply(xs: Iterable[String]) = AsciiIdentifierFilter(xs)
  def apply(x: String) = AsciiIdentifierFilter(x)
}
