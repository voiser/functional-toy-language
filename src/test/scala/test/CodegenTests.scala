package test

import org.scalatest.FunSuite
import ast2._
import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import java.lang.reflect.InvocationTargetException
import intermediate.Intermediate
import org.xml.sax.helpers.NewInstance

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
  
  def ty(code: String) = {
    val lexer = new TypegrammarLexer(new ANTLRInputStream(code))
    val parser = new TypegrammarParser(new CommonTokenStream(lexer))
    val cst = parser.ty()
    val gty = new TypeVisitor().visitTy(cst)
    val ty = Typegrammar.toType(gty)
    ty
  }

  /*
  test("Intermediate") { // manual test
    val code = """
      factorial = { x -> if x == 2 then x else x * factorial(x - 1) }
      factorial(4)
      """
    println(intermediate(code))
  }
  */
}
