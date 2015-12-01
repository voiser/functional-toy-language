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
class CodegenTests extends FunSuite {
  
  def intermediate(code: String) = {
    val filename = "test"
    val modulename = "test"
    val code2 = "module " + modulename + "\n" + code
    val unit = Main.process(filename, code2)
    val module = Intermediate.codegen(unit)
    println(module)
    val bytes = Codegen.codegen(module)
    val ret = Main.execute(unit.module.name, bytes)
  }
  
  /*
  test("Intermediate") { // manual test
//      g :: Int -> Int
    val code = """
      def f = { x, y -> 1 }
      f(4, 9)
      """
    intermediate(code)
  }
  */
}
