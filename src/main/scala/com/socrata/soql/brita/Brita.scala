package com.socrata.soql.brita

/** Creates an identifier from a (sequence of) strings according to
  * the SoQL specification for creating aliases of expressions. */
object Brita {
  def apply(xs: Iterable[String]) = new BritaImpl(xs).go()
  def apply(x: String) = new BritaImpl(Some(x)).go()

  private class BritaImpl(xs: Iterable[String]) extends AbstractBrita(xs) {
    def isGoodStartCharacter(c: Char) =
      Character.isJavaIdentifierStart(c) || c == '-'

    def isGoodCharacter(c: Char) =
      Character.isJavaIdentifierPart(c) || c == '-'

    def isUnderscoreish(c: Int) =
      c == '_' || c == '-'
  }
}
