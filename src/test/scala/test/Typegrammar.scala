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
    Typer3.getType(Main.rootEnv, module.main, module)
    Main.show(module.main, code2)
  }
  
  def tree(code: String) = {
    val lexer = new TypegrammarLexer(new ANTLRInputStream(code))
    val parser = new TypegrammarParser(new CommonTokenStream(lexer))
    val cst = parser.ty()
    val gty = new TypeVisitor().visitTy(cst)
    val ty = Typegrammar.toType(gty)
    ty.repr
  }

  test("Parse complex") {
    val code = "(Int -> List[List[Float]]) -> Int"
    val expected = "((Int -> List[List[Float]]) -> Int)"
    assert (tree(code) == expected)
  }
  
  test("Parse with vars") {
    val code = "a,b -> List[a]"
    val expected = "(a,b -> List[a])"
    assert (tree(code) == expected)
  }
  
  test("Parse with surrounding parens") {
    val code = "(Int -> Int)"
    val expected = "(Int -> Int)"
    assert (tree(code) == expected)
  }

  test("Parse simple type") {
    val code = "Int"
    val expected = "Int"
    assert (tree(code) == expected)
  }
  
  test("Parse with restrictions") {
    val code = "a +Num+Eq, b +Eq -> c+Eq"
    val expected = "(a+Num+Eq,b+Eq -> c+Eq)"
    assert (tree(code) == expected)
  }
}
