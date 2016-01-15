package test

import java.lang.reflect.InvocationTargetException

import ast2._
import intermediate.Intermediate
import org.scalatest.FunSuite

/**
 * @author david
 */
class ClassesTests extends FunSuite {
  
  def run(code: String) = {
    val filename = "test"
    val modulename = "test"
    val code2 = "module " + modulename + "\n" + code
    try {
      val unit = Main.process(filename, code2)
      val module = Intermediate.codegen(unit)
      val bytes = Codegen.codegen(module)
      Main.execute(unit.module.name, bytes)
    }
    catch {
      case e: TypeException =>
        Main.showException(e, code2)
        throw e
    }
  }

  test("Basic classes") {
    val code = """
      class Person(name Str, age Int)
      nameof (x Person) = x.name
      me = Person("David", 34)
      nameof(me)
      """
    val ret = run(code)
    assert("David" == ret.toString())
  }

  test("Class as Eq") {
    val code = """
      class Box(content a+Eq) is Eq {
        eq(a this, b this) = a.content == b.content
      }
      [Box(9) == Box(1), Box("lala") == Box("lele"), Box(false) == Box(true), Box(9) == Box(9), Box("lala") == Box("lala"), Box(false) == Box(false)]
      """
    val ret = run(code)
    assert("[false, false, false, true, true, true]" == ret.toString())
  }
}

