package intermediate

import ast2.CompilationUnit
import ast2.CompilationUnitFunction
import ast2.NApply
import ast2.NDef
import ast2.NFloat
import ast2.NFn
import ast2.NForward
import ast2.NIf
import ast2.NInt
import ast2.NRef
import ast2.NString
import ast2.Node
import ast2.Ty
import ast2.Tyfn
import ast2.NRefAnon
import ast2.NDefAnon

abstract class CodegenType
case class CodegenValue() extends CodegenType {
  override def toString() = "VALUE"
}
case class CodegenFunction() extends CodegenType {
  override def toString() = "FUNCTION"
}

abstract class CodegenStep {
  def children: List[CodegenStep]
  def repr(depth: Int): String
  def margin(i: Int) = "  " * i
  def childrepr(depth: Int) = listrepr(children, depth)
  def listrepr(list: List[CodegenStep], depth: Int) = list.map { s => s.repr(depth + 1) }.mkString("\n")
  override def toString() = repr(0)
}
case class CreateModule(
    name: String, 
    functions: List[CreateFunction]) 
    extends CodegenStep {
  def repr(d: Int) = margin(d) + "Module " + name + " {\n" + childrepr(d) + "\n" + margin(d) + "}"
  def children = functions
  def exportedFunctions = functions // we don't have private and public functions yet
}
case class CreateFunction(
    name: String,
    arity: Int,
    constants: List[CreateConstant], 
    captures: List[CreateCapture], 
    externs: List[CreateExtern], 
    locals: List[CreateLocal], 
    instantiations: List[Instantiate], 
    initializations: List[Initialize], 
    code: List[CodeStep], 
    metadata: List[CodegenMetadata])
    extends CodegenStep {
  def repr(d: Int) = margin(d) + "Function " + name + " " + arity + " {\n" + childrepr(d) + "\n" + margin(d) + "}"
  def children = metadata ++ constants ++ captures ++ externs ++ locals ++ instantiations ++ initializations ++ code
}
abstract class SingleCodegenStep extends CodegenStep {
  def children() = List()
}
case class CreateConstant(
    name: String, 
    ty: CodegenType,
    node: Node)
    extends SingleCodegenStep {
  def repr(d: Int) = margin(d) + "Constant " + name + " " + node + " " + ty
}
case class CreateCapture(
    name: String, 
    ty: CodegenType)
    extends SingleCodegenStep {
  def repr(d: Int) = margin(d) + "Capture " + name + " " + ty
}
case class CreateExtern(
    name: String, 
    fullname: String,
    ty: CodegenType)
    extends SingleCodegenStep {
  def repr(d: Int) = margin(d) + "Extern " + name + " " + ty + " " + fullname
}
case class CreateLocal(
    name: String, 
    index: Int,
    ty: CodegenType)
    extends SingleCodegenStep {
  def repr(d: Int) = margin(d) + "Local " + index + " " + name + " " + ty
}
abstract class CodegenMetadata extends CodegenStep
case class MetadataType(
    ty: String)
    extends CodegenMetadata {
  def repr(d: Int) = margin(d) + "MetadataType " + ty
  def children = List()
}
case class Instantiate(
    name: String, 
    index: Int)
    extends SingleCodegenStep {
  def repr(d: Int) = margin(d) + "Instantiate " + index + " " + name
}
case class Initialize(
    name: String, 
    index: Int, 
    params: List[CodegenStep])
    extends SingleCodegenStep {
  def repr(d: Int) = margin(d) + "Initialize " + index + " " + name + " " + params.mkString("(", ", ", ")")
}
abstract class CodeStep extends CodegenStep {
  def childr(d: Int) = " {\n" + childrepr(d) + "\n" + margin(d) + "}"
}
case class Nop() extends CodeStep {
  def repr(d: Int) = margin(d) + "nop"
  def children = List()
}
case class StoreLocal(
    index: Int,
    name: String,
    value: CodeStep)
    extends CodeStep {
  def repr(d: Int) = margin(d) + "StoreLocal " + index + " " + name + childr(d)
  def children = List(value)
}
case class Local(
    index: Int)
    extends CodeStep {
  def repr(d: Int) = margin(d) + "Local " + index
  def children = List()
}
case class Constant(
    name: String)
    extends CodeStep {
  def repr(d: Int) = margin(d) + "Constant " + name
  def children = List()
}
case class Capture(
    name: String)
    extends CodeStep {
  def repr(d: Int) = margin(d) + "Capture " + name
  def children = List()
}
case class Extern(
    name: String)
    extends CodeStep {
  def repr(d: Int) = margin(d) + "Extern " + name
  def children = List()
}
case class SInt(
    i: Int)
    extends CodeStep {
  def repr(d: Int) = margin(d) + "SInt " + i
  def children = List()
}
case class SFloat(
    f: Float)
    extends CodeStep {
  def repr(d: Int) = margin(d) + "SFloat " + f
  def children = List()
}
case class SString(
    s: String)
    extends CodeStep {
  def repr(d: Int) = margin(d) + "SString " + s
  def children = List()
}
case class CallLocal(
    index: Int,
    name: String, 
    params: List[CodeStep])
    extends CodeStep {
  def repr(d: Int) = margin(d) + "CallLocal " + index + " " + name + childr(d)
  def children = params
}
case class CallConstant(
    name: String, 
    params: List[CodeStep])
    extends CodeStep {
  def repr(d: Int) = margin(d) + "CallConstant " + name + childr(d)
  def children = params
}
case class CallExtern(
    name: String, 
    params: List[CodeStep])
    extends CodeStep {
  def repr(d: Int) = margin(d) + "CallExtern " + name + childr(d)
  def children = params
}
case class SIf(
    cond: CodeStep, 
    exptrue: CodeStep, 
    expfalse: CodeStep
    ) extends CodeStep {
  def listr(list: List[CodeStep], d: Int) = " {\n" + listrepr(list, d) + "\n" + margin(d) + "}"
  def repr(d: Int) = margin(d) + "If {\n" + cond.repr(d + 1) + "\n" + margin(d) + 
    "} then {\n" + exptrue.repr(d + 1) + "\n" + margin(d) + 
    "} else {\n" + expfalse.repr(d + 1) + "\n" + margin(d) + "}"
  def children = List()
}
    

