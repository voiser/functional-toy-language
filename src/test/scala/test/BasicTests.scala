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
class BasicTests extends FunSuite {
  
  def run(code: String) = {
    val filename = "test"
    val modulename = "test"
    val unit = Main.process(filename, "module " + modulename + "\n" + code)
    val module = Intermediate.codegen(unit)
    val bytes = Codegen.codegen(module)
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
      g :: Int -> Int
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
      g :: Int -> Int
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
      g :: Int -> Int
      def f = { x -> if x == 2 then x else x * g(x - 1) }
      def g = { x -> f(x) }
      f(4)
      """
    val ret = run(code)
    assert ("24" == ret.toString())
  }

  test("Generics and forward definitions") {
    val code = """
      g :: a -> List[a]
      def f = { x -> g(x) }
      def g = { x -> list(x) }
      f(4)
      """
    val ret = run(code)
    assert ("[4]" == ret.toString())
  }
  
  test("Syntactic sugar for lists") {
    val code = """
      def xs = [1, 2, 3]
      """
    val ret = run(code)
    assert ("[1 2 3]" == ret.toString())
  }
  
  test("Dict and extend") {
    val code = """
      def m = dict("Name", "John")
      extend("Surname", "Surjohn", m)
      """
    val ret = run(code)
    assert("[Name:John, Surname:Surjohn]" == ret.toString())
  }
  
  test("Syntactic sugar for maps") {
    val code = """
      def m = ["a":1, "b":2]
      m
      """
    val ret = run(code)
    assert("[b:2, a:1]" == ret.toString())
  }
  
  test("Forwards, lists and maps") {
    val code = """
      a :: List[Int]
      b :: Dict[Str, Int]
      def a = [1, 2, 3]
      def b = ["a": 1, "b": 2]
      """
    run(code) // assert no error
  }
  
  test("isa") {
    assert (Typer3.isa(Typer3.eqType, Typer3.intType))
  }
}