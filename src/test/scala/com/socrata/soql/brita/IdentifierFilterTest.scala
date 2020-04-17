package com.socrata.soql.brita

import org.scalatest.FunSuite
import org.scalatest.MustMatchers
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

class IdentifierFilterTest extends FunSuite with MustMatchers with ScalaCheckPropertyChecks {
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

  test("An empty list returns a single underscore") {
    IdentifierFilter(Nil) must equal ("_")
  }

  test("A sequence of strings are underscore-separated") {
    IdentifierFilter(List("hello", "world")) must equal ("hello_world")
  }

  test("Initial non-BMP character") {
    val nonBMP = new String(Character.toChars(0x10000)) + "x"
    IdentifierFilter(nonBMP) must equal (nonBMP)
  }

  test("Subsequent non-BMP character") {
    val nonBMP = "x" + new String(Character.toChars(0x10000)) + "x"
    IdentifierFilter(nonBMP) must equal (nonBMP)
  }

  test("Multiple non-BMP character") {
    val nonBMP = "x" + new String(Character.toChars(0x10000) ++ Character.toChars(0x10001) ++ Character.toChars(0x10002)) + "x"
    IdentifierFilter(nonBMP) must equal (nonBMP)
  }

  test("Illegal initial non-BMP character") {
    val nonBMP = new String(Character.toChars(0x10100)) + "x"
    IdentifierFilter(nonBMP) must equal ("x")
  }

  test("Illegal medial non-BMP character") {
    val nonBMP = "x" + new String(Character.toChars(0x10100)) + "y"
    IdentifierFilter(nonBMP) must equal ("x_y")
  }

  test("Illegal terminal non-BMP character") {
    val nonBMP = "x" + new String(Character.toChars(0x10100))
    IdentifierFilter(nonBMP) must equal ("x")
  }

  test("A stray high surrogate doesn't get it confused") {
    IdentifierFilter("x\ud800y") must equal ("x_y")
  }

  test("A stray low surrogate doesn't get it confused") {
    IdentifierFilter("x\udc00y") must equal ("x_y")
  }

  test("Applying an identifier filter is idempotent") {
    forAll { xs: List[String] =>
      IdentifierFilter(IdentifierFilter(xs)) must equal (IdentifierFilter(xs))
    }
  }
}
