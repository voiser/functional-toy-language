package runtime;

public class add extends Func {

	@Override
	public Thing apply2(Thing a, Thing b) {
		return (Thing)(((Num)a).add((Num)b));
	}
}
