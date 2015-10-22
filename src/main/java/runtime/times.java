package runtime;

public class times extends Func {

	@Override
	public Thing apply2(Thing a, Thing b) {
		return (Thing)(((Num)a).times((Num)b));
	}
}
