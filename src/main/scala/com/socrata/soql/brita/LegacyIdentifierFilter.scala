package com.socrata.soql.brita

@deprecated("Use AsciiIdentifierFilter instead")
object LegacyIdentifierFilter {
  def apply(xs: Iterable[String]) = AsciiIdentifierFilter(xs)
  def apply(x: String) = AsciiIdentifierFilter(x)
}
