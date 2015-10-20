package ast2

import org.objectweb.asm._
import org.objectweb.asm.Opcodes._

/**
 * @author david
 */

class CodegenException(msg: String) extends RuntimeException(msg);

object Codegen {
  
  def addConstant(cw: ClassWriter, name: String, value: Node, ty: Ty) {
    val fv = cw.visitField(ACC_PUBLIC, name, "Lruntime/Thing;", null, null);
    fv.visitEnd();
  }
  
  def addFunc(cw: ClassWriter, name: String, ty: Ty) {
    val fv = cw.visitField(ACC_PUBLIC, name, "Lruntime/Func;", null, null);
    fv.visitEnd();
  }
  
  def initInt(mv: MethodVisitor, className: String, name: String, value: NInt, ty: Ty) {
    mv.visitVarInsn(ALOAD, 0);
    mv.visitTypeInsn(NEW, "runtime/Int");
    mv.visitInsn(DUP);
    mv.visitIntInsn(BIPUSH, value.i);
    mv.visitMethodInsn(INVOKESPECIAL, "runtime/Int", "<init>", "(I)V", false);
    mv.visitFieldInsn(PUTFIELD, className, name, "Lruntime/Thing;");
  }
  
  def initConstant(mv: MethodVisitor, className: String, name: String, value: Node, ty: Ty) {
    (value, ty) match {
      case (v @ NInt(i), Tycon("Int", List())) => initInt(mv, className, name, v, ty)
      case _ => throw new CodegenException("I can't initialize a constant of type " + ty)
    }    
  } 
  
  def initRuntimeFunc(mv: MethodVisitor, className: String, fname: String, fjavaclass: String) {
    mv.visitVarInsn(ALOAD, 0);
    mv.visitTypeInsn(NEW, fjavaclass);
    mv.visitInsn(DUP);
    mv.visitMethodInsn(INVOKESPECIAL, fjavaclass, "<init>", "()V", false);
    mv.visitFieldInsn(PUTFIELD, className, fname, "Lruntime/Func;");
  }

  
  
  
  
  
  /*
  
  def codegen(module: Main.Module, env: Env) = {
    
    val predefNames = List("add")

    val predef = predefNames.map { n => (n, Main.rootEnv.get(n).get.tpe, "runtime/" + n) }
    
    val constants : List[(String, Node, Ty)] = module.defs.collect {
      case (NDef(name, v @ NInt(i)), ty) => (name, v, ty)
      case (NDef(name, v @ NFloat(i)), ty) => (name, v, ty)
    }
    
    val className = module.name.replace(".", "/")
    val superclass = "runtime/Module"
    
    val cw = new ClassWriter(0)
    cw.visit(V1_7, ACC_PUBLIC + ACC_SUPER, className, null, superclass, null)

    constants.map { x => addConstant(cw, x._1, x._2, x._3) }
    predef.map { x => addFunc(cw, x._1, x._2)}
    
    {
      val mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null)
      mv.visitCode()
      
      mv.visitVarInsn(ALOAD, 0)
      mv.visitMethodInsn(INVOKESPECIAL, superclass, "<init>", "()V", false)
      
      constants.map { x => initConstant(mv, className, x._1, x._2, x._3) }
      predef.map { x => initRuntimeFunc(mv, className, x._1, x._3) }
      
      mv.visitInsn(RETURN)

      mv.visitMaxs(4, 1)
      mv.visitEnd()
    }
    
    module.defs.map { x => 
      val node = x._1
      val ty = x._2
      
      (node, ty) match {
        
        case (NDef(name, n : NApply), ty: Ty) =>
          println ("Creating function " + node.name + " with type " + ty)
          val mv = cw.visitMethod(ACC_PUBLIC, name, "()V", null, null)
          
          val target = n.name
          val fulltarget = env.getFull(target)
          
          mv.visitVarInsn(ALOAD, 0);
          mv.visitFieldInsn(GETFIELD, className, n.name, "Lruntime/Func;");
          mv.visitTypeInsn(CHECKCAST, fulltarget);
          
          n.params.foreach { paramnode =>
            
            paramnode match {
              case NRef(name) => 
                val fullname = env.getFull(name)
                if (fullname == null) {
                  // it's a local 
                }
                else throw new CodegenException("do me")
                
                
              case _ => throw new CodegenException("What am I supposed to do?")
            }
            
          }
          
          
          
          mv.visitVarInsn(ALOAD, 0);
          mv.visitFieldInsn(GETFIELD, className, "y", "Lruntime/Thing;");
          
          mv.visitMethodInsn(INVOKEVIRTUAL, "runtime/add", "apply", "(Lruntime/Thing;Lruntime/Thing;)V", false);
          
          mv.visitInsn(RETURN);
          
          mv.visitMaxs(3, 1)
          mv.visitEnd()
          
        case _ =>
      }
    }
    
    
    
//    {
//      val mv = cw.visitMethod(ACC_PUBLIC, "run", "()V", null, null) 
//      mv.visitCode()
//      
//      //mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;")
//      //mv.visitLdcInsn("Hello!")
//      //mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false)
//      //mv.visitInsn(RETURN)
//      
//      mv.visitVarInsn(ALOAD, 0);
//      mv.visitFieldInsn(GETFIELD, className, "add", "Lruntime/Func;");
//      mv.visitTypeInsn(CHECKCAST, "runtime/add");
//      mv.visitVarInsn(ALOAD, 0);
//      mv.visitFieldInsn(GETFIELD, className, "x", "Lruntime/Thing;");
//      mv.visitVarInsn(ALOAD, 0);
//      mv.visitFieldInsn(GETFIELD, className, "y", "Lruntime/Thing;");
//      mv.visitMethodInsn(INVOKEVIRTUAL, "runtime/add", "apply", "(Lruntime/Thing;Lruntime/Thing;)V", false);
//      mv.visitInsn(RETURN);
//      
//      mv.visitMaxs(3, 1)
//      mv.visitEnd()
//    }

    cw.visitEnd()
    cw.toByteArray()
  }
*/
  
}