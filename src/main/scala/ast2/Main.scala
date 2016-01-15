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
  def registerTy(fullname: String, child: String)(implicit env: Env) : Ty = registerTy(fullname, child, List()) 


  /*
   * Registers a type in an environment
   * 
   * registerTy("runtime/Int", "Int", List("Num"), env)
   *
   * This method looks for all parent types
   */
  def registerTy(fullname: String, child: String, parents: List[String])(implicit env: Env) : Ty = registerTy(fullname, tycon(child), parents)

  def registerTy(fullname: String, ty: Tycon, parents: List[String])(implicit env: Env) : Ty = {
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
    val tyvars = Typer3.tyvars(ty)
    val ts = TypeScheme(tyvars, ty)
    env.put(ty.name, ts)
    env.putType(ty.name, fullname, ty)
    isa(ty, allparents)
  }

  /*
   * Registers a IS-A restriction in an environment
   *
   * val int = Tycon("Int", ...)
   * isa(int, List("Num", "Eq"), env)
   */
  def isa(ty: Tycon, parents: List[String])(implicit env: Env) = {
    val ps = parents map tycon
    val res = ps.map { p => 
      env.getType(p.name) match {
        case Some(Type(_, Tycon(name, params))) => p
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
  def registerFn(nativefn: String, name: String, ty: String)(implicit env: Env) : Ty = {
    val tyf = parseType(ty)
    val tyvars = Typer3.tyvars(tyf)
    val ts = TypeScheme(tyvars, tyf)
    env.put(name, ts)
    env.putOverride(name, nativefn, ts)
    tyf
  }

  def registerFn(name: String, ty: String)(implicit env: Env) : Ty = {
    val tyf = parseType(ty)
    val tyvars = Typer3.tyvars(tyf)
    val ts = TypeScheme(tyvars, tyf)
    env.put(name, ts)
    tyf
  }
  
  def registerOverride(nativefn: String, name: String, ty: String)(implicit env: Env) = {
    val t = parseType(ty)
    val ts = TypeScheme(Typer3.tyvars(t), t)
    env.putOverride(name, nativefn, ts)
    t
  }

  def registerInterface(cname: String, methods: List[String])(implicit env: Env) = {
    val requirements = methods.map { m =>
      env.get(m) match {
        case Some(ts @ TypeScheme(_, tyfn @ Tyfn(input, output))) =>
          val inputvars = input.collect {
            case tv @ Tyvar(tn, tr) => tr collect {
              case Isa(Tycon(cname, _)) => tv
            }
          }.flatten.distinct
          if (inputvars.size != 1) throw new RuntimeException("What should I do in this case?")
          val theInputType = inputvars(0)
          val canonicFnType = Typer3.emptySubst.extend(theInputType, Tyvar.wildard)(tyfn)
          (m, canonicFnType.asInstanceOf[Tyfn])
        case _ => throw new RuntimeException("This is a compiler bug")
      }
    }
    val inter = new Interface(cname, requirements)
    env.putInterface(inter)
  }

  /*
   * Creates the root environment which contains the most basic types and functions
   */
  def rootEnv = {
    implicit val env = Env()

    registerTy("runtime/Class",     "Class[a]")
    registerTy("runtime/Unit",      "Unit")
    registerTy("runtime/Eq",        "Eq")
    registerTy("runtime/Set",       "Set[a]")
    registerTy("runtime/Pair",      "Pair[l, r]")
    
    registerTy("runtime/Num",       "Num",        List("Eq"))
    registerTy("runtime/Str",       "Str",        List("Eq"))
    registerTy("runtime/Bool",      "Bool",       List("Eq"))
    registerTy("runtime/Int",       "Int",        List("Num"))
    registerTy("runtime/Float",     "Float",      List("Num"))
    registerTy("runtime/List",      "List[x]",    List("Set[x]"))
    registerTy("runtime/Dict",      "Dict[k, v]", List("Set[Pair[k, v]]"))

    registerFn("runtime/id",        "id",         "a -> a")
    registerFn("runtime/do_",       "do",         "(a -> b), a -> b")
    registerFn("runtime/add",       "add",        "a+Num, a -> a")
    registerFn("runtime/sub",       "sub",        "a+Num, a -> a")
    registerFn("runtime/times",     "times",      "a+Num, a -> a")
    registerFn("runtime/div",       "div",        "a+Num, a -> a")

    registerFn("runtime/oneof",     "oneof",      "a+Set[s] -> s")
    registerFn("runtime/List$size", "List$size",  "List[x] -> Int")
    registerFn("runtime/list_of",   "list",       "a -> List[a]")
    registerFn("runtime/cons",      "cons",       "a, List[a] -> List[a]")
    registerFn("runtime/Nil",       "nil",        "List[a]")
    registerFn("runtime/dict_of",   "dict",       "a, b -> Dict[a, b]")
    registerFn("runtime/extend",    "extend",     "a, b, Dict[a, b] -> Dict[a, b]")
    registerFn("runtime/typeof",    "typeof",     "a -> List[Str]")

    registerFn("eq", "a+Eq, a -> Bool")
    registerOverride("runtime/Int$eq",   "eq", "Int, Int -> Bool")
    registerOverride("runtime/Float$eq", "eq", "Float, Float -> Bool")
    registerOverride("runtime/Str$eq",   "eq", "Str, Str -> Bool")
    registerOverride("runtime/Bool$eq", "eq", "Bool, Bool -> Bool")

    registerFn("size", "a+Set[b] -> Int")
    registerOverride("runtime/List$size", "size", "List[x] -> Int")
    registerOverride("runtime/Dict$size", "size", "Dict[a, b] -> Int")

    registerInterface("Set", List("size"))
    registerInterface("Eq", List("eq"))

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
      env.put(alias, TypeScheme(tyvars, parsed))
      env.putOverride(alias, realname, TypeScheme(tyvars, parsed))
    }

    module
  }
  
  
  /*
   * Type the AST
   */
  class stageType(env: Env, code: String) extends Function1[NModule, NModule] {
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
  class stageTransformFunctions(env: Env, code: String) extends Function1[NModule, NModule] {
    def apply(module: NModule) = {
      // Name all named functions
      new FunctionNamerVisitor2(null).visit(module.main)
      // Set all classes namespaces
      new ClassNamer(module.name, null).visit(module.main)
      // Name all anonymous functions
      val anonFuncs = new AnonymousFunctionNamerVisitor(module).anonFuncs
      // make anonymous functions local
      val ret = new AnonymousFunction2LocalTransformer(module, anonFuncs.toList).apply()
      //show(module.main, code)
      ret
    }
  }


  /*
   * Generates overrides
   */
  class stageGenerateOverrides(env: Env, code: String) extends Function1[NModule, NModule] {
    def apply(module: NModule) = {
      new OverGenerator(module)
      module
    }
  }


  /*
   * Generates a CompilationUnit 
   */
  def stageGenerateUnit(filename: String, module: NModule): CompilationUnit = {
    // Extract references to all functions
    val funcs = new FunctionVisitor(module).functions.toList
    // Extract references to all external symbols
    val externs = funcs.map { f => Extern(f.function, new OverVisitor(f.function).externs) }
    // Extract all defined classes
    val classes = new ClassExtractor(module).classes
    // The final compilation unit.
    new CompilationUnit(filename, module, funcs, externs, classes)
  }
  
  
  /*
   * Parses, types and transforms a source file into a CompilationUnit
   */
  def process(filename: String, code: String) = {
    val env = rootEnv
    val stages = List(
        new stageType(env, code),
        new stageObjectStyle(env),
        new stageTransformFunctions(env, code),
        new stageGenerateOverrides(env, code))
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

        case x @ NClass(name, params, parents, ex) =>
          rep("Class " + name)
          show0(ex, d+1)

        case x @ NInstantiation(name, params) =>
          rep("new " + name)
          params.foreach { x => show0(x, d+1) }

        case _ =>
          rep(n.toString())
      }
    }
    show0(n, 0)
  }
  
  def showLine(codelines: Array[String], message: String, node: Node) = {
    if (node != null) {
      println("At " + node.filename + ":" + node.line + " - ")
      println("    " + message)
      println(codelines(node.line - 1))
      println(" " * node.column + "^")
    }
    else {
      println("    " + message)
    }
  }
  
  def showException(e: TypeException, code: String) = {
    val codelines = code.split("\n")
    println("\n** Type error **")
    showLine(codelines, e.getMessage, e.node)
    e.trace.reverse.foreach { x =>
      showLine(codelines, x.message, x.node)
    }
    println()
  }
}
