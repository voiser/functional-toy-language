package test

import org.scalatest.FunSuite
import ast2._
import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream

/**
 * @author david
 */
class Typegrammar extends FunSuite {
  
  def ty(code: String) = {
    val filename = "test"
    val modulename = "test"
    val code2 = "module " + modulename + "\n" + code
    val lexer = new GrammarLexer(new ANTLRInputStream(code2))
    val parser = new GrammarParser(new CommonTokenStream(lexer))
    val cst = parser.file()
    val module = new FirstVisitor(filename).visitFile(cst)
    module.main.name = "main"
    Typer3.getType(Main.rootEnv, module.main)
    Main.show(module.main, code2)
  }
  
  def tree(code: String) = {
    val lexer = new TypegrammarLexer(new ANTLRInputStream(code))
    val parser = new TypegrammarParser(new CommonTokenStream(lexer))
    val cst = parser.ty()
    val gty = new TypeVisitor().visitTy(cst)
    val ty = Typegrammar.toType(gty, Main.rootEnv)
    ty.repr
  }

  test("Parse 1") {
    val code = "(Int -> List[List[Float]]) -> Int"
    val expected = "((Int -> List[List[Float]]) -> Int)"
    assert (tree(code) == expected)
  }
  
  test("Parse with vars") {
    val code = "a -> List[a]"
    val expected = "(a -> List[a])"
    assert (tree(code) == expected)
  }
}
