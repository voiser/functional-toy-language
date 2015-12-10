package ast2

import org.antlr.v4.runtime.ParserRuleContext

/**
 * Used for typed parameters in functions
 * 
 * { a -> ... }     'a' is NFnArg("a", KlassVar())
 * { a Int -> ... } 'a' is NFnArg("a", KlassConst("Int"))  
 */
abstract class KlassRef
case class KlassConst(name: String) extends KlassRef
case class KlassVar(name: String) extends KlassRef


/**
 * Used for representing types in forward declarations
 * 
 * f :: (Int -> Int) -> String
 *                      ^^^^^^   GTycon
 *              ^^^              GTycon
 *       ^^^                     GTycon
 *      ^^^^^^^^^^^^             GYyfn
 *      ^^^^^^^^^^^^^^^^^^^^^^   GTyfn
 */
abstract class GTy
case class GTycon(name: String, params: List[GTy]) extends GTy
case class GTyfn(lefts: List[GTy], right: GTy) extends GTy
case class GTyvar(name: String, restrictions: List[GTy]) extends GTy


/**
 * The syntax tree
 */
abstract class Node {
  var filename: String = null
  var ctx: ParserRuleContext = null
  var ty: Ty = null
  var env: Env = null
  def line = if (ctx == null) 0 else ctx.start.getLine
  def column = if (ctx == null) 0 else ctx.start.getCharPositionInLine
  def charsize = if (ctx == null) 0 else ctx.getText.length() + 1
}
case class NModule(name: String, imports: List[(String, String)], main: NFn) extends Node
case class NBlock(children: List[Node]) extends Node
case class NInt(i: Int) extends Node
case class NFloat(f: Float) extends Node
case class NString(s: String) extends Node
case class NDef(name: String, value: Node) extends Node
case class NRef(name: String) extends Node {
  var isRecursive: Boolean = false
}
case class NDefAnon(name: String, value: Node) extends Node
case class NRefAnon(name: String) extends Node 
case class NFnArg(name: String, klass: KlassRef) extends Node
case class NFn(params: List[NFnArg], value: NBlock) extends Node {
  // def f = {...} ---> name = envx$f, defname = f
  var name: String = null
  var defname: String = null
  def typedArgs = params.collect { 
    case x @ NFnArg(_, KlassConst(_)) => x 
  }
  def hasTypedArgs = typedArgs.length > 0
}
case class NApply(name: String, params: List[Node]) extends Node {
  // add(1, 1) ---> name = add, realname = add$a
  // def f = {...} ; f(x)   ---> name = realname = f
  var realName: String = null
  var isRecursive: Boolean = false
}
case class NIf(cond: Node, exptrue: Node, expfalse: Node) extends Node
case class NForward(name: String, tydef: GTy) extends Node


/**
 * A type restriction. 
 */
abstract class Restriction
{
  def repr: String
  def tyvars: List[Tyvar]
}
case class Isa(ty: Tycon) extends Restriction {
  def repr = "+" + ty.repr
  def tyvars = Typer3.tyvars(ty)
}


/**
 * Represents types
 */
abstract class Ty {
  def repr: String
}
case class Tyfn(in: List[Ty], out: Ty) extends Ty {
  override def repr = {
    val i = in.map{_.repr}.mkString(",") 
    "(" + (if (i == "") "()" else i) + " -> " + out.repr + ")"
  }
}
case class Tycon(name: String, types: List[Ty]) extends Ty {
  override def repr = name + (if (types.size == 0) "" else types.map { _.repr}.mkString("[", ",", "]"))
} 
case class Tyvar(name: String, restrictions: List[Restriction]) extends Ty {
  override def repr = name + restrictions.map{_.repr}.mkString("")
}
object Tycon {
  def apply(name: String) : Tycon = Tycon(name, List())
}


/**
 * Exceptions and error traces
 */
case class TraceElement(message: String, node: Node)
class ParseException(m: String) extends Exception(m)
class TypeException(m: String, val node: Node, val trace: List[TraceElement]) extends Exception(m)
class CodegenException(m: String) extends Exception(m)


/**
 * Generates fresh tyvars
 */
class TyvarGenerator(prefix: String) {
  var n = 0
  
  def get() = {
    n = n + 1
    Tyvar(prefix + n, List())
  }
  
  def get(t: Tyvar) = {
    n = n + 1
    Tyvar(prefix + n, t.restrictions)
  }
}


/**
 * See http://www.scala-lang.org/docu/files/ScalaByExample.pdf 
 * 
 * a = Tyvar("a")
 * b = Tyvar("b")
 * t = Tycon("Map", List(a, b), ...)     t is Map[a, b]
 * s = TypeScheme(List(a, b), t)         s is a wrapper of t, knowing that a and b are variables
 * 
 * Calling s.newInstance(...) gives a new type like 't' with fresh variables
 * 
 * s.newInstance(...) -> Map[t1, t2]
 * s.newInstance(...) -> Map[t3, t4]
 * ...
 * 
 * str = Tycon("str", ...)
 * int = Tycon("int", ...)
 * 
 * t.applyTo(List(str, int)) -> Map[str, int]
 */
