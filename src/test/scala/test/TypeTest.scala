package test

import org.junit.Test
import org.junit.Assert._
import ast2._
import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream

/**
 * @author david
 */
class TypeTest {
  
  /*
  def parse(line: String, env: Env) = {
    val lexer = new GrammarLexer(new ANTLRInputStream(line))
    val parser = new GrammarParser(new CommonTokenStream(lexer))
    
    val tree = parser.r()
    val expression = tree.expression()
    val root = new FirstVisitor().visitExpression(expression)
    
    val x = Typer3.getType(env, root)
    
    val tyvars = Typer3.tyvars(x)
    
    val s = (Typer3.emptySubst /: tyvars) ((s, v) => s.extend(v, TyAny()))
    
    s(x)
  }
  
  @Test
  def test_13928 = {
    val env = Main.rootEnv
    val ty1 = parse("def f = id ", env)
    assertEquals ("(Any -> Any)", ty1.repr)
    val ty2 = parse("f(1)", env)
    assertEquals("Int", ty2.repr)
  }
  
  @Test
  def test_91278 = {
    val env = Main.rootEnv
    val ty1 = parse("def f = {x -> x} ", env)
    assertEquals ("(Any -> Any)", ty1.repr)
    val ty2 = parse("f(1)", env)
    assertEquals("Int", ty2.repr)
  }

  @Test
  def test_81817 = {
    val env = Main.rootEnv
    val ty1 = parse("do(id, 1) ", env)
    assertEquals("Int", ty1.repr)
  }

  @Test
  def test_23581 = {
    val env = Main.rootEnv
    val ty1 = parse("add(do(id, 1), do(id, 2)) ", env)
    assertEquals("Int", ty1.repr)
  }
  
  @Test
  def test_71871 = {
    val env = Main.rootEnv
    val ty1 = parse("id(id(id(id(id(9)))))", env)
    assertEquals("Int", ty1.repr)
  }

  @Test
  def test_23471 = {
    val env = Main.rootEnv
    val ty1 = parse("def f = {x -> {y -> add(x, y)}}", env)
    assertEquals("(Int -> (Int -> Int))", ty1.repr)
  }
  
  @Test
  def test_87423 = {
    val env = Main.rootEnv
    val ty1 = parse("def f = { x -> if eq(x, 1) then 1 else f(sub(x, 1)) }", env)
    println (ty1.repr)
  }
  * 
  *
  */
}