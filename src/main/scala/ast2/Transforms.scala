package ast2

class Transformer {
  
  def fill[T<:Node](origin: T, dest: T): T = {
    dest.env = origin.env
    dest.ctx = origin.ctx
    dest.ty = origin.ty
    
    (origin, dest) match {
      case (o:NFn, d:NFn) =>
        d.name = o.name
        d.defname = o.defname
        
      case (o: NApply, d:NApply) =>
        d.realName = o.realName
        d.isRecursive = o.isRecursive
      
      case _ => 
    }
    dest
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
  
  def visitNIf(n: NIf): Node = {
    NIf(visit(n.cond), visit(n.exptrue), visit(n.expfalse))
  }
  
  def visitNRef(n: NRef): Node = {
    NRef(n.name)
  }
  
  def visit(n: Node) : Node = {
    n match {
      
      case x : NFn =>
        fill(n, visitNFn(x))
        
      case x : NBlock =>
        fill(n, visitNBlock(x))
        
      case x : NDef =>
        fill(n, visitNDef(x))
        
      case x : NApply =>
        fill(n, visitNApply(x))
        
      case x : NIf =>
        fill(n, visitNIf(x))
        
      case x : NRef =>
        fill(n, visitNRef(x))
      
      case _ => 
        n
    }
  }
}


class AnonymousFunction2LocalTransformer(module: NModule, anons: List[NFn]) extends Transformer {
 
  val anonNames = anons.map { x => x.name }
  
  def apply() : NModule = {
    val main2 = visitNFn(module.main).asInstanceOf[NFn] 
    val newdefs = anons.map { a =>
      fill(a, NDef(a.name, a))
    }
    val newlines = newdefs ++ main2.value.children
    val newf = NFn(module.main.params, fill(module.main.value, NBlock(newlines)))
    val m2 = NModule(module.name, module.imports, fill(module.main, newf))
    fill(module, m2)
  }
  
  override def visitNFn(n: NFn) : Node = {
    //println("Visiting a function with name " + n.name)
    if (anonNames.contains(n.name)) {
      //println("  This is an anonymous function!")
      val x = NRef(n.name)
      fill(n, x)
      x
    } 
    else {
      super.visitNFn(n)
    }
  }
  
}



