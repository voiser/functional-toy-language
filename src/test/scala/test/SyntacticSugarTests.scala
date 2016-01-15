package test

import org.scalatest.FunSuite
import ast2._
import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import java.lang.reflect.InvocationTargetException
import intermediate.Intermediate

/**
 * @author david
 */
class SyntacticSugarTests extends FunSuite {
  
  def run(code: String) = {
    val filename = "test"
    val modulename = "test"
    val unit = Main.process(filename, "module " + modulename + "\n" + code)
    val module = Intermediate.codegen(unit)
    val bytes = Codegen.codegen(module)
    val ret = Main.execute(unit.module.name, bytes)
    ret
  }

  test("Lists") {
    val code = """
      xs = [1, 2, 3]
      """
    val ret = run(code)
    assert ("[1, 2, 3]" == ret.toString())
  }

  test("Maps") {
    val code = """
      m = ["a":1, "b":2]
      """
    val ret = run(code)
    assert("[b:2, a:1]" == ret.toString())
  }

  test("Functions 1") {
    val code = """
      f() = 12
      f()
      """
    val ret = run(code)
    assert ("12" == ret.toString())
  }

  test("Functions 2") {
    val code = """
      f(x) = x
      f(31)
      """
    val ret = run(code)
    assert ("31" == ret.toString())
  }

  test("Functions 3") {
    val code = """
      f(x) = { x }
      f(21)
      """
    val ret = run(code)
    assert ("21" == ret.toString())
  }

  test("Functions 4") {
    val code = """
      f(x) = if x == 2 then x else x * f(x - 1)
      f(4)
      """
    val ret = run(code)
    assert ("24" == ret.toString())
  }

  test("Functions 5") {
    val code = """
      f() = { x => x - 1 }
      g = f()
      g(4)
      """
    val ret = run(code)
    assert ("3" == ret.toString())
  }

  test("Functions 6") {
    val code = """
      f(y) = { x => x - y }
      g = f(2)
      g(4)
      """
    val ret = run(code)
    assert ("2" == ret.toString())
  }

  test("Object style calls") {
    val code = """
      [[1, 2, 3].size(), size([1, 2, 3])]
      """
    val ret = run(code)
    assert ("[3, 3]" == ret.toString())
  }

  test("Object fields") {
    val code = """
      class Age(years Int)
      class Person(name Str, age Age)
      Person("David", Age(34)).age.years
      """
    val ret = run(code)
    assert ("34" == ret.toString())
  }

}
