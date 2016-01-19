package ast2

import scala.collection.mutable

/**
 * A visitor that returns a node for each visited one.
 * Meant to be used as a basis to a tree transformation.
 */
class Transformer {
  
  def fill[T<:Node](origin: T, dest: T): T = {
    if (dest != null) {
      dest.env = origin.env
      dest.ctx = origin.ctx
      dest.ty = origin.ty
      dest.filename = origin.filename

      (origin, dest) match {
        case (o: NFn, d: NFn) =>
          d.name = o.name
          d.defname = o.defname
          d.fwdty = o.fwdty
          d.isOverride = o.isOverride

        case (o: NApply, d: NApply) =>
          d.realName = o.realName
          d.isRecursive = o.isRecursive
          d.resolvedType = o.resolvedType

        case _ =>
      }
    }
    dest
  }
  
  def visitNModule(n: NModule): Node = {
    NModule(n.name, n.imports, visit(n.main).asInstanceOf[NFn])
  }
  
  def visitNFn(n: NFn): Node = {
    val b = visit(n.value).asInstanceOf[NBlock]
    NFn(n.params, fill(n.value, b))
  }
  
  def visitNBlock(n: NBlock): Node = {
    val children = n.children.map { x => visit(x) }
    NBlock(children)
  }
  
  def visitNDef(n: NDef): Node = {
    NDef(n.name, visit(n.value))
  }
  
  def visitNApply(n: NApply): Node = {
    val params = n.params.map { x => visit(x) }
    NApply(n.name, params)
  }
  
  def visitNObjApply(n: NObjApply): Node = {
    NObjApply(visit(n.callee), visit(n.apply).asInstanceOf[NApply])
  }
  
  def visitNIf(n: NIf): Node = {
    NIf(visit(n.cond), visit(n.exptrue), visit(n.expfalse))
  }
  
  def visitNRef(n: NRef): Node = {
    NRef(n.name)
  }
  
  def visitNDefAnon(n: NDefAnon): Node = {
    NDefAnon(n.name, visit(n.value))
  }
  
  def visitNRefAnon(n: NRefAnon): Node = {
    NRefAnon(n.name)
  }

  def visitNClass(n: NClass): Node = {
    NClass(n.name, n.params, n.is, visit(n.block).asInstanceOf[NBlock])
  }

  def visitNDefFn(d: NDef, f: NFn): Node = {
    NDef(d.name, visit(f))
  }

  def visit(n: Node) : Node = {
    n match {
      
      case x : NModule =>
        fill(n, visitNModule(x))
      
      case x : NFn =>
        fill(n, visitNFn(x))
        
      case x : NBlock =>
        fill(n, visitNBlock(x))

      case x @ NDef(_, f : NFn) =>
        fill(n, visitNDefFn(x, f))

      case x : NDef =>
        fill(n, visitNDef(x))
        
      case x : NApply =>
        fill(n, visitNApply(x))
        
      case x : NObjApply =>
        fill(n, visitNObjApply(x))
        
      case x : NIf =>
        fill(n, visitNIf(x))
        
      case x : NRef =>
        fill(n, visitNRef(x))
      
      case x : NDefAnon =>
        fill(n, visitNDefAnon(x))
        
      case x : NRefAnon =>
        fill(n, visitNRefAnon(x))

      case x : NClass =>
        fill(n, visitNClass(x))

      case _ =>
        n
    }
  }
}


/**
 * Transforms an anonymous function into a local one.
 * 
 * a = { ... { blah } ... }
 * 
 * is transformed to:
 * 
 * envxx$_x = { blah }
 * a = { ... NRefAnon(envxx$_x) ... }
 * 
 */
class AnonymousFunction2LocalTransformer(module: NModule, anons: List[NFn]) extends Transformer {
 
  val anonNames = anons.map { x => x.name }
  
  def apply() : NModule = {
    val main2 = visitNFn(module.main).asInstanceOf[NFn] 
    val newdefs = anons.map { a =>
      fill(a, NDefAnon(a.name, a))
    }
    val newlines = newdefs ++ main2.value.children
    val newf = NFn(module.main.params, fill(module.main.value, NBlock(newlines)))
    val m2 = NModule(module.name, module.imports, fill(module.main, newf))
    fill(module, m2)
  }
  
  override def visitNFn(n: NFn) : Node = {
    if (anonNames.contains(n.name)) {
      val x = NRefAnon(n.name)
      fill(n, x)
      x
    } 
    else {
      super.visitNFn(n)
    }
  }
}


/*
 *
 */
class ClassMover(module: NModule) extends Transformer {

  val classes = mutable.MutableList[Node]()

  def apply() = {
    val oldefs = visitNBlock(module.main.value).asInstanceOf[NBlock].children.filterNot(_==null)
    val newdefs = classes.toList ++ oldefs
    val newBlock = NBlock(newdefs)
    val newf = NFn(module.main.params, fill(module.main.value, newBlock))
    val m2 = NModule(module.name, module.imports, fill(module.main, newf))
    fill(module, m2)
  }

  override def visitNBlock(n: NBlock) : Node = {
    val innerclasses = n.children.collect { case x : NClass => x }
    val newchildren = n.children.diff(innerclasses)
    innerclasses foreach { k => classes.+=(visitNClass(k)) }
    fill(n, NBlock(newchildren))
  }

  override def visitNClass(n: NClass): Node = {
    val newblock = visitNBlock(n.block).asInstanceOf[NBlock]
    fill(n, NClass(n.name, n.params, n.is, newblock))
  }
}


/*
 *
 */
class OverrideMover(module: NModule) extends Transformer {

  val classes = mutable.MutableList[Node]()
  val defs = mutable.MutableList[Node]()

  def apply() = {
    val oldefs = visitNBlock(module.main.value).asInstanceOf[NBlock].children.filterNot(_==null)
    val newdefs = classes.toList ++ defs.toList ++ oldefs
    val newblock = NBlock(newdefs)
    val newf = NFn(module.main.params, fill(module.main.value, newblock))
    val m2 = NModule(module.name, module.imports, fill(module.main, newf))
    fill(module, m2)
  }

  override def visitNClass(n: NClass): Node = {
    defs.++=(n.block.children)
    classes.+=(fill(n, NClass(n.name, n.params, n.is, fill(n.block, NBlock(List())))))
    null
  }
}

/**
 * Transforms object-style calls
 * 
 * a.method(p1, p2)
 * 
 * into
 * 
 * ClassName.method(a, p1, p2)
 */
class ObjCallTransformer(module: NModule) extends Transformer {
  
  def apply() : NModule = {
    visitNModule(module).asInstanceOf[NModule]
  }
  
  override def visitNObjApply(n: NObjApply) = {
    n.callee.ty match {
      case Tycon(name, _) =>
        val realfname = name + "$" + n.apply.name
        val x = NApply(realfname, n.callee :: n.apply.params)
        fill(n.apply, x)
        x
    }
  }
}
