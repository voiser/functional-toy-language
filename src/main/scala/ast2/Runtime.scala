package ast2

import java.io.FileOutputStream
import java.io.File

/**
 * @author david
 */

class VolatileClassLoader(parent: ClassLoader) extends ClassLoader {
  
  val classes = scala.collection.mutable.Map[String, Class[_]]()
  
  def add(name: String, bytes: Array[Byte]) {
    val klass = defineClass(name, bytes, 0, bytes.length);
    classes.put(name, klass)
  }

  override def loadClass(name: String) : Class[_] = {
    classes.get(name) match {
      case None => parent.loadClass(name)
      case Some(klass) => klass
    }
  }
}


class Runtime {
  
  val x = new VolatileClassLoader(this.getClass.getClassLoader)
  
  def register(pack: String, name: String, bytes: Array[Byte]) {
    x.add(pack + "." + name, bytes)
    val destination = new File("/tmp", pack)
    destination.mkdirs()
    val f = new FileOutputStream(new File(destination, name + ".class"))
    f.write(bytes)
    f.close()
  }
  
  def inspect(name: String) {
    val k = x.loadClass(name)
    val i = k.newInstance()
    k.getMethod("inspect").invoke(i)
  }
  
  def apply0(name: String) = {
    val k = x.loadClass(name)
    val i = k.newInstance()
    k.getMethod("apply0").invoke(i)    
  }
}