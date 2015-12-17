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
class DynamicDispatchTests extends FunSuite {
  
  def run(code: String) = {
    val filename = "test"
    val modulename = "test"
    val unit = Main.process(filename, "module " + modulename + "\n" + code)
    val module = Intermediate.codegen(unit)
    val bytes = Codegen.codegen(module)
    val ret = Main.execute(unit.module.name, bytes)
    ret
  }
  
  test ("Basic dynamic dispatch") { 
    val code = """
      mysize = { x => size(x) }
      a = [1, 2]
      b = ["a":1]
      [ mysize(a), mysize(b) ]
      """
    val ret = run(code)
    assert ("[2 1]" == ret.toString())
  }

  test ("Static dispatch with the typechecker help") { 
    val code = """
      a = [1, 2]
      b = ["a":1]
      [ size(a), size(b) ]
      """
    val ret = run(code)
    assert ("[2 1]" == ret.toString())
  }

  test ("Static dispatch with the typechecker help 2") { 
    val code = """
      mysize = { x List[Int] => size(x) }
      a = [1, 2]
      mysize(a)
      """
    val ret = run(code)
    assert ("2" == ret.toString())
  }
}
