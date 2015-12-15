package ast2

import scala.collection.JavaConverters._
import org.antlr.v4.runtime.ParserRuleContext

/**
 * @author david
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

  def visit(n: Node) : Unit = {
    n match {
      
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
      
      case _ =>    
    }
  }
}

class FunctionNamerVisitor2(name: String) extends Visitor {
  
  override def visit(n: NDef) {
    val newName = if (name != null) name + "$" + n.name else n.name
    n.value match {
      case x: NFn =>
        x.name = newName
        x.defname = n.name
      case _ => 
    }
    new FunctionNamerVisitor2(newName).visit(n.value)
  }
}

class AnonymousFunctionNamerVisitor(module: NModule) extends Visitor {
  
  val anonFuncs = scala.collection.mutable.ArrayBuffer[NFn]()
  var i = 0
  visit(module.main)
  
  override def visitNFn(n: NFn) {
    if (n.name == null) {
      val newName = n.env.repr + "$_" + i
      n.name = newName
      n.defname = newName
      anonFuncs += n
    }
  }
}

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


/*
 * Extracts all functions in a module, listing all references in each function
 */
class FunctionVisitor(module: NModule) extends Visitor {
  
  val functions = scala.collection.mutable.ArrayBuffer[Function]()
  visit(module.main)
  
  override def visitNFn(n: NFn) {
    val v = new FetchRefsVisitor(n)
    val r = Function(n, v.captures.toList) //, v.recursive.toList))
    functions += r
    super.visitNFn(n)
  }
}

class ReferenceExtractor(root: Node) extends Visitor {
  
  val externalFunctions = scala.collection.mutable.Set[String]()
  visit(root)
  
  override def visitNApply(n: NApply) {
    val fullname = n.env.getFull(n.realName)
    if (fullname != null) externalFunctions += n.realName
  }
  
  override def visitNRef(n: NRef) {
    val fullname = n.env.getFull(n.name)
    if (fullname != null) externalFunctions += n.name
  }
}


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

