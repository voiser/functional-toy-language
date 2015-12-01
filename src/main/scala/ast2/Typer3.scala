package ast2

/**
 * @author david
 */

class TyvarGenerator(prefix: String) {
  var n = 0
  
  def get() = {
    n = n + 1
    Tyvar(prefix + n, List[String]())
  }
}


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
  
  /*
  def isa(t1: Tycon, t2: Tycon) = {
    def extract(t: List[Tycon], acc: List[Tycon]) : List[Tycon] = t match {
      case x :: List(xs) =>
        x :: extract(x.isa, List()) ++ acc
        
      case List(x) =>
        x :: extract(x.isa, List())
        
      case List() =>
        List()
    }
    
    val all = t2 :: extract(t2.isa, List())
    all.contains(t1)
  }

  
  def common(t1: Ty, t2: Ty, n: Node) = (t1, t2) match {
    case (a : Tycon, b : Tycon) =>     
      if (isa(a, b)) b
      else if (isa(b, a)) a
      else throw new TypeException("Can't find common parent of " + t1 + " and " + t2, n)
    case _ =>
      throw new TypeException("Can't find common parent of " + t1 + " and " + t2, n)
  }
  * 
  */
  
  /**
   * 
   */
  
  def unify(t1: Ty, t2: Ty, s: Subs, n: Node, gen: TyvarGenerator) : Subs = {
    val s1 = s(t1)
    val s2 = s(t2)
    (s1, s2) match {
      
      case (a @ Tyvar(na, List()), b @ Tyvar(nb, _)) =>
        if (na == nb) s
        else s.extend(a, b)
          
      case (_, a @ Tyvar(na, List())) =>
        unify(t2, t1, s, n, gen)
      
      case (a @ Tyvar(na, resa), b @ Tyvar(nb, resb)) => 
        val allRestrictions = (resa ++ resb).distinct
        val newTyvar = Tyvar(gen.get().name, allRestrictions)
        s.extend(a, newTyvar).extend(b, newTyvar)  
      
      case (a @ Tyvar(name, res), x @ Tycon(nam, tv, res1)) if !(tyvars(x) contains a) =>
        //println("****** I have to guarantee that " + x + " meets the restrictions " + res)
        val res2 = res1.flatMap { z => z.all }.distinct
        res.foreach { z => 
          //println("Does " + x.repr + " guarantee " + z + "?")
          //println("  res2 = " + res2)
          if (!res2.contains(z)) 
            throw new TypeException("Can't unify " + s1.repr + " with " + s2.repr + ": The type " + x.repr + " does not guarantee " + z, n)
        }
        s.extend(a, x)

      case (a @ Tyvar(name, List()), x) if !(tyvars(x) contains a) =>
        s.extend(a, x)

      case (a @ Tyvar(name, res), x) if !(tyvars(x) contains a) =>
        println("****** I have to guarantee that " + x + " meets the restrictions " + res)
        throw new Exception("lalilo")
        
      case (Tyfn(in1, out1), Tyfn(in2, out2)) =>
        if (in1.length != in2.length) throw new TypeException("Arguments do not match: " + in1.length + " vs " + in2.length, n)
        val s1 = (s /: (in1 zip in2)) ((s, tu) => unify(tu._1, tu._2, s, n, gen))
        unify(out1, out2, s1, n, gen)
        
      case (Tycon(n1, tv1, isa1), Tycon(n2, tv2, isa2)) if (n1 == n2) =>
        (s /: (tv1 zip tv2)) ((s, tu) => unify(tu._1, tu._2, s, n, gen))
      
      case _ => throw new TypeException("Can't unify " + s1.repr + " with " + s2.repr, n)
    }
  }
  
  /**
   * 
   */
  def tp(env: Env, n: Node, t: Ty, gen: TyvarGenerator, s: Subs) : Subs = {
    n.env = env
    val tysub = n match {
      
      case a @ NForward(name, gty) =>
        val ty = Typegrammar.toType(gty, env)
        env.put(name + "$$forward", ty)
        unify(t, ty, s, n, gen)
      
      case a : NInt => unify(t, intType, s, n, gen)
      case a : NFloat => unify(t, floatType, s, n, gen)
      case a : NString => unify(t, stringType, s, n, gen)
      
      case a @ NRef(name) =>
        val u = env.get(name)
        u match {
          case None => throw new TypeException("Can't find '" + name + "' in the environment", n)
          case Some(typescheme) => unify(t, typescheme.newInstance(gen), s, n, gen)
        }

      case a @ NDef(name, ex) =>
        val u = env.get(name)
        u match {
          case Some(typescheme) => throw new TypeException("'" + name + "' is already defined", n)
          case None =>
            // is there a forward definition?
            env.get(name + "$$forward") match {
              case Some(ts) =>
                // There is a forward definition. 
                // if we are defining a function, it can't have typed params 
                
                ex match {
                  case x @ NFn(params, ex) =>
                    if (x.hasTypedArgs) 
                      throw new TypeException("Function " + name + " can't have typed arguments because it has a forward definition", a)
                    
                  case _ =>
                }
                
                val forward = ts.newInstance(gen)
                val env1 = env
                env1.put(name, forward)
                //
                val s1 = tp(env1, ex, t, gen, s)
                //val computed = s1(t)

                //println("The expression type is " + t)
                //println("The forward definition of " + name + " is " + forward.repr)
                //println("The computed type of " + name + " is " + computed.repr)
                
                //val s2 = unify(t, computed, s, n)
                //println("s2 = " + s2)
                
                //val r = unify(computed, forward, s2, n)
                //println("r = " + r(t).repr)
                s1
                
                
              case None =>
                val env1 = env
                val a = gen.get()
                env1.put(name, a)
            
                val s1 = tp(env1, ex, t, gen, s)
                env.put(name, s1(t))
                s1
            }
        }
      
      case x @ NFn(params, ex) =>
        def f(p: NFnArg) : Ty = {
          val n = p.name
          val t = p.klass
          //val tt = gen.get()
          t match {
            case KlassConst(name) => 
              env.getType(name) match {
                case None => throw new TypeException("No type " + name + " defined", x)
                case Some(tt) => tt
              }
            case KlassVar(name) => gen.get() 
          }
        }
        val a = params.map { x => f(x) }
        val b = gen.get()
        val s1 = unify(t, Tyfn(a, b), s, n, gen)
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
            case e: NoCandidateException =>
              throw e
              
            case e: TypeException => 
              //println("  This one did NOT work " + e)
              //e.printStackTrace()
              null
          }
        }.filterNot { _ == null }
        
        val candsnames = cands.map(_._1)
        val fwd = name + "$$forward"
        val finalcands = 
          if (cands.length == 2 && candsnames.contains(fwd)) cands.filterNot(_._1 == fwd)
          else cands
        
        if (finalcands.length == 0) throw new NoCandidateException("No candidates for '" + name + "'", n)
        else if (finalcands.length > 1) throw new NoCandidateException("Too many candidates for '" + name + "'", n)
        else {
          x.realName = finalcands(0)._1.replace("$$forward", "")
          finalcands(0)._2
        }
                
      case NIf(cond, e1, e2) =>
        val a, b, c = gen.get()
        
        //println("starting = " + s)
        
        //val condenv = Env(env, cond)
        //val trueenv = Env(env, e1)
        //val falsenv = Env(env, e2)
        
        val condenv = env
        val trueenv = env
        val falsenv = env
        
        val conds = tp(condenv, cond, a, gen, s)
        val trues = tp(trueenv, e1, b, gen, conds)
        val falss = tp(falsenv, e2, c, gen, trues)
        
        //println("Variables are " + t.repr + " " + (a.repr, b.repr, c.repr))
        //println("conds  = " + conds)
        //println("trues  = " + trues)
        //println("falses = " + falss)
        //println("x      = " + env.get("x"))
        
        val s2 = unify(a, boolType, falss, n, gen)
        val s3 = unify(b, c, falss, n, gen)
        val s4 = unify(t, s3(b), s3, n, gen)
        //println("s4     = " + s4)
        
        s4
        
      case NBlock(exs) =>
        //val env1 = Env(env, n)
        val env1 = env
        var b: Tyvar = null
        val s1 = (s /: exs) { (s2, ex) =>
          b = gen.get()
          //println("I'm typing a block expression " + b + " " + ex)
          val x = tp(env1, ex, b, gen, s2)
          //println ("Result: " + x)
          x
        }
        unify(t, s1(b), s1, n, gen)
        
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
