
sbt=./sbt/bin/sbt

test:
	${sbt} test

antlr:
	java -Xmx500M -cp "lib/antlr-4.5.1-complete.jar" org.antlr.v4.Tool -visitor -package ast2 grammar/Grammar.g4 
	mv grammar/*java src/main/java/ast2


