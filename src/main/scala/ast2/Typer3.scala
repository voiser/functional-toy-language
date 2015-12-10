package ast2

/**
 * This type checker is based in the fantastic Martin Odersky's implementation of Hindley Milner  
 * 
 * http://www.scala-lang.org/docu/files/ScalaByExample.pdf 
 * 
 * @author david
 */

/**
 * Traverses a systax tree, typing each expression and checking for correctness
 */
object Typer3 {
  
  /**
   * Some basic types
   */
  val intType = Tycon("Int", List())
  val stringType = Tycon("Str", List())
  val floatType = Tycon("Float", List())
  val boolType = Tycon("Bool", List())

  /**
   * Obtains all type variables from a type
   */
  def tyvars(t: Ty) : List[Tyvar] = t match {
    case tv @ Tyvar(name, rs) => (rs flatMap {_.tyvars}) ++ List(tv) 
    case Tyfn(in, out) => 
      val x = (in flatMap { x => tyvars(x) }) 
      x union tyvars(out) distinct
    case Tycon(k, ts) => (List[Tyvar]() /: ts) ((tvs, t) => tvs union tyvars(t)) distinct
  }
  
  /**
   * Models a type substitution.
   * 
   * a = Tyvar("a")
   * b = Tyvar("b")
   * int = Tycon("int")
   * s = emptySubst.extend(a, int) 
   * 
   * s(a) -> int
   * s(b) -> b
   */
  abstract class Subs extends Function1[Ty, Ty] {
  
    def lookup(t: Tyvar) : Ty
    
    def repr: String;
    
    override def toString() = "subs: " + repr
    
    def apply(a: Ty) : Ty = a match {
      case x @ Tyvar(name, List()) =>
        val v = lookup(x)
        if (v == a) a
        else apply(v)
        
      case x @ Tyvar(name, res) =>
        lookup(x) match {
          case Tyvar(name2, res) =>
            Tyvar(name2, res map apply)
          case x => x 
        }
        
      case Tyfn(params, out) =>
        Tyfn(params map apply, apply(out))
        
      case Tycon(name, types) =>
        Tycon(name, types map apply)
    }
    
    def apply(a: Restriction) : Restriction = a match {
      case Isa(t) => apply(t) match {
        case x : Tycon => Isa(x)
        case _ => throw new Exception("what?")
      }
    }

    def extend(origin: Tyvar, dest: Ty) : Subs = new Subs {
      def lookup(t: Tyvar) = if (t == origin) dest else Subs.this.lookup(t)
      def repr = origin.repr + " => " + dest.repr + "; " + Subs.this.repr 
    }
  }
  
  /**
   * The empty substitution.
   */
  val emptySubst = new Subs { 
    def lookup(t: Tyvar) : Ty = t
    def repr = ""
  }
  
  /**
   * 
   */
  def exception(message: String, n: Node) (implicit trace: List[TraceElement]) =
    throw new TypeException(message, n, trace)
  
  
  /**
   * 
   */
  def checkRestriction(r: Restriction, t: Ty, s: Subs, n: Node) (implicit gen: TyvarGenerator, trace: List[TraceElement]) : Subs = {
    (t, r) match {
      case (x : Tycon, ty @ Isa(Tycon(name, params))) =>
        //println("I am checking that " + x.repr + " is a " + ty.repr)
        n.env.getIsa(x.name) match {
          case None => exception("Can't check restriction", n)
          case Some((orig, isa)) =>
            isa.collect {
              case x @ Tycon(n, tys) if (n == name) => x
            } match {
              case List() => exception("Can't check restriction. Seems that " + x.repr + " is not " + ty.repr, n)
              case List(parent) =>
              val s1 = unify(x, orig, s, n)
              val real = s1(parent)
              //println("OK, it seems that " + x.repr + " is a " + real.repr)
              unify(ty.ty, real, s1, n)
            }
        }
    }
  }
  
