package runtime;

public class div extends Func {

	@Override
	public Thing apply2(Thing a, Thing b) {
		return (Thing)(((Num)a).div((Num)b));
	}
}
