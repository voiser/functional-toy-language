package ast2

import scala.collection.JavaConverters._
import org.antlr.v4.runtime.ParserRuleContext

import scala.collection.mutable

/**
 * @author david
 */

/**
 * An empty visitor. Override the appropriate functions to visit a kind
 * of nodes and/or control recursion 
 */
class Visitor {
  
  def visitNFn(n: NFn) {
  }
  
  def visitNBlock(n: NBlock) {
  }
  
  def visitNDef(n: NDef) {
  }

  def visitNDefAnonFunc(n: NDefAnon) {
  }
  
  def visitNApply(n: NApply) {
  }
  
  def visitNIf(n: NIf) {
  }
  
  def visitNRef(n: NRef) {
  }
  
  def visitNRefAnon(n: NRefAnon) {
  }
  
  def visitFunctionDefinition(n: NDef, f: NFn) {
  }
  
  def visitAlias(n: NDef, r: NRef) {
  }

  def visitNClass(n: NClass) {
  }

  def visitNInstantiation(n: NInstantiation) {
  }

  def visitNField(n: NField) {
  }

  def visitNMatch(n: NMatch) {
  }

  def visit(n: NFn): Unit = {
    visitNFn(n)
    visit(n.value)
  }
  
  def visit(n: NBlock): Unit = {
    visitNBlock(n)
    n.children.foreach { x => visit(x) }
  }
  
  def visit(n: NDef): Unit = {
    visitNDef(n)
    visit(n.value)
  }

  def visit(n: NDefAnon): Unit = {
    visitNDefAnonFunc(n)
    visit(n.value)
  }
  
  def visit(n: NApply): Unit = {
    visitNApply(n)
    n.params.foreach { x => visit(x) }
  }
  
  def visit(n: NIf): Unit = {
    visitNIf(n)
    visit(n.cond)
    visit(n.exptrue)
    visit(n.expfalse)
  }
  
  def visit(n: NRef): Unit = {
    visitNRef(n)
  }
  
  def visit(n: NRefAnon): Unit = {
    visitNRefAnon(n)
  }
  
  def visit(n: NDef, f: NFn): Unit = {
    visitFunctionDefinition(n, f)
    visit(n)
  }
  
  def visit(n: NDef, r: NRef): Unit = {
    visitAlias(n, r)
    visit(n)
  }

  def visit(n: NClass): Unit = {
    visitNClass(n)
    visit(n.block)
  }

  def visit(n: NInstantiation): Unit = {
    visitNInstantiation(n)
    n.params.foreach { x => visit(x) }
  }

  def visit(n: NField): Unit = {
    visitNField(n)
    visit(n.owner)
  }

  def visit(n: NMatch): Unit = {
    visitNMatch(n)
    visit(n.source)
    visit(n.exptrue)
    visit(n.expfalse)
  }

  def visit(n: Node) : Unit = {
    n match {
      
      case x @ NDef(name, y : NFn) =>
        visit(x, y)
        
      case x @ NDef(name, y : NRef) =>
        visit(x, y)
      
      case x : NFn =>
        visit(x)
        
      case x : NBlock =>
        visit(x)
        
      case x : NDef =>
        visit(x)
      
      case x : NDefAnon =>
        visit(x)
        
      case x : NApply =>
        visit(x)
        
      case x : NIf =>
        visit(x)
        
      case x : NRef =>
        visit(x)
        
      case x : NRefAnon =>
        visit(x)

      case x : NClass =>
        visit(x)

      case x : NInstantiation =>
        visit(x)

      case x : NField =>
        visit(x)

      case x : NMatch =>
        visit(x)

      case _ =>    
    }
  }
}


/**
 * Sets a name to all functions
 */
class FunctionNamerVisitor2(name: String) extends Visitor {
  
  override def visit(n: NDef, x: NFn) {
    val newName = if (name != null) name + "$" + n.name else n.name
    x.name = newName
    x.defname = n.name
    new FunctionNamerVisitor2(newName).visit(n.value)
  }
  
  override def visit(n: NDef) {
    val newName = if (name != null) name + "$" + n.name else n.name
    new FunctionNamerVisitor2(newName).visit(n.value)
  }

  override def visit(n: NClass) {
    val newName = if (name != null) name + "$" + n.name else n.name
    new FunctionNamerVisitor2(newName).visit(n.block)
  }
}


/**
 * Gives a generated name to all anonymous functions
 */
class AnonymousFunctionNamerVisitor(module: NModule) extends Visitor {
  
  val anonFuncs = scala.collection.mutable.ArrayBuffer[NFn]()
  var i = 0
  visit(module.main.value)
  
  override def visit(n: NDef, x: NFn) {
    visit(x.value)
  }
  
  override def visitNFn(n: NFn) {
    val newName = n.env.repr + "$_" + i
    n.name = newName
    n.defname = newName
    anonFuncs += n
  }
}


