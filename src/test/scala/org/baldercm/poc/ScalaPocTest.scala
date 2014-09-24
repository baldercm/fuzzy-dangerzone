package org.baldercm.poc

import org.junit.runner.RunWith
import org.scalatest.Assertions
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ScalaPocTest extends FlatSpec {

  behavior of "ScalaPoc.doSomething()"

  it should "return expected text" in {
    val text = ScalaPoc.doSomething()

    assert("Hello from Scala!" == text)
  }

}