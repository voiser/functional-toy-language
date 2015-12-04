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
      a = 1
      b = 2
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
      g = { x -> g(x) }
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
      f = { x -> if eq(x, 2) then x else times(x, f(sub(x, 1))) }
      f(4)
      """
    val ret = run(code)
    assert ("24" == ret.toString())
  }

  test("Mutual recursion") {
    val code = """
      g : Int -> Int
      f = { x -> if eq(x, 2) then x else times(x, g(sub(x, 1))) }
      g = { x -> f(x) }
      f(4)
      """
    val ret = run(code)
    assert ("24" == ret.toString())
  }
  
  test("binary op") {
    val code = """
      a = 1 + (2 * 3) - (4 / 2)
      a
      """
    val ret = run(code)
    assert ("5" == ret.toString())
  }
  
  test("Mutual recursion with binary operations") {
    val code = """
      g : Int -> Int
      f = { x -> if x == 2 then x else x * g(x - 1) }
      g = { x -> f(x) }
      f(4)
      """
    val ret = run(code)
    assert ("24" == ret.toString())
  }

  test("Generics and forward definitions") {
    val code = """
      g : a -> List[a]
      f = { x -> g(x) }
      g = { x -> list(x) }
      f(4)
      """
    val ret = run(code)
    assert ("[4]" == ret.toString())
  }
  
  test("Syntactic sugar for lists") {
    val code = """
      xs = [1, 2, 3]
      """
    val ret = run(code)
    assert ("[1 2 3]" == ret.toString())
  }
  
  test("Dict and extend") {
    val code = """
      m = dict("Name", "John")
      extend("Surname", "Surjohn", m)
      """
    val ret = run(code)
    assert("[Name:John, Surname:Surjohn]" == ret.toString())
  }
  
  test("Syntactic sugar for maps") {
    val code = """
      m = ["a":1, "b":2]
      m
      """
    val ret = run(code)
    assert("[b:2, a:1]" == ret.toString())
  }
  
  test("Forwards, lists and maps") {
    val code = """
      a : List[Int]
      b : Dict[Str, Int]
      a = [1, 2, 3]
      b = ["a": 1, "b": 2]
      """
    run(code) // assert no error
  }
  
  test("Typed params") {
    val code = """
      f = { a Int, b Int -> a + b }
      f(1, 2)
      """
    val ret = run(code)
    assert("3" == ret.toString())
  }

  test("Typed params and forwards") {
    val code = """
      f : Int, Int -> Int
      f = { a Int, b Int -> a + b }
      f(1, 2)
      """
    try {
      run(code)
    }
    catch {
      case e: TypeException =>
        assert (e.getMessage().contains("can't have typed arguments"))
    }
  }
  
  test("Anonymous functions, captures") {
    val code = """
      addcurried = { x -> { y -> add(x, y) } }
      addtwo = addcurried(2)
      addtwo(19) 
      """
    val ret = run(code)
    assert("21" == ret.toString())
  }
  
  test("Anonymous functions, captures 2") {
    val code = """
      apply = { f -> { x -> f(x) } } 
      equalsToOne = apply({x -> eq(1, x)})
      equalsToOne(2)
      """
    val ret = run(code)
    assert("False" == ret.toString())
  }
}