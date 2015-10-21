package ast2

import org.objectweb.asm._
import org.objectweb.asm.Opcodes._

/**
 * @author david
 */


/**
 * Utility class to measure the max stack depth  
 */
class Stack {
  
  var depth = 0;
  var maxdepth = 0;
  
  def push { 
    depth = depth + 1
    if (depth > maxdepth) maxdepth = depth  
  }
  
  def pop {
    depth = depth - 1
    if (depth < 0) throw new RuntimeException("Stack depth < 0 -> This is a bug!")
  }
}

/**
 * Models a single function 
 * It will be compiled to a Java class
 */
class CompilationUnitFunction(
    val unit: CompilationUnit, 
    val root: NFn,
    val name: String, 
    val className: String, 
    val slashedName: String, 
    val constants: List[(String, Node)], 
    val params: List[(String, Node, Int)], 
    val locals: List[(String, Node, Int)], 
    val externs: List[(String, Ty)],
    val captures: List[NRef]
)

/**
 * Models a set of functions
 * It will be compiled to a Java package
 */
class CompilationUnit(
  val filename: String,
  val module: NModule, 
  val functions: List[Function], 
  val externs: List[Extern]
) {
  
  // Generates all function units contained in this set
  def unitFunctions = {
    functions.map { f =>
      val root = f.function
      val name = f.function.name
      val captures = f.captures
      
      //println("Hi, I'm " + name + " and my captures are " + captures)
      
      val className = (module.name + "." + name)
      val slashedName = className.replace(".", "/")
    
      val constants: List[(String, Node)] = root.value.children.collect {
        case NDef(name, v : NInt) => (name, v)
        case NDef(name, v : NFloat) => (name, v)
        case NDef(name, v : NString) => (name, v)
      }
      
      val localmap = scala.collection.mutable.Map[String, Int]()
      localmap.put(name, 0) // In the JVM, "this" is local 0 
      var l = 0
      
      val myself = (name, root, 0)
      
      val params: List[(String, Node, Int)] = root.params.collect {
        case x @ NFnArg(name, ty) => 
          l = l + 1
          localmap.put(name, l)
          (name, x, l)
      }
      
      val locals: List[(String, Node, Int)] = myself :: params ++ root.value.children.collect {
        case NDef(name, v : NApply) => 
          l = l + 1
          localmap.put(name, l)
          (name, v, l)
        case NDef(name, v : NFn) => 
          l = l + 1
          localmap.put(name, l)
          (name, v, l)
        case NDef(name, v : NRef) =>
          localmap.get(v.name) match {
            case Some(int) =>
              localmap.put(name, int)
              (name, v, int)
            case None => throw new CodegenException("Can't find '" + v.name + "' in local map")
          }
      }
      
      val exts: List[(String, Ty)] = externs.find { x => x.function.name == root.name } match {
        case Some(Extern(node, names)) =>
          names.map { name => (name, node.env.get(name).get.tpe) }   
        case None => List()
      }
      
      new CompilationUnitFunction(this, root, name, className, slashedName, constants, params, locals, exts, captures)
    }
  }
}

/**
 * 
 */
object Codegen2 {

  val TRUE   = "runtime/True"
  val FUNC   = "runtime/Func"
  val THING  = "runtime/Thing"
  val JFUNC  = "L" + FUNC + ";"
  val JTHING = "L" + THING + ";"

  def superclass(ty: Ty) = ty match {
    case Tyfn(_, _) => FUNC
    case _ => THING
  }

  def jsuperclass(ty: Ty) = ty match {
    case Tyfn(_, _) => JFUNC
    case _ => JTHING
  }
  
  def addConstants(
      cw: ClassWriter, 
      uf: CompilationUnitFunction) {
    
    uf.constants.foreach { x =>  
      val fv = cw.visitField(ACC_PUBLIC, x._1, JTHING, null, null);
      fv.visitEnd();
    }
    
    uf.captures.foreach { x => 
      //println ("Adding capture field " + x.name)
      val javaty = jsuperclass(x.ty)
      val fv = cw.visitField(ACC_PUBLIC, x.name, javaty, null, null);
      fv.visitEnd();
    }
    
    uf.externs.foreach { x =>
      val name = x._1
      val ty = x._2
      val javaty = jsuperclass(ty)
      val fv = cw.visitField(ACC_PUBLIC, name, javaty, null, null);
      fv.visitEnd();
    }
  }
  
