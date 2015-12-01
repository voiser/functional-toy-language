package ast2

abstract class KlassRef
case class KlassConst(name: String) extends KlassRef
case class KlassVar(name: String) extends KlassRef

abstract class GTy
case class GTycon(name: String, params: List[GTy]) extends GTy
case class GTyfn(lefts: List[GTy], right: GTy) extends GTy
case class GTyvar(name: String) extends GTy

abstract class Node {
  var ty: Ty = null
  var position: (String, Int, Int) = null
  var env: Env = null
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
case class NFnArg(name: String, klass: KlassRef) extends Node
case class NFn(params: List[NFnArg], value: NBlock) extends Node {
  // def f = {...} ---> name = envx$f, defname = f
  var name: String = null
  var defname: String = null
  def hasTypedArgs = params.map { x => x.klass }.map { _.isInstanceOf[KlassConst] }.foldLeft(false)((a, b) => a || b)
}
case class NApply(name: String, params: List[Node]) extends Node {
  // add(1, 1) ---> name = add, realname = add$a
  // def f = {...} ; f(x)   ---> name = realname = f
  var realName: String = null
  var isRecursive: Boolean = false
}
case class NIf(cond: Node, exptrue: Node, expfalse: Node) extends Node
case class NForward(name: String, tydef: GTy) extends Node


case class Restriction(name: String, isa: List[Restriction]) {
  def repr: String = "+" + name
  def all : List[String] = name :: isa.flatMap { x => x.all }
}

abstract class Ty {
  def repr: String
}
case class Tyfn(in: List[Ty], out: Ty) extends Ty {
  override def repr = {
    val i = in.map{_.repr}.mkString(",") 
    "(" + (if (i == "") "()" else i) + " -> " + out.repr + ")"
  }
}
case class Tycon(name: String, types: List[Ty], restrictions: List[Restriction]) extends Ty {
  override def repr = name + (if (types.size == 0) "" else types.map { _.repr}.mkString("[", ",", "]"))
} 
case class Tyvar(name: String, restrictions: List[String]) extends Ty {
  override def repr = name + restrictions.map {"+" + _.repr}.mkString("")
}
case class TyAny() extends Ty {
  override def repr = "Any"
}

object Tycon {
  def apply(name: String) : Tycon = Tycon(name, List(), List())
}

class ParseException(m: String) extends Exception(m)
class TypeException(m: String, node: Node, trace: List[String]) extends Exception(m) {
  override def getMessage() = {
    if (node.position == null) println ("This node has no position: " + node)
    m + " - at " + node.position._1 + " Line " + node.position._2 + trace.mkString("\n", "\n", "\n")
  }
}
class CodegenException(m: String) extends Exception(m)


case class TypeScheme(tyvars: List[Tyvar], tpe: Ty) {
  def newInstance(gen: TyvarGenerator) : Ty = {
    (Typer3.emptySubst /: tyvars) ((s, tv) => s.extend(tv, gen.get())) (tpe)
  }
  
  def applyTo(tys: List[Ty]) = {
    (Typer3.emptySubst /: (tyvars zip tys)) ((s, tv) => s.extend(tv._1, tv._2)) (tpe)
  }
}


case class Function(function: NFn, captures: List[NRef]) {
  override def toString() = "Function(" + function.defname + ", " + captures + ")"
}

case class Call(function: NFn, calls: List[String])

case class Extern(function: NFn, symbols: List[String]) {
  override def toString() = "Extern(" + function.defname + ", " + symbols + ")"
}


class Env(var id: String, val parent: Env, introducedBy: Node) {

  if (introducedBy != null) introducedBy.env = this
  
  val names = scala.collection.mutable.Map[String, TypeScheme]()
  val fullnames = scala.collection.mutable.Map[String, String]()
  val types = scala.collection.mutable.Map[String, Ty]()
  val restrictions = scala.collection.mutable.Map[String, Restriction]()
  
  def allFull = fullnames ++ (if (parent != null) parent.fullnames else Map())
  def allNames = names ++ (if (parent != null) parent.names else Map())
 
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
