package ast2

import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import scala.collection.JavaConverters._

object Main {

  /*
   * Registers a type in an environment
   * This is a convenient alias for registering types without parents:
   *
   * registerTy("runtime/Eq", "Eq", env)
   */
  def registerTy(fullname: String, child: String, env: Env) : Ty = registerTy(fullname, child, List(), env) 


  /*
   * Registers a type in an environment
   * 
   * registerTy("runtime/Int", "Int", List("Num"), env)
   *
   * This method looks for all parent types
   */
  def registerTy(fullname: String, child: String, parents: List[String], env: Env) : Ty = {
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
    
    val tyvars = Typer3.tyvars(ty)
    val ts = TypeScheme(tyvars, ty)
    env.put(ty.name, fullname, ts)
    isa(ty, allparents, env)
  }


  /*
   * Registers a IS-A restriction in an environment
   *
   * val int = Tycon("Int", ...)
   * isa(int, List("Num", "Eq"), env)
   */
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


  /*
   * Registers a native function in an environment
   * 
   * registerFn("runtime/add", "add", "a+Num, a -> a", env)
   */
  def registerFn(nativefn: String, name: String, ty: String, env: Env) : Ty = {
    val tyf = parseType(ty)
    val tyvars = Typer3.tyvars(tyf)
    val ts = TypeScheme(tyvars, tyf)
    env.put(name, nativefn, ts)
    tyf
  }


  /*
   * Creates the root environment which contains the most basic types and functions
   */
  def rootEnv = {
    val env = Env()

    registerTy("runtime/Eq", "Eq",   env)
    registerTy("runtime/Num", "Num", List("Eq"), env)
    
    registerTy("runtime/Bool", "Bool", env)
    registerTy("runtime/Str", "Str",  List("Eq"), env)
    registerTy("runtime/Unit", "Unit", env)
    
    registerTy("runtime/Int", "Int",   List("Num"), env)
    registerTy("runtime/Float", "Float", List("Num"), env)

    registerFn("runtime/id",    "id",    "a -> a",           env)
    registerFn("runtime/do_",   "do",    "(a -> b), a -> b", env) 
    registerFn("runtime/True",  "true",  "Bool",             env)
    registerFn("runtime/False", "false", "Bool",             env)
    registerFn("runtime/add",   "add",   "a+Num, a -> a",    env)
    registerFn("runtime/sub",   "sub",   "a+Num, a -> a",    env)
    registerFn("runtime/times", "times", "a+Num, a -> a",    env)
    registerFn("runtime/div",   "div",   "a+Num, a -> a",    env)
    registerFn("runtime/eq_",   "eq",    "a+Eq,  a -> Bool", env)

    registerTy("runtime/Set", "Set[a]", env)
    //registerFn("runtime/size",  "size",  "a+Set[s] -> Int", env)
    registerFn("runtime/oneof", "oneof", "a+Set[s] -> s", env)

    registerTy("runtime/List", "List[x]", List("Set[x]"), env)
    registerFn("runtime/List$size", "List$size", "List[x] -> Int", env)
    registerFn("runtime/list_of", "list", "a -> List[a]", env)
    registerFn("runtime/cons",    "cons", "a, List[a] -> List[a]", env)
    registerFn("runtime/Nil",     "nil",  "List[a]", env)
    
    registerTy("runtime/Pair", "Pair[l, r]", env)
    
    registerTy("runtime/Dict", "Dict[k, v]", List("Set[Pair[k, v]]"), env)
    registerFn("runtime/dict_of", "dict",   "a, b -> Dict[a, b]", env)
    registerFn("runtime/extend",  "extend", "a, b, Dict[a, b] -> Dict[a, b]", env)
    
    env
  }


  /*
   * Parses a type definition
   *
   * parseType("Int -> Int")
   */
  def parseType(code: String) = {
    val lexer = new TypegrammarLexer(new ANTLRInputStream(code))
    val parser = new TypegrammarParser(new CommonTokenStream(lexer))
    val cst = parser.ty()
    val gty = new TypeVisitor().visitTy(cst)
    Typegrammar.toType(gty)
  }
 