  def initConstants(
      mv: MethodVisitor, 
      stack: Stack,
      uf: CompilationUnitFunction) {
    
    uf.constants.foreach { x =>
      val name = x._1
      val node = x._2
      node match {
        case NInt(i) =>
          mv.visitVarInsn(ALOAD, 0)
          stack.push
          mv.visitTypeInsn(NEW, "runtime/Int")
          stack.push
          mv.visitInsn(DUP)
          stack.push
          mv.visitIntInsn(BIPUSH, i)
          stack.push
          mv.visitMethodInsn(INVOKESPECIAL, "runtime/Int", "<init>", "(I)V", false)
          stack.pop
          stack.pop
          mv.visitFieldInsn(PUTFIELD, uf.slashedName, name, JTHING)
          stack.pop
          stack.pop

        case NFloat(f) =>
          mv.visitVarInsn(ALOAD, 0);
          stack.push
          mv.visitTypeInsn(NEW, "runtime/Float");
          stack.push
          mv.visitInsn(DUP);
          stack.push
          mv.visitLdcInsn(new java.lang.Float(f));
          stack.push
          mv.visitMethodInsn(INVOKESPECIAL, "runtime/Float", "<init>", "(F)V", false);
          stack.pop
          stack.pop
          mv.visitFieldInsn(PUTFIELD, uf.slashedName, name, JTHING)
          stack.pop
          stack.pop

        case _ => throw new CodegenException("Can't init constant " + x)
      }
    }
    
    uf.externs.foreach { pair =>
      val name = pair._1
      val ty = pair._2
      val fullname = uf.root.env.getFull(name)
      val javaclass = jsuperclass(ty)
      mv.visitVarInsn(ALOAD, 0);
      stack.push
      mv.visitTypeInsn(NEW, fullname);
      stack.push
      mv.visitInsn(DUP);
      stack.push
      mv.visitMethodInsn(INVOKESPECIAL, fullname, "<init>", "()V", false);
      stack.pop
      mv.visitFieldInsn(PUTFIELD, uf.slashedName, name, javaclass);
      stack.pop
      stack.pop
    }
  }
  
  def addConstructor(
        cw: ClassWriter, 
        uf: CompilationUnitFunction) {
    val ncaptures = uf.captures.length
    val args = JTHING * ncaptures 
    
    val mv = cw.visitMethod(ACC_PUBLIC, "<init>", "(" + args + ")V", null, null)
    mv.visitCode()
    
    mv.visitVarInsn(ALOAD, 0)
    mv.visitMethodInsn(INVOKESPECIAL, FUNC, "<init>", "()V", false)

    val stack = new Stack()
    initConstants(mv, stack, uf)
    
    var i = 1
    uf.captures.foreach { capture =>
      mv.visitVarInsn(ALOAD, 0)
      stack.push
      mv.visitVarInsn(ALOAD, i)
      stack.push
      val javaty = jsuperclass(capture.ty)
      val ty = superclass(capture.ty)
      mv.visitTypeInsn(CHECKCAST, ty);
      mv.visitFieldInsn(PUTFIELD, uf.slashedName, capture.name, javaty);
      stack.pop
      stack.pop
    }
    
    mv.visitInsn(RETURN)

    mv.visitMaxs(stack.maxdepth + 1, ncaptures + 1)
    mv.visitEnd()
  }
  
  def findlocal(name: String, locals: List[(String, Node, Int)]) =
    locals.find(p => p._1 == name) 
  
