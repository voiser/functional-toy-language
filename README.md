# What?

This is a tiny functional toy compiler for the JVM. The language is built on top of Martin Odersky's Hindley Milner implementation that can be found in http://www.scala-lang.org/docu/files/ScalaByExample.pdf

The idea of this language is that type variables can be restricted to an interface, in the spirit of Haskell typeclasses. For instance:

    f = { x -> x + 1 }
  
The type of this function is inferred:

    f : a+Num -> a+Num

That means that f is a function that takes anything, as long as it's a number, and returns some other thing with the same type. Interfaces (well, typeclasses) are defined like:

    interface Set[a] {
      size : this -> Int
    }

This interface defines a function:

    size : Set[a] -> Int
  
For any type declared as Set[z], the function size must be overriden. Suppose a Box, which is a Set that contains only one element:

    class Box(x) is Set[x] {
      size(this) = 1
    }

# some other examples

You can find many code snippets in the test cases, but at a glance:

    module test
    
    a = 1
    f1 = { x -> x + 1 } // x is inferred as a+Num
    f2(x) = x + 1 // syntactic sugar, same as f1
    
    f3(x Int) = x + 1 // x is not inferred, it's defined as Int
    
    f4(x) = size(x) // x inferred as a+Set[b]
    
    b = [1, 2, 3] // syntactic sugar for cons(1, cons(2, list(3)))
    b.size() // Syntactic sugar for size(b)
    
    c = ["a": 1, "b": 2] // syntactic sugar for extend("a", "1", dict("b", 2))
    
    size([1, 2]) // static dispatch: List$size will be called
    size(["a": 1, "b": 2]) // idem. Dict$size will be called
    
    mysize(x) = size(x) // x is inferred as a+Set[x], so it will dynamically dispatched.
                        // In runtime, a concrete version of size will be deduced by checking the actual type of x
    
    d = if a == 1 then 9 else 8 // both branches of 'if' should return the same type
    
    class Pair(a, b) // This is a simple bean.
    
    z = Pair(1, Pair(2, 3)) // Constructors
    z.a // object-like member access
    
    e = if z is Pair(a, x Pair(b, c)) then x // Pattern matching: e will be Pair(2, 3)
  
  
  

# compiler overview 

  - Parse the input file with ANTLR4 to generate an AST (Main.scala)
  - Perform some transformations to simplify the AST (Main.scala)
  - Type it using Hindley Milner (Typer3.scala)
  - Perform more transformations and other annotations on the AST (Main.scala)
  - Generate a compilation unit (CompilationUnit.scala)
  - Translate to an intermediate code (Intermediate.scala)
  - Generate the final JVM code (Codegen.scala)