case class TypeScheme(tyvars: List[Tyvar], tpe: Ty) {
  def newInstance(gen: TyvarGenerator) : Ty = {
    val s = (Typer3.emptySubst /: tyvars) { (s, tv) =>
      val x = gen.get(tv)
      s.extend(tv, Tyvar(x.name, tv.restrictions))
    }
    s(tpe)
  }
  
  def applyTo(tys: List[Ty]) = {
    (Typer3.emptySubst /: (tyvars zip tys)) ((s, tv) => s.extend(tv._1, tv._2)) (tpe)
  }
}


/**
 * Utility classes for code analysis
 */
case class Function(function: NFn, captures: List[NRef]) {
  override def toString() = "Function(" + function.defname + ", " + captures + ")"
}

case class Call(function: NFn, calls: List[String])

case class Extern(function: NFn, symbols: List[String]) {
  override def toString() = "Extern(" + function.defname + ", " + symbols + ")"
}


/**
 * Contains variables, references, types, etc. that are stored when traversing the AST
 * There is a root environment, which contains the basic types and functions. 
 * Every module and function introduces a new environment, with the previous environment
 * as a parent. Thus, a tree of environments are generated.
 */
class Env(var id: String, val parent: Env, val introducedBy: Node) {

  if (introducedBy != null) introducedBy.env = this
  
  val names = scala.collection.mutable.Map[String, TypeScheme]()
  val fullnames = scala.collection.mutable.Map[String, String]()
  val types = scala.collection.mutable.Map[String, Ty]()
  val restrictions = scala.collection.mutable.Map[String, Restriction]()
  val forwards = scala.collection.mutable.Map[String, NForward]()
  val isas = scala.collection.mutable.Map[String, (Tycon, List[Ty])]()
  
  def allFull = fullnames ++ (if (parent != null) parent.fullnames else Map())
  def allNames = names ++ (if (parent != null) parent.names else Map())
 
  def putIsa(child: Tycon, parents: List[Ty]) = isas.put(child.name, (child, parents))
  
  def getIsa(child: String) : Option[(Tycon, List[Ty])] = isas.get(child) match {
    case x : Some[(Tycon, List[Ty])] => x
    case None =>
      if (parent != null) parent.getIsa(child)
      else None
  }
  
  def putForward(name: String, node: NForward) = forwards.put(name, node)

  def getForward(name: String) : Option[NForward] = forwards.get(name) match {
    case x : Some[NForward] => x
    case None =>
      if (parent != null) parent.getForward(name)
      else None
  }
  
  def putRestriction(name: String, res: Restriction) = restrictions.put(name, res)

  def getRestriction(name: String) : Option[Restriction] = restrictions.get(name) match {
    case x : Some[Restriction] => x
    case None =>
      if (parent != null) parent.getRestriction(name)
      else None
  }
  
  def putType(name: String, ty: Ty) = types.put(name, ty)

  def getType(name: String) : Option[Ty] = types.get(name) match {
    case x:Some[Ty] => x
    case None =>
      if (parent != null) parent.getType(name)
      else None
  }

  def put(name: String, fullname: String, ty: TypeScheme) {
    names.put(name, ty)
    fullnames.put(name, fullname)
  }
  
  def put(name: String, ty: Ty) {
    names.put(name, TypeScheme(Typer3.tyvars(ty), ty))
    fullnames.put(name, null)
  }

  def get2(name: String) : List[(String, TypeScheme)] = {
    val mine = names.filter(p => p._1 == name || p._1.startsWith(name + "$$")).toList
    val inherited = if (parent != null) parent.get2(name) else List()
    mine ++ inherited
  }
  
  def get(name: String) : Option[TypeScheme] = names.get(name) match {
    case x: Some[TypeScheme] => x
    case None => 
      if (parent != null) parent.get(name) 
      else None
  }
  
  def getFull(name: String) : String = fullnames.get(name) match {
    case Some(x) => x
    case None =>
      if (parent != null) parent.getFull(name)
      else null
  }
  
  def locate(name: String) : Env = names.get(name) match {
    case Some(x) => this
    case None =>
      if (parent != null) parent.locate(name)
      else null
  }
  
  def isChildOf(e: Env) : Boolean = 
    if (parent == null) false
    else {
      if (parent == e) true
      else parent.isChildOf(e)
    }
  
  override def toString = repr + 
    //names.map {x => (x._1, x._2.tpe.repr)}.mkString(",") +
    names.map { x => x._1 }.mkString("(", ",", ")") +
    (if (parent != null) (" parent: " + parent.repr) else "")
    
  def repr = {
      id
  }
}

object Env {
  
  var count = 0
  
  def apply() = { 
      count = count + 1
      new Env("env" + count, null, null) 
  }
  
  def apply(env: Env, introducedBy: Node) = {
    count = count + 1
    new Env("env" + count, env, introducedBy) 
  }
}
