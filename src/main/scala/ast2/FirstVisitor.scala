package ast2

import scala.collection.JavaConverters._
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import ast2.GrammarParser.ExpressionContext
import ast2.GrammarParser.ForwardContext

/**
 * @author david
 */

class FirstVisitor(filename: String) extends GrammarBaseVisitor[Node] {

  def fill[T <: Node](n: T, c: ParserRuleContext) = {
    n.filename = filename
    n.ctx = c
    n
  }

  def getTy(ty: ParserRuleContext) = {
    val lexer = new TypegrammarLexer(new ANTLRInputStream(ty.getText))
    val parser = new TypegrammarParser(new CommonTokenStream(lexer))
    val cst = parser.ty()
    new TypeVisitor().visitTy(cst)
  }

  override def visitExpression(ctx: GrammarParser.ExpressionContext) = {
    if (ctx.exp != null)
      visitExpression(ctx.exp)
    else if (ctx.binop != null) {
      val left = visitExpression(ctx.left)
      val right = visitExpression(ctx.right)
      val op = ctx.binop.getText
      val fname = op match {
        case "+" => "add"
        case "-" => "sub"
        case "*" => "times"
        case "/" => "div"
        case "==" => "eq"
        case "!=" => "neq"
      }
      fill(NApply(fname, List(left, right)), ctx)
    }
    else if (ctx.defsimple != null) {
      val ident = ctx.defsimple.getText
      val expr = visitExpression(ctx.defsimple2)
      fill(NDef(ident, expr), ctx)
    }
    else if (ctx.defn != null) {
      val pars = ctx.fnargpair().asScala.toList.map { visitFnargpair(_).asInstanceOf[NFnArg] }
      val thefn = (pars, visitExpression(ctx.body)) match {
        case (params, x @ NFn(List(), _)) if params.isEmpty => x
        case (params, x @ NFn(List(), fb)) => fill(NFn(params, fb), ctx.body)
        case (params, x @ NFn(fp, b)) => fill(NFn(params, fill(NBlock(List(x)), ctx.body)), ctx.body)
        case (params, e) => fill(NFn(params, NBlock(List(e))), ctx.body)
      }
      val ident = ctx.defn.getText
      fill(NDef(ident, thefn), ctx)
    }
    else if (ctx.objfield != null) {
      val owner = visitExpression(ctx.objfield)
      val field = ctx.ID().getText
      fill(new NField(owner, field), ctx)
    }
    else if (ctx.objapply != null) {
      val owner = visitExpression(ctx.objapply)
      val apply = visitApply(ctx.apply())
      fill(NObjApply(owner, apply), ctx)
    }
    else visitChildren(ctx)
  }
  
  override def visitValue(ctx: GrammarParser.ValueContext) = {
    if (ctx.INTEGER != null) {
      val raw = ctx.INTEGER.getText
      val i = java.lang.Integer.parseInt(raw)
      fill(NInt(i), ctx)
    } 
    else if (ctx.FLOAT != null) {
      val raw = ctx.FLOAT.getText
      val f = java.lang.Float.parseFloat(raw)
      fill(NFloat(f), ctx)
    }
    else if (ctx.STRING != null) {
      fill(NString(ctx.STRING.getText), ctx)
    }
    else if (ctx.boolfalse != null) {
      fill(NBool(false), ctx)
    }
    else if (ctx.booltrue != null) {
      fill(NBool(true), ctx)
    }
    else {
      throw new ParseException("Can't parse value " + ctx)
    }
  }

  override def visitApply(ctx: GrammarParser.ApplyContext) = {
    val fname = ctx.ID.getText
    val params= ctx.expression().asScala.toList map visitExpression
    fill(NApply(fname, params), ctx)
  }

  override def visitRef(ctx: GrammarParser.RefContext) = {
    val ident = ctx.ID.getText
    fill(NRef(ident), ctx)
  }

