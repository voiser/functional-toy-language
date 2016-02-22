package intermediate

import ast2._
import runtime.Export

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
    functions: List[CreateFunction],
    classes: List[CreateClass],
    exports: List[Export])
    extends CodegenStep {
  def repr(d: Int) = margin(d) + "Module " + name + " {\n" + showExports(d) + childrepr(d) + "\n" + margin(d) + "}"
  def children = functions ++ classes
  def exportedFunctions = exports // we don't have private and public functions yet
  def showExports(d: Int) =
    if (exports.isEmpty) ""
    else exports.map { e =>
      "Export " + e.name + " " + e.`type` + " " + e.overrides.mkString(" ") }
      .mkString(margin(d+1), "\n" + margin(d+1), "\n" + margin(d))
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
  def repr(d: Int) = margin(d) + "CreateConstant " + name + " " + node + " " + ty
}
case class CreateCapture(
    name: String, 
    ty: CodegenType)
    extends SingleCodegenStep {
  def repr(d: Int) = margin(d) + "CreateCapture " + name + " " + ty
}
case class CreateExtern(
    name: String, 
    fullname: String,
    ty: CodegenType)
    extends SingleCodegenStep {
  def repr(d: Int) = margin(d) + "CreateExtern " + name + " " + ty + " " + fullname
}
case class CreateLocal(
    name: String, 
    index: Int,
    ty: CodegenType)
    extends SingleCodegenStep {
  def repr(d: Int) = margin(d) + "CreateLocal " + index + " " + name + " " + ty
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
case class SBool(
    b: Boolean)
    extends CodeStep {
  def repr(d: Int) = margin(d) + "SBool " + b
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
case class CallDynamic(
    name: String, 
    params: List[CodeStep],
    options: List[String], 
    intypes: List[String])
    extends CodeStep {
  def repr(d: Int) = margin(d) + "CallDynamic " + name + " in " + options.mkString("(", ",", ")") + childr(d)
  def children = params
}
case class SIf(
    cond: CodeStep, 
    exptrue: CodeStep, 
    expfalse: CodeStep)
    extends CodeStep {
  def listr(list: List[CodeStep], d: Int) = " {\n" + listrepr(list, d) + "\n" + margin(d) + "}"
  def repr(d: Int) = margin(d) + "If {\n" + cond.repr(d + 1) + "\n" + margin(d) + 
    "} then {\n" + exptrue.repr(d + 1) + "\n" + margin(d) + 
    "} else {\n" + expfalse.repr(d + 1) + "\n" + margin(d) + "}"
  def children = List()
}
case class CreateClass(
    name: String,
    fields: List[CreateField])
    extends CodegenStep {
  def repr(d: Int) = margin(d) + "Class " + name + " {\n" + childrepr(d) + "\n" + margin(d) + "}"
  def children = fields
}
case class CreateField(
    name: String,
    ty: CodegenType)
    extends CodegenStep {
  def repr(d: Int) = margin(d) + "Field " + name + " " + ty
  def children = List()
}
case class Instance(
    classname: String,
    argtypes: List[CodegenType],
    params: List[CodeStep])
    extends CodeStep {
  def repr(d: Int) = margin(d) + "Instantiate " + classname + childr(d)
  def children = params
}
case class InstanceField(
    owner: CodeStep,
    classname: String,
    fieldname: String)
    extends CodeStep {
  def repr(d: Int) = margin(d) + "InstanceField " + classname + " " + fieldname + " {\n" + childrepr(d) + "\n" + margin(d) + "}"
  def children = List(owner)
}
case class NewAnon(
    name: String,
    captures: List[CodegenStep])
    extends CodeStep {
  def repr(d: Int) = margin(d) + "NewAnon "  + name + " {\n" + childrepr(d) + "\n" + margin(d) + "}"
  def children = captures
}
case class Match(
    local: Int,
    what: CodegenStep,
    klass: String,
    params: List[(String, Int)],
    exptrue: CodeStep,
    expfalse: CodeStep)
    extends CodeStep {
  def repr(d: Int) = margin(d) + "Match " + local + " " + klass + " " + params.mkString("(", ",", ")") + " {\n" + childrepr(d) + "\n" + margin(d) +
    "} then {\n" + exptrue.repr(d + 1) + "\n" + margin(d) +
    "} else {\n" + expfalse.repr(d + 1) + "\n" + margin(d) + "}"
  def children = List(what)
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
      name: String, 
      funcname: String) = {
    
    val destname = unit.module.name + "/" + funcname
    val local = flocal(name, allLocals)
    val params = unit.unitFunctions.find { f => f.name == funcname } match {
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
  
  def sym(symbol: String) = symbol.split("/").last 
 
  def genfunction(unit: CompilationUnit, function: CompilationUnitFunction) = {
    val name = function.name
    val arity = function.root.params.length
    val constants = function.constants.map { x => CreateConstant(x._1, codegenType(x._2.ty), x._2) }
    val captures = function.captures.map { x => CreateCapture(x.name, codegenType(x.ty)) }
    val externs = function.externs.map { x =>
      val (node, name, symbol, ty) = x
      val s = sym(symbol)
      if (s == function.name) null
      else CreateExtern(s, symbol, codegenType(ty))
    }.filterNot(_ == null)
    val locals = function.locals.map { x => CreateLocal(x._1, x._3, codegenType(x._2.ty)) }
    val allLocals = (function.locals ++ function.params).distinct
    
    val instantiations = function.root.value.children.collect {
      case NDef(name, v: NFn) if v.isOverride == null =>
        val destname = unit.module.name + "/" + v.name
        val local = flocal(name, allLocals)
        Instantiate(destname, local)
      case NDef(name, v: NFn) if v.isOverride != null =>
        val destname = unit.module.name + "/" + v.name
        val name1 = v.isOverride._1.localname + "$" + name
        val local = flocal(name1, allLocals)
        Instantiate(destname, local)
    }
    val initializations = function.root.value.children.collect {
      case NDef(name, v: NFn) if v.isOverride == null => createInitialize(unit, function, allLocals, captures, name, v.name)
      case NDef(name, v: NFn) if v.isOverride != null =>
        val name1 = v.isOverride._1.localname + "$" + name
        createInitialize(unit, function, allLocals, captures, name1, v.name)
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
    case NDef(name, v : NBool) => Nop()
    case x : NForward => Nop()
    
    case NDef(name, v) =>
      val local = flocal(name, allLocals)
      StoreLocal(local, name, translate(unit, function, allLocals, externs, captures, v))
          
    case x @ NRef(name) =>
      x.over match {
        case None =>
          findlocal(name, allLocals) match {
            case Some((name, _, i)) => Local(i)
            case None => captures.find(_.name == x.name) match {
              case Some(CreateCapture(name, ty)) => Capture(name)
              case None => Constant(name)
            } 
          }
          
        case Some(o) => Extern(sym(o.fullname))
      }
      
    case NInt(v) => SInt(v)
    case NFloat(f) => SFloat(f)
    case NString(s) => SString(s)
    case NBool(b) => SBool(b)

    case x @ NApply(fname, args) =>
      val params = args.map { x => translate(unit, function, allLocals, externs, captures, x) }
      (x.over, x.dynamicOver) match {
        
        case (None, null) =>
          findlocal(fname, allLocals) match {
            case Some((name, _, i)) => CallLocal(i, fname, params)
            case _ => CallConstant(fname, params)
          }
        
        case (None, x) =>
          val intypes = x.map { o => 
            o.ts.tpe match {
              case Tyfn(in, out) => 
                def rep(t: Ty) = t match {
                  case Tycon(name, _) => name
                  case _ => throw new RuntimeException("Please fill me")
                }
                in.map(rep).mkString(";")
              case z => throw new RuntimeException(fname + " should be a function but its type is " + z.repr) 
            }
          }
          CallDynamic(fname, params, x.map(_.fullname), intypes)
        
        case (Some(o), _) =>
          val s = sym(o.fullname)
          findlocal(s, allLocals) match {
            case Some((name, _, i)) => CallLocal(i, s, params)
            case None => CallExtern(s, params)
          }
      }
       
    case x @ NIf(cond, extrue, exfalse) =>
      val c = translate(unit, function, allLocals, externs, captures, cond)
      val t = translate(unit, function, allLocals, externs, captures, extrue)
      val f = translate(unit, function, allLocals, externs, captures, exfalse)
      SIf(c, t, f)
      
    case x @ NRefAnon(name) =>
      unit.unitFunctions.find(f => f.name == name) match {
        case None => throw new RuntimeException("Can't locate anonymous function " + name + ". This is a compiler bug")
        case Some(cuf) =>
          val args = cuf.captures.map { cap => translate(unit, function, allLocals, externs, captures, cap) }
          NewAnon(unit.module.name + "/" + name, args)
      }

    case x @ NInstantiation(cname, args) =>
      x.env.getClass(cname) match {
        case None => throw new RuntimeException("This is a compiler bug")
        case Some(k) =>
          val params = args.map { x => translate(unit, function, allLocals, externs, captures, x) }
          Instance(k.fullname, k.fields.toList.map{f => codegenType(f.ty)}, params)
      }

    case x @ NField(owner, fname) =>
      val o = translate(unit, function, allLocals, externs, captures, owner)
      InstanceField(o, x.klass.fullname, fname)

    case x @ NMatch(source, pattern, exptrue, expfalse) =>
      val s = translate(unit, function, allLocals, externs, captures, source)
      val t = translate(unit, function, allLocals, externs, captures, exptrue)
      val f = translate(unit, function, allLocals, externs, captures, expfalse)
      pattern match {
        case x : PVar => throw new RuntimeException("This match is not valid")
        case PClass(_, name, params, vname) =>
          val klass = unit.classes.find { k => k.localname == name }.get
          val pars = params.map { p =>
            p match {
              case PVar(_, pn) => flocal(pn, allLocals)
              case _ => throw new RuntimeException("This is a compiler bug")
            }
          }

          val fields2pars = klass.fields.map(_.name) zip pars
          Match(flocal(vname, allLocals), s, klass.fullname, fields2pars.toList, t, f)
      }

    case _ => Nop()
  }

  def genclass(unit: CompilationUnit, k: Klass) = {
    val fields = k.fields.toList.map(f => CreateField(f.name, codegenType(f.ty)))
    CreateClass(k.localname, fields)
  }

  def genExports(unit: CompilationUnit) = {
    val functionExports = unit.unitFunctions.map { uf =>
      val over = uf.root.isOverride
      if (over != null) (uf.root.defname, uf.unit.module.name + "." + uf.name)
      else (uf.root.defname, uf.unit.module.name + "." + uf.name)
    }.groupBy(_._1)
      .map { x =>
        val name = x._1
        unit.module.main.env.get(name) match {
          case Some(ts) =>
            val ty = ts.tpe.repr
            val overs = x._2.map(z => z._2).toArray
            new Export(name, ty, overs)
          case None => null
        }
      }.filterNot(_ == null)
      .toList

    val classExports : List[Export] = unit.classes.map { k =>
      new Export(k.name, k.ctor.repr, Array())
    }

    functionExports ++ classExports
  }

  def codegen(unit: CompilationUnit) = {
    val functions = unit.unitFunctions.map { uf =>
      genfunction(unit, uf)
    }
    val classes = unit.classes.map { k =>
      genclass(unit, k)
    }
    val exports = genExports(unit)

    CreateModule(unit.filename, functions, classes, exports)
  }
}
