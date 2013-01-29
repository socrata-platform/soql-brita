package com.socrata.soql.brita

/** Creates an identifier from a (sequence of) strings according to
  * the SoQL specification for creating aliases of expressions. */
object IdentifierFilter {
  def apply(xs: Iterable[String]) = new Impl(xs).go()
  def apply(x: String) = new Impl(Some(x)).go()

  private class Impl(xs: Iterable[String]) extends AbstractIdentifierFilter(xs) {
    def isGoodStartCharacter(c: Char) =
      Character.isJavaIdentifierStart(c) || c == '-'

    def isGoodCharacter(c: Char) =
      Character.isJavaIdentifierPart(c) || c == '-'

    def isUnderscoreish(c: Int) =
      c == '_' || c == '-'
  }
}
