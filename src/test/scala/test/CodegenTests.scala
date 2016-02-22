package test

import org.scalatest.FunSuite
import ast2._
import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import intermediate.Intermediate

/**
 * @author david
 */
class CodegenTests extends FunSuite {
  
  def intermediate(filename: String, code: String, runtime: Runtime) = {

    try {
      val unit = Main.process(filename, code, runtime)
      val module = Intermediate.codegen(unit)
      println(module)
      val bytes = Codegen.codegen(module)
      Main.execute(unit.module.name, bytes, runtime)
    } 
    catch {
      case e: TypeException =>
        Main.showException(e, code)
        throw e
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
    val exp2 = "^" + exp.replace("(", "\\(").replace(")", "\\)") + "$"
    val r = exp.r.findFirstIn(in.toString)
    r match {
      case Some(_) => true
      case None => false
    }
  }

  /*
  test("Intermediate") { // manual test
    val runtime = new Runtime()
    val code1 = """
      module test

      class Some(x)
      class Thing(x Int)

      f(x) = if x is Some(Thing(z)) then z else 0

      f(Some(Thing(1)))

      """
    val res1 = intermediate("test", code1, runtime)
    println("matches = " + res1)
  }
  */
}
