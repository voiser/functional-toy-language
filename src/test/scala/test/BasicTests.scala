package test

import org.scalatest.FunSuite
import ast2._
import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import java.lang.reflect.InvocationTargetException

/**
 * @author david
 */
class BasicTests extends FunSuite {
  
  def run(code: String) = {
    val filename = "test"
    val modulename = "test"
    val unit = Main.process(filename, "module " + modulename + "\n" + code)
    val bytes = Codegen2.codegen(unit)
    val ret = Main.execute(unit.module.name, bytes)
    ret
  }
  
  test ("1 + 2 = 3") { 
    val code = """
      def a = 1
      def b = 2
      add(a, b)
      """
    val ret = run(code)
    assert ("3" == ret.toString())
  }

  test ("Create a list") {
    val code = """
      cons(1, list(2))
      """
    val ret = run(code)
    assert ("[1 2]" == ret.toString())
  }
  
  test("Forward definition") {
    val code = """
      g : Int -> Int
      def g = { x -> g(x) }
      g(1)
    """
    try {
      run(code)
    }
    catch {
      case e: InvocationTargetException =>
        assert (e.getCause.isInstanceOf[StackOverflowError])
    }
  }
  
  test("Recursive call") {
    val code = """
      def f = { x -> if eq(x, 2) then x else times(x, f(sub(x, 1))) }
      f(4)
      """
    val ret = run(code)
    assert ("24" == ret.toString())
  }

  test("Mutual recursion") {
    val code = """
      g : Int -> Int
      def f = { x -> if eq(x, 2) then x else times(x, g(sub(x, 1))) }
      def g = { x -> f(x) }
      f(4)
      """
    val ret = run(code)
    assert ("24" == ret.toString())
  }
  
  test("binary op") {
    val code = """
      def a = 1 + (2 * 3) - (4 / 2)
      a
      """
    val ret = run(code)
    assert ("5" == ret.toString())
  }
  
  test("Mutual recursion with binary operations") {
    val code = """
      g : Int -> Int
      def f = { x -> if x == 2 then x else x * g(x - 1) }
      def g = { x -> f(x) }
      f(4)
      """
    val ret = run(code)
    assert ("24" == ret.toString())
  }

  test("isa") {
    assert (Typer3.isa(Typer3.eqType, Typer3.intType))
  }
}