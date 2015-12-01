package ast2

import scala.collection.JavaConverters._

class TypeVisitor extends TypegrammarBaseVisitor[GTy] {

  override def visitTy(ctx: TypegrammarParser.TyContext) = {
    if (ctx.simple != null) visitSimple(ctx.simple)
    else if (ctx.generic != null) visitGeneric(ctx.generic)
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
    GTyvar(name)
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
  
  def toType(ty: GTy, env: Env) : Ty = ty match {
    
    case GTyvar(name) =>
      Tyvar(name, List())
    
    case GTycon(name, params) =>
      env.getType(name) match {
        
        case None => throw new TypegrammarException("Can't find type " + name + " in the environment")
        
        case Some(t) => t match {
          case Tycon(n, tys, isa) => 
            val tyvars = Typer3.tyvars(t)
            val ts = TypeScheme(tyvars, t)
            val typarams = params map { x => toType(x, env) }
            ts.applyTo(typarams)
            
        }
      }
      
    case GTyfn(lefts, right) => 
      val l = lefts.map { x => toType(x, env) }
      val r = toType(right, env)
      Tyfn(l, r)
      
    case _ =>
      throw new TypegrammarException("Can't map " + ty)
  }
}
