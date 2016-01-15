package ast2

import scala.collection.JavaConverters._

class TypeVisitor extends TypegrammarBaseVisitor[GTy] {

  val restrictions = scala.collection.mutable.Map[String, List[GTy]]()
  
  override def visitTy(ctx: TypegrammarParser.TyContext) = {
    if (ctx.simple != null) visitSimple(ctx.simple)
    else if (ctx.generic != null) visitGeneric(ctx.generic)
    else if (ctx.`var` != null) visitVar(ctx.`var`)
    else visitFn(ctx.fn)
  }
  
  override def visitTy2(ctx: TypegrammarParser.Ty2Context) = {
    if (ctx.simple != null) visitSimple(ctx.simple)
    else if (ctx.generic != null) visitGeneric(ctx.generic)
    else if (ctx.`var` != null) visitVar(ctx.`var`)
    else visitFn(ctx.fn)
  }
  
  override def visitSimple(ctx: TypegrammarParser.SimpleContext) = {
    val name = ctx.getText
    GTycon(name, List())
  }
  
  override def visitGeneric(ctx: TypegrammarParser.GenericContext) = {
    val name = ctx.ID().getText
    val params = ctx.ty2().asScala.toList map visitTy2
    GTycon(name, params)
  }
  
  override def visitVar(ctx: TypegrammarParser.VarContext) = {
    val name = ctx.VAR().getText
    val ret = ctx.restriction.asScala.toList map visitRestriction
    val res = 
      if (ctx.restriction.size() > 0) {
        restrictions.get(name) match {
          case None => 
            restrictions.put(name, ret)
            ret
          case Some(x) if x == ret =>
            ret
          case Some(x) if x != ret =>
            throw new TypegrammarException("Re-defining restrictions for type " + name)
        }
      }
      else {
        restrictions.get(name) match {
          case None => List[GTy]()
          case Some(x) => x
        }
      }
    GTyvar(name, res)
  }
  
  override def visitRestriction(ctx: TypegrammarParser.RestrictionContext) = {
    if (ctx.simple() != null) visitSimple(ctx.simple())
    else visitGeneric(ctx.generic())
  }

  override def visitLeft(ctx: TypegrammarParser.LeftContext) = {
    visitTy2(ctx.ty2)
  }
  
  override def visitFn(ctx: TypegrammarParser.FnContext) = {
    val lefts = ctx.left().asScala.toList.map (visitChildren)
    val right = visitChildren(ctx.right)
    GTyfn(lefts, right)
  }
}

class TypegrammarException(cause: String) extends Exception(cause)

object Typegrammar {
  
  def toRestriction(ty: GTy) : Restriction = ty match {
    case GTycon(name, params) => 
      Isa(Tycon(name, params map toType))
      
    case _ =>
      throw new TypegrammarException("Can't create restriction of " + ty)
  }
  
  def toType(ty: GTy) : Ty = ty match {
    
    case GTyvar(name, restrictions) =>
      Tyvar(name, restrictions map toRestriction)
    
    case GTycon(name, params) =>
      Tycon(name, params map toType)
      
    case GTyfn(lefts, right) => 
      val l = lefts map toType
      val r = toType(right)
      Tyfn(l, r)
      
    case _ =>
      throw new TypegrammarException("Can't map " + ty)
  }
}