  override def visitFnargpair(ctx: GrammarParser.FnargpairContext) = {
    val ident = ctx.ID.getText
    val r =
      if (ctx.tydef() != null) NFnArg(ident, KlassConst(getTy(ctx.tydef())))
      else NFnArg(ident, KlassVar("T"))
    fill(r, ctx)
  }
  
  override def visitFn(ctx: GrammarParser.FnContext) = {
    val expr = visitBlock(ctx.block())
    val args = ctx.fnargpair()
    if (args == null || args.size == 0) {
      fill(NFn(List(), expr), ctx)
    }
    else {
      def params = args.asScala.toList.map { visitFnargpair(_).asInstanceOf[NFnArg] }
      fill(NFn(params, expr), ctx)
    }
  }
  
  override def visitCond(ctx: GrammarParser.CondContext) = {
    val cond = visitExpression(ctx.condition)
    val exptrue = visitExpression(ctx.exptrue)
    val expfalse = visitExpression(ctx.expfalse)
    fill(NIf(cond, exptrue, expfalse), ctx)
  }
  
  override def visitBlock(ctx: GrammarParser.BlockContext) = {
    val visitedChildren = ctx.children.asScala.toList.collect { 
      case x : ExpressionContext => visitExpression(x)
      case x : ForwardContext => visitForward(x)
    }
    fill(NBlock(visitedChildren), ctx)
  }
  
  override def visitFile(ctx: GrammarParser.FileContext) = {
    val f = fill(NFn(List(), visitBlock(ctx.block())), ctx)
    val imports = ctx.imp().asScala.toList.map { x =>
      val list = x.ID.asScala.toList.map{_.getText}
      val (pack, fname) = (list.init.mkString("."), list.last)
      (pack, fname)
    }
    fill(NModule(ctx.name.getText, imports, f), ctx)
  }
  
  override def visitForward(ctx: GrammarParser.ForwardContext) = {
    val name = ctx.ID().getText
    val gty = getTy(ctx.ty)
    fill(NForward(name, gty), ctx)
  }

  override def visitList(ctx: GrammarParser.ListContext) = {
    val exprs = ctx.expression().asScala.toList    
    def cons(exprs: List[GrammarParser.ExpressionContext]) : Node = exprs match {
      case List(a) => fill(NApply("list", List(visitExpression(a))), ctx)
      case x :: xs => fill(NApply("cons", List(visitExpression(x), cons(xs))), ctx)
    }
    cons(exprs)
  }
  
  override def visitMap(ctx: GrammarParser.MapContext) = {
    val pairs = ctx.mappair().asScala.toList
    def cons(exprs: List[GrammarParser.MappairContext]) : Node = exprs match {
      case List(a) => fill(NApply("dict", List(visitExpression(a.mapkey), visitExpression(a.mapvalue))), ctx)
      case x :: xs => fill(NApply("extend", List(visitExpression(x.mapkey), visitExpression(x.mapvalue), cons(xs))), ctx)
    }
    cons(pairs)
  }

  override def visitKlass(ctx: GrammarParser.KlassContext) = {
    val gen = new TyvarGenerator("a")
    def getKlassvar(ctx: GrammarParser.KlassvarContext) = {
      val name = ctx.ID().getText
      val ty =
        if (ctx.tydef() != null) getTy(ctx.tydef)
        else GTyvar(gen.get().name, List())
      (name, ty)
    }
    val name = ctx.CLASSID().getText
    val typarams = ctx.klassvar.asScala.toList map getKlassvar
    val block =
      if (ctx.block() != null) visitBlock(ctx.block())
      else fill(NBlock(List()), ctx)
    val supers = ctx.klassparent().asScala.toList.map(getTy)
    fill(NClass(name, typarams, supers, block), ctx)
  }

  override def visitInstantiation(ctx: GrammarParser.InstantiationContext) = {
    val className = ctx.CLASSID().getText
    val params = ctx.expression().asScala.toList map visitExpression
    fill(NInstantiation(className, params), ctx)
  }
}

