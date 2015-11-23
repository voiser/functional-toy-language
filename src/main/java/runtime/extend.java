package runtime;

public class extend extends Func {

	@Override
	public Thing apply3(Thing k, Thing v, Thing m) {
		return ((Dict)m).extend(k, v);
	}
}
