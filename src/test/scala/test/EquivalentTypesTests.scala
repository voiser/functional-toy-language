package test

import ast2.Typer3.Subs
import ast2._
import intermediate.Intermediate
import org.antlr.v4.runtime.{ANTLRInputStream, CommonTokenStream}
import org.scalatest.FunSuite

/**
 * @author david
 */
class EquivalentTypesTests extends FunSuite {
  
  def equivalent(t1: Ty, t2: Ty) : Boolean = {
    val v1 = Typer3.tyvars(t1)
    val v2 = Typer3.tyvars(t2)
    if (v1.length != v2.length) false
    else {
      try {
        Typer3.unify(t1, t2, Typer3.emptySubst, null)(null, List())
        true
      }
      catch {
        case x : TypeException => false
      }
    }
  }

  def equivalent(t1: String, t2: String) : Boolean = equivalent(Main.parseType(t1), Main.parseType(t2))

  def unifies(t1: String, t2: String) : Boolean = unifies(Main.parseType(t1), Main.parseType(t2))

  def unifies(t1: Ty, t2: Ty) : Boolean = {
    try {
      val s = Typer3.unify(t1, t2, Typer3.emptySubst, null)(new TyvarGenerator("a"), List())
      s(t1).repr == t2.repr
    } catch {
      case x : TypeException => false
    }
  }

  test("Unify a+Set[y] to x+Set[b]") {
    assert(unifies("a+Set[y]", "x+Set[b]"))
  }

  test("Unify a+Set[y]+Eq to x+Set[b]") {
    assert(!unifies("a+Set[y]+Eq", "x+Set[b]"))
  }

  test("Unify a+Eq to x+Set[b]") {
    assert(!unifies("a+Eq", "x+Set[b]"))
  }

  test("Unify a to x+Set[b]") {
    assert(unifies("a", "x+Set[b]"))
  }

  test("Int vs Int") {
    assert(equivalent("Int", "Int"))
  }

  test("Int vs Float") {
    assert(!equivalent("Int", "Float"))
  }

  test("List[Int] vs List[Int]") {
    assert(equivalent("List[Int]", "List[Int]"))
  }

  test("List[Int] vs List[Float]") {
    assert(!equivalent("List[Int]", "List[Float]"))
  }

  test("a vs b") {
    assert(equivalent("a", "b"))
  }

  test("a vs List") {
    assert(!equivalent("a", "Int"))
  }

  test("List[a] vs List[Int]") {
    assert(!equivalent("List[a]", "List[Int]"))
  }

  test("List[a] vs List[b]") {
    assert(equivalent("List[a]", "List[b]"))
  }

  test("Int->Int vs Int->Int") {
    assert(equivalent("Int->Int", "Int->Int"))
  }

  test("a->Int vs n->Int") {
    assert(equivalent("a->Int", "n->Int"))
  }

  test("a->x vs n->Int") {
    assert(!equivalent("a->x", "n->Int"))
  }

  test("a+Set[Int] vs b+Set[Int]") {
    assert(equivalent("a+Set[Int]", "b+Set[Int]"))
  }

  test("a+Set[b] vs b+Set[Int]") {
    assert(!equivalent("a+Set[b]", "b+Set[Int]"))
  }

  test("a+Set[b]+Eq vs b+Set[c]") {
    assert(!equivalent("a+Set[b]+Eq", "b+Set[c]"))
  }

  test("a+Set[b] vs b+Set[c]") {
    assert(equivalent("a+Set[b]", "b+Set[c]"))
  }

  test("a+Set[b]+Abc[x] vs b+Abc[y]+Set[c]") {
    assert(equivalent("a+Set[b]+Abc[x]", "b+Abc[y]+Set[c]"))
  }

  test("a+Set[b] vs b+Abc[y]") {
    assert(!equivalent("a+Set[b]", "b+Abc[y]"))
  }

  test("a+Set[Int] vs b+Set[y]") {
    assert(!equivalent("a+Set[Int]", "b+Set[y]"))
  }
}
