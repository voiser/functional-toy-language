package runtime;

public class cons extends Func {

	@Override
	public Thing apply2(Thing a, Thing l) {
		return new List(a, (List)l);
	}
}
