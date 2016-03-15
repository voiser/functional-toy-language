# what?

This is a tiny functional toy compiler for the JVM. The language is built on top of Martin Odersky's Hindley Milner implementation that can be found in http://www.scala-lang.org/docu/files/ScalaByExample.pdf

# compiler overview 

  - Parse the input file with ANTLR4 to generate an AST (Main.scala)
  - Perform some transformations to simplify the AST (Main.scala)
  - Type it using Hindley Milner (Typer3.scala)
  - Perform more transformations and other annotations on the AST (Main.scala)
  - Generate a compilation unit (CompilationUnit.scala)
  - Translate to an intermediate code (Intermediate.scala)
  - Generate the final JVM code (Codegen.scala)

