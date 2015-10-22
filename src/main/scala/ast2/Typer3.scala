package ast2

/**
 * @author david
 */

class TyvarGenerator(prefix: String) {
  var n = 0
  
  def get() = {
    n = n + 1
    Tyvar(prefix + n)
  }
}


object Typer3 {
  
  val eqType = Tycon("Eq")
  val numType = Tycon("Num", List(), List(eqType))
  val intType = Tycon("Int", List(), List(numType, eqType))
  val stringType = Tycon("Str", List(), List(eqType))
  val floatType = Tycon("Float", List(), List(numType, eqType))
  val boolType = Tycon("Bool", List(), List(eqType))
  val unitType = Tycon("Unit")
  
  def tyvars(t: Ty) : List[Tyvar] = t match {
    case tv @ Tyvar(name) => List(tv)
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
      case x @ Tyvar(name) =>
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
  
  def isa(t1: Tycon, t2: Tycon) = t2.isa.contains(t1)
  
  /**
   * 
   */
  def unify(t1: Ty, t2: Ty, s: Subs, n: Node) : Subs = {
    val s1 = s(t1)
    val s2 = s(t2)
    (s1, s2) match {
      
      case (a @ Tyvar(na), b @ Tyvar(nb)) => 
        if (na == nb) s
        else s.extend(a, b)
      
      case (_, a : Tyvar) =>
        unify(t2, t1, s, n)
      
      case (a : Tyvar, x) if !(tyvars(x) contains a) =>
        s.extend(a, x)
        
      case (Tyfn(in1, out1), Tyfn(in2, out2)) =>
        val s1 = (s /: (in1 zip in2)) ((s, tu) => unify(tu._1, tu._2, s, n))
        unify(out1, out2, s1, n)
        
      case (Tycon(n1, tv1, isa1), Tycon(n2, tv2, isa2)) if (n1 == n2) =>
        (s /: (tv1 zip tv2)) ((s, tu) => unify(tu._1, tu._2, s, n))
        
      case (a @ Tycon(n1, tv1, isa1), b @ Tycon(n2, tv2, isa2)) if (isa(b, a)) =>
        unify(t2, t1, s, n)
        
      case (a @ Tycon(n1, tv1, isa1), b @ Tycon(n2, tv2, isa2)) if (isa(a, b)) =>
        (s /: (tv1 zip tv2)) ((s, tu) => unify(tu._1, tu._2, s, n))
        
      case _ => throw new TypeException("Can't unify " + t1 + " with " + t2, n)
    }
  }
  
  /**
   * 
   */
  def tp(env: Env, n: Node, t: Ty, gen: TyvarGenerator, s: Subs) : Subs = {
    n.env = env
    val tysub = n match {
      
      case a : NInt => unify(t, intType, s, n)
      case a : NFloat => unify(t, floatType, s, n)
      case a : NString => unify(t, stringType, s, n)
      
      case a @ NRef(name) =>
        val u = env.get(name)
        u match {
          case None => throw new TypeException("Can't find '" + name + "' in the environment", n)
          case Some(typescheme) => unify(t, typescheme.newInstance(gen), s, n)
        }

      case a @ NDef(name, ex) =>
        val u = env.get(name)
        u match {
          case Some(typescheme) => throw new TypeException("'" + name + "' is already defined", n)
          case None =>
            //val env1 = Env(env, n)
            val env1 = env
            val a = gen.get()
            env1.put(name, a)
            
            val s1 = tp(env1, ex, t, gen, s)
            env.put(name, s1(t))
            s1
        }
      
      case x @ NFn(params, ex) =>
        def f(p: NFnArg) : Ty = {
          val n = p.name
          val t = p.klass
          val tt = gen.get()
          t match {
            case KlassConst(name) => throw new TypeException("Not yet implemented!", x); 
            case KlassVar(name) => tt 
          }
        }
        val a = params.map { x => f(x) }
        val b = gen.get()
        val s1 = unify(t, Tyfn(a, b), s, n)
        val env1 = Env(env, n)
        (params zip a).foreach { x => env1.put(x._1.name, null, TypeScheme(List(), x._2)) }
        tp(env1, ex, b, gen, s1)
        
      case x @ NApply(name, args) => 
        val a = args.map { x => gen.get() }
        val candidates = env.get2(name).map { _._1}
        //println ("OK I have several candidates, let's try one by one...")
        val cands = candidates.map { candidate =>
          //println("  Trying candidate " + candidate)
          try {
            val r = NRef(candidate)
            r.position = x.position
            val s1 = tp(env, r, Tyfn(a, t), gen, s)
            val s2 = (s1 /: (args zip a)) ((s2, arg) => tp(env, arg._1, arg._2, gen, s2))
            //println("  This one worked")
            (candidate, s2)
          } 
          catch {
            case e: TypeException => 
              println("  This one did NOT work " + e)
              //e.printStackTrace()
              null
          }
        }.filterNot { _ == null }
        
        if (cands.length == 0) throw new TypeException("No candidates for '" + name + "'", n)
        else if (cands.length > 1) throw new TypeException("Too many candidates for '" + name + "'", n)
        else {
          x.realName = cands(0)._1
          cands(0)._2
        }
                
      case NIf(cond, e1, e2) =>
        val a = gen.get()
        val conds = tp(env, cond, a, gen, s)
        val conds2 = unify(conds(a), boolType, conds, n)

        val isBoolean = conds2(a) == boolType
        if (!isBoolean) throw new TypeException("If condition must be boolean", n)

        val b, c = gen.get()
        val trues = tp(env, e1, b, gen, conds2)
        //val falses = tp(env, e2, c, gen, trues)
        // If I could type the CASE_TRUE branch, I will assume that
        // the CASE_FALSE branch is exactly the same type.
        val falses = tp(env, e2, c, gen, trues.extend(c, trues(b)))
        
        val u = unify(falses(b), falses(c), falses, n)
        unify(t, c, u, n)
        
      case NBlock(exs) =>
        //val env1 = Env(env, n)
        val env1 = env
        var b: Tyvar = null
        val s1 = (s /: exs) { (s2, ex) =>
          b = gen.get()
          tp(env1, ex, b, gen, s2)
        }
        unify(t, s1(b), s1, n)
        
      case _ => throw new TypeException("Can't type node " + n, n)
    }
    n.ty = tysub(t)
    tysub
  }
  
  def getType(env: Env, n: Node) = {
    val gen = new TyvarGenerator("t")
    val rootVar = gen.get()
    val subs = tp(env, n, rootVar, gen, emptySubst)
    //val x = subs(rootVar)
    //x
    n.ty
  }  
}
