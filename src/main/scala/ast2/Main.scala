package ast2

import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import scala.collection.JavaConverters._

object Main {

  def rootEnv = {
    val env = Env()
    val a = Tyvar("a")
    val b = Tyvar("b")
    
    val eq = Typer3.eqType
    val num = Typer3.numType
    val int = Typer3.intType
    val float = Typer3.floatType
    val bool = Typer3.boolType
    val unit = Typer3.unitType
    val str = Typer3.stringType
    val listType = Tycon("List", List(a), List())
    val dictType = Tycon("Dict", List(a, b), List())
    
    env.putType("Eq", eq)
    env.putType("Num", num)
    env.putType("Int", int)
    env.putType("Float", float)
    env.putType("Bool", bool)
    env.putType("Str", str)
    env.putType("Unit", unit)
    env.putType("List", listType)
    env.putType("Dict", dictType)
    
    env.put("true", "runtime/True", TypeScheme(List(), bool))
    env.put("false", "runtime/False", TypeScheme(List(), bool))

    env.put("add", "runtime/add", TypeScheme(List(), Tyfn(List(num, num), num)))
    env.put("sub", "runtime/sub", TypeScheme(List(), Tyfn(List(num, num), num)))
    env.put("times", "runtime/times", TypeScheme(List(), Tyfn(List(num, num), num)))
    env.put("div", "runtime/div", TypeScheme(List(), Tyfn(List(num, num), num)))

    env.put("id", "runtime/id", TypeScheme(List(a), Tyfn(List(a), a)))
    env.put("do", "runtime/do_", TypeScheme(List(a, b), Tyfn(List(Tyfn(List(a), b), a), b)))
    env.put("eq", "runtime/eq_", TypeScheme(List(), Tyfn(List(eq, eq), bool)))
    //env.put("puts", "runtime/puts", TypeScheme(List(), Tyfn(List(a), unit)))
    
    env.put("list", "runtime/list_of", TypeScheme(List(a), Tyfn(List(a), listType)))
    env.put("cons", "runtime/cons", TypeScheme(List(a), Tyfn(List(a, listType), listType)))
    env.put("nil",  "runtime/Nil", TypeScheme(List(a), listType))
    
    env.put("dict", "runtime/dict_of", TypeScheme(List(a, b), Tyfn(List(a, b), dictType)))
    env.put("extend", "runtime/extend", TypeScheme(List(a, b), Tyfn(List(a, b, dictType), dictType)))
    
    env
  }

  def parseType(code: String) = {
    val lexer = new TypegrammarLexer(new ANTLRInputStream(code))
    val parser = new TypegrammarParser(new CommonTokenStream(lexer))
    val cst = parser.ty()
    val gty = new TypeVisitor().visitTy(cst)
    Typegrammar.toType(gty, Main.rootEnv)
  }
  
  def process(filename: String, code: String) = {
    
    // Build the CST
    val lexer = new GrammarLexer(new ANTLRInputStream(code))
    val parser = new GrammarParser(new CommonTokenStream(lexer))
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

    //show(module.main, code)
        
    // Name all named functions
    // new FunctionNamerVisitor(module)
    new FunctionNamerVisitor2(null).visit(module.main)
    
    // Name all anonymous functions
    val anonFuncs = new AnonymousFunctionNamerVisitor(module).anonFuncs
    
    // make anonymous functions local
    val module2 = new AnonymousFunction2LocalTransformer(module, anonFuncs.toList).apply()
    
    //show(module2.main, code)
    
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
        val position = n.position._1 + " Line " + n.position._2
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

}
