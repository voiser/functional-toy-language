package ast2

import org.objectweb.asm._
import org.objectweb.asm.Opcodes._
import intermediate._

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

    initConstants(mv, module, uf)
    
    mv.visitInsn(RETURN)

    mv.visitMaxs(0, 0)
    mv.visitEnd()
  }
  
  /**
   * Adds constant initialization code to a constructor given by a method visitor
   */
  def initConstants(
      mv: MethodVisitor, 
      module: CreateModule,
      uf: CreateFunction) {
    
    val slashedName = module.name + "/" + uf.name
    
    uf.constants.foreach { x =>
      val name = x.name
      val node = x.node
      node match {
        case NInt(i) =>
          mv.visitVarInsn(ALOAD, 0)
          mv.visitTypeInsn(NEW, "runtime/Int")
          mv.visitInsn(DUP)
          mv.visitIntInsn(BIPUSH, i)
          mv.visitMethodInsn(INVOKESPECIAL, "runtime/Int", "<init>", "(I)V", false)
          mv.visitFieldInsn(PUTFIELD, slashedName, name, JTHING)

        case NFloat(f) =>
          mv.visitVarInsn(ALOAD, 0)
          mv.visitTypeInsn(NEW, "runtime/Float")
          mv.visitInsn(DUP)
          mv.visitLdcInsn(new java.lang.Float(f))
          mv.visitMethodInsn(INVOKESPECIAL, "runtime/Float", "<init>", "(F)V", false)
          mv.visitFieldInsn(PUTFIELD, slashedName, name, JTHING)

        case _ => throw new CodegenException("Can't init constant " + x)
      }
    }
    
    uf.externs.foreach { pair =>
      val name = pair.name
      val ty = pair.ty
      val fullname = pair.fullname
      val javaclass = jsuperclass(ty)
      mv.visitVarInsn(ALOAD, 0)
      mv.visitTypeInsn(NEW, fullname)
      mv.visitInsn(DUP)
      mv.visitMethodInsn(INVOKESPECIAL, fullname, "<init>", "()V", false)
      mv.visitFieldInsn(PUTFIELD, slashedName, name, javaclass)
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
    
    var i = 1
    uf.captures.foreach { capture =>
      mv.visitVarInsn(ALOAD, 0)
      mv.visitVarInsn(ALOAD, i)
      i = i + 1
      val javaty = jsuperclass(capture.ty)
      val ty = superclass(capture.ty)
      mv.visitTypeInsn(CHECKCAST, ty)
      mv.visitFieldInsn(PUTFIELD, slashedName, capture.name, javaty)
    }

    //initConstants(mv, stack, module, uf)

    mv.visitInsn(RETURN)

    mv.visitMaxs(0, 0)
    mv.visitEnd()
  }
  
  /**
   * 
   */
  def instantiate(
      module: CreateModule,
      uf: CreateFunction, 
      mv: MethodVisitor,
      ex: Instantiate) {
    mv.visitTypeInsn(NEW, ex.name)
    mv.visitInsn(DUP)
    mv.visitMethodInsn(INVOKESPECIAL, ex.name, "<init>", "()V", false)
    mv.visitTypeInsn(CHECKCAST, FUNC)
    mv.visitVarInsn(ASTORE, ex.index)
  }
  
  /**
   * 
   */
  def initialize(
      module: CreateModule,
      uf: CreateFunction, 
      mv: MethodVisitor,
      ex: Initialize) {
    mv.visitVarInsn(ALOAD, ex.index)
    mv.visitTypeInsn(CHECKCAST, ex.name)
    ex.params.foreach { x =>
      add(module, uf, mv, x)
    }
    val ncaptures = ex.params.length
    val j = JTHING * ncaptures
    mv.visitMethodInsn(INVOKEVIRTUAL, ex.name, "initialize", "(" + j + ")V", false)
  }
  
  /**
   * 
   */
  def add(
      module: CreateModule,
      uf: CreateFunction, 
      mv: MethodVisitor,
      ex: CodegenStep) {
    
    val sn = slashedName(module, uf)
    
    ex match {
      case x: Nop =>
      
      case SInt(v) =>
        mv.visitTypeInsn(NEW, "runtime/Int")
        mv.visitInsn(DUP)
        mv.visitLdcInsn(new Integer(v))
        mv.visitMethodInsn(INVOKESPECIAL, "runtime/Int", "<init>", "(I)V", false)

      case SFloat(f) =>
        mv.visitTypeInsn(NEW, "runtime/Float")
        mv.visitInsn(DUP)
        mv.visitLdcInsn(new java.lang.Float(f))
        mv.visitMethodInsn(INVOKESPECIAL, "runtime/Float", "<init>", "(F)V", false)

      case SString(s) =>
        mv.visitTypeInsn(NEW, "runtime/Str")
        mv.visitInsn(DUP)
        mv.visitLdcInsn(s)
        mv.visitMethodInsn(INVOKESPECIAL, "runtime/Str", "<init>", "(Ljava/lang/String;)V", false)

      case SBool(b) =>
        mv.visitTypeInsn(NEW, "runtime/Bool")
        mv.visitInsn(DUP)
        mv.visitLdcInsn(b)
        mv.visitMethodInsn(INVOKESPECIAL, "runtime/Bool", "<init>", "(Z)V", false)

      case Local(i) =>
        mv.visitVarInsn(ALOAD, i)

      case Constant(name) =>
        val ty = uf.constants.collectFirst { case CreateConstant(n, t, _) if name == n => jsuperclass(t) }
        ty match {
          case None => throw new CodegenException("In class " + uf.name + ", I can't find the type of " + name + ". This is a compiler bug")
          case Some(t) =>
            mv.visitVarInsn(ALOAD, 0)
            mv.visitFieldInsn(GETFIELD, sn, name, t)
        }
        
      case Capture(name) =>
        val ty = uf.captures.collectFirst { case CreateCapture(n, t) if name == n => jsuperclass(t) }
        ty match {
          case None => throw new CodegenException("In class " + uf.name + ", I can't find the type of " + name + ". This is a compiler bug")
          case Some(t) =>
            mv.visitVarInsn(ALOAD, 0)
            mv.visitFieldInsn(GETFIELD, sn, name, t)
        }
        
      case intermediate.Extern(name) =>
        val ty = uf.externs.collectFirst { case CreateExtern(n, f, t) if name == n => jsuperclass(t) }
        ty match {
          case None => throw new RuntimeException("I can't find the type of " + name + ". This is a compiler bug")
          case Some(t) =>
            mv.visitVarInsn(ALOAD, 0)
            mv.visitFieldInsn(GETFIELD, sn, name, t)
        }
        
      case StoreLocal(local, _, value) =>
        add(module, uf, mv, value)
        mv.visitVarInsn(ASTORE, local)
        //println ("** I am storing " + value)
        //println (uf.locals.find { cl => cl.index == local }.get)

      case CallLocal(local, _, args) =>
        val javafname = "apply" + args.length
        val javasignature = "(" + (JTHING * args.length) + ")" + JTHING
        mv.visitVarInsn(ALOAD, local)
        mv.visitTypeInsn(CHECKCAST, FUNC)
        args.foreach { arg => 
            add(module, uf, mv, arg)
        }
        mv.visitMethodInsn(INVOKEVIRTUAL, FUNC, javafname, javasignature, false)

      case CallConstant(name, args) =>
        val javafname = "apply" + args.length
        val javasignature = "(" + (JTHING * args.length) + ")" + JTHING
        mv.visitVarInsn(ALOAD, 0)
        mv.visitFieldInsn(GETFIELD, sn, name, JFUNC)
        args.foreach { arg => 
            add(module, uf, mv, arg)
        }
        mv.visitMethodInsn(INVOKEVIRTUAL, FUNC, javafname, javasignature, false)

      case CallExtern(name, args) =>
        val javafname = "apply" + args.length
        val javasignature = "(" + (JTHING * args.length) + ")" + JTHING
        mv.visitVarInsn(ALOAD, 0)
        mv.visitFieldInsn(GETFIELD, sn, name, JFUNC)
        args.foreach { arg => 
            add(module, uf, mv, arg)
        }
        mv.visitMethodInsn(INVOKEVIRTUAL, FUNC, javafname, javasignature, false)

      case CallDynamic(_, args, options, intypes) =>
        def pushArray(l: List[_], jt: String) {
          mv.visitLdcInsn(new Integer(l.length))
          mv.visitTypeInsn(ANEWARRAY, jt)
          (0 to l.length-1).foreach { case i =>
            mv.visitInsn(DUP)
            mv.visitLdcInsn(new Integer(i))
            mv.visitLdcInsn(l(i))
            mv.visitInsn(AASTORE)
          }
        }
        
        pushArray(options.map{_.replace("/", ".")}, "java/lang/String")
        pushArray(intypes, "java/lang/String")

        {
          mv.visitLdcInsn(new Integer(args.length))
          mv.visitTypeInsn(ANEWARRAY, THING)
          (0 to args.length-1).foreach { case i =>
            mv.visitInsn(DUP)
            mv.visitLdcInsn(new Integer(i))
            add(module, uf, mv, args(i))
            mv.visitInsn(AASTORE)
          }
        }
        mv.visitMethodInsn(INVOKESTATIC, "runtime/DynamicDispatch", "dispatch", "([Ljava/lang/String;[Ljava/lang/String;[Lruntime/Thing;)Lruntime/Thing;", false)

      case SIf(cond, extrue, exfalse) =>
        add(module, uf, mv, cond)
        mv.visitTypeInsn(CHECKCAST, BOOL)
        mv.visitFieldInsn(GETFIELD, BOOL, "b", "Z")
        val l1 = new Label()
        val l2 = new Label()
        mv.visitJumpInsn(IFEQ, l1)
        add(module, uf, mv, extrue)
        mv.visitJumpInsn(GOTO, l2)
        mv.visitLabel(l1)
        add(module, uf, mv, exfalse)
        mv.visitLabel(l2)

      case Instance(cname, argtypes, args) =>
        val signature = argtypes.map(a => jsuperclass(a)).mkString("(", "", ")V")
        mv.visitTypeInsn(NEW, cname)
        mv.visitInsn(DUP)
        args.foreach { arg =>
          add(module, uf, mv, arg)
        }
        mv.visitMethodInsn(INVOKESPECIAL, cname, "<init>", signature, false)

      case InstanceField(owner, classname, fieldname) =>
        add(module, uf, mv, owner)
        mv.visitTypeInsn(CHECKCAST, classname)
        mv.visitFieldInsn(GETFIELD, classname, fieldname, JTHING)

      case NewAnon(cname, args) =>
        {
          val signature = "()V"
          mv.visitTypeInsn(NEW, cname)
          mv.visitInsn(DUP)
          mv.visitMethodInsn(INVOKESPECIAL, cname, "<init>", signature, false)
        }
        {
          mv.visitInsn(DUP)
          args.foreach { arg =>
            add(module, uf, mv, arg)
          }
          val ncaptures = args.length
          val j = JTHING * ncaptures
          mv.visitMethodInsn(INVOKEVIRTUAL, cname, "initialize", "(" + j + ")V", false)
        }

      case Match(vlocal, what, cname, pars, exptrue, expfalse) =>
        add(module, uf, mv, what)
        mv.visitInsn(DUP)
        mv.visitTypeInsn(INSTANCEOF, cname)
        val l1 = new Label()
        val l2 = new Label()
        mv.visitJumpInsn(IFEQ, l1)
        mv.visitTypeInsn(CHECKCAST, cname)
        mv.visitVarInsn(ASTORE, vlocal)
        pars.foreach {
          case (field, local) =>
            //mv.visitTypeInsn(CHECKCAST, cname)
            mv.visitVarInsn(ALOAD, vlocal)
            mv.visitFieldInsn(GETFIELD, cname, field, JTHING)
            mv.visitVarInsn(ASTORE, local)
        }
        add(module, uf, mv, exptrue)
        mv.visitJumpInsn(GOTO, l2)
        mv.visitLabel(l1)
        mv.visitInsn(POP)
        add(module, uf, mv, expfalse)
        mv.visitLabel(l2)
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
    
    uf.instantiations.foreach { x =>
      instantiate(module, uf, mv, x)
    }

    uf.initializations.foreach { x => 
      initialize(module, uf, mv, x)
    }
    
    uf.code.foreach { ex =>
      add(module, uf, mv, ex)
    }
    
    uf.code.last match {
      case StoreLocal(local, _, _) =>
        mv.visitVarInsn(ALOAD, local)

      case Nop() =>
        mv.visitVarInsn(ALOAD, 0)

      case _ =>
    }
    mv.visitInsn(ARETURN)
    mv.visitMaxs(0, 0)
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

    if (uf.name == "main") addExports(cw, mv, module, uf)
    
    mv.visitInsn(RETURN)
    mv.visitMaxs(60, 60)
    mv.visitEnd()
  }

  /**
   * Adds a static field containing the exported functions
   */
  def addExports(cw: ClassWriter, mv: MethodVisitor, module: CreateModule, uf: CreateFunction) {
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
    val cw = new MyClassWriter(ClassWriter.COMPUTE_FRAMES + ClassWriter.COMPUTE_MAXS)
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
    mv.visitVarInsn(ALOAD, 0)
    mv.visitMethodInsn(INVOKESPECIAL, THING, "<init>", "()V", false)
    ((1 to nFields) zip c.fields).foreach { case (i, f) =>
      mv.visitVarInsn(ALOAD, 0)
      mv.visitVarInsn(ALOAD, i)
      mv.visitFieldInsn(PUTFIELD, slashedName(module, c), f.name, jsuperclass(f.ty))
    }
    mv.visitInsn(RETURN)
    mv.visitMaxs(0, 0)
    mv.visitEnd()
  }

  /**
   * add a repr() method to a class in a module
   */
  def addRepr(cw: ClassWriter, module: CreateModule, c: CreateClass): Unit = {

    val sn = slashedName(module, c)
    val mv = cw.visitMethod(ACC_PUBLIC, "repr", "()[Ljava/lang/String;", null, null)
    mv.visitCode()

    mv.visitLdcInsn(new Integer(c.fields.length + 2)) // those 2 are "class(" and ")"
    mv.visitTypeInsn(ANEWARRAY, "java/lang/String")

    mv.visitInsn(DUP)
    mv.visitLdcInsn(new Integer(0))
    mv.visitLdcInsn(c.name + "(")
    mv.visitInsn(AASTORE)

    c.fields.zipWithIndex.foreach { case (f, i) =>
      mv.visitInsn(DUP)
      mv.visitLdcInsn(new Integer(i + 1))
      mv.visitVarInsn(ALOAD, 0)
      mv.visitFieldInsn(GETFIELD, sn, f.name, JTHING)
      mv.visitMethodInsn(INVOKEVIRTUAL, THING, "toString", "()Ljava/lang/String;", false)
      mv.visitInsn(AASTORE)
    }

    mv.visitInsn(DUP)
    mv.visitLdcInsn(new Integer(c.fields.length + 1))
    mv.visitLdcInsn(")")
    mv.visitInsn(AASTORE)

    mv.visitInsn(ARETURN)
    mv.visitMaxs(0, 0)
    mv.visitEnd()
  }

  /**
   * Generates a Java class for a class in a module
   */
  def genclass(module: CreateModule, c: CreateClass) = {
    val cw = new MyClassWriter(ClassWriter.COMPUTE_FRAMES + ClassWriter.COMPUTE_MAXS)
    cw.visit(V1_7, ACC_PUBLIC + ACC_SUPER, slashedName(module, c), null, THING, null)

    addFields(cw, module, c)
    addConstructor(cw, module, c)
    addRepr(cw, module, c)
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


class MyClassWriter(flags: Int) extends ClassWriter(flags) {
  override def getCommonSuperClass(a: String, b: String): String = {
    try {
      super.getCommonSuperClass(a, b)
    }
    catch {
      case _ : Throwable =>
        Codegen.THING
    }
  }
}