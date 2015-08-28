package com.socrata.soql.brita

@deprecated("Use AsciiIdentifierFilter instead", "1.1.0")
object LegacyBrita {
  def apply(xs: Iterable[String]) = AsciiIdentifierFilter(xs)
  def apply(x: String) = AsciiIdentifierFilter(x)
}
