package com.socrata.soql.brita

import org.scalatest.FunSuite
import org.scalatest.matchers.MustMatchers

class IdentifierFilterTest extends FunSuite with MustMatchers {
  test("A simple identifier is unchanged") {
    IdentifierFilter("hello") must equal ("hello")
  }

  test("Separate words are underscore-separated") {
    IdentifierFilter("hello world") must equal ("hello_world")
  }

  test("Runs of non-identifier characters at the start are eliminated") {
    IdentifierFilter("!#@hello") must equal ("hello")
  }

  test("Runs of non-identifier characters at the end are eliminated") {
    IdentifierFilter("hello!@#") must equal ("hello")
  }

  test("Runs of non-identifier characters in the middle become a single underscore if not adjacent to one") {
    IdentifierFilter("hel!@#lo") must equal ("hel_lo")
  }

  test("Runs of non-identifier characters in the middle disappear if adjacent to an underscore") {
    IdentifierFilter("hel!@#_lo") must equal ("hel_lo")
    IdentifierFilter("hel_!@#lo") must equal ("hel_lo")
  }

  test("An underscore is prepended if the identfier does not start with a valid start character") {
    IdentifierFilter("5") must equal ("_5")
  }

  test("An empty string returns a single underscore") {
    IdentifierFilter("") must equal ("_")
  }

  test("A sequence of strings are underscore-separated") {
    IdentifierFilter(List("hello", "world")) must equal ("hello_world")
  }
}