/**
 * Extracts all functions in a module, listing all references in each function
 */
class FunctionVisitor(module: NModule) extends Visitor {

  val functions = scala.collection.mutable.ArrayBuffer[Function]()
  visit(module.main)

  override def visitNFn(n: NFn) {
    val v = new FetchRefsVisitor(module, n)
    val r = Function(n, v.captures.toList)
    functions += r
    super.visitNFn(n)
  }

  /**
    * Accumulates a reference to each symbols defined in a parent environment.
    * Basically, it locates captured symbols
    */
  class FetchRefsVisitor(module: NModule, root: NFn) extends Visitor {

    val captured = scala.collection.mutable.ArrayBuffer[NodeRef]()  //val intro = scala.collection.mutable.ArrayBuffer[NRef]()

    val myvars = root.value.children.collect {
      case NDef(name, v) => NRef(name)
      case NDefAnon(name, v) => NRefAnon(name)
    }

    visit(root.value)

    def captures = captured.diff(myvars)

    override def visitNRef(n: NRef) {
      val e1 = n.env
      val e2 = n.env.locate(n.name)
      if (e1.isChildOf(e2) && (e2.parent != null) && root.env.isChildOf(e2)) {
        if (root.defname == n.name) {
          n.isRecursive = true
        }
        else {
          captured += n
        }
      }
    }

    override def visitNRefAnon(n: NRefAnon) {
      module.main.value.children.foreach {
        case x @ NDefAnon(n.name, fn : NFn) =>
          val caps = new FetchRefsVisitor(module, fn).captures
          captured ++= caps
        case _ =>
      }
    }

    override def visitNApply(n: NApply) {
      val e1 = n.env
      val e2 = n.env.locate(n.name)
      if (e1.isChildOf(e2) && (e2.parent != null) && root.env.isChildOf(e2)) {
        val r = NRef(n.name)
        r.env = e1
        r.ctx = n.ctx
        r.ty = e1.get(n.name).get.tpe
        if (root.defname == n.name) {
          n.isRecursive = true
        }
        else {
          captured += r
        }
      }
    }
  }
}


/**
 * Extract all function names
 */
class CallExtractor(root: NFn) extends Visitor {
  
  val calls = scala.collection.mutable.Set[String]()
  visit(root)
  
  override def visitNApply(n: NApply) {
    val fullname = n.env.getFull(n.realName)
    if (fullname == null) {
      // it is not an external function  
      calls += n.realName
    }
  }
}


/**
 * Finds the concrete implementation of a call of a polymorphic function.
 * 
 *   a = [1, 2]
 *   b = ["a":1, "b":2]
 * 
 *   size(a) <- This visitor finds that the correct implementation is List.size
 *   size(b) <- This visitor finds that the correct implementation is Dict.size
 * 
 *   mysize = { x List[a] => size(x) }  <- List.size
 *   mysiz2 = { x => size(x) }          <- ??
 *
 * In this case the correct implementation of 'size' must be found dynamically
 * in runtime. This visitor annotates the call node with all the 'size' 
 * candidates (List.size and Dict.size)
 * 
 * How does it work?
 *
 * Suppose:
 * 
 *   size([1, 2])
 * 
 * Facts:
 * 
 * A) 'size' is a+Set[b]->Int. This is the type given by the environment.
 * B) This call is List[Int]->Int. This is the type given by the type checker.
 * C) List.size is defined as List[x]->Int
 * D) Dict.size is defined as Dict[a, b]->Int
 * 
 * The type B is unified independently with the types C and D. It can be only 
 * unified with C, so that the correct implementation is List.size.
 * 
 *   mysize = { x -> size(x) }
 * 
 * A) 'mysize' is a+Set[b]->Int. This is inferred by the type checker.
 * B) 'x' is a+Set[b]. The call to 'size' is a+Set[b]->Int
 * C) List.size is defined as List[x]->Int
 * D) Dict.size is defined as Dict[a, b]->Int
 * 
 * The type B can be unified with both C and D, so it can't be resolved in compile time.
 * This call will be marked as dynamic 
 */
class OverVisitor(root: NFn) extends Visitor {

  val functions = scala.collection.mutable.Set[Over]()
  val pending = scala.collection.mutable.Map[String, (NRef, List[Over])]()
  def allovers = functions.toList.groupBy(_.fullname).map(_._2.head).toList // remove duplicates (Overs with the same full name)
  visit(root.value)
  
  def lookupOverride(myType: Ty, name: String, n: Node, overrides: List[Over]) = {
    overrides.map { over =>
      try {
        Typer3.unify(myType, over.ts.tpe, Typer3.emptySubst, n)(new TyvarGenerator("z"), List())
        over
      }
      catch {
        case e:TypeException => null
      }
    }.filterNot { x => x == null }
  }

