package ast2

import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import scala.collection.JavaConverters._

object Main {

  def rootEnv = {
    val env = Env()
    val a = Tyvar("a")
    val b = Tyvar("b")
    
    val int = Typer3.intType
    val float = Typer3.floatType
    val bool = Typer3.boolType
    val unit = Typer3.unitType
    
    val listType = Tycon("List", List(a))
    
    env.put("true", "runtime/True", TypeScheme(List(), bool))
    env.put("false", "runtime/False", TypeScheme(List(), bool))

    env.put("add$a", "runtime/add$a", TypeScheme(List(), Tyfn(List(int, int), int)))
    env.put("add$b", "runtime/add$b", TypeScheme(List(), Tyfn(List(int, float), float)))
    env.put("add$c", "runtime/add$c", TypeScheme(List(), Tyfn(List(float, int), float)))
    env.put("add$d", "runtime/add$d", TypeScheme(List(), Tyfn(List(float, float), float)))
    
    env.put("sub$a", "runtime/sub$a", TypeScheme(List(), Tyfn(List(int, int), int)))
    env.put("sub$b", "runtime/sub$b", TypeScheme(List(), Tyfn(List(int, float), float)))
    env.put("sub$c", "runtime/sub$c", TypeScheme(List(), Tyfn(List(float, int), float)))
    env.put("sub$d", "runtime/sub$d", TypeScheme(List(), Tyfn(List(float, float), float)))

    env.put("times$a", "runtime/times$a", TypeScheme(List(), Tyfn(List(int, int), int)))
    env.put("times$b", "runtime/times$b", TypeScheme(List(), Tyfn(List(int, float), float)))
    env.put("times$c", "runtime/times$c", TypeScheme(List(), Tyfn(List(float, int), float)))
    env.put("times$d", "runtime/times$d", TypeScheme(List(), Tyfn(List(float, float), float)))
    
    env.put("id", "runtime/id", TypeScheme(List(a), Tyfn(List(a), a)))
    env.put("do", "runtime/do_", TypeScheme(List(a, b), Tyfn(List(Tyfn(List(a), b), a), b)))
    env.put("eq", "runtime/eq", TypeScheme(List(a), Tyfn(List(a, a), bool)))
    env.put("puts", "runtime/puts", TypeScheme(List(), Tyfn(List(a), unit)))
    
    env.put("list", "runtime/list_of", TypeScheme(List(a), Tyfn(List(a), listType)))
    env.put("cons", "runtime/cons", TypeScheme(List(a), Tyfn(List(a, listType), listType)))
    env.put("nil",  "runtime/Nil", TypeScheme(List(a), listType))
    
    env
  }

  def process(filename: String, code: String) = {
    
    // Build the CST
    val lexer = new GrammarLexer(new ANTLRInputStream(code))
    val parser = new GrammarParser(new CommonTokenStream(lexer))
    val cst = parser.file()
    
    // Build the AST
    val module = new FirstVisitor(filename).visitFile(cst)
    module.main.name = "main"
        
    // Type the AST
    Typer3.getType(rootEnv, module.main)
    
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
  
  def show(n: Node,code: String) {
    val codelines = code.split("\n")
    
    def show0(n: Node, d: Int) {
    
      def rep(s: String) {
        val position = n.position._1 + " Line " + n.position._2
        val prefix = "  " * d + " " + s + " :: " + n.ty.repr 
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
                  
        case _ =>
          rep(n.toString())
      }
    }
    
    show0(n, 0)
  }

}
