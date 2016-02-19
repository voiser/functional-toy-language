package ast2

/**
 * This type checker is based in the fantastic Martin Odersky's implementation of Hindley Milner
 *
 * http://www.scala-lang.org/docu/files/ScalaByExample.pdf
 *
 * @author david
 */

/**
 * Traverses a syntax tree, typing each expression and checking for correctness
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
        val z = lookup(x)
        z match {
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
  def checkedType(gty: GTy, env: Env, n: Node) (implicit gen: TyvarGenerator, trace: List[TraceElement]) : Ty =
    checkedType(Typegrammar.toType(gty), env, n)

  def checkedType(ty: Ty, env: Env, n: Node) (implicit gen: TyvarGenerator, trace: List[TraceElement]) : Ty =
    ty match {
      case z @ Tycon(tname, tparams) =>
        env.get(tname) match {
          case None => exception("Reference to unknown type " + tname, n)
          case Some(TypeScheme(tv, tp)) =>
            if (tparams.size != tv.size) exception("Incorrect number of type parameters for type " + tname + ": given " + tparams.size + ", needed " + tv.size, n)
            z
        }
      case z @ Tyvar("this", List()) =>
        env.introducedBy match {
          case k @ NClass(cname, _, _, _) =>
            env.get(cname) match {
              case Some(ts) => ts.newInstance(gen).asInstanceOf[Tyfn].out
              case _ => throw new Exception("This is a compiler bug")
            }
          case _ => exception("'this' can be used only inside a class", n)
        }
      case z @ _ => z
    }

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
                val neworig = TypeScheme(tyvars(orig), orig).newInstance(gen)
                val s1 = unify(x, neworig, s, n)
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

    (s1, s2) match {

      case (a @ Tyvar(na, List()), b @ Tyvar(nb, List())) =>
        // println("case 1")
        if (na == nb) s
        else s.extend(a, b)

      case (_, a @ Tyvar(na, List())) =>
        // println("case 2")
        unify(t2, t1, s, n)

      case (a @ Tyvar(name, List()), x) if !(tyvars(x) contains a) =>
        // println("case 3")
        s.extend(a, x)

      case (Tyfn(in1, out1), Tyfn(in2, out2)) =>
        // println("case 4")
        if (in1.length != in2.length) exception("Arguments do not match. Given " + in1.length + ", required " + in2.length, n)
        val s1 = (s /: (in1 zip in2)) ((s, tu) => unify(tu._1, tu._2, s, n))
        unify(out1, out2, s1, n)

      case (Tycon(n1, tv1), Tycon(n2, tv2)) if (n1 == n2) =>
        // println("case 5")
        (s /: (tv1 zip tv2)) ((s, tu) => unify(tu._1, tu._2, s, n))

      case (a : Tycon, b @ Tyvar(_, rs)) =>
        // println("case 6")
        unify(t2, t1, s, n)

      case (a @ Tyvar(nam, rs), b : Tycon) =>
        // println("case 7")
        val s1 = (s /: rs) ((s, r) => checkRestriction(r, b, s, n))
        s1.extend(a, b)

      case (a @ Tyvar(n1, r1), b @ Tyvar(n2, r2)) if (r1 == r2) =>
        // println("case 8")
        if (n1 == n2) s
        else s.extend(a, b)

      case (a @ Tyvar(n1, r1), b @ Tyvar(n2, r2)) if (r1.size > r2.size) =>
        // println("case 9")
        exception("Can't match " + a.repr + " to a less restrictive " + b.repr, n) // shouldn't it try to unify in reverse?

      case (a @ Tyvar(n1, r1), b @ Tyvar(n2, r2)) =>
        // println("case 10")
        val pairs = r1 map { r =>
          val cands = r match {
            case x @ Isa(Tycon(name, _)) =>
              r2.collect {
                case y @ Isa(Tycon(name2, _)) if (name2 == name) => y
              }
          }
          if (cands.length != 1) exception("Can't match restriction " + r.repr + ": " + cands.map{_.repr}, n)
          (r, cands(0))
        }
        val s1 = (s /: pairs) { (s, pair) =>
          pair match {
            case (Isa(x), Isa(y)) => unify(x, y, s, n)
          }
        }
        s1.extend(a, b)

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
   * When a function has a forward definition, checks that the forward and its inferred types are equivalent.
   * Two types are equivalent iff (a) they have the same number of type variables and (b) they are unifiable
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
  def checkForward(fname: String, t1: Ty, t2: Ty, n: Node, nf: Node, trace: List[TraceElement]) = {
    val v1 = Typer3.tyvars(t1)
    val v2 = Typer3.tyvars(t2)
    if (v1.length != v2.length) false
    else {
      try {
        Typer3.unify(t1, t2, Typer3.emptySubst, null)(null, List())
        true
      }
      catch {
        case x: TypeException => false
      }
    }
  }

  /**
   *
   */
  def basicType(name: String, env: Env, n: Node) (implicit gen: TyvarGenerator, trace: List[TraceElement]) =
    env.get(name) match {
      case Some(ts) => ts.newInstance(gen)
      case None => exception("Failed to locate a basic type. This is a compiler bug.", n)
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

    // Utility function to add a trace
    def trac(s: String) = mktrace(s, n, trace)

    // Keep in mind that when typing a node "n", a type "t" has already been assigned to the node
    val tysub = n match {

      /*
       * Forward expression
       * Store the forward type
       * Unify t with the given type
       *
       *   f :: Int -> Int
       *
       * env("f$$forward") = Int -> Int
       */
      case a @ NForward(name, gty) =>
        val ty = Typegrammar.toType(gty)
        env.put(name + "$$forward", ty)
        env.putForward(name + "$$forward", a)
        //unify(t, ty, s, n)(gen, trac("When typing forward definition"))
        s

      /*
       * Basic expressions
       * Unify t with the corresponding base type
       */
      case a : NInt => unify(t, basicType("Int", env, n), s, n)
      case a : NFloat => unify(t, basicType("Float", env, n), s, n)
      case a : NString => unify(t, basicType("Str", env, n), s, n)
      case a : NBool => unify(t, basicType("Bool", env, n), s, n)

      /*
       * Reference expression, like in
       *
       *   def a = 9
       *   def b = a
       *
       * Find the referenced name's type in the env
       * unify t with it
       */
      case NRef(name) =>
        env.get(name) match {
          case None => throw new TypeException("Can't find '" + name + "' in the environment", n, trace)
          case Some(typescheme) => unify(t, typescheme.newInstance(gen), s, n)
        }

      /*
       * Definition expression
       */
      case a @ NDef(name, ex) =>
        val u = env.get(name)
        u match {

          /*
           * The symbol is defined
           */

          case Some(typescheme) =>
            env.introducedBy match {

              /*
               * We are in a class definition
               */
              case z @ NClass(cname, _, _, _) =>

                val theKlass = env.getClass(cname) match {
                  case Some(k) => k
                  case None => throw new Exception("This is a compiler bug")
                }
                val ktycon = theKlass.constructor.tpe.asInstanceOf[Tyfn].out.asInstanceOf[Tycon]
                val a = gen.get()
                val s1 = tp(env, ex, a, s)
                val defining = s1(a)

                // find which interface defines the function
                val (interface, wildcarded) = env.interfaceFor(name) match {
                  case Some(((a, b), (c, d))) => (b, d)
                  case None => throw new Exception("This is a compiler bug")
                }
                val matchingInterface = theKlass.isas collect {
                  case z @ Tycon(name, _) if name == interface.name => z
                }

                if (matchingInterface.isEmpty) exception("Overriding method " + name + " of interface " + interface.name + " in a class that does not implement it", n)

                val t2 = emptySubst.extend(Tyvar.wildard, ktycon)(wildcarded).asInstanceOf[Tyfn]
                try {
                  unify(t2, defining, s1, n)
                }
                catch {
                  case e : TypeException =>
                    val te1 = TraceElement("When overloading function " + name, null)
                    val te2 = TraceElement("When defining class " + cname, null)
                    val te3 = TraceElement("Expected type: " + t2.repr, null)
                    val te4 = TraceElement("Given type: " + defining.repr, null)
                    exception("Incompatible types", n)(te1 :: te2 :: te3 :: te4 :: e.trace)
                }

                ex.asInstanceOf[NFn].isOverride = (theKlass, name, t2)
                unify(t, t2, s1, n)

              /*
               * We are outside a class definition
               */
              case _ => throw new TypeException("'" + name + "' is already defined", n, trac("When typing the definition of " + name))
            }

          case None =>
            (env.get(name + "$$forward"), ex) match {

              /*
               * No forward definition
               *
               *   def x = ...
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
               *   f :: Int -> Int
               *   def f = { ... }
               *
               * Infer the function type by typing the function expression.
               * The forward and inferred types should be unifiable and equivalent (see #checkForward)
               */
              case (Some(ts), ex2 @ NFn(params, _)) =>
                val fwd = getForward(ex, env, name + "$$forward")
                val fwdty = ts.tpe
                val newtrace = mktrace("The forward definition is " + fwd.ty.repr, fwd, trace)
                if (ex2.hasTypedArgs) throw new TypeException("Function " + name + " can't have typed arguments because it has a forward definition", ex2.typedArgs(0), newtrace)
                fwdty match {
                  case forward: Tyfn =>
                    ex2.fwdty = forward
                    env.put(name, forward)
                    val s1 = tp(env, ex2, t, s)
                    val computed = s1(t)
                    val valid = checkForward(name, forward, computed, n, fwd, trace)
                    if (!valid) {
                      val t1 = TraceElement("The computed definition is " + computed.repr, n)
                      val t2 = TraceElement("The forward definition is  " + forward.repr, fwd)
                      exception("The forward definition of " + name + " does not match", fwd)(t1 :: t2 :: trace)
                    }
                    s1
                }

              /*
               * There is a forward definition, and it's not a function
               *
               *   f :: List[Int]
               *   def f = [1, 2, 3]
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
                checkForward(name, forward, computed, n, fwd, trace)
                env.put(name, s2(t))
                s2
            }
        }

      /*
       * Function expression
       *
       *   { x, y Int -> add(x, y) }
       *
       * generate new function type:
       *
       *   t' = a, Int -> c
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
        val newtype =
          if (x.fwdty != null) TypeScheme(tyvars(x.fwdty), x.fwdty).newInstance(gen).asInstanceOf[Tyfn]
          else {
            val in = params.map { p =>
              p.klass match {
                case KlassConst(gty) => checkedType(gty, env, p)
                case KlassVar(_) => gen.get()
              }
            }
            val out = gen.get()
            Tyfn(in, out)
          }
        if (params.length != newtype.in.length) throw new TypeException("Incorrect arity", n, trace)
        val env1 = Env(env, n)
        (params zip newtype.in) foreach { x =>
          val ts = TypeScheme(List(), x._2)
          env1.put(x._1.name, ts)
        }
        val s1 = unify(t, newtype, s, n)
        val s2 = tp(env1, ex, newtype.out, s1)

        val env2 = Env(env, n)
        (params zip newtype.in) foreach { x =>
          val ts = TypeScheme(List(), s2(x._2))
          env2.put(x._1.name, ts)
        }
        val s3 = tp(env2, ex, newtype.out, s2)
        s3

      /*
       * Function application
       *   f(a, b, c)
       */
      case x @ NApply(name, args) =>
        val a = args.map { x => gen.get("__IN__"+name) }
        val t2 = Tyfn(a, t)
        val candidates = env.get2(name).map { _._1}.sortBy { x => x.length }

        def typ(n: String) (implicit trace: List[TraceElement]) = {
          val ttt = env.get(n) match {
            case Some(ts) => ts.newInstance(gen)
            case None => throw new RuntimeException("This is a compiler bug")
          }
          val s1 = unify(t2, ttt, s, x)

          /*
          val r = NRef(n)
          r.ctx = x.ctx
          val s1 = tp(env, r, t2, s)
          */
          //val s1 = s
          val s2 = (s1 /: (args zip a)) ((s2, arg) => tp(env, arg._1, arg._2, s2))
          s2
        }

        val res = candidates match {

          /*
           * The function is not defined
           */
          case List() =>
            throw new TypeException("Can't find definition of '" + name + "'", n, trace)

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
        x.resolvedType = res(t2) match {
          case x : Tyfn => x
        }
        res

      /*
       * object-style calls
       *
       *   x.f(a, b, c)
       *
       * is typed like
       *
       *   apply("[type name of x].f", x, a, b, c)
       */
      case x @ NObjApply(z , y @ NApply(fname, params)) =>
        val t1 = gen.get()
        val s2 = tp(env, z, t1, s)
        val basetype = s2(t1) match {
          case Tycon(tname, _) => tname
          case x @ _ =>
            throw new TypeException("Can't make a object-style function call on " + x.repr, n, trac("When typing an object-style function call"))
        }
        val realfname = basetype + "$" + fname
        val node = NApply(realfname, z :: params)
        val s1 = tp(env, node, t, s)
        y.realName = node.realName
        y.isRecursive = node.isRecursive
        y.resolvedType = node.resolvedType
        s1

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
        var b: Tyvar = gen.get()
        val s1 = (s /: exs) { (s2, ex) =>
          b = gen.get()
          tp(env1, ex, b, s2)
        }
        unify(t, s1(b), s1, n)

      /*
       * Class definition
       * Register the class and the constructor in the environment
       * The type of the definition of class X[...] is Class[X[...]]
       * Unify t with Class[X[...]]
       * Create a new child environment and register the class members into it
       * Type the class block
       */
      case z @ NClass(name, params, isas, block) =>
        val members = params.map { pair =>
          val name = pair._1
          val gty = pair._2
          (name, Typegrammar.toType(gty))
        }
        val typedParams = members map {_._2}
        val vars = (typedParams map tyvars).flatten.distinct
        val y = Tycon(name, vars)
        val x = Tycon("Class", List(y))
        Main.registerTy(null, y, List())(env)
        val constructor = TypeScheme(vars, Tyfn(typedParams, y))
        env.put(name, constructor)
        val isas2 = isas map (Typegrammar.toType(_).asInstanceOf[Tycon])
        val k = new Klass(name, constructor, isas2)
        members.foreach { member =>
          k.addField(member._1, member._2)
        }
        k.definedat = z
        env.putClass(k)
        val s1 = unify(t, x, s, n)
        Main.isa(y, isas2 map {_.repr})(env)
        val env1 = Env(env, z)
        members.foreach { member =>
          env1.put(member._1, member._2)
        }
        val tt = gen.get()
        tp(env1, block, tt, s1)

      /*
       * Class instantiation
       *
       *   class Pair(left l, right r)
       *   aPair = Pair("hi", 3)
       *
       * Type the call of the constructor like a normal function call.
       */
      case z @ NInstantiation(classname, params) =>
        env.get(classname) match {
          case None => throw new TypeException("Can't find class " + classname, n, trace)
          case Some(ts @ TypeScheme(_, zz : Tyfn)) =>
            val constructor = ts.newInstance(gen)
            constructor match {
              case Tyfn(ins, out) =>
                if (ins.length != params.length) throw new TypeException("Constructor parameters do not match. Given " + params.length + " needed " + ins.length, n, trace)
                val s2 = (s /: (params zip ins)) ((s, x) => tp(env, x._1, x._2, s))
                unify(t, out, s2, n)
            }
          case Some(ts) => exception("Can't instantiate " + ts.tpe.repr, z)
        }

      /*
       * Access an instance field
       *
       *   class Pair(left l, right t)
       *   myPair = Pair("one", 1)
       *   myPair.left
       *
       * Find the class definition:
       *
       *   class Pair(left l, right r)
       *
       * Find the instance definition:
       *
       *   myPair = Pair("one", 1)
       *
       * Unify the arguments:
       *
       *   l -> Str
       *   r -> Int
       *
       * Find the field type
       *
       *   Pair.left -> l
       *
       * Apply the unification
       *
       *   myPair.left -> Str
       *
       */
      case z @ NField(owner, field) =>
        val o = gen.get()
        val s1 = tp(env, owner, o, s)
        val obj = s1(o)
        val className = obj match {
          case Tycon(name, _) => name
          case _ => throw new TypeException("Accessing a field of an unknown type instance", n, trace)
        }
        val klass = env.getClass(className) match {
          case Some(k) => k
          case None => throw new TypeException("Can't access field " + field + " of class " + s1(o).repr, n, trace)
        }
        val ctor = klass.constructor.tpe match {
          case x : Tyfn => x
          case _ => throw new TypeException("This is a compiler bug", n, trace)
        }
        val ss = unify(ctor.out, obj, emptySubst, n)
        val f = klass.fields.find(_.name == field) match {
          case None => throw new TypeException("Field " + field + " not found in class " + className, n, trace)
          case Some(f2) => f2
        }
        val fieldty = ss(f.ty)
        z.klass = klass
        unify(t, fieldty, s, n)


      /*
       *
       */
      case NMatch(source, pattern, block) =>
        val a = gen.get()
        val s1 = tp(env, source, a, s)
        val sourcety = s1(a) match {
          case x : Tycon  => x
          case x : Ty => x // throw new TypeException("Trying to match a non-class value " + x.repr, n, trace)
        }
        val patty = tyForPattern(n, pattern)

        pattern match {

          case _:PVar => ???

          case PClass(_, name, params) =>
            val klass = env.getClass(name) match {
              case Some(k) => k
              case None => throw new TypeException("Unknown class " + name, n, trace)
            }
            val ctor = Tycon(name, tyvars(klass.ctor))
            val canonic = Tycon(name, klass.ctor.in)
            val selma = try {
              unify(ctor, sourcety, emptySubst, n)
            }
            catch {
              case e : TypeException =>
                exception("Value and pattern can't match", n)
            }
            val ctor2 = selma(Tycon(name, klass.ctor.in))
            val s2 = unify(patty, ctor2, selma, n)
            val marge = s2(patty).asInstanceOf[Tycon]
            val env1 = Env(env, n)
            (tyvars(patty) zip marge.types).foreach {
              case (ta,tb) => env1.put(ta.name, tb)
            }
            val b = gen.get()
            val s3 = tp(env1, block, b, s)
            unify(t, b, s3, n)
        }
    }
    n.ty = tysub(t)
    tysub
  }

  /**
   * Utility function to append a new trace element to an already existing trace
   */
  def mktrace(t: String, n: Node, trace: List[TraceElement]) = TraceElement(t, n) :: trace

  def tyForPattern(n: Node, pattern: Pattern) : Ty = pattern match {
    case PVar(_, name) => Tyvar(name, List())
    case PClass(_, name, params) =>  Tycon(name, params map (tyForPattern(n, _)))
  }

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
