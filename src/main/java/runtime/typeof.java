package runtime;

import ast2.GTy;
import ast2.TypeVisitor;
import ast2.TypegrammarLexer;
import ast2.TypegrammarParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

public class typeof extends Func {

    private GTy getTy(String text) {
        TypegrammarLexer lexer = new TypegrammarLexer(new ANTLRInputStream(text));
        TypegrammarParser parser = new TypegrammarParser(new CommonTokenStream(lexer));
        TypegrammarParser.TyContext ctx = parser.ty();
        return new TypeVisitor().visitTy(ctx);
    }

	@Override
	public Thing apply1(Thing a) {

        String type;
        Class<?> k = a.getClass();
        try {
             type = k.getDeclaredField("type").get(a).toString();
        } catch (NoSuchFieldException | IllegalAccessException e) {
             type = k.getName();
        }

        //return new Java(getTy(type));
        return Str.from(type);
	}
}
