package test

import org.junit.Test
import org.junit.Assert._
import ast2._
import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream

/**
 * @author david
 */
class BasicTests {
  
  def run(code: String) = {
    val filename = "test"
    val modulename = "test"
    val unit = Main.process(filename, "module " + modulename + "\n" + code)
    val bytes = Codegen2.codegen(unit)
    val ret = Main.execute(unit.module.name, bytes)
    ret
  }

  @Test
  def test_23464 = {
    val code = """
      def a = 1
      def b = 2
      add(a, b)
      """
    val ret = run(code)
    assertEquals("3", ret.toString())
  }
  
  @Test
  def test_91262 = {
    val code = """
      cons(1, list(2))
      """
    val ret = run(code)
    assertEquals("[1 2]", ret.toString())
  }
  
  @Test
  def test_23781 = {
    val code = """
      def f = { x Int -> if eq(x, 2) then x else times(x, f(sub(x, 1))) }
      f(4)
      """
    val ret = run(code)
    assertEquals("24", ret.toString())
  }
}