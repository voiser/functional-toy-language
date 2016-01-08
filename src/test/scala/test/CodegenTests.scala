package test

import org.scalatest.FunSuite
import ast2._
import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import java.lang.reflect.InvocationTargetException
import intermediate.Intermediate
import org.xml.sax.helpers.NewInstance
import runtime.Java

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

  def matches(in: Any, exp: String) = {
    val exp2 = "^" + (exp.replace("(", "\\(").replace(")", "\\)")) + "$"
    val r = exp.r.findFirstIn(in.toString)
    r match {
      case Some(_) => true
      case None => false
    }
  }

  /*
  test("Intermediate") { // manual test
    val code = """
      a(b, c) = { x => x + b + c }
      z = a(1, 2)
      z(1)
      """
    val res = intermediate(code)
    println("matches = " + res)
  }
  */
}
