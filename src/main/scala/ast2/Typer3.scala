package ast2

/**
 * This type checker is based in the fantastic Martin Odersky's implementation of Hindley Milner  
 * 
 * http://www.scala-lang.org/docu/files/ScalaByExample.pdf 
 * 
 * @author david
 */

class TyvarGenerator(prefix: String) {
  var n = 0
  
  def get() = {
    n = n + 1
    Tyvar(prefix + n, List[String]())
  }
}

case class TraceElement(message: String, node: Node)


object Typer3 {
  
  val eqType = Restriction("Eq", List())
  val numType = Restriction("Num", List(eqType))
  
  val intType = Tycon("Int", List(), List(numType))
  val stringType = Tycon("Str", List(), List(eqType))
  val floatType = Tycon("Float", List(), List(numType))
  val boolType = Tycon("Bool", List(), List(eqType))
  val unitType = Tycon("Unit")
  
  def tyvars(t: Ty) : List[Tyvar] = t match {
    case tv @ Tyvar(name, res) => List(tv)
    case Tyfn(in, out) => 
      val x = (in flatMap { x => tyvars(x) }) 
      x union tyvars(out) distinct
    case Tycon(k, ts, isa) => (List[Tyvar]() /: ts) ((tvs, t) => tvs union tyvars(t)) distinct 
  }
  
  /**
   * 
   */
  abstract class Subs extends Function1[Ty, Ty] {
  
    def lookup(t: Tyvar) : Ty
    
    def repr: String;
    
    override def toString() = "subs: " + repr
    
    def apply(a: Ty) : Ty = a match {
      case x @ Tyvar(name, res) =>
        val v = lookup(x)
        if (v == a) a
        else apply(v)
        
      case Tyfn(params, out) =>
        Tyfn(params map apply, apply(out))
        
      case Tycon(name, types, isa) =>
        Tycon(name, types map apply, isa)
        
      case TyAny() => a
    }

    def extend(origin: Tyvar, dest: Ty) : Subs = new Subs {
      def lookup(t: Tyvar) = if (t == origin) dest else Subs.this.lookup(t)
      def repr = origin.repr + " => " + dest.repr + "; " + Subs.this.repr 
    }
  }
  
  val emptySubst = new Subs { 
    def lookup(t: Tyvar) : Ty = t
    def repr = ""
  }
  
  /**
   * 
   */
  
  def exception(message: String, n: Node) (implicit trace: List[TraceElement]) =
    throw new TypeException(message, n, trace)
  
  def unify(t1: Ty, t2: Ty, s: Subs, n: Node) (implicit gen: TyvarGenerator, trace: List[TraceElement]) : Subs = {
    val s1 = s(t1)
    val s2 = s(t2)
    val tr = mktrace("When unifying " + t2 + "(" + s1 + ") with " + t1 + "(" + s2 + ")", n, trace)
    (s1, s2) match {
      
      case (a @ Tyvar(na, List()), b @ Tyvar(nb, _)) =>
        if (na == nb) s
        else s.extend(a, b)
          
      case (_, a @ Tyvar(na, List())) =>
        unify(t2, t1, s, n)
      
      case (a @ Tyvar(na, resa), b @ Tyvar(nb, resb)) => 
        val allRestrictions = (resa ++ resb).distinct
        val newTyvar = Tyvar(gen.get().name, allRestrictions)
        s.extend(a, newTyvar).extend(b, newTyvar)  
      
      case (a @ Tyvar(name, res), x @ Tycon(nam, tv, res1)) if !(tyvars(x) contains a) =>
        val res2 = res1.flatMap { z => z.all }.distinct
        res.foreach { z => 
          if (!res2.contains(z)) 
            exception("Can't unify " + s1.repr + " with " + s2.repr + ": The type " + x.repr + " does not guarantee " + z, n)
        }
        s.extend(a, x)

      case (a @ Tyvar(name, List()), x) if !(tyvars(x) contains a) =>
        s.extend(a, x)

      case (a @ Tyvar(name, res), x) if !(tyvars(x) contains a) =>
        exception("TODO: I have to guarantee that " + x + " meets the restrictions " + res, n)
        
      case (Tyfn(in1, out1), Tyfn(in2, out2)) =>
        if (in1.length != in2.length) exception("Arguments do not match. Given " + in1.length + ", required " + in2.length, n)
        val s1 = (s /: (in1 zip in2)) ((s, tu) => unify(tu._1, tu._2, s, n))
        unify(out1, out2, s1, n)
        
      case (Tycon(n1, tv1, isa1), Tycon(n2, tv2, isa2)) if (n1 == n2) =>
        (s /: (tv1 zip tv2)) ((s, tu) => unify(tu._1, tu._2, s, n))
      
      case _ => exception("Type mismatch. Needed " + s1.repr + " but given " + s2.repr, n)
    }
  }
  
  def getForward(x: Node, env: Env, n: String)(implicit trace: List[TraceElement]) = 
    env.getForward(n) match {
      case None => throw new TypeException("Can't find forward in env. This is a compiler bug", x, mktrace("When locating forward node", x, trace))
      case Some(fwd) =>
        fwd     
  }
  
