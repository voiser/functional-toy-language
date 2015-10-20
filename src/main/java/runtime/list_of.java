package runtime;

public class list_of extends Func {

	private static Nil nil = new Nil();
	
	@Override
	public Thing apply1(Thing a) {
		return new List(a, nil);
	}
}
