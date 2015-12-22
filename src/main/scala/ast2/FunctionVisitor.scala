package ast2

import scala.collection.JavaConverters._
import org.antlr.v4.runtime.ParserRuleContext

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
 * Accumulates a reference to each symbols defined in a parent environment.
 * Basically, it locates captured symbols
 */
class FetchRefsVisitor(root: NFn) extends Visitor {

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
    if (e1.isChildOf(e2) && (e2.parent != null)) {
      if (root.defname == n.name) {
        n.isRecursive = true
      }
      else {
        captured += n
      }
    }
  }
  
  override def visitNRefAnon(n: NRefAnon) {
    captured += n
  }
  
  override def visitNApply(n: NApply) {
    val e1 = n.env
    val e2 = n.env.locate(n.name)
    if (e1.isChildOf(e2) && (e2.parent != null)) {
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


/**
 * Extracts all functions in a module, listing all references in each function
 */
class FunctionVisitor(module: NModule) extends Visitor {
  
  val functions = scala.collection.mutable.ArrayBuffer[Function]()
  visit(module.main)
  
  override def visitNFn(n: NFn) {
    val v = new FetchRefsVisitor(n)
    val r = Function(n, v.captures.toList)
    functions += r
    super.visitNFn(n)
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
  def externs = functions.toList
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
  
  override def visit(n: NDef, r: NRef): Unit = {
    pending.put(n.name, (r, n.env.getOverrides(r.name)))
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
            }
          case None => // the function is local
        }
        
      case x => // Several options: this means a dynamic dispatch 
        x.foreach { functions += _ }
        n.dynamicOver = x
    }
  }
}


class ClassNamer(module: String, name: String) extends Visitor {

  override def visit(x: NClass) {
    val ns = if (name != null) name else null
    x.env.getClass(x.name) match {
      case None => throw new TypeException("Class " + x.name + " not found. This is a compiler bug", x, List())
      case Some(k) =>
        k.namespace = ns
        k.modulename = module
    }
  }

  override def visit(n: NDef) {
    val newName = if (name != null) name + "$" + n.name else n.name
    new ClassNamer(module, newName).visit(n.value)
  }
}


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
