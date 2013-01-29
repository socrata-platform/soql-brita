package com.socrata.soql.brita

/** Creates an identifier from a (sequence of) strings according to a
  * non-unicode-aware and non-hyphen-accepting variation of the SoQL
  * specification for creating aliases of expressions. */
object LegacyBrita {
  def apply(xs: Iterable[String]) = new BritaImpl(xs).go()
  def apply(x: String) = new BritaImpl(Some(x)).go()

  private class BritaImpl(xs: Iterable[String]) extends AbstractBrita(xs) {
    def isGoodStartCharacter(c: Char) =
      (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_'

    def isGoodCharacter(c: Char) =
      (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || c == '_'

    def isUnderscoreish(c: Int) =
      c == '_'
  }
}
