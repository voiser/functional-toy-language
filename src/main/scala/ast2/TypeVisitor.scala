package ast2

import scala.collection.JavaConverters._

class TypeVisitor extends TypegrammarBaseVisitor[GTy] {

  override def visitTy(ctx: TypegrammarParser.TyContext) = {
    if (ctx.simple != null) visitSimple(ctx.simple)
    else visitFn(ctx.fn)
  }
  
  override def visitTy2(ctx: TypegrammarParser.Ty2Context) = {
    if (ctx.simple != null) visitSimple(ctx.simple)
    else visitFn(ctx.fn)
  }
  
  override def visitSimple(ctx: TypegrammarParser.SimpleContext) = {
    val name = ctx.getText
    GTycon(name)
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
    case GTycon(name) =>
      env.getType(name) match {
        case Some(ty) => ty
        case None => throw new TypegrammarException("Can't find type " + name + " in the environment")
      }
      
    case GTyfn(lefts, right) => 
      val l = lefts.map { x => toType(x, env) }
      val r = toType(right, env)
      Tyfn(l, r)
      
    case _ =>
      throw new TypegrammarException("Can't map " + ty)
  }
}
