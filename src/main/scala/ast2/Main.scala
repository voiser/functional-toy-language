package ast2

import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import scala.collection.JavaConverters._

object Main {

  def register(child: String, env: Env) : Ty = register(child, List(), env) 
  
  def register(child: String, parents: List[String], env: Env) : Ty = {
    def parentsOf(n: String) : List[String] = {
      val myparents = env.getIsa(n) match {
        case None => List()
        case Some((x, y)) => 
          y.collect {
            case z : Tycon => z.name
          }
      }
      val theirparents = myparents flatMap parentsOf
      (myparents ++ theirparents).distinct
    }
    
    val allparents = parents ++ (parents flatMap parentsOf)
    val ty = tycon(child)
    env.putType(ty.name, ty)
    isa(ty, allparents, env)
  }
  
  def isa(ty: Tycon, parents: List[String], env: Env) = {
    val ps = parents map tycon
    val res = ps.map { p => 
      env.getType(p.name) match {
        case Some(Tycon(name, params)) => p
        case _ => throw new Exception("Can't find type " + p + " in the environment.")
      }
    }
    env.putIsa(ty, res)
    ty
  }
  
  def register(name: String, nativefn: String, ty: String, env: Env) : Ty = {
    val tyf = parseType(ty)
    val tyvars = Typer3.tyvars(tyf)
    val ts = TypeScheme(tyvars, tyf)
    env.put(name, nativefn, ts)
    tyf
  }
  
  def rootEnv = {
    val env = Env()

    register("Eq",   env)
    register("Num", List("Eq"), env)
    
    register("Bool", env)
    register("Str",  List("Eq"), env)
    register("Unit", env)
    
    register("Int",   List("Num"), env)
    register("Float", List("Num"), env)

    register("id",    "runtime/id",    "a -> a",           env)
    register("do",    "runtime/do_",   "(a -> b), a -> b", env) 
    register("true",  "runtime/True",  "Bool",             env)
    register("false", "runtime/False", "Bool",             env)
    register("add",   "runtime/add",   "a+Num, a -> a",    env)
    register("sub",   "runtime/sub",   "a+Num, a -> a",    env)
    register("times", "runtime/times", "a+Num, a -> a",    env)
    register("div",   "runtime/div",   "a+Num, a -> a",    env)
    register("eq",    "runtime/eq_",   "a+Eq,  a -> Bool", env)

    register("Set[a]", env)
    register("size", "runtime/size", "a+Set[s] -> Int", env)
    register("oneof", "runtime/oneof", "a+Set[s] -> s", env)

    register("List[x]", List("Set[x]"), env)
    register("list", "runtime/list_of", "a -> List[a]", env)
    register("cons", "runtime/cons", "a, List[a] -> List[a]", env)
    register("nil", "runtime/Nil", "List[a]", env)
    
    register("Pair[l, r]", env)
    
    register("Dict[k, v]", List("Set[Pair[k, v]]"), env)
    register("dict", "runtime/dict_of", "a, b -> Dict[a, b]", env)
    register("extend", "runtime/extend", "a, b, Dict[a, b] -> Dict[a, b]", env)
    
    env
  }

  def parseType(code: String) = {
    val lexer = new TypegrammarLexer(new ANTLRInputStream(code))
    val parser = new TypegrammarParser(new CommonTokenStream(lexer))
    val cst = parser.ty()
    val gty = new TypeVisitor().visitTy(cst)
    Typegrammar.toType(gty)
  }
  
  def tycon(code: String) : Tycon = parseType(code) match {
    case x : Tycon => x
    case _ => throw new Exception("Type " + code + " is not a type constant")
  }
  
  def tyfn(code: String) : Tyfn = parseType(code) match {
    case x : Tyfn => x
    case _ => throw new Exception("Type " + code + " is not a function type")
  } 
  