  /**
   * Unifies two types, that is, finds a substitution that applied to the first type gives the second one.
   * 
   * s = emptySubst
   * a = Tyvar("a")
   * int = Tycon("int")
   * 
   * s1 = unify(a, int, s)
   * 
   * s1(a) -> int
   */
  def unify(t1: Ty, t2: Ty, s: Subs, n: Node) (implicit gen: TyvarGenerator, trace: List[TraceElement]) : Subs = {
    val s1 = s(t1)
    val s2 = s(t2)
    val tr = mktrace("When unifying " + t2 + "(" + s1 + ") with " + t1 + "(" + s2 + ")", n, trace)
    
    // println("Unifying " + t1.repr + " (that is, " + s1.repr + ") and " + t2.repr + " (that is, " + s2.repr + ")")
    
    (t1, t2, s1, s2) match {
      
      case (_, _, a @ Tyvar(na, List()), b @ Tyvar(nb, List())) =>
        if (na == nb) s
        else s.extend(a, b)
          
      case (_, _, _, a @ Tyvar(na, List())) =>
        unify(t2, t1, s, n)

      case (_, _, a @ Tyvar(name, List()), x) if !(tyvars(x) contains a) =>
        s.extend(a, x)

      case (_, _, Tyfn(in1, out1), Tyfn(in2, out2)) =>
        if (in1.length != in2.length) exception("Arguments do not match. Given " + in1.length + ", required " + in2.length, n)
        val s1 = (s /: (in1 zip in2)) ((s, tu) => unify(tu._1, tu._2, s, n))
        unify(out1, out2, s1, n)
        
      case (_, _, Tycon(n1, tv1), Tycon(n2, tv2)) if (n1 == n2) =>
        (s /: (tv1 zip tv2)) ((s, tu) => unify(tu._1, tu._2, s, n))
      
      case (_, _, a : Tycon, b @ Tyvar(_, rs)) =>
        unify(t2, t1, s, n)
        
      case (_, _, a @ Tyvar(nam, rs), b : Tycon) =>
        val s1 = (s /: rs) ((s, r) => checkRestriction(r, b, s, n))
        s1.extend(a, b)
        
      case (_, _, a @ Tyvar(n1, r1), b @ Tyvar(n2, r2)) if (r1 == r2) =>
        if (n1 == n2) s
        else s.extend(a, b)
        
      case _ => exception("Type mismatch: incompatible types " + s1.repr + " and " + s2.repr, n)
    }
  }
  
  /**
   * Finds the forward definition node for a given function name
   */
  def getForward(x: Node, env: Env, n: String)(implicit trace: List[TraceElement]) = 
    env.getForward(n) match {
      case None => throw new TypeException("Can't find forward in env. This is a compiler bug", x, mktrace("When locating forward node", x, trace))
      case Some(fwd) =>
        fwd     
  }

  /**
   * When a function has a forward definition, checks that the forward and its inferred types are equivalent
   * 
   * Suppose: 
   * 
   * f :: a+Num, b+Num -> c+Num
   * def f = { x, y -> add(x, y) }
   * 
   * The inferred type is:
   * 
   * a+Num, a+Num -> a+Num
   * 
   * The inferred and forward types can be unified, but the forward 
   * definition is not valid (it's more general than the inferred). 
   * 
   */
  def checkForward(fname: String, forward: Ty, computed: Ty, n: Node, nf: Node, trace: List[TraceElement]) = {
    def regen(ty: Ty) = {
      val gen = new TyvarGenerator("t")
      (emptySubst /: tyvars(ty))((s, t) => s.extend(t, gen.get()))(ty)
    }
    val cleanf = regen(forward)
    val cleanc = regen(computed)
    val matches = (cleanf.repr == cleanc.repr)
    if (!matches) {
      val t1 = TraceElement("The computed definition is " + cleanc.repr, n)
      val t2 = TraceElement("The forward definition is  " + cleanf.repr, nf)
      exception("The forward definition of " + fname + " does not match", nf)(t1 :: t2 :: trace) 
    }
  }
  