  override def visit(n: NFn) = {}

  override def visit(n: NDef, r: NRef): Unit = {
    r.env.root.getOverrides(r.name) match {
      case List(x) => // it's an imported symbol
        functions += x

      case _ =>
        pending.put(n.name, (r, n.env.getOverrides(r.name)))
    }
  }

  override def visitNRef(r: NRef) {
    r.env.getOverrides(r.name) match {
      case List() => // it's a local reference
      case List(x) => 
        functions += x
        r.over = Some(x)
      case _ => throw new TypeException("Too many overrides in " + r, r, List())
    }
  }
  
  override def visitNApply(n: NApply) {
    lookupOverride(n.resolvedType, n.name, n, n.env.getOverrides(n.name)) match {
      case List(o) => 
        functions += o
        n.over = Some(o)
        
      case List() => 
        pending.get(n.name) match {
          case Some((ref, overs)) =>
            lookupOverride(n.resolvedType, ref.name, n, ref.env.getOverrides(ref.name)) match {
              case List(o) =>
                functions += o
                n.over = Some(o)
                
              case List() => // the function references a local function
/*
              case x : List[Over] => // Several options: this means a dynamic dispatch
                x.foreach { functions += _ }
                n.dynamicOver = x
*/
            }
          case None => // the function is local
        }
        
      case x => // Several options: this means a dynamic dispatch 
        //x.foreach { functions += _ }
        n.dynamicOver = x
    }
  }
}


/*
 * Sets all class names
 */
class ClassNamer(module: String, name: String) extends Visitor {

  override def visit(x: NClass) {
    val ns = if (name != null) name else null
    x.env.getClass(x.name) match {
      case None => throw new TypeException("Class " + x.name + " not found. This is a compiler bug", x, List())
      case Some(k) =>
        k.namespace = ns
        k.modulename = module
    }
    val newName = if (name != null) name + "$" + x.name else x.name
    new ClassNamer(module, newName).visit(x.block)
  }

  override def visit(n: NDef) {
    val newName = if (name != null) name + "$" + n.name else n.name
    new ClassNamer(module, newName).visit(n.value)
  }
}


/*
 * Extracts all classes in a module
 */
class ClassExtractor(module: NModule) extends Visitor {

  val found = scala.collection.mutable.MutableList[Klass]()
  visit(module.main.value)

  def classes = found.toList

  override def visitNClass(n: NClass) {
    val cname = n.name
    val klass = n.env.getClass(cname)
    klass match {
      case None => throw new TypeException("This is a compiler bug", n, List())
      case Some(k) => found.+=(k)
    }
  }
}


/**
 * Registers overrides corresponding to methods defined for a particular class
 */
class OverGenerator(module: NModule) extends Visitor {

  visit(module.main.value)

  override def visitNFn(n: NFn): Unit = {
    n.isOverride match {
      case (k, nn, f) =>
        val fullname = module.name + "/" + n.name
        val over = Main.registerOverride(fullname, nn, f.repr)(n.env.root)
        over.node = n

      case null =>
    }
  }
}


/*
 * Checks that a class defines all methods from its interfaces
 */
class InterfaceChecker(klass: Klass) extends Visitor {

  val env = klass.definedat.env
  klass.isas.foreach { isa =>
    env.getInterface(isa.name) match {
      case None => throw new RuntimeException("This is an engine bug")
      case Some(interface) =>
        interface.requirements.foreach {
          case (rname, rty) =>
            klass.definedat.block.children.find {
              case NDef(name, x : NFn) if rname == name => true
              case _ => false
            } match {
              case Some(x) =>
              case None =>
                error(interface, isa, rname, env)
            }
        }
    }
  }

  def error(interface: Interface, isa: Tycon, rname: String, env: Env) {
    val interty = env.get(isa.name).get.tpe
    val tr2 = interface.requirements.map {
      case (a, b) => TraceElement("  - " + a + " : " + env.get(a).get.tpe.repr, null)
    }
    val tr1 = TraceElement("The following functions define a " + interty.repr + ":", null)
    throw new TypeException("Class " + klass.name + " is declared to be " + isa.name + " but does not define " + rname, klass.definedat, tr2 ++ List(tr1))
  }
}

class MatchVarsExtractor(root: Node) extends Visitor {
  val vs = mutable.MutableList[(String, Node)]()

  def vars = vs.toList

  def varnames = vars.map(_._1)

  visit(root)

  def vars(p: Pattern) : List[String] = p match {
    case PVar(_, name) => List(name)
    case PClass(_, _, params, vname) => vname :: params.flatMap { x => vars(x) }
  }

  override def visitNMatch(n: NMatch): Unit = {
    vars(n.pattern).foreach { v => vs.+=((v, n)) }
    super.visitNMatch(n)
  }
}