  def process(filename: String, code: String) = {
    
    // Build the CST
    val lexer = new GrammarLexer(new ANTLRInputStream(code))
    val parser = new GrammarParser(new CommonTokenStream(lexer))
    //parser.setTrace(true)
    val cst = parser.file()
    
    // Build the AST
    val module = new FirstVisitor(filename).visitFile(cst)
    module.main.name = "main"

    // Generate the root environment
    val rootEnvironment = rootEnv 
    
    // Imports
    module.imports.foreach { case (realname, alias) => 
      val klass = Class.forName(realname)
      val field = klass.getField("type")
      val functype = field.get(null).asInstanceOf[String]
      val parsed = parseType(functype)
      val tyvars = Typer3.tyvars(parsed)
      rootEnvironment.put(alias, realname, TypeScheme(tyvars, parsed))
    }
    
    // Type the AST
    Typer3.getType(rootEnvironment, module.main)

    // show(module.main, code)
        
    // Name all named functions
    // new FunctionNamerVisitor(module)
    new FunctionNamerVisitor2(null).visit(module.main)
    
    // Name all anonymous functions
    val anonFuncs = new AnonymousFunctionNamerVisitor(module).anonFuncs
    
    // make anonymous functions local
    val module2 = new AnonymousFunction2LocalTransformer(module, anonFuncs.toList).apply()
    
    // show(module2.main, code)
    
    // Extract references to all functions
    val funcs = new FunctionVisitor(module2).functions.toList
   
    // Extract references to all external symbols
    val externs = funcs.map { x => Extern(x.function, new ReferenceExtractor(x.function).externalFunctions.toList) }
    
    // Extract references to all function calls 
    //val calls = funcs.map { x => Call(x.function, new CallExtractor(x.function).calls.toList) }
    
    val unit = new CompilationUnit(filename, module2, funcs, externs)
    
    unit
  }

  def execute(module: String, bytes: List[(String, String, Array[Byte])]) = {
	  val runtime = new Runtime()
    bytes.foreach { x =>
      runtime.register(x._1, x._2, x._3)
    }
    runtime.apply0(module + ".main") 
  }
  
  def main(args: Array[String]) {

  }
  
  def show(n: Node, code: String) {
    val codelines = code.split("\n")
    
    def show0(n: Node, d: Int) {
    
      def rep(s: String) {
        val position = n.filename + " Line " + n.line
        val prefix = "  " * d + " " + s + " :: " + (if (n.ty != null) n.ty.repr else "(none yet)") 
        val infix = if (prefix.length() < 50) " " * (5 + (50 - prefix.length())) else "     "
        val suffix1 = "at " + position + " "
        val suffix2 = "in " + n.env
        println(prefix + infix + suffix1 + suffix2)
      }
          
      n match {
            
        case NBlock(ex) =>
          rep("Block")
          ex.foreach { ex => show0(ex, d+1) }
                
        case NDef(name, ex) =>
          rep("Def " + name)
          show0(ex, d+1)
          
        case NDefAnon(name, ex) =>
          rep("DefAnon " + name)
          show0(ex, d+1)
                
        case NFn(params, ex) =>
          rep("Fn")
          show0(ex, d+1)        
                  
        case NApply(name, params) =>
          rep("Apply " + name)
          params.foreach { x => show0(x, d+1) }
          
        case NIf(cond, exptrue, expfalse) =>
          rep("If")
          show0(cond, d+1)
          show0(exptrue, d+1)
          show0(expfalse, d+1)
                  
        case _ =>
          rep(n.toString())
      }
    }
    show0(n, 0)
  }
  
  def showLine(codelines: Array[String], message: String, node: Node) = {
    println("At " + node.filename + ":" + node.line + " - " + message)
    println(codelines(node.line - 1))
    println(" " * node.column + "^" * node.charsize) 
  }
  
  def showException(e: TypeException, code: String) = {
    val codelines = code.split("\n")
    println("\n** Type error **")
    showLine(codelines, e.getMessage, e.node)
    e.trace.reverse.foreach { x =>
      showLine(codelines, x.message, x.node)
    }
    println()
    throw e
  }
}
