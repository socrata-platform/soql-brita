package com.socrata.soql.brita

import org.scalatest.FunSuite
import org.scalatest.matchers.MustMatchers

class BritaTest extends FunSuite with MustMatchers {
  test("A simple identifier is unchanged") {
    Brita("hello") must equal ("hello")
  }

  test("Separate words are underscore-separated") {
    Brita("hello world") must equal ("hello_world")
  }

  test("Runs of non-identifier characters at the start are eliminated") {
    Brita("!#@hello") must equal ("hello")
  }

  test("Runs of non-identifier characters at the end are eliminated") {
    Brita("hello!@#") must equal ("hello")
  }

  test("Runs of non-identifier characters in the middle become a single underscore if not adjacent to one") {
    Brita("hel!@#lo") must equal ("hel_lo")
  }

  test("Runs of non-identifier characters in the middle disappear if adjacent to an underscore") {
    Brita("hel!@#_lo") must equal ("hel_lo")
    Brita("hel_!@#lo") must equal ("hel_lo")
  }

  test("An underscore is prepended if the identfier does not start with a valid start character") {
    Brita("5") must equal ("_5")
  }

  test("An empty string returns a single underscore") {
    Brita("") must equal ("_")
  }
}
