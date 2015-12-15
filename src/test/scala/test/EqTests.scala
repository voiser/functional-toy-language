package test

import org.scalatest.FunSuite
import ast2._
import intermediate.Intermediate

/**
 * @author david
 */
class EqTests extends FunSuite {
  
  def run(code: String) = {
    val filename = "test"
    val modulename = "test"
    val unit = Main.process(filename, "module " + modulename + "\n" + code)
    val module = Intermediate.codegen(unit)
    val bytes = Codegen.codegen(module)
    val ret = Main.execute(unit.module.name, bytes)
    ret
  }

  test("Int as Eq") {
    val code = """
      cons(eq(1, 2), cons(eq(1, 1), nil))
      """
    val ret = run(code)
    assert ("[False True]" == ret.toString())
  }
  
  test("Float as Eq") {
    val code = """
      cons(eq(1.0, 2.0), cons(eq(1.0, 1.0), nil))
      """
    val ret = run(code)
    assert ("[False True]" == ret.toString())
  }
  
  test("String as Eq") {
    val code = """
      cons(eq("a", "b"), cons(eq("c", "c"), nil))
      """
    val ret = run(code)
    assert ("[False True]" == ret.toString())

  }

  test("Bool as Eq") {
    val code = """
      cons(eq(true, false), cons(eq(true, true), nil))
      """
    val ret = run(code)
    assert ("[False True]" == ret.toString())
  }

}