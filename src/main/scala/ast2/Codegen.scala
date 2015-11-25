package ast2

import org.objectweb.asm._
import org.objectweb.asm.Opcodes._
import intermediate._

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
 * Java code generator
 */
object Codegen {
  
  val TRUE   = "runtime/True"
  val FUNC   = "runtime/Func"
  val THING  = "runtime/Thing"
  val JFUNC  = "L" + FUNC + ";"
  val JTHING = "L" + THING + ";"
  
  /**
   * Obtains the Java superclass from a codegen type
   */
  def superclass(ty: CodegenType) = ty match {
    case x : CodegenValue => THING
    case x : CodegenFunction => FUNC
  }
  
  /**
   * Obtains the Java superclass signature from a codegen type
   */
  def jsuperclass(ty : CodegenType) = ty match {
    case x : CodegenValue => JTHING
    case x : CodegenFunction => JFUNC
  }
  
  /**
   * Adds instance fields to a class
   */
  def addConstants(
      cw: ClassWriter,
      module: CreateModule,
      uf: CreateFunction) {
    
    uf.constants.foreach { x =>  
      val fv = cw.visitField(ACC_PUBLIC, x.name, JTHING, null, null);
      fv.visitEnd();
    }
    
    uf.captures.foreach { x => 
      val javaty = jsuperclass(x.ty)
      val fv = cw.visitField(ACC_PUBLIC, x.name, javaty, null, null);
      fv.visitEnd();
    }
    
    uf.externs.foreach { x =>
      val javaty = jsuperclass(x.ty)
      val fv = cw.visitField(ACC_PUBLIC, x.name, javaty, null, null);
      fv.visitEnd();
    }
  }
  
  /**
   * Adds an empty constructor to a class
   */
  def addConstructor(
        cw: ClassWriter, 
        module: CreateModule,
        uf: CreateFunction) {

    val mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null)
    mv.visitCode()
    
    mv.visitVarInsn(ALOAD, 0)
    mv.visitMethodInsn(INVOKESPECIAL, FUNC, "<init>", "()V", false)

    val stack = new Stack()
    initConstants(mv, stack, module, uf)
    
    mv.visitInsn(RETURN)

