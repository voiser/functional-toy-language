
sbt=./sbt/bin/sbt

all: test package

test:
	${sbt} test

package:
	${sbt} assembly
	cp target/scala-2.11/l.jar .

antlr:
	java -Xmx500M -cp "lib/antlr-4.5.1-complete.jar" org.antlr.v4.Tool -visitor -package ast2 src/main/antlr/Grammar.g4 
	java -Xmx500M -cp "lib/antlr-4.5.1-complete.jar" org.antlr.v4.Tool -visitor -package ast2 src/main/antlr/Typegrammar.g4 
	mv src/main/antlr/*java src/main/java/ast2

sbt:
	${sbt}

.PHONY: sbt
