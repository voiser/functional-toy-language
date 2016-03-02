package runtime;

public class list extends Func {

	private static nil nil = new nil();
	
	@Override
	public Thing apply1(Thing a) {
		return new List(a, nil);
	}
}
