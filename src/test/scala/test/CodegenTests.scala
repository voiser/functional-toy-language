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
    
    try {
      val unit = Main.process(filename, code2)
      val module = Intermediate.codegen(unit)
      println(module)
      val bytes = Codegen.codegen(module)
      Main.execute(unit.module.name, bytes)
    } 
    catch {
      case e: TypeException =>
        Main.showException(e, code2)
    }
  }
  
  /*
  test("Intermediate") { // manual test
    val code = """
      def ident = { y ->
        { x -> y(x) }
      }
      def f = { x -> { y -> add(x, y) } } 
      def z = ident({x -> add(x, 1)})
      z(1)
      def j = f(1)
      j(19)
      """
    println("result = " + intermediate(code)))
  }
  */
}