  /**
   * Finds the type of a syntax tree node. 
   * When traversing the AST, each node is assigned a new type variable. This method
   * finds a substitution that, applied to that type variable, gives the node's type.
   * A simple example:
   *  
   * def a = 1
   * 
   * The AST is NDef("a", NInt(1))
   * step 1     ^^^^^^^^^^^^^^^^^^ assign type t1     substitution = empty                     env("a") = t1
   * step 2               ^^^^^^^  try to type this
   * step 3                        assign type t2
   * step 4                        this is an int
   * step 5                        unify (t2, int)    substitution = (t2 -> int)
   * step 6     ^^^^^^^^^^^^^^^^^^ unify (t1, t2)     substitution = (t1 -> int, t2 -> int)    env("a") = substitution(t1) = int
   */
  def tp(env: Env, n: Node, t: Ty, s: Subs) (implicit gen: TyvarGenerator, trace: List[TraceElement]) : Subs = {
    n.env = env
    
    // println("Typing " + n)
    
    // Utility function to add a trace
    def trac(s: String) = mktrace(s, n, trace)
    
    // Keep in mind that when typing a node "n", a type "t" has already been assigned to the node
    val tysub = n match {
      
      /*
       * Forward expression
       * Store the forward type
       * Unify t with the given type
       * 
       * f :: Int -> Int
       * 
       * env("f$$forward") = Int -> Int
       */
      case a @ NForward(name, gty) =>
        val ty = Typegrammar.toType(gty)
        env.put(name + "$$forward", ty)
        env.putForward(name + "$$forward", a)
        unify(t, ty, s, n)(gen, trac("When typing forward definition"))
      
      /*
       * Basic expressions
       * Unify t with the corresponding base type
       */
      case a : NInt => unify(t, intType, s, n)(gen, trac("When typing Int"))
      case a : NFloat => unify(t, floatType, s, n)(gen, trac("When typing Float"))
      case a : NString => unify(t, stringType, s, n)(gen, trac("When typing String"))
      
      /*
       * Reference expression, like in 
       * 
       * def a = 9
       * def b = a
       * 
       * Find the referenced name's type in the env
       * unify t with it
       */
      case a @ NRef(name) =>
        val u = env.get(name)
        u match {
          case None => throw new TypeException("Can't find '" + name + "' in the environment", n, trace)
          case Some(typescheme) => unify(t, typescheme.newInstance(gen), s, n)
        }

      /*
       * Definition expression
       */
      case a @ NDef(name, ex) =>
        val u = env.get(name)
        u match {
          case Some(typescheme) => throw new TypeException("'" + name + "' is already defined", n, trac("When typing the definition of " + name))
          case None =>
            (env.get(name + "$$forward"), ex) match {
              
              /*
               * No forward definition
               * 
               * def x = ...
               * 
               * Type the expression, store its type in the environment and unify t with it
               * 
               */
              case (None, ex) =>
                val a = gen.get()
                env.put(name, a)
                val s1 = tp(env, ex, t, s)
                env.put(name, s1(t))
                s1
              
              /*
               * There is a forward definition and it's a function
               * 
               * f :: Int -> Int
               * def f = { ... }
               * 
               * Infer the function type by typing the function expression.
               * The forward and inferred types should be unifiable and equivalent (see #checkForward)
               */
              case (Some(ts), ex @ NFn(params, _)) =>
                val fwd = getForward(ex, env, name + "$$forward")
                val newtrace = mktrace("The forward definition is " + fwd.ty.repr, fwd, trace)
                if (ex.hasTypedArgs) throw new TypeException("Function " + name + " can't have typed arguments because it has a forward definition", ex.typedArgs(0), newtrace)
                val forward = ts.newInstance(gen)
                env.put(name, forward)
                val s1 = tp(env, ex, t, s)
                val computed = s1(t)
                val s2 = unify(forward, computed, s1, n)(gen, newtrace)
                checkForward(name, fwd.ty, s1(t), n, fwd, trace)
                env.put(name, s2(t))
                ex.ty = s2(t)
                s2
                
              /*
               * There is a forward definition, and it's not a function
               * 
               * f :: List[Int]
               * def f = [1, 2, 3]
               * 
               * Like the previous case without function-specific stuff
               */
              case (Some(ts), ex) =>
                val fwd = getForward(ex, env, name + "$$forward")
                val newtrace = mktrace("The forward definition is " + fwd.ty.repr, fwd, trace)
                val forward = ts.newInstance(gen)
                env.put(name, forward)
                val s1 = tp(env, ex, t, s)
                val computed = s1(t)
                val s2 = unify(forward, computed, s1, n)(gen, newtrace)
                checkForward(name, fwd.ty, s1(t), n, fwd, trace)
                env.put(name, s2(t))
                s2
            }
        }
      
      /*
       * Function expression
       * 
       * { x, y Int -> add(x, y) }
       * 
       * generate new function type:
       * 
       * t' = a, Int -> c
       * 
       * Unify t with t'
       * Generate a new env and store each parameter type:
       *   env' ("x") = a
       *   env' ("y") = Int
       * Type the function expression in this new environment
       * Store the inferred types:
       *   env' ("x") = Int
       *   env' ("y") = Int 
       */
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
      
      /*
       * Function application
       * f(a, b, c)
       */
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
          
          /*
           * The function is not defined
           */
          case List() =>
            throw new TypeException("Can't find definition of '" + name + "'", n, trac("When typing a function call"))
            
          /*
           * There is only the forward definition
           */
          case List(n) if (n.endsWith("$$forward")) =>
            val fwd = getForward(x, env, n)
            val newtrace = mktrace("The forward definition is " + fwd.ty.repr, fwd, trace)
            x.realName = n.replace("$$forward", "")
            typ(n)(newtrace)

          /*
           * The function is defined and there is no forward definition
           */
          case List(n) =>
            x.realName = n
            typ(n)
            
          /*
           * Both the real function and its forward declaration are defined
           */
          case List(a, b) if (b == a + "$$forward") =>
            x.realName = a
            typ(a)
          case _ =>
            throw new TypeException("Too many candidates for '" + name + "'. This is a compiler bug", n, trac("When typing a function call"))
        }
      
      /*
       * 'If' expression
       * 
       * Unify the condition with boolean and the two branches between themselves. 
       * Unify t with the branches type.
       */
      case NIf(cond, e1, e2) =>
        val a, b, c = gen.get()
        val conds = tp(env, cond, a, s)
        val trues = tp(env, e1, b, conds)
        val falss = tp(env, e2, c, trues)
        val s2 = unify(a, boolType, falss, n)(gen, trac("When typing a condition"))
        val s3 = unify(b, c, falss, n)(gen, trac("When typing a true branch"))
        val s4 = unify(t, s3(b), s3, n)(gen, trac("When typing a false branch"))
        s4
        
      /*
       * Block expression
       * Assign a new var to each subexpression, and type it.
       * Unify t with the last expression's type.
       */
      case NBlock(exs) =>
        val env1 = env
        var b: Tyvar = null
        val s1 = (s /: exs) { (s2, ex) =>
          b = gen.get()
          val x = tp(env1, ex, b, s2)
          x
        }
        unify(t, s1(b), s1, n)
    }
    n.ty = tysub(t)
    tysub
  }
  
  /**
   * Utility function to append a new trace element to an already existing trace
   */
  def mktrace(t: String, n: Node, trace: List[TraceElement]) = TraceElement(t, n) :: trace
  
  /**
   * It all starts here.
   */
  def getType(env: Env, n: Node) = {
    val gen = new TyvarGenerator("t")
    val rootVar = gen.get()
    val subs = tp(env, n, rootVar, emptySubst)(gen, List())
    n.ty
  }  
}