  /*
   * Parses a type constant definition
   *
   * tycon("Int")
   */
  def tycon(code: String) : Tycon = parseType(code) match {
    case x : Tycon => x
    case _ => throw new Exception("Type " + code + " is not a type constant")
  }
 

  /*
   * Parses a function type definition
   * 
   * tyfn("Int -> Int")
   */
  def tyfn(code: String) : Tyfn = parseType(code) match {
    case x : Tyfn => x
    case _ => throw new Exception("Type " + code + " is not a function type")
  } 
  
  /*
   * Build the initial code representation
   */
  def stageZero(filename: String, code: String, env: Env) : NModule = {
    // Build the CST
    val lexer = new GrammarLexer(new ANTLRInputStream(code))
    val parser = new GrammarParser(new CommonTokenStream(lexer))
    // parser.setTrace(true)
    val cst = parser.file()
    
    // Build the AST
    val module = new FirstVisitor(filename).visitFile(cst)
    module.main.name = "main"
    
    // show(module.main, code)
    
    // Imports
    module.imports.foreach { case (realname, alias) => 
      val klass = Class.forName(realname)
      val field = klass.getField("type")
      val functype = field.get(null).asInstanceOf[String]
      val parsed = parseType(functype)
      val tyvars = Typer3.tyvars(parsed)
      env.put(alias, realname, TypeScheme(tyvars, parsed))
    }

    module
  }
  
  
  /*
   * Type the AST
   */
  class stageType(env: Env) extends Function1[NModule, NModule] {
    def apply(module: NModule) = {
      module.main.fwdty = Tyfn(List(), Tyvar("a", List()))
      Typer3.getType(env, module.main)
      // show(module.main, code)
      module
    }
  }

  
  /*
   * Convert object-style calls
   */
  class stageObjectStyle(env: Env) extends Function1[NModule, NModule] {
    def apply(module: NModule) = {
      val ret = new ObjCallTransformer(module).apply()
      // show(ret.main, code)
      ret
    }
  }
  
  
  /*
   * Performs some transformations on functions
   */
  class stageTransformFunctions(env: Env) extends Function1[NModule, NModule] {
    def apply(module: NModule) = {
      // Name all named functions
      new FunctionNamerVisitor2(null).visit(module.main)
      // Name all anonymous functions
      val anonFuncs = new AnonymousFunctionNamerVisitor(module).anonFuncs
      // make anonymous functions local
      val ret = new AnonymousFunction2LocalTransformer(module, anonFuncs.toList).apply()
      // show(module3.main, code)
      ret
    }
  }
  
  
  /*
   * Generates a CompilationUnit 
   */
  def stageGenerateUnit(filename: String, module: NModule) = {
    // Extract references to all functions
    val funcs = new FunctionVisitor(module).functions.toList
    // Extract references to all external symbols
    val externs = funcs.map { x => Extern(x.function, new ReferenceExtractor(x.function).externalFunctions.toList) }
    new CompilationUnit(filename, module, funcs, externs)
  }
  
  
  /*
   * Parses, types and transforms a source file into a CompilationUnit
   */
  def process(filename: String, code: String) = {
    val env = rootEnv
    val stages = List(
        new stageType(env), 
        new stageObjectStyle(env), 
        new stageTransformFunctions(env))
    val module0 = stageZero(filename, code, env)
    val module = stages.foldLeft(module0) { (module, stage) => stage(module) }
    val unit = stageGenerateUnit(filename, module)
    unit
  }


  /*
   * Executes a module
   */
  def execute(module: String, bytes: List[(String, String, Array[Byte])]) = {
    val runtime = new Runtime()
    bytes.foreach { x =>
      runtime.register(x._1, x._2, x._3)
    }
    runtime.apply0(module + ".main") 
  }
  

  /*
   *
   */
  def main(args: Array[String]) {

  }
 
  /*
   * Utility methods to show the typed parse tree
   */
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
