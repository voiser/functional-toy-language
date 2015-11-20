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
  
  def visitNApply(n: NApply) {
  }
  
  def visitNIf(n: NIf) {
  }
  
  def visitNRef(n: NRef) {
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

  def visit(n: Node) : Unit = {
    n match {
      
      case x : NFn =>
        visit(x)
        
      case x : NBlock =>
        visit(x)
        
      case x : NDef =>
        visit(x)
        
      case x : NApply =>
        visit(x)
        
      case x : NIf =>
        visit(x)
        
      case x : NRef =>
        visit(x)
      
      case _ =>    
    }
  }
}

class FunctionNamerVisitor2(name: String) extends Visitor {
  
  override def visit(n: NDef) {
    val newName = if (name != null) name + "$" + n.name else n.name
    //println ("Hi, I'm FNV2 on def " + n.name)
    n.value match {
      case x: NFn =>
        //println ("Naming function " + x + " with name " + newName)
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
      //println ("Naming function " + n + " with name " + newName)
      anonFuncs += n
    }
  }
}

class FetchRefsVisitor(root: NFn) extends Visitor {
  
  val captured = scala.collection.mutable.ArrayBuffer[NRef]()  //val intro = scala.collection.mutable.ArrayBuffer[NRef]()
  //val recursive = scala.collection.mutable.ArrayBuffer[NRef]()
  
  override def visitNRef(n: NRef) {
    val e1 = n.env
    val e2 = n.env.locate(n.name)
    //println("  I have a ref to " + n.name + " in " + e1.repr + " which was introduced in " + e2.repr)
    if (e1.isChildOf(e2) && (e2.parent != null)) {
      //println ("  " + root.defname + " " + n.name)
      if (root.defname == n.name) {
        //println ("  This is a recursive reference")
        //recursive += n
        n.isRecursive = true
      }
      else {
        //println ("  This is a captured variable")
        captured += n
      }
    }
    //else {
      //println ("  This is *not* a captured variable")
      //intro += n
    //}
  }
  
  override def visitNApply(n: NApply) {
    val e1 = n.env
    val e2 = n.env.locate(n.name)
    //println("  I have a ref (apply) to " + n.name + " in " + e1.repr + " which was introduced in " + e2.repr) 
    if (e1.isChildOf(e2) && (e2.parent != null)) {
      //println ("  " + root.defname + " " + n.name)
      val r = NRef(n.name)
      r.env = e1
      r.position = n.position
      r.ty = e1.get(n.name).get.tpe
      if (root.defname == n.name) {
        //println ("  This is a recursive call")
        //recursive += r
        n.isRecursive = true
      }
      else {
        //println ("  This is a captured variable")
        captured += r
      }
    }
  }
  
  override def visit(n: NFn) {
  }  
}


/*
 * Extracts all functions in a module, listing all references in each function
 */
class FunctionVisitor(module: NModule) extends Visitor {
  
  val functions = scala.collection.mutable.ArrayBuffer[Function]()
  visit(module.main)
  
  override def visitNFn(n: NFn) {
    //println("Oh I found a function! Is it a closure?")
    //println("  This function introduces environment " + n.env.repr)
    val v = new FetchRefsVisitor(n)
    v.visit(n.value)
    functions += Function(n, v.captured.toList) //, v.recursive.toList))
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

