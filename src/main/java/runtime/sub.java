package runtime;

public class sub extends Func {

	@Override
	public Thing apply2(Thing a, Thing b) {
		return (Thing)(((Num)a).sub((Num)b));
	}
}