  def add(
      uf: CompilationUnitFunction, 
      mv: MethodVisitor, 
      ex: Node, 
      stack: Stack) {
      
      val allLocals = uf.params ++ uf.locals
    
      ex match {
        case NDef(name, v : NInt) =>
        case NDef(name, v : NFloat) =>
        case NDef(name, v : NString) =>
        case NDef(name, v : NRef) =>

        case NDef(name, v : NFn) =>
          val destname = uf.unit.module.name + "/" + v.name
          val local = findlocal(name, allLocals).getOrElse(throw new RuntimeException("No local for " + name))._3
          mv.visitTypeInsn(NEW, destname);
          stack.push
          mv.visitInsn(DUP);
          stack.push
          
          val ncaptures = uf.unit.unitFunctions.find { f => f.name == v.name } match {
            case Some(u) =>
              val captures = u.captures
              captures.foreach { x => 
                add(uf, mv, x, stack)    
              }
              captures.length
              
            case None => throw new RuntimeException("Ouch! This is a compiler bug")
          }
          
          val j = JTHING * ncaptures
          
          mv.visitMethodInsn(INVOKESPECIAL, destname, "<init>", "(" + j + ")V", false);          
          mv.visitTypeInsn(CHECKCAST, FUNC);
          mv.visitVarInsn(ASTORE, local)
          stack.pop
          
        case NDef(name, v : NApply) =>
          val local = findlocal(name, allLocals).getOrElse(throw new RuntimeException("No local for " + name))._3
          add(uf, mv, v, stack)
          //mv.visitTypeInsn(CHECKCAST, FUNC);
          mv.visitInsn(DUP)
          stack.push
          mv.visitVarInsn(ASTORE, local)
          stack.pop
          
        case x @ NRef(name) =>
          val ty = jsuperclass(ex.ty)
          val l = findlocal(name, allLocals) 
          l match {
            case Some((name, _, i)) =>
              // It's a local 
              mv.visitVarInsn(ALOAD, i);
              stack.push
                
            case _ =>
              // It's a field
              mv.visitVarInsn(ALOAD, 0);
              mv.visitFieldInsn(GETFIELD, uf.slashedName, name, ty);
              stack.push
          }
          
        case NInt(v) =>
          mv.visitTypeInsn(NEW, "runtime/Int");
          stack.push
          mv.visitInsn(DUP);
          stack.push
          mv.visitIntInsn(SIPUSH, v);
          stack.push
          mv.visitMethodInsn(INVOKESPECIAL, "runtime/Int", "<init>", "(I)V", false);
          stack.pop
          stack.pop
          
        case x @ NApply(fname, args) =>
          val realname = x.realName
          val javafname = "apply" + args.length
          val javasignature = "(" + (JTHING * args.length) + ")" + JTHING

          //println ("Compiling apply recursive = " + x.isRecursive)
          
          // push the function object
          //if (x.isRecursive) {
          //  mv.visitVarInsn(ALOAD, 0)
          //  stack.push
          //} 
          //else { 
            findlocal(realname, allLocals) match {
              case Some((name, node, i)) =>
                // it's a local
                mv.visitVarInsn(ALOAD, i)
                stack.push
                
              case None =>
                // it's a ref
                mv.visitVarInsn(ALOAD, 0)
                stack.push
                mv.visitFieldInsn(GETFIELD, uf.slashedName, realname, JFUNC)
            }
          //}
          // push the arguments
          args.foreach { arg => 
            add(uf, mv, arg, stack)
          }
          // invoke the function object
          mv.visitMethodInsn(INVOKEVIRTUAL, FUNC, javafname, javasignature, false)
          args.foreach { x => stack.pop }
          
        case x @ NIf(cond, extrue, exfalse) =>
          add(uf, mv, cond, stack)
          mv.visitTypeInsn(INSTANCEOF, TRUE)
          val l1 = new Label()
          mv.visitJumpInsn(IFEQ, l1)
          // true branch
          add(uf, mv, extrue, stack)
          stack.push
          mv.visitInsn(ARETURN)
          // falsew branch
          mv.visitLabel(l1)
          mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null)
          add(uf, mv, exfalse, stack)
          stack.push
      }
  }
  
  def addApply(
      nargs: Int, 
      cw: ClassWriter, 
      uf: CompilationUnitFunction) { 

    val javaname = "apply" + nargs
    val javasign = "(" + (JTHING * nargs) + ")" + JTHING
    
    val mv = cw.visitMethod(ACC_PUBLIC, javaname, javasign, null, null)
    mv.visitCode()
    
    val stack = new Stack()
    (0 to nargs).map { x => stack.push }
    
    uf.root.value.children.foreach { ex => 
      add(uf, mv, ex, stack)
    }

    mv.visitInsn(ARETURN)
    mv.visitMaxs(stack.maxdepth, uf.locals.size + 1)
    mv.visitEnd()
  }
  
  def addEntryPoint(cw: ClassWriter, uf: CompilationUnitFunction) {
    val mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null)
    mv.visitCode()
    mv.visitTypeInsn(NEW, uf.slashedName)
    mv.visitInsn(DUP)
    mv.visitMethodInsn(INVOKESPECIAL, uf.slashedName, "<init>", "()V", false)
    mv.visitMethodInsn(INVOKEVIRTUAL, uf.slashedName, "apply0", "()Lruntime/Thing;", false)
    mv.visitInsn(POP)
    mv.visitInsn(RETURN)
    mv.visitMaxs(2, 1)
    mv.visitEnd()
  }
  
  def genclass(uf: CompilationUnitFunction) = {
    
    //println ("Generating class " + uf.className)

    val cw = new ClassWriter(0)
    cw.visit(V1_7, ACC_PUBLIC + ACC_SUPER, uf.slashedName, null, FUNC, null)
    
    addConstants(cw, uf)
    addConstructor(cw, uf)
    if (uf.root.params.length == 0) addApply(0, cw, uf)
    if (uf.root.params.length == 1) addApply(1, cw, uf)
    if (uf.name == "main") addEntryPoint(cw, uf)
    
    cw.visitEnd()
    (uf.unit.module.name, uf.name, cw.toByteArray())
  }
  
  def codegen(unit: CompilationUnit) = {
    unit.unitFunctions.map { uf =>
      genclass(uf)
    }
  }
}
