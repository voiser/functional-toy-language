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
        // Main.showException(e, code2)
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

  test("Incomplete class") {
    val code = """
      class Box(content a) is Eq
      """
    try {
      run(code)
      fail()
    }
    catch {
      case e : TypeException =>
        assert(e.getMessage.contains("Class Box is declared to be Eq but does not define eq"))
    }
  }

  test("Incomplete class 2") {
    val code = """
      class Box(content a) is Eq, Set {
        eq (a this, b this) = true
      }
      """
    try {
      run(code)
      fail()
    }
    catch {
      case e : TypeException =>
        assert(e.getMessage.contains("Class Box is declared to be Set but does not define size"))
    }
  }

  test("Incomplete class 3") {
    val code = """
      class Box(content a) is Eq {
        eq (a this, b Int) = false
      }
      """
    try {
      run(code)
      fail()
    }
    catch {
      case e : TypeException =>
        assert(e.getMessage.contains("Incompatible types"))
    }
  }

  test("Syntactic sugar in variables and tyvars") {
    val code = """
      class Box(x) is Set[x] {
        size(this) = 1
      }
      size(Box(9))
      """
    val ret = run(code)
    assert("1" == ret.toString())
  }

  test("Captures in classes") {
    val code = """
      a = 1
      class Box(x) is Set[x] {
        b = 2
        size(this) = a + b
      }
      size(Box(9))
      """
    val ret = run(code)
    assert("3" == ret.toString())
  }

  test("Dynamic dispatch") {
    val code = """
      a = 1
      class Box(x) is Set[x] {
        b = 2
        size(this) = a + b
      }

      class Xob(x) is Set[x] {
        b = 3
        size(this) = a + b
      }

      [ size(Box(9)), size(Xob(10)) ]
      """
    val ret = run(code)
    assert("[3, 4]" == ret.toString())
  }

  test("Matching 1") {
    val code = """
      class Some(x)
      class Thing(y)

      f(x) = if x is Some(Thing(z)) then z else -1

      [ f(Some(Thing(9))), f(Some(Some(1))) ]
    """
    val ret = run(code)
    assert("[9, -1]" == ret.toString())
  }

  test("Matching 2") {
    val code = """
      class Some(x)
      class Thing(y)

      f(x) = if x is Some(j Thing(z)) then j else Thing(-1)

      [ f(Some(Thing(9))), f(Some(Some(1))) ]
      """
    val ret = run(code)
    assert("[Thing(9), Thing(-1)]" == ret.toString())
  }

  test("Interfaces 1") {
    val code = """

    interface MySet[x] {
      mysize : this -> Int
    }

    class MyList(x) is MySet[x] {
      mysize (this) = 1
    }

    a = MyList(3)

    mysize(a)
    """
    val ret = run(code)
    assert("1" == ret.toString())
  }

  test("Interfaces 2") {
    val code = """

    interface MySet[x] {
      mysize : this -> Int
    }

    class MyList(x) is MySet[x] {
      mysize (this) = this.x
    }
    """
    try {
      run(code)
      fail()
    }
    catch {
      case e : TypeException =>
        assert(e.getMessage.contains("forces MyList to reduce generality"))
    }
  }
}