    mv.visitMaxs(stack.maxdepth + 1, 1)
    mv.visitEnd()
  }
  
  /**
   * Adds constant initialization code to a constructor given by a method visitor
   */
  def initConstants(
      mv: MethodVisitor, 
      stack: Stack,
      module: CreateModule,
      uf: CreateFunction) {
    
    val slashedName = module.name + "/" + uf.name
    
    uf.constants.foreach { x =>
      val name = x.name
      val node = x.node
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
          mv.visitFieldInsn(PUTFIELD, slashedName, name, JTHING)
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
          mv.visitFieldInsn(PUTFIELD, slashedName, name, JTHING)
          stack.pop
          stack.pop

        case _ => throw new CodegenException("Can't init constant " + x)
      }
    }
    
    uf.externs.foreach { pair =>
      val name = pair.name
      val ty = pair.ty
      val fullname = pair.fullname
      val javaclass = jsuperclass(ty)
      mv.visitVarInsn(ALOAD, 0);
      stack.push
      mv.visitTypeInsn(NEW, fullname);
      stack.push
      mv.visitInsn(DUP);
      stack.push
      mv.visitMethodInsn(INVOKESPECIAL, fullname, "<init>", "()V", false);
      stack.pop
      mv.visitFieldInsn(PUTFIELD, slashedName, name, javaclass);
      stack.pop
      stack.pop
    }
  }
  
  /**
   * Adds a capture initialization method
   */
  def addInitializer(
        cw: ClassWriter, 
        module: CreateModule,
        uf: CreateFunction) {
    
    val slashedName = module.name + "/" + uf.name
    val ncaptures = uf.captures.length
    val args = JTHING * ncaptures 
    
    val mv = cw.visitMethod(ACC_PUBLIC, "initialize", "(" + args + ")V", null, null)
    mv.visitCode()
    
    val stack = new Stack()
    
    var i = 1
    uf.captures.foreach { capture =>
      mv.visitVarInsn(ALOAD, 0)
      stack.push
      mv.visitVarInsn(ALOAD, i)
      stack.push
      val javaty = jsuperclass(capture.ty)
      val ty = superclass(capture.ty)
      mv.visitTypeInsn(CHECKCAST, ty);
      mv.visitFieldInsn(PUTFIELD, slashedName, capture.name, javaty);
      stack.pop
      stack.pop
    }
    
    mv.visitInsn(RETURN)

    mv.visitMaxs(stack.maxdepth + 1, ncaptures + 1)
    mv.visitEnd()
  }
  
  /**
   * 
   */
  def instantiate(
      module: CreateModule,
      uf: CreateFunction, 
      mv: MethodVisitor,
      ex: Instantiate,
      stack: Stack) {
    
    mv.visitTypeInsn(NEW, ex.name);
    stack.push
    mv.visitInsn(DUP);
    stack.push
        
    mv.visitMethodInsn(INVOKESPECIAL, ex.name, "<init>", "()V", false);          
    mv.visitTypeInsn(CHECKCAST, FUNC);
    mv.visitVarInsn(ASTORE, ex.index)
    stack.pop
  }
  
  /**
   * 
   */
  def initialize(
      module: CreateModule,
      uf: CreateFunction, 
      mv: MethodVisitor,
      ex: Initialize,
      stack: Stack) {
    
    
    mv.visitVarInsn(ALOAD, ex.index);
    mv.visitTypeInsn(CHECKCAST, ex.name);
        
    ex.params.foreach { x =>
      add(module, uf, mv, x, stack)
    }
    
    val ncaptures = ex.params.length
    val j = JTHING * ncaptures
        
    mv.visitMethodInsn(INVOKEVIRTUAL, ex.name, "initialize", "(" + j + ")V", false);          
    stack.pop
  }
  
  /**
   * 
   */
  def add(
      module: CreateModule,
      uf: CreateFunction, 
      mv: MethodVisitor,
      ex: CodegenStep,
      stack: Stack) {
    
    val sn = slashedName(module, uf)
    
    ex match {
      case x: Nop =>
      
      case SInt(v) =>
        mv.visitTypeInsn(NEW, "runtime/Int");
        stack.push
        mv.visitInsn(DUP);
        stack.push
        mv.visitIntInsn(SIPUSH, v);
        stack.push
        mv.visitMethodInsn(INVOKESPECIAL, "runtime/Int", "<init>", "(I)V", false);
        stack.pop
        stack.pop
        
      case SFloat(f) =>
        mv.visitTypeInsn(NEW, "runtime/Float");
        stack.push
        mv.visitInsn(DUP);
        stack.push
        mv.visitLdcInsn(new java.lang.Float(f));
        stack.push
        mv.visitMethodInsn(INVOKESPECIAL, "runtime/Float", "<init>", "(F)V", false);
        stack.pop
        stack.pop
        
      case SString(s) =>
        mv.visitTypeInsn(NEW, "runtime/Str")
        stack.push
        mv.visitInsn(DUP);
        stack.push
        mv.visitLdcInsn(s)
        stack.push
        mv.visitMethodInsn(INVOKESPECIAL, "runtime/Str", "<init>", "(Ljava/lang/String;)V", false)
        stack.pop
        stack.pop
        
      case Local(i) =>
        mv.visitVarInsn(ALOAD, i);
        stack.push
      
      case Constant(name) =>
        val ty = uf.constants.collectFirst { case CreateConstant(n, t, _) if (name == n) => jsuperclass(t) }
        ty match {
          case None => throw new RuntimeException("I can't find the type of " + name + ". This is a compiler bug")
          case Some(t) =>
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, sn, name, t);
            stack.push
        }
        
      case intermediate.Extern(name) =>
        val ty = uf.externs.collectFirst { case CreateExtern(n, f, t) if (name == n) => jsuperclass(t) }
        ty match {
          case None => throw new RuntimeException("I can't find the type of " + name + ". This is a compiler bug")
          case Some(t) =>
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, sn, name, t);
            stack.push
        }
        
      case StoreLocal(local, _, value) =>
        add(module, uf, mv, value, stack)
        mv.visitInsn(DUP) // do I really need this? 
        stack.push
        mv.visitVarInsn(ASTORE, local)
        stack.pop
        
      case CallLocal(local, _, args) =>
        val javafname = "apply" + args.length
        val javasignature = "(" + (JTHING * args.length) + ")" + JTHING
        mv.visitVarInsn(ALOAD, local)
        stack.push
        args.foreach { arg => 
            add(module, uf, mv, arg, stack)
        }
        mv.visitMethodInsn(INVOKEVIRTUAL, FUNC, javafname, javasignature, false)
        args.foreach { x => stack.pop }
        
      case CallConstant(name, args) =>
        val javafname = "apply" + args.length
        val javasignature = "(" + (JTHING * args.length) + ")" + JTHING
        mv.visitVarInsn(ALOAD, 0)
        stack.push
        mv.visitFieldInsn(GETFIELD, sn, name, JFUNC)
        args.foreach { arg => 
            add(module, uf, mv, arg, stack)
        }
        mv.visitMethodInsn(INVOKEVIRTUAL, FUNC, javafname, javasignature, false)
        args.foreach { x => stack.pop }
        
      case CallExtern(name, args) =>
        val javafname = "apply" + args.length
        val javasignature = "(" + (JTHING * args.length) + ")" + JTHING
        mv.visitVarInsn(ALOAD, 0)
        stack.push
        mv.visitFieldInsn(GETFIELD, sn, name, JFUNC)
        args.foreach { arg => 
            add(module, uf, mv, arg, stack)
        }
        mv.visitMethodInsn(INVOKEVIRTUAL, FUNC, javafname, javasignature, false)
        args.foreach { x => stack.pop }
     
      case SIf(cond, extrue, exfalse) =>
        add(module, uf, mv, cond, stack)
        mv.visitTypeInsn(INSTANCEOF, TRUE)
        val l1 = new Label()
        mv.visitJumpInsn(IFEQ, l1)
        add(module, uf, mv, extrue, stack)
        stack.push
        mv.visitInsn(ARETURN)
        mv.visitLabel(l1)
        mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null)
        add(module, uf, mv, exfalse, stack)
        stack.push
    }
  }
  
  /**
   * Adds the function entry point
   */
  def addApply(
      nargs: Int, 
      cw: ClassWriter,
      module: CreateModule,
      uf: CreateFunction) { 

    val javaname = "apply" + nargs
    val javasign = "(" + (JTHING * nargs) + ")" + JTHING
    
    val mv = cw.visitMethod(ACC_PUBLIC, javaname, javasign, null, null)
    mv.visitCode()
    
    val stack = new Stack()
    (0 to nargs).map { x => stack.push }
    
    uf.instantiations.foreach { x => 
      instantiate(module, uf, mv, x, stack)
    }

    uf.initializations.foreach { x => 
      initialize(module, uf, mv, x, stack)  
    }
    
    uf.code.foreach { ex =>
      add(module, uf, mv, ex, stack)
    }
    
    mv.visitInsn(ARETURN)
    mv.visitMaxs(stack.maxdepth, uf.locals.size + 1)
    mv.visitEnd()
  }
  
  /**
   * Adds the public static void main method
   */
  def addEntryPoint(cw: ClassWriter, module: CreateModule, uf: CreateFunction) {
    val mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null)
    mv.visitCode()
    mv.visitTypeInsn(NEW, slashedName(module, uf))
    mv.visitInsn(DUP)
    mv.visitMethodInsn(INVOKESPECIAL, slashedName(module, uf), "<init>", "()V", false)
    mv.visitMethodInsn(INVOKEVIRTUAL, slashedName(module, uf), "apply0", "()Lruntime/Thing;", false)
    mv.visitInsn(POP)
    mv.visitInsn(RETURN)
    mv.visitMaxs(2, 1)
    mv.visitEnd()
  }
  
  /**
   * Adds a static field containing the function metadata 
   */
  def addMetadata(cw: ClassWriter, module: CreateModule, uf: CreateFunction) {
    val fv = cw.visitField(ACC_PUBLIC + ACC_STATIC, "type", "Ljava/lang/String;", null, null);
    fv.visitEnd();

    val mv = cw.visitMethod(ACC_STATIC, "<clinit>", "()V", null, null);
    mv.visitCode();
    mv.visitLdcInsn(uf.tyrepr);
    mv.visitFieldInsn(PUTSTATIC, slashedName(module, uf), "type", "Ljava/lang/String;");
    
    if (uf.name == "main") addExports(cw, mv, module, uf)
    
    mv.visitInsn(RETURN);
    mv.visitMaxs(6, 0);
    mv.visitEnd();
  }
  
  /**
   * Adds a static field containing the exported functions
   */
  def addExports(cw: ClassWriter, mv: MethodVisitor, module: CreateModule, uf: CreateFunction) {
    val fv = cw.visitField(ACC_PUBLIC + ACC_STATIC, "exports", "[Lruntime/Export;", null, null);
    fv.visitEnd();
    
    val nExports = module.exportedFunctions.length
    
    mv.visitIntInsn(SIPUSH, nExports);
    mv.visitTypeInsn(ANEWARRAY, "runtime/Export");
    
    ((0 to nExports-1) zip module.exportedFunctions).foreach { case (i, x) => 
      mv.visitInsn(DUP);
      mv.visitIntInsn(SIPUSH, i);
      mv.visitTypeInsn(NEW, "runtime/Export");
      mv.visitInsn(DUP);
      mv.visitLdcInsn(x.name);
      mv.visitMethodInsn(INVOKESPECIAL, "runtime/Export", "<init>", "(Ljava/lang/String;)V", false);
      mv.visitInsn(AASTORE);
    }
    
    mv.visitFieldInsn(PUTSTATIC, slashedName(module, uf), "exports", "[Lruntime/Export;");
  }
  
  /**
   * Guess what
   */
  def slashedName(module: CreateModule, uf: CreateFunction) = module.name + "/" + uf.name
  
  /**
   * Generates a Java class for a function in a module
   */
  def genclass(module: CreateModule, f: CreateFunction) = {
    
    //println ("Generating class " + uf.className)

    val cw = new ClassWriter(0)
    cw.visit(V1_7, ACC_PUBLIC + ACC_SUPER, slashedName(module, f), null, FUNC, null)
    
    addConstants(cw, module, f)
    addConstructor(cw, module, f)
    addInitializer(cw, module, f)
    addApply(f.arity, cw, module, f)
    addMetadata(cw, module, f)
    if (f.name == "main") {
      addEntryPoint(cw, module, f)
    }
    cw.visitEnd()
    cw.toByteArray()
    
    (module.name, f.name, cw.toByteArray())
  }
  
  def codegen(module: CreateModule) = {
    module.functions.map { f =>
      genclass(module, f)
    }
  }
}