object Intermediate {
  
  def codegenType(ty: Ty) = ty match {
    case Tyfn(_, _) => CodegenFunction()
    case _ => CodegenValue()
  }
  
  def findlocal(name: String, locals: List[(String, Node, Int)]) =
    locals.find(p => p._1 == name)
    
  def flocal(name: String, locals: List[(String, Node, Int)]) = 
    findlocal(name, locals).getOrElse(throw new RuntimeException("No local for " + name))._3

  def createInitialize(
      unit: CompilationUnit, 
      function: CompilationUnitFunction,
      allLocals: List[(String, Node, Int)],
      captures: List[CreateCapture],
      name: String) = {
    
    val destname = unit.module.name + "/" + name
    val local = flocal(name, allLocals)
    val params = unit.unitFunctions.find { f => f.name == name } match {
      case None => throw new RuntimeException("Ouch! This is a compiler bug")
      case Some(u) => 
        u.captures.map { x => 
          val l = findlocal(x.name, allLocals) 
          l match {
            case Some((name, _, i)) => Local(i)
            case None => 
              captures.find(_.name == x.name) match {
                case Some(CreateCapture(name, ty)) => Capture(name)
                case None => Constant(x.name)
              }
          }
        }
    }
    Initialize(destname, local, params)
  }
    
