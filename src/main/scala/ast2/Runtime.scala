package ast2

import java.io.FileOutputStream
import java.io.File
import java.net.{URL, URLClassLoader}

/**
 * @author david
 */

class VolatileClassLoader(default: ClassLoader) extends ClassLoader {

  val classes = scala.collection.mutable.Map[String, Class[_]]()
  
  def add(name: String, bytes: Array[Byte]) {
    val klass = defineClass(name, bytes, 0, bytes.length);
    classes.put(name, klass)
  }

  override def loadClass(name: String) : Class[_] = {
    classes.get(name) match {
      case None => default.loadClass(name)
      case Some(klass) => klass
    }
  }
}


class Runtime {

  val parent = this.getClass.getClassLoader
  val x = new VolatileClassLoader(parent)
  
  def register(pack: String, name: String, bytes: Array[Byte]) {
    val destination = new File("/tmp", pack)
    destination.mkdirs()
    val f = new FileOutputStream(new File(destination, name + ".class"))
    f.write(bytes)
    f.close()
    x.add(pack + "." + name, bytes)
  }
  
  def inspect(name: String) {
    val k = x.loadClass(name)
    val i = k.newInstance()
    k.getMethod("inspect").invoke(i)
  }
  
  def apply0(name: String) = {
    val k = x.loadClass(name)
    val i = k.newInstance()
    k.getMethod("initialize").invoke(i)
    k.getMethod("apply0").invoke(i)    
  }

  def classForName(name: String) =
    try {
      Class.forName(name)
    }
    catch {
      case e: Exception => x.loadClass(name)
    }
}