  /**
   * 
   */
  def tp(env: Env, n: Node, t: Ty, s: Subs) (implicit gen: TyvarGenerator, trace: List[TraceElement]) : Subs = {
    n.env = env
    
    def trac(s: String) = mktrace(s, n, trace)
    
    val tysub = n match {
      
      case a @ NForward(name, gty) =>
        val ty = Typegrammar.toType(gty, env)
        env.put(name + "$$forward", ty)
        env.putForward(name + "$$forward", a)
        unify(t, ty, s, n)(gen, trac("When typing forward definition"))
      
      case a : NInt => unify(t, intType, s, n)(gen, trac("When typing Int"))
      case a : NFloat => unify(t, floatType, s, n)(gen, trac("When typing Float"))
      case a : NString => unify(t, stringType, s, n)(gen, trac("When typing String"))
      
      case a @ NRef(name) =>
        val u = env.get(name)
        u match {
          case None => throw new TypeException("Can't find '" + name + "' in the environment", n, trace)
          case Some(typescheme) => unify(t, typescheme.newInstance(gen), s, n)
        }

      case a @ NDef(name, ex) =>
        val u = env.get(name)
        u match {
          case Some(typescheme) => throw new TypeException("'" + name + "' is already defined", n, trac("When typing the definition of " + name))
          case None =>
            env.get(name + "$$forward") match {
              case Some(ts) =>
                ex match {
                  case x @ NFn(params, ex) if (x.hasTypedArgs) =>
                    val fwd = getForward(x, env, name + "$$forward")
                    val newtrace = mktrace("The forward definition is " + fwd.ty.repr, fwd, trace)
                    throw new TypeException("Function " + name + " can't have typed arguments because it has a forward definition", x.typedArgs(0), newtrace)
                    
                  case _ =>
                }
                val forward = ts.newInstance(gen)
                val env1 = env
                env1.put(name, forward)
                val s1 = tp(env1, ex, t, s)
                val computed = s1(t)
                val s2 = unify(forward, computed, s1, n)
                env1.put(name, s2(t))
                ex match {
                  case x @ NFn(params, ex) =>
                    x.ty = s2(t)
                    
                  case _ =>
                }
                s2
                
              case None =>
                val env1 = env
                val a = gen.get()
                env1.put(name, a)
            
                val s1 = tp(env1, ex, t, s)
                env.put(name, s1(t))
                s1
            }
        }
      
      case x @ NFn(params, ex) =>
        def f(p: NFnArg) : Ty = {
          val n = p.name
          val t = p.klass
          t match {
            case KlassConst(name) => 
              env.getType(name) match {
                case None => throw new TypeException("No type " + name + " defined", x, trac("When typing a function body"))
                case Some(tt) => tt
              }
            case KlassVar(name) => gen.get() 
          }
        }
        val a = params.map { x => f(x) }
        val b = gen.get()
        val s1 = unify(t, Tyfn(a, b), s, n) (gen, trac("When typing a function body"))
        val env1 = Env(env, n)
        (params zip a).foreach { x => env1.put(x._1.name, null, TypeScheme(List(), x._2))}
        val ret = tp(env1, ex, b, s1)
        (params zip a).foreach { x => env1.put(x._1.name, null, TypeScheme(List(), ret(x._2)))}
        ret
        
      case x @ NApply(name, args) => 
        val a = args.map { x => gen.get() }
        val candidates = env.get2(name).map { _._1}.sortBy { x => x.length }
        def typ(n: String) (implicit trace: List[TraceElement]) = {
          val r = NRef(n)
          r.ctx = x.ctx
          val s1 = tp(env, r, Tyfn(a, t), s)
          val s2 = (s1 /: (args zip a)) ((s2, arg) => tp(env, arg._1, arg._2, s2))
          s2
        }
        candidates match {
          case List() =>
            throw new TypeException("Can't find definition of '" + name + "'", n, trac("When typing a function call"))
          case List(n) =>
            val newtrace = 
              if (n.endsWith("$$forward")) {
                val fwd = getForward(x, env, n)
                mktrace("The forward definition is " + fwd.ty.repr, fwd, trace)
              }
              else trace
            x.realName = n.replace("$$forward", "")
            typ(n)(newtrace)
          case List(a, b) if (b == a + "$$forward") =>
            x.realName = a
            typ(a)
          case _ =>
            throw new TypeException("Too many candidates for '" + name + "'. This is a compiler bug", n, trac("When typing a function call"))
        }
        
      case NIf(cond, e1, e2) =>
        val a, b, c = gen.get()
        val conds = tp(env, cond, a, s)
        val trues = tp(env, e1, b, conds)
        val falss = tp(env, e2, c, trues)
        val s2 = unify(a, boolType, falss, n)(gen, trac("When typing a condition"))
        val s3 = unify(b, c, falss, n)(gen, trac("When typing a true branch"))
        val s4 = unify(t, s3(b), s3, n)(gen, trac("When typing a false branch"))
        s4
        
      case NBlock(exs) =>
        val env1 = env
        var b: Tyvar = null
        val s1 = (s /: exs) { (s2, ex) =>
          b = gen.get()
          val x = tp(env1, ex, b, s2)
          x
        }
        unify(t, s1(b), s1, n)
        
      case _ => throw new TypeException("Can't type node " + n, n, trac("When typing a generic node"))
    }
    n.ty = tysub(t)
    tysub
  }
  
  def mktrace(t: String, n: Node, trace: List[TraceElement]) = TraceElement(t, n) :: trace
  
  def getType(env: Env, n: Node) = {
    val gen = new TyvarGenerator("t")
    val rootVar = gen.get()
    val subs = tp(env, n, rootVar, emptySubst)(gen, List())
    n.ty
  }  
}
