package ast2

import org.objectweb.asm._
import org.objectweb.asm.Opcodes._
import intermediate._

/**
 * Utility class to measure the max stack depth  
 */
class Stack {
  
  var depth = 0
  var maxdepth = 0
  
  def push {
    push(1)
  }
  
  def push(x: List[_]) {
    push(x.size)
  }
  
  def push(i: Int) {
    depth = depth + 1
    if (depth > maxdepth) maxdepth = depth  
  }
  
  def pop {
    pop (1)
  }
  
  def pop(x : List[_]) {
    pop (x.size)
  }
  
  def pop(i: Int) {
    depth = depth - i
    if (depth < 0) throw new RuntimeException("Stack depth < 0 -> This is a bug!")
  }
}

/**
 * Java code generator
 */
object Codegen {

  val BOOL   = "runtime/Bool"
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
      val fv = cw.visitField(ACC_PUBLIC, x.name, JTHING, null, null)
      fv.visitEnd();
    }
    
    uf.captures.foreach { x => 
      val javaty = jsuperclass(x.ty)
      val fv = cw.visitField(ACC_PUBLIC, x.name, javaty, null, null)
      fv.visitEnd();
    }
    
    uf.externs.foreach { x =>
      val javaty = jsuperclass(x.ty)
      val fv = cw.visitField(ACC_PUBLIC, x.name, javaty, null, null)
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
          mv.visitVarInsn(ALOAD, 0)
          stack.push
          mv.visitTypeInsn(NEW, "runtime/Float")
          stack.push
          mv.visitInsn(DUP)
          stack.push
          mv.visitLdcInsn(new java.lang.Float(f))
          stack.push
          mv.visitMethodInsn(INVOKESPECIAL, "runtime/Float", "<init>", "(F)V", false)
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
      mv.visitVarInsn(ALOAD, 0)
      stack.push
      mv.visitTypeInsn(NEW, fullname)
      stack.push
      mv.visitInsn(DUP)
      stack.push
      mv.visitMethodInsn(INVOKESPECIAL, fullname, "<init>", "()V", false)
      stack.pop
      mv.visitFieldInsn(PUTFIELD, slashedName, name, javaclass)
      stack.pop
      stack.pop
    }

    /*
    uf.externs.foreach { pair =>
      val name = pair.name
      val ty = pair.ty
      val fullname = pair.fullname
      val javaclass = jsuperclass(ty)
      mv.visitVarInsn(ALOAD, 0)
      stack.push
      mv.visitFieldInsn(GETFIELD, slashedName, name, javaclass)
      mv.visitTypeInsn(CHECKCAST, fullname)
      mv.visitMethodInsn(INVOKEVIRTUAL, fullname, "initialize", "()V", false)
      stack.pop
    }
    */
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
      i = i + 1
      stack.push
      val javaty = jsuperclass(capture.ty)
      val ty = superclass(capture.ty)
      mv.visitTypeInsn(CHECKCAST, ty)
      mv.visitFieldInsn(PUTFIELD, slashedName, capture.name, javaty)
      stack.pop
      stack.pop
    }

    //initConstants(mv, stack, module, uf)

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
    
    mv.visitTypeInsn(NEW, ex.name)
    stack.push
    mv.visitInsn(DUP)
    stack.push
        
    mv.visitMethodInsn(INVOKESPECIAL, ex.name, "<init>", "()V", false)
    mv.visitTypeInsn(CHECKCAST, FUNC)
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
    
    
    mv.visitVarInsn(ALOAD, ex.index)
    mv.visitTypeInsn(CHECKCAST, ex.name)
        
    ex.params.foreach { x =>
      add(module, uf, mv, x, stack)
    }
    
    val ncaptures = ex.params.length
    val j = JTHING * ncaptures
        
    mv.visitMethodInsn(INVOKEVIRTUAL, ex.name, "initialize", "(" + j + ")V", false)
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
        mv.visitTypeInsn(NEW, "runtime/Int")
        stack.push
        mv.visitInsn(DUP)
        stack.push
        mv.visitLdcInsn(new Integer(v))
        stack.push
        mv.visitMethodInsn(INVOKESPECIAL, "runtime/Int", "<init>", "(I)V", false)
        stack.pop
        stack.pop
        
      case SFloat(f) =>
        mv.visitTypeInsn(NEW, "runtime/Float")
        stack.push
        mv.visitInsn(DUP)
        stack.push
        mv.visitLdcInsn(new java.lang.Float(f))
        stack.push
        mv.visitMethodInsn(INVOKESPECIAL, "runtime/Float", "<init>", "(F)V", false)
        stack.pop
        stack.pop
        
      case SString(s) =>
        mv.visitTypeInsn(NEW, "runtime/Str")
        stack.push
        mv.visitInsn(DUP)
        stack.push
        mv.visitLdcInsn(s)
        stack.push
        mv.visitMethodInsn(INVOKESPECIAL, "runtime/Str", "<init>", "(Ljava/lang/String;)V", false)
        stack.pop
        stack.pop

      case SBool(b) =>
        mv.visitTypeInsn(NEW, "runtime/Bool")
        stack.push
        mv.visitInsn(DUP)
        stack.push
        mv.visitLdcInsn(b)
        stack.push
        mv.visitMethodInsn(INVOKESPECIAL, "runtime/Bool", "<init>", "(Z)V", false)
        stack.pop
        stack.pop

      case Local(i) =>
        mv.visitVarInsn(ALOAD, i)
        stack.push
      
      case Constant(name) =>
        val ty = uf.constants.collectFirst { case CreateConstant(n, t, _) if name == n => jsuperclass(t) }
        ty match {
          case None => throw new CodegenException("In class " + uf.name + ", I can't find the type of " + name + ". This is a compiler bug")
          case Some(t) =>
            mv.visitVarInsn(ALOAD, 0)
            mv.visitFieldInsn(GETFIELD, sn, name, t)
            stack.push
        }
        
      case Capture(name) =>
        val ty = uf.captures.collectFirst { case CreateCapture(n, t) if name == n => jsuperclass(t) }
        ty match {
          case None => throw new CodegenException("In class " + uf.name + ", I can't find the type of " + name + ". This is a compiler bug")
          case Some(t) =>
            mv.visitVarInsn(ALOAD, 0)
            mv.visitFieldInsn(GETFIELD, sn, name, t)
            stack.push
        }  
        
      case intermediate.Extern(name) =>
        val ty = uf.externs.collectFirst { case CreateExtern(n, f, t) if name == n => jsuperclass(t) }
        ty match {
          case None => throw new RuntimeException("I can't find the type of " + name + ". This is a compiler bug")
          case Some(t) =>
            mv.visitVarInsn(ALOAD, 0)
            mv.visitFieldInsn(GETFIELD, sn, name, t)
            stack.push
        }
        
      case StoreLocal(local, _, value) =>
        add(module, uf, mv, value, stack)
        stack.push
        mv.visitVarInsn(ASTORE, local)
        stack.pop
        
      case CallLocal(local, _, args) =>
        val javafname = "apply" + args.length
        val javasignature = "(" + (JTHING * args.length) + ")" + JTHING
        mv.visitVarInsn(ALOAD, local)
        stack.push
        mv.visitTypeInsn(CHECKCAST, FUNC)
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
     
      case CallDynamic(_, args, options, intypes) =>
        def pushArray(l: List[_], jt: String) {
          mv.visitLdcInsn(new Integer(l.length))
          stack.push
          mv.visitTypeInsn(ANEWARRAY, jt)
          stack.push
          (0 to l.length-1).foreach { case i => 
            mv.visitInsn(DUP)
            stack.push
            mv.visitLdcInsn(new Integer(i))
            stack.push
            mv.visitLdcInsn(l(i))
            stack.push
            mv.visitInsn(AASTORE)
            stack pop 3
          }
        }
        
        pushArray(options.map{_.replace("/", ".")}, "java/lang/String")
        pushArray(intypes, "java/lang/String")

        {
          mv.visitLdcInsn(new Integer(args.length))
          stack.push
          mv.visitTypeInsn(ANEWARRAY, THING)
          stack.push
          (0 to args.length-1).foreach { case i => 
            mv.visitInsn(DUP)
            stack.push
            mv.visitLdcInsn(new Integer(i))
            stack.push
            add(module, uf, mv, args(i), stack)
            //mv.visitVarInsn(ALOAD, i + 1)
            stack.push
            mv.visitInsn(AASTORE)
            stack pop 3
          }
        }
        mv.visitMethodInsn(INVOKESTATIC, "runtime/DynamicDispatch", "dispatch", "([Ljava/lang/String;[Ljava/lang/String;[Lruntime/Thing;)Lruntime/Thing;", false)
        stack pop args
        stack.push
      
      case SIf(cond, extrue, exfalse) =>
        add(module, uf, mv, cond, stack)
        mv.visitTypeInsn(CHECKCAST, BOOL)
        mv.visitFieldInsn(GETFIELD, BOOL, "b", "Z")
        val l1 = new Label()
        mv.visitJumpInsn(IFEQ, l1)
        add(module, uf, mv, extrue, stack)
        stack.push
        mv.visitInsn(ARETURN)
        mv.visitLabel(l1)
        mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null)
        add(module, uf, mv, exfalse, stack)
        stack.push

      case Instance(cname, argtypes, args) =>
        val signature = argtypes.map(a => jsuperclass(a)).mkString("(", "", ")V")
        mv.visitTypeInsn(NEW, cname)
        stack.push
        mv.visitInsn(DUP)
        stack.push
        args.foreach { arg =>
          add(module, uf, mv, arg, stack)
        }
        mv.visitMethodInsn(INVOKESPECIAL, cname, "<init>", signature, false)
        stack.pop
        stack.pop

      case InstanceField(owner, classname, fieldname) =>
        add(module, uf, mv, owner, stack)
        mv.visitTypeInsn(CHECKCAST, classname)
        mv.visitFieldInsn(GETFIELD, classname, fieldname, JTHING)
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
    (0 to nargs).foreach { x => stack.push }
    
    uf.instantiations.foreach { x => 
      instantiate(module, uf, mv, x, stack)
    }

    uf.initializations.foreach { x => 
      initialize(module, uf, mv, x, stack)  
    }
    
    uf.code.foreach { ex =>
      add(module, uf, mv, ex, stack)
    }
    
    uf.code.last match {
      case StoreLocal(local, _, _) =>
        mv.visitVarInsn(ALOAD, local)
        stack.push
        
      case Nop() =>
        mv.visitVarInsn(ALOAD, 0)
        stack.push
        
      case _ =>
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
    //mv.visitInsn(DUP)
    //mv.visitMethodInsn(INVOKEVIRTUAL, slashedName(module, uf), "initialize", "()V", false)
    mv.visitMethodInsn(INVOKEVIRTUAL, slashedName(module, uf), "apply0", "()Lruntime/Thing;", false)
    mv.visitInsn(POP)
    mv.visitInsn(RETURN)
    mv.visitMaxs(3, 1)
    mv.visitEnd()
  }
  
  /**
   * Adds a static field containing the function typeof
   */
  def addMetadata(cw: ClassWriter, module: CreateModule, uf: CreateFunction) {
    
    uf.metadata.foreach { 
      case MetadataType(repr) =>
        val fv = cw.visitField(ACC_PUBLIC + ACC_STATIC, "type", "Ljava/lang/String;", null, null)
        fv.visitEnd();
    }

    val mv = cw.visitMethod(ACC_STATIC, "<clinit>", "()V", null, null)
    mv.visitCode()
    
    uf.metadata.foreach { 
      case MetadataType(repr) =>
        mv.visitLdcInsn(repr)
        mv.visitFieldInsn(PUTSTATIC, slashedName(module, uf), "type", "Ljava/lang/String;");
    }

    val stack = new Stack
    if (uf.name == "main") addExports(cw, mv, module, uf, stack)
    
    mv.visitInsn(RETURN)
    mv.visitMaxs(60, 60)
    mv.visitEnd()
  }
  
  /**
   * Adds a static field containing the exported functions
   */
  def addExports(cw: ClassWriter, mv: MethodVisitor, module: CreateModule, uf: CreateFunction, stack: Stack) {
    val fv = cw.visitField(ACC_PUBLIC + ACC_STATIC, "exports", "[Lruntime/Export;", null, null);
    fv.visitEnd()
    
    val nExports = module.exportedFunctions.length
    
    mv.visitLdcInsn(new Integer(nExports))
    mv.visitTypeInsn(ANEWARRAY, "runtime/Export")
    
    ((0 to nExports-1) zip module.exportedFunctions).foreach { case (i, x) => 
      mv.visitInsn(DUP)
      mv.visitLdcInsn(new Integer(i))
      mv.visitTypeInsn(NEW, "runtime/Export")
      mv.visitInsn(DUP)
      mv.visitLdcInsn(x.name)
      mv.visitLdcInsn(x.`type`)
      mv.visitLdcInsn(x.overrides.length)
      mv.visitTypeInsn(ANEWARRAY, "java/lang/String")
      ((0 to x.overrides.length) zip x.overrides).foreach { case (ii, o) =>
        mv.visitInsn(DUP)
        mv.visitLdcInsn(new Integer(ii))
        mv.visitLdcInsn(o)
        mv.visitInsn(AASTORE)
      }
      mv.visitMethodInsn(INVOKESPECIAL, "runtime/Export", "<init>", "(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)V", false)
      mv.visitInsn(AASTORE)
    }
    
    mv.visitFieldInsn(PUTSTATIC, slashedName(module, uf), "exports", "[Lruntime/Export;")
  }
  
  /**
   * Guess what
   */
  def slashedName(module: CreateModule, uf: CreateFunction) = module.name + "/" + uf.name
  def slashedName(module: CreateModule, uf: CreateClass) = module.name + "/" + uf.name

  /**
   * Generates a Java class for a function in a module
   */
  def genclass(module: CreateModule, f: CreateFunction) = {
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

  /**
   * Adds fields to a class
   */
  def addFields(cw: ClassWriter, module: CreateModule, c: CreateClass) {
    c.fields.foreach { f =>
      val fv  = cw.visitField(ACC_PUBLIC + ACC_FINAL, f.name, jsuperclass(f.ty), null, null)
      fv.visitEnd
    }
  }

  /**
   * Adds an empty constructor to a class
   */
  def addConstructor(cw: ClassWriter, module: CreateModule, c: CreateClass) {
    val cname = c.name
    val nFields = c.fields.size
    val signature = c.fields.map(f => jsuperclass(f.ty)).mkString("(", "", ")V")
    val mv = cw.visitMethod(ACC_PUBLIC, "<init>", signature, null, null)
    mv.visitCode()

    val stack = new Stack()

    mv.visitVarInsn(ALOAD, 0)
    stack.push
    mv.visitMethodInsn(INVOKESPECIAL, THING, "<init>", "()V", false)
    stack.pop

    ((1 to nFields) zip c.fields).foreach { case (i, f) =>
      mv.visitVarInsn(ALOAD, 0)
      stack.push
      mv.visitVarInsn(ALOAD, i)
      stack.push
      mv.visitFieldInsn(PUTFIELD, slashedName(module, c), f.name, jsuperclass(f.ty))
      stack pop 2
    }

    mv.visitInsn(RETURN)
    mv.visitMaxs(stack.maxdepth + 1, nFields + 1)
    mv.visitEnd()
  }

  /**
   * Generates a Java class for a class in a module
   */
  def genclass(module: CreateModule, c: CreateClass) = {
    val cw = new ClassWriter(0)
    cw.visit(V1_7, ACC_PUBLIC + ACC_SUPER, slashedName(module, c), null, THING, null)

    addFields(cw, module, c)
    addConstructor(cw, module, c)
    cw.visitEnd()
    cw.toByteArray()

    (module.name, c.name, cw.toByteArray())
  }
  
  def codegen(module: CreateModule) = {
    val fs = module.functions.map { f =>
      genclass(module, f)
    }
    val cs = module.classes.map { c =>
      genclass(module, c)
    }
    fs ++ cs
  }
}
