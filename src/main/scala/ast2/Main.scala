package ast2

import org.antlr.v4.runtime.misc.ParseCancellationException
import org.antlr.v4.runtime._
import runtime.Export
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
    parser.setErrorHandler(new BailErrorStrategy)
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
   * Parsing error reporter
   */
  class SyntaxErrorStrategy(filename: String, code: String) extends DefaultErrorStrategy {

    def error(token: Token) {
      val codelines = code.split("\n")
      showLine(codelines, "Syntax error", filename, token.getLine, token.getCharPositionInLine)
      throw new RuntimeException("Syntax error in " + filename + ":" + token.getLine)
    }

    override def recoverInline (recognizer: Parser) : Token = {
      error(recognizer.getCurrentToken)
      null
    }

    override def reportError (parser: Parser, e: RecognitionException) {
      error(parser.getCurrentToken)
    }
  }

  /*
   * Build the initial code representation
   */
  def stageZero(filename: String, code: String, env: Env, runtime: Runtime) : NModule = {
    // Build the CST
    val lexer = new GrammarLexer(new ANTLRInputStream(code))
    val parser = new GrammarParser(new CommonTokenStream(lexer))
    parser.setErrorHandler(new SyntaxErrorStrategy(filename, code))
    // parser.setTrace(true)
    val cst = parser.file()

    // Build the AST
    val module = new FirstVisitor(filename).visitFile(cst)
    module.main.name = "main"
    
    // show(module.main, code)
    
    // Imports
    module.imports.foreach {
      case (pack, fname) =>

        val moduleClass = runtime.classForName(pack + ".main") //Class.forName(pack + ".main")
        val exports = moduleClass.getField("exports").get(null).asInstanceOf[Array[Export]].toList

        val export = exports.find { _.name == fname } match {
          case None => throw new Exception("Function " + fname + " can't be located in module " + pack)
          case Some(export) => export

        }

        val genericType = tyfn(export.`type`)
        val overs = export.overrides

        if (overs.isEmpty) {
          // We are importing a class
          // TODO: when creating a "Whatever.class", export its member information
          val klass = runtime.classForName(pack + "." + fname)
          val isas = klass.getField("isa").get(null).asInstanceOf[Array[String]].toList.map(Tycon(_))
          val theKlass = new Klass(fname, TypeScheme(genericType), isas)
          theKlass.modulename = pack
          val fields = klass.getField("fields").get(null).asInstanceOf[Array[String]].map { f =>
            val tokens = f.split(":")
            Field(tokens(0).trim, parseType(tokens(1)))
          }
          theKlass.fields.++=(fields)
          registerTy(pack + "/" + fname, genericType.out.repr, isas.map{_.repr})(env)
          env.putClass(theKlass)
          env.put(fname, genericType)
        }
        else {
          // we are importing a function
          // Register the generic function
          env.get(fname) match {
            case Some(_) =>
            case None =>
              // first import
              registerFn(fname, genericType.repr)(env)
          }
          overs.foreach { over =>
            val klass = runtime.classForName(over)
            val field = klass.getField("type")
            val functype = field.get(null).asInstanceOf[String]
            val parsed = parseType(functype)
            val tyvars = Typer3.tyvars(parsed)
            registerOverride(over.replace(".", "/"), fname, parsed.repr)(env)
          }
        }
    }

    module
  }


  /*
   * Transform:
   *   if a is AA(x, BB(y)) then { ... }
   * into:
   *   if a is AA(x, _0) then
   *     if _0 is BB(y) then
   *       {...}
   */
  class stageTransformMatches(env: Env, code: String) extends Function1[NModule, NModule] {
    def apply(module: NModule) = {
      // show(module.main, code)
      val ret = new MatchTransformer(module).apply()
      // show(ret.main, code)
      ret
    }
  }


  /*
   * Type the AST
   */
  class stageType(env: Env, code: String) extends Function1[NModule, NModule] {
    def apply(module: NModule) = {
      module.main.fwdty = Tyfn(List(), Tyvar("a", List()))
      Typer3.getType(env, module.main)
      show(module.main, code)
      module
    }
  }

  /*
   * Checks that all defined classes implements the methods of their interfaces
   */
  class stageCheckInterfaces(env: Env, code: String) extends Function1[NModule, NModule] {
    def apply(module: NModule) = {
      val classes = new ClassExtractor(module).classes
      classes.foreach(k => new InterfaceChecker(k))
      module
    }
  }
  
  /*
   * Convert object-style calls
   */
  class stageObjectStyle(env: Env, code: String) extends Function1[NModule, NModule] {
    def apply(module: NModule) = {
      val ret = new ObjCallTransformer(module).apply()
      // show(ret.main, code)
      ret
    }
  }


  /*
   * Rename class constants
   */
  class stageClassConstants(env: Env, code: String) extends Function1[NModule, NModule] {
    def apply(module: NModule) = {
      // show(module.main, code)
      val ret = new ClassConstantRenamer(module).apply()
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
      // show(module.main, code)
      val ret1 = new AnonymousFunction2LocalTransformer(module, anonFuncs.toList).apply()
      val ret2 = new ClassMover(ret1).apply()
      val ret = new OverrideMover(ret2).apply()
      // show(ret.main, code)
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
    val externs = funcs.map { f =>
      val allovers = new OverVisitor(f.function).allovers
      val split = allovers.groupBy(_.isLocal)
      val externs =
        if (split.contains(false)) split(false)
        else List()
      Extern(f.function, externs)
    }
    // Extract all defined classes
    val classes = new ClassExtractor(module).classes
    // The final compilation unit.
    new CompilationUnit(filename, module, funcs, externs, classes/*, localOvers*/)
  }
  
  
  /*
   * Parses, types and transforms a source file into a CompilationUnit
   */
  def process(filename: String, code: String) : CompilationUnit = process(filename, code, new Runtime())
  def process(filename: String, code: String, runtime: Runtime) : CompilationUnit = {
    val env = rootEnv
    val stages = List(
        new stageTransformMatches(env, code),
        new stageType(env, code),
        new stageCheckInterfaces(env, code),
        new stageObjectStyle(env, code),
        new stageClassConstants(env, code),
        new stageTransformFunctions(env, code),
        new stageGenerateOverrides(env, code))
    val module0 = stageZero(filename, code, env, runtime)
    val module = stages.foldLeft(module0) { (module, stage) => stage(module) }
    //show(module.main, code)
    val unit = stageGenerateUnit(filename, module)
    unit
  }


  /*
   * Executes a module
   */
  def execute(module: String, bytes: List[(String, String, Array[Byte])]) : Any = execute(module, bytes, new Runtime())
  def execute(module: String, bytes: List[(String, String, Array[Byte])], runtime: Runtime) : Any = {
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

        case x @ NMatch(source, pattern, block) =>
          rep("match " + pattern)
          show0(source, d+1)
          show0(block, d+1)

        case _ =>
          rep(n.toString())
      }
    }
    show0(n, 0)
  }
  
  def showLine(codelines: Array[String], message: String, node: Node): Unit = {
    if (node != null) showLine(codelines, message, node.filename, node.line, node.column)
    else println("    " + message)
  }

  def showLine(codelines: Array[String], message: String, filename: String, line: Int, column: Int) = {
    println("At " + filename + ":" + line + " - ")
    println("    " + message)
    println(codelines(line - 1))
    println(" " * column + "^")
  }
  
  def showException(e: TypeException, code: String) = {
    try {
      val codelines = code.split("\n")
      println("\n** Type error **")
      showLine(codelines, e.getMessage, e.node)
      e.trace.reverse.foreach { x =>
        showLine(codelines, x.message, x.node)
      }
      println()
    }
    catch {
      case e2 : Throwable =>
        throw e
    }
  }
}