  def genfunction(unit: CompilationUnit, function: CompilationUnitFunction) = {
    val name = function.name
    val arity = function.root.params.length
    val constants = function.constants.map { x => CreateConstant(x._1, codegenType(x._2.ty), x._2) }
    val captures = function.captures.map { x => CreateCapture(x.name, codegenType(x.ty)) }
    val externs = function.externs.map { x => CreateExtern(x._1, function.root.env.getFull(x._1).replace(".", "/"), codegenType(x._2)) }
    val locals = function.locals.map { x => CreateLocal(x._1, x._3, codegenType(x._2.ty)) }

    val allLocals = function.params ++ function.locals
    
    val instantiations = function.root.value.children.collect { 
      case NDef(name, v: NFn) =>
        val destname = unit.module.name + "/" + v.name
        val local = flocal(name, allLocals)
        Instantiate(destname, local)
      case a @ NDefAnon(name, _) if (a.env.parent == function.root.value.env) =>
        val destname = unit.module.name + "/" + name
        val local = flocal(name, allLocals)
        Instantiate(destname, local)
      case x @ NRefAnon(name) =>
        val destname = unit.module.name + "/" + name
        val local = flocal(name, allLocals)
        Instantiate(destname, local)
    }
   
    val initializations = function.root.value.children.collect {
      case NDef(name, v: NFn) => createInitialize(unit, function, allLocals, captures, v.name)
      case a @ NDefAnon(name, _) if (a.env.parent == function.root.value.env) => createInitialize(unit, function, allLocals, captures, name)
      case x @ NRefAnon(name) => createInitialize(unit, function, allLocals, captures, name)
    }
    val code = function.root.value.children.map { x =>
      translate(unit, function, allLocals, externs, captures, x) 
    }
    
    val metaType = MetadataType(function.root.ty.repr)
    val metadata = metaType :: List()

    CreateFunction(
        name, 
        arity, 
        constants, 
        captures, 
        externs, 
        locals, 
        instantiations, 
        initializations, 
        code, 
        metadata)
  }
  
  def translate(
      unit: CompilationUnit,
      function: CompilationUnitFunction,
      allLocals: List[(String, Node, Int)],
      externs: List[CreateExtern],
      captures: List[CreateCapture],
      node: Node) : CodeStep = node match {
    
    case NDef(name, v : NInt) => Nop()
    case NDef(name, v : NFloat) => Nop()
    case NDef(name, v : NString) => Nop()
    case NDef(name, v : NRef) => Nop()
    case NDef(name, v : NFn) => Nop()
    case x : NForward => Nop()
    
    case NDef(name, v) =>
      val local = flocal(name, allLocals)
      StoreLocal(local, name, translate(unit, function, allLocals, externs, captures, v))
          
    case x @ NRef(name) =>
      val local = findlocal(name, allLocals) 
      local match {
        case Some((name, _, i)) => Local(i)
        case None => 
          externs.find(_.name == x.name) match {
            case Some(CreateExtern(name, ty, fullname)) => Extern(x.name)
            case None => 
              captures.find(_.name == x.name) match {
                case Some(CreateCapture(name, ty)) => Capture(name)
                case None => Constant(x.name)
              }
          }
      }
      
     case NInt(v) => SInt(v)
     case NFloat(f) => SFloat(f)
     case NString(s) => SString(s)

     case x @ NApply(fname, args) =>
       val realname = x.realName
       val params = args.map { x => translate(unit, function, allLocals, externs, captures, x) }
       val local = findlocal(realname, allLocals)
       local match {
        case Some((name, _, i)) => CallLocal(i, realname, params)
        case _ => 
          externs.find(_.name == realname) match {
            case Some(CreateExtern(name, ty, fullname)) => CallExtern(name, params)
            case None => CallConstant(realname, params)
          }
       }
       
    case x @ NIf(cond, extrue, exfalse) =>
      val c = translate(unit, function, allLocals, externs, captures, cond)
      val t = translate(unit, function, allLocals, externs, captures, extrue)
      val f = translate(unit, function, allLocals, externs, captures, exfalse)
      SIf(c, t, f)
      
    case x @ NRefAnon(name) =>  
      val local = findlocal(name, allLocals) 
      local match {
        case Some((name, _, i)) => Local(i)
        case _ => 
          externs.find(_.name == x.name) match {
            case Some(CreateExtern(name, ty, fullname)) => Extern(x.name)
            case None => Constant(x.name)
          }
      }
      
    case _ => Nop()
  }
  
  def codegen(unit: CompilationUnit) = {
    val functions = unit.unitFunctions.map { uf =>
      genfunction(unit, uf)
    }
    CreateModule(unit.filename, functions) 
  }
}
