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
      a = 1
      
      f = { x ->
        g = { y -> add(y, a)}
        g(x)
      }
      
      g = {
        add(1, 1)
      }
      
      f(19)
      """
    println("result = " + intermediate(code))
  }
  */
